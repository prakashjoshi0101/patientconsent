/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.src.kerberos.servers;

import java.net.InetAddress;
import java.util.Date;
import java.util.HashMap;

import javax.crypto.SealedObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.me.src.Business;
import com.me.src.kerberos.messages.AsToClient;
import com.me.src.kerberos.messages.TicketGrantingTicket;
import com.me.src.kerberos.messages.WrapperAsToClient;
import com.me.src.security.DesEncrypter;
import com.me.src.security.HashGenerator;

/**
 *
 * @author prakashj
 */
public class AuthenticationServer {

    public static final long TICKET_LIFETIME = 3600; // in seconds
    private HashMap<String, String> clientTgsKeyMap;
    private HashMap<String, String> userAccountMap;
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationServer.class);

    public AuthenticationServer() {
        clientTgsKeyMap = new HashMap<String, String>();
        userAccountMap = new HashMap<String, String>();

        setupUserAccounts();
    }

    private void setupUserAccounts() {
        userAccountMap.put("prakash", "pprakash");
        userAccountMap.put("tom", "ptom");
        userAccountMap.put("harry", "pharry");
        userAccountMap.put("dick", "pdick");
    }

    public String getPasswordForUser(String username) {
        return userAccountMap.get(username);
    }
    
    public boolean authenticateUserAccount(String username, String password) {
        for(String key : userAccountMap.keySet()) {
            if(username.equalsIgnoreCase(key) && password.equals(userAccountMap.get(key))) {
                return true;
            }
        }
        return false;
    }
    
    

    public WrapperAsToClient authenticateUser(String username, int idTgs, long timeClient) throws Exception {
        
        String password = userAccountMap.get(username);
        if (password == null) {
            System.out.println("User does not exist");
            return null;
        }        
        
        long timeKdc = new Date().getTime();
        Business business = Business.getInstance();
        
        logger.info("verified username password");
        
        // generate Kc
        String clientKey = HashGenerator.getClientKey(username + password);
        logger.info("regenerated Kc: " + clientKey);
        
        // get Ktgs
        String tgsKey = business.getServerKey(Business.ServerEnum.TicketGrantingServer);
        logger.info("retrieve Ktgs: " + tgsKey);
        
        // generate Kc, tgs
        String clientTgsKey = generateClientTgsKey(tgsKey, clientKey, username);
        logger.info("generated Kc, tgs: " + clientTgsKey);
        
        // get client address
        InetAddress ip = InetAddress.getLocalHost();

        // create TGT
        TicketGrantingTicket tgt = new TicketGrantingTicket();
        tgt.setClientTgsKey(clientTgsKey);
        tgt.setUsername(username);
        tgt.setIdTgs(idTgs);
        tgt.setLifetime(TicketGrantingServer.TICKET_LIFETIME);
        tgt.setTimeKdc(timeKdc);
        tgt.setClientNetworkAddress(ip.getHostAddress());
        //System.out.println("IP Address: " + ip.getHostAddress());        
        
        // encrypt TGT with Ktgs
        DesEncrypter desEncrypter = new DesEncrypter(tgsKey.getBytes());
        SealedObject sealedTgt = desEncrypter.encrypt(tgt);
        logger.info("generated TGT and encrypted");
        
        // Develop Message to return
        AsToClient asToClient = new AsToClient();
        asToClient.setClientTgsKey(clientTgsKey);
        asToClient.setIdTgs(idTgs);
        asToClient.setTimeKdc(timeKdc);
        asToClient.setLifetime(AuthenticationServer.TICKET_LIFETIME);
        asToClient.setSealedTgt(sealedTgt);        

        // encrypt AsToClient with Kc
        desEncrypter = new DesEncrypter(clientKey.getBytes());
        SealedObject sealedAsToClient = desEncrypter.encrypt(asToClient);
        logger.info("encrypted AsToClient message with Kc");
        
        // prepare final message
        WrapperAsToClient message = new WrapperAsToClient();
        message.setSealedAsToClient(sealedAsToClient);        

        return message;
    }

    // should generate short term key, should consider time as well then
    public String generateClientTgsKey(String tgsKey, String clientKey, String username)  throws Exception {
        
        // generate Kc, tgs
        DesEncrypter desEncrypter = new DesEncrypter(tgsKey.getBytes());
        String clientTgsKey = desEncrypter.encrypt(clientKey);

        // store in map
        clientTgsKeyMap.put(username, clientTgsKey);

        return clientTgsKey;
    }

    public String getClientTgsKey(String username) {
        return clientTgsKeyMap.get(username);
    }
}
