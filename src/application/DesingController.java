package application;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class DesingController implements Serializable {
	
	private List<MousePosition> mousePositions = new ArrayList<>();
	
	private int desenhoSelected;

	@FXML
	private AnchorPane janela;

	@FXML
	private Button btnClear;

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
    public void initialize() {
        gc = canvas.getGraphicsContext2D();
    }

	@FXML
	private void onClearSelect(ActionEvent event) {
		// TODO metodo clear
		System.out.println("TESTESSSSS");
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}

	@FXML
	private void onLinhaSelect() {
		desenhoSelected = 1;
		mousePositions.clear();
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
	private void onCanvasClick(MouseEvent mouseEvent) {
		// TODO metodo linha
		System.out.println("Posicao X: " + mouseEvent.getX() + " Y: " + mouseEvent.getY());
		System.out.println("canvas click");
		MousePosition newPosition = new MousePosition(mouseEvent.getX(), mouseEvent.getY());
		desenhaSelecionado(newPosition);
	}
	
	private void desenhaSelecionado(MousePosition position) {
		switch (desenhoSelected) {
		case 1:
			if(mousePositions.size() == 1) {
				gc.strokeLine(mousePositions.get(0).getX(), mousePositions.get(0).getY(), position.getX(), position.getY());
				mousePositions.clear();
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
			// TODO erro necessario selecionar um desenho
		}
	}
}
