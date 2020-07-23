package br.uema.pecs.ed.lista03;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class Josefus2<Item> {
	private Item[] vetor;
	private int N;
	private int count;
	
	
	@SuppressWarnings("unchecked")
	public Josefus2() {
		vetor = (Item[]) new Object[10];
		N = 0;
		count = 0;
	}
	
	public boolean eVazia() {
		return N == 0;
	}
	
	public int size() {
		return N;
	}
	
	public int sizeT() {
		return vetor.length;
	}
	
	public boolean append(Item item) {
		if (this.N < this.vetor.length) {
			this.vetor[this.N] = item;
			this.N++;
			return true;
		} else {
			return false;
		}
	}
	
	public boolean appendIni(Item item) {
		if (N <= vetor.length) {
		for(int i = this.N - 1; i >= 0; i--) {
    		this.vetor[i + 1] = this.vetor[i];
    	}
		this.vetor[0] = item;
		this.N++;
		return true;
		} else {
			return false;
		}
	}
	
	
	public Item removeIni() {
		if (this.eVazia()) {
			return null;
		} else {
			Item ini = vetor[0];
			for (int i =0; i < this.N -1; i++) {
				this.vetor[i] = this.vetor[i + 1];
			}
			vetor[this.N - 1] = null;
			this.N--;
			return ini;
		}
	}
	
	public Item removeUlt() { 
		Item ult = vetor[this.N - 1];
		vetor[this.N - 1] = null;
		this.N--;
		return ult;
		
	}//----------------------------------------------------------------------------------------REMOVE_QUALQUER_POSICAO
	
	
	
	
	private Item direita(int num) {
		if (num > N) {
			while (num > N) {
				num = num - N;
			}
			for (int i = 0; i < num -1 ; i++) {
				Item a = this.removeIni();
				this.append(a);
			}
			return this.removeIni();
		} else {
			for (int i = 0; i < num -1 ; i++) {
				this.append(this.removeIni());
			}
			return this.removeIni();			
		}
	}
	
	private Item esquerda(int num) {
		if (num > N) {
			while(num > N) {
				num = num - N;
			}
			for (int i = this.N - 1; i > (this.N -1) - (num - 1); i--) {
				this.appendIni(this.removeUlt());
			}
			return this.removeUlt();
		} else {
			for (int i = this.N - 1; i > (this.N -1) - (num - 1); i--) {
				this.appendIni(this.removeUlt());
			}
			return this.removeUlt();
		}
	}	

	
	public Item jose(int num) {
		if (num > 0) {
			count++;
			return  direita(num);
		} else { 
			num = num * -1;
			if (count == 0) {
				this.append(this.removeIni());
				count++;
				return esquerda(num);
			} else {
				count++;
				return esquerda(num);				
			}
		}
	}

	
	public int numAleatorio(int min, int max) {
		Random rand = new Random();
		return rand.nextInt((max - min) + 1) + min;
	
	}	
	
	public int primeiroAleatorio(int num1, int num2) {
		int a = 0;
		while (a == 0) {
			a = numAleatorio(num1, num2);
		}
		for (int i = 0; i < a - 1; i++) {
			this.append(this.removeIni());	
		}
		return a;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (this.N > 1) {
			s.append("[");
			for (int i = 0; i < this.N - 1; i++) {
				s.append(this.vetor[i]);
				s.append(", ");
			}
			if (this.N > 0) {
				s.append(this.vetor[this.N - 1]);
			}

			s.append("]");
			return s.toString();
		} else {
			s.append(this.vetor[0]);
			return s.toString();
		}
		
	}//-------------------------------------------------------------------------------------------------- TO_STRING
	
	public String todoVetor() {
		return Arrays.toString(vetor);
	}

	public static void main(String[] args) {
		
		/* O PROBLEMA DE JOSEFUS DESCRITO NO ENUNCIADO DA QUESTÃO, É UMA ADAPTÇÃO DO PROBLEMA DE JOSEFUS,  
		 * NO QUAL O HISTORIADOR FLAVIUS JOSEPHUS, ONDE ELE E MAIS 40 SOLDADOS FORAM PRESOS EM UMA CAVERNA 
		 * POR SOLDADOS ROMANOS. E ESCOLHERAM O SACRIFÍCIO DO QUE SER CAPIRUTRADO.
		 * A DESCRIÇÃO DO PROBLEMA NA ENUNCIADO INDICA, QUE INICIALMENTE É REALIZADO UM SORTEIO PARA INDICAR
		 * O INÍCIO DA CONTAGEM, EM SEGUIDAS SÃO REALIZADAS OS DEMAIS SORTEIOS QUE RESULTARÃO NAS SAIDAS DOS
		 * SOLDADOS. COMO SE CONHECE QUAL SOLDADO SERÁ INICIADO A PRIMEIRA CONTAGEM, RESTA SORTEAR O NUMERO 
		 * DA CONTAGEM, OS NÚMEROS SORTEADOS VÃO DE -9 A 9, EXCETO O 0. CASO O NUMERO SEJA NEGATIVO A CONTA
		 * GEM É REALIZADO NO SENTIDO ANTI-HORÁRIO E SE POSITIVO NO SENTIDO HORÁRIO.     
		 * UMA DAS MANEIRAS DE RESOLVER ESSA QUESTÃO É SE UTILIZANDO FILA, SEJA ESSA LINEAR OU ENCADEADA, NO
		 * CASO FOI UTILIZADA UMA FILA LINEAR COM ALGUMAS MODIFICAÇÕES. QUANDO A CONTAGEM É POSITIVIDA OU SEJA
		 * PARA A DIREITA É RETIRADO ELEMENTOS DO INICIO DA FILA E OS COLOCA NO FINAL, ATÉ ATINGIR O NÚMERO DA
		 * CONTAGEM SORTEADA, CASO A CONTAGEM SEJA PRA ESQUERDA SÃO RETIRADOS VALORES DO FINAL DA FILA E OS ADI-
		 * CIONANDO NO INÍCIO DA FILA, ASSIM É POSSÍVEL A CRIAÇÃO DE UMA "FILA CIRCULAR LINEAR".
		 * 
		 * 
		 * OBS: UMA OBSERVAÇÃO IMPORTANTE É QUE NO CASO DA RESOLUÇÃO APRESENTADA A CONTAGEM SE INICIA EM 1, CON-
		 * TANDO A PARTIR DO VALOR INICIAL, E NÃO A PARTIR DE 0, COMO É A CONTAGEM DO JAVA.
		 * 
		 * ABAIXO TERÁ UM EXEMPLO DE FUNCIONAMENTO DO ALGORITMO
		 * 
		 * NO ENUNCIADO DA QUESTÃO É DITO QUE APÓS O SOLDADO SER RETIRADO, É FEITO OUTRO SORTEIO PRA ESCLUSÃO
		 * E A CONTAGEM SE INICIA NO SOLDADO SEGUINTE AO QUE FOI ELIMINADO. PORÉM QUANDO SE RETIRA UM SOLDADO SE TEM
		 * 2 POSSIBILIDADES DE INÍCIO. SE A CONTAGEM FOR PARA A DIREITA O PROX SOLDADO APÓS O ELIMINADO SERÁ O INICIO
		 * DA CONTAGEM, MAS SE A CONTAGEM FOR A ESQUERDA, O SOLDADO ANTERIOR AO REIRADO É QUE SE INICIA A CONTAGEM.
		 * 
		 * 1º ETAPA:
		 * INICIALMENTE É SORTEADO UM SOLDADO, PARA SER INICIADO A CONTAGEM. DIGAMOS QUE É SORTEADO O SOLDADO 5.
		 * ENTÃO A CONTAGEM SE INICIARA NESSE SOLDADO.
		 * COMO NENHUM SOLDADO FOI EXCLUIDO AINDA. NA PRIMEIRA ESCLUSÃO A CONTAGEM TANTO PRA DIREITA QUANTO PRA ESQUERDA,
		 * SE INICIAM NO MESMO SOLDADO. APOS EXLUIR A CONTAGEM A DIREITA COMEÇARÁ NO SOLDADO APOS EXCLUIDO, E A CONTAGEM A
		 * ESQUERDA COMEÇARÁ NO SOLDADO ANTERIORMENTE EXCLUIDO.
		 * POSTERIOEMENTE É INICIADO A PRIMEIRA CONTAGEM PRA ELIMINAÇÃO. DIGAMOS QUE É SORTEADO 8 (PERCORRE).
		 * A DIREITA, COMEÇANDO A CONTAGEM PELO 5, DEPOIS 6 ATÉ PARAR NO 2. ESSE SOLDADO É EXCLUIDO 
		 *
		 *                1
		 *             10    2 <- EXCLUIDO
		 *           9         3
		 *           8         4 
		 *             7     5 <-INI
		 *                6
		 *                 
		 * 2º ETAPA:
		 * É SORTEADO UM NÚMERO PARA A SEGUNDA ELIMINAÇÃO. SE O NÚMERO SORTEADO FOR POSITIVO (CONTAGEM A DIREITA)
		 * A CONTAGEM SE INICIA EM 3, SE FOR NEGATIVO (CONTAGEM A ESQUERDA) SE INICIA EM 1. SUPÕE-SE QUE É SORTEADO
		 * -3, ENTÃO A CONTAGEM SE INICIA EM 1, PARANDO EM 9. ENTÃO ESSE SOLDADO SERÁ EXCLUIDO. ENTÃO DE ACORDO COM
		 * O PRÓXIMO NUMERO SORTEADO, SE TERÁ UM NOVO INICIO TANTO PARA DIREITA QUANTO ESQUERDA. 
		 *                    
		 *                             1 <- ANTIGO INICIO ESQUERDA
		 * NOVO INICIO DIREITA ->   10    2 <- EXCLUIDO
		 *              EXCLUIDO-> 9         3 -> ANTIGO INICIO DIREITA
		 * NOVO INICIO ESQUERDA -> 8         4 
		 *                           7     5 
		 *                              6
		 * TRANSFORMANDO EM FILA SERIA O SEGUINTE:
		 * INICIO CONTAGEM DIREITA -> [10, 1, 3, 4, 5, 6, 7, 8] <- FIM CONTAGEM DIREITA
		 * FIM CONTAGEM ESQUERDA -> [10, 1, 3, 4, 5, 6, 7, 8] <- INICIO CONTAGEM ESQUERDA
		 *                    
		 *                    
		 */
			
		Scanner sc = new Scanner(System.in);
			Josefus2<Integer> fila = new Josefus2<Integer>();
			for (int i = 1; i <= 10; i++) {
				fila.append(i);
			}
			
			System.out.println("Sorteando numero aleatório para início da primeira contagem: ");
			System.out.println("Contagem vai iniciar em: " + fila.primeiroAleatorio(1,10));
			System.out.println("A lista de soldados iniciais é : " + fila);
			System.out.println("A lista atualmente tem o tamanho " + fila.size());
			System.out.println();
			
			
			
			for (int i = 1; i <= 9; i++) {
				int n = fila.numAleatorio(-9, 9);
				System.out.print("Sorteando: " + n);
				
				if (n == 0) {
					System.out.println(". Valor inválido ");
					System.out.println("Valor deve ser entre - 9 a 9, exceto 0. ");
					System.out.println();
					i = i -1;
				} else {
					
					if (n > 0) {
						System.out.print(". Movendo para a direita \n");
						System.out.println("Soldado que saiu: " + fila.jose(n));
						System.out.println();
					} else {
						System.out.print(". Movendo para a esquerda para a esquerda \n");
						System.out.println("Soldado que saiu: " + fila.jose(n));
						System.out.println();
					}
					System.out.println("Lista atualizada, Inicio Direita -> " + fila + " <- Fim Direita");
					System.out.println("Lista atualizada, Fim Esquerda -> " + fila + " <- Inicio Esquerda");
					
						
				}
			}
			System.out.println();
			System.out.println("O soldado(a) que irá chamar reforços é o(a): " + fila);
			sc.close();		
	}
	

}
