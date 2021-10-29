# sublime text 运行 C

## MAC OS

1. brew install gcc

2. gcc -v

3. 设置sublime text：Tools → Build System → New Build System... → 粘贴以下并 command+s

   ```
   {
       "cmd": ["mkdir -p target && gcc ./*.c -o ./target/${file_base_name} && ./target/${file_base_name}"],
       "shell": true,
       "working_dir": "$file_path",
       "selector": "source.c",
       "encoding": "utf-8",
       // "variants": [{
       		// "name" : "Run",
       		// "cmd" : "open -a terminal.app ./${file_base_name}"
       // }]
   }
   ```

4. Tools → Build System 选择执行此配置

5. command+b 运行

## Windows 10

1. 下载MainGW，找到里边的gcc

2. 编写批处理脚本 C_RUN.bat

   ```
   @echo off
   mkdir target 2> nul
   cd %~dp1
   D:\C\MinGW\bin\gcc -o .\target\%~n1 %~n1.c
   .\target\%~n1
   ```

3. 设置sublime text：Tools → Build System → New Build System... → 粘贴以下并 ctrl+s

   ```json
   {
   	"shell_cmd": "C:/APP/SCRIPT/C_RUN.bat \"$file\"",
       "file_regex": "^(...*?):([0-9]*):?([0-9]*)",
       "selector": "source.java",
       "encoding": "GBK"
   }
   ```

4. Tools → Build System 选择执行此配置

5. ctrl+b 运行



# gcc

- -c ：只编译 不链接为可执行文件
- -o <输出文件名> ： 指定编译结束后指定的文件名
- -g ：添加调试信息，需要配合调试工具
- -O ：优化编译
- -O2 ：加大幅度的优化编译

## 编译过程

1. 预处理

   ```
   gcc -E main.c -o main.i
   ```

2. 编译

   ```
   gcc -S main.i -o main.s
   gcc -S main.c
   ```

3. 汇编

   ```
   gcc -c main.s -o main.o
   gcc -c main.c
   ```

4. 链接

   ```
   gcc main.o -o main
   ```

## 编译 

- 直接编译 
  - gcc main.c -o main
- 多文件编译
  - gcc func.c main.c -o main
  - gcc ./* -o main

# make Makefile

https://blog.csdn.net/LEON1741/article/details/78174953

http://www.gnu.org/software/make/manual/

源代码管理工具，使用gcc进行编译，自动编译工具

**注：每个平台的Makefile格式不一样**

## Makefile 规则格式

文件名：Makefile

```
目标: 依赖文件集合。。。
	命令1
	命令2
	。。。
目标2: 依赖文件集合
	命令1
	命令2
	。。。
```

注：这里写命令的地方用tab键缩进，终极目标放在第一个

**Makefile文件编写完成后，在Makefile同级目录执行make命令，就会按照Makefile文件进行执行**

例1：

1. 目录下有main.c func.h func.c，main.c 引入 func.h

2. 编写Makefile

   ```
   main: main.o func.o
   	gcc -o main main.o func.o
   main.o: main.c
   	gcc -c main.c
   func.o: func.c
   	gcc -c func.c
   	
   clean:
   	rm *.o
   	rm main
   ```

3. 执行make命令，根据当前目录的Makefile文件编译执行

4. 执行 ./main 即可执行程序

5. 执行 make clean 命令可清除编译 链接后的文件

## Makefile 变量

Makefile的变量都是字符串

如例1第一个目标 可改写成

```
objs = main.o func.o 
main: $(objs)
	gcc -o main $(objs)
```

## Makefile 赋值

| 操作符 | 功能                     |
| ------ | ------------------------ |
| =      | 类似于引用，a = $(xxx)。 |
| :=     | 直接赋值                 |
| ?=     | 如果有值 则不赋值        |
| +=     | 追加                     |

## Makefile 模式规则

% ，类似于通配符

如 例1 中第二个规则可改写成

```
%.o: %.c
	gcc -c main.c
```

通常配合自动化变量使用

## Makefile 自动化变量

| 自动化变量 | 描述                                                         | 简介             |
| :--------: | ------------------------------------------------------------ | ---------------- |
|   **$@**   | 规则中的目标集合,在模式规则中,如果有多个目标的话,“$@”表示匹配模式中定义的目标集合。 | 目标             |
|     $%     | 当目标是函数库的时候表示规则中的目标成员名,如果目标不是函数库文件,那么其值为空。 |                  |
|   **$<**   | 依赖文件集合中的第一个文件,如果依赖文件是以模式(即“%”)定义的,那么“$<”就是符合模式的一系列的文件集合。 | 第一个依赖       |
|     $?     | 所有比目标新的依赖目标集合,以空格分开                        | 第一个变化的依赖 |
|   **$^**   | 所有依赖文件的集合,使用空格分开,如果在依赖文件中有多个重复的文件,“$^”会去除重复的依赖文件,值保留一份 | 全部依赖         |
|     $+     | 和“$^”类似,但是当依赖文件存在重复的话不会去除重复的依赖文件。 |                  |
|     $*     | 这个变量表示目标模式中"%"及其之前的部分,如果目标是test/a.test.c,目标模式为a.%.c,那么“$*”就是 test/a.test |                  |

则 例1 可改写成：

```
main: main.o func.o
	gcc -o main main.o func.o
%.o: %.c
	gcc -c $<
	
clean:
	rm *.o
	rm main
```

## Makefile 伪目标

如 例1 中的 clean目标， 如果有一个名为clean的文件，执行make clean 就 不会执行clean下的命令 rm 等

声明为伪目标 可避免这种情况

则 例1 可改写为

```
.PHONY: clean
main: main.o func.o
	gcc -o main main.o func.o
main.o: main.c
	gcc -c main.c
func.o: func.c
	gcc -c func.c
	
clean:
	rm *.o
	rm main
```

## Makefile @

命令前加@ 控制台就不打印执行的命令

如 例1 改写为

```
main: main.o func.o
	@gcc -o main main.o func.o
main.o: main.c
	@gcc -c main.c
func.o: func.c
	@gcc -c func.c
	
clean:
	@rm *.o
	@rm main
```

## Makefile -

命令前加 “-”，则命令报错仍继续执行后续命令

如 例1 改写，执行make clean

```
main: main.o func.o
	gcc -o main main.o func.o
main.o: main.c
	gcc -c main.c
func.o: func.c
	gcc -c func.c
	
clean:
	-rm *.o
	-@rm *.o
	rm main
```

## Makefile 函数

1. **wildcard** 找到所有文件
2. **patsubst** 替换

则 例1 改写为

```
srcs = $(wildcard *.c)
objs = $(patsubst %.c,%.o,$(srcs))

main: $(objs)
	gcc -o main $(objs)
	
%.o: %.c
	gcc -c $< -o $@
	
clean:
	rm -f *.o
	rm -f main
```

## Makefile 伪目标 all

一般定义 all  是为了生成多个目标

则 例1 改写为

```
all: main main1

main: main.o func.o
	gcc -o $@ main.o func.o
main1: main.o func.o
	gcc -o $@ main.o func.o
main.o: main.c
	gcc -c main.c
func.o: func.c
	gcc -c func.c
	
clean:
	rm *.o
	rm main main1
```



# CMake

自动根据平台生成make 的 Makefile文件

https://www.bilibili.com/video/BV16V411k7eF?from=search&seid=11581367427860258173&spm_id_from=333.337.0.0

语法有点复杂，先弃用

## 语法

文件名：CMakeLists.txt

```
```













# 相关学习

B站搜 正点原子

