#include <bits/stdc++.h>
using namespace std;


struct Point
{
    int x, y;
};



int compareX(Point a, Point b)
{

    return a.x<b.x;
}


int compareY(Point a, Point b)
{

    return a.y<b.y;
}


float dist(Point p1, Point p2)
{
    return sqrt( (p1.x - p2.x)*(p1.x - p2.x) + (p1.y - p2.y)*(p1.y - p2.y));
}


float bruteForce(Point P[], int n, float d, Point A[], float mini)
{

    for (int i = 0; i < n; ++i)
        for (int j = i+1; j < n; ++j)
            if (dist(P[i], P[j]) < d and dist(P[i], P[j])>mini){
                d = dist(P[i], P[j]);
                A[0].x=P[i].x, A[0].y=P[i].y;
                A[1].x=P[j].x, A[1].y=P[j].y;

            }
    return d;
}





float stripClosest(Point strip[], int size, float d, Point A[], float mini)
{
    float min = d;

    for (int i = 0; i < size; ++i)
        for (int j = i+1; j < size && (strip[j].y - strip[i].y) < min; ++j)
            if (dist(strip[i],strip[j]) < min and dist(strip[i],strip[j]) > mini){
                min = dist(strip[i], strip[j]);
                A[0].x=strip[i].x, A[0].y=strip[i].y;
                A[1].x=strip[j].x, A[1].y=strip[j].y;
            }

    return min;
}


float closestUtil(Point Px[], Point Py[], int n, float d, Point A[], float mini)
{
    if (n <= 3)
        return bruteForce(Px, n, d, A, mini);


    int mid = n/2;
    Point midPoint = Px[mid];



    Point Pyl[mid];   // y sorted points on left of vertical line //n 10 hole mid hocche 5 tai 0theke 4 ta hobe ei case e
    Point Pyr[n-mid];  // y sorted points on right of vertical line
    int li = 0, ri = 0;  // indexes of left and right subarrays
    for (int i = 0; i < n; i++)
    {
      if (Py[i].x <= midPoint.x && li<mid)
         Pyl[li++] = Py[i];
      else
         Pyr[ri++] = Py[i];
    }


    d = closestUtil(Px, Pyl, mid, d, A, mini); //Py1 sorted hoye dhuktese
    d = closestUtil(Px + mid, Pyr, n-mid, d, A, mini); //Pyr sorted hoye dhuktese

    Point strip[n];
    int j = 0;
    for (int i = 0; i < n; i++)
        if (abs(Py[i].x - midPoint.x) < d) //Py to age thekei sorted hoyei ache
            strip[j] = Py[i], j++; //strip sorted hoye jacche karon Py age thekei sorted


    return stripClosest(strip, j, d, A, mini);
}


float closest(Point P[], int n, float d, Point A[], float mini)
{
    Point Px[n];
    Point Py[n];
    for (int i = 0; i < n; i++)
    {
        Px[i] = P[i];
        Py[i] = P[i];
    }

    sort(Px, Px+n, compareX);
    sort(Py, Py+n, compareY);


    return closestUtil(Px, Py, n, d, A, mini);
}


int main()
{
    freopen("text.txt", "r", stdin);
    int n;
    cin>>n;
    Point P[n], Main[n];
    for(int i=0; i<n; i++)
    {
        cin>>P[i].x>>P[i].y;
        Main[i].x=P[i].x;
        Main[i].y=P[i].y;
    }

    Point A[2];
    float d=2034655.1555, mini=0;
    d=closest(P, n, d, A, mini);
    //cout<<A[0].x<<" "<<A[0].y<<" "<<A[1].x<< " "<<A[1].y<<endl;


    Point P1[n-1], P2[n-1], B[2], C[2];
    bool first=false, second=false;
    float d1=131564636.5646465;
    float d2=465465432.46546;

    for (int i=0, j=0; i<n; i++)
    {
        if (!first and P[i].x==A[0].x and P[i].y==A[0].y)
            first=true;
        else
        {
            P1[j].x=P[i].x, P1[j].y=P[i].y; j++;
        }
    }
    for (int i=0, j=0; i<n; i++)
    {
        if (!second and P[i].x==A[1].x and P[i].y==A[1].y)
            first=true;
        else
        {
            P2[j].x=P[i].x, P2[j].y=P[i].y; j++;
        }
    }

    mini=d;
    d1=closest(P1, n-1, d1, B, mini);
    d2=closest(P2, n-1, d2, C, mini);
    if (d1<d2)
    {
        d=d1;
        A[0].x=B[0].x;
        A[0].y=B[0].y;
        A[1].x=B[1].x;
        A[1].y=B[1].y;
    }
    else

    {
        d=d2;
        A[0].x=C[0].x;
        A[0].y=C[0].y;
        A[1].x=C[1].x;
        A[1].y=C[1].y;
    }

    int index1, index2;
    for (int i=0; i<n; i++)
    {
        if (Main[i].x==A[0].x and Main[i].y==A[0].y)
        {
            index1=i;
            break;
        }
    }
    for (int i=0; i<n; i++)
    {
        if (Main[i].x==A[1].x and Main[i].y==A[1].y)
        {
            index2=i;
            break;
        }
    }

    cout<<index1<<" "<<index2<<endl;
    cout<<fixed<<setprecision(4)<<d<<endl;
    return 0;
}
