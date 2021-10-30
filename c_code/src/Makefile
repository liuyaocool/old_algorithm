
# 定义编译目录
buildFolder = target
# 搜索所有需要链接的文件
srcs = $(wildcard */*.c)
srcs += $(wildcard *.c)
srcFolders = $(wildcard */*.c)
srcFolders := $(patsubst %.c,%,$(srcFolders))

objs = $(patsubst %.c,%.o,$(srcs))


all: dir
	
dir:
	@echo srcFolders: $(srcFolders)
	@echo objs: $(objs)
	@mkdir -p ../$(buildFolder)
	@mkdir -p ../bin

main: $(objs)
	gcc -o ../bin/$@ $(objs)

%.o: %.c
	gcc -c $< -o ../$(buildFolder)/$@

clean:
	-@rm *.o
	-@rm main