package com.xiaobei.mybatis.demo.dao;

import com.xiaobei.mybatis.demo.common.Mapper;
import com.xiaobei.mybatis.demo.domain.User;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.builder.annotation.ProviderContext;
import org.apache.ibatis.jdbc.SQL;

import java.lang.reflect.Method;
import java.util.List;

/**
 * TODO
 *
 * @author <a href="mailto:legend0508@163.com">xiaobei-ihmhny</a>
 * @date 2022-10-16 16:35:35
 */
public interface UserMapper extends Mapper<User> {


//    @SelectProvider(type = MySqlProvider.class, method = "selectByParams")
//    List<User> selectByUser(User user);

//    @SelectProvider(type = Mapper.MySqlProvider.class, method = "selectByContext")
//    List<User> selectByContext(User user);

}
