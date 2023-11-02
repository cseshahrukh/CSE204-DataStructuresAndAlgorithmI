package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Array{
    private String[] Arr;
    private int currentIndex;

    Array()
    {
        Arr=new String[2];
        currentIndex=0;
    }
    Array(int n)
    {
        Arr=new String[n];
        currentIndex=0;
    }
    Array (String[] A)
    {
        Arr=new String [A.length];
        int i=0;
        for (String str: A)
        {
            Arr[i++]=str;
        }
        currentIndex=i;
    }

    int getCurrentIndex()
    {
        return currentIndex;
    }
    String[] getArray()
    {
        return Arr;
    }
    String getAnElement(int i)
    {
        //System.out.println("inside get an elemnt");
        //System.out.println("printing current Index");
        //System.out.println(currentIndex);
        if (currentIndex==0) {
            return "No element";
        }
        if (i>=currentIndex)
            return "Invalid Input in getAnElement";

        return Arr[i];
    }
    void add(String element)
    {
        if (currentIndex< Arr.length)
        {
            System.out.println("Inside add currI is "+ currentIndex +" and Arr.len is "+Arr.length);
            Arr[currentIndex]=element;
        }
        else
        {
            String[] B=new String[Arr.length+1];
            for (int i=0; i<Arr.length; i++)
            {
                B[i]=Arr[i];
            }
            B[Arr.length]=element;

            Arr=new String[B.length];
            for (int i=0; i<B.length; i++)
            {
                Arr[i]=B[i];
            }
        }
        currentIndex++;
    }

    void add(int i,String element)
    {
        if (i>currentIndex) {
            System.out.println("Invalid index");
            return;
        }
        else if (i==currentIndex && i< Arr.length-1)
        {
            //No need to make it bigger size
            for (int j=currentIndex; j>i; j--)
            {
                Arr[j]=Arr[j-1]; //shifting to right
            }
            Arr[i]=element;
        }
        else
        {
            //System.out.println("inside else ");
            //resize and shift
            String[] B=new String[Arr.length+1];
            for (int j=0; j<Arr.length; j++)
            {
                //System.out.println("inside first for loop of add");
                B[j]=Arr[j];
            }

            Arr=new String[B.length];
            for (int j=0; j<B.length; j++)
            {
                //System.out.println("inside second for loop of add");
                Arr[j]=B[j];
            }

            for (int j=Arr.length-1; j>i; j--)
            {
                //System.out.println("inside third for loop of add");
                Arr[j]=Arr[j-1]; //shifting to right
            }
            Arr[i]=element;
        }
        currentIndex++;
    }

    void Remove(int i)
    {
        if (i>=currentIndex)
            System.out.println("Your input is so big index !!! ");
        for (int j=i; j<currentIndex-1; j++)
        {
            Arr[j]=Arr[j+1];
        }
        currentIndex--;
        Arr[currentIndex]=null; //last element
        //System.out.println("in remove currentIndex Update "+ currentIndex);
    }
    void Remove(String element)
    {

        for (int i=0; i<currentIndex; i++)
        {
            if (Arr[i].compareTo(element)==0)
            {
                //System.out.println();
                //System.out.println("before remove i and current Index"+ i +" "+currentIndex);
                //System.out.println(Arrays.toString(Arr));

                Remove(i);
                //System.out.println();
                //System.out.println(Arrays.toString(Arr));
                //System.out.println("after remove i and current Index"+ i +" "+currentIndex);
                //System.out.println();
                i--; //pashapashi remove kora element ta thakte pare. so update hoye ith element e oita thakte pare
            }
        }
    }
    List<Integer> findIndex(String element)
    {
        //System.out.println("start of findIndex");
        List<Integer> Indexs = new ArrayList<>();
        //System.out.println("current index is "+ currentIndex);
        for (int i=0; i<currentIndex; i++)
        {
            //System.out.println("inside for loop ");
            if (Arr[i].compareTo(element)==0)
            {
                Indexs.add(i);
            }
        }
        //System.out.println("Inside findIndex printing indexs ");
        //System.out.println(Indexs.toString());
        return Indexs;
    }
    String[] subArray(int start, int end)
    {
        if (start<0 || end>=currentIndex || start>end )
        {
            System.out.println("invalid parameter");
        }
        String B[]=new String[end-start+1];
        for (int i=start; i<=end; i++)
        {
            B[i-start]=Arr[i];

        }
        return B;
    }
    int length()
    {
        return Arr.length;
    }
    boolean isEmpty()
    {
        if (currentIndex==0)
            return true;
        return false;
    }
    void merge(String A1[], String A2[])
    {
        Arr=new String[A1.length+ A2.length];
        currentIndex=A1.length+A2.length;
        //System.out.println("size of this array is "+ Arr.length);
        int index, indexA=0, indexB=0;
        for (index=0; indexA<A1.length && indexB<A2.length; index++)
        {
            //System.out.println("inside for ");
            if (A1[indexA].compareTo(A2[indexB])<0)
            {
                Arr[index]=A1[indexA++];
            }
            else
                Arr[index]=A2[indexB++];
        }
        while (indexA< A1.length)
        {
            Arr[index++]=A1[indexA++];
        }
        while (indexB< A2.length)
        {
            Arr[index++]=A2[indexB++];
        }
    }
}



