package Processo;

import java.util.List;

import anel.AnelController;

public class Processo extends Thread{	
	
	private int id;
	private boolean coordenador;
	private boolean ativo;
	
	AnelController anelController = AnelController.getInstance();
	
	public Processo(int id, boolean coordenador,boolean ativo) {		
		this.id = id;
		this.coordenador = coordenador;
		this.ativo = ativo;
	}
	
	
	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
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
	
	
	public void requisicao() {
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(isAtivo()) {	
						
				if(anelController.verificarCoordenador(getId())&&!isCoordenador()) {
					
					System.out.println("Processo "+getId()+ " comunicou-se com o coordenador ");
					
				}else if(anelController.verificarCoordenador(getId())==false) {
					
					anelController.elegerCoordenador((int)getId());
					System.out.println("O processo "+id+" iniciou a eleição");	
				}								
		}				
	}
	
		
	
	@Override
	public void run() {	
		
			while(true) {
				requisicao();
			}							
				
	}
	

}
