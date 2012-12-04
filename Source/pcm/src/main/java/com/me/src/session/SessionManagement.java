/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.src.session;

import java.util.Date;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.me.src.Business;
import com.me.src.kerberos.messages.Authenticator;
import com.me.src.security.DesEncrypter;
import com.me.src.security.HashGenerator;

/**
 *
 * @author prakashj
 */
public class SessionManagement {
    private HashMap<String, SessionToken> sessionMap; // username, token map
    public static final long TIMEOUT = 30 * 1000; // 30 seconds
    private static final Logger logger = LoggerFactory.getLogger(SessionManagement.class);
    
    public SessionManagement() {
        sessionMap = new HashMap<String, SessionToken>();
    }    
    
    public SessionToken generateSessionToken(Authenticator authenticator) throws Exception {
        Business business = Business.getInstance();        
        
        logger.info("Generating session for: " + authenticator.getUsername());
        // check if session exist for the user
        
        // generate session id based on all the attributes of authenticator
        // based on auth.toString() generate SHA-256, add salt, and iterations.
        String authStr = authenticator.toString();
        String seesionMgtKey = business.getServerKey(Business.ServerEnum.SessionManagementServer.getId());
        
        DesEncrypter desEncrypter = new DesEncrypter(seesionMgtKey.getBytes());
        String sessionId = desEncrypter.encrypt(authStr);
        
        // hash the session key is our session id, so the length of the session is constant
        sessionId = HashGenerator.getSessionHash(sessionId); 
        
        
        // store in map
        SessionToken token = new SessionToken();
        token.setAuthenticator(authenticator);
        token.setRecentActivityTimeNow();
        token.setSessionId(sessionId);        
        long stepId = token.getNextStepId();        
        
        sessionMap.put(authenticator.getUsername(), token);
        
        logger.info("added session session id: " + sessionId + " setep id: " + stepId);        
        
        // store in history
        
        return token;
    }
    
    public boolean validateSession(Request request) {        
        
        // verify sessionId, stepId, nwAddress
        SessionToken token = sessionMap.get(request.getAuthenticator().getUsername());
        
        logger.info("validating session user: " + request.getAuthenticator().getUsername());

        if(token == null) {
        	logger.info("could not find session token");
            return false;
        }
        
        if(token.getSessionId().equals(request.getSessionId()) == false) {
        	logger.info("invalid session id");
            return false;
        }
        
        if(token.getSteptId() != request.getStepId()) {
        	logger.info("invalid step id");
            return false;
        }
        
        // verify timeout
        long now = new Date().getTime();
        if((now - token.getRecentActivityTime()) >= SessionManagement.TIMEOUT) {
        	logger.info("timeout, login again");
            return false;
        }
        
        // if failed increase attempt count        
        // ***verify attempt count and ask to relogin
        
        return true;
    }
    
    public boolean verifyAccessControl(String forResource) {
        return false;
    }        
    
    public SessionToken getSessionToken(String username) {
        return sessionMap.get(username);
    }
}
