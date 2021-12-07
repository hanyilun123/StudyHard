package com.study.hard.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NetConfig implements Serializable {
    private int id;
    private String address;
    private Date createtime;
}
