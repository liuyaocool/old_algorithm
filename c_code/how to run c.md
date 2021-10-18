# MAC OS sublime text 运行 C

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

