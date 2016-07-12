package bcms.matematica;

/**
 * 
 * @author brunomeloesilva
 *
 */
public class ImprimeCartela {

	/**
	 * @param cartelas
	 *            Recebe uma matriz.
	 * @return void, Apenas imprime a matriz recebida.
	 */
	public static void imprimeCombinacoes(int[][] cartelas) {
		for (int[] is : cartelas) {
			for (int i : is) {
				System.out.print("" + i + "-");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		imprimeCombinacoes(CombinacaoSimples.getElementosCombinacoesSimples(3, 2));
	}

}
