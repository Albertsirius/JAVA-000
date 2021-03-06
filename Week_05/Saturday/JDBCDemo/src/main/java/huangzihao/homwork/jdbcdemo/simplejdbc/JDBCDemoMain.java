package huangzihao.homwork.jdbcdemo.simplejdbc;

import java.math.BigInteger;
import java.sql.*;
import java.util.stream.Stream;

/**
 * <p>使用原生JDBC接口
 *
 * @author huangzihao
 * @since 2020/11/22
 */
public class JDBCDemoMain {

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/java_homework_db", "root", "1234567890");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }


    public static void main(String[] args) throws SQLException {

        Connection connection = getConnection();
        if(connection == null) return;


        //simpleJDBC(connection);
        transactionJDBC(connection);
        connection.close();
    }

    //采用简单插入方法
    private static void simpleJDBC(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            //增加记录
            statement.executeUpdate("INSERT INTO USER(ID,NAME) VALUES('1','aaa')");
            //查询记录
            ResultSet resultSet = statement.executeQuery("SELECT count(*) FROM USER");
            resultSet.next();
            System.out.println(resultSet.getLong(1));
            resultSet.close();
            //更新记录
            statement.executeUpdate("UPDATE USER SET DESCRIPTION = 'Hello World!' WHERE ID = '1'");
            //删除记录
            statement.executeUpdate("DELETE FROM USER");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //采用批量方式插入
    private static void transactionJDBC(Connection connection) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO USER(ID,NAME) VALUES(?,?)");
            for( int i = 1; i < 10 ; i++){
                preparedStatement.setString(1, String.valueOf(i));
                preparedStatement.setString(2,"aaa");
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
