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

public class ClientToTgs {
    private int idServiceServer;
    private SealedObject sealedTgt; // TicketGrantingTicket encrypted with Ktgs
    private SealedObject sealedAuth;  // Authenticator encypted with Kc,tgs

    public SealedObject getSealedAuth() {
        return sealedAuth;
    }

    public void setSealedAuth(SealedObject sealedAuth) {
        this.sealedAuth = sealedAuth;
    }    

    public int getIdServiceServer() {
        return idServiceServer;
    }

    public void setIdServiceServer(int idServiceServer) {
        this.idServiceServer = idServiceServer;
    }

    public SealedObject getSealedTgt() {
        return sealedTgt;
    }

    public void setSealedTgt(SealedObject sealedTgt) {
        this.sealedTgt = sealedTgt;
    }      
}
