package me.ifmo.common.interaction;

import java.io.Serializable;
import java.util.Objects;

/**
 * A class responsible for interaction between client and server on the server side.
 */

public class Response implements Serializable {
    private String textOfResponse;
    private ResponseCode responseCode;

    /**
     * Constructor for the Response class.
     */

    public Response(String textOfResponse, ResponseCode responseCode){
        this.textOfResponse = textOfResponse;
        this.responseCode = responseCode;
    }

    /**
     * Method for setting the new value of field textOfResponse.
     * @param textOfResponse - The new textOfResponse value.
     */

    public void setTextOfResponse(String textOfResponse) {
        this.textOfResponse = textOfResponse;
    }

    /**
     * Method for setting the new value of field responseCode.
     * @param responseCode - The new responseCode value.
     */

    public void setResponseCode(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    /**
     * Method for getting the value of the textOfResponse field.
     * @return Returns value of the commandName field.
     */

    public String getTextOfResponse() {
        return this.textOfResponse;
    }

    /**
     * Returns value of the responseCode field.
     * @return Returns value of the responseCode field.
     */

    public ResponseCode getResponseCode() {
        return this.responseCode;
    }

    /**
     * Overridden toString method for the Response class.
     * @return Returns information about an object of type Response.
     */

    @Override
    public String toString() {
        return "Response: STATE - " + getResponseCode() + " || BODY - " + getTextOfResponse();
    }

    /**
     * Overridden hashCode method for the Response class.
     * @return Returns the hashcode of an object of type Response.
     */

    @Override
    public int hashCode() {
        return this.textOfResponse.hashCode() + this.responseCode.hashCode();
    }

    /**
     * Overridden equals method for the Response class.
     * @param obj - The object to be compared to.
     * @return Returns the result of comparing two objects of type Response.
     */

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj instanceof Response responseObj){
            return (Objects.equals(this.textOfResponse, responseObj.getTextOfResponse())) && (this.responseCode == responseObj.getResponseCode());
        }
        return false;
    }
}
