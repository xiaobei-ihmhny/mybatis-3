package com.xiaobei.mybatis.demo.domain;

import com.xiaobei.mybatis.demo.common.Column;
import com.xiaobei.mybatis.demo.common.Table;

import java.io.Serializable;

/**
 * TODO
 *
 * @author <a href="mailto:legend0508@163.com">xiaobei-ihmhny</a>
 * @date 2022-10-16 16:33:33
 */
@Table("user")
public class User implements Serializable {

    private Integer id;

    private String username;

    private String age;

    @Column("birth_place")
    private String birthPlace;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age='" + age + '\'' +
                ", birthPlace='" + birthPlace + '\'' +
                '}';
    }
}
