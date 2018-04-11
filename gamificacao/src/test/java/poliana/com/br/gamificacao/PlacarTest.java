package poliana.com.br.gamificacao;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import poliana.com.br.gamificacao.mock.ArmazenamentoMock;
import poliana.com.br.gamificacao.pontuacao.TipoDePontoEnum;

public class PlacarTest {
	
	Placar placar ;
	ArmazenamentoInterface armazenamento ;
    
	@Before
	public void setUp() throws IOException {
		armazenamento = new ArmazenamentoMock();
		placar = new Placar(armazenamento);
	}
    

	@Test
	public void registrarPontosParaUsuarioTest() throws IOException {
		String nomeUsuario = "POLIANA";
		int qtd = 10 ; 
		placar.registrarPontosParaUsuario(nomeUsuario, qtd, TipoDePontoEnum.CURTIDA);
	}
	
	@Test
	public void recuperarTodosPontosDoUsuarioTest() throws IOException {
		String nomeUsuario = "POLIANA";
		String todosPontosUsuario = placar.recuperarTodosPontosDoUsuario(nomeUsuario);
		assertTrue(todosPontosUsuario.contains("COMENTARIO 10"));
		assertTrue(todosPontosUsuario.contains("CURTIDA 10"));
		assertTrue(todosPontosUsuario.contains("TOPICO 10"));
	}	

	@Test	
	public void retornarRankingDeUmTipoDePontoTest() throws IOException {
		String hanking = placar.retornarRankingDeUmTipoDePonto(TipoDePontoEnum.COMENTARIO);
		assertTrue(hanking.contains("POLIANA 10  VITOR 10"));
	}

}
