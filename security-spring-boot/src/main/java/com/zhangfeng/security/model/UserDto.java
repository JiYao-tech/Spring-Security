package com.zhangfeng.security.model;

import lombok.Data;

import javax.annotation.sql.DataSourceDefinition;

@Data
public class UserDto {
    private String id;
    private String username;
    private String password;
    private String fullname;
    private String mobile;

}
