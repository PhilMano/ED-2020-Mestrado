/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uema.pecs.ed.basico;

import java.util.Scanner;


/**
 *
 * @author lccf
 */
/**
 *
 * @author lccf
 * @param <Item> It's really simple. It's a new feature introduced in J2SE 5.
 * Specifying angular brackets after the class name means you are creating a
 * temporary data type which can hold any type of data.
 *
 *
 * Instead of <T>, you can actually write anything and it will work the same
 * way. Try writing <ABC> in place of <T>. This is just for convenience:
 *
 * <T> is referred to as any type
 * <E> as element type
 * <N> as number type
 * <V> as value
 * <K> as key
 *
 * But you can name it anything you want, it doesn't really matter. Moreover,
 * Integer, String, Boolean etc are wrapper classes of Java which help in
 * checking of types during compilation. For example, in the above code, obj is
 * of type String, so you can't add any other type to it (try obj.add(1), it
 * will cast an error). Similarly, obj1 is of the Integer type, you can't add
 * any other type to it (try obj1.add("hello"), error will be there).
 */
public class ListaRA<Item> {

    private Item[] a;
    private int N;

    //CRIAÇÃO DE UMA LISTA
    @SuppressWarnings("unchecked")
	public ListaRA() {
        a = (Item[]) new Object[2];
        N = 0;
    }
    
    public boolean eVazia() {
    	return this.N == 0;
    }
    @SuppressWarnings("unchecked")
	private void resize(int max) {
        Item[] temp;
        temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        this.a = temp;
    }

    //INSERIR UM NO NO FIM
    public boolean append(Item item) {
        if (N == a.length) {
            resize(2 * a.length);
        }
        if (this.N < this.a.length) {
        	this.a[this.N] = item;
        	this.N++;
        	return true;
        } else {
        	return false;
        }
    }//------------------------------------------------------------------------FIM_INSERIR_QUALQUER_POSIÇÃO
    
    // ADICONAR QUALQUER POSIÇÃO FORA A ULTIMA, PELO INDICE
    public boolean insert (int posicao, Item item) {
    	posicao = posicao -1;
    	if (!(posicao >= 0 && posicao < N)) {
    		return false;    		
    	}
    	if (this.N == this.a.length) {
    		this.resize(2 * this.a.length);
    	}
    	for(int i = this.N - 1; i >= posicao; i--) {
    		this.a[i + 1] = this.a[i];
    	}
    	this.a[posicao] = item;
    	this.N++;
    	return true;
    }//--------------------------------------------------------------------------------------FIM_ADD_INDICE
   
    // TAMANHO OCUPADO
    public int size() {
		return this.N;
	}//----------------------------------------------------------------------------------------RETORNA-TAMANHO
	
    // TAMANHO TOTAL DO VETOR
	public int sizeT() {
		return this.a.length;
	}//----------------------------------------------------------------------------------------RETORNA-TAMANHO
	
	// REMOVE ULTIMO
    public Item removeU() {
		if(this.eVazia()) {
			return null;
			
		} else {
			Item item = this.a[this.N - 1];
			this.N--;
			
			if(this.N > 0 && this.N == this.a.length / 4) {
				this.resize(this.a.length / 2);
			}
			return item;
		}
	}//----------------------------------------------------------------------------------------------DESEMPILHAR
	
	
    // REMOVE POR POSIÇÃO
	public Item remove(int posicao) {	
		posicao = posicao -1; // EU FIZ ISSO PRA SE ADEQUAR A CONTAGEM DE VETOR DO JAVA
		if (!(posicao >= 0 && posicao < this.N)) {
			return null;
		}
		if (this.eVazia()){
			return null;
		}
		Item item = this.a[posicao];
		for(int i = posicao; i < this.N - 1; i++) {
			this.a[i] = this.a[i + 1];			
		}
		this.N--;
		if(this.N > 0 && this.N == this.a.length / 4) {
			this.resize(this.a.length / 2);
		}
		
		return item;
		
	}//------------------------------------------------------------------------FIM_REMOVE_QUALQUER_POSICAO
	
	
    //BUSCAR UM NÓ
    public Item busca(int posicao) {
    	posicao = posicao - 1;
        if (!(posicao >= 0 && posicao < this.N)) {
            return null;
        }
        return this.a[posicao];
    }//----------------------------------------------------------------------------------------IM_BUSCA_NO
    
   
    
    //DESTRUIR UMA LISTA
    public void clearList() {
        a = (Item[]) new Object[2];
        N = 0;
    }

    @Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("[");
		for(int i =0; i < this.N -1 ; i++) {
			s.append(this.a[i]);
			s.append(", ");
		}
		
		if (this.N > 0) {
			s.append(this.a[this.N -1 ]);
		}
		s.append("]");
		return s.toString();
	}//-------------------------------------------------------------------------------------------------- TO_STRING
	

    public int getN() {
        return N;
    }
    
    public static void main(String[] args) {
        
    	// OBS PARA A INSERÇÃO, REMOÇÃO E BUSCA POR INDICE, COMEÇE POR 1, OS MÉTODOS JA FAZEM A CONVERSÃO
    	//PARA A CONTAGEM DO VETOR JAVA.
    	Scanner sc = new Scanner(System.in);
		ListaRA<String> lista = new ListaRA<String>();

		// PARA RESURMIR OS MÉTODOS DE INSERÇÃO, CRIEI UMA PARA INICIAR NO FINAL, CASO
		// SEJA O PRIMEIRO ELEMENTO DA LISTA
		// E CRIEI OUTRO PARA INSERIR EM QUALQUER POSIÇÃO DA PRIMEIRA ATÉ A PENULTIMA.
		lista.append("F");
		lista.insert(1, "A");
		lista.insert(2, "B");
		lista.insert(3, "C");
		lista.insert(4, "D");
		lista.insert(1, "E");
		lista.append("G");
		lista.append("H");
		lista.append("I");
		System.out.println(lista);
		System.out.println(lista.size());
		System.out.println(lista.sizeT());

		// A MESMA COISA VALE PRA REMOVER QUE O removeU REMOVE O ULTIMO, OS OUTROS PODEM
		// SER REMOVIDOS
		// COM O remove

		System.out.println(lista.removeU());
		System.out.println(lista.remove(1));
		System.out.println(lista);
		System.out.println(lista.size());
		System.out.println(lista.sizeT());
		System.out.println();

		System.out.println(lista.removeU());
		System.out.println(lista.remove(4));
		System.out.println(lista);
		System.out.println(lista.size());
		System.out.println(lista.sizeT());
		System.out.println();

		System.out.println(lista.remove(4));
		System.out.println(lista);
		System.out.println(lista.size());
		System.out.println(lista.sizeT());
		System.out.println();
		
		System.out.print(lista.busca(2));
		
		sc.close();
    }

}
