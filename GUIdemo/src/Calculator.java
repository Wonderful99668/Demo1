import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Wonderful on 2017/7/7.
 */
public class Calculator extends JFrame implements ActionListener {
    private boolean firstDigit = true;

    /*文本框(JTextField)是界面中用于输入和输出一行文本的框。JTextField类用来建立文本框*/
    JTextField text = new JTextField("0");

    //定义字符数组，为显示的按钮赋值
    private final String[] KEYS = {"7", "8", "9", "X", "4", "5", "6", "÷", "1", "2", "3", "—", "0", "（", "）", "+"};
    private final String[] COMMAND = {"AC", "=", "记忆"};
    private JButton[] keys = new JButton[KEYS.length];
    private JButton[] command = new JButton[COMMAND.length];

    /*要new一个Calculator的GUI实例，就要重写Calculator生成器*/
    public Calculator(String s) {
        super(s);
        setVisible(true);
        setSize(400, 500);
        setLocationRelativeTo(null);//设置窗体居中放置
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置退出
        init();//初始化计算器
        this.setBackground(Color.LIGHT_GRAY);
        //this.pack();//使计算器各个组件大小合适
    }

    /*初始化计算器*/
    private void init() {
        JPanel calckeys = new JPanel();
        calckeys.setLayout(new GridLayout(4, 4, 3, 3));
        for (int i = 0; i < KEYS.length; ++i) {
            keys[i] = new JButton(KEYS[i]);
            calckeys.add(keys[i]);
            keys[i].setForeground(Color.BLUE);
        }

            /*运算键用黑色标识，其他键用蓝色*/
        keys[3].setForeground(Color.BLACK);
        keys[7].setForeground(Color.BLACK);
        keys[11].setForeground(Color.BLACK);
        for (int i = 13; i < keys.length; ++i)
            keys[i].setForeground(Color.BLACK);

        JPanel commandpanel = new JPanel();
        commandpanel.setLayout(new GridLayout(1, 3, 3, 3));
        for (int i = 0; i < command.length; ++i) {
            command[i] = new JButton(COMMAND[i]);
            commandpanel.add(command[i]);
            command[i].setForeground(Color.red);
        }

        // 新建一个大的画板，将上面建立的commandpanel和calckeys画板放在该画板内
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(3, 3));
        panel.add("North", commandpanel);
        panel.add("Center", calckeys);
        commandpanel.setPreferredSize(new Dimension(400, 50));//重新设置commandpanel画板的大小

        text.setHorizontalAlignment(JTextField.RIGHT);//文本框中的输入采用右对齐方式
        text.setFont(new Font("TimesRoman", Font.BOLD, 18));//设置文本框中的输入格式
        text.setBackground(Color.WHITE);

        //建立一个画板放文本框
        JPanel top = new JPanel();
        top.setLayout(new BorderLayout());
        top.add("Center", text);

        //整体布局
        getContentPane().setLayout(new BorderLayout(3, 5));
        getContentPane().add("North", top);
        getContentPane().add("Center", panel);
        top.setPreferredSize(new Dimension(400, 50));//重新设置文本框的大小

        // 为各按钮添加事件侦听器
        for (int i = 0; i < keys.length; ++i) {
            keys[i].addActionListener(this);
        }
        for (int i = 0; i < command.length; ++i) {
            command[i].addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //获取事件源标签
        String label = e.getActionCommand();

        if (label.equals(COMMAND[0])) {
            text.setText("0");
            firstDigit = true;
        }
        else if (label.equals(COMMAND[1]))//如果点击"=",则输出结果
        {
            text.setText(new calcmethod().solution(text.getText()));
        }
        else if (label.equals(COMMAND[2]))
            ;
        else {
            if (firstDigit)
            {
                text.setText(label);
                firstDigit = false;
            }
            else
                text.setText(text.getText() + label);
        }
    }


}
