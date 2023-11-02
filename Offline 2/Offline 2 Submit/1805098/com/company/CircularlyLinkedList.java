package com.company;

import java.util.Scanner;

class Node{
    private int playerNo;
    private int time;
    private int timeConsumed=0;
    private Node next;
    private Node prev;

    public Node()
    {
        next=null;
    }
    public Node(int e, int PlayerNumber , Node next, Node previous)
    {
        time=e;
        playerNo=PlayerNumber;
        this.next=next;
        this.prev=previous;
        //System.out.println("creating a node with player no" +PlayerNumber);
    }

    public int getTimeConsumed(){
        return timeConsumed;
    }
    public void setTime(int time) {
        this.time = time;
    }
    public void setPlayerNo(int no) {
        playerNo = no;
    }
    public void setTimeConsumed(int timeConsumed) {
        this.timeConsumed = timeConsumed;
    }

    public int getPlayerNo(){
        return playerNo;
    }
    public int getTime() {
        return time;
    }

    public Node getNext() {
        return next;
    }

    public Node getPrev()
    {
        return prev;
    }
    public void setNext(Node next) {
        this.next = next;
    }
    public void setPrev(Node prev){
        this.prev=prev;
    }
}
public class CircularlyLinkedList {


    private Node head=null;
    private Node tail=null;
    private int size=0;
    private int LeftToRight=1;
    private Node current=null;
    public CircularlyLinkedList(){
    }

    public int getSize() {
        return size;
    }
    public boolean isEmpty()
    {
        return size==0;
    }



    /*Function to insert element at end */
    public void insertAtEnd(int val, int playerNo)
    {
        Node nptr = new Node(val, playerNo, null, null);
        if (head == null)
        {
            nptr.setNext(nptr);
            nptr.setPrev(nptr);
            head = nptr;
            tail = head;
        }
        else
        {
            nptr.setPrev(tail);
            tail.setNext(nptr);
            head.setPrev(nptr);
            nptr.setNext(head);
            tail = nptr;
        }
        size++;
    }
    /* Function to insert element at position */
    public void insertAtPos(int time, int playNumber, int timer )
    {
        Node nptr = new Node(time, playNumber, null, null);

        if (LeftToRight==1) {
        //pillow is going left to right so shift at left
            nptr.setPrev(current.getPrev());
            nptr.setNext(current);
            current.getPrev().setNext(nptr);
            current.setPrev(nptr);
        }
        else {
        //pillow is going right to left so insert at right

            nptr.setPrev(current);
            nptr.setNext(current.getNext());

            current.getNext().setPrev(nptr);
            current.setNext(nptr);
        }
        size++ ;
        getAnElementAtTime(timer);
    }

    public int deleteCurrent()
    {
        int savedplayer=current.getPlayerNo();
        current.getPrev().setNext(current.getNext());
        current.getNext().setPrev(current.getPrev());

        if (LeftToRight==1)
        current=current.getNext();
        else
        current=current.getPrev();

        size--;
        return savedplayer;
    }

    public int deleteCurrentAtTime(int time)
    {
        int timeConsumed = 0;
        int savedPlayer;
        while (true) {
            timeConsumed+=current.getTime()-current.getTimeConsumed();
            if (time <= timeConsumed){
                current.setTimeConsumed(0);
                savedPlayer=deleteCurrent();
                return  savedPlayer;
            }
            current.setTimeConsumed(0);
            if (LeftToRight==1) current = current.getNext();
            else current= current.getPrev();

            current.setTimeConsumed(0);
        }
    }


