package poliana.com.br.gamificacao;

import java.io.IOException;
import java.util.List;

import poliana.com.br.gamificacao.pontuacao.TipoDePontoEnum;

public interface ArmazenamentoInterface {

	public void armazenaPontosParaUsuario(String nomeUsuario, int qtd, TipoDePontoEnum tipoPonto) throws IOException ;

	public int recuperarPontosDeUmTipoPorUsuario(String nomeUsuario, TipoDePontoEnum tipoPonto) throws IOException ;

	public String recuperaListaUsuariosComPontos() ;

	public List<TipoDePontoEnum> recuperarTodosTiposPontosDoUsuario(String nomeUsuario) throws IOException ;
	
}
