package caixa.eletronico.interfaces;

import caixa.eletronico.ContaCorrente;

public interface ServicoRemoto {
	 
	public ContaCorrente recuperarConta(String nrCC);
	
	public void persistirConta(ContaCorrente cc);
	
	public String pegarSenhaDaConta(String numeroConta) ; 

}
