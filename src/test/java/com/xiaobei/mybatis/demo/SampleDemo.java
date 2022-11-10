package com.xiaobei.mybatis.demo;

import com.alibaba.fastjson.JSON;
import com.xiaobei.mybatis.demo.dao.UserMapper;
import com.xiaobei.mybatis.demo.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * TODO
 *
 * @author <a href="mailto:legend0508@163.com">xiaobei-ihmhny</a>
 * @date 2022-10-16 15:22:22
 */
public class SampleDemo {

    public static void main(String[] args) throws IOException {
        // 构建 SqlSessionFactoryBuilder
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        String resource = "META-INF/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 通过 SqlSessionFactoryBuilder 获取 SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = builder.build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setUsername("guoguo");
//        List<User> userList = mapper.selectByUser(user);
        List<User> userList = mapper.selectByParam(user);
        System.out.println(JSON.toJSONString(userList));
    }
}
