package anel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Processo.Processo;

public class AnelController{
	
	private static AnelController anel;
	
	public static synchronized AnelController getInstance() {
		if(anel==null) {
			return anel = new AnelController();
		}else {
			return anel;
		}
	}
	
	public static  List<Processo>listaProcessos = new ArrayList<Processo>();
	Random r = new Random();
	private static int x = 1;
	private final Object lock = new Object(); 
	
	public void gerarProcesso() {
		new Thread(new Runnable() {			
			@Override			
				public void run() {
					while(true) {						
						
						
							if(listaProcessos.isEmpty()) {
								
								listaProcessos.add(new  Processo(x, false,true));
								System.out.println("Processo "+x+" criado e adicionado na posição:"+listaProcessos.size());
								listaProcessos.get(0).start();
								x++;
							}else {
								
								listaProcessos.add(new Processo(x, false,true));
								System.out.println("Processo "+x+" criado e adicionado na posição:"+listaProcessos.size());								
								listaProcessos.get(listaProcessos.size()-1).start();								
								x++;
							}
							
							try {
								Thread.sleep(5000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					
					}
				}
		}).start();		
		
	}
	
	
	public void elegerCoordenador(int id) {
		new Thread(new Runnable() {			
			@Override
			public void run() {				
					synchronized (lock) {						
						int flag = -1;
						int x=0;							
							
							for(int i =0;i<listaProcessos.size();i++) {
								
								if(listaProcessos.get(i).getId()>flag) {
									
									flag=(int)listaProcessos.get(i).getId();
									x=i; 
								}
							}
							listaProcessos.get(x).setCoordenador(true);;
							System.out.println("O processo "+listaProcessos.get(x).getId()+" é o novo coordenador");
													
					}										
				
			}
		}).start();		
		
	}
	
	public Boolean verificarCoordenador(long id) {
				
		synchronized (lock) {
			
			for(Processo p:listaProcessos) {
				if(p.isCoordenador()) {
					return true;
				}
			}
			
			return false;
		}		
	}	
	
	
	public void finalizarProcesso() {		
		new Thread(new Runnable() {			
			@Override
			public void run() {
				while(true) {					
						
						try {
							Thread.sleep(10000);
							
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						for(int i=0;i<listaProcessos.size();i++) {
							if(!listaProcessos.get(i).isCoordenador()) {
								
								int j = r.nextInt(listaProcessos.size());
								if(j>0) {
									j--;
								}
								System.out.println("Processo "+listaProcessos.get(j).getId()+" Finalizado");
								listaProcessos.get(j).setAtivo(false);
								listaProcessos.remove(j);
							}
						}
						
					}		
					
				
			}
		}).start();
		
	}
	
	public void finalizarCoordenador() {
		new Thread(new Runnable() {			
			@Override
			public void run() {
				
				while(true) {
										
					try {
						Thread.sleep(12000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}					
						
						for(int i=0;i<listaProcessos.size();i++) {
							
							if(listaProcessos.get(i).isCoordenador()) {							
								System.out.println("Coordenador finalizado");
								listaProcessos.get(i).setAtivo(false);
								listaProcessos.remove(i);
							}
						}								
					}	
			}
		}).start();
		
	}
	
		

}