    public void display(int aTime, int prevTime)
    {
        int time=aTime-prevTime;
        int timeConsumed = 0;
        while (true) {
            if (current.getPlayerNo()==3)
            {
                //System.out.println("when player 3"+current.getTimeConsumed());
            }
            timeConsumed+=current.getTime()-current.getTimeConsumed();
            if (time <= timeConsumed){
                //System.out.println("Setting a time consumed of "+current.getPlayerNo()+" is "+current.getTimeConsumed());
                current.setTimeConsumed(timeConsumed-time);
                break;
            }
            current.setTimeConsumed(0);
            //System.out.println("shifting from "+current.getPlayerNo()+"to "+current.getNext().getPlayerNo());
            if (LeftToRight==1) current = current.getNext();
            else current= current.getPrev();
        }
        System.out.print("Game over : Player "+current.getPlayerNo()+" is holding the pillow at " +
                "t="+aTime+",  pillow passing sequence = Player ");
        //System.out.print("\nCircular Doubly Linked List = ");
        Node ptr = current;
        if (size == 0)
        {
            System.out.print("empty\n");
            return;
        }
        if (current.getNext() == current)
        {
            System.out.print(current.getPlayerNo()+ " <-> "+ptr.getPlayerNo()+ "\n");
            return;
        }
        System.out.print(current.getPlayerNo());
        if (LeftToRight==1)
        ptr = current.getNext();
        else
            ptr=current.getPrev();
        if (LeftToRight==1) {
            while (ptr.getNext() != current) {
                System.out.print(", "+ ptr.getPlayerNo() );
                ptr = ptr.getNext();
            }
        }
        else
        {
            while (ptr.getPrev() != current) {
                System.out.print(", "+ptr.getPlayerNo());
                ptr = ptr.getPrev();
            }
        }
        System.out.print(", "+ ptr.getPlayerNo());
        if (LeftToRight==1) ptr = ptr.getNext();
        else ptr=ptr.getPrev();
        //System.out.print(ptr.getPlayerNo()+ "\n");
    }




    public int getAnElementAtTime(int timer) {
        int timeConsumed = 0;
        int nextTimeThatWillConsumed=0;
        if (timer==0) return current.getPlayerNo();
        if (current.getTime()==current.getTimeConsumed() )
        {
            current.setTimeConsumed(0);
            if (LeftToRight==1) current = current.getNext();
            else current= current.getPrev();

        }
        while (true) {
            nextTimeThatWillConsumed+=current.getTime()-current.getTimeConsumed();
            if (timer <= nextTimeThatWillConsumed){
                //System.out.println("inside bigger");
                //System.out.println("timer currenttimeconsumed nexttimethatwill consudmed"+timer+" "+current.getTimeConsumed()+" "+nextTimeThatWillConsumed);
                //timeConsumed=timer;
                //current.setTimeConsumed(timer-timeConsumed);
                if (timer==nextTimeThatWillConsumed)
                {
                    current.setTimeConsumed(current.getTime());
                    int saved=current.getPlayerNo();
                    return saved;
                }
                current.setTimeConsumed(timer-timeConsumed+current.getTimeConsumed());
                    return current.getPlayerNo();
            }
            //System.out.println("outside bigger");
            //System.out.println("timer currenttimeconsumed nexttimethatwill consudmed"+timer+" "+current.getTimeConsumed()+" "+nextTimeThatWillConsumed);
            current.setTimeConsumed(0);
            timeConsumed=nextTimeThatWillConsumed;
            if (LeftToRight==1) current = current.getNext();
            else current= current.getPrev();

        }
    }

