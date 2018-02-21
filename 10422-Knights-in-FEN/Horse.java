 

import java.util.*;
import java.io.*;

/**
 * Classe para resolução do Problema 10422-Uva Onlie Judge
 */
public class Horse {

    //Objeto para ler entrada do teclado
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    //Classe auxiliar para representar posicoes do tabuleiro
    class Pos {
        //Contrutor
        Pos(int x, int y) {
			//Se x ou y tiverem valores inválidos 
            //O objeto sera preenchido com valores nulos			
            if (x < 0 || x > 4) {
                this.x = -1;

            } else {
                this.x = x;
            }

            if (y < 0 || y > 4) {
                this.y = -1;

            } else {
                this.y = y;
            }

        }
        //Variaveis para armazenar indices da posicao no tabuleiro 
        public int x;
        public int y;
        //Metodo que verifica se as posicoes sao validas 
        public boolean isNulo() {
            return (x == -1 || y == -1);

        }
		//Metodo que clona a posicao 
        public Pos clone() {
            return new Pos(this.x, this.y);
        }
		//Metod que verifica se uma posicao é igual a outra
        public boolean equals(Pos a) {
            return (this.x == a.x && this.y == a.y);
        }
    }

    // Metodo retornar posicao dos cavalos
    //que podem andar até a posicao x,y
    public Pos[] getMove(int x, int y) {
        //Array com todas as posicões
        Pos pos[] = new Pos[8];
     	//calcular posicoes possiveis considerando 
        //que x e y refiram-se a posição central do tabuleiro	
        //as posicoes invalidas geradas serao ignoradas no contrutor
        pos[0] = new Pos(x - 2, y + 1);
        pos[1] = new Pos(x - 2, y - 1);
        pos[2] = new Pos(x + 2, y + 1);
        pos[3] = new Pos(x + 2, y - 1);
        pos[4] = new Pos(x - 1, y - 2);
        pos[5] = new Pos(x - 1, y + 2);
        pos[6] = new Pos(x + 1, y + 2);
        pos[7] = new Pos(x + 1, y - 2);

        return pos;

    }

	//Classe para representar o tabuleiro
    class Tabuleiro {
		//Matriz que armazenara organização das pecas
        public char[][] tabuleiro;
		//Construtor
        Tabuleiro() {
			//intancia a matriz
            tabuleiro = new char[5][5];
        }
		
		//Metodo para clonar o tabuleiro b 
        public void clone(Tabuleiro b) {
            for (int x = 0; x < 5; x++) {
                for (int y = 0; y < 5; y++) {
                    this.tabuleiro[x][y] = b.tabuleiro[x][y];
                }

            }

        }
		//Metodo para mostrar tabuleiro
        public void mostrar() {
            System.out.println("===============");
            for (int x = 0; x < 5; x++) {
                System.out.print("|");
                for (int y = 0; y < 5; y++) {
                    if (tabuleiro[x][y] == ' ') {
                        System.out.print("_ ");
                    } else {
                        System.out.print(tabuleiro[x][y] + " ");
                    }
                }
                System.out.print("|\n");
            }

            System.out.println("===============");

        }
		//Metodo para mostrar tabuleiro
        public void mostrar(String s) {
            System.out.println("========INICIO " + s + "=========");
            for (int x = 0; x < 5; x++) {
                System.out.print("|");
                for (int y = 0; y < 5; y++) {
                    if (tabuleiro[x][y] == ' ') {
                        System.out.print("_ ");
                    } else {
                        System.out.print(tabuleiro[x][y] + " ");
                    }
                }
                System.out.print("|\n");
            }

            System.out.println("========FIM " + s + "=========");

        }
		//Metodo que retorna a posicao vazia do tabuleiro
        public Pos getVazio() {
            int a = -1;
            int b = -1;
            for (int x = 0; x < tabuleiro.length; x++) {
                for (int y = 0; y < tabuleiro.length; y++) {
                    if (tabuleiro[x][y] == ' ') {
                        a = x;
                        b = y;
                        break;
                    }
                }
            }
            return new Pos(a, b);
        }
		//Metodo que verifica se tabuleiro esta organizado
        public boolean isOrganizado() {
            boolean resp = true;
            for (int x = 0; x < tabuleiro.length; x++) {
                for (int y = x + 1; y < tabuleiro.length; y++) {
                    if (tabuleiro[x][y] != '1') {
                        resp = false;
                        break;
                    }
                }
            }

            return (resp & (tabuleiro[0][0] == '1' && tabuleiro[1][1] == '1' && tabuleiro[2][2] == ' '));
        }
		//Metodo para mover cavalo no tabuleiro
        public void moverCavalo(Pos vazio, Pos cavalo) {
            tabuleiro[vazio.x][vazio.y] = tabuleiro[cavalo.x][cavalo.y];
            tabuleiro[cavalo.x][cavalo.y] = ' ';
        }
		//Metodo que le uma linha do arquivo e preenche uma linha do tabuleiro
        public void preencheTabuleiro(String line, int count) {
            for (int x = 0; x < 5; x++) {
                tabuleiro[count][x] = line.charAt(x);
            }

        }

    }

