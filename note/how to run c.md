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

https://www.bilibili.com/video/BV16V411k7eF?from=search&seid=11581367427860258173&spm_id_from=333.337.0.0

源代码管理工具，使用gcc进行编译，自动编译工具

