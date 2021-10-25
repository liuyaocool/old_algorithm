
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#include "AlgorithmUtil.h"

int* randomArray(int maxSize, int maxValue)
{
	static int a[10];

	printf( "1\n");

	return a;
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