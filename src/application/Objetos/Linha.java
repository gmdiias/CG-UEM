package application.Objetos;

import javafx.scene.canvas.GraphicsContext;

public class Linha extends ObjetoGrafico {

	@Override
	public boolean validarObjeto() {
		if(this.getPontos().size() == 2) {
			return true;
		}
		return false;
	}

	@Override
	public boolean desenharObjeto(GraphicsContext gc) {
		if(!validarObjeto())
			return false;
		
		gc.strokeLine(this.getPontos().get(0).getX(), this.getPontos().get(0).getY(), this.getPontos().get(1).getX(), this.getPontos().get(1).getY());
		return true;
	}

}
