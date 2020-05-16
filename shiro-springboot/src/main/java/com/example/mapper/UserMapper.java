package com.example.mapper;

import com.example.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

/**
 * Created by chenzhipeng on 2020/5/15 10:08
 */

@Mapper

public interface UserMapper {

    public User queryUserByName(String name);
}
