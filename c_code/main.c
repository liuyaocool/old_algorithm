
#include<stdio.h>
#include <stdlib.h>
#include <time.h>
// #include "utils/AlgorithmUtil.h"
#include "func.h"

int main()
{
	printf("hello world\n");

	printf("hello world\n");

	printf("hello world\n");


	printf("hello world\n");

	// int* a = randomArray(3, 3);
	int b = add(12, 345);
	return 0;
}

/* 要生成和返回随机数的函数 */
int * getRandom( )
{
  static int  r[10];
  int i;
 
  /* 设置种子 */
  srand( (unsigned)time( NULL ) );
  for ( i = 0; i < 10; ++i)
  {
     r[i] = rand();
     printf( "r[%d] = %d\n", i, r[i]);
 
  }
 
  return r;
}