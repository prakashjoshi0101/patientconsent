/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.src.kerberos.servers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.me.src.Business;
import com.me.src.kerberos.messages.Authenticator;
import com.me.src.kerberos.messages.ClientToServiceServer;
import com.me.src.kerberos.messages.ServiceTicket;
import com.me.src.security.DesEncrypter;
import com.me.src.session.Request;
import com.me.src.session.Response;

/**
 *
 * @author prakashj
 */
public class HelloServiceServer implements ServiceServer {

	private static final Logger logger = LoggerFactory.getLogger(HelloServiceServer.class);
	
    private boolean validateClient(ServiceTicket serviceTicket, Authenticator auth) {
        if (!auth.getUsername().equals(serviceTicket.getUsername())) {
            return false;
        }

        if (auth.getTime() < serviceTicket.getTimeTgs()) {
            return false;
        }

        if (!auth.getClientNetworkAddress().equals(serviceTicket.getClientNetworkAddress())) {
            return false;
        }

        // timeout
        if (serviceTicket.getTimeTgs() + serviceTicket.getLifetime() < auth.getTime()) {
            return false;
        }

        return true;
    }

    public String verifyServer(ClientToServiceServer clientToServiceServer) throws Exception {        
        Business business = Business.getInstance();

        // get Kv  from KeyStore      
        String serviceServerKey = business.getServerKey(Business.ServerEnum.HelloServiceServer);
        logger.info("retrieved Kv: " + serviceServerKey);
        
        // decrypt ServiceTicket with Kv
        DesEncrypter desEncrypter = new DesEncrypter(serviceServerKey.getBytes());
        ServiceTicket serviceTicket = (ServiceTicket) desEncrypter.decrypt(clientToServiceServer.getSealedServiceTicket());
        logger.info("decrypted ServiceTicket with Kv");
        
        // decrypt Authenticator with Kc, v
        String clientServiceServerKey = business.getTicketGrantingServer().getClientServiceServerKey(serviceTicket.getUsername());
        desEncrypter = new DesEncrypter(clientServiceServerKey.getBytes());
        Authenticator auth = (Authenticator) desEncrypter.decrypt(clientToServiceServer.getSealedAuth());
        logger.info("decrypted Authenticator with Kc, v");
        
        // verify client, id, address, time 
        if (validateClient(serviceTicket, auth) == false) {
            throw new Exception("Authenticator parameters doesn't match, or may be timeout");
        }
        
        logger.info("verified client: " + serviceTicket.getUsername() + " " + serviceTicket.getClientNetworkAddress());
        
        // enrcypt (time_c + 1) with Kc, v
        String time = Long.toString(auth.getTime()+1);
        logger.info("time+1: " + time);
        //desEncrypter = new DesEncrypter(clientServiceServerKey.getBytes());
        String message = desEncrypter.encrypt(time);
        logger.info("encrypted time+1 with Kc, v: " + message);
        
        return message;
    }
    
    public String helloService(String name) {
        return "Hello Mr. " + name + ", finally you made it!!! ";
    }

    public Response service(Request request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
