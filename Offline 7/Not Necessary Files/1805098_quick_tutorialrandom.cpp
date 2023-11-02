#include<iostream>
#include<cstdlib>

using namespace std;



int Partition(int a[], int l, int h) {
   int pivot, index, i;
   index = l;
   pivot = h;
   for(i = l; i < h; i++) {
      if(a[i] < a[pivot]) { //ai last element er cheye choto hole
                            //ai ar aindex swap korbo
         swap(a[i], a[index]);
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
int RandomPivotPartition(int a[], int l, int h) {
   int pvt, n, temp;
   n = rand();
   pvt = l + n%(h-l+1); //l theke r porjonto jekono index nibo
   swap(a[h], a[pvt]); //pivot ta sob theke right e chole gelo //sob array tei pivot ta china jabe
   return Partition(a, l, h);
}
int QuickSort(int a[], int l, int h) {
   int pindex;
   if(l < h) {
      pindex = RandomPivotPartition(a, l, h); //ekhon pindex er age sob gula a[pindex] er cheye choto
                                                //ar  porer sob gula a[pindex] theke boro
      QuickSort(a, l, pindex-1);
      QuickSort(a, pindex+1, h);
   }
   return 0;
}
int main() {
   int n, i;
   cout<<"\nEnter the number of data element to be sorted: ";
   cin>>n;
   int arr[n];
   for(i = 0; i < n; i++) {
      cout<<"Enter element "<<i+1<<": ";
      cin>>arr[i];
   }
   QuickSort(arr, 0, n-1);
   cout<<"\nSorted Data ";
   for (i = 0; i < n; i++)
      cout<<"->"<<arr[i];
   return 0;
}
