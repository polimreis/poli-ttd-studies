package test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class QuebraCamelCaseTest {

	QuebraCamelCase qcc;

	@Before
	public void setUp() throws Exception {
		qcc = new QuebraCamelCase();
	}

	@Test
	public void singleNameMintest() {

		String singleNameMin = "name";
		List<String> listaStrings = qcc.converterCamelCase(singleNameMin);
		assertEquals("name", listaStrings.get(0));
	}

	@Test
	public void singleNameMaitest() {
		String singleNameMai = "Name";
		List<String> listaStrings = qcc.converterCamelCase(singleNameMai);
		assertEquals("name", listaStrings.get(0));
	}

	@Test
	public void compostNameMintest() {
		String compostNameMin = "nomeComposto";
		List<String> listaStrings = qcc.converterCamelCase(compostNameMin);
		assertEquals("nome", listaStrings.get(0));
		assertEquals("composto", listaStrings.get(1));
	}
	
	@Test
	public void compostNameMaitest() {
		String compostNameMin = "NomeComposto";
		List<String> listaStrings = qcc.converterCamelCase(compostNameMin);
		assertEquals("nome", listaStrings.get(0));
		assertEquals("composto", listaStrings.get(1));
	}
	
    @Test
	public void CPFtest() {
		String entrada = "CPF";
		List<String> listaStrings = qcc.converterCamelCase(entrada);
		assertEquals("CPF", listaStrings.get(0));
	}
	
    @Test
	public void numeroCPFtest() {
		String entrada = "numeroCPF";
		List<String> listaStrings = qcc.converterCamelCase(entrada);
		assertEquals("numero", listaStrings.get(0));
		assertEquals("CPF", listaStrings.get(1));
	}
	
    @Test
	public void numeroCPFContribuintetest() {
		String entrada = "numeroCPFContribuinte";
		List<String> listaStrings = qcc.converterCamelCase(entrada);
		assertEquals("numero", listaStrings.get(0));
		assertEquals("CPF", listaStrings.get(1));
		assertEquals("contribuinte", listaStrings.get(2));
	}
    @Test
	public void recupera10Primeirostest() {
		String entrada = "recupera10Primeiros";
		List<String> listaStrings = qcc.converterCamelCase(entrada);
		assertEquals("recupera", listaStrings.get(0));
		assertEquals("10", listaStrings.get(1));
		assertEquals("primeiros", listaStrings.get(2));
	}
    
    @Test(expected=FormatInvalidException.class)
  	public void invalid10Primeirostest() {
  		String invalid10Primeiros = "10Primeiros";
  		List<String> listaStrings = qcc.converterCamelCase(invalid10Primeiros);
  	}
    
    @Test(expected=FormatInvalidException.class)
  	public void invalidCaracterEspecialtest() {
  		String entradaInvalida = "nome#Composto";
  		List<String> listaStrings = qcc.converterCamelCase(entradaInvalida);
  	}
    
   


}
