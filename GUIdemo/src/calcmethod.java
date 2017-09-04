import java.util.Stack;

/**
 * Created by Wonderful on 2017/7/9.
 */
public class calcmethod {

    private String[] getChar(String s){
        String[] result=new String[s.length()];
        for (int i=0;i<s.length();++i)
        {
            result[i]=s.substring(i,i+1);
        }
        return result;
    }

    public String solution(String s){
        s = s.replace("X","*");
        s = s.replace("÷","/");
        s = s.replace("—","-");
        s = s.replace("（","(");
        s = s.replace("）",")");
        String[] equation=getChar(s);

        Stack<String> stack1=new Stack<>();
        Stack<String> stack2=new Stack<>();
        int length=0;
        stack1.push("#");
        for (int i=0;i<s.length();++i){

            switch (equation[i]){
                case "(":
                    stack1.push("(");
                    break;

                case ")":
                    while (stack1.peek()!="(")
                        stack2.push(stack1.pop());
                    stack1.pop();
                    break;

                case "+":
                case "-":
                    while (stack1.peek() != "(" && stack1.peek() != "#")
                        stack2.push(stack1.pop());
                    stack1.push(equation[i]);
                    length++;
                    break;

                case "*":
                case "/":
                    while (stack1.peek()=="*"||stack1.peek()=="/")
                        stack2.push(stack1.pop());
                    stack1.push(equation[i]);
                    length++;
                    break;

                default:
                    stack2.push(equation[i]);
                    length++;
                    break;
            }
        }
        while (stack1.peek() != "#")
            stack2.push(stack1.pop());

        String[] result1=new String[length];
        int count=0;
        while (!stack2.isEmpty())
            result1[count++]=stack2.pop();

        // 得到后缀表达式
        String[] result=new String[length];
           for (int i=0,x=length-1;i<length;i++)
            result[i]=result1[x--];

           return this.calculate(result);

//        StringBuffer middle=new StringBuffer();
//        for (int i=length-1;i>=0;i--)
//            middle.append(result[i]);
//        String result1=middle.toString();
//
//        return result1;
    }

    /**************计算后缀表达式的值**************/
    public String calculate(String[] result){
        Stack<Integer> stack = new Stack<>();
        for (int i=0;i<result.length;++i){
            if (result[i].equals("+")){
                int b = stack.pop();
                int a = stack.pop();
                stack.push(a+b);
            }
            else if (result[i].equals("-")){
                int b = stack.pop();
                int a = stack.pop();
                stack.push(a-b);
            }
            else if (result[i].equals("*")){
                int b = stack.pop();
                int a = stack.pop();
                stack.push(a*b);
            }
            else if (result[i].equals("/")){
                int b = stack.pop();
                int a = stack.pop();
                //处理除数为0的异常
                try {
                    stack.push(a/b);
                }catch (ArithmeticException e){
                   return "输入错误";
                }

            }
            else
                stack.push(Integer.parseInt(result[i],10));
        }
        return String.valueOf(stack.pop());
    }
}
