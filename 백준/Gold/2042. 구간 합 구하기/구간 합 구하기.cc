/*강사님 코드*/
#include <bits/stdc++.h>

using namespace std;

#define MAXK 21

int N, M, K, B, T;
long long IDT[1<<MAXK];

void initIDT()
{
    for(int i=B-1; i>0; i--)
    {
        IDT[i] = (IDT[i<<1] + IDT[(i<<1)|1]);
    }
}

void update(int p, long long v)
{
    p += (B-1);
    IDT[p] = v;
    while(p >>= 1)
    {
        IDT[p] = (IDT[p<<1] + IDT[(p<<1)|1]);
    }
}

long long lgSum(int l, int r)
{
    l += (B-1); r += (B-1);
    long long sum = 0;
    while(l<=r)
    {
        if((l&1)==1) sum += IDT[l];
        if((r&1)==0) sum += IDT[r];

        l = (l+1)>>1;
        r = (r-1)>>1;
    }
    return sum;
}

int main()
{
    scanf("%d %d %d", &N, &M, &K);

    for(B=1; B<N; B<<=1);

    for(int i=B; i<N+B; i++)
    {
        scanf("%lld",&IDT[i]);
    }

    initIDT();

    T = M+K;

    int a, b;
    long long c;
    while(T--)
    {
        scanf("%d %d %lld", &a, &b, &c);

        if(a==1) update(b, c);
        else printf("%lld\n", lgSum(b, c));
    }

    return 0;
}