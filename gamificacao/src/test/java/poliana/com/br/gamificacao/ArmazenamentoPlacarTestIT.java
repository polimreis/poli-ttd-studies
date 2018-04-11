package poliana.com.br.gamificacao;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.Before;
import org.junit.Test;

import poliana.com.br.gamificacao.pontuacao.TipoDePontoEnum;

public class ArmazenamentoPlacarTestIT {

	Placar placar ;

	private Armazenamento armazenamento;
	private String pathTest = "./teste";
	private static File diretorio;

	@Before
	public void setUp() throws IOException {
		diretorio = new File(pathTest);
		
		boolean existe = diretorio.exists();

		if (!existe) {
			diretorio.mkdir();
		}

		armazenamento = new Armazenamento(pathTest);
		placar = new Placar(armazenamento);
	}
    
	@Test
	public void registrarPontosParaUsuarioTest() throws IOException {
		String nomeUsuario = "POLIANA";
		String nomeArquivo = "/" + nomeUsuario.toUpperCase() + ".txt";
		apagarArquivo(nomeArquivo);
		
		int qtd = 5;
		placar.registrarPontosParaUsuario(nomeUsuario, qtd, TipoDePontoEnum.CURTIDA);
		
		String dadosDoArquivo = lerArquivo("/" + nomeUsuario.toUpperCase() + ".txt");
		assertTrue( dadosDoArquivo.contains("CURTIDA 5"));
		
	}
	
	@Test
	public void recuperarTodosPontosDoUsuarioTest() throws IOException {
		String nomeUsuario = "VITOR";
		
		String nomeArquivo = "/" + nomeUsuario.toUpperCase() + ".txt";
		apagarArquivo(nomeArquivo);
		
		int qtd1 = 10;
		placar.registrarPontosParaUsuario(nomeUsuario, qtd1, TipoDePontoEnum.ESTRELA);
		
		int qtd2 = 8;
		placar.registrarPontosParaUsuario(nomeUsuario, qtd2, TipoDePontoEnum.MOEDA);
		
		int qtd3 = 5;
		placar.registrarPontosParaUsuario(nomeUsuario, qtd3, TipoDePontoEnum.TOPICO);
		
		
		String todosPontosUsuario = placar.recuperarTodosPontosDoUsuario(nomeUsuario);
		assertTrue(todosPontosUsuario.contains("ESTRELA 10"));
		assertTrue(todosPontosUsuario.contains("MOEDA 8"));
		assertTrue(todosPontosUsuario.contains("TOPICO 5"));
	}	


	@Test
	public void retornarRankingDeUmTipoDePontoTest() throws IOException {
		String nomeUsuario = "POLIANA";
		String nomeArquivo = "/" + nomeUsuario.toUpperCase() + ".txt";
		apagarArquivo(nomeArquivo);
		int qtd = 8;
		placar.registrarPontosParaUsuario(nomeUsuario, qtd, TipoDePontoEnum.CURTIDA);
		
		String nomeUsuario2 = "VITOR";
		String nomeArquivo2 = "/" + nomeUsuario2.toUpperCase() + ".txt";
		apagarArquivo(nomeArquivo2);
		int qtd2 = 5;
		placar.registrarPontosParaUsuario(nomeUsuario2, qtd2, TipoDePontoEnum.CURTIDA);

		String nomeUsuario3 = "JOSE";
		String nomeArquivo3 = "/" + nomeUsuario3.toUpperCase() + ".txt";
		apagarArquivo(nomeArquivo3);
		int qtd3 = 10;
		placar.registrarPontosParaUsuario(nomeUsuario3, qtd3, TipoDePontoEnum.CURTIDA);
		
		String hanking = placar.retornarRankingDeUmTipoDePonto(TipoDePontoEnum.CURTIDA);
		assertTrue(hanking.contains("JOSE 10  POLIANA 8  VITOR 5"));
	}
	
	
	
	
	private String lerArquivo(String nomeArquivo) throws IOException {
		File file = new File(pathTest + nomeArquivo);
		String dados = new String(Files.readAllBytes(file.toPath()));
		return dados;
	}
	
	private void apagarArquivo(String nomeArquivo) throws IOException {
		File file = new File(pathTest + nomeArquivo);
		Files.deleteIfExists(file.toPath()) ;
	}

}
