package br.uema.pecs.ed.basico;

import java.util.Scanner;

public class StackL<Item> {
		
	private Node first;
	private int lenght;
	
	private class Node{
		Item item;
		Node next;
	}
	
	public boolean isEmpty() {
        return first == null;
    }
	
	public StackL(){
		this.lenght = 0;
		
	}

    public void push(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        lenght++;
    }

    public Item pop() {
       try {

       	Item item = first.item;
           first = first.next;
           lenght--;
           return item;
       } catch (NullPointerException e) {
    	   return null;
		// TODO: handle exception
	}
    }
    
    public Item popBase() {
    	try {
    	Node atual = first;
    	Node ant = null;
    	while(atual.next != null) {
    		ant = atual;
    		atual = atual.next;
    	}
		Item item = atual.item;
    	ant.next = atual.next;
    	lenght--;
    	return item;
    	} catch (NullPointerException e) {
    		return null;
			// TODO: handle exception
		}
    	
    }
    
    public Item removeB(Node ant, Node atu) {
    	try {    		
       	if (atu.next != null) {
    		return removeB(atu, atu.next);
    	}
    	Item item = atu.item;
    	ant.next = null;
    	lenght--;
    	return item;
    	} catch (NullPointerException c) {
    		return null;
			// TODO: handle exception
		}
    }
    

    public Item top() {
		if (this.isEmpty()) {
			return null;
		} else {
			return this.first.item;
		}
    }
    
    public String sizeA() {
    	return "O tamanho da pilha é " + this.lenght;
    }
    
   
	public int sizeL() {
		if (first != null) {
			first = first.next;
			return 1 + sizeL();
		} 
		return 0;
	}
    

    public String toString() {
    	
		String msg = "";
		if (this.isEmpty()) {
			msg = "A lista está vazia";
		} else {
			Node atual = this.first;
			while (atual != null) {
				msg += atual.item + " ";
				atual = atual.next;
			}
		}

		return "Topo -> " + msg;

    }
      
    public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StackL<String> pilha = new StackL<String>();
		String item = "";
		
		System.out.println("- remover da pilha");
		System.out.println("br remove da base (recursiva) pilha");
		System.out.println("b remove da base pilha");
		System.out.println("p empilha");
		System.out.println("t mostra o topo da pilha");
		
		System.out.println();
		
		
		do {

			System.out.print("Opção: ");
			item = sc.next();

			if (!item.equals("*")) {
				switch (item) {
				case "-":
					System.out.println(pilha.pop() + " - retirado");
					System.out.println();
					break;
				case "br":
					System.out.println(pilha.removeB(null, pilha.first) + " - retirado");
					System.out.println();
					break;
				case "b":
					System.out.println(pilha.popBase() +  " - retirado");
					System.out.println();
					break;	
				case "p":
					System.out.println("Elemento para adcionar na pilha: ");
					String elem = sc.next();
					pilha.push(elem);
					System.out.println();
					break;
				case "t":
					System.out.println("Topo = "+pilha.top());
					System.out.println();
					break;
				default:
					System.out.println(pilha);
					System.out.println();
					
				}
			}
			System.out.println(pilha.sizeA());

		} while (!item.equals("*"));

		System.out.println(pilha);
		System.out.println("Existem " + pilha.sizeL() + " elementos na pilha");
		sc.close();
    }
    
  
    

    
    
}
