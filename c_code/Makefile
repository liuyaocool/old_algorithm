buildFolder = target
objs = $(buildFolder)/main.o $(buildFolder)/func.o $(buildFolder)/utils/AlgorithmUtil.o

bin/main: $(objs)
	mkdir -p $(buildFolder)
	mkdir -p bin
	gcc -o $@ $(objs)
$(buildFolder)/%.o: %.c
	gcc -c $< -o $@
$(buildFolder)/utils/%.o: utils/%.c
	gcc -c $< -o $@

clean:
	rm *.o
	rm main