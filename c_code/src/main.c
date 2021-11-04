
#include <stdio.h>
#include <stdlib.h>
#include "utils/AlgorithmUtil.h"
#include "fu/func.h"

int main()
{

	printf("hello world\n");

	int intsize = sizeof(int);
	printf("intsize = %d\n", intsize);

	struct array_int a = randomArray(600, 400);
	for (int i = 0; i < a.len; i++)
	{
		// printf("a[%d] = %d\n", i, a.arr[i]);
	}
	
	printf("a[1] = %d\n", a.arr[1]);
	printf("a size = %d\n", a.len);
	upsetArray(a.arr, a.len);
	printf("a[1] = %d\n", a.arr[1]);

	free(a.arr);

	int aa = randomInt(12, 199);
	printf("aa = %d\n", aa);
	// int b = add(12, 345);

	return 0;
}

