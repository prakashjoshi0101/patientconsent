/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.src.kerberos.messages;

import java.io.Serializable;

/**
 *
 * @author prakashj
 */

// Encrypted with Ktgs
public class TicketGrantingTicket implements Serializable {
    private String clientTgsKey; // Kc,tgs
    private String username; // IDc
    private String clientNetworkAddress;  // Addrc
    private int idTgs;
    private long timeKdc;
    private long lifetime;

    public String getClientNetworkAddress() {
        return clientNetworkAddress;
    }

    public void setClientNetworkAddress(String clientNetworkAddress) {
        this.clientNetworkAddress = clientNetworkAddress;
    }

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

    public long getTimeKdc() {
        return timeKdc;
    }

    public void setTimeKdc(long timeKdc) {
        this.timeKdc = timeKdc;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    
}
