#include <stdio.h>
#include <stdlib.h>
#include <iostream>
 

using namespace std;

//Array com valores das moedas * 100
int currency[] = { 5000, 2000, 1000, 500, 200, 100, 50, 20, 10, 5 };
//Variavel para armazenar quantidade de modos
int qtmodos;
 
    //Metodo recursivo para calcular qtmodos	
    void calcular(int valor, int pos) {
	//Se valor = 0 significa que uma maneira de ter o valor foi encontrada         
	if (valor == 0) {
            qtmodos++;
        } else {
            //Encontrar no vetor de moedas a moeda com valor igual ou menor
	 
            for (int i = pos; i < 10; i++) {
		 //Se moeda com valor igual ou menor			
                if (valor >= currency[i]) {
		    //Calcule novamente com a moeda <= de valor e passando a posição da moeda no vetor 
                    calcular(valor - currency[i], i);
                }
            }
        }
    }
    //Metodo principal	
    int main() {
	
	
	double num;
	//Enquanto houver entrada
        while (cin >> num ) {
            //Multiplicar o valor lido por cem 
            int valor = (int) num * 100;
            qtmodos = 0;
        //Se valor inserido igual a zero parar de calcular 	   
	 if(valor == 0)
                break;
            //Chama metdo calcular
	    calcular(valor, 0);
		
	    printf("%lf %d\n", num , qtmodos);
        }
 	return 0;
    }

