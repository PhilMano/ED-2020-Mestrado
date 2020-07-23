package br.uema.pecs.ed.lista02;


import java.util.Scanner;



public class ListaRA2<T> {
	

	private int[] vetor;
	private int tamanho;  // VAI CONTROLAR O TAMANHO REAL DO VETOR, APENAS OS QUE NÃO TEM VALOR NULL
	

	public ListaRA2() {
		this.vetor =  new int[2];
		this.tamanho = 0;
		
	}
	
	public boolean eVazia() {
		return this.tamanho == 0 ;
	}

	public boolean append(int elemento) {
		if (this.tamanho == vetor.length) {
			this.resize(2 * this.vetor.length);
		}
			
		if (this.tamanho < this.vetor.length) {
			this.vetor[this.tamanho] = elemento;
			this.tamanho++;
			return true;
		} else {
			return false;
		}

	}// ---------------------------------------------------------------------------------------Add_Element
	
	public boolean insert(int posicao, int elemento) {
		posicao = posicao -1; // EU FIZ ISSO PRA SE ADEQUAR A CONTAGEM DE VETOR DO JAVA
		if (!(posicao >= 0 && posicao < tamanho)) {
			return false;
		}
		if (this.tamanho == vetor.length) {
			this.resize(2 * this.vetor.length);
		}
		for(int i = this.tamanho - 1; i >= posicao; i--) {
			this.vetor[i + 1] = this.vetor[i];			
		}
		
		this.vetor[posicao] = elemento;
		this.tamanho++;
		return true;
	}//--------------------------------------------------------------------------------------Adicona_Qual_Elemento
	
	private void resize(int tam) {
			
			int[] vetor2 =  new int[tam];		
			for (int i =0; i < this.tamanho ; i ++) {
				vetor2[i] = this.vetor[i];
			}
			
			this.vetor = vetor2;
		
	}//-------------------------------------------------------------------------------------AUMENTA_CAPACIDADE
	
	public int size() {
		return this.tamanho;
	}//----------------------------------------------------------------------------------------RETORNA-TAMANHO
	
	public int sizeT() {
		return this.vetor.length;
	}//----------------------------------------------------------------------------------------RETORNA-TAMANHO
	
	
	public int busca(int posicao) {
		posicao = posicao -1; // EU FIZ ISSO PRA SE ADEQUAR A CONTAGEM DE VETOR DO JAVA
		if (!(posicao >= 0 && posicao < tamanho)) {
			return -1;
		}
		
		return this.vetor[posicao];
		
	}//-----------------------------------------------------------------------------------------POSICAO_DO_ELEMENTO
	
