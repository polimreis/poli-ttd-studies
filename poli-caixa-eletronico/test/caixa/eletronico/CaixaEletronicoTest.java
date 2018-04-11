package caixa.eletronico;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import caixa.eletronico.CaixaEletronico;
import test.mock.HardwareMock;
import test.mock.ServicoRemotoMock;

public class CaixaEletronicoTest {
	
	CaixaEletronico ce;

	 HardwareMock hardwareMock  = new HardwareMock();
     ServicoRemotoMock servicoRemotoMock = new ServicoRemotoMock() ;
     
	@Before
	public void setUp() {
		ce = new CaixaEletronico(hardwareMock, servicoRemotoMock);
	}


     
	@Test
	public void logarSucessoTest() {
		String msg = ce.logar( "abc123");
		assertEquals("Usuário Autenticado", msg);
	}

	@Test
	public void logarIncorretoTest() {
		String msg = ce.logar("123123");
		assertEquals("Não foi possível autenticar o usuário", msg);
	}

	@Test
	public void logarFalhaTest() {
		hardwareMock.setSimularFalhaDeHardware(true);
		String msg = ce.logar("123123");
		assertEquals("falha de funcionamento do hardware.", msg);
	}

	@Test
	public void sacarComSucessoTest() {
		String msg = ce.sacar(100);
		assertEquals("Retire seu dinheiro", msg);
	}

	@Test
	public void sacarSemSucessoTest() {
		String msg = ce.sacar(500);
		assertEquals("Saldo insuficiente", msg);
	}
	
	@Test
	public void sacarFalhaTest() {
		hardwareMock.setSimularFalhaDeHardware(true);
		String msg = ce.sacar(100);
		assertEquals("falha de funcionamento do hardware.", msg);
	}


	@Test
	public void depositarTest() {
		String msg = ce.depositar(50);
		assertEquals("Depósito recebido com sucesso", msg);
	}
	
	@Test
	public void depositarFalhaTest() {
		hardwareMock.setSimularFalhaDeHardware(true);
		String msg = ce.depositar(50);
		assertEquals("falha de funcionamento do hardware.", msg);
	}

	@Test
	public void saldoTest() {
		String msg = ce.saldo();
		assertEquals("O saldo é R$200.00", msg);
	}
}