    //Metodo de teste
    public void debug(Tabuleiro board) {
        Pos vazio = board.getVazio();
        System.out.println("Posicao vazia (" + vazio.x + "," + vazio.y + ")");
        Pos[] move = this.getMove(vazio.x, vazio.y);
        int a = 0;
        int b = 0;
        System.out.println("Movimentos possiveis :");
        for (int x = 0; x < move.length; x++) {
            if (!move[x].isNulo()) {
                System.out.println(x + " (" + move[x].x + "," + move[x].y + ")");
                b++;
                a = x;
            }
        }
        System.out.println("Quantidade de movimentos validos : " + b);
        System.out.println("Organizado: " + board.isOrganizado());
        System.out.println("Board original: ");
        board.mostrar();
        System.out.println("Board após primeiro movimento válido: " + a);
        board.moverCavalo(vazio, move[a]);
        board.mostrar();

    }

    //Metodo principal
    public static void main(String[] args) throws Exception {
		int qt = Integer.parseInt(in.readLine());
		int c = 0;
        Horse h = new Horse();
        while(c  < qt){
			
			h.exec();
			c++;
			
		}
		
        

    }

    public void exec() throws Exception {
             
        int count = 0;
        Horse h = new Horse();
        Tabuleiro board = new Tabuleiro();
        //Ler cinco linhas contendo 5 caraceres em cada linha 	
        for (int c = 0; c < 5; c++) {
            //chama metodo que preenche uma linha do tabuleiro 				
            board.preencheTabuleiro(in.readLine(), count);
            count++;
        }
        h.org(board);
        h.getResp();
	}

	//Lista para armazenar os numeros de movimentos de cada possibilidade de resolução
	ArrayList<Integer> contadores = new ArrayList<>();
	
	//Metodo que busca na lista de movimentos a menor qunatidade
	public void getResp(){
			int menor  = 99999999;
			for(Integer i : contadores){
				if(i < menor && i!= 11){
					menor = i;
					//System.out.println(i);
				}
			}
			if(menor == 99999999){
				System.out.println("Unsolvable in less than 11 move(s).");
				
			}else{
				System.out.println("Solvable in "+menor+" move(s).");
			}
	
	}
    //Metodo para testar possibilidades de movimento usando BF
    public void dps(Tabuleiro tab, Pos ultima, int count) {
		int aux = count;
		//Se quantidade movimento maior que onze parar
        if (count < 11) {
			//Pega posicao vazia
            Pos vazio = tab.getVazio();
            //Pega cavalos que podem ir ate a posicao vazia
            Pos[] moves = getMove(vazio.x, vazio.y);
			//Array para aramazenar tabuleiro de cada movimento valido
            Tabuleiro[] tabs = new Tabuleiro[moves.length];
			//Para cada movimento valido possivel
            for (int x = 0; x < moves.length; x++) {
				//Se o movimento for valido e for diferente do ultimo movimento realizado
                if ((!moves[x].isNulo()) && (!moves[x].equals(ultima))) {
					//Cria novo tabuleiro
                    tabs[x] = new Tabuleiro();
                    //Copia o tabuleiro atual
                    tabs[x].clone(tab);
                    //Realiza o movimento 
                    tabs[x].moverCavalo(vazio, moves[x]);
                    //Incrementa contador 
					aux++;
					//Se o tabuleiro foi organizado
                    if (tabs[x].isOrganizado()) {
							//Adicione a quantidade de movimentos realizado a lista  
							contadores.add(aux);
                    } else {
						//Senao realize o proximo movimento valido a partir do movimento feito na jogada atual
						dps(tabs[x], vazio, (aux));
                    }
				 
                } 
                //A cada série de movimento possiveis retornar contador a valor inicial da chamada
				aux = count;
            }

        }
    }

    //Metodo auxiliar para organizar o tabulerio
    public void org(Tabuleiro board) {
        //Pega vazio
        Pos vazio = board.getVazio();
        //Pega cavalos possiveis
        Pos[] moves = getMove(vazio.x, vazio.y);
        //Verifica se o tabuleiro ja esta organizado
		if(board.isOrganizado()){
			System.out.println("Solvable in 0 move(s).");
		}else{
			//Cria vetor um tabuleiro para cada move valido
			Tabuleiro[] tabs = new Tabuleiro[moves.length];
			//Para cada movimento valido
			for (int x = 0; x < moves.length; x++) {
				if (!moves[x].isNulo()) {
					//Cria o tabuleiro
					tabs[x] = new Tabuleiro();
					//Copia tabuleiro orginal
					tabs[x].clone(board);
					//Move cavalo
					tabs[x].moverCavalo(vazio, moves[x]);
					//Verfica se esta organizado
					if (tabs[x].isOrganizado()) {
						System.out.println("Solvable in 1 move(s).");
					} else {
						//Senao estiver chame metodo recursivo para realizar proximo movimento
						this.dps(tabs[x], vazio, 1);
					}
					 //board.mostrar("Original");
					//tabs[x].mostrar("Alterado");
					//System.out.println("Fim do movimento ("+ moves[x].x+","+moves[x].y+")");

					} else {

					}

			}
		}
		
	 
    }

}
