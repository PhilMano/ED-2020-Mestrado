package br.uema.pecs.ed.lista03;

import java.util.Random;
import java.util.Scanner;



public class Josefus<Item> {
	
	
	@SuppressWarnings("hiding")
	class No<Item> {
		private Item item;
		private No<Item> prox;
		private No<Item> ant;
		

		No(Item item) {
			this.item = item;
			this.prox = null;
		}


		public Item getItem() {
			return item;
		}


		public void setItem(Item item) {
			this.item = item;
		}


		public No<Item> getProx() {
			return prox;
		}


		public void setProx(No<Item> prox) {
			this.prox = prox;
		}


		public No<Item> getAnt() {
			return ant;
		}


		public void setAnt(No<Item> ant) {
			this.ant = ant;
		}

	}// ---------------------------------------------------------------------------------------ESTRUTURA_DO_NO

	

	private No<Item> prim;
	private No<Item> ult;
	private int lenght;
	private int countE;
	
	public Josefus() {
		this.prim = null;
		this.ult = null;
		this.lenght = 0;
		this.countE = 0;
	}

	public No<Item> getPrim() {
		return prim;
	}

	public void setPrim(No<Item> prim) {
		this.prim = prim;
	}

	public No<Item> getUlt() {
		return ult;
	}

	public void setUlt(No<Item> ult) {
		this.ult = ult;
	}

	public int getLenght() {
		return lenght;
	}

	public void setLenght(int lenght) {
		this.lenght = lenght;
	}// ---------------------------------------------------------------------------------------GETTERS_AND_SETTERS

	// VERIFICA SE A LISTA É VAZIA
	public boolean eVazia() {
		return (this.prim == null);
	}// ----------------------------------------------------------------------------------------------------------

	public int size() {
		return this.lenght;
	}
	
	// ADICIONANDO LISTA
	public void append(Item item) {
		No<Item> novoNo = new No<Item>(item);
		if (this.eVazia()) {
			this.prim = novoNo;
			this.prim.setProx(novoNo);
			this.prim.setAnt(novoNo);
		} else {
			this.ult.setProx(novoNo);
			novoNo.setAnt(this.ult);
			this.prim.setAnt(novoNo);
			novoNo.setProx(this.prim);
			
		}
		this.ult = novoNo;
		this.lenght++;

	}// ---------------------------------------------------------------------------------------------InserirUltimo

	public Item removeD(int rem) {
		try {
				No<Item> ant = null;
				int count = 1;
				if (count == rem) {
					ant = this.prim;
					this.ult = this.ult.getProx();
					this.ult = this.ult.getAnt();
					count++;
				} else {
					while (count != rem) {
						ant = this.prim.getProx();
						this.prim = this.prim.getProx();
						this.ult = this.ult.getProx();
						count++;
					}					
				}
				this.prim = this.prim.getProx();
				this.ult.setProx(ant.getProx());
				this.prim.setAnt(ant.getAnt());
				this.lenght--;
				return ant.getItem();

		} catch (NullPointerException e) {
			return null;
		}

	}// -------ELEMENTOS A DIREITA
	
	public Item removeE(int rem) {
		try {	
				No<Item> ant = null;
				int count = 1;
				if (count == rem) {
					ant = this.ult;
					this.prim = this.prim.getAnt();
					this.prim = this.prim.getProx();
				} else {
					while (count != rem) {
						ant = this.ult.getAnt();
						this.ult = this.ult.getAnt();
						this.prim = this.prim.getAnt();
						count++;
					}					
				}
				this.ult = this.ult.getAnt();
				this.prim.setAnt(ant.getAnt());
				this.ult.setProx(ant.getProx());
				this.lenght--;
				return ant.getItem();	
		} catch (NullPointerException e) {
			return null;
		}

	}// -------ELEMENTOS A DIREITA
	
	public Item josefus(int num) {
		if (num > 0) {
			countE++;
			return removeD(num);
		} else {
			num = num * -1;
			if (countE == 0) {
				this.prim = this.prim.getProx();
				this.ult = this.ult.getProx();
				countE++;
				return removeE(num);
			}else {
				countE ++;
				return removeE(num);
			}
			
		}
	}
	
	

	public String toString() {
		String msg = "";
		if (this.eVazia()) {
			msg = "A lista está vazia";
		} else {
			No<Item> atual = this.prim;
			int count = 0;
			while (count != lenght) {
				msg += atual.getItem() + " -> ";
				atual = atual.getProx();
				count++;

			}
		}

		return msg;
	}
	
