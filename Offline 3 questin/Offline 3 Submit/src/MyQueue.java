// Author Md. Shahrukh Islam
// Buet 1805098
//Date Start 21.03.2021
// Last Date 22.03.2021

import java.util.Scanner;

public class MyQueue<T> {
    private MyLinkedList<T> list=new MyLinkedList<T>();

    public MyQueue(){

    }
    public MyQueue(T element)
    {
        push(element);
    }

    public void push(T elem) {
        list.addLast(elem);
    }

    public T getFront() {
        if (isEmpty()) throw new RuntimeException("Queue Empty");
        return list.getFirst();
    }


    public T pop() {
        if (isEmpty()) throw new RuntimeException("Queue Empty");
        return list.removeFirst();
    }
    public int size() {
        return list.size();
    }


    public boolean isEmpty() {
        return size() == 0;
    }

    
    public static void main(String[] args) {
        //System.out.println("here first ");
        MyQueue q=new MyQueue();
        Scanner scn=new Scanner(System.in);
        String input;
        System.out.println("Give Input");
        input=scn.next();


        MyLinkedList answerCharList=new MyLinkedList();
        StringBuilder answer= new StringBuilder();
        int[] occurance=new int[26]; //occurance[0] means occurance of a

        for (int i=0; i<input.length(); i++)
        {
            //System.out.println("inside for loop");

            //System.out.println("and i is "+i);
            if (occurance[input.charAt(i)-'a']==0)
            {
                //making deal
                q.push(input.charAt(i));
                occurance[input.charAt(i)-'a']++;
            }

            else
            {
                occurance[input.charAt(i)-'a']=2;
                //no deal with this
                //occurance 2 or more than 2 will get same operations
            }

            char ch; //can't do minus operation in object
            while (!q.isEmpty() && occurance[(char)q.getFront()-'a']>1)
            {
                q.pop(); 
            }
            if (!q.isEmpty())
            {
                //got an one. so we have value
                answer.append(q.getFront());
            }
            else
            {
                answer.append('#');
            }


        }

        System.out.println(answer);
    }


}
