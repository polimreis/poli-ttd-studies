package poliana.com.br.gamificacao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import poliana.com.br.gamificacao.pontuacao.TipoDePontoEnum;

public class ArmazenamentoTest {

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
	}

    @Test
	public void armazenaPontosParaUsuarioTest() throws IOException {
		String nomeUsuario = "POLIANA";
		String nomeArquivo = "/" + nomeUsuario.toUpperCase() + ".txt";
		apagarArquivo(nomeArquivo);
		
		int qtd = 5;
		armazenamento.armazenaPontosParaUsuario(nomeUsuario, qtd, TipoDePontoEnum.COMENTARIO);

		String dadosDoArquivo = lerArquivo("/" + nomeUsuario.toUpperCase() + ".txt");
		assertTrue( dadosDoArquivo.contains("COMENTARIO 5"));

	}

	@Test
	public void recuperarPontosDeUmTipoPorUsuarioTest() throws IOException {
		String nomeUsuario = "VITOR";
		String nomeArquivo = "/" + nomeUsuario.toUpperCase() + ".txt";
		apagarArquivo(nomeArquivo);
		
		int qtd = 10;
		armazenamento.armazenaPontosParaUsuario(nomeUsuario, qtd, TipoDePontoEnum.ESTRELA);
		
		int qtdRetornada = armazenamento.recuperarPontosDeUmTipoPorUsuario(nomeUsuario, TipoDePontoEnum.ESTRELA);
		assertEquals(qtd, qtdRetornada);
	}
	
    @Test
	public void recuperarPontosDeUmTipoRepetidoPorUsuarioTest() throws IOException {
		String nomeUsuario = "VITOR";
		
		String nomeArquivo = "/" + nomeUsuario.toUpperCase() + ".txt";
		apagarArquivo(nomeArquivo);
		
		int qtd1 = 10;
		armazenamento.armazenaPontosParaUsuario(nomeUsuario, qtd1, TipoDePontoEnum.ESTRELA);
		
		int qtd2 = 8;
		armazenamento.armazenaPontosParaUsuario(nomeUsuario, qtd2, TipoDePontoEnum.MOEDA);
		
		int qtd3 = 8;
		armazenamento.armazenaPontosParaUsuario(nomeUsuario, qtd3, TipoDePontoEnum.ESTRELA);
		
		
		int qtdRetornada = armazenamento.recuperarPontosDeUmTipoPorUsuario(nomeUsuario, TipoDePontoEnum.ESTRELA);
		assertEquals(qtd1+qtd3, qtdRetornada);
	}
	
    @Test
	public void recuperarPontosDeUmTipoInesistentePorUsuarioTest() throws IOException {
		String nomeUsuario = "VITOR";
		
		String nomeArquivo = "/" + nomeUsuario.toUpperCase() + ".txt";
		apagarArquivo(nomeArquivo);
		
		int qtd1 = 10;
		armazenamento.armazenaPontosParaUsuario(nomeUsuario, qtd1, TipoDePontoEnum.ESTRELA);
		
		int qtd2 = 8;
		armazenamento.armazenaPontosParaUsuario(nomeUsuario, qtd2, TipoDePontoEnum.MOEDA);
		
		int qtdRetornada = armazenamento.recuperarPontosDeUmTipoPorUsuario(nomeUsuario, TipoDePontoEnum.TOPICO);
		assertEquals(0, qtdRetornada);
	}
	
	
   
    @Test
	public void recuperaListaUsuariosComPontosTest() throws IOException {
        String nomeUsuario1 = "VITOR";
		String nomeArquivo1 = "/" + nomeUsuario1.toUpperCase() + ".txt";
		apagarArquivo(nomeArquivo1);
		
		int qtd1 = 10;
		armazenamento.armazenaPontosParaUsuario(nomeUsuario1, qtd1, TipoDePontoEnum.ESTRELA);
		
		String nomeUsuario2 = "POLIANA";
		String nomeArquivo2 = "/" + nomeUsuario2.toUpperCase() + ".txt";
		apagarArquivo(nomeArquivo2);
		int qtd2 = 5;
		armazenamento.armazenaPontosParaUsuario(nomeUsuario2, qtd2, TipoDePontoEnum.COMENTARIO);

		String listaUsuarios = armazenamento.recuperaListaUsuariosComPontos();
		
		assertTrue(listaUsuarios.contains(nomeUsuario1));
		assertTrue(listaUsuarios.contains(nomeUsuario2));
    }
		

    @Test
	public void recuperarTodosTiposPontosDoUsuarioTest() throws IOException {
        String nomeUsuario = "VITOR";
		
		String nomeArquivo = "/" + nomeUsuario.toUpperCase() + ".txt";
		apagarArquivo(nomeArquivo);
		
		int qtd1 = 10;
		armazenamento.armazenaPontosParaUsuario(nomeUsuario, qtd1, TipoDePontoEnum.ESTRELA);
		
		int qtd2 = 8;
		armazenamento.armazenaPontosParaUsuario(nomeUsuario, qtd2, TipoDePontoEnum.MOEDA);
		
		int qtd3 = 8;
		armazenamento.armazenaPontosParaUsuario(nomeUsuario, qtd3, TipoDePontoEnum.TOPICO);
		
		
		List<TipoDePontoEnum> listaUsuarios = armazenamento.recuperarTodosTiposPontosDoUsuario(nomeUsuario);
		assertTrue(listaUsuarios.contains(TipoDePontoEnum.ESTRELA));
		assertTrue(listaUsuarios.contains(TipoDePontoEnum.MOEDA));
		assertTrue(listaUsuarios.contains(TipoDePontoEnum.TOPICO));
	
	}

	private void apagarArquivo(String nomeArquivo) throws IOException {
		File file = new File(pathTest + nomeArquivo);
		Files.deleteIfExists(file.toPath()) ;
	}
	
	private String lerArquivo(String nomeArquivo) throws IOException {
		File file = new File(pathTest + nomeArquivo);
		String dados = new String(Files.readAllBytes(file.toPath()));
		return dados;
	}

}
