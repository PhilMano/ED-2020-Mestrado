package br.uema.pecs.ed.basico;

import java.util.Arrays;

public class QueueRA<Item> {
	
	private Item[] fila;
	private int N;
	private int ini;
	private int fim;
	
	@SuppressWarnings("unchecked")
	public QueueRA() {
		fila = (Item[]) new Object[2];
		N = 0;
		ini = 0;
		fim =0;
	}
	
	public boolean eVazia() {
		return N == 0;
	}
	
	public int size() {
		return N;
	}
	
	public int sizeT() {
		return fila.length;
	}
	
	
	private void resize(int max) {
		@SuppressWarnings("unchecked")
		Item[] filaN = (Item[]) new Object[max];
		for (int i = 0; i < N; i++) {
			filaN[i] = fila[(ini + 1) % fila.length];			
		}
		fila = filaN;
		ini = 0;
		fim = N;
		
	}
	
	public void inserir(Item item) {
		if (N == fila.length) {
			this.resize(2 * fila.length);
		}
		this.fila[this.fim++] = item;
		if (fim == fila.length) {
			fim = 0;
		}
		N++;
	}
	
	public Item remover() {
		if (this.eVazia()) {
			return null;
		} else {
			Item item = fila[ini];
			fila[ini] = null;
			N--;
			ini++;
			
			if (ini == fila.length) {
				ini = 0;
			}
			
			if(N >0 && N == fila.length/4) {
				this.resize(fila.length/2);
			}
			return item;
		}
	}
	
	public Item peek() {
		if (this.eVazia()) {
			return null;
		} else {
			return fila[ini];
		}
	}

	@Override
	public String toString() {
		return "QueueRA [fila=" + Arrays.toString(fila) + ", N=" + N + ", ini=" + ini + ", fim=" + fim + "]";
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

			QueueRA<String> fila = new QueueRA<String>();
			
			System.out.println(fila.eVazia());
			System.out.println(fila.size());
			System.out.println(fila.sizeT());
			
			fila.inserir("A");
			fila.inserir("B");
			fila.inserir("C");
			fila.inserir("C");
			fila.inserir("D");
			fila.inserir("E");
			
			
			System.out.println(fila);
			
			
	}
	
	
	
}
