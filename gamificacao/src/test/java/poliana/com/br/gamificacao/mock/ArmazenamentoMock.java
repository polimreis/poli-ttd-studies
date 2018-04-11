package poliana.com.br.gamificacao.mock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import poliana.com.br.gamificacao.ArmazenamentoInterface;
import poliana.com.br.gamificacao.pontuacao.TipoDePontoEnum;

public class ArmazenamentoMock implements ArmazenamentoInterface {

	public void armazenaPontosParaUsuario(String nomeUsuario, int qtd, TipoDePontoEnum tipoPonto) throws IOException {

	}

	public int recuperarPontosDeUmTipoPorUsuario(String nomeUsuario, TipoDePontoEnum tipoPonto) throws IOException {
		return 10;
	}

	public String recuperaListaUsuariosComPontos() {
		return "POLIANA VITOR";
	}

	public List<TipoDePontoEnum> recuperarTodosTiposPontosDoUsuario(String nomeUsuario) throws IOException {

		List<TipoDePontoEnum> listaTiposPontos = new ArrayList<TipoDePontoEnum>();

		listaTiposPontos.add(TipoDePontoEnum.COMENTARIO);
		listaTiposPontos.add(TipoDePontoEnum.CURTIDA);
		listaTiposPontos.add(TipoDePontoEnum.TOPICO);

		return listaTiposPontos;

	}

}
