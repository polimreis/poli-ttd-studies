package testes;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;

import model.Armazenamento;
import model.Placar;

public class PlacarArmazenamentoIT {

	Armazenamento arm =new Armazenamento("integracao");
	Placar p = new Placar(arm);
	@Before
	public void inicia(){
		arm.limpaArquivo();
		p.registraTipoPonto("Vicente", 10, "estrela");
		p.registraTipoPonto("Vicente", 10, "moeda");
		p.registraTipoPonto("Vicente", 10, "estrela");			
	}

	@Test
	public void recuperaPontosTipoUsuarioTeste(){
		assertEquals(20,arm.recuperaPontosTipoUsuario("estrela","Vicente"));
		assertEquals(10,arm.recuperaPontosTipoUsuario("moeda","Vicente"));
	}
	
	@Test
	public void rankTest() {		
		p.registraTipoPonto("Vicente", 10, "estrela");
		p.registraTipoPonto("Carlos", 7, "moeda");
		p.registraTipoPonto("Simone", 6, "estrela");
		p.registraTipoPonto("Raissa", 11, "estrela");
		Map<String, Integer> ranks = p.getRanking("estrela");
		assertTrue(!ranks.isEmpty());
		int i = 1;
		for(Entry<String, Integer> rank: ranks.entrySet()){
			if (i == 1){
				assertEquals("Vicente", rank.getKey());
				assertTrue(rank.getValue().equals(30));
			}
			if (i == 2){
				assertEquals("Raissa", rank.getKey());
				assertTrue(rank.getValue().equals(11));
			}
			if (i == 3){
				assertEquals("Simone", rank.getKey());
				assertTrue(rank.getValue().equals(6));
			}
			i++;
		}
		
	}	
}