	// IMPLEMENTAÇÃO DA FUNÇÃO MaxMin O MELHOR CASO ENCONTRADO ESTÁ PRESENTE NA PÁGINA 9 DO LIVRO QUE POSSUI A FUNÇÃO ((3.N)/2) - 2, PARA O MLHOR CASO
	// PIOR CASO E CASO MÉDIO.
	public void maxMin() {
		int max, min, i, fim;
		if (this.tamanho % 2 > 0) {
			fim = this.tamanho;
		} else {
			fim = tamanho - 1;
		}
		if (this.vetor[1] > this.vetor[2]) {
			max = this.vetor[1];
			min = this.vetor[2];
		} else {
			max = this.vetor[2];
			min = this.vetor[1];
		}

		i = 2;
		while (i <= fim) {
			if (this.vetor[i] > this.vetor[i + 1]) {
				if (this.vetor[i] > max) {
					max = this.vetor[i];
				} else if (this.vetor[i + 1] < min) {
					min = this.vetor[i + 1];
				}
			} else {
				if (this.vetor[i] < min) {
					min = this.vetor[i];
				} else if (this.vetor[i + 1] > max) {
					max = this.vetor[i + 1];
				}
			}
			i = i + 2;
		}
		
		System.out.println("Valor Máximo: " + max + "\nValor Mínimo: " + min);
	}//-----------------------------------------------------------------------------------------------------------------------------------FIM_MIN_E_MAX
	
	
	public int removeU() {
		if(this.eVazia()) {
			return -1;
			
		} else {
			int item = this.vetor[this.tamanho - 1];
			this.tamanho--;
			
			if(this.tamanho > 0 && this.tamanho == this.vetor.length / 4) {
				this.resize(this.vetor.length / 2);
			}
			return item;
		}
	}//----------------------------------------------------------------------------------------------DESEMPILHAR
	
	
	public int remove(int posicao) {	
		posicao = posicao -1; // EU FIZ ISSO PRA SE ADEQUAR A CONTAGEM DE VETOR DO JAVA
		if (!(posicao >= 0 && posicao < tamanho)) {
			return -1;
		}
		if (this.eVazia()){
			return -1;
		}
		int a = this.vetor[posicao];
		for(int i = posicao; i < this.tamanho - 1; i++) {
			this.vetor[i] = this.vetor[i + 1];			
		}
		this.tamanho--;
		if(this.tamanho > 0 && this.tamanho == this.vetor.length / 4) {
			this.resize(this.vetor.length / 2);
		}
		
		return a;
		
	}//----------------------------------------------------------------------------------------REMOVE_QUALQUER_POSICAO
	
	
	public void clearLista() {
		this.vetor = new int[this.vetor.length];
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("[");
		for(int i =0; i < this.tamanho -1 ; i++) {
			s.append(this.vetor[i]);
			s.append(", ");
		}
		
		if (this.tamanho > 0) {
			s.append(this.vetor[this.tamanho -1 ]);
		}
			
		
		s.append("]");
		return s.toString();
	}//-------------------------------------------------------------------------------------------------- TO_STRING
	

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		 *  2 QUESTÃO
		 * */
		
		
		// OBS PARA A INSERÇÃO, REMOÇÃO E BUSCA POR INDICE, COMEÇE POR 1, OS MÉTODOS JA FAZEM A CONVERSÃO
    	//PARA A CONTAGEM DO VETOR.
		Scanner sc = new Scanner(System.in);
		ListaRA2<String> lista = new ListaRA2<String>();
		
		// PARA RESURMIR OS MÉTODOS DE INSERÇÃO, CRIEI UMA PARA INICIAR NO FINAL, CAJO SEJA O PRIMEIRO ELEMENTO DA LISTA
		// E CRIEI OUTRO PARA INSERIR EM QUALQUER POSIÇÃO DA PRIMEIRA ATÉ A PENULTIMA. 
		lista.append(1);
		lista.insert(1, 10);
		lista.insert(2, 20);
		lista.insert(3, 30);
		lista.insert(4, 40);
		lista.insert(1, 50);
		lista.append(70);
		lista.append(-20);
		lista.append(-70);
		System.out.println(lista);
		System.out.println(lista.size());
		System.out.println(lista.sizeT());
		lista.maxMin();
		System.out.println();
		
		// A MESMA COISA VALE PRA REMOVER QUE O removeU REMOVE O ULTIMO, OS OUTROS PODEM SER REMOVIDOS
		// COM O remove
		
		System.out.println(lista.removeU());
		System.out.println(lista.remove(1));
		System.out.println(lista);
		System.out.println(lista.size());
		System.out.println(lista.sizeT());
		lista.maxMin();
		System.out.println();
		
		System.out.println(lista.removeU());
		System.out.println(lista.remove(4));		
		System.out.println(lista);
		System.out.println(lista.size());
		System.out.println(lista.sizeT());
		lista.maxMin();
		System.out.println();
		
	
		System.out.println(lista.remove(4));		
		System.out.println(lista);
		System.out.println(lista.size());
		System.out.println(lista.sizeT());
		lista.maxMin();
		System.out.println();
		
		sc.close();
		
		
		
		
		
		
		
	}



}
