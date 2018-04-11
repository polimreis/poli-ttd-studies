package caixa.eletronico.interfaces;

import caixa.eletronico.exception.FalhaHardwareException;

public interface Hardware {

	public String pegarNumeroDaContaCartao() throws FalhaHardwareException ;
	
	public void  entregarDinheiro() throws FalhaHardwareException ;
	
	public void lerEnvelope() throws FalhaHardwareException ;
	
}
