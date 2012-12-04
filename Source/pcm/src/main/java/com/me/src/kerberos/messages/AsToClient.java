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

// Encrypted with Kc
public class AsToClient implements Serializable {
    private String clientTgsKey; // Kc,tgs
    private int idTgs;
    private long timeKdc;
    private long lifetime;     
    private SealedObject sealedTgt; // TicketGrantingTicket encrypted by Ktgs
    

    public String getClientTgsKey() {
        return clientTgsKey;
    }

    public void setClientTgsKey(String clientTgsKey) {
        this.clientTgsKey = clientTgsKey;
    }

    public int getIdTgs() {
        return idTgs;
    }

    public void setIdTgs(int idTgs) {
        this.idTgs = idTgs;
    }

    public long getLifetime() {
        return lifetime;
    }

    public void setLifetime(long lifetime) {
        this.lifetime = lifetime;
    }

    public SealedObject getSealedTgt() {
        return sealedTgt;
    }

    public void setSealedTgt(SealedObject sealedTgt) {
        this.sealedTgt = sealedTgt;
    }
   
    public long getTimeKdc() {
        return timeKdc;
    }

    public void setTimeKdc(long timeKdc) {
        this.timeKdc = timeKdc;
    }        
}
