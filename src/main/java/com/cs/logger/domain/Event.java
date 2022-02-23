package com.cs.logger.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Data
@Entity
@Table(name = "event")
public class Event {
    @Id
    private String id;
    @Transient
    private Status state;
    @Transient
    private long timeStamp;
    private long duration;
    private String type;
    private String host;
    private Boolean alert;
}