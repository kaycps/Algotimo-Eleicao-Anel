import Processo.Processo;
import anel.AnelController;

public class Main {
	
	public static void main(String[] args) {
		
		AnelController anel = AnelController.getInstance();
		
		anel.gerarProcesso();		
		anel.finalizarProcesso();
		anel.finalizarCoordenador();
		
		
	}

}
