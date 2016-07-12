package bcms.matematica;

import java.util.Arrays;

/**
 * 
 * @author brunomeloesilva
 *
 */
public class CombinacaoSimples {
	/**
	 * @param n
	 *            É o numero a ser fazer o fatorial.
	 * @return O valor fatorial no numero informado. Por motivos de limitação,
	 *         só é permitido calcular até o fatorial de 12.
	 * @throws IllegalArgumentException
	 *             Caso um numero maior que 12 seja passado como parametro um
	 *             IllegalArgumentException é disparado.
	 */
	public static int getFatorial(int n) {
		int r = 1;
		if (n <= 12) {
			for (int i = 2; i <= n; i++)
				r *= i;
		} else {
			throw new IllegalArgumentException("Só calcular até o fatorial de 12.");
		}
		return r;
	}

	// Multiplica um intervalo, exemplo.: de 8 ate 10: 8*9*10 = 720.
	// Este método é usado como parte do metodo que calcula as combinações
	// simples.
	private static int getMultiplicaIntervalo(int inicio, int fim) {
		int r = 1;
		for (int i = inicio; i <= fim; i++)
			r *= i;
		return r;
	}

	/**
	 * @param n O número total de elementos do conjunto.
	 * @param p A quantidade de elementos de cada agrupamento.
	 * @return A quantidade de combinações simples.
	 * @see Explicações: Quando formamos agrupamentos com p elementos, (p<n) de
	 * forma que os p elementos sejam distintos entre sí pela espécie. Na
	 * Combinação Simples não ocorre a repetição de qualquer elemento em cada
	 * grupo de p elementos, a fórmula dada por: C(n,p) = n!/[p!*(n-p)!] Cálculo
	 * para o exemplo: C(4,2) = 4!/[2!2!] = 24/4 = 6. Exemplo: Seja C =
	 * {A,B,C,D}, n=4 e p=2. As combinações simples desses 4 elementos tomados 2
	 * a 2 são 6 grupos que não podem ter a repetição de qualquer elemento nem
	 * podem aparecer na ordem trocada. Todos os agrupamentos estão no conjunto:
	 * Cs={AB,AC,AD,BC,BD,CD}
	 */
	public static int getCombinacoesSimples(int n, int p) {
		if (p < n) {
			// Este é por motivo do corte que há entre o fatorial do divisor e
			// dividendo, para economizar "calculo". Exemplo: 
			//10! / 3! * (10-3)! = 10*9*8*7! / 6 * 7! -> Os 7! são cortados !
			n = getMultiplicaIntervalo(n - p + 1, n);
			p = getFatorial(p);
			return n / p;
		}
		return 0;
	}
	
	/**
	 * @param numeroElementosUniverso
	 *            O número total de elementos do conjunto.
	 * @param numeroElementosCombinacao
	 *            A quantidade de elementos de cada agrupamento.
	 * @return Uma matriz com todos os elementos das combinações possíveis, conforme a regra
	 *         de combinação simples.
	 */
	public static int[][] getElementosCombinacoesSimples(int numeroElementosUniverso, int numeroElementosCombinacao) {
		// Cria a tabela universo e insere os elementos
		int[] universo = new int[numeroElementosUniverso];
		for (int i = 0; i < universo.length; i++)
			universo[i] = i + 1;
		// Cria a matriz com capacidade para guardar todas as possibilidades do
		// jogo
		int linhas = getCombinacoesSimples(numeroElementosUniverso, numeroElementosCombinacao);
		int[][] cartelas = new int[linhas][numeroElementosCombinacao];
		// Monta a primeira combinacao inicial
		int linha = 0;
		for (int i = 0; i < numeroElementosCombinacao; i++) {
			cartelas[linha][i] = i + 1;
		}
		// Conclui a sequencia da montagem da primeira combinacao
		while (cartelas[linha][numeroElementosCombinacao - 1] != numeroElementosUniverso) {
			cartelas[++linha] = Arrays.copyOf(cartelas[linha - 1], numeroElementosCombinacao);
			cartelas[linha][numeroElementosCombinacao - 1] = ++cartelas[linha][numeroElementosCombinacao - 1];
		}
		// Nucleo principal pras combinacoes
		int c = numeroElementosCombinacao - 1;
		while (c > 0) {
			if (cartelas[linha][c] - 1 != cartelas[linha][c - 1]) {
				cartelas[++linha] = Arrays.copyOf(cartelas[linha - 1], numeroElementosCombinacao);
				cartelas[linha][c - 1] = ++cartelas[linha][c - 1];
				for (int i = c; i < numeroElementosCombinacao; i++)
					cartelas[linha][i] = cartelas[linha][i - 1] + 1;
				for (int i = cartelas[linha][numeroElementosCombinacao - 1] - 1; i < numeroElementosUniverso; i++) {
					cartelas[linha][numeroElementosCombinacao - 1] = universo[i];
					if (i + 1 != numeroElementosUniverso)
						cartelas[++linha] = Arrays.copyOf(cartelas[linha - 1], numeroElementosCombinacao);
				}
				c = numeroElementosCombinacao - 1;
				continue;
			}
			--c;
		}
		return cartelas;
	}
}
