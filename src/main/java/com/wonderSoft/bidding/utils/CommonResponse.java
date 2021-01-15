package com.wonderSoft.bidding.utils;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * ==============================================================
 * A Common response with payload/status used as return results
 * of Controller/Service methods
 * ==============================================================
 **/

public class CommonResponse {

    private List<Object> payload = null;
    private List<String> errorMessages = new ArrayList<>();
    private HttpStatus status ;




    public List<Object> getPayload() {
        return payload;
    }

    public void setPayload(List<Object> payload) {
        this.payload = payload;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public void setErrorMessage(String message){
        errorMessages.add(message);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
