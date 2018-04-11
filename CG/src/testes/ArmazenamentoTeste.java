package testes;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import model.Armazenamento;
import model.Usuario;

public class ArmazenamentoTeste {
	Armazenamento arm =new Armazenamento("teste");
	Armazenamento arm2 = new Armazenamento("arquivo2");
	@Before
	public void inicia(){
		arm.limpaArquivo();
		arm.armazenaPontoTipoUsuario("estrela",5,"Guerra");
		arm.armazenaPontoTipoUsuario("estrela",2,"Vicente");
		arm.armazenaPontoTipoUsuario("moeda",30,"Guerra");
		arm.armazenaPontoTipoUsuario("estrela",10,"Guerra");
		arm.armazenaPontoTipoUsuario("estrela",2,"Vicente");
		arm.armazenaPontoTipoUsuario("curtir",7,"lucio");
		
		arm2.limpaArquivo();
		arm2.armazenaPontoTipoUsuario("curtida",5,"Simone");
		arm2.armazenaPontoTipoUsuario("curtida",2,"Valerio");
		arm2.armazenaPontoTipoUsuario("comentario",30,"Simone");
		arm2.armazenaPontoTipoUsuario("compartilhamento",10,"Simone");
		arm2.armazenaPontoTipoUsuario("comentario",2,"Valerio");
		arm2.armazenaPontoTipoUsuario("curtida",7,"Raissa");	
	}

	@Test
	public void recuperaPontosTipoUsuarioTeste(){
		assertEquals(15,arm.recuperaPontosTipoUsuario("estrela","Guerra"));
		assertEquals(30,arm.recuperaPontosTipoUsuario("moeda","Guerra"));
		assertEquals(4,arm.recuperaPontosTipoUsuario("estrela","Vicente"));
	}

	@Test
	public void recuperaUsuarioPontoTeste(){
		Set<String> retorno = arm.recuperaUsuariosComPontos();
		assertTrue(retorno.stream().anyMatch(nome -> nome.equals("Vicente")));
		assertTrue(retorno.stream().anyMatch(nome -> nome.equals("Guerra")));
		assertTrue(retorno.stream().anyMatch(nome -> nome.equals("lucio")));
	}

	@Test
	public void recuperaTiposPontoUsuarioTeste(){
		Set<String> retorno = arm.recuperaTiposPontos();
		assertTrue(retorno.stream().anyMatch(usuario -> usuario.equals("moeda")));
		assertTrue(retorno.stream().anyMatch(usuario -> usuario.equals("estrela")));
		assertTrue(retorno.stream().anyMatch(usuario -> usuario.equals("curtir")));
	}
	@Test
	public void recuperaPontosTipoUsuarioTesteArquivo2(){
		assertEquals(7,arm2.recuperaPontosTipoUsuario("curtida","Raissa"));
		assertEquals(10,arm2.recuperaPontosTipoUsuario("compartilhamento","Simone"));
		assertEquals(2,arm2.recuperaPontosTipoUsuario("comentario","Valerio"));
	}

	@Test
	public void recuperaUsuarioPontoTesteArquivo2(){
		Set<String> retorno = arm2.recuperaUsuariosComPontos();
		assertTrue(retorno.stream().anyMatch(nome -> nome.equals("Simone")));
		assertTrue(retorno.stream().anyMatch(nome -> nome.equals("Valerio")));
		assertTrue(retorno.stream().anyMatch(nome -> nome.equals("Raissa")));
	}

	@Test
	public void recuperaTiposPontoUsuarioTesteArquivo2(){
		Set<String> retorno = arm2.recuperaTiposPontos();
		assertTrue(retorno.stream().anyMatch(usuario -> usuario.equals("curtida")));
		assertTrue(retorno.stream().anyMatch(usuario -> usuario.equals("compartilhamento")));
		assertTrue(retorno.stream().anyMatch(usuario -> usuario.equals("comentario")));
	}

}
