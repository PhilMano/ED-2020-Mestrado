package br.uema.pecs.ed.lista02;

import java.util.Scanner;


public class LinkedList5 {

	// CRIANDO A CLASSE DO NO
	class No {
		private int num;
		private No prox;

		No(int num) {
			this.num = num;
			this.prox = null;
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

	}// ---------------------------------------------------------------------------------------ESTRUTURA_DO_NO

	private No prim;
	private No ult;
	private int lenght;
	private int countp;

	public LinkedList5() {
		this.prim = null;
		this.ult = null;
		this.lenght = 0;
		this.countp = 0;
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
	}

	public int getCountp() {
		return countp;
	}

	public void setCountp(int countp) {
		this.countp = countp;
	}// ---------------------------------------------------------------------------------------GETTERS_AND_SETTERS

	// VERIFICA SE A LISTA É VAZIA
	public boolean eVazia() {
		return (this.prim == null);
	}// ----------------------------------------------------------------------------------------------------------

	// TAMANHO DA LISTA
	public int size() {
		return this.lenght;
	}

	// ADICONANDO NA INICIO DA LISTA
	public void add(int num) {
		No novoNo = new No(num);
		if (num % 2 != 0) {
			this.countp++;
		}
		if (this.eVazia()) {
			this.ult = novoNo;
		}
		novoNo.setProx(this.prim);
		this.prim = novoNo;
		this.lenght++;
	}// -----------------------------------------------------------------------------------------------------------

	// INSERE NO MEIO DA LISTA
	public void addMeio(int num) {
		No novoNo = new No(num);
		No atual = this.prim;

		if (num % 2 != 0) {
			this.countp++;
		}
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

	// ADICONA NO FIM DA LISTA
	public void addFim(int num) {
		No novoNo = new No(num);
		if (num % 2 != 0) {
			this.countp++;
		}
		if (this.eVazia()) {
			this.prim = novoNo;
		} else {
			this.ult.setProx(novoNo);
		}
		this.ult = novoNo;
		this.lenght++;

	}// ---------------------------------------------------------------------------------------------InserirUltimo

	// REMOVE QUALQUER ELEMENTO DA LISTA, E TEM COMO PARAMETRO UM ELEMENTO PRESENTE
	// NA LISTA
	public int remove(int num) {
		try {
			No atual = this.prim;
			No anterior = null;

			if (this.eVazia()) {
				return -1;
			} else {

				if (num % 2 != 0) {
					this.countp--;
				}
				while ((atual != null) && (atual.getNum() != num)) {
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
				return atual.getNum();
			}
		} catch (NullPointerException e) {
			if (num % 2 != 0) {
				this.countp++;
			}

			return -1;
		}

	}// ---------------------------------------------------------------------------------------REMOVE_QUALQUER_ELEEMENTO

	// IMPRIME TODOS OS NUMEROS PRIMOS DA LISTA ENCADEADA
	public String primos() {

		int[] vet = new int[countp];
		No atual = this.prim;
		int i = 0;
		if (countp == 0) {
			return "Não existe números primos";
		} else {
			while ((atual != null)) {
				if (atual.getNum() % 2 != 0) {
					vet[i++] = atual.getNum();
				}
				atual = atual.getProx();
			}

			StringBuilder s = new StringBuilder();
			s.append("[");

			for (i = 0; i < countp - 1; i++) {
				s.append(vet[i]);
				s.append(", ");
			}

			if (this.countp > 0) {
				s.append(vet[this.countp - 1]);
			}

			s.append("]");
			return "Os numeros primos são: " + s.toString();
		}
	}

	// BUSCA ELEMENTO PELO INDICE
		public String busca(int num) {
			try {
			No atual = this.prim;
			No ant = null;
			for (int i = 0; i < num; i++ ) {
				ant = atual;
				atual = atual.getProx();
			}
			return "Elemento encontrado: " + ant.getNum();
			} catch (NullPointerException e) {
				return "Indice excede a lista" ;
			}
		}

	// SOBRESCRITA DO MÉTODO toString
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
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		 * 5 QUESTÃO
		 * 
		 * */
		Scanner sc = new Scanner(System.in);
		LinkedList5 lista = new LinkedList5();

		String item = "";
		int num;
		System.out.println(" - para imprimir elementos primos: ");
		System.out.println(" i para adiconar no inicio: ");
		System.out.println(" m para adicionar no meio: ");
		System.out.println(" f para adicionar no fim: ");
		System.out.println(" r para remover algum elemento pela valor: ");
		System.out.println(" s para tamanho da lista: ");
		System.out.println(" b para buscar elemento por posição na lista (começa em 1): ");
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
					System.out.println(lista.primos());
					System.out.println();
					break;
				case "i":
					System.out.println();
					System.out.print("Digite numero a ser inserido no inicio: ");
					num = sc.nextInt();
					sc.nextLine();
					lista.add(num);
					System.out.println();
					break;
				case "m":
					if (lista.size() <= 1) {
						System.out.println("Não é possível ainda adicionar no meio");
						System.out.println("Insira no inicio ou no final");
						System.out.println();
					} else {System.out.println();
					System.out.print("Digite numero a ser inserido no meio: ");
					num = sc.nextInt();
					lista.addMeio(num);
					System.out.println();						
					}
					
					break;
				case "f":
					System.out.println();
					System.out.print("Digite numero a ser inserido no fim: ");
					num = sc.nextInt();
					lista.addFim(num);
					System.out.println();
					break;
				case "r":
					System.out.println();
					System.out.print("Digite o numero a ser removido: ");
					num = sc.nextInt();
					System.out.print(lista.remove(num));
					System.out.println();
					break;
				case "s":
					System.out.println();
					System.out.print("Tamanho da lista: " + lista.size());
					System.out.println();
					break;
				case "b":
					System.out.println();
					System.out.print("Digite o numero ser buscado pelo indice: ");
					num = sc.nextInt();
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
