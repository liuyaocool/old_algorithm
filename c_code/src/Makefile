
# 定义编译目录
buildFolder = target
# 搜索所有需要链接的文件
objs = $(patsubst %.c,%.o,$(wildcard *.c))


all: main

main: $(objs)
	@mkdir -p ../$(buildFolder)
	@mkdir -p ../bin
	@echo $(objs)
	@gcc -o ../bin/$@ $(objs)

%.o: %.c
	gcc -c $< -o ../$(buildFolder)/$@ -L utils

clean:
	-@rm *.o
	-@rm main