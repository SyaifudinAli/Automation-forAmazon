/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.investasikita.test.object;

import java.time.Instant;

/**
 *
 * @author rendr
 */
public class SMSText {
    
    public static final String SMSTYPE_TOKEN_VERIFICATION = "TOKEN_VERIFICATION";
    public static final String SMSTYPE_GREETINGS = "GREETINGS";
    public static final String SMSVIA_ALL = "ALL";
    public static final String SMSVIA_GW_ONLY = "GW_ONLY";
    public static final String SMSVIA_BACKUP_ONLY = "BACKUP_ONLY";
    private String type;
    private String[] phoneNumbers;
    private String content;
    private String via;
    private Instant timeStamp;
    
    public SMSText(){}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(String[] phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public Instant getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Instant timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "SMSText{" + "type=" + type + ", phoneNumbers=" + phoneNumbers + ", content=" + content + ", via=" + via + ", timeStamp=" + timeStamp + '}';
    } 
    
}
