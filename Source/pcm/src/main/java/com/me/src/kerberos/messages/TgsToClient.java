/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.src.kerberos.messages;

import java.io.Serializable;
import javax.crypto.SealedObject;

/**
 *
 * @author prakashj
 */

// Encypted with Kc,tgs
public class TgsToClient implements Serializable {
    private String clientServiceServerKey;
    private int idServiceServer;
    private long timeTgs;    
    private SealedObject sealedServiceTicket;  // encrypted ServiceTicket with Kv
    
    // session data
    private String sessionId;
    private long stepId;

    public String getClientServiceServerKey() {
        return clientServiceServerKey;
    }

    public void setClientServiceServerKey(String clientServiceServerKey) {
        this.clientServiceServerKey = clientServiceServerKey;
    }

    public int getIdServiceServer() {
        return idServiceServer;
    }

    public void setIdServiceServer(int idServiceServer) {
        this.idServiceServer = idServiceServer;
    }

    public SealedObject getSealedServiceTicket() {
        return sealedServiceTicket;
    }

    public void setSealedServiceTicket(SealedObject sealedServiceTicket) {
        this.sealedServiceTicket = sealedServiceTicket;
    }

    
    public long getTimeTgs() {
        return timeTgs;
    }

    public void setTimeTgs(long timeTgs) {
        this.timeTgs = timeTgs;
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
        
}
