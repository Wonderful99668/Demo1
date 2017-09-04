import java.util.Scanner;

/**
 * 搭建视图层
 * 关键：程序启动后保持运行
 * 接受由控制台传入的参数
 */
public class View {
    //提示语
    private static final String CONTEXT = "欢迎来到数据库demo\n" +
            "demo功能列表：\n" +
            "[QUERY/Q]: 查询数据库全部信息\n" +
            "[FINDbyID/F]: 查询特定用户信息（根据ID）\n" +
            "[FINDbyNAME/N]: 查询特定用户信息（根据用户姓名）\n" +
            "[ADD/A]:  添加用户\n" +
            "[UPDATE/U]: 更新用户\n" +
            "[DELETE/D]: 删除用户\n" +
            "[EXIT/E]: 退出应用\n" +
            "[BREAK/B]: 退出当前功能，返回主菜单\n";

    //操作标记
    private static final String OPERATION_QUERY = "QUERY";
    private static final String OPERATION_FIND = "FINDbyID";
    private static final String OPERATION_FINDKEY = "FINDbyNAME";
    private static final String OPERATION_ADD = "ADD";
    private static final String OPERATION_UPDATE = "UPDATE";
    private static final String OPERATION_DELETE = "DELETE";
    private static final String OPERATION_EXIT = "EXIT";
    private static final String OPERATION_BREAK = "BREAK";

    public static void main(String[] args) throws Exception {
        //输出提示语
        System.out.println(CONTEXT);
        String previous = null;//记录上一次请求
        int step = 1;//步骤记录
        boolean find = false;
        boolean FindbyName = false;
        Scanner in = new Scanner(System.in);
        DemoDao dao = new DemoDao();
        Data data = new Data();

        while (true) {
            boolean mark = false;

            //有输入时进入循环
            while (in.hasNext()) {
                String input = in.next();
                if (OPERATION_EXIT.equals(input.toUpperCase()) || "E".equals(input.toUpperCase())) {
                    mark = true;
                    break;
                } else if (OPERATION_BREAK.equals(input.toUpperCase()) || "B".equals(input.toUpperCase())) {
                    System.out.println("结束操作，返回");
                    break;
                } else if (OPERATION_QUERY.equals(input.toUpperCase()) || "Q".equals(input.toUpperCase())) {
                    dao.findall();
                } else if ("F".equals(input.toUpperCase()) || find) {
                    if (!find) {
                        System.out.println("请输入您要查询的id");
                        find = true;
                    } else {
                        int check = -1;
                        try {
                            check = Integer.parseInt(input);
                        } catch (Exception e) {
                            System.out.println("输入有误");
                            find = false;
                            break;
                        }
                        if (check <= 0) {
                            System.out.println("请输入有效ID");
                            find = false;
                            break;
                        } else {
                            dao.find(check);
                            find = false;
                        }
                    }
                } else if ("N".equals(input.toUpperCase()) || FindbyName) {
                    if (!FindbyName) {
                        System.out.println("请输入您要查询的姓名");
                        FindbyName = true;
                    } else {
                        dao.find(input);
                        FindbyName = false;
                    }
                } else if (OPERATION_ADD.equals(input.toUpperCase()) || "A".equals(input.toUpperCase()) ||
                        OPERATION_ADD.equals(previous)) {
                    previous = OPERATION_ADD;

                    if (step == 1) {
                        System.out.println("请输入您的ID：");
                    } else if (step == 2) {
                        int ID = -1;
                        try {
                            ID = Integer.parseInt(input);
                        } catch (Exception e) {
                            System.out.println("输入有误");
                            break;
                        }
                        if (ID <= 0) {
                            System.out.println("请输入有效ID");
                            break;
                        } else {
                            data.setID(ID);
                        }

                        System.out.println("请输入你的名字：");
                    } else if (step == 3) {
                        data.setUserName(input);
                        System.out.println("请输入您的年龄：");
                    } else if (step == 4) {
                        int Age = -1;
                        try {
                            Age = Integer.parseInt(input);
                        } catch (Exception e) {
                            System.out.println("输入有误");
                            break;
                        }
                        if (Age <= 0) {
                            System.out.println("请输入有效年龄");
                            break;
                        } else {
                            data.setAge(Age);
                        }

                        System.out.println("请输入您的性别。女：1；男：0");
                    } else if (step == 5) {
                        int Sex = -1;
                        try {
                            Sex = Integer.parseInt(input);
                        } catch (Exception e) {
                            System.out.println("输入有误");
                            break;
                        }
                        if (Sex != 0 && Sex != 1) {
                            System.out.println("输入有误");
                        } else {
                            data.setSex(Sex);
                        }

                        System.out.println("请输入您的邮箱：");
                    } else if (step == 6) {
                        data.setEmail(input);
                        try {
                            dao.addData(data);
                            System.out.println("添加数据成功");
                            break;
                        } catch (Exception e) {
                            System.out.println("新增数据失败");
                            break;
                        }
                    }
                }
                if (OPERATION_ADD.equals(previous) && step < 6) {
                    step++;
                } else if (step == 6) {
                    previous = null;
                    step = 1;
                    data = new Data();
                }
            }
            if (mark == true)
                break;
        }
    }
}