    public void display()
    {
        Node save=new Node(0,0,null, null);
        if (current==null)
            current=head;
        save=current;
        System.out.println(save.getPlayerNo()+" "+save.getTimeConsumed());
        //System.out.println(current.getPlayerNo()+" "+current.getTimeConsumed());
        //System.out.println("just printed current.getplayerno");
        int temp=0;
        while(save.getNext().getPlayerNo()!=current.getPlayerNo())
        {
            //System.out.println("inside while of display");
            save=save.getNext();
            System.out.println(save.getPlayerNo()+" "+save.getTimeConsumed());
            temp++;
        }
    }
    public boolean check()
    {
        //System.out.println("inside checking ");
        if (size==1)
        {
            System.out.println("Game over : Player "+current.getPlayerNo()+" wins!!");
        }
        return size==1;
    }
    public void reverse(int timer)
    {
        int timeConsumed = 0;
        if (timer==0)
        {
            LeftToRight=LeftToRight^1;
            return;
        }
        int nextTimeThatWillConsumed=0;
        if (current.getTime()==current.getTimeConsumed())
        {
            current.setTimeConsumed(0);
            if (LeftToRight==1) current = current.getNext();
            else current= current.getPrev();

        }
        while (true) {
            nextTimeThatWillConsumed+=current.getTime()-current.getTimeConsumed();
            if (timer <= nextTimeThatWillConsumed){
                //System.out.println("inside bigger");
                //System.out.println("timer currenttimeconsumed nexttimethatwill consudmed"+timer+" "+current.getTimeConsumed()+" "+nextTimeThatWillConsumed);
                //timeConsumed=timer;
                //current.setTimeConsumed(timer-timeConsumed);
                if (timer==nextTimeThatWillConsumed)
                {
                    current.setTimeConsumed(current.getTime());
                    int saved=current.getPlayerNo();
                    if (LeftToRight==1) current = current.getPrev();
                    else current= current.getNext(); //reverse hocche bole
                    break;
                }
                current.setTimeConsumed(timer-timeConsumed+current.getTimeConsumed());
                break;
            }
            //System.out.println("outside bigger");
            //System.out.println("timer currenttimeconsumed nexttimethatwill consudmed"+timer+" "+current.getTimeConsumed()+" "+nextTimeThatWillConsumed);
            current.setTimeConsumed(0);
            timeConsumed=nextTimeThatWillConsumed;
            if (LeftToRight==1) current = current.getNext();
            else current= current.getPrev();

        }
        LeftToRight=LeftToRight^1;
    }
    public static void main(String[] args) {
        // write your code here
        Scanner scanner=new Scanner(System.in);
        CircularlyLinkedList var=new CircularlyLinkedList();
        int N, temp;
        N=scanner.nextInt();
        int totalPlayer=0;
        for (int i =0; i<N; i++)
        {
            temp=scanner.nextInt();
            var.insertAtEnd(temp, ++totalPlayer);
        }
        //System.out.println("before display after inpuitng");
        //var.display();
        //System.out.println("after dispaly in main");
        int aTime, prevTime=0;
        char command;
        int playerNew;
        Node node=new Node();
        var.current=var.head;
        boolean flag=false; //this means game is not over
        while (true) {
            //System.out.println("inside main while loop");

            aTime = scanner.nextInt();
            command = scanner.next().charAt(0);
            //System.out.println("command is "+ command);


            //as it was written in pdf
             if (command == 'I') {
                playerNew = scanner.nextInt();
                if (flag)
                    continue;
                var.insertAtPos(playerNew, ++totalPlayer, aTime-prevTime); //playerNew means time of playerNew

            }


            else if (command == 'P')
            {
                if (flag)
                    continue;
                playerNew = var.getAnElementAtTime(aTime - prevTime);
                System.out.println("Player " + playerNew + " is holding the pillow at t=" + aTime);
            }
            else if (command == 'R')
            {
                //System.out.println("inside R");
                if (flag)
                    continue;
                var.reverse(aTime - prevTime);
                //System.out.println("exiting from R");

            }
            else if (command == 'M')
            {
                if (flag) {
                    //System.out.println("When command is M and flag is true");
                    continue;

                }
                playerNew=var.deleteCurrentAtTime(aTime-prevTime);
                System.out.println("Player "+playerNew+" has been eliminated at t="+aTime);
                //var.display();
                flag= var.check();

            }


            else if (command == 'F')
            {
                //System.out.println("inside F");
                //var.display();
                if (flag)
                    break;
                var.display(aTime,prevTime);
                //var.display();
                break;
            }
            //System.out.println("after time "+aTime);
            //var.display();

            //System.out.println("an end of while loop in main");
            prevTime=aTime;
        }
    }
}
