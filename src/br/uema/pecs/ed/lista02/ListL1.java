package br.uema.pecs.ed.lista02;

import java.util.Scanner;



public class ListL1{

	@SuppressWarnings("hiding")
	// CRIANDO A CLASSE DO NO
	class No {
		private int item;
		private No prox;

		No(int item) {
			this.item = item;
			this.prox = null;
		}

		public int getItem() {
			return item;
		}

		public void setItem(int item) {
			this.item = item;
		}

		public No getProx() {
			return prox;
		}

		public void setProx(No prox) {
			this.prox = prox;
		}

	}// ---------------------------------------------------------------------------------------ESTRUTURA_DO_NO

	private No prim;
	private No ult;
	private int lenght;
	private int max;
	private int min;

	public ListL1() {
		this.prim = null;
		this.ult = null;
		this.lenght = 0;
		this.max = 0;
		this.min = 2147483647;		
	}
	

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
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
	}// --------------------------------------------------------------------------------GETTERS_AND_SETTERS

	// VERIFICA SE A LISTA É VAZIA
	public boolean eVazia() {
		return (this.prim == null);
	}// ----------------------------------------------------------------------------------------------VAZIA

	// VERIFICAR TAMANHO DA LISTA
	public int size() {
		return this.lenght;
	}// ----------------------------------------------------------------------------------------FIM_DA_LISTA

	// ADICONANDO FILA
	public void add(int item) {
		No novoNo = new No(item);
		this.maxMin(item);
		if (this.eVazia()) {
			this.ult = novoNo;
		}
		novoNo.setProx(this.prim);
		this.prim = novoNo;
		this.lenght++;
	}// -----------------------------------------------------------------------------------------------------------

	// ADICIONANDO MEIO
	public void addMeio(int item) {
		try {
			this.maxMin(item);
			No novoNo = new No(item);
			No atual = this.prim;

			if (this.eVazia()) {
				System.out.println("Lista vazia, não é possível inserir no meio!!!");
			} else {
				int tam = (this.lenght / 2) - 1;
				int count = 0;
				while (tam != count) {
					atual = atual.getProx();
					count++;
				}

				novoNo.setProx(atual.getProx());
				atual.setProx(novoNo);
				this.lenght++;
			}
		} catch (NullPointerException e) {
			// TODO: handle exception
			System.out.println("Existe apenas um elemento na fila, operação ainda não disponível \n"
					+ "Adicione um elemento no início ou no fim");
		}

	}// ----------------------------------------------------------------------------------------InserirMeio

	// ADICIONANDO FIM
	public void addFim(int item) {
		No novoNo = new No(item);
		this.maxMin(item);
		if (this.eVazia()) {
			this.prim = novoNo;
		} else {
			this.ult.setProx(novoNo);
		}
		this.ult = novoNo;
		this.lenght++;

	}// --------------------------------------------------------------------------------------InserirUltimo
	
	
	// REMOVE POR ELEMENTO
	public int removeI(int item) {
		try {
			No atual = this.prim;
			No anterior = null;

			if (this.eVazia()) {
				return -1;
			} else {

				while ((atual != null) && (atual.getItem() != item)) {
					anterior = atual;
					atual = atual.getProx();
				}

				if (atual == this.prim) {
					if (this.prim == this.ult) {
						this.ult = null;
					}
					this.prim = this.prim.getProx();
				} else {
					if (atual == this.ult) {
						this.ult = anterior;
					}
					anterior.setProx(atual.getProx());
				}

				this.lenght--;
				return atual.getItem();
			}
		} catch (NullPointerException e) {
			return -1;
		}

	}// --------------------------------------------------------------------------REMOVE_QUALQUER_ELEMENTO

	// BUSCA ELEMENTO PELO INDICE
	public String busca(int num) {
		try {
			No atual = this.prim;
			No ant = null;

			if (this.eVazia()) {
				return "Lista vazia";
			} else {
				for (int i = 0; i < num; i++) {
					ant = atual;
					atual = atual.getProx();
				}
				return "Elemento encontrado: " + ant.getItem();
			}
		} catch (NullPointerException e) {
			return "Indice excede a lista";
		}
	}// ------------------------------------------------------------------------------FIM_BUSCAR_POR_INDICE
	
	// BUSCA RECURSIVA
	public int buscaRecursiva(No no, int num) {
		try {

			if (no.getItem() == num) {
				return no.getItem();
			} else {
				return buscaRecursiva(no.getProx(), num);
			}
		} catch (NullPointerException e) {
			// TODO: handle exception
			return -1;
		}
	}// ---------------------------------------------------------------------------------IM_BUSCA_RECURSIVA
	
	// FUNÇÃO MAX E MIN
	public void maxMin(int num) {
		if (num > max) {
			this.max = num;
		}
		if (num < min) {
			this.min = num;
		}
		
	}
	// ATUALIZA MAX E MIN
	public String atualizaMaMin() {
		No atual = this.prim;
		while(atual != null) {
			this.maxMin(atual.getItem());
			atual = atual.getProx();
		}
		
		return "Atualizando";
	}
	// toString Max e Min
	public String maxMinE() {
		return "O maior número da lista é: " + max + " e o menor número da lista é: " + min;
	}
	// SOBRESCRITA DO toString
	public String toString() {
		String msg = "";
		if (this.eVazia()) {
			msg = "A lista está vazia";
		} else {
			No atual = this.prim;
			while (atual != null) {
				msg += atual.getItem() + " -> ";
				atual = atual.getProx();
			}
		}

		return msg + "fim";
	}// ---------------------------------------------------------------------------------------FIM_toString

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		 * 1 QUESTÃO LISTA ENCADEADA
		 * */
		Scanner sc = new Scanner(System.in);
		ListL1 lista = new ListL1();

		String item = "";
		int num;
		System.out.println(" i para adiconar no inicio: ");
		System.out.println(" m para adicionar no meio: ");
		System.out.println(" f para adicionar no fim: ");
		System.out.println(" r para remover algum elemento pela chave(valor): ");
		System.out.println(" s para tamanho da lista: ");
		System.out.println(" b para buscar elemento por indice na lista: ");
		System.out.println(" br para buscar elemento recursivamente por valor na lista: ");	
		System.out.println(" n para ver os maiores e menor valores da lista: ");		
		System.out.println(" * para fim da execução: ");
		
		do {

			System.out.print("Digite a opção: ");
			item = sc.next();

			if (!item.equals("*")) {
				switch (item) {
				case "i":
					System.out.println();
					System.out.print("Digite o numero a ser inserido no inicio: ");
					num = sc.nextInt();
					lista.add(num);
					System.out.println();
					break;
				case "m":
					System.out.println();
					System.out.print("Digite o numero a ser inserido no meio: ");
					num = sc.nextInt();
					lista.addMeio(num);
					System.out.println();
					break;
				case "f":
					System.out.println();
					System.out.print("Digite o numero a ser inserido no fim: ");
					num = sc.nextInt();
					lista.addFim(num);
					System.out.println();
					break;
				case "r":
					System.out.println();
					System.out.print("Digite o numero a ser removido: ");
					num = sc.nextInt();
					System.out.print(lista.removeI(num) + '\n');
					if (num == lista.getMax()) {
						lista.setMax(0);
						System.out.print(lista.atualizaMaMin());
					}
					if (num == lista.getMin()) {
						lista.setMin(2147483647);
						System.out.print(lista.atualizaMaMin());
					}
					System.out.println();
					break;
				case "s":
					System.out.println();
					System.out.print("Tamanho da lista: " + lista.size());
					System.out.println();
					break;
				case "b":
					System.out.println();
					System.out.print("Digite o elemento ser buscado pelo indice: ");
					num = sc.nextInt();
					System.out.print(lista.busca(num));
					System.out.println();
					break;
				case "br":
					System.out.println();
					System.out.print("Digite o elemento ser buscado pelo valor: ");
					num = sc.nextInt();
					System.out.print(lista.buscaRecursiva(lista.prim, num));
					System.out.println();
					break;
				case "n":
					System.out.println();
					System.out.print(lista.maxMinE());
					System.out.println();
					break;
				default:
					System.out.println();
					System.out.println("Lista -> " + lista);
					System.out.println();
				}
			}

		} while (!item.equals("*"));
		System.out.print("Lista -> " + lista);
		sc.close();
	}

}
