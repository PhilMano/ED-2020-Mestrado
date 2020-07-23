package br.uema.pecs.ed.lista02;

import java.util.Scanner;




public class LinkedList4<Item> {

	@SuppressWarnings("hiding")
	// CRIANDO A CLASSE DO NO
	class No<Item> {
		private Item item;
		private No<Item> prox;

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

	}// ---------------------------------------------------------------------------------------ESTRUTURA_DO_NO

	private No<Item> prim;
	private No<Item> ult;
	private int lenght;

	public LinkedList4() {
		this.prim = null;
		this.ult = null;
		this.lenght = 0;
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

	// ADICONANDO FILA
	public void add(Item item) {
		No<Item> novoNo = new No<Item>(item);
		if (this.eVazia()) {
			this.ult = novoNo;
		}
		novoNo.setProx(this.prim);
		this.prim = novoNo;
		this.lenght++;
	}// -----------------------------------------------------------------------------------------------------------

	// ADICIONANDO MEIO
	public void addMeio(Item item) {
		No<Item> novoNo = new No<Item>(item);
		No<Item> atual = this.prim;

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

	}// ----------------------------------------------------------------------------------------------InserirMeio

	// ADICIONANDO FIM
	public void addFim(Item item) {
		No<Item> novoNo = new No<Item>(item);
		if (this.eVazia()) {
			this.prim = novoNo;
		} else {
			this.ult.setProx(novoNo);
		}
		this.ult = novoNo;
		this.lenght++;

	}// ---------------------------------------------------------------------------------------------InserirUltimo

	public Item removeI(Item item) {
		try {
			No<Item> atual = this.prim;
			No<Item> anterior = null;

			if (this.eVazia()) {
				return null;
			} else {
				
				while ((atual != null) && (!atual.getItem().equals(item))) {
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
			return null;
		}

	}// -------REMOVE_QUALQUER_ELEMENTO

	// REMOVE OS ELEMENTOS DE CHAVES PARES

	public int removePar() {
		try {
			No<Item> atual = this.prim;
			No<Item> anterior = null;
			int count = 0;
			if (this.eVazia()) {
				return -1;
			} else {
				
				for (int i = 0; i < this.lenght; i++) {
					if (i == 0) {
						this.prim = this.prim.getProx();
						count++;
					}
					if (i % 2 == 0 && i != 0) {
						anterior.setProx(atual.getProx());
						if(atual == this.ult) {
							this.ult = anterior;
						}
						count++;
					}
					if (i % 2 != 0) {
					anterior = atual;
					}
					atual = atual.getProx();
				}
				this.lenght = this.lenght - count;
				return count;
			}
		} catch (NullPointerException e) {
			return (int) -1;
		}

	}// -------REMOVE_ELEMENTO_PAR

	// BUSCA ELEMENTO PELO INDICE
	public String busca(int num) {
		try {
			No<Item> atual = this.prim;
			No<Item> ant = null;
			for (int i = 0; i < num; i++) {
				ant = atual;
				atual = atual.getProx();
			}
			return "Elemento encontrado: " + ant.getItem();
		} catch (NullPointerException e) {
			return "Indice excede a lista";
		}
	}

	public String toString() {
		String msg = "";
		if (this.eVazia()) {
			msg = "A lista está vazia";
		} else {
			No<Item> atual = this.prim;
			while (atual != null) {
				msg += atual.getItem() + " -> ";
				atual = atual.getProx();
			}
		}

		return msg + " -> fim";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		 * 4 QUESTÃO
		 * 
		 * */
		Scanner sc = new Scanner(System.in);
		LinkedList4<String> lista = new LinkedList4<String>();

		String item = "";
		System.out.println(" - para retirar os elementos com chaves(indices) pares (utiliza-se a contagem do Java começando em 0): ");
		System.out.println(" i para adiconar no inicio: ");
		System.out.println(" m para adicionar no meio: ");
		System.out.println(" f para adicionar no fim: ");
		System.out.println(" r para remover algum elemento pela chave(valor): ");
		System.out.println(" s para tamanho da lista: ");
		System.out.println(" b para buscar elemento por indice na lista: ");
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
					int o = lista.removePar();
					if (o > 0) {
						if (o  == 1) {
							System.out.println("Retirando " + o + " chave par");
						} else {
							System.out.println("Retirando " + o + " chaves pares");
						}
						
						System.out.println();
					} else {
						System.out.println("Erro na remoção das chaves pares");
					}
					break;
				case "i":
					System.out.println();
					System.out.print("Digite elemento a ser inserido no inicio: ");
					item = sc.next();
					lista.add(item);
					System.out.println();
					break;
				case "m":
					if (lista.size() <= 1) {
						System.out.println("Impossível acionar elemento no meio, pois so existe 1 elemento na lista");
						System.out.println("Adicione um elemento no início ou final");
					} else {
						System.out.println();
						System.out.print("Digite elemento a ser inserido no meio: ");
						item = sc.next();
						lista.addMeio(item);
					}
			
					System.out.println();
					break;
				case "f":
					System.out.println();
					System.out.print("Digite elemento a ser inserido no fim: ");
					item = sc.next();
					lista.addFim(item);
					System.out.println();
					break;
				case "r":
					System.out.println();
					System.out.print("Digite o elemento a ser removido: ");
					item = sc.next();
					System.out.print(lista.removeI(item));
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
					int num = sc.nextInt();
					sc.nextLine();
					System.out.print(lista.busca(num));
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
