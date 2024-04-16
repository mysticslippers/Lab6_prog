package me.ifmo.server.utils;

/**
 * The class that forms the body of the response.
 */

public class ResponseBodyFormatter{
    private static final StringBuilder body = new StringBuilder();

    /**
     * A method that adds some text to the response body.
     * @param response - Text for response body.
     */

    public static void addResponseText(String response){
        body.append(response).append("\n");
    }

    /**
     * Method returning the generated response body.
     * @return - Returns the body of the response.
     */

    public static String getResponse(){
        String text = body.toString();
        body.delete(0, body.length());
        return text;
    }
}
