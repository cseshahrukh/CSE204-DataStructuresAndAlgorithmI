#include <bits/stdc++.h>
using namespace std;
int arr[1000000];
    // A utility function to swap two elements
void swap(int* a, int* b)
{
    int t = *a;
    *a = *b;
    *b = t;
}

/* This function takes last element as pivot, places
the pivot element at its correct position in sorted
array, and places all smaller (smaller than pivot)
to left of pivot and all greater elements to right
of pivot */
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




// Driver Code
int main()
{int n, i;
   cout<<"\nEnter the number of data element to be sorted: ";
   cin>>n;
   for (i=0; i<n; i++)
    arr[i]=i;


    clock_t time_of_insertion_in_pq = clock();

   quickSort(arr, 0, n - 1);


cout <<"Quick sort takes time "<< float(clock() - time_of_insertion_in_pq)*1000 / CLOCKS_PER_SEC << " miliseconds\n";


    return 0;
}

