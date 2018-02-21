import java.util.Scanner;

public class Questao10129 {

	public static String[] array;
	public static int tamanho;

	public static boolean verificaLetras(String a, String b) {
		boolean verifica = false;

		// verifica o tamanho de cada palavra
		if ((a.length() > 2 && a.length() < 1000) && (b.length() > 2 && b.length() < 1000)) {
			// verifica se as duas palavras podem ser ordenadas
			if (a.charAt(a.length() - 1) == b.charAt(0)) {
				verifica = true;
			}
		}

		return verifica;
	}

	public static void testeDeOrdenacao() {
		int aux = 0;
		// percorre o array
		for (int i = 0; i < tamanho; i++) {
			for (int j = i + 1; j < tamanho; j++) {
				// verifica se pode ser ordenado
				if (verificaLetras(array[i], array[j]))
					aux++;
			}
		}
		// verifica se pode ser ordenado (pelo menos uma vez)
		if (aux >= tamanho - 1)
			System.out.println("Ordering is possible");
		else
			System.out.println("The door cannot be opened.");
	}

	public static void main(String[] args) {
		// criando variavel de scanner do teclado
		Scanner stdin = new Scanner(System.in);
		// lendo a quantidade de conjuntos de palavras
		int qtConjuntos = Integer.parseInt(stdin.nextLine());
		int count = 0;

		while (count < qtConjuntos) {
			// lendo o tamanho do array (quantidade de palavras)
			tamanho = Integer.parseInt(stdin.nextLine());

			// verificao do numero de palavras que podem possuir em cada
			// conjunto
			if (tamanho >= 1 && tamanho <= 100000) {
				array = new String[tamanho];
				// preenchendo o array de strings
				for (int x = 0; x < tamanho; x++) {
					String a = "";
					// Ler a String
					a = stdin.nextLine();
					// guardando a string no array
					array[x] = a;
				}
			}
			count++;
			// chamando metodo de testes
			testeDeOrdenacao();
		}
		// fechando a leitura
		stdin.close();
	}
}