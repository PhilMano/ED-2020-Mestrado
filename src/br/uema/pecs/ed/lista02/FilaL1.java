package br.uema.pecs.ed.lista02;

import java.util.Scanner;




public class FilaL1{

	class No {
		private int num;
		private No prox;
		private No ant;

		No(int num) {
			this.num = num;
			this.prox = null;
			this.ant = null;
		}

		public int getNum() {
			return num;
		}

		public void setNum(int num) {
			this.num = num;
		}

		public No getProx() {
			return prox;
		}

		public void setProx(No prox) {
			this.prox = prox;
		}

		public No getAnt() {
			return ant;
		}

		public void setAnt(No ant) {
			this.ant = ant;
		}

	}// ---------------------------------------------------------------------------------------ESTRUTURA_DO_NO

	private No prim;
	private No ult;
	private No prim2;
	private No ult2;
	private int lenght;

	public FilaL1() {
		this.prim = null;
		this.ult = null;
		this.prim2 = null;
		this.ult2 = null;
		this.lenght = 0;
	}

	public No getPrim() {
		return prim;
	}

	public void setPrim(No prim) {
		this.prim = prim;
	}

	public No getUlt() {
		return ult;
	}

	public void setUlt(No ult) {
		this.ult = ult;
	}

	public int getLenght() {
		return lenght;
	}

	public void setLenght(int lenght) {
		this.lenght = lenght;
	}// -------------------------------------------------------------------------------GETTERS_AND_SETTERS

	// TESTA SE A LISTA PRINCIPAL É VAZIA
	public boolean eVazia() {
		return this.prim == null;
	}//---------------------------------------------------------------------------FIM_LISTA_PRINCIPAL_VAZIA
	
	// TESTA SE A LISTA SECUNDARIA É VAZIA 
	public boolean eVazia2() {
		return this.prim2 == null;
	}//--------------------------------------------------------------------------FIM_LISTA_SECUNDARIA_VAZIA
	
	// ADICONA FIM DA FILA PRINCIPAL
	public void append(int num) { 
		No novoNo = new No(num); 
		if (this.eVazia()) { 
			this.prim = novoNo; 
		} else {
			this.ult.setProx(novoNo);
			
		}
		this.ult = novoNo; 
		this.lenght++;
	}// ---------------------------------------------------------------------------------------------InserirUltimo

	
	// ADICIONA ORDENADAMENTE NA FILA PRINCIPAL
	public void sortedEnqueue(int num) {
		No novoNo = new No(num);
		No aux = this.prim;
		No ant = null;
		if (eVazia()) {
			this.prim = novoNo;
			this.lenght++;
			return;
		}
		// VERIFICA A POSIÇÃO A SER ADICIONADO
		while (aux != null) {
			if (aux.getNum() > novoNo.getNum()) {
				break;
			}
			ant = aux;
			aux = aux.getProx();
		}

		// INSERE NO INICIO
		if (ant == null) {
			this.prim.setAnt(novoNo);
			novoNo.setProx(this.prim);
			this.prim = novoNo;
			this.lenght++;
		} else {

			// INSERIR ENTRE O ANTERIOR E O AUX
			ant.setProx(novoNo);
			novoNo.setAnt(ant);
			novoNo.setProx(aux);
			this.lenght++;
			// INSERE ULTIMO
			if (aux != null) {
				aux.setAnt(novoNo);
			}
		}

	}// ----------------------------------------------------------------------------------------------ADICIONAR_NA_LISTA
	
	// MÉTODO QUE ORDENA A FILA SECUNDARIA
	public void sorted(int num) {
		No novoNo = new No(num);
		No aux = this.prim2;
		No ant = null;
		if (eVazia2()) {
			this.prim2 = novoNo;
			this.lenght++;
			return;
		}
		// VERIFICA A POSIÇÃO A SER ADICIONADO
		while (aux != null) {
			if (aux.getNum() > novoNo.getNum()) {
				break;
			}
			ant = aux;
			aux = aux.getProx();
		}

		// INSERE NO INICIO
		if (ant == null) {
			this.prim2.setAnt(novoNo);
			novoNo.setProx(this.prim2);
			this.prim2 = novoNo;
			this.lenght++;
		} else {

			// INSERIR ENTRE O ANTERIOR E O AUX
			ant.setProx(novoNo);
			novoNo.setAnt(ant);
			novoNo.setProx(aux);
			this.lenght++;
			// INSERE ULTIMO
			if (aux != null) {
				aux.setAnt(novoNo);
			}
			
		}
		this.prim = this.prim2;
		this.ult = aux;
	}// ----------------------------------------------------------------------------------------------ADICIONAR_NA_LISTA
	
