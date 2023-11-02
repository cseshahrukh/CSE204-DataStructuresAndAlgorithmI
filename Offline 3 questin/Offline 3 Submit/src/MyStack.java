// Author Md. Shahrukh Islam
// Buet 1805098
//Date Start 21.03.2021

import java.util.Scanner;

public class MyStack<T> {
    private MyLinkedList<T> list=new MyLinkedList<T>();

    public MyStack()
    {

    }
    public MyStack(T element)
    {
        push(element);
    }

    public void push(T element)
    {
        list.addLast(element);
    }
    public T pop(){
        if (isEmpty()) throw new java.util.EmptyStackException();
        return list.removeLast();
    }

    public T getTop(){
        if (isEmpty()) throw new java.util.EmptyStackException();
        return list.getLast();
    }
    public int size(){
        return list.size();
    }

    public boolean isEmpty(){
        return size()==0;
    }


    public static boolean isVailid(String input)
    {
        boolean flag=true;  //this means it is valid
        MyStack bkStk=new MyStack();

        for (int i=0; i<input.length(); i++)
        {
            if (input.charAt(i)=='(')
            {
                bkStk.push('(');
            }
            else if (input.charAt(i)==')')
            {
                if (bkStk.size()==0)
                {
                    flag=false;
                    break;
                }

                else if ((char)bkStk.getTop()=='(')
                {
                    bkStk.pop();
                }
                else
                {
                    // not ( in the top element
                    flag=false;
                break; }

            }
        }

        if (bkStk.size()!=0)
        {
            flag=false;
        }
        //if flag is true then stack is empty
        //no need to iterate to end
        //bracket checking done,


        //consecutive operation check
        int indexOfLastOperation=-1;
        for (int i=0; i<input.length(); i++)
        {
            if (ASMD(input.charAt(i))){
                if (i-indexOfLastOperation==1)
                {
                    flag=false;
                }
                else
                    indexOfLastOperation=i;
            }
        }

        //  (minus number +-*/ check
        int savedBracketIndex=-100, savedMinusIndex=-100, savedNumberIndex=-100, savedOpIndex=-100;
        int flagMinus=0, anotherIndex=0;
        //The reason of anotherIndex is to indicate position of multidigit number at single index
        for (int i=0; i<input.length(); i++, anotherIndex++)
        {
            if (input.charAt(i)=='(')
            {
                savedBracketIndex=anotherIndex;
            }
            else if (i>0 && input.charAt(i)=='-' && input.charAt(i-1)=='(')
            {
                savedMinusIndex=anotherIndex;
            }
            else if (input.charAt(i)>='0' && input.charAt(i)<='9')
            {
                //StringBuffer number=new StringBuffer(); //vector of char
                //may be more than one digit
                savedNumberIndex=anotherIndex;
                while (i<input.length() && input.charAt(i)>='0' && input.charAt(i)<='9')
                {
                    //number.append(input.charAt(i));
                    i++;
                }
                //if (flagMinus==1) {
                    //values.push(-1 * Integer.parseInt(number.toString()));
                    //flagMinus=0;
                //}
                //else
                    //values.push( Integer.parseInt(number.toString()));
                i--; //increment 2 hoye jabe eita na korle

            }
            else if (ASMD(input.charAt(i)))
            {
                savedOpIndex=anotherIndex;
            }

            if (savedOpIndex-savedNumberIndex==1 && savedNumberIndex-savedMinusIndex==1 && savedMinusIndex-savedBracketIndex==1)
            {
                flag=false;
                break;
            }

        }


        //5+6(-) handle
        for (int i=0; i<input.length(); i++)
        {
            if (i > 0 && i < input.length() - 1 && ASMD(input.charAt(i))  && input.charAt(i - 1) == '(' && input.charAt(i + 1) == ')') {
                flag = false;
                break;
            }
        }

        //integer confirm
        //System.out.println(input);
        for (int i=0; i<input.length(); i++)
        {

            char ch=input.charAt(i);
            //if (i==10) System.out.println(ch);
            if (!  (   ASMD(ch) || (ch >= '0' && ch <= '9') || ch == ' ' || ch == '(' || ch == ')')  ) {
                flag = false;
                break;
            }

        }

        //( er por only minus tai accpetalbe
        for (int i=0; i<input.length(); i++)
        {
            char ch=input.charAt(i);
            if (i > 0 && input.charAt(i - 1) == '(' && (ch == '+' || ch == '*' || ch == '/' || ch == ')')) {
                flag = false;
                break;
            }
        }

        return flag;
    }

    public static boolean checkPrecedence(char op1, char op2)
    {
        //return 1 if op1>op2
        //else return 0
        if (op2=='('|| op2==')')
            return true;

        if ((op1=='+' || op1=='-') && (op2=='*' || op2=='/'))
        return false;
        if ((op1=='*' || op1=='/') && (op2=='+' || op2=='-'))
            return true;

        else
            return false; //same precidence 
    }

