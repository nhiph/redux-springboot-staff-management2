package dev.nhiph.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title; //Họ tên
    private String email;// Email
    private String phone;// sdt
    private String gender;// gioi tinh
    private String detail; //diem trung binh
    private String hang; //diem trung binh
}