/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.src.session;

import com.me.src.kerberos.messages.Authenticator;

/**
 *
 * @author prakashj
 */
public abstract class Request {
    private String sessionId;
    private long stepId;
    private Authenticator authenticator;

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

    public Authenticator getAuthenticator() {
        return authenticator;
    }

    public void setAuthenticator(Authenticator authenticator) {
        this.authenticator = authenticator;
    }
    
}
