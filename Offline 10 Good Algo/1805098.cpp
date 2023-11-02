#include <bits/stdc++.h>
using namespace std;
#define ll long long
#define mod 1000000007
ll dp[103][10003];

ll solve(ll n, ll s, ll f[])
{
//    for(int i=1; i<=n; i++)
//        cout<<f[i]<<' ';
//    cout<<endl;
    dp[0][0]=1;
    for(int j=1; j<=s; j++)
    {
        dp[0][j]=1; //just a cumulative sum
    }
    for(int i=1; i<=n; i++)
    {
        for(int j=0; j<=s; j++)
        {

            if (j-f[i]-1<0)
                dp[i][j]=dp[i-1][j-1];
            else
            {
                dp[i][j]=dp[i-1][j-1]-dp[i-1][j-f[i]-1];
                if (dp[i][j]<0)
                    dp[i][j]+=mod;
            }
        }
        if (i==n)
            break;

        //Cumulative sum
        for(int j=1; j<=s; j++)
        {
            dp[i][j]=(dp[i][j]+dp[i][j-1])%mod;

        }
    }


    //printing the dp
//    cout<<"Printing the dp array"<<endl;
//    for (int i=0; i<=n;i++)
//    {
//        for(int j=0; j<=s; j++)
//            cout<<dp[i][j]<<' ';
//        cout<<endl;
//    }
    return dp[n][s];
}
int main ()
{
    //freopen("Test 6.txt", "r", stdin);
    //freopen("text.txt", "r", stdin);
    ll n, s;
    cin>>n>>s;

    ll f[n+1];
    for(int i=1; i<=n; i++)
    {
        cin>>f[i];
    }

    ll result=solve(n, s, f);
    cout<<result<<endl;

}
