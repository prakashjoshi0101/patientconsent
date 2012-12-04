/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.src.kerberos.servers;

import java.net.InetAddress;
import java.util.Date;

import javax.crypto.SealedObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.me.src.Business;
import com.me.src.Business.ServerEnum;
import com.me.src.kerberos.messages.AsToClient;
import com.me.src.kerberos.messages.Authenticator;
import com.me.src.kerberos.messages.ClientToServiceServer;
import com.me.src.kerberos.messages.ClientToTgs;
import com.me.src.kerberos.messages.TgsToClient;
import com.me.src.kerberos.messages.WrapperAsToClient;
import com.me.src.kerberos.messages.WrapperTgsToClient;
import com.me.src.security.DesEncrypter;
import com.me.src.security.HashGenerator;
import com.me.src.session.HelloRequest;
import com.me.src.session.Response;

/**
 *
 * @author prakashj
 */
public class HelloClient {

    private String username;
    private String password;
    private Authenticator authenticator;
    private String sessionId;
    private long stepId;
    
    private static final Logger logger = LoggerFactory.getLogger(HelloClient.class);

    public HelloClient(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }    
    

    public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public long getStepId() {
		return stepId;
	}

	public void setStepId(long stepId) {
		this.stepId = stepId;
	}

	public boolean connect() {
        try {            
            Business business = Business.getInstance();
            long time = new Date().getTime();
            logger.info("Connecting Authentication Server: " + time);
            WrapperAsToClient wrapperAsToClient = business.getAuthenticationServer().authenticateUser(username, ServerEnum.TicketGrantingServer.getId(), time);
            logger.info("Received AsToClient Message from Authentication Server");

            // generate Kc
            String clientKey = HashGenerator.getClientKey(username + password);
            logger.info("clientKey: " + clientKey);
            AsToClient asToClient = null;
            try {
                DesEncrypter desEncrypter = new DesEncrypter(clientKey.getBytes());
                 asToClient = (AsToClient) desEncrypter.decrypt(wrapperAsToClient.getSealedAsToClient());
            } catch (Exception ex) {
            	logger.info("unable to decrypt AsToClient message, may be wrong username, password");
                return false;
            }
            logger.info("decrypted AsToClient Message");

            return obtainServiceTicket(asToClient.getClientTgsKey(), asToClient.getSealedTgt());

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    private boolean obtainServiceTicket(String clientTgsKey, SealedObject sealedTgt) throws Exception {
    	logger.info("calling to get ServiceTicket");

        // create Authenticator 
        Authenticator auth = new Authenticator();
        auth.setTime(new Date().getTime());
        auth.setUsername(username);
        InetAddress ip = InetAddress.getLocalHost();
        auth.setClientNetworkAddress(ip.getHostAddress());

        // encrypt Authenticator with Kc, tgs
        DesEncrypter desEncrypter = new DesEncrypter(clientTgsKey.getBytes());
        SealedObject sealedAuth = desEncrypter.encrypt(auth);
        //logger.append("HelloClient: encrypted Authenticator");

        //logger.append("HelloClient: prepared ClientToTgs Message");
        // create ClientToTgs message
        ClientToTgs clientToTgs = new ClientToTgs();
        clientToTgs.setIdServiceServer(ServerEnum.HelloServiceServer.getId());
        clientToTgs.setSealedAuth(sealedAuth);
        clientToTgs.setSealedTgt(sealedTgt);

        Business business = Business.getInstance();
        WrapperTgsToClient message = business.getTicketGrantingServer().authenticateUser(clientToTgs);

        // decrypt using Kc, tgs key
        TgsToClient tgsToClient = (TgsToClient) desEncrypter.decrypt(message.getSealedTgsToClient());
        logger.info("received ServiceTicket");

        // store session details, we will need them later to send request
        this.sessionId = tgsToClient.getSessionId();
        this.stepId = tgsToClient.getStepId();
        this.authenticator = auth;  // authenticator which was sent to TGS
        logger.info("received session id: " + this.sessionId + " step id: " + this.stepId);

        return verifyServiceServer(tgsToClient.getClientServiceServerKey(), tgsToClient.getSealedServiceTicket());
    }

    private boolean verifyServiceServer(String clientServiceServerKey, SealedObject sealedServiceTicket) throws Exception {

    	logger.info("verify Service Server");

        InetAddress ip = InetAddress.getLocalHost();

        // create Authenticator
        Authenticator auth = new Authenticator();
        auth.setUsername(username);
        auth.setTime(new Date().getTime());
        auth.setClientNetworkAddress(ip.getHostAddress());

        // encrypt Authenticator with Kc, v
        DesEncrypter desEncrypter = new DesEncrypter(clientServiceServerKey.getBytes());
        SealedObject sealedAuth = desEncrypter.encrypt(auth);

        // create message to send
        ClientToServiceServer clientToServiceServer = new ClientToServiceServer();
        clientToServiceServer.setSealedServiceTicket(sealedServiceTicket);
        clientToServiceServer.setSealedAuth(sealedAuth);

        logger.info("sent time: " + auth.getTime());
        Business business = Business.getInstance();
        String message = business.getFrontController().verifyServer(clientToServiceServer, ServerEnum.HelloServiceServer);

        // decrypt message with Kc, v
        //desEncrypter = new DesEncrypter(clientServiceServerKey.getBytes());
        String messageToVerify = desEncrypter.decrypt(message);
        logger.info("received time: " + messageToVerify);

        long timeToVerify = Long.parseLong(messageToVerify);

        if (auth.getTime() + 1 == timeToVerify) {
            return true;
        } else {
            return false;
        }
    }

    public String callService(String name) {
        Business business = Business.getInstance();        

        // prepare request
        HelloRequest request = new HelloRequest();
        request.setMessage(name);
        request.setAuthenticator(this.authenticator);
        request.setSessionId(this.sessionId);
        request.setStepId(this.stepId);

        Response response = business.getFrontController().callService(request, ServerEnum.HelloServiceServer);
        if (response.getErrorCode() != Response.ErrorCode.Success) {
        	logger.info("error from callService: " + response.getErrorCode());
            return "";
        }

        // store session info
        this.sessionId = response.getSessionId();
        this.stepId = response.getStepId();

        return (String) response.getResponse();
    }
}
