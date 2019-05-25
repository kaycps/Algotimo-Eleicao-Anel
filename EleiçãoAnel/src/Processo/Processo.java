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
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(isAtivo()) {	
			
			int flag = 0;
			
			for(int i=0; i<anelController.listaProcessos.size();i++) {
				
				if(anelController.listaProcessos.get(i).isCoordenador()&&anelController.listaProcessos.get(i).getName()!=getName()) {
					
					System.out.println("Processo "+getId()+ " comunicou-se com o coordenador "+anelController.listaProcessos.get(i).getId());
					flag=1;
				}
			}
			
			if(!isCoordenador()&&flag==1) {				
				
				anelController.elegerCoordenador((int)getId());
				System.out.println("id: "+getId());
				System.out.println("entrou");		
				flag=0;
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
