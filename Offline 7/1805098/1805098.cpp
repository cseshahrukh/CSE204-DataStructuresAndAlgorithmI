//Roll 1805098
//Md. Shahrukh Islam
#include <bits/stdc++.h>
#include<ctime>
using namespace std;
int arr[1000000], arr2[1000000];
int L[500000], R[500000];
int n1, n2;
void printArray(int A[], int B[], int size)
{
    for (int i = 0; i < size; i++)
        cout << A[i] << "\t\t\t\t"<<B[i]<<endl;

    cout<<endl;
}
void swap(int* a, int* b)
{
    int t = *a;
    *a = *b;
    *b = t;
}

void quickSort(int arr[], int low, int high)
{
    if (low>=high)
        return;
    int p=arr[high];
    int l=low, r=high-1; //high is pivot

    while (l<=r)
    {
        //find element largar than pivot
        while(l<=r and arr[l]<=p)
        {
            l=l+1;
        }

        //find smaller element than pivot
        while(r>=l and arr[r]>=p)
        {
            r=r-1;
        }

        if (l<r)
        {
            swap(arr[l], arr[r]);
        }

    }

    //putting pivot at good place
    swap(arr[l], arr[high]);

    quickSort(arr, low, l - 1);
    quickSort(arr, l + 1, high);
}

void merge(int arr[], int l, int mid, int r)
{
    //m is mid ja left er sathe ache
    n1 = mid - l + 1;
    n2 = r - mid;

    //Copy
    for (int i = 0; i < n1; i++)
        L[i] = arr[l + i];
    for (int j = 0; j < n2; j++)
        R[j] = arr[mid + 1 + j];

    // first subarray traverse
    int i = 0;

    // second subarray traverse
    int j = 0;

    // original array traverse
    int k = l;

    while (i < n1 && j < n2)
    {
        if (L[i] <= R[j])
        {
            arr[k] = L[i];
            i++;
        }
        else
        {
            arr[k] = R[j];
            j++;
        }
        k++;
    }

    //left tay ekhono keu ache
    while (i < n1)
    {
        arr[k] = L[i];
        i++;
        k++;
    }

    // right tay ekhono keu ache
    while (j < n2)
    {
        arr[k] = R[j];
        j++;
        k++;
    }
}


void mergeSort(int arr[],int l,int r)
{
    if(l>=r)
    {
        return;
    }
    int mid =l+ (r-l)/2;
    mergeSort(arr,l,mid);
    mergeSort(arr,mid+1,r);
    merge(arr,l,mid,r);
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
                arr[0]=rand()%100;
                for (int i=1; i<n; i++)
                {
                    arr[i]=arr[i-1]+1;
                }
            }
            else if (order==2)
            {
                arr[0]=n-rand()%100;
                for (int i=1; i<n; i++)
                {
                    arr[i]=arr[i-1]-1;
                }
            }
            else
            {


                for (int i=0; i<n; i++)
                {
                    arr[i]=rand();
                }
            }

            for (int i=0; i<n; i++)
            {
                arr2[i]=arr[i];
            }

            cout<<"Test" <<test++<<endl;

            clock_t time_of_sort = clock();
            mergeSort(arr, 0, n-1);
            cout <<"Merge sort takes time "<< float(clock() - time_of_sort)*1000 / CLOCKS_PER_SEC << " miliseconds\n";
            mergesorttime+=float(clock() - time_of_sort)*1000 / CLOCKS_PER_SEC;

            clock_t time_of_qsort = clock();
            quickSort(arr2, 0, n-1);
            cout <<"Quick sort takes time "<< float(clock() - time_of_qsort)*1000 / CLOCKS_PER_SEC << " miliseconds\n";
            QuickSorttime+=float(clock() - time_of_qsort)*1000 / CLOCKS_PER_SEC;
        }

        mergesorttime/=5;
        QuickSorttime/=5;
        cout<<"avg merge mili second time "<<mergesorttime<<endl;
        cout<<"avg quick mili second time "<<QuickSorttime<<endl;

        printArray(arr, arr2, n);
        int command=1;
        cout<<"If you want to quit then press 0 else press other number"<<endl;
        cin>>command;
        if (command==0)
            break;
    }
}