public class Main {


    public static void main(String[] args) {
	// write your code here
        Array DefaultConstructor=new Array();
        System.out.println(DefaultConstructor.length()); //should print 2
        System.out.println(DefaultConstructor.isEmpty()); //should print true
        System.out.println("printing current index");
        System.out.println(DefaultConstructor.getCurrentIndex());
        DefaultConstructor.add("Hello");
        System.out.println(Arrays.toString(DefaultConstructor.getArray()));
        System.out.println("printing current index");
        System.out.println(DefaultConstructor.getCurrentIndex());
        DefaultConstructor.add("World");
        System.out.println(Arrays.toString(DefaultConstructor.getArray()));

        DefaultConstructor.add("Again");
        System.out.println(Arrays.toString(DefaultConstructor.getArray()));
        System.out.println(DefaultConstructor.length()); //should print 3
        System.out.println(DefaultConstructor.isEmpty()); //should print false
        DefaultConstructor.add(3,"After Again");
        System.out.println(Arrays.toString(DefaultConstructor.getArray()));
        DefaultConstructor.add(2,"Before Again");
        System.out.println(Arrays.toString(DefaultConstructor.getArray()));
        DefaultConstructor.add(23, "This will not work");
        System.out.println(Arrays.toString(DefaultConstructor.getArray()));


        Array parCons=new Array(120);
        System.out.println(parCons.length()); //should print 120
        System.out.println(parCons.isEmpty()); //should print true
        System.out.println(parCons.getAnElement(110)); // should get invalid message as no initialization done


        String[] countries = {"Zimbabwe", "South-Africa", "India", "America", "Yugoslavia",
                " Australia", "Denmark", "France", "Netherlands", "Italy", "Germany"};

        Array var=new Array(countries);
        System.out.println(var.length());//should print 11
        System.out.println(var.isEmpty()); //should print false
        System.out.println(var.getAnElement(110)); // should get invalid message
        System.out.println(var.getAnElement(2)); // should get India
        System.out.println(Arrays.toString(var.getArray()));

        String[] sameCountries=var.getArray();
        System.out.println(Arrays.toString(sameCountries));

        var.Remove(2);
        System.out.println(Arrays.toString(var.getArray()));



        String[] sorted1={"Australia", "Bangladesh", "Germany"};
        String[] sorted2={"Austria", "India", "Italy"};
        var.merge(sorted1, sorted2);
        System.out.println(var.getCurrentIndex());
        System.out.println(Arrays.toString(var.getArray()));

        String[] subCountries= var.subArray(1, 3);
        System.out.println(Arrays.toString(subCountries));

        var.add("Bangladesh");
        var.add("Bangladesh");
        System.out.println(Arrays.toString(var.getArray()));

        var.add(6, "Nepal");
        //System.out.println(var.length());
        //System.out.println(var.currentIndex);
        List<Integer> indexofBangladesh=  var.findIndex("Bangladesh");
        for(int indexx: indexofBangladesh)
            System.out.print(indexx+" ");
        System.out.println();
        var.Remove(56); //should get error
        var.Remove("Bangladesh");
        System.out.println(Arrays.toString(var.getArray()));
        System.out.println(var.length());
        System.out.println(var.isEmpty());

    }
}
