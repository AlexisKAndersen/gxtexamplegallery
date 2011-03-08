/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ratul.gxtexamplegalary.client.util;

import java.io.Serializable;

/**
 *
 * @author Ratul
 */
public class ClientException extends Exception implements Serializable
{

    private static final long serialVersionUID = 1L;
    private String message = "";

    public ClientException()
    {
    
    }
    public ClientException(String message)
    {
        super(message);
        this.message = message;
    }

    public ClientException(Throwable cause)
    {
        super(cause);
    }
    /**
     * Get the value of message
     *
     * @return the value of message
     */
    @Override
    public String getMessage()
    {
        return message;
    }

    /**
     * Set the value of message
     *
     * @param message new value of message
     */
    public void setMessage(String message)
    {
        this.message = message;
    }

    

   
}
