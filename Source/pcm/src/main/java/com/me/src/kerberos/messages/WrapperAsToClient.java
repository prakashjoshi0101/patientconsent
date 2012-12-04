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
public class WrapperAsToClient {
    private SealedObject sealedAsToClient;    

    public SealedObject getSealedAsToClient() {
        return sealedAsToClient;
    }

    public void setSealedAsToClient(SealedObject sealedAsToClient) {
        this.sealedAsToClient = sealedAsToClient;
    }
}
