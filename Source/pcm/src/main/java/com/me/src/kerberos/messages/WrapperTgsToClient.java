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
public class WrapperTgsToClient {
    private SealedObject sealedTgsToClient;

    public SealedObject getSealedTgsToClient() {
        return sealedTgsToClient;
    }

    public void setSealedTgsToClient(SealedObject sealedTgsToClient) {
        this.sealedTgsToClient = sealedTgsToClient;
    }                    
}
