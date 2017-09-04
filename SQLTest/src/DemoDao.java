import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * 新增dao层，实现增，删，减，查
 */
public class DemoDao {
    /**
     * 增
     **/
    public void addData(Data data) throws Exception {
        Connection conn = SqlDemo.getConnection();
        //参数用?表示，相当于占位符
        String sql = "insert into tbl1 (ID,UserName,Age,Sex,Email,createUser,updateUser) values (?,?,?,?,?,?,?)";
        //预编译sql语句
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, data.getID());
        statement.setString(2, data.getUserName());
        statement.setInt(3, data.getAge());
        statement.setInt(4, data.getSex());
        statement.setString(5, data.getEmail());
        statement.setString(6, data.getCreateUser());
        statement.setString(7, data.getUpdateUser());

        statement.execute();
    }

    /**
     * 更新数据
     **/
    public void updateData(int ID) throws Exception {
        Connection conn = SqlDemo.getConnection();

        String check = "select * from tbl1 where ID = ?";
        String sql = "update tbl1 set USerName = ?,Age = ?,Sex = ?,Email = ?,createUser = ?" +
                "where ID = ?";

        PreparedStatement state = conn.prepareStatement(check);
        state.setInt(1, ID);
        ResultSet res = state.executeQuery();
        String name = res.getString("UserName");
        if (name == null) {
            System.out.println("更新的用户不存在");
            return;
        } else {
            Scanner in = new Scanner(System.in);
            Data data = new Data();
            state = conn.prepareStatement(sql);
            state.setInt(6, ID);
            System.out.println("请输入姓名");

        }
    }

    /**
     * 删除数据
     **/
    public void deletData(int ID) throws Exception {
        Connection conn = SqlDemo.getConnection();
        String sql = "delete from tbl1 where ID = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, ID);

        statement.execute();
    }

    /**
     * 根据ID查询数据
     **/
    public void find(int ID) throws Exception {
        String result = null;
        try {
            Connection conn = SqlDemo.getConnection();
            String sql = "select * from tbl1 where ID = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, ID);
            //返回结果集
            ResultSet res = statement.executeQuery();
            //遍历结果集
            while (res.next()) {
                int id = res.getInt("ID");
                String name = res.getString("UserName");
                int sex = res.getInt("Sex");
                int age = res.getInt("Age");
                String email = res.getString("Email");

                StringBuffer excute = new StringBuffer();
                excute.append(id + "\t" + name + "\t");
                if (sex == 0) {
                    excute.append("男" + "\t");
                } else if (sex == 1) {
                    excute.append("女" + "\t");
                }
                excute.append(age + "\t" + email);

                result = excute.toString();
            }
            res.close();
            conn.close();
            if (result == null)
                System.out.println("用户不存在，请重新输入。");
            else
                System.out.println(result);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    /**
     * 根据姓名查询数据
     **/
    public void find(String UserName) throws Exception {
        String result = null;
        try {
            Connection conn = SqlDemo.getConnection();
            String sql = "select * from tbl1 where UserName = ?";
            PreparedStatement state = conn.prepareStatement(sql);
            state.setString(1, UserName);
            ResultSet res = state.executeQuery();
            while (res.next()) {
                int id = res.getInt("ID");
                String name = res.getString("UserName");
                int sex = res.getInt("Sex");
                int age = res.getInt("Age");
                String email = res.getString("Email");

                StringBuffer excute = new StringBuffer();
                excute.append(id + "\t" + name + "\t");
                if (sex == 0) {
                    excute.append("男" + "\t");
                } else if (sex == 1) {
                    excute.append("女" + "\t");
                }
                excute.append(age + "\t" + email);

                result = excute.toString();
            }
            res.close();
            conn.close();
            if (result == null)
                System.out.println("用户不存在，请重新输入。");
            else
                System.out.println(result);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    /**
     * 查询数据库中所有数据
     **/
    public void findall() throws SQLException {
        Connection conn = SqlDemo.getConnection();
        String sql = "select * from tbl1";
        PreparedStatement state = conn.prepareStatement(sql);
        ResultSet res = state.executeQuery();
        String result = null;
        StringBuffer excute = new StringBuffer();
        while (res.next()) {
            int id = res.getInt("ID");
            String name = res.getString("UserName");
            int sex = res.getInt("Sex");

            excute.append(id + "\t" + name + "\t");
            if (sex == 0) {
                excute.append("男" + "\t");
            } else if (sex == 1) {
                excute.append("女" + "\t");
            }
            excute.append("\n");
        }

        System.out.print(excute);
        res.close();
        conn.close();
    }

}
