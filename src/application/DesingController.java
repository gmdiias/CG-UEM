package application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class DesingController implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private List<MousePosition> mousePositions = new ArrayList<>();
	
	private int desenhoSelected;

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
	private ComboBox box;

	@FXML
    public void initialize() {
        gc = canvas.getGraphicsContext2D();
    }

	@FXML
	private void onClearSelect() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}

	@FXML
	private void onLinhaSelect() {
		desenhoSelected = 1;
		mousePositions.clear();
		showMessageText("Selecione dois pontos");
	}
	
	@FXML
	private void onTrianguloSelect() {
		desenhoSelected = 2;
		mousePositions.clear();
	}
	
	@FXML
	private void onQuadradoSelect() {
		desenhoSelected = 3;
		mousePositions.clear();
	}
	
	@FXML
	private void onCirculoSelect() {
		desenhoSelected = 4;
		mousePositions.clear();
	}

	@FXML
	private void canvasMousePosition(MouseEvent mouseEvent) {
		xPosition.setText("X: " + mouseEvent.getX());
		yPosition.setText("Y: " + mouseEvent.getY());
	}
	
	@FXML
	private void onCanvasClick(MouseEvent mouseEvent) {
		// TODO metodo linha
		System.out.println("Posicao X: " + mouseEvent.getX() + " Y: " + mouseEvent.getY());
		MousePosition newPosition = new MousePosition(mouseEvent.getX(), mouseEvent.getY());
		desenhaSelecionado(newPosition);
	}
	
	private void desenhaSelecionado(MousePosition position) {
		switch (desenhoSelected) {
		case 1:
			if(mousePositions.size() == 1) {
				gc.strokeLine(mousePositions.get(0).getX(), mousePositions.get(0).getY(), position.getX(), position.getY());
				mousePositions.clear();
				box.getItems().add(mousePositions);
			}
			else {
				mousePositions.add(position);
			}
			break;
			
		case 2:
			
			break;
			
		case 3:
			
			break;
			
		case 4:
			if(mousePositions.size() == 1) {
				gc.strokeOval(mousePositions.get(0).getX(), mousePositions.get(0).getY(), position.getX(), position.getY());
				mousePositions.clear();
			}
			else {
				mousePositions.add(position);
			}
			break;

		default:
			showMessageText("Erro: � necess�rio selecionar um objeto para ser desenhado!");
		}
	}
	
	private void showMessageText(String mensagem) {
		messageText.setText(mensagem);
	}
}
