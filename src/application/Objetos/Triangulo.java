package application.Objetos;

import javafx.scene.canvas.GraphicsContext;

public class Triangulo extends ObjetoGrafico {

	@Override
	public boolean validarObjeto() {
		if(this.getPontos().size() == 3) {
			return true;
		}
		return false;
	}

	@Override
	public boolean desenharObjeto(GraphicsContext gc) {
		if(!validarObjeto())
			return false;
		
		gc.strokePolygon(new double[]{this.getPontos().get(0).getX(), this.getPontos().get(1).getX(), this.getPontos().get(2).getX()}, 
						new double[]{this.getPontos().get(0).getY(), this.getPontos().get(1).getY(), this.getPontos().get(2).getY()}, 3);
		
		return true;
	}

}
