package application.Objetos;

import java.util.ArrayList;
import java.util.List;

import application.MousePosition;
import javafx.scene.canvas.GraphicsContext;

public abstract class ObjetoGrafico {

	private int id;
	private String nome;
	private List<MousePosition> pontos = new ArrayList<>();

	public abstract boolean validarObjeto();

	public abstract boolean desenharObjeto(GraphicsContext gc);

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<MousePosition> getPontos() {
		return pontos;
	}

	public void setPontos(List<MousePosition> pontos) {
		this.pontos = pontos;
	}
	
	public void adicionaPonto(double pontoX, double pontoY) {
		if (!validarObjeto()) {
			MousePosition position = new MousePosition(pontoX, pontoY);
			this.getPontos().add(position);
		}
	}

	@Override
	public String toString() {
		StringBuilder retorno = new StringBuilder();
		retorno.append(this.getClass().getSimpleName());
		retorno.append(" - ");
		pontos.forEach(ponto -> {
			retorno.append(ponto.toString());
			retorno.append(",");
		});
		return retorno.toString();
	}

}
