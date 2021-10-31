bin_path	:= ../bin
# Makefile 绝对路径
# mkf_path := $(abspath $(lastword $(MAKEFILE_LIST)))
# mkf_abpath := $(patsubst %/Makefile, %/, dir $(mkf_abpath))
mkf_abpath	:= $(patsubst %Makefile, %, $(abspath $(MAKEFILE_LIST)))
# 搜索所有需要链接的文件
# srcs	:= $(wildcard */*.c) $(wildcard *.c)
srcs	:= $(wildcard */*.c)
objs	:= $(patsubst %.c, %.o, $(srcs))
bins	:= $(patsubst %.c, %.out, $(wildcard *.c))

# 保留所有中间文件
.SECONDARY:

all: dir $(bins)

dir:
	@mkdir -p $(mkf_abpath)../bin

%.out: %.o $(objs)
	gcc -o $(bin_path)/$* $^

%.o: %.c
	gcc -c $< -o $@

.PHONY: clean

clean:
	-rm -f $(objs)
	-rm -f *.o
	-rm -f $(bin_path)/*