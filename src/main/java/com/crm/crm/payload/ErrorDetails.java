package com.crm.crm.payload;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ErrorDetails {
    private Date date;
    private String message;
    private String request;



    public ErrorDetails( String message, Date date , String request) {

        this.message = message;
        this.date = date;
        this.request = request;
    }




}
