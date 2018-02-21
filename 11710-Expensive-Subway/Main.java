import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;



class Main{
   //Lista para armazenar as arestas 
   public LinkedList<Aresta> arestas;
   //Objeto para ler do teclado
   public BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
   //Vetor para aramzenar os pais do vertices 
   public int pai[];
   //Hashmap para mapear nomes da cidades no ids dos vertices 
   public HashMap<String, Integer> vertices;
   //Quantidade de vertices e arestas
   public int qtVertice;
   public int qtAresta;

   //Construtor
  Main(){
       //Cria uma lista linkada para armazenar as arestas 
      arestas = new LinkedList<Aresta>();
       //Cria um hashmap para mapear os nomes da cidades
      vertices = new HashMap<String, Integer>();
   }   

   //Classe para representar aresta 
   static class Aresta implements Comparable<Aresta>{
      int v1;
      int v2;
      int peso;
      //Construtor
      public Aresta(int v1 , int v2 , int peso){
         this.v1 = v1;
         this.v2 = v2;
         this.peso = peso ;
      }
      //Metodo para comparacao na ordenacao da lista de arestas 
      @Override
      public int compareTo(Aresta a) {
         return peso - a.peso;
      }
   	
   	
   }
   
   
   //Metodo para ler vertices 
   void lerVertices()throws Exception{
      //Ler vertices
      vertices = new HashMap<String, Integer>();
      for(int x = 0; x < qtVertice; x++){
           //Adiciona cidade no hashmap com seu id
         vertices.put(in.readLine(), x);
      }
   }
   //Metodo para ler arestas 
   void lerArestas()throws Exception{
      //Ler arestas 
      arestas = new LinkedList<Aresta>();
   
      for(int x = 0; x < qtAresta; x++){
            //Separa campos na linha pelo caracter " "
         String line[] = in.readLine().split(" ");
            //Pega o peso 
         int peso = Integer.parseInt(line[2]);
            //Adciona aresta na lista
         arestas.add(new Aresta(vertices.get(line[0]), vertices.get(line[1]), peso));
      }
         //Ordenar arestas 
      Collections.sort(arestas);
   }


   //Metodo que encontra o pai de um vertice
   int encontrarPai(int vertice){
      //Se o pai do vertice for ele proprio 
      //retorne o propio vertice
      if(pai[vertice] == vertice){
         return vertice ;
      }
      else{
         //Senao retorne o pai do pai (avo)
         return pai[vertice] = encontrarPai(pai[vertice]);
      }
   }
   //Metodo para unir pais vertices em unico pai	
   public void unirPai(int v1,int v2){
   //Encontrar pais dos vertices
      int pai1= encontrarPai(v1);
      int pai2= encontrarPai(v2);
      
      //Se v1 maior que v2 
      if(v1 > v2){
         //Pai do pai de v2
         //passa ser o pai do v1  
         pai[pai2] = pai1;	
      }
      else{
         //Pai do pai de v1
         //passa ser o pai do v2
         pai[pai1] = pai2;
      }
   }
   
   //Metodo para inicar vetor de pais
   void iniciaPai(){
      pai = new int[qtVertice];
   
         //para cada vertice
      for(int x = 0; x < qtVertice; x++){
         //Inicialmente definir si mesmo como pai
      
         pai[x] = x;
      }
   }
   
   void calculaCusto()throws Exception{
          //Contador de arestas da arvore
      int cont = qtVertice - 1;
         //variavel para aramzenar o custo
      int custo = 0;
         //Para cada aresta da lista ordenada 
      for(Aresta aresta : arestas){
           	//Se dois vertices tiverem pais diferentes
            //significa que não fecha circuito
         if( encontrarPai(aresta.v1)!= encontrarPai(aresta.v2)){
               //Una os pais dos vertices para não formar circuito entre
               //eles caso uma futura  aresta  de um deles 
               // nao seja adicionada            
            unirPai(aresta.v1, aresta.v2);
               //Incremente o custo da aresta ao custo total
            custo+=aresta.peso;
               //decremente  o contador 
            cont--;
         }
      }
      in.readLine();
      //Se n-1 aresta foram adicionadas 
      if(cont == 0){
            //Mostre o custo
         System.out.println(custo);
      }
      else{
            //Senao mostre que é impossivel
         System.out.println("Impossible");
      }
   
   
   }

   void exec()throws Exception{
      //Ler linha inicial
      String [] linha = in.readLine().split(" ");
      //Pega numero de vertices e arestas 
      qtVertice = Integer.parseInt(linha[0]);
      qtAresta = Integer.parseInt(linha[1]);
   	//Enquanto vertices e aresta diferente de 0
      while(qtVertice != 0 || qtAresta != 0){ 
         iniciaPai();     
         lerVertices();
         lerArestas();
         calculaCusto();
      
         linha = in.readLine().split(" ");
         qtVertice = Integer.parseInt(linha[0]);
         qtAresta = Integer.parseInt(linha[1]);
      }
   
   
   }

   public static void main(String[] args) throws  Exception {
     Main sub =  new Main();
      sub.exec();
          
   }
}