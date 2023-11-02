#include <bits/stdc++.h>
#define ll long long
using namespace std;

int main ()
{
    ll n, k;
    cin>>n>>k;
    ll price[n];
    for (int i=0; i<n; i++)
        cin>>price[i];

    sort (price, price+n, greater<long long> ());

    ll cost=0, cnt=0;
    for (int i=0; i<n; i++)
    {
        cnt=i/k+1;
        cost+=price[i]*cnt;
    }
    cout<<cost<<endl;
}
