package com.nice.task.mc3.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MessageDTO implements Serializable {
    private int id;
    private int sessionId;
    private Date MC1_timestamp;
    private Date MC2_timestamp;
    private Date MC3_timestamp;
    private Date end_timestamp;

    public MessageDTO() {
    }

    public MessageDTO(int sessionId, Date MC1_timestamp, Date MC2_timestamp, Date MC3_timestamp, Date end_timestamp) {
        this.sessionId = sessionId;
        this.MC1_timestamp = MC1_timestamp;
        this.MC2_timestamp = MC2_timestamp;
        this.MC3_timestamp = MC3_timestamp;
        this.end_timestamp = end_timestamp;
    }
}
