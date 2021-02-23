package com.example.SpringDatabase.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;


@RedisHash
@Getter
@Setter
public class Employee {

    @Id
    private long id;
    private String name;
    private String departmentName;

}
