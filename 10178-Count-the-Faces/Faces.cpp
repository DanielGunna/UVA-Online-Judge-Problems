#include<stdio.h>
#include<stdlib.h>
#include<iostream>
#include <cmath>  
#define MAX_VERTICE 3000
#define Vertice int 
#define NULO -2

using namespace std;
char grafo[MAX_VERTICE][MAX_VERTICE];
int qtVertice,qtAresta,qtEdge,componentes = 0 ;
int faces = 1 ;
bool visitados[MAX_VERTICE];
//Metodo para inciar array de vertices visitados
void iniciaVisitados(){
	for(int i = 0; i < qtVertice; i++){
		visitados[i] = false;
	}	
}

bool isAresta(Vertice a, Vertice b){
	return (grafo[a][b] == 1);
}
//--------------------------------------------------------------------
// visitar: visita o vertice recebido no vetor de visitados
//--------------------------------------------------------------------
void visitar(Vertice v){
	 	//Marcar v como vistado
		visitados[v] = true;

		//Visitar adjacentes a v não visitados 
		for(int i =  1; i <= qtVertice; i++){
			if(isAresta(i,v) && visitados[i] == false){
				  visitar(i);
				
			}
				
		}
	

	 	
}
	
 	
/*
*  METODO DE BUSCA:
*/
//--------------------------------------------------------------------
// buscaProfundidade: algoritmo que caminha no grafo
// 		      utilizando a procura em profundidade
//--------------------------------------------------------------------

void buscaProfundidade(){
	//Incia array de visitados 
	iniciaVisitados();
	
	//Para cada vertice faca 
	for(int i = 1; i <= qtVertice; i++){
		//Se o vertice i ainda na foi visitado
		if(visitados[i] == false){

		  //visitar
		  visitar(i);
  		  componentes++;
	       		
		}
 
	}
	 
}




int getGrau(Vertice v){
	int count = 0 ;
	for(int x = 0 ; x < 52; x++){
		if(isAresta(v,x))
			count++;
 	}

	return count;
}

void iniciaGrafo(){

	for (int x = 0; x < 52; x++){
		for(int y = 0 ; y < 52 ; y++){
			grafo[x][y] = NULO ; 	

		}
	}
	


}


bool isConexo(){
	bool resp = true;	
	for(int x = 0 ; x < 52 ; x++){
		if(getGrau(x)%2 != 0){
			resp = false;
			break;
		}

	}

 
	return resp;

}




int contarArestas(){
	int count = 0;
	for(int x = 0 ;  x < 52; x++){
		for(int y = 0 ;  y < 52; y++){
			if(isAresta(x,y))
				count++;
		}
	}

	return count;

}


int main(){

	int count = 0 ;
	//Iniciar grafos   	 
	iniciaGrafo();
	//Enquanto houver grafos
	while (	cin >> qtVertice && cin >> qtAresta){
		
	
		for (int x = 0; x < qtAresta;x++) {
				//Vertices 
				char a,b = ' ';
				//Ler vertices 
				cin >> a;
				cin >> b;
				int posa,posb = 0 ;
				//Insere aresta	
				if(a > 90){
					posa  = (25 + (a%97) + 1 );
					
				}else{
					posa  =  a%65 ;
				}
				if(b > 90){
					posb = (25 + (b%97) + 1 );

				}else{
					posb = b%65;		
				}

				 
 				
				
					grafo[posa][posb] = grafo[posb][posa] = 1 ;
		}	
		 
		if(isConexo()){
			if(qtAresta > 0){
				faces = (qtVertice - contarArestas()) + 2;
			}else if(qtAresta == 0){
				faces = 1 ;
			}
		}else{
			faces = componentes +  1 - qtVertice + qtAresta;
		}  
			
		 componentes = 0;
		 cout << faces << endl;
		
	
	count++; 		
	}
			
	
	
	return 0 ;
}
