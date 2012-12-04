/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.src.session;

/**
 *
 * @author prakashj
 */
public class Response {
    
    public enum ErrorCode {
        Success,
        PageNotFound,
        InvalidSession,
        InvalidAccess;
    }
    
    private ErrorCode errorCode;
    private String sessionId;
    private long stepId;
    private Object response;

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public long getStepId() {
        return stepId;
    }

    public void setStepId(long stepId) {
        this.stepId = stepId;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }        
    
}
