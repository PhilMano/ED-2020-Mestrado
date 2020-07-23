package br.uema.pecs.ed.basico;


import java.util.Scanner;



public class QueueL<Item> {
	private Node first; // mais recente
	private Node last; // mais antigo
	private int N;

	private class Node {
		private Item item;
		private Node next;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public int size() {
		return N;
	}

	public void enqueue(Item item) {
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		if (isEmpty())
			first = last;
		else
			oldlast.next = last;
		N++;
	}

	public Item dequeue() {
		try {
			Item item = first.item;
			first = first.next;
			N--;
			if (isEmpty()) {
				last = null;
				return item;
			}
			return item;
		} catch (NullPointerException e) {
			// TODO: handle exception
			return null;
		}
	}
	
	public Item peek() {
		if (isEmpty()) {
			return null;
		} else {
			return this.first.item;
		}
	}
	
	public String toString() {
		
		String msg = "";
		if (this.isEmpty()) {
			msg = "A lista está vazia";
		} else {
			Node atual = this.first;
			while (atual != null) {
				msg += atual.item + " -> ";
				atual = atual.next;
			}
		}

		return msg + " Fim Fila";
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		QueueL<String> fila = new QueueL<String>();
        
        String op = "";
        String  item;
        
        System.out.println(" e para adiconar na fila");
        System.out.println(" p verificar primeiro elemento da fila");
        System.out.println(" - para remover da fila");
        System.out.println(" f para ver fila");
        
        System.out.println();
        
        while (!op.equals("*")) {
        	System.out.print("Digite a opção: ");
        	op = sc.next();
            if (op.equals("e")) {
            	System.out.print("Adicionar na fila: ");
            	item = sc.next(); 
                fila.enqueue(item);
                System.out.println("Enfileirando");
                System.out.println();
            } else if (op.equals("-")) {
            	System.out.println("Desenfileirando");
            	System.out.print(fila.dequeue() + " ");
            	System.out.println();
            } else if (op.equals("p")){
            	System.out.println("Primeiro elemento da fila: " + fila.peek());
            	System.out.println();
            } else if (op.equals("f")){
            	System.out.println("FIla: " + fila);
            	System.out.println();
            }                       
        }
        
        System.out.println(fila);
        System.out.println("Existe(ns) " + fila.size() + " elemento(s) na fila");
        sc.close();
    }
	

}
