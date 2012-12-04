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

public class Authenticator implements Serializable {
    private String username;
    private String clientNetworkAddress;
    private long time;

    public String getClientNetworkAddress() {
        return clientNetworkAddress;
    }

    public void setClientNetworkAddress(String clientNetworkAddress) {
        this.clientNetworkAddress = clientNetworkAddress;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    @Override
    public String toString() {
        return username + " " + clientNetworkAddress + " " + time;
    }
        
}
