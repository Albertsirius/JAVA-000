package huangzihao.homework.sqldemo;

import com.google.common.base.Stopwatch;

import java.sql.*;
import java.util.concurrent.TimeUnit;

/**
 * <p>测试不同的插入方式插入100万条数据的执行效率
 *
 * @author huangzihao
 * @since 2020/12/10
 */
public class SQLMain {

    public static String INSERT_SQL = "INSERT INTO `ORDER`(USER_ID, TOTAL_AMOUNT, CCY) VALUES(1,1.00,'CNY')";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/emall", "root", "1234567890");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //使用简单的插入方式
    public static void simpleInsert(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        Stopwatch stopwatch = Stopwatch.createStarted();
        for(int i = 0; i<1000000; i++) {
            statement.executeUpdate(INSERT_SQL);
        }
        stopwatch.stop();
        //测试需时642s
        System.out.println("time: " + String.valueOf(stopwatch.elapsed(TimeUnit.SECONDS)));
    }

    public static void batchInsert(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL);
        Stopwatch stopwatch = Stopwatch.createStarted();
        for(int i = 0; i<1000000; i++) {
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
        stopwatch.stop();
        //测试需时566s
        System.out.println("time: " + String.valueOf(stopwatch.elapsed(TimeUnit.SECONDS)));
    }

    public static void main(String[] args) throws SQLException {

        Connection connection = getConnection();
        //simpleInsert(connection);
        batchInsert(connection);
        connection.close();

    }
}
