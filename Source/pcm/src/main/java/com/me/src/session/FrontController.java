/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.src.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.me.src.Business;
import com.me.src.Business.ServerEnum;
import com.me.src.kerberos.messages.ClientToServiceServer;

/**
 *
 * @author prakashj
 */
public class FrontController {
	private static final Logger logger = LoggerFactory.getLogger(FrontController.class);
    
    public Response callService(Request request, ServerEnum server) {
        Business business = Business.getInstance();        
        Response response = new Response();
        
        logger.info("received request for: " + server.toString());
        
        // call session management
        if(business.getSessionManagementServer().validateSession(request) == false) {
            response.setErrorCode(Response.ErrorCode.InvalidSession);
            return response;
        }
        
        // if everything okay: call service
        if(server == ServerEnum.HelloServiceServer) {
            HelloRequest hr = (HelloRequest)request;
            String message = business.getHelloServiceServer().helloService(hr.getMessage());
            response.setResponse(message); // set output message       
        }
        
        response.setErrorCode(Response.ErrorCode.Success);
        response.setSessionId(request.getSessionId());
                
        SessionToken token = business.getSessionManagementServer().getSessionToken(request.getAuthenticator().getUsername());
        token.setRecentActivityTimeNow();
        response.setStepId(token.getNextStepId());
        
        logger.info("next step id: " + response.getStepId());
        logger.info("set recent activity time: " + token.getRecentActivityTime());
        
        return response;
    }
    
    public String verifyServer(ClientToServiceServer clientToServiceServer, ServerEnum server) throws Exception {
        Business business = Business.getInstance();        
        
        logger.info("verify Server: " + server.toString());                

        if(server == ServerEnum.HelloServiceServer) {            
            return business.getHelloServiceServer().verifyServer(clientToServiceServer);
        }
        
        return "";
    }
}
