#include<stdio.h>
#include<stdlib.h>
#include<iostream>
#include<math.h>
#include<list>

using namespace std;

 /**
A solução apresentada sempre retorna resultados corretos, porém a medida que N cresce o tempo de execução também cresce.Como o UVA utiliza inteiro muito grandes a solução é rejeeitada por tempo excedido (3 segundos);
*/
//Metodo que calcula o Fibonacci do inteiro n
unsigned long long fib(unsigned int n ){

	
	//F0  ou F1
	if(n==0)
		return 0;
	if(n==1)	
		return 1;
	//Array para auxiliar calculo do Fn
	unsigned long long fib[3];
	//Fibonacci de zero e de um 			
	fib[0] = 0;
	fib[1] = 1;
	//Variaveis para aramzenar indice de n - 1 e n - 2
	int num, ndois = 0;
	int resp = 0;	
	
	//Loop começando de 2 até para  n calcular Fn
	for (int x = 2 ; x <= n ; x++){
		//Calcula a posicao em que Fx sera aramzenado 
		int pos = x%4 ;
		
		//Descobrir qual a posicao de n-1 e n-2 em relacao a variavel pos
		if(pos == 0){
			num = 3;
			ndois = 2;
		}else if(pos == 1){
			num = 0;
			ndois = 3;			
		}else{
			num = pos - 1 ;
			ndois = pos - 2; 	
		}
		 	
		//Fx = Fx - 1 + Fx - 2;
		fib[pos] = fib[num] + fib[ndois];
		//Se terminado retorne a posicao de Fn no vetor
		if(x == n)
			resp = pos;
			
	}
	
	//retorne Fn
	return fib[resp];
}



//Metodo principal
int main ()
{
	unsigned int n = 0 ;
	double m = 0 ;	
	long long  Mn = 0;

	
	//Enquanto houver entrada
	while(cin  >> n && cin >> m){	
		// Calcula 2^m
		int pot = pow (2.0, m);
		//Mn = Fn mod  2^m
		Mn = fib(n)% pot ;
		//Mostra resultado	
		cout   << Mn << endl;
		
	}



	return 0;
}

