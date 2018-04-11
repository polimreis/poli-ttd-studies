package test.mock;

import java.util.HashMap;
import java.util.Map;

import caixa.eletronico.ContaCorrente;
import caixa.eletronico.interfaces.ServicoRemoto;

public class ServicoRemotoMock implements ServicoRemoto {
	
	ContaCorrente cc ;
	Map<String, String> listaContaESenha = new HashMap<>();

	public ServicoRemotoMock() {
		super();
		cc = new ContaCorrente();
	}

	@Override
	public ContaCorrente recuperarConta(String nrContaAtual) {
		cc.setSaldoAtual(200);
		return cc;
	}
	
	@Override
	public void persistirConta(ContaCorrente cc) {
		this.cc = cc;
	}
	
	public String pegarSenhaDaConta(String numeroConta) {
		return "abc123";
	}

}
