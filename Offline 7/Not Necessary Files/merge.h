#include <bits/stdc++.h>
int L[500000], R[500000];
int n1, n2;
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
