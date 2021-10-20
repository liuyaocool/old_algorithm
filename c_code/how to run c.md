# sublime text 运行 C

## MAC OS

1. brew install gcc

2. gcc -v

3. 设置sublime text：Tools → Build System → New Build System... → 粘贴以下并 command+s

   ```
   {
       "cmd": ["mkdir -p target && gcc -o ./target/${file_base_name} $file_name && ./target/${file_base_name}"],
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

