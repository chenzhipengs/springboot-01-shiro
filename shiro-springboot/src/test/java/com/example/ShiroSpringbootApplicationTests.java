package com.example;


import com.example.mapper.UserMapper;
import com.example.pojo.User;
import com.example.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/*springboot的单元注入，可以在测试期间类似编码一样进行自动注入等容器的功能*/
@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan(basePackages = "com.example.mapper")
class ShiroSpringbootApplicationTests {
    private static final transient Logger log = LoggerFactory.getLogger(ShiroSpringbootApplicationTests.class);

    @Autowired
    DataSource dataSource;
    @Autowired
    UserService userService;


    @Test
    void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        System.out.println(userService.queryUserByName("111"));

    }


}
