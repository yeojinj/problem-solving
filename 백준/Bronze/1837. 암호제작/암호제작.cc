/*강사님 코드*/
#include <bits/stdc++.h>

using namespace std;

#define MAXS 102
#define MAXK 1000002

char P[MAXS];//두 소수의 곱 비밀키
int K;//K보다 작은 암호는 BAD, 큰 암호는 GOOD
bool Visit[MAXK];//소수:false, 소수 아님:true

//초등학교에서 배운 나눗셈을 구현
bool check(int n)
{
    int r = 0;//나머지

    for(int i=0; P[i]; i++) r = (r*10 + (P[i]-'0'))%n;

    return r==0;
}

int main()
{
    scanf("%s %d", P, &K);

    bool good = true;
    int minPrime = 0;

    for(int i=2; i<K; i++)
    {
        if(Visit[i]) continue;

        if(check(i))//i로 나뉘어 지는지 확인
        {
            good = false;
            minPrime = i;
            break;
        }

        //i*i는 int 범위를 넘어설 수 있음에 주의해야 한다.
        for(long long j=(long long)i*i; j<K; j+=i) Visit[j] = true;
    }

    if(good) printf("GOOD\n");
    else printf("BAD %d\n", minPrime);

    return 0;
}