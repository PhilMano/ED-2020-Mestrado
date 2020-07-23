package entitiesList3;

public class ListChained {
	
	private No prim;
	private No ult;
	private No aux1;
	private Site aux2;
	private int lenght;
	
	// CONSTRUTOR
	public ListChained() {
		this.prim = null;
		this.ult = null;
		this.aux1 = null;
		this.aux2 = null;
		this.lenght = 0;
	}//-------------------------------------------------------------------------------------------------FIM_CONSTRUTOR

	// MÉTODOS GETS E SETS
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
	}//---------------------------------------------------------------------------------------------GETTERS_AND_SETTERS
	
	// VERIFICA SE A LISTA É VAZIA
	public boolean eVazia() { // VERIFICA SE A LISTA É VAZIA
		return (this.prim == null);
	}// -----------------------------------------------------------------------------------------------------FIM_eVazia
	
	// INSERE PRIMEIRO
	public void inserirPrimeiro(Site s) {
		No novoNo = new No(s);
		if (this.eVazia()) { 
			this.ult = novoNo;	
		}
		novoNo.setProx(this.prim);
		this.prim = novoNo;
		this.lenght++;

	}// --------------------------------------------------------------------------------------------FIM_InserirPrimeiro
	
	// INSERIR MEIO
	public void inserirMeio(Site s) {
		No novoNo = new No(s);
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
	}// ---------------------------------------------------------------------------------------------FIM_InserirMeio
	
	
	// INSERE ULTIMO
	public void inserirUltimo(Site s) { 
		No novoNo = new No(s); 
		if (this.eVazia()) { 
			this.prim = novoNo; 		
		} else {
			this.ult.setProx(novoNo);		
		}
		this.ult = novoNo;
		this.lenght++;
	}// --------------------------------------------------------------------------------------------FIM_InserirUltimo
	
	// REMOVE NO
	public boolean removerNo(String nome_site) {
		No atual = this.prim;
		No anterior = null;
		try {
		if (this.eVazia()) {
			return false;
		} else {
			while ((atual != null) && (!atual.getS().getNome().equalsIgnoreCase(nome_site))) { 
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
			aux1 = atual;
			aux2 = atual.getS();
			return true;
		}
		}catch (NullPointerException e) {
			// TODO: handle exception
			return false;
		}

	}// -------------------------------------------------------------------------------------------------FIM_RemoverNo
	
	// PESQUISAR NO
	public String pesquisaNo(String nome_site) {
		String msg = "";
		boolean resp = this.removerNo(nome_site);
		if (resp) {
			Site novoS = aux2;
			this.inserirPrimeiro(novoS);
			return msg = "Site: " + aux1.getS().getNome() + "\n" +
				     "Link: " + aux1.getS().getLink();
		} else {
			return "Site não contido na lista";
		} 
		
		
	}// ---------------------------------------------------------------------------------------------pesquisarNo
	
	
	// IMPRIMIR LISTA
	public String imprimirLista() {
		String msg = "";
		if (this.eVazia()) {
			msg = "A lista está vazia";
		} else {
			No atual = this.prim;
			while (atual != null) {
				msg += atual.getS().getNome() + " -> ";
				atual = atual.getProx();
			}
		}

		return msg + "fim";
	}//FIM_IMPRIMIR_LISTA
}
