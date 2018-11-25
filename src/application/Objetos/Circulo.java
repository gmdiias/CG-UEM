package application.Objetos;

import javafx.scene.canvas.GraphicsContext;

public class Circulo extends ObjetoGrafico {

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
		
		double posX, posY, escalaX, escalaY;
		
		if(this.getPontos().get(1).getX() < this.getPontos().get(0).getX()) {
			posX = this.getPontos().get(1).getX();
			escalaX = this.getPontos().get(0).getX() - this.getPontos().get(1).getX();
		}
		else {
			posX = this.getPontos().get(0).getX();
			escalaX = this.getPontos().get(1).getX() - this.getPontos().get(0).getX();
		}
		
		if(this.getPontos().get(1).getY() < this.getPontos().get(0).getY()) {
			posY = this.getPontos().get(1).getY();
			escalaY = this.getPontos().get(0).getY() - this.getPontos().get(1).getY();
		}
		else {
			posY = this.getPontos().get(0).getY();
			escalaY = this.getPontos().get(1).getY() - this.getPontos().get(0).getY();
		}
		
		gc.strokeOval(posX, posY, escalaX, escalaY);
		return true;
	}

}
