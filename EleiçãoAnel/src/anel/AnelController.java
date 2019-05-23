package anel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Processo.Processo;

public class AnelController extends Thread{
	
	List<Processo>listaProcessos = new ArrayList<Processo>();
	
	public void gerarProcesso() {
		
		if(listaProcessos.isEmpty()) {
			
			listaProcessos.add(new Processo(0, false));
		}else {
			
			listaProcessos.add(new Processo(listaProcessos.size(), false));
		}
		
	}
	
	
	public void elegerCoordenador(Processo processo) {
		
		if(vericarCoordenador()==false) {
			
			int flag = -1;
			for(int i =0;i<listaProcessos.size();i++) {
				
				if(listaProcessos.get(i).getId()>flag) {
					
					flag=(int) listaProcessos.get(i).getId();
				}
			}
			
			listaProcessos.get(flag).setCoordenador(true);
		}
		
	}
	
	public boolean vericarCoordenador() {
		
		int i =0;
		
		for(int j=0;j<listaProcessos.size();j++) {
			
			if(listaProcessos.get(j).isCoordenador()) {
				
				i++;
			}
			
		}
			
		if(i==0) {
			
			return false;
		}else {
			
			return true;
		}
		
	}
	
	public void finalizarProcesso() {
		
		Random r = null;
		int i = r.nextInt(listaProcessos.size());
		
		
		listaProcessos.get(i).interrupt();
		
	}

}
