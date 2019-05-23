package Processo;

public class Processo extends Thread{
	
	
	private int id;
	private boolean coordenador;	
	
	public Processo(int id, boolean coordenador) {		
		this.id = id;
		this.coordenador = coordenador;
	}
	
	public long getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isCoordenador() {
		return coordenador;
	}
	public void setCoordenador(boolean coordenador) {
		this.coordenador = coordenador;
	}
	
	
	
	

}
