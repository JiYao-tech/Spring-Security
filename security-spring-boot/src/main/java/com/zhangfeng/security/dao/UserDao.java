package com.zhangfeng.security.dao;

import com.zhangfeng.security.model.PermissionDto;
import com.zhangfeng.security.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    //根据用户名查询用户
    public UserDto getUserByUsername(String userName){
        String sql = "select id,username,password,fullname from t_user where username = ?";

        /*Map<String, Object> map = jdbcTemplate.queryForMap(sql, userName);
        System.out.println(map);*/

        List<UserDto> userList = jdbcTemplate.query(sql, new Object[]{userName}, new BeanPropertyRowMapper<>(UserDto.class));
        if(userList!=null && userList.size()>0){
            return userList.get(0);
        }
        return null;
    }

    //根据用户ID查询用去权限
    public List<PermissionDto> getPermissionByUserId(String userId){
        String sql = "SELECT permission.*\n" +
                "FROM t_permission permission\n" +
                "\tINNER JOIN t_role_permission role_permission ON permission.id = role_permission.permission_id\n" +
                "\tINNER JOIN t_role role ON role_permission.role_id = role.id\n" +
                "\tINNER JOIN t_user_role user_role\n" +
                "\tON role.id = user_role.role_id\n" +
                "\t\tAND user_role.user_id = ?";
        List<PermissionDto> permissionDtoList = jdbcTemplate.query(sql, new Object[]{userId}, new BeanPropertyRowMapper<>(PermissionDto.class));

        if(permissionDtoList!=null && permissionDtoList.size() >0){
            return permissionDtoList;
        }
        return null;
    }
}
