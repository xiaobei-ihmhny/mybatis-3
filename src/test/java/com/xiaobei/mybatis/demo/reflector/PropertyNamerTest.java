package com.xiaobei.mybatis.demo.reflector;

import com.xiaobei.mybatis.demo.domain.User;
import org.apache.ibatis.reflection.property.PropertyNamer;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author <a href="mailto:legend0508@163.com">xiaobei-ihmhny</a>
 * @date 2022-11-14 23:39:39
 */
public class PropertyNamerTest {

    @Test
    public void methodToProperty() {
        String name = PropertyNamer.methodToProperty("getbAccount");
        System.out.println(name);
    }

    @Test
    public void isAssignableFrom() {
        Class<List> list1 = List.class;
        Class<ArrayList> arrayList = ArrayList.class;
        boolean assignableFrom1 = list1.isAssignableFrom(arrayList);
        boolean assignableFrom2 = arrayList.isAssignableFrom(list1);
        System.out.println(assignableFrom1);
        System.out.println(assignableFrom2);
    }

    @Test
    public void objectMethod() {
        Class<User> clazz = User.class;
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            boolean declaringObject = method.getDeclaringClass() == Object.class;
            System.out.println(method.getName() + "_" + declaringObject);
        }
    }
}