	// MÉTODO QUE USA A FILA PRINCIPAL E A ORDENA, SOBREPÕE A FILA PRINCIPAL NÃO ORDENADA.
	public String convert() {
		// TODA VEZ QUE FOR INICADA TEM QUE ZERA O INICIO E FIM DA FILA, POIS ELE GUARDA VALORES DAS VEZES
		// EM QUE FOI INSTANCIADA ANTERIORMENTE
		this.prim2 = null;
		this.ult2 = null;
		No aux2 = this.prim;
		int count = 0;
		while(aux2 != null && count < this.lenght) {
			this.sorted(aux2.getNum());
			aux2 = aux2.getProx();
			this.lenght--;
			count++;
		}
		return this.converti();
	}
	
	// MÉTODO TOString DA FILA SECUNDARIA
	public String converti() {
		String msg = "";
		if (this.eVazia()) {
			msg = "A lista está vazia";
		} else {

			No atual = this.prim2;
			while (atual != null) {
				msg += atual.getNum() + " -> ";
				atual = atual.getProx();
			}

		}

		return msg + "fim";
	}//---------------------------------------------------------------------------FIM_TO_STRING_SECUNDARIA
	
	// REMOVE DA FILA PRINCIPAL
	public int remove() {
		try {
			int item = this.prim.getNum();
			if (eVazia()) {
				this.ult = null;
				return 0;
			}
			this.prim = this.prim.getProx();
			this.lenght--;
			return item;
		} catch (NullPointerException e) {
			// TODO: handle exception
			return 0 ;
		}
	}//----------------------------------------------------------------------------FIM_REMOVE_FILA_PINCIPAL

	// VERIFICA PRIMEIRO ELEMENTO DA FILA PRINCIPAL
	public int peek() {
		if (eVazia()) {
			return -1;
		} else {
			return this.prim.getNum();
		}
	}//-------------------------------------------------------------------------------FIM_VERIFICA_ELEMENTO
	
	// TAMANHO DA FILA PRINCIPAL
	public int size() {
		return this.lenght;
	}//--------------------------------------------------------------------------FIM_TAMANHO_FILA_PRINCIPAL

	//  toString FILA PRINCIPAL
	public String toString() {
		String msg = "";
		if (this.eVazia()) {
			msg = "A lista está vazia";
		} else {

			No atual = this.prim;
			while (atual != null) {
				msg += atual.getNum() + " -> ";
				atual = atual.getProx();
			}

		}

		return msg + "fim";
	}//------------------------------------------------------------------------FIM_toString_FILA_PRINCIPAL
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		 * 1 QUESTÃO
		 * 
		 * */
		Scanner sc = new Scanner(System.in);
		FilaL1 fila = new FilaL1();
		
		String item = "";
		int num;
		System.out.println(" - para retirar elemento da fila (0 em caso de erro): ");
		System.out.println(" ap para adicionar na fila (Não ordenada): ");
		System.out.println(" se para adicionar na fila (Ordenada): ");
		System.out.println(" c para ordenar fila : ");
		System.out.println(" s para tamanho da lista: ");
		System.out.println(" p para olhar o primeiro elemento da fila (-1 em caso de erro ou fila vazia): ");
		System.out.println(" * para fim da execução: ");
		do {

			System.out.print("Digite a opção: ");
			item = sc.next();

			if (!item.equals("*")) {
				switch (item) {
				case "-":
					System.out.println();
					// -1 para caso de falha;
					// 1 para sucesso.
					System.out.println("Retirando elementos " + fila.remove());
					System.out.println();
					break;

				case "ap":
					System.out.println();
					System.out.print("Digite elemento a ser adiconado: ");
					num = sc.nextInt();
					fila.append(num);
					System.out.println();
					break;
				case "se":
					System.out.println();
					System.out.print("Digite elemento a ser adiconado: ");
					num = sc.nextInt();
					sc.nextLine();
					fila.sortedEnqueue(num);
					System.out.println();
					break;
				case "c":
					System.out.println();
					System.out.print("Ordenando... \n");
					System.out.print("FIla -> " + fila.convert());
					System.out.println();
					break;	
				case "s":
					System.out.println();
					System.out.print("Tamanho da lista: " + fila.size());
					System.out.println();
					break;
				case "p":
					System.out.println();
					System.out.print("O primeiro elemento da fila: " + fila.peek());
					System.out.println();
					break;
				default:
					System.out.println();
					System.out.println("Fila -> " + fila);
					System.out.println();

				}
			}

		} while (!item.equals("*"));
		System.out.print("Fila -> " + fila);
		sc.close();
	}

}
