#include<iostream>
#include<fstream>
#include<cstdio>
#include<queue>
#include<ctime>

using namespace std;

int left(int i)
{

    return 2*i+1; //as zero based
}

int right(int i)
{
    return 2*i+2;
}

int parent(int i)
{
    return (i-1)/2;
}

void heapify(int numbers[], int i)
{
    //bubble up up approach
    //i er left right gula heap manche.
    //cout<<"insdie heapify function"<<endl;
    if (i==0) return;
    int p=parent(i);

    if (numbers[i]>numbers[p])
    {
        swap(numbers[i], numbers[p]);

        heapify(numbers, p);
    }

}


void max_heapify(int heap[], int heap_size, int i)
{
    //bubble down approach
    //heap_size ta sort e lagbe
    //actually heap_size er dorkar e nai !!
    //this means this is a leaf
    if (2*i+1>=heap_size)
        return ;

    int l, r, largest, t;
    l=left(i);
    r=right(i);

    if (l<heap_size && heap[l]>heap[i])
        largest=l;
    else
        largest=i;

    if (r<heap_size && heap[r]>heap[largest])
        largest=r;

    //base case
    if (largest==i)
        return;
    swap(heap[i], heap[largest]);

    max_heapify(heap, heap_size, largest);
}


class Heap
{
public:
    int* numbers;
    int heapSize=0;
    int current=0;

    Heap(int siz)
    {
        heapSize=siz;
        numbers=new int[siz];
    }
    int size()
    {
        return heapSize;
    }
    void insert(int element)
    {

        //cout<<"Inside insert"<<endl;
        //numbers.push_back(element);
        numbers[current++]=element;

        //bottom up
        //cout<<"before calling heapify"<<endl;
        heapify(numbers, current-1); //heapify(numbers, last index) //heapify hocche bubble up approach
    }


    void deleteKey()
    {
        // Get the last element
        int lastElement = numbers[current-1];

        // Replace root with first element
        numbers[0] = lastElement; //root is gone !!!
        //numbers.pop_back();

        current--;
        // heapify the root
        max_heapify(numbers, current, 0); //bubble down
        //heap er property main tain korte hobe

//        for(int i=0; i<current; i++)
//        {
//            cout<<numbers[i]<<" ";
//        }
//        cout<<endl;
    }
    int getMax()
    {
        return numbers[0];
    }
};


void heapsort(vector <int> &arr)
{
    Heap hp(arr.size());
    for (int i=0; i<arr.size(); i++)
        hp.insert(arr[i]);

//        cout<<"inside heapsort printing unchanged arrays"<<endl;
//        for (int i=0; i<hp.current; i++)
//    {
//        cout<<hp.numbers[i]<<" ";
//    }
//    cout<<endl;
    for (int i=0; i<arr.size(); i++)
    {
        arr[i]=hp.getMax();
        hp.deleteKey();
    }
}
