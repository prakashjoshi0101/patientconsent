/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.src.kerberos.servers;

import com.me.src.session.Request;
import com.me.src.session.Response;

/**
 *
 * @author prakashj
 */
public interface ServiceServer {
    public Response service(Request request);
    
}
