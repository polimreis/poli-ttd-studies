package testes;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import mock.ArmazenamentoMock;
import model.Armazenamento;
import model.Placar;

public class PlacarTeste {
	Armazenamento a = new ArmazenamentoMock("mock");
	Placar p = new Placar(a);
	@Test
	public void registraEgetPontosTest() {		
		p.registraTipoPonto("Vicente", 10, "estrela");
		p.registraTipoPonto("Vicente", 10, "moeda");
		p.registraTipoPonto("Vicente", 10, "estrela");
		assertTrue(p.getPontos("Vicente").get("estrela").equals(20));
		assertTrue(p.getPontos("Vicente").get("moeda").equals(10));
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
				assertEquals("Raissa", rank.getKey());
				assertTrue(rank.getValue().equals(11));
			}
			if (i == 2){
				assertEquals("Vicente", rank.getKey());
				assertTrue(rank.getValue().equals(10));
			}
			if (i == 3){
				assertEquals("Simone", rank.getKey());
				assertTrue(rank.getValue().equals(6));
			}
			i++;
		}
		
	}

}