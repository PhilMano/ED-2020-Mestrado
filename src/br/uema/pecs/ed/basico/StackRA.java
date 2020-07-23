package br.uema.pecs.ed.basico;

import java.util.Scanner;

/*
 * 
 * */
public class StackRA<Item> {

	private Item[] a;
	private int N;

	@SuppressWarnings("unchecked")
	public StackRA() {
		a = (Item[]) new Object[2];
		N = 0;
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public void push(Item item) {
		if (N == a.length) {
			resize(2 * a.length);
		}
		a[N++] = item;
	}

	public Item pop() {

		if (this.isEmpty()) {
			return null;
		} else {
			Item item = a[--N];
			if (N > 0 && N == a.length / 4) {
				resize(a.length / 2);
			}
			return item;
		}
	}

	public Item peek() {
		return a[N - 1];
	}

	public int size() {
		return this.N;
	}

	public int sizeT() {
		return this.a.length;
	}

	@SuppressWarnings("unchecked")
	private void resize(int max) {
		Item[] temp;
		temp = (Item[]) new Object[max];
		for (int i = 0; i < N; i++) {
			temp[i] = a[i];
		}
		a = temp;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("[");
		for (int i = 0; i < N - 1; i++) {
			s.append(this.a[i]);
			s.append(", ");
		}

		if (this.N > 0) {
			s.append(this.a[this.N - 1]);
		}
		s.append("]");
		return s.toString();
	}// --------------------------------------------------------------------------------------------MOSTRAR_PILHA
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		StackRA<String> pilha = new StackRA<String>();
		
		System.out.println(pilha.size());
		
		pilha.push("A");
		pilha.push("B");
		pilha.push("C");
		pilha.push("D");
		pilha.push("E");
		pilha.push("F");
		pilha.push("G");
		pilha.push("I");
		pilha.push("H");
		
		
		System.out.println("O elemento no topo é: "+ pilha.peek());
		System.out.println("O tamanho da pilha com elementos ocupados é " + pilha.size());
		System.out.println("O tamanho total da pilha é " + pilha.sizeT());
		System.out.println(pilha);
		
		
		System.out.println("Retirando da pilha: " + pilha.pop());
		System.out.println("Retirando da pilha: " + pilha.pop());
		System.out.println("Retirando da pilha: " + pilha.pop());
		System.out.println("Retirando da pilha: " + pilha.pop());
		System.out.println("Retirando da pilha: " + pilha.pop());
		System.out.println("O tamanho da pilha com elementos ocupados é " + pilha.size());
		System.out.println("O tamanho total da pilha é " + pilha.sizeT());
		System.out.println(pilha);
		sc.close();
	}
}
