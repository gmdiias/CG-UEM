package application.Objetos;

import javafx.scene.canvas.GraphicsContext;

public class Circulo extends ObjetoGrafico {

	@Override
	public boolean validarObjeto() {
		if (this.getPontos().size() == 2) {
			return true;
		}
		return false;
	}

	@Override
	public boolean desenharObjeto(GraphicsContext gc) {
		if (!validarObjeto())
			return false;

		double posX, posY, escalaX, escalaY;

		posX = this.getPontos().get(0).getX();
		escalaX = (this.getPontos().get(1).getX() - this.getPontos().get(0).getX())*2;

		posY = this.getPontos().get(0).getY();
		escalaY = (this.getPontos().get(1).getY() - this.getPontos().get(0).getY())*2;

		double raio = (escalaX * escalaX) + (escalaY * escalaY);
		raio = Math.sqrt(raio);

		gc.strokeOval(posX - (raio / 2), posY - (raio / 2), raio, raio);
		return true;
	}

}
