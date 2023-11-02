#include <bits/stdc++.h>
#include "heap.h"
using namespace std;
int main ()
{

    vector<int> v;
    v.push_back(0);
    v.push_back(1);
    v.push_back(16); v.push_back(19); v.push_back(22); v.push_back(-69); v.push_back(16); v.push_back(32);
    heapsort(v);
    //cout<<"Printing after heapsort function call"<<endl;
     for (int i=0; i<v.size(); i++)
    {
        cout<<v[i]<<" ";
    }
    cout <<endl;


//    sort(v.begin(), v.end(), greater<int>());
//
//    for (int i=0; i<v.size(); i++)
//    {
//        cout<<v[i]<<" ";
//    }
//
//
//
//    cout<<endl;
//    heapsort(v);
//     for (int i=0; i<v.size(); i++)
//    {
//        cout<<v[i]<<" ";
//    }
//    cout <<endl;
//
//    sort(v.begin(), v.end());
//    for (int i=0; i<v.size(); i++)
//    {
//        cout<<v[i]<<" ";
//    }
//    cout <<endl;

}
