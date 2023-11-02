#include <bits/stdc++.h>
using namespace std;


#define pb          push_back
#define ll          long long
#define N 1005

int reward[N];
vector<int> g[N];
bool visited[N];
int friends[N];
int friendsCity[N];

int dfs(int source)
{
    int thisReward=0;
    thisReward+=reward[source];
    reward[source]=0;
    visited[source]=true;
    for(int v: g[source])
    {
        if(!visited[v])
        {
            visited[v]=true;
            thisReward+=dfs(v);
        }
    }
    return thisReward;
}
int main()
{

    //freopen("input.txt", "r", stdin);
    ll C, R, L, F, C1, C2, Cx, Px, Cy, Fy, Collection=0;
    ll totalPiece=0;
    cin>>C>>R>>L>>F;
    ofstream x("text.txt");
    for (int i=0; i<R; i++)
    {
        cin>>C1>>C2;
        g[C1].push_back(C2);
        g[C2].push_back(C1);
    }

    for (int i=0; i<L; i++)
    {
        cin>>Cx>>Px;
        reward[Cx]=Px; //Cx city te Px reward ache
        totalPiece+=Px;
    }

//    cout<<"printing reward place"<<endl;
//    for (int i=0; i<C; i++)
//        cout<<reward[i]<<endl;

    for (int i=0; i<F; i++)
    {
        cin>>Cy>>Fy;
        friendsCity[Fy]=Cy;

        //dfs e Cy ke visited korbo
    }

//    cout<<"printing frend pos"<<endl;
//    for(int i=0; i<F; i++)
//    {
//
//        cout<<friendsCity[i]<<endl;
//    }
    for(int i=0; i<F; i++)
    {
        friends[i]=dfs(friendsCity[i]);
        Collection+=friends[i];
    }

    bool flag=true;
    for(int i=0; i<C; i++)
    {
        if (reward[i]>0)
        {
            flag=false;
            break;
        }
    }
    if (flag)
    {
        cout<<"Mission Accomplished"<<endl;
        x<<"Mission Accomplished"<<endl;
        cout<<Collection<<" out of "<<totalPiece<<" pieces are collected"<<endl;
        x<<Collection<<" out of "<<totalPiece<<" pieces are collected"<<endl;
    }
    else
    {
        cout<<"Mission Impossible"<<endl;
        x<<"Mission Impossible"<<endl;
        cout<<Collection<<" out of "<<totalPiece<<" pieces are collected"<<endl;
        x<<Collection<<" out of "<<totalPiece<<" pieces are collected"<<endl;
    }
    for (int i=0; i<F; i++)
    {
        cout<<i<<" collected "<<friends[i]<< " pieces"<<endl;
        x<<i<<" collected "<<friends[i]<< " pieces"<<endl;
    }



    return 0;
}
