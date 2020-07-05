package com.zhangfeng.security.uua.service;

import com.zhangfeng.security.uua.dao.UserDao;
import com.zhangfeng.security.uua.model.PermissionDto;
import com.zhangfeng.security.uua.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpringDataUserDetailsService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //拿到用户信息
        UserDto user = userDao.getUserByUsername(userName);
        if(user == null){
            return null;
        }

        //拿到用户权限信息系
        List<PermissionDto> permissionDtoList = userDao.getPermissionByUserId(user.getId());
        String[] permissionArray = new String[permissionDtoList.size()];
        for (int i = permissionDtoList.size() - 1; i >= 0; i--) {
            permissionArray[i] = permissionDtoList.get(i).getCode();
        }
        //这里暂时使用静态数据
        UserDetails userDetails = User.withUsername(user.getUsername()).password(user.getPassword()).authorities(permissionArray).build();
        return userDetails;
    }
}
