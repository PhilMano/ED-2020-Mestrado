package entitiesList3;

public class No {
	
	private Site s;
	private No prox;
	
	public No(Site s) {
		this.s = s;
		this.prox = null;
	}

	public Site getS() {
		return s;
	}

	public void setS(Site s) {
		this.s = s;
	}

	public No getProx() {
		return prox;
	}

	public void setProx(No prox) {
		this.prox = prox;
	}
	
	
	

}
