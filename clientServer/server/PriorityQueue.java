package server;

public class PriorityQueue<Item>{
	

	@SuppressWarnings("hiding")
	// CRIANDO A CLASSE DO NO
	class No<Item> {
		private Item item;
		private Message message;
		private No<Item> prox;

		No(Message message, Item item) {
			this.item = item;
			this.message = message;
			this.prox = null;
		}

		public Item getItem() {
			return item;
		}

		public void setItem(Item item) {
			this.item = item;
		}

		public Message getMessage() {
			return message;
		}

		public void setMessage(Message message) {
			this.message = message;
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

	public PriorityQueue(){
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

	// VERIFICA SE A FILA É VAZIA
	public boolean eVazia() {
		return (this.prim == null);
	}// ----------------------------------------------------------------------------------------------------------
	
	// VERIFICA TAMANHO DA FILA
	public int size() {
		return this.lenght;
	}
	
	public void insert(int pos, No<Item> no) {
		if (pos == 0) {
			no.setProx(this.prim);
			this.prim = no;

		} else if (pos == lenght) {
			this.ult.setProx(no);
			this.ult = no;
		} else {
			No<Item> atual = this.prim;
			No<Item> ant = null;

			for (int i = 0; i < pos; i++) {
				ant = atual;
				atual = atual.getProx();
			}
			no.setProx(atual);
			ant.setProx(no);
		}
	}
	// ADICIONANDO FILA
	public void enqueue(Message message, Item item) {
		No<Item> novoNo = new No<Item>(message,item);
		No<Item> aux = this.prim;
		int i;
		@SuppressWarnings("unchecked")
		Comparable<Item> chave = (Comparable<Item>) item; 
		if (this.eVazia()) {
			this.prim = novoNo;
			this.ult = novoNo;
		} else {
			for (i = 0; i < this.lenght; i++) {
				if(chave.compareTo(aux.getItem()) < 0) {
					break;
				} else if(chave.compareTo(aux.getItem()) == 0){
					i = i + 1;
					break;
				}
				aux = aux.getProx();
			}
			this.insert(i, novoNo);
		}
		this.lenght++;
	}// ---------------------------------------------------------------------------------------------InserirUltimo

	public String dequeue() {
		if (eVazia()) {
			return null;
		} else {
			Message item = this.prim.getMessage();
			this.prim = this.prim.prox;
			lenght--;
			if (eVazia())
				this.ult = null;
			return item.getName();
		}
	}
	
	public Object dequeueO() {
		if (eVazia()) {
			return null;
		} else {
			Message item = this.prim.getMessage();
			this.prim = this.prim.prox;
			lenght--;
			if (eVazia())
				this.ult = null;
			return item;
		}
	}
	
	public String peek(){
		if(eVazia()) {
			return null;
		} else {
			return "Nome do cliente: " + this.prim.getMessage().getName();
		}
	}
	
	
	public String toString() {
		
		String msg = "";
		if (this.eVazia()) {
			msg = "A lista está vazia";
		} else {
			No<Item> atual = this.prim;
			while (atual != null) {
				msg += atual.message.getName();
				if (atual.prox != null) {
					msg += " -> ";
				}
				atual = atual.prox;
			}
		}

		return msg;
	}
}
