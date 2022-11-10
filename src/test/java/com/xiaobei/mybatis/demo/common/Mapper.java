package com.xiaobei.mybatis.demo.common;

import com.xiaobei.mybatis.demo.dao.UserMapper;
import com.xiaobei.mybatis.demo.domain.User;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.builder.annotation.ProviderContext;
import org.apache.ibatis.scripting.xmltags.XMLLanguageDriver;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author <a href="mailto:legend0508@163.com">xiaobei-ihmhny</a>
 * @date 2022-11-09 23:44:44
 */
public interface Mapper<T> {


    @Lang(XMLLanguageDriver.class)
    @SelectProvider(type = MySqlProvider.class, method = "selectByParam")
    List<T> selectByParam(T user);


    /**
     * 定义相应的动态sql语句
     */
    class MySqlProvider {

        private static String IF = "<if test=\"%s != null\">\n    AND %s = #{%s}\n</if>\n";

        public static String selectByParam(ProviderContext providerContext) {
            // 实际的sql方法
            Method mapperMethod = providerContext.getMapperMethod();
            Class<?> mapperType = providerContext.getMapperType();
            Type[] types = GenericTypeResolver.resolveParamTypes(mapperMethod, mapperType);
            Class<?> returnType = GenericTypeResolver.getReturnType(mapperMethod, mapperType);
            Field[] declaredFields = returnType.getDeclaredFields();
            StringBuilder sqlBuilder = new StringBuilder();
            List<String> columnList = new ArrayList<>();
            for (Field field : declaredFields) {
                String columnName = field.getName();
                if(field.isAnnotationPresent(Column.class)) {
                    Column annotation = field.getAnnotation(Column.class);
                    columnName = annotation.value();
                }
                columnList.add(columnName);
            }
            String tableName = returnType.getSimpleName();
            // 表名
            if(returnType.isAnnotationPresent(Table.class)) {
                tableName = returnType.getAnnotation(Table.class).value();
            }
            sqlBuilder.append("<script>").append("SELECT ")
                    .append(String.join(",", columnList))
                    .append(" FROM ")
                    .append(tableName)
                    .append("\n<where>\n");
            for (Type type : types) {
                if(type instanceof Class) {
                    Class<?> paramClazz = (Class<?>) type;
                    // 获取参数信息
                    Field[] fields = paramClazz.getDeclaredFields();
                    for (Field field : fields) {
                        String name = field.getName();
                        String columnName = name;
                        if(field.isAnnotationPresent(Column.class)) {
                            Column annotation = field.getAnnotation(Column.class);
                            columnName = annotation.value();
                        }
                        // 拼接sql
                        sqlBuilder.append(String.format(IF, name, columnName, name));
                    }
                }
            }
            sqlBuilder.append("</where>").append("</script>");
            return sqlBuilder.toString();
        }
        public static String selectByContextAndOneParam(ProviderContext providerContext, User user) {
            System.out.println(providerContext);

            return "Select 1";
        }

        /**
         * 根据给定的用户信息为条件查询满足条件的所有用户列表
         * @param user
         * @return
         */
        /*public static String selectByParams(User user) {
            return new SQL() {{
                SELECT("id", "username", "age", "birth_place");
                FROM("user");
                if(user != null && user.getId() != null && user.getId() != 0) {
                    WHERE("id = #{id}");
                }
                if(user != null && user.getUsername() != null) {
                    WHERE("username LIKE CONCAT('%', #{username}, '%')");
                }
                if(user != null && user.getAge() != null) {
                    WHERE("age = #{age}");
                }
                if(user != null && user.getBirthPlace() != null) {
                    WHERE("birth_place = #{birthPlace}");
                }
            }}.toString();
        }*/
    }
}
