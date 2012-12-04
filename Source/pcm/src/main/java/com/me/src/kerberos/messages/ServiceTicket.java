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

// Encrypted with Kv i.e. Service Server Key
public class ServiceTicket implements Serializable {
    private String clientServiceServerKey;
    private String username;
    private String clientNetworkAddress;
    private int idServiceServer;
    private long timeTgs;
    private long lifetime;

    public String getClientNetworkAddress() {
        return clientNetworkAddress;
    }

    public void setClientNetworkAddress(String clientNetworkAddress) {
        this.clientNetworkAddress = clientNetworkAddress;
    }

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

    public long getLifetime() {
        return lifetime;
    }

    public void setLifetime(long lifetime) {
        this.lifetime = lifetime;
    }

    public long getTimeTgs() {
        return timeTgs;
    }

    public void setTimeTgs(long timeTgs) {
        this.timeTgs = timeTgs;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
        
}
