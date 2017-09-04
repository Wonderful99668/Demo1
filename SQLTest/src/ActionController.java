import java.sql.Connection;
import java.sql.Statement;

/**
 * Created by Wonderful on 2017/8/8.
 */
public class ActionController {
    public static void main(String[] args) throws Exception {
            Connection connection = SqlDemo.getConnection();
            DemoDao dao = new DemoDao();
            Data data = new Data();
            Statement statement = connection.createStatement();
            statement.executeUpdate("delete from tbl1 where UserName = 'null'");
//            data.setUserName("小夏");
//            data.setID(1);
//            data.setAge(19);
//            data.setSex(1);
//            data.setEmail("dw6000@163.com");
//            data.setCreateUser("me");
//            data.setUpdateUser("me");
//
//            dao.addData(data);
           // dao.findall();
            //dao.find("小夏");

    }


}
