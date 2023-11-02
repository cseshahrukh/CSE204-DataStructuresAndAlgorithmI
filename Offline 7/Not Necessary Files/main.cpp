#include <bits/stdc++.h>
#include<ctime>
using namespace std;
int arr[1000000], arr1[1000000], arr2[1000000];
int L[500000], R[500000];
int n1, n2;
void printArray(int A[], int size)
{
    for (int i = 0; i < size; i++)
        cout << A[i] << " ";

    cout<<endl;
}
void swap(int* a, int* b)
{
    int t = *a;
    *a = *b;
    *b = t;
}

int partition (int arr[], int low, int high)
{
    int pivot = arr[high]; // pivot //last element ke pivot dhorche
    int i = (low - 1); // Index of smaller element and indicates the right position of pivot found so far

    for (int j = low; j <= high - 1; j++)
    {
        // If current element is smaller than the pivot
        if (arr[j] < pivot)
        {
            i++; // increment index of smaller element
            swap(&arr[i], &arr[j]);
        }
    }
    swap(&arr[i + 1], &arr[high]);
    return (i + 1);
}



void quickSort(int arr[], int low, int high)
{
    if (low < high)
    {
        /* pi is partitioning index, arr[p] is now
        at right place */
        int pi = partition(arr, low, high);

        // Separately sort elements before
        // partition and after partition
        quickSort(arr, low, pi - 1);
        quickSort(arr, pi + 1, high);
    }
}

void merge(int arr[], int l, int m, int r)
{
    n1 = m - l + 1;
    n2 = r - m;

    // Create temp arrays
    //int L[n1], R[n2];

    // Copy data to temp arrays L[] and R[]
    for (int i = 0; i < n1; i++)
        L[i] = arr[l + i];
    for (int j = 0; j < n2; j++)
        R[j] = arr[m + 1 + j];



    // first subarray traverse
    int i = 0;

    // second subarray traverse
    int j = 0;

    // original array traverse
    int k = l;

    while (i < n1 && j < n2) {
        if (L[i] <= R[j]) {
            arr[k] = L[i];
            i++;
        }
        else {
            arr[k] = R[j];
            j++;
        }
        k++;
    }

    //left tay ekhono keu ache
    while (i < n1) {
        arr[k] = L[i];
        i++;
        k++;
    }

    // right tay ekhono keu ache
    while (j < n2) {
        arr[k] = R[j];
        j++;
        k++;
    }
}

// l is for left index and r is
// right index of the sub-array
// of arr to be sorted */
void mergeSort(int arr[],int l,int r){
    if(l>=r){
        return;//returns recursively
    }
    int m =l+ (r-l)/2;
    mergeSort(arr,l,m);
    mergeSort(arr,m+1,r);
    merge(arr,l,m,r);
}

int main ()
{
    while(1)
    {
        int n, order;
        cout<<"Give total number of elements you want to sort"<<endl;
        cin>>n;
        cout<<"Press 1 to generate ascending order"<<endl;
        cout<<"Press 2 to generate descending order"<<endl;
        cout<<"Press 3 to generate random order"<<endl;

        int test=1;
        double mergesorttime=0, QuickSorttime=0;
        cin>>order;

        while(test<=5)
        {


            if (order==1)
            {
                for (int i=0; i<n; i++)
                {
                    arr[i]=i;
                }
            }
            else if (order==2)
            {
                for (int i=0; i<n; i++)
                {
                    arr[i]=n-i;
                }
            }
            else
                for (int i=0; i<n; i++)
                {
                    arr[i]=rand();
                }


            for (int i=0; i<n; i++)
            {
                arr2[i]=arr[i];
            }

            clock_t time_of_insertion_in_pq = clock();
            //cout<<"Before Merge Sort"<<endl;
            //printArray(arr, n);
            //cout<<"After Merge Sort"<<endl;
            mergeSort(arr, 0, n-1);
            //printArray(arr, n);

            cout<<"Test" <<test++<<endl;
            cout <<"Merge sort takes time "<< float(clock() - time_of_insertion_in_pq)*1000 / CLOCKS_PER_SEC << " miliseconds\n";
            mergesorttime+=float(clock() - time_of_insertion_in_pq)*1000 / CLOCKS_PER_SEC;

            clock_t time_of_insertion_in_my_heap = clock();
            //cout<<"Before Quick Sort"<<endl;
            //printArray(arr2, n);
            quickSort(arr2, 0, n-1);
            //cout<<"After quick sort"<<endl;
            //printArray(arr2, n);
            cout <<"Quick sort takes time "<< float(clock() - time_of_insertion_in_pq)*1000 / CLOCKS_PER_SEC << " miliseconds\n";
            QuickSorttime+=float(clock() - time_of_insertion_in_pq)*1000 / CLOCKS_PER_SEC;
        }

        mergesorttime/=5;
        QuickSorttime/=5;
        cout<<"avg merge mili second time "<<mergesorttime<<endl;
        cout<<"avg quick mili second time "<<QuickSorttime<<endl;


        int command=1;
        cout<<"If you want to quit then press 0 else press other number"<<endl;
        cin>>command;
        if (command==0)
            break;
    }
}
