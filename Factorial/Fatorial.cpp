#include <stdio.h>
#include <stdlib.h>
#include <iostream>


using namespace std;


//Metodo que calcula o fatorial do inteiro n
void  fat(int n ){
	//Valor maximo do fatorial
	unsigned long max = 6227020800;	 
	unsigned long min = 10000;
	unsigned long fatorial = 0;
	
	//Se n igual  a zero ou um retornar 1 fat(0) = fat(1) = 1
	if(n ==  0 || n == 1){
		fatorial = 1;
		cout  << "Underflow!\n";
		return;
	}

	//Variavel  auxiliar para acumular o produto 
	unsigned long ant = 1;

	//Calcular produto dos valores até n-1
	for(unsigned int i = 2 ; i <=n ; i++){
		ant = ant * i;

		if(ant > max){
			
			cout  <<"Overflow!\n";
			return;
		}
		
		if(ant == 0){
			
			cout  <<"Overflow!\n";
			return;
		}
			
		
	}

	
	if(ant < min){		
		cout  << "Underflow!\n";
		return;
	}else{
		//Calcula fatorial
		
		cout << ant << "\n"; 			
	}
					
	


}




//Metodo principal
int main (){
	long num = 0;
	int x = 0 ;
	//Enquanto houver numeros a serem lidos do teclado	
	while(scanf("%ld",&num) == 1){

		if(num < 0){
		 fat((num * -1));
		
		}else{
		 fat(num);
		}
		
	
		x++;

	}
	

	return 0;
}
