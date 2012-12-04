/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.src.kerberos.messages;

import javax.crypto.SealedObject;

/**
 *
 * @author prakashj
 */
public class ClientToServiceServer {
    private SealedObject sealedServiceTicket; // encrypted ServiceTicket with Kv
    private SealedObject sealedAuth; // encrypted Authenticator with Kc,v

    public SealedObject getSealedAuth() {
        return sealedAuth;
    }

    public void setSealedAuth(SealedObject sealedAuth) {
        this.sealedAuth = sealedAuth;
    }

    public SealedObject getSealedServiceTicket() {
        return sealedServiceTicket;
    }

    public void setSealedServiceTicket(SealedObject sealedServiceTicket) {
        this.sealedServiceTicket = sealedServiceTicket;
    }        
}