    public static int binaryOperation(int a, char op, int b)
    {

        //top ta a
        //top er porer ta b
        //so b+a hole a top e chilo b tar nice
        if (op=='+') {
            //System.out.println("happenning plus ");
            return b + a;
        }
        else if (op=='-') {
            //System.out.println("happenning minus ");
            return b - a;
        }
        else if (op=='*')
            return b*a;
        else if (op=='/'){
            if (b==0)
                throw new ArithmeticException("Cannot divide by zero");
            //System.out.println("returning a/b"+ a+ b);
            return b/a;

        }
        return 0;
    }
    public static boolean ASMD(char ch)
    {
        return ch=='+' || ch=='-' || ch=='*' || ch=='/';
    }
    public static int solve(String input)
    {

        //System.out.println("input is "+ input);
        MyStack<Integer> values=new MyStack<>();
        MyStack<Character> operations=new MyStack<>();

        //int flagMinus=0; //
        for (int i=0; i<input.length(); i++)
        {

            if (input.charAt(i)=='(')
            {
                operations.push('(');
                //System.out.println("operation e ( push holo");
            }
            else if (input.charAt(i)>='0' && input.charAt(i)<='9')
            {
                StringBuilder number=new StringBuilder(); //vector of char
                //may be more than one digit

                while (i<input.length() && input.charAt(i)>='0' && input.charAt(i)<='9')
                {
                    number.append(input.charAt(i));
                    i++;
                }
                //System.out.println("the number is "+number);
                /*if (flagMinus==1) {
                    values.push(-1 * Integer.parseInt(number.toString()));
                    System.out.println("pushed value is "+ (-1 * Integer.parseInt(number.toString())));
                    flagMinus=0;
                }
                else*/
                values.push( Integer.parseInt(number.toString()));
                //System.out.println("values e push holo "+ values.getTop());
                i--; //increment 2 hoye jabe eita na korle

            }

            else if (ASMD(input.charAt(i))) //char ta +, -, *, /
            {
                /*if (input.charAt(i)=='-' && !operations.isEmpty() && operations.getTop()=='(' && values.isEmpty())
                {
                    //(-5) mane age kono value nai
                    // ((-1)-(-3)) else this is corner case //solved in next eles
                    flagMinus=1;
                    continue;
                }
                else if (input.charAt(i)=='-' && !operations.isEmpty() && operations.getTop()=='(')
                {
                    //(-5) mane age kono value nai
                    // ((-1)-(-3)) else this is corner case
                    flagMinus=1;
                    continue;
                }*/
                if (input.charAt(i)=='-' &&i>0 && input.charAt(i-1)=='(')
                {
                    values.push(0);
                    //System.out.println("values e push holo 0");
                }

                while (!operations.isEmpty() && !checkPrecedence(input.charAt(i), operations.getTop()))
                {
                    //if (flagMinus==1) System.out.println("flag is 1 !! which is not good for me ");
                    //when first < later operator
                    //ei operator e big hole outside while
                    //mane current ops er cheye stack er tar pres beshi
                    values.push(binaryOperation(values.pop(), operations.pop(), values.pop()));
                    //System.out.println("top of values "+values.getTop());
                }
                operations.push(input.charAt(i)); //upore greater pres er gulo hoye gelo
                //System.out.println("operation e push holo "+operations.getTop());
                //ekhon eita korar pala
            }
            else if (input.charAt(i)==' ')
                continue;



            else if (input.charAt(i)==')')
            {
                while (operations.getTop()!='(')
                {

                        int value1=values.pop();
                        //System.out.println("value theke jeta pop hobe "+value1);
                        int value2=values.pop();
                        //System.out.println("value theke jeta pop hobe "+value2);
                        char ch=operations.pop();
                    values.push(binaryOperation(value1, ch, value2));
                    //System.out.println("value te ja push holo "+values.getTop());

                }
                operations.pop();
            }




        }
        //outside the for loop
        //means string traverse hoye geche
        //but stact 2 ta empty na (5+2) emon thakte pare

        //System.out.println("size of values" +values.size());

        //System.out.println("size of opes " +operations.size());
        while (!operations.isEmpty())
        {
            //System.out.println("here 1");
            int value1=values.pop();
            //System.out.println("here 2");
            int value2=values.pop();
            //System.out.println("here 3");
            char ch=operations.pop();
            //int aValue=binaryOperation(values.pop(),  operations.pop(), values.pop());
            int aValue=binaryOperation(value1, ch, value2);
            values.push(aValue);
        }

        return values.pop();

    }
    public static void main(String[] args) {
        String input;
        Scanner scn=new Scanner(System.in);
        input=scn.nextLine();
        if (!isVailid(input))
            System.out.println("Not valid");
        else
        {
            System.out.println("Valid expression");
            System.out.println("Computed value: "+solve(input));
        }

    }

}
