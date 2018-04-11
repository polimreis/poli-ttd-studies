package caixa.eletronico;

import java.text.DecimalFormat;
import java.util.List;

import caixa.eletronico.exception.FalhaHardwareException;
import caixa.eletronico.interfaces.Hardware;
import caixa.eletronico.interfaces.ServicoRemoto;


public class CaixaEletronico {
	
     Hardware hardware ;
     ServicoRemoto servicoRemoto ;
     List <ContaCorrente> listaContaCorrente;
     
     String NrContaAtual;

	public CaixaEletronico(Hardware hardwareMock, ServicoRemoto servicoRemotoMock) {
		super();
		this.hardware = hardwareMock;
		this.servicoRemoto = servicoRemotoMock;
	}

	public String logar(String senhaDigitada) {
		try {
			String nrCC = hardware.pegarNumeroDaContaCartao();
			String senha = servicoRemoto.pegarSenhaDaConta(nrCC);
			if (senha.equals(senhaDigitada)) {
				NrContaAtual = nrCC;
				return "Usuário Autenticado";
			}
		} catch (FalhaHardwareException e) {
			return e.getMessage();
		}
		return "Não foi possível autenticar o usuário";
	}

	public String sacar(long valorParaSacar) {
		ContaCorrente cc = servicoRemoto.recuperarConta(NrContaAtual);
		if (cc!= null && cc.getSaldoAtual() > valorParaSacar) {
			double saldoRestante = cc.getSaldoAtual() - valorParaSacar;
			cc.setSaldoAtual(saldoRestante);
			servicoRemoto.persistirConta(cc);
			try {
				hardware.entregarDinheiro();
			} catch (FalhaHardwareException e) {
				return e.getMessage();
			}
			return "Retire seu dinheiro";
		}
		return "Saldo insuficiente";
	}

	
	public String depositar(long valorParaDepositar) {
		try {
			hardware.lerEnvelope();
			ContaCorrente cc = servicoRemoto.recuperarConta(NrContaAtual);
			double novoSaldo = cc.getSaldoAtual() + valorParaDepositar;
			cc.setSaldoAtual(novoSaldo);
			servicoRemoto.persistirConta(cc);
		} catch (FalhaHardwareException e) {
			return e.getMessage();
		}
		return "Depósito recebido com sucesso";
	}

	public String saldo() {
		ContaCorrente cc = servicoRemoto.recuperarConta(NrContaAtual);
		String msg = "O saldo é R$";
		DecimalFormat df = new DecimalFormat("####.00"); 
		if (cc!= null) {
			msg +=  df.format(cc.getSaldoAtual()); 
		}
		
		return msg;
	}

}
