#include <stdio.h>

int main() {
	int num;
	scanf("%d", &num);
	int check = num % 8;
	int ans;
	if (check == 1) ans = 1;
	if (check == 2 || check == 0) ans = 2;
	if (check == 3 || check == 7) ans = 3;
	if (check == 4 || check == 6) ans = 4;
	if (check == 5) ans = 5;
	printf("%d", ans);
	return 0;
}