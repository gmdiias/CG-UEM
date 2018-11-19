package application.Objetos;

public class Triangulo extends ObjetoGrafico {

	@Override
	boolean validarObjeto() {
		if(this.getPontos().size() == 3) {
			return true;
		}
		return false;
	}
	
}
