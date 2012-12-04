/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.src.kerberos.servers;

import java.util.Date;
import java.util.HashMap;

import javax.crypto.SealedObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.me.src.Business;
import com.me.src.kerberos.messages.Authenticator;
import com.me.src.kerberos.messages.ClientToTgs;
import com.me.src.kerberos.messages.ServiceTicket;
import com.me.src.kerberos.messages.TgsToClient;
import com.me.src.kerberos.messages.TicketGrantingTicket;
import com.me.src.kerberos.messages.WrapperTgsToClient;
import com.me.src.security.DesEncrypter;
import com.me.src.security.HashGenerator;
import com.me.src.session.SessionToken;

/**
 *
 * @author prakashj
 */
public class TicketGrantingServer {
    public final static int TICKET_LIFETIME = 1800;
    private HashMap<String, String> clientServiceServerKeyMap;
    private static final Logger logger = LoggerFactory.getLogger(TicketGrantingServer.class);
    
    public TicketGrantingServer() {
        clientServiceServerKeyMap = new HashMap<String, String>();
    }
            
    private boolean validateClient(TicketGrantingTicket tgt, Authenticator auth) {
        if(!auth.getUsername().equals(tgt.getUsername())) {
            return false;
        }
        
        if(auth.getTime() < tgt.getTimeKdc()) {
            return false;
        }        
        
        if(!auth.getClientNetworkAddress().equals(tgt.getClientNetworkAddress())) {
            return false;
        }
        
        // timeout
        if(tgt.getTimeKdc() + tgt.getLifetime() < auth.getTime()) {
            return false;
        }
        
        return true;
    }
    
    
    public WrapperTgsToClient authenticateUser(ClientToTgs clientToTgs) throws Exception {
        Business business = Business.getInstance();        
        
        // get Ktgs  from KeyStore      
        String tgsKey = business.getServerKey(Business.ServerEnum.TicketGrantingServer);        
        logger.info("retrieve Ktgs" + tgsKey);
        
        // decrypt TGT with Ktgs
        DesEncrypter desEncrypter = new DesEncrypter(tgsKey.getBytes());
        TicketGrantingTicket tgt = (TicketGrantingTicket)desEncrypter.decrypt(clientToTgs.getSealedTgt());        
        logger.info("decrypt TGT");
        
        // decrypt Authenticator with Kc, tgs
        String clientTgsKey = business.getAuthenticationServer().getClientTgsKey(tgt.getUsername());
        desEncrypter = new DesEncrypter(clientTgsKey.getBytes());
        Authenticator auth = (Authenticator)desEncrypter.decrypt(clientToTgs.getSealedAuth());
        logger.info("decrypt Authenticator message");
                        
        // verify client, id, address, time 
        if(validateClient(tgt, auth) == false) {
            throw new Exception("Authenticator parameters doesn't match, or may be timeout");
        }
        
        logger.info("verified client: " + tgt.getUsername() + " " + tgt.getClientNetworkAddress());
        
        // get Kv        
        String serviceServerKey = business.getServerKey(clientToTgs.getIdServiceServer());
        logger.info("retrieve Kv: " + serviceServerKey);
        
        // recreate Kc
        String password = business.getAuthenticationServer().getPasswordForUser(tgt.getUsername());
        String clientKey = HashGenerator.getClientKey(tgt.getUsername() + password);        
        
        // generate Kc, v key
        String clientServiceServerKey = generateClientServiceServerKey(serviceServerKey, clientKey, tgt.getUsername());
        logger.info("generated Kc,v: " + clientServiceServerKey);
        
        long timeTgs = new Date().getTime();
        logger.info("time tgs: " + timeTgs);
        
        // create ServiceTicket 
        ServiceTicket serviceTicket = new ServiceTicket();        
        serviceTicket.setClientServiceServerKey(clientServiceServerKey);
        serviceTicket.setUsername(tgt.getUsername());
        serviceTicket.setClientNetworkAddress(tgt.getClientNetworkAddress());
        serviceTicket.setIdServiceServer(clientToTgs.getIdServiceServer());
        serviceTicket.setTimeTgs(timeTgs);
        serviceTicket.setLifetime(TicketGrantingServer.TICKET_LIFETIME);
        
        // encrypt ServiceTicket with Kv        
        desEncrypter = new DesEncrypter(serviceServerKey.getBytes());
        SealedObject sealedServiceTicket = desEncrypter.encrypt(serviceTicket);
        logger.info("generated ServiceTicket and encrypted with Kv");
        
        // create TgsToClient message
        TgsToClient tgsToClient = new TgsToClient();
        tgsToClient.setSealedServiceTicket(sealedServiceTicket);
        tgsToClient.setClientServiceServerKey(clientServiceServerKey);
        tgsToClient.setTimeTgs(timeTgs);
        tgsToClient.setIdServiceServer(clientToTgs.getIdServiceServer());
        
        // generate session token, and give it to user
        SessionToken token = business.getSessionManagementServer().generateSessionToken(auth);        
        tgsToClient.setSessionId(token.getSessionId());
        tgsToClient.setStepId(token.getSteptId());
        
        // encrypt TgsToClient with Kc, tgs
        desEncrypter = new DesEncrypter(clientTgsKey.getBytes());
        SealedObject sealedTgsToClient = desEncrypter.encrypt(tgsToClient);
        logger.info("encrypted TgsToClient with Kc, tgs");
        
        // prepare message to return
        WrapperTgsToClient message = new WrapperTgsToClient();
        message.setSealedTgsToClient(sealedTgsToClient);
                       
        return message;
    }
    
    // should generate short term key, should consider time as well then
    public String generateClientServiceServerKey(String serviceServerKey, String clientKey, String username) throws Exception {        
        // generate Kc, v
        DesEncrypter desEncrypter = new DesEncrypter(serviceServerKey.getBytes());
        String clientServiceServerKey = desEncrypter.encrypt(clientKey);

        // store in map
        clientServiceServerKeyMap.put(username, clientServiceServerKey);

        return clientServiceServerKey;
    }
    
    public String getClientServiceServerKey(String username) {
        return clientServiceServerKeyMap.get(username);
    }
    
}
