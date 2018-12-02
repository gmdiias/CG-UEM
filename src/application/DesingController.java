package application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import application.Objetos.Circulo;
import application.Objetos.Linha;
import application.Objetos.ObjetoGrafico;
import application.Objetos.Quadrado;
import application.Objetos.Triangulo;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class DesingController implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private List<MousePosition> mousePositions = new ArrayList<>();
	
	private int desenhoSelected;
	
	private ObjetoGrafico objeto;

	@FXML
	private AnchorPane janela;

	@FXML
	private ImageView clear;

	@FXML
	private ImageView linha;
	
	@FXML
	private ImageView triangulo;
	
	@FXML
	private ImageView quadrado;
	
	@FXML
	private ImageView circulo;

	@FXML
	private Canvas canvas;

	@FXML
	private GraphicsContext gc;
	
	@FXML
	private TextField xPosition;
	
	@FXML
	private TextField yPosition;
	
	@FXML
	private TextField messageText;
	
	@FXML
	private ComboBox<ObjetoGrafico> box;
	
	@FXML
    public void initialize() {
        gc = canvas.getGraphicsContext2D();
    }

	@FXML
	private void onClearSelect() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		box.getItems().clear();
	}
	
	@FXML
	private void onSelectObject() {
		desenhoSelected = 0;

		if(box.getSelectionModel().getSelectedItem() == null) {
			return;
		}

		objeto = box.getSelectionModel().getSelectedItem();
		gc.setStroke(Color.RED);
		objeto.desenharObjeto(gc);
		gc.setStroke(Color.BLACK);
		// gc.scale(0.5, 0.5);
	}
	
	@FXML
	private void onMudancaEscalaSelect() {
		String[] pontoReceived = obtemPosicoes("Mudança de Escala");
		double transladaX = Double.parseDouble(pontoReceived[0]);
		double transladaY = Double.parseDouble(pontoReceived[1]);
		
		String[] positionsReceived = obtemPosicoes("Mudança de Escala");
		double deslocX = Double.parseDouble(positionsReceived[0]);
		double deslocY = Double.parseDouble(positionsReceived[1]);
		
		objeto.getPontos().forEach(dado -> {
			dado.setX(dado.getX() - transladaX);
			dado.setY(dado.getY() - transladaY);
			dado.setX(dado.getX() * deslocX);
			dado.setY(dado.getY() * deslocY);
			dado.setX(dado.getX() + transladaX);
			dado.setY(dado.getY() + transladaY);
		});
		redesenha();
	}
	
	@FXML
	private void onRotacaoSelect() {
		String[] pontoReceived = obtemPosicoes("Mudança de Escala");
		double transladaX = Double.parseDouble(pontoReceived[0]);
		double transladaY = Double.parseDouble(pontoReceived[1]);
		
		String[] grausReceived = obtemPosicoes("Mudança de Escala");
		double graus = Double.parseDouble(grausReceived[0]);
		
        double angulo = Math.PI * graus / 180;
        objeto.getPontos().forEach(dado -> {
			dado.setX(dado.getX() - transladaX);
			dado.setY(dado.getY() - transladaY);
			dado.setX((Math.cos(angulo) * dado.getX()) + (-Math.sin(angulo) * dado.getY()) + (transladaY*Math.sin(angulo) - (transladaX*Math.cos(angulo)) + transladaX));
			dado.setY((Math.sin(angulo) * dado.getX()) + (Math.cos(angulo) * dado.getY()) + (((-transladaX)*Math.sin(angulo)) - (transladaY*Math.cos(angulo)) +transladaY));
			dado.setX(dado.getX() + transladaX);
			dado.setY(dado.getY() + transladaY);
		});
		
		redesenha();
	}
	
	
	@FXML
	private void onTransacaoSelect() {
		String[] positionsReceived = obtemPosicoes("Translação");
		double deslocX = Double.parseDouble(positionsReceived[0]);
		double deslocY = Double.parseDouble(positionsReceived[1]);
		
		objeto.getPontos().forEach(dado -> {
			dado.setX(dado.getX() + deslocX);
			dado.setY(dado.getY() + deslocY);
		});
		redesenha();
		
	}
	
	@FXML
	private void onLinhaSelect() {
		desenhoSelected = 1;
		mousePositions.clear();
		objeto = new Linha();
		showMessageText("Selecione dois pontos para desenhar a reta");
	}
	
	@FXML
	private void onTrianguloSelect() {
		desenhoSelected = 2;
		mousePositions.clear();
		objeto = new Triangulo();
		showMessageText("Selecione tres pontos para desenhar o triangulo");
	}
	
	@FXML
	private void onQuadradoSelect() {
		desenhoSelected = 3;
		mousePositions.clear();
		objeto = new Quadrado();
		showMessageText("Selecione dois pontos para desenhar o retangulo");
	}
	
	@FXML
	private void onCirculoSelect() {
		desenhoSelected = 4;
		mousePositions.clear();
		objeto = new Circulo();
		showMessageText("Selecione dois pontos para desenhar o circulo");
	}

	@FXML
	private void canvasMousePosition(MouseEvent mouseEvent) {
		xPosition.setText("X: " + mouseEvent.getX());
		yPosition.setText("Y: " + mouseEvent.getY());
	}
	
	@FXML
	private void onCanvasClick(MouseEvent mouseEvent) {
		System.out.println("Clique detectado nas posições X: " + mouseEvent.getX() + " Y: " + mouseEvent.getY());
		MousePosition newPosition = new MousePosition(mouseEvent.getX(), mouseEvent.getY());
		desenhaSelecionado(newPosition);
	}
	
	private void redesenha() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		gc.setStroke(Color.BLACK);
		box.getItems().forEach(dado -> dado.desenharObjeto(gc));
	}
	
	private void desenhaSelecionado(MousePosition position) {
		redesenha();
		switch (desenhoSelected) {
		case 1:
			objeto.adicionaPonto(position.getX(), position.getY());
			if(objeto.desenharObjeto(gc)) {
				box.getItems().add(objeto);
				objeto = new Linha();
			}
				
			break;
			
		case 2:
			objeto.adicionaPonto(position.getX(), position.getY());
			if(objeto.desenharObjeto(gc)) {
				box.getItems().add(objeto);
				objeto = new Triangulo();
			}
			break;
			
		case 3:
			objeto.adicionaPonto(position.getX(), position.getY());
			if(objeto.desenharObjeto(gc)) {
				box.getItems().add(objeto);
				objeto = new Quadrado();
			}
			break;
			
		case 4:
			objeto.adicionaPonto(position.getX(), position.getY());
			if(objeto.desenharObjeto(gc)) {
				box.getItems().add(objeto);
				objeto = new Circulo();
			}
			break;

		default:
			showMessageText("Erro: É necessário selecionar um objeto para ser desenhado!");
		}
	}
	
	private void showMessageText(String mensagem) {
		messageText.setText(mensagem);
	}
	
	private String[] obtemPosicoes(String msg) {
		JFrame frame = new JFrame("Realizando transformação de " + msg);
		String receivedPositions = JOptionPane.showInputDialog(frame, "Quanto deseja deslocar ??");

		return receivedPositions.split(";");
	}
	
}
