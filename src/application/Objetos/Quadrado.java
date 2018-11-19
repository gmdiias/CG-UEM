package application.Objetos;

import javafx.scene.canvas.GraphicsContext;

public class Quadrado extends ObjetoGrafico {

	@Override
	boolean validarObjeto() {
		if(this.getPontos().size() == 2) 
			return true;
		
		return false;
	}

	@Override
	void desenharObjeto(GraphicsContext gc) {
		if(!validarObjeto())
			return;
		
		gc.strokeLine(this.getPontos().get(0).getX(), this.getPontos().get(0).getY(), this.getPontos().get(1).getX(), this.getPontos().get(1).getY());
	}
	
}
