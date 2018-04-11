package poliana.com.br.gamificacao;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import poliana.com.br.gamificacao.pontuacao.TipoDePontoEnum;

public class Placar {

	ArmazenamentoInterface armazenamento;

	public Placar(ArmazenamentoInterface armazenamento) {
		this.armazenamento = armazenamento;
	}

	public void registrarPontosParaUsuario(String nomeUsuario, int qtd, TipoDePontoEnum tipoPonto) throws IOException {
		armazenamento.armazenaPontosParaUsuario(nomeUsuario, qtd, tipoPonto);
	}

	public String recuperarTodosPontosDoUsuario(String nomeUsuario) throws IOException {
		List<TipoDePontoEnum> listaTiposPontos = armazenamento.recuperarTodosTiposPontosDoUsuario(nomeUsuario);

		String pontosPorUsuario = "";

		for (TipoDePontoEnum type : listaTiposPontos) {
			pontosPorUsuario += type.toString() + " "
					+ armazenamento.recuperarPontosDeUmTipoPorUsuario(nomeUsuario, type) + " , ";
		}

		return pontosPorUsuario;
	}

	public String retornarRankingDeUmTipoDePonto(TipoDePontoEnum tipoPonto) throws IOException {

		String listaUsuarioStr = armazenamento.recuperaListaUsuariosComPontos();
		List<String> listaUsuario = Arrays.asList(listaUsuarioStr.trim().split(" "));
		Map<String, Integer> mapaUsuarioQuantidade = new LinkedHashMap<String, Integer>();

		for (String nomeUsuario : listaUsuario) {
			if (!nomeUsuario.isEmpty()) {
				int qtd = armazenamento.recuperarPontosDeUmTipoPorUsuario(nomeUsuario, tipoPonto);
				mapaUsuarioQuantidade.put(nomeUsuario, qtd);
			}
		}

		return printMap(sortByValue(mapaUsuarioQuantidade));
	}

	private static Map<String, Integer> sortByValue(Map<String, Integer> unsortMap) {
		List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});

		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for (Map.Entry<String, Integer> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}

		return sortedMap;
	}

	private static <K, V> String printMap(Map<K, V> map) {
		String listaOrdenada = "";
		for (Map.Entry<K, V> entry : map.entrySet()) {
			listaOrdenada += "  " + entry.getKey() + " " + entry.getValue();
		}
		return listaOrdenada;
	}

}
