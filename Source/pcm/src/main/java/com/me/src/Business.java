/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.src;

import java.util.EnumMap;

import javax.crypto.SecretKey;

import sun.misc.BASE64Encoder;

import com.me.src.kerberos.servers.AuthenticationServer;
import com.me.src.kerberos.servers.HelloServiceServer;
import com.me.src.kerberos.servers.TicketGrantingServer;
import com.me.src.security.KeyStoreWrapper;
import com.me.src.session.FrontController;
import com.me.src.session.SessionManagement;

/**
 *
 * @author prakashj
 */
//singelton class
public class Business {

    private static Business business;
    private AuthenticationServer authenticationServer;
    private TicketGrantingServer ticketGrantingServer;    
    private SessionManagement sessionManagementServer;
    private HelloServiceServer helloServiceServer;
    private FrontController frontController;
    
    private KeyStoreWrapper keyStoreWrapper;
    private EnumMap<ServerEnum, String> aliasPasswordMap;
    
    
    public static enum ServerEnum {
        AuthenticationServer(1001, "Ticket Granting Server"),
        TicketGrantingServer(1002, "Ticket Granting Server"),
        SessionManagementServer(1003, "Session Management Server"),
        HelloServiceServer(1004, "Hello Service Server");
        
        private final String name;
        private final int id;
        
        private ServerEnum(int id, String s) {
            this.id = id;
            this.name = s;
        }

        public int getId() {
            return id;
        }        
        
        @Override
        public String toString() {
            return name;
        }
    }
    
    private final static String keyStorePassword = "My Key Store Password";
    private final static String keyStoreFilePath = "db.keystore";
    
    private Business() {

        aliasPasswordMap = new EnumMap<ServerEnum, String>(ServerEnum.class);
        
        keyStoreWrapper = new KeyStoreWrapper(keyStoreFilePath, keyStorePassword, "jceks");
        
        authenticationServer = new AuthenticationServer();
        ticketGrantingServer = new TicketGrantingServer();
        sessionManagementServer = new SessionManagement();
        helloServiceServer = new HelloServiceServer();
        frontController = new FrontController();
        
        setupServerKeysInKeyStore();
    }
        

    public static Business getInstance() {
        if (business == null) {
            business = new Business();
        }
        return business;
    }
    
    public KeyStoreWrapper getKeyStoreWrapper() {
        return keyStoreWrapper;
    }
    
    private void setupServerKeysInKeyStore() {
        // Set password and alias in map
        aliasPasswordMap.put(ServerEnum.TicketGrantingServer, "Ticket Granting Server Passowrd 123");
        aliasPasswordMap.put(ServerEnum.SessionManagementServer, "Hello Session Management Server Passowrd 123");
        aliasPasswordMap.put(ServerEnum.HelloServiceServer, "Hello Service Server Passowrd 123");
        
        // create keys in key store
        keyStoreWrapper.createKeyEntry(ServerEnum.TicketGrantingServer.toString(), aliasPasswordMap.get(ServerEnum.TicketGrantingServer));
        keyStoreWrapper.createKeyEntry(ServerEnum.SessionManagementServer.toString(), aliasPasswordMap.get(ServerEnum.SessionManagementServer));
        keyStoreWrapper.createKeyEntry(ServerEnum.HelloServiceServer.toString(), aliasPasswordMap.get(ServerEnum.HelloServiceServer));
    }    
    
    public AuthenticationServer getAuthenticationServer() {
        return authenticationServer;
    }

    public HelloServiceServer getHelloServiceServer() {
        return helloServiceServer;
    }

    public TicketGrantingServer getTicketGrantingServer() {
        return ticketGrantingServer;
    }

    public SessionManagement getSessionManagementServer() {
        return sessionManagementServer;
    }

    public FrontController getFrontController() {
        return frontController;
    }
        
    
    public String getServerKey(int id) throws Exception {
        for(ServerEnum server : ServerEnum.values()) {
            if(id == server.getId()) {
                return getServerKey(server);
            }
        }
        return null;
    }
    
    public String getServerKey(ServerEnum server) throws Exception {
        String aliasPassword = aliasPasswordMap.get(server);
        SecretKey secretKey = keyStoreWrapper.getSecretKey(server.toString(), aliasPassword);
        if(secretKey == null) {
            throw new Exception("Could not find key for server: " + server.toString());
        }
        return new BASE64Encoder().encode(secretKey.getEncoded());
    }
    
    
}
