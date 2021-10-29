
# 定义编译目录
buildFolder = target
# 搜索所有需要链接的文件
srcs = $(wildcard */*.c)
srcs += $(wildcard *.c)
objs = $(patsubst %.c,%.o,$(srcs))


all: main
	@echo $(objs)
	@mkdir -p ../$(buildFolder)
	@mkdir -p ../bin
	
main: $(objs)
	@gcc -o ../bin/$@ $(objs)

%.o: %.c
	gcc -c $< -o ../$(buildFolder)/$@

clean:
	-@rm *.o
	-@rm main