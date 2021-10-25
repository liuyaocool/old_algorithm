
# 定义编译目录
buildFolder = target
# 搜索所有需要链接的文件
objs = $(wildcard $(target)/*.o)


all: bin/main

bin/main: $(objs)
	mkdir -p $(buildFolder)
	mkdir -p bin
	gcc -o $@ $(objs)

$(buildFolder)/%.o: %.c
	gcc -c $< -o $@
$(buildFolder)/utils/%.o: utils/%.c
	gcc -c $< -o $@

clean:
	-@rm *.o
	-@rm main