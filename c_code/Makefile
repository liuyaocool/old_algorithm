
all: 
	make -C ./src/

clean:
	cd src && make clean

main:
	@make -C ./src/
	@echo ------------------------ start ------------------------
	@./bin/main

%:
	@make -C ./src/
	@echo ------------------------ start ------------------------
	@./bin/main_$*