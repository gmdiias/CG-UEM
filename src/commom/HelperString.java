package commom;

public class HelperString {
	private static String helperString;
	
	public static String getStringHelper() {
		StringBuilder helpStr = new StringBuilder();
		helpStr.append("Olá, vimos que você está precisando de ajuda, então vamos lá ...");
		helpStr.append(System.lineSeparator());
		helpStr.append(System.lineSeparator());
		helpStr.append("* Como desenhar objetos ??");
		helpStr.append(System.lineSeparator());
		helpStr.append(
				"Para desenhar objetos é preciso selecionar a forma do objeto que deseja desenhar e selecionar no canvas a quantidade de     ");
		helpStr.append(System.lineSeparator());
		helpStr.append(
				"pontos necessárias para desenha-lo, para saber a quantidade basta selecionar o objeto e sistema irá lhe avisar no console.");
		helpStr.append(System.lineSeparator());
		helpStr.append(System.lineSeparator());
		helpStr.append("* Como aplicar transformações ??");
		helpStr.append(System.lineSeparator());
		helpStr.append(
				"Para aplicar as transformações é necessário selecionar os objetos, escolher a transformação desejada e informar os pontos sobre");
		helpStr.append(System.lineSeparator());
		helpStr.append("que deseja realizar as transformações.");
		helpStr.append(System.lineSeparator());
		helpStr.append(System.lineSeparator());
		helpStr.append("* Como selecionar objetos ??");
		helpStr.append(System.lineSeparator());
		helpStr.append(
				"Para selecionar objetos é necessário escolher o objeto através da lista disponivel no ponto superior direito, abrindo a lista e");
		helpStr.append(System.lineSeparator());
		helpStr.append(
				"escolhendo o objeto desejado, caso deseja selecionar todos os objetos é necessário marcar a seleção \"Selecionar todos objetos\". ");
		helpStr.append(System.lineSeparator());
		
		return helpStr.toString();
	}
}
