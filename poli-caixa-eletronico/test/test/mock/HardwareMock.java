package test.mock;

import caixa.eletronico.exception.FalhaHardwareException;
import caixa.eletronico.interfaces.Hardware;

public class HardwareMock implements Hardware {

	private boolean simularFalhaDeHardware;
	
	
	
	public boolean isSimularFalhaDeHardware() {
		return simularFalhaDeHardware;
	}


	public void setSimularFalhaDeHardware(boolean simularFalhaDeHardware) {
		this.simularFalhaDeHardware = simularFalhaDeHardware;
	}


	@Override
	public String pegarNumeroDaContaCartao() throws FalhaHardwareException {
		if (this.isSimularFalhaDeHardware()) {
			throw new FalhaHardwareException ("falha de funcionamento do hardware.") ;
		}else {
			return  "12345678";
		}
	}

	@Override
	public void entregarDinheiro() throws FalhaHardwareException {
		if (this.isSimularFalhaDeHardware()) {
			throw new FalhaHardwareException ("falha de funcionamento do hardware.") ;
		}	
	}

	@Override
	public void lerEnvelope() throws FalhaHardwareException {
		if (this.isSimularFalhaDeHardware()) {
			throw new FalhaHardwareException ("falha de funcionamento do hardware.") ;
		}
	}

}
