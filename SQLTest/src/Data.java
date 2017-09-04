import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * data层用于获取用户输入的数据
 */
public class Data {
    private int ID;
    private String UserName;
    private int Age;
    private int Sex;
    private String Email;
    private String createUser;
    private String updateUser;

    public void setID(int id) {
        this.ID = id;
    }

    public int getID() {
        return ID;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }

    public int getAge() {
        return Age;
    }

    public void setSex(int Sex) {
        this.Sex = Sex;
    }

    public int getSex() {
        return Sex;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getEmail() {
        return Email;
    }

    public void setCreateUser(String newUser) {
        this.createUser = newUser;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }


    //返回用于打印数据的链表
    public List<Data> query() throws Exception {
        Connection conn = SqlDemo.getConnection();
        Statement state = conn.createStatement();
        String sql = "select ID,UserName from tbl1;";
        ResultSet resultSet = state.executeQuery(sql);
        //构建res列表，储存输出数据，方便在Action层打印
        List<Data> res = new ArrayList<Data>();
        Data data = null;
        //只要对象有数据就会打印出来
        while (resultSet.next()) {
            data = new Data();
            data.setID(resultSet.getInt("ID"));
            data.setUserName(resultSet.getString("UserName"));
            res.add(data);
        }
        return res;
    }
}