	public String contrario() {
		String msg = "";
		if (this.eVazia()) {
			msg = "A lista está vazia";
		} else {
			No<Item> atual = this.ult;
			int count = 0;
			while (count != lenght ) {
				msg += atual.getItem() + " -> ";
				atual = atual.getAnt();
				count++;
			}
		}

		return msg;
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
			this.prim = this.prim.getProx();
			this.ult = this.ult.getProx();	
		}
		return a;
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		 * 
		 * 
		 * A DESCRIÇÃO DO PROBLEMA NA ENUNCIADO INDICA, QUE INICIALMENTE É REALIZADO UM SORTEIO PARA INDICAR
		 * O INÍCIO DA CONTAGEM, EM SEGUIDAS SÃO REALIZADAS OS DEMAIS SORTEIOS QUE RESULTARÃO NAS SAIDAS DOS
		 * SOLDADOS. COMO SE CONHECE QUAL SOLDADO SERÁ INICIADO A PRIMEIRA CONTAGEM, RESTA SORTEAR O NUMERO 
		 * DA CONTAGEM DE EXCLUSÃO, OS NÚMEROS SORTEADOS VÃO DE -9 A 9, EXCETO O 0. CASO O NUMERO SEJA NEGATIVO A CONTA
		 * GEM É REALIZADO NO SENTIDO ANTI-HORÁRIO (ESQUERDA) E SE POSITIVO NO SENTIDO HORÁRIO (DIREITA).     
		 * UMA DAS MANEIRAS DE RESOLVER ESSA QUESTÃO É SE UTILIZANDO ESTRUTURA DE DADOS, SEJAM FILAS, LISTAS.
		 * NESSE ARQUIVO FOI UTILIZADO UMA LISTA CIRCULAR DUPLAMENTE ENCADEADA. ENQUANDO QUE NO Josefus2 FOI SE
		 * UTILIZADO UMA FILA LINEAR CIRCULA.
		 * 
		 * ENTENDA QUE O PRIM É PRIMEIRO A DIREITA, E O ULTIMO É O PRIMEIRO A ESQUERDA, QUANDO SE REALIZA UMA EXCLUSÃO
		 * 	
		 * OBS: UMA OBSERVAÇÃO IMPORTANTE É QUE NO CASO DA RESOLUÇÃO APRESENTADA A CONTAGEM SE INICIA EM 1, CON-
		 * TANDO A PARTIR DO VALOR INICIAL, E NÃO A PARTIR DE 0, COMO É A CONTAGEM DO JAVA.
		 * 
		 * ABAIXO TERÁ UMA EXPLICAÇÃO DO ALRITMO
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
		 * 
		 * E ASSIM SEGUE ATÉ RESTAR APENAS UM SOLDADO
		 * 
		 * A PRINCIPAL DIFERENÇA DESSA SOLUÇÃO PARA A DE Josefus2 É QUE NÃO PREXISA INSERIR OU RETIRAR ELEMENTOS DO INICIO OU FINAL
		 * PARA FAZER A FILA ANDAR, BASTA IR SEGUINDO PARA O PROXIMO OU ANTERIOR, POIS COMO É UMA LISTA CIRCULAR, UM NÒ TEM
		 * O PONTEIRO PARA O ANTERIOR  A ELE E O PROXIMO A ELE. 
		 * 
	 
		 * 
		 * */
		Scanner sc = new Scanner(System.in);
		Josefus<Integer> lista = new Josefus<Integer>();
		

		for (int i = 1; i <= 10; i++) {
			lista.append(i);
		}
		
		System.out.println("Sorteando numero aleatório para início da primeira contagem: ");
		System.out.println("Contagem vai iniciar em: " + lista.primeiroAleatorio(1, 10));
		System.out.println("A lista de soldados iniciais é com contagem a direita é : " + lista);
		
		System.out.println("A lista atualmente tem o tamanho " + lista.size());
		System.out.println();
		
		for (int i = 1; i <= 9; i++) {
			int n = lista.numAleatorio(-9, 9);
			System.out.print("Sorteando: " + n);
			
			if (n == 0) {
				System.out.println(". Valor inválido ");
				System.out.println("Valor deve ser entre - 9 a 9, exceto 0. ");
				System.out.println();
				i = i -1;
			} else {
				
				if (n > 0) {
					System.out.print(". Movendo para a direita \n");
					System.out.println("Soldado que saiu: " + lista.josefus(n));
				} else {
					System.out.print(". Movendo para a esquerda para a esquerda \n");
					System.out.println("Soldado que saiu: " + lista.josefus(n));
				}
				System.out.println("Novo Inicio da contagem a Direita: " + lista.getPrim().getItem());
				System.out.println("Novo Inicio da contagem a Esquerda: " + lista.getUlt().getItem());
				System.out.println("Tamanho da lista: " + lista.size());
				System.out.println();
				
					
			}
		}
		System.out.println();
		System.out.println("O soldado(a) que irá chamar reforços é o(a): " + lista + " Ajuda");
		sc.close();
		
		
		
		
	}

}
