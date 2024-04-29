
package com.yuqi.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * SqlSessionFactoryUtil工具类抽取
 * @author yuqi
 */
public class SqlSessionFactoryUtil {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        // 静态代码块会随着类的加载而自动执行，且只执行一次，保证只有一个sqlSessionFactory，不浪费资源
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}