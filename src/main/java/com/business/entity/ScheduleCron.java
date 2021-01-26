package com.business.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ScheduleCron implements Serializable {

    private int id;

    private String cronDesc;

    private String cron;

    private String author;
}
