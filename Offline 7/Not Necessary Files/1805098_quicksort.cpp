#include<iostream>
#include<cstdlib>
#include<bits/stdc++.h>
using namespace std;
int arr[1000000];
int Partition(int a[], int l, int h) {
   int pivot, index, i;
   index = l;
   pivot = h;
   for(i = l; i < h; i++) {
      if(a[i] < a[pivot]) { //ai last element er cheye choto hole
                            //ai ar aindex swap korbo
         swap(a[i], a[index]); //a[index] e boro ar a[i] te choto tai swap hoye jabe
         index++;
      }
   }
   swap(a[pivot], a[index]); //traverse seshe pivot tato sob theke right e ache
   //pivot tato tar jaygay thaka uchit jeno bame sobai choto ar dane sobai boro thake
   //pivot er age ekhon sob e choto
                                //ar pivot er pore ekhon sob e boro
   return index;
   //pivot ekhon jeikhane oi index ta return korchi
}

void QuickSort(int a[], int l, int h) {
   int pindex;
   if(l < h) {
        pindex=Partition(a, l, h); //last element emon jaygay bosabo jeno tar age sob gula choto ar pore sob gula boro
      QuickSort(a, l, pindex-1);
      QuickSort(a, pindex+1, h);
   }
   //return 0;
}

int main() {
   int n, i;
   cout<<"\nEnter the number of data element to be sorted: ";
   cin>>n;
   for (i=0; i<n; i++)
    arr[i]=i;

   clock_t time_of_insertion_in_pq = clock();
   QuickSort(arr, 0, n-1);


cout <<"Quick sort takes time "<< float(clock() - time_of_insertion_in_pq)*1000 / CLOCKS_PER_SEC << " miliseconds\n";
   return 0;
}
