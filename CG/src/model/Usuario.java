package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Usuario implements Serializable{

	private static final long serialVersionUID = -6991918586877919159L;
	private String nome;
	private Map<String, Integer> pontos = new HashMap<String, Integer>(); 

	public Map<String, Integer> getPontos() {
		return pontos;
	}

	public void setPontos(Map<String, Integer> pontos) {
		this.pontos = pontos;
	}

	public Usuario(String nome) {
		this.nome = nome;
	}

	public void addPonto(int qtd, String tipo) {
		pontos.put(tipo, getPonto(tipo)+qtd);
	}

	public int getPonto(String tipo) {
		if (pontos.get(tipo) == null){
			return 0;
		}
		return pontos.get(tipo);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
