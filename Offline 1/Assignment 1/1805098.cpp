#include <bits/stdc++.h>
#define Defined 100
class Array
{
    string A[];
    int len;
    int currentI;

public :
    Array()
    {
        A=new string[Defined];
        len=Defined;
        currentI=0;
    }
    Array(int n)
    {
        A=new string[n];
        len=n;
        currentI=0;
    }

    Array (string b[])
    {
        currentI=0;
        if (len<b.size())
        {
            len=b.size();
            A=new string[len];
        }
        for (int i=0; i<len; i++)
        {
            strcpy(b[i], a[i]);

            currentI++;
        }

    }

    string[] getArray()
    {
        return A;
    }

    string getAnElement(int i)
    {
        return A[i];
    }

    void add(string element)
    {
        if (len<=currentI)
        {
            len++;
            string B=new string [len];
            strcpy(element, B[len]);
            strcpy(element,A[currentI]);
            currentI++;
        }
        else
        {
            strcpy(element,A[currentI]);
            currentI++;
        }
    }
    void add ( int i, string element)
    {
        if (currentI!=len)
        {

            for (int j = currentI+1; j>i ; j--)
            {

                a[j]=a[j-1]; //moving to right

            }
            strcpy(element, a[i]);
        }
        else
        {
            string[] b=new string[len+1];
            for (int j=0; j<len; j++)
            {
                strcpy(a[j],b[j]); //just copying
            }

            for (int j = currentI+1; j>i ; j--)
            {

                b[j]=b[j-1]; //moving to right

            }
            strcpy(element, b[i]);

            delete A[];
            A=new string [++len];
            for (int j=0; j<len; j++)
            {
                strcpy(b[j], A[j]);
            }



        }
    }

    void Remove(string element)
    {
        for (int i=0; i<currentI; i++)
        {
            if (A[i]==element)
            {
                for (int j=i; j<currentI-1; j++)
                {
                    A[j]=A[j+1];

                }
                currentI--;
            }
        }
    }

    int []findIndex(string element)
    {
        int totalElementNum=0;
        for (int i=0; i<len; i++)
        {
            if (A[i]==element)
            {
                totalElement++;
            }
        }
        int index[totalElement];
        int j=0;
         for (int i=0; i<len; i++)
        {
            if (A[i]==element)
            {
                index[j++]=i;
            }
        }
        return index;
    }
    string[] subArray(int start, int endd)
    {
        string b[endd-start+1];

        for (int i=start, i<=endd; i++)
        {
            b[i-start]=a[i]; // i will change if not works
        }
        return b;
    }
    string[] merge(string A1[], string A2[])
    {
        string b[A1.size()+A2.size()];
        int i=0;
        for ( i=0; i<A1.size; i++)
        {

            b[i]=A1[i];
        }
        for (;i<A1.size()+A2.size(); i++)
        {
            b[i]=A2[i-A1.size()];
        }
        return b;
    }
    bool isEmpty()
    {
        if (currentI==0)
            return false;
        return true;
    }
    int length()
    {
        return len;
    }

};
