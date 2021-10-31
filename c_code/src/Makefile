
# Makefile 绝对路径
# mkf_path := $(abspath $(lastword $(MAKEFILE_LIST)))
# mkf_abpath := $(patsubst %/Makefile, %/, dir $(mkf_abpath))
mkf_abpath	:= $(patsubst %Makefile, %, $(abspath $(MAKEFILE_LIST)))
bin_path	:= ../bin
# 搜索所有需要链接的文件
srcs	:= $(wildcard */*.c) $(wildcard *.c)
objs	:= $(patsubst %.c, %.o, $(srcs))


all: dir main
	
dir:
#	@echo srcs: $(srcs)
#	@echo objs: $(objs)
	@mkdir -p $(mkf_abpath)../bin

main: $(objs)
	gcc -o $(bin_path)/$@ $(objs)

%.o: %.c
	gcc -c $< -o $@

.PHONY: clean run c_run
run:
	@$(bin_path)/main
c_run:
	@make
	@echo ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ run ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	@$(bin_path)/main
	@echo ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ run finished ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
clean:
	-rm -f $(objs)
	-rm -f $(bin_path)/main