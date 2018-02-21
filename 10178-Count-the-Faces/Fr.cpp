#include<stdio.h>
#include<stdlib.h>
#include<iostream>
#define MAX_VERTICE 3000
#define Vertice int 

using namespace std;

//Array para marcar vertices visitados
bool visitados[MAX_VERTICE];
	//Inicia variaveis
int count,
		qtGrafos,
		qtVertice, 
        qtAresta,
		componentes,
		friends,
		totalFriends = 0;
int 	grafo[MAX_VERTICE][MAX_VERTICE];
int tam[500];
		
		
//--------------------------------------------------------------------
// iniciaVisitados: Inicia o vetor de visitados
//--------------------------------------------------------------------

//Metodo que verifica se existe aresta entre a  e b 
bool isAresta(Vertice a , Vertice b){
	return grafo[a][b] == 1;
}

//Metodo para inciar array de vertices visitados
void iniciaVisitados(){
	for(int i = 0; i < qtVertice; i++){
		visitados[i] = false;
	}	
}


//--------------------------------------------------------------------
// visitar: visita o vertice recebido no vetor de visitados
//--------------------------------------------------------------------
void visitar(Vertice v){
	 	//Marcar v como vistado
		visitados[v] = true;
		//Soma amigos
		friends++;
		//Visitar adjacentes a v não visitados 
		for(int i =  1; i <= qtVertice; i++){
			if(isAresta(i,v) && visitados[i] == false){
				  visitar(i);
				
			}
				
		}
	

	 	
}
	
//Metodo que Testa valores da entrada
void teste(int n, int m){
	if((n<=0 || n>=30000)&&(m<=0 || m>=500000)){
		printf("ERRO TAMANHO INADEQUADO!!");
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
	          //Se totalFriends < friends quer dizer que um novo
		  // componente com mais vertices foi encontrado        
 	          if(totalFriends < friends){
		          //		
                    	  totalFriends = friends;
			  //Zera friends para contar vertices de um novo componente
			  friends = 0;
		  }
		  	
		
		}
 
	}
	 
}




int main(){

	int count = 0 ;
	//Ler quant grafos 
	cin >> qtGrafos ; 
   	 //Enquanto houver grafos
	while (count < qtGrafos){
		//Ler quantidade de Vertices e arestas 
		cin >> qtVertice;
		cin >> qtAresta;
		//Cria o grafo com tamanho qtVertices 	
		//Ler arestas a serem criadas  	
		for (int x = 0; x < qtAresta;x++) {
				//Vertices 
				int a,b = -1;
				//Ler vertices 
				cin >> a;
				cin >> b;
				//Insere aresta			
				grafo[a][b] = grafo[b][a] = 1 ;
		}	
		
		 buscaProfundidade();
		 cout  <<  totalFriends << endl; 
		
		totalFriends = friends = 0 ; 
		
	//Acumula qt grafos lidos 		
	 	
	count++; 		
	}
			
	
	
	return 0 ;
}
