import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * 测试通过数据库连接池获取连接对象
 * @author yuqi
 */
public class TestDruid {
    @Test
    public void getDruidConn() throws Exception {
        Properties properties = new Properties();
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
        properties.load(is);
        //获取连接池对象
        DataSource source = DruidDataSourceFactory.createDataSource(properties);
        Connection conn = source.getConnection();
        System.out.println(conn);
        // 成功
    }
}
