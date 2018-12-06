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
import commom.HelperString;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
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

	private double valorZoom = 1;

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
	private CheckBox allObjects;

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

		if (box.getSelectionModel().getSelectedItem() == null) {
			return;
		}

		objeto = box.getSelectionModel().getSelectedItem();
		gc.setStroke(Color.RED);
		objeto.desenharObjeto(gc);
		gc.setStroke(Color.BLACK);
	}

	@FXML
	private void onSelectAllObject() {
		desenhoSelected = 0;

		if (allObjects.isSelected()) {
			gc.setStroke(Color.RED);
			redesenha();
			gc.setStroke(Color.BLACK);
		} else {
			gc.setStroke(Color.BLACK);
			redesenha();
			gc.setStroke(Color.BLACK);
			allObjects.setSelected(false);
		}
	}

	@FXML
	private void onMudancaEscalaSelect() {
		if(!verificaSelectedObject())
			return;
		
		String[] pontoReceived = obtemPosicoes("Sobre qual posição deseja realizar a mudança de escala ??"
				+ System.lineSeparator() + " Ex: 0;0 para realizar sobre a posição inicial.");
		if(!validaEntradaDados(2, pontoReceived))
			return;
		
		double transladaX = Double.parseDouble(pontoReceived[0]);
		double transladaY = Double.parseDouble(pontoReceived[1]);

		String[] positionsReceived = obtemPosicoes(
				"Qual o valor de x e y que deseja aplicar a transformação de mudança de escala ??"
						+ System.lineSeparator() + " Ex: 2;2 para dobrar o tamanho do objeto");
		if(!validaEntradaDados(2, positionsReceived))
			return;

		double deslocX = Double.parseDouble(positionsReceived[0]);
		double deslocY = Double.parseDouble(positionsReceived[1]);

		if (allObjects.isSelected()) {
			for (ObjetoGrafico obj : box.getItems()) {
				obj.getPontos().forEach(dado -> {
					dado.setX(dado.getX() - transladaX);
					dado.setY(dado.getY() - transladaY);
					dado.setX(dado.getX() * deslocX);
					dado.setY(dado.getY() * deslocY);
					dado.setX(dado.getX() + transladaX);
					dado.setY(dado.getY() + transladaY);
				});
			}
			allObjects.setSelected(false);
		} else {
			objeto.getPontos().forEach(dado -> {
				dado.setX(dado.getX() - transladaX);
				dado.setY(dado.getY() - transladaY);
				dado.setX(dado.getX() * deslocX);
				dado.setY(dado.getY() * deslocY);
				dado.setX(dado.getX() + transladaX);
				dado.setY(dado.getY() + transladaY);
			});
		}

		redesenha();
	}

	@FXML
	private void onRotacaoSelect() {
		if(!verificaSelectedObject())
			return;
		
		String[] pontoReceived = obtemPosicoes("Sobre qual posição deseja realizar a mudança de rotação ??"
				+ System.lineSeparator() + " Ex: 0;0 para realizar sobre a posição inicial.");
		
		if(!validaEntradaDados(2, pontoReceived))
			return;
		
		double transladaX = Double.parseDouble(pontoReceived[0]);
		double transladaY = Double.parseDouble(pontoReceived[1]);

		String[] grausReceived = obtemPosicoes(
				"Qual o angulo de rotacao desejado ??" + System.lineSeparator() + " Ex: 20 para rotacionar 20 graus.");
		
		if(!validaEntradaDados(1, grausReceived))
			return;
		
		double graus = Double.parseDouble(grausReceived[0]);

		double angulo = Math.PI * graus / 180;
		if (allObjects.isSelected()) {
			for (ObjetoGrafico obj : box.getItems()) {
				obj.getPontos().forEach(dado -> {
					dado.setX(dado.getX() - transladaX);
					dado.setY(dado.getY() - transladaY);
					dado.setX((Math.cos(angulo) * dado.getX()) + (-Math.sin(angulo) * dado.getY()));
					dado.setY((Math.sin(angulo) * dado.getX()) + (Math.cos(angulo) * dado.getY()));
					dado.setX(dado.getX() + transladaX);
					dado.setY(dado.getY() + transladaY);
				});
			}
			allObjects.setSelected(false);
		} else {
			objeto.getPontos().forEach(dado -> {
				dado.setX(dado.getX() - transladaX);
				dado.setY(dado.getY() - transladaY);
				dado.setX((Math.cos(angulo) * dado.getX()) + (-Math.sin(angulo) * dado.getY()));
				dado.setY((Math.sin(angulo) * dado.getX()) + (Math.cos(angulo) * dado.getY()));
				dado.setX(dado.getX() + transladaX);
				dado.setY(dado.getY() + transladaY);
			});
		}

		redesenha();
	}

	@FXML
	private void onTransacaoSelect() {
		if(!verificaSelectedObject())
			return;
		
		String[] positionsReceived = obtemPosicoes(
				"Qual o valor de x e y que deseja aplicar a transformação de translação ??" + System.lineSeparator()
						+ " Ex: 50;20 para deslocar o objeto 50 posições em x e 20 em y;");
		
		if(!validaEntradaDados(2, positionsReceived))
			return;
		
		double deslocX = Double.parseDouble(positionsReceived[0]);
		double deslocY = Double.parseDouble(positionsReceived[1]);

		if (allObjects.isSelected()) {
			for (ObjetoGrafico obj : box.getItems()) {
				obj.getPontos().forEach(dado -> {
					dado.setX(dado.getX() + deslocX);
					dado.setY(dado.getY() + deslocY);
				});
			}
			allObjects.setSelected(false);
		} else {
			objeto.getPontos().forEach(dado -> {
				dado.setX(dado.getX() + deslocX);
				dado.setY(dado.getY() + deslocY);
			});
		}
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
		MousePosition newPosition = new MousePosition(mouseEvent.getX() / valorZoom, mouseEvent.getY() / valorZoom);
		desenhaSelecionado(newPosition);
	}

	@FXML
	private void onZoomSelect() {
		String[] positionsReceived = obtemPosicoes("Informe os valores desejados para zoom ??" + System.lineSeparator()
				+ " Ex: 2 para dar zoom 2x (1 para voltar ao normal)");
		double scala = Double.parseDouble(positionsReceived[0]);

		gc.scale(1 / valorZoom, 1 / valorZoom);
		gc.scale(scala, scala);
		valorZoom = scala;
		redesenha();
	}

	@FXML
	private void onHelpSelected() {
		JFrame frame = new JFrame("Ajuda");
		JOptionPane.showMessageDialog(frame, HelperString.getStringHelper(), "Ajuda", 1);
	}

	private void redesenha() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		box.getItems().forEach(dado -> dado.desenharObjeto(gc));
	}

	private void desenhaSelecionado(MousePosition position) {
		redesenha();
		switch (desenhoSelected) {
		case 1:
			objeto.adicionaPonto(position.getX(), position.getY());
			if (objeto.desenharObjeto(gc)) {
				box.getItems().add(objeto);
				objeto = new Linha();
			}

			break;

		case 2:
			objeto.adicionaPonto(position.getX(), position.getY());
			if (objeto.desenharObjeto(gc)) {
				box.getItems().add(objeto);
				objeto = new Triangulo();
			}
			break;

		case 3:
			objeto.adicionaPonto(position.getX(), position.getY());
			if (objeto.desenharObjeto(gc)) {
				box.getItems().add(objeto);
				objeto = new Quadrado();
			}
			break;

		case 4:
			objeto.adicionaPonto(position.getX(), position.getY());
			if (objeto.desenharObjeto(gc)) {
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
		JFrame frame = new JFrame("Realizando transformação");
		String receivedPositions = JOptionPane.showInputDialog(frame, msg);

		return receivedPositions.split(";");
	}
	
	private boolean verificaSelectedObject() {
		if(box.getSelectionModel().getSelectedItem() == null && !allObjects.isSelected()) {
			JFrame frame = new JFrame("Nenhum objeto selecionado");
			JOptionPane.showMessageDialog(frame, "Não foi selecionado nenhum objeto para realizar a transformação!" + 
			System.lineSeparator() + "Selecione um objeto e tente novamente.", "Nenhum objeto selecionado", 1);
			return false;
		}
		return true;
	}
	
	private boolean validaEntradaDados(int esperado, String[] obtido) {
		if(obtido.length < esperado) {
			JFrame frame = new JFrame("Entrada inválida");
			JOptionPane.showMessageDialog(frame, "Formato dos dados informados inválidos.", "Entrada inválida", 1);
			return false;
		}
		return true;
	}

}
