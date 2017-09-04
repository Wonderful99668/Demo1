public class TestSql {

//    public static void main(String[] args) {
//        String dirver = "org.sqlite.JDBC";
//        String user = "Wonderful";
//        String password = "123456";
//        Connection con = null;
//        try {
//            //加载驱动程序
//            Class.forName(dirver);
//            //1.getConnection()方法，连接sqlite数据库！！
//            con = DriverManager.getConnection("jdbc:sqlite:test.db",user,password);
//            if (!con.isClosed())
//                System.out.println("Succeeded connecting to the Database!");
//
//            //2.创建statement类对象，用来执行SQL语句！！
//            Statement statement = con.createStatement();
//            //创建表
//            //statement.executeUpdate("CREATE TABLE tbl1(ID int,NAME String,Address String,Salary int);");
//            //向表内插入数据
//            statement.executeUpdate("INSERT INTO tbl1 VALUES (01,\"LiHua\",\"Jianshe Road\",8000);");
//            statement.executeUpdate("INSERT INTO tbl1 values (02,\"ZhangSan\",\"Nanjing Road\",6000);");
//            //要执行的sql语句
//            String sql = "select * from tbl1;";
//            //ResultSet类，用来存放获取的结果集！！
//            ResultSet res = statement.executeQuery(sql);
//            //打印输出结果
//            while (res.next()){
//                int id = res.getInt("ID");
//                String name = res.getString("NAME");
//                String add = res.getString("Address");
//                String salary = res.getString("Salary");
//                System.out.println(id + "\t" + name +"\t"+add+ "\t" + salary);
//            }
//            res.close();
//            con.close();//结束数据库的连接
//        } catch (Exception e) {
//            System.err.println(e.getClass().getName() + ": " + e.getMessage());
//            System.exit(0);
//        }
//        System.out.println();
//        System.out.println("Opened database successfully");
//    }
}
