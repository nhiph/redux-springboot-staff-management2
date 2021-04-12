package dev.nhiph.model.dto;

import lombok.Data;

import java.util.Date;
@Data
public class TodoDTO {
    private String account;
    private String name;
    private String email;
    private String password;
    private Date startDate;
    private int salary;
    private int position;
    private int hours;
}
