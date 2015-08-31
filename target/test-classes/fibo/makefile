all: fibo

fibo: fibo.o
	g++ fibo.o -o fibo

fibo.o: fibo.cpp
	g++ -g -c fibo.cpp

clean:
	rm -rf *.o fibo
