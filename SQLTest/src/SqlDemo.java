import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 */
public class SqlDemo {

    private static final String sqlDriver = "org.sqlite.JDBC";
    private static final String URL = "jdbc:sqlite:Demo.db";
    private static final String NAME = "root";
    private static final String PASSWORD = "root";
    private static Connection connect = null;


    //静态代码块（将加载驱动、连接数据库放入静态块中）
    static {
        try {
            Class.forName(sqlDriver);
            connect = DriverManager.getConnection(URL, NAME, PASSWORD);
            Connection connect = SqlDemo.getConnection();
            //2.创建statement类对象，用来执行SQL语句！！
            Statement state = connect.createStatement();
            //插入表tbl1;
            String sql = "create table IF NOT EXISTS tbl1" +
                    "(ID int,UserName String,Age int,Sex int,Email String,createUser String,updateUser String);";
            state.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //对外提供一个方法来获取数据库连接
    public static Connection getConnection() {
        return connect;
    }

}
