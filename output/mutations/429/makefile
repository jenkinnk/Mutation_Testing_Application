all: lucas

lucas: lucas.o
	g++ lucas.o -o lucas

lucas.o: lucas.cpp lucas.h
	g++ -g -c lucas.cpp

clean:
	rm -rf *.o lucas
