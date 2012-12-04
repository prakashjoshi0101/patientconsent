/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.src.session;

import java.util.Date;

import com.me.src.kerberos.messages.Authenticator;

/**
 *
 * @author prakashj
 */
public class SessionToken {
    private String sessionId;
    private long steptId;  // random number
    private long recentActivityTime;
    private Authenticator authenticator; // auth received by TGS from Client
    //private HashMap<Object, Object> keyValueMap;

    public Authenticator getAuthenticator() {
        return authenticator;
    }

    public void setAuthenticator(Authenticator authenticator) {
        this.authenticator = authenticator;
    }

    public long getRecentActivityTime() {
        return recentActivityTime;
    }

    public void setRecentActivityTimeNow() {
        this.recentActivityTime = new Date().getTime();
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public long getSteptId() {
        return steptId;
    }

    public void setSteptId(long steptId) {
        this.steptId = steptId;
    }
    
    public long getNextStepId() {
        // generate big random number
        this.steptId = 87654321 + (long)(Math.random() * (Long.MAX_VALUE - 54321));        
        return this.steptId;
    }
    
}
