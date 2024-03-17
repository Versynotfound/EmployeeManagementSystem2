package com.yuqi.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * 创建数据库连接池的工具类
 * @author yuqi
 */
public class JDBCUtil {
    private static DataSource source;

    static {
        try {
            // 创建properties对象，用来封装从文件中获取的数据流
            Properties properties = new Properties();
            // 采用类加载方式获取文件的内容，并封装成流
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
            // 将流传入properties对象中
            properties.load(is);
            // 利用工厂类创建数据库连接池
            source = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws Exception{
        // 所有的数据库连接池要想被java使用，都必须先实现sun公司提供的一个接口DataSource
        // 获取数据库连接池对象
        Connection conn = source.getConnection();
        return conn;
    }
}
