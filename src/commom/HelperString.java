package commom;

public class HelperString {
	private static String helperString;
	
	public static String getStringHelper() {
		StringBuilder helpStr = new StringBuilder();
		helpStr.append("Ol�, vimos que voc� est� precisando de ajuda, ent�o vamos l� ...");
		helpStr.append(System.lineSeparator());
		helpStr.append(System.lineSeparator());
		helpStr.append("* Como desenhar objetos ??");
		helpStr.append(System.lineSeparator());
		helpStr.append(
				"Para desenhar objetos � preciso selecionar a forma do objeto que deseja desenhar e selecionar no canvas a quantidade de     ");
		helpStr.append(System.lineSeparator());
		helpStr.append(
				"pontos necess�rias para desenha-lo, para saber a quantidade basta selecionar o objeto e sistema ir� lhe avisar no console.");
		helpStr.append(System.lineSeparator());
		helpStr.append(System.lineSeparator());
		helpStr.append("* Como aplicar transforma��es ??");
		helpStr.append(System.lineSeparator());
		helpStr.append(
				"Para aplicar as transforma��es � necess�rio selecionar os objetos, escolher a transforma��o desejada e informar os pontos sobre");
		helpStr.append(System.lineSeparator());
		helpStr.append("que deseja realizar as transforma��es.");
		helpStr.append(System.lineSeparator());
		helpStr.append(System.lineSeparator());
		helpStr.append("* Como selecionar objetos ??");
		helpStr.append(System.lineSeparator());
		helpStr.append(
				"Para selecionar objetos � necess�rio escolher o objeto atrav�s da lista disponivel no ponto superior direito, abrindo a lista e");
		helpStr.append(System.lineSeparator());
		helpStr.append(
				"escolhendo o objeto desejado, caso deseja selecionar todos os objetos � necess�rio marcar a sele��o \"Selecionar todos objetos\". ");
		helpStr.append(System.lineSeparator());
		
		return helpStr.toString();
	}
}
