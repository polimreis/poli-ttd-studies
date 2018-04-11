package mock;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import model.Armazenamento;
import model.Usuario;

public class ArmazenamentoMock extends Armazenamento {
	Map<String,Usuario> usuarios;
	String nomeArquivo;
	
	public ArmazenamentoMock(String nomeArquivo) {
		super(nomeArquivo);
	}

	
	@Override
	public void armazenaPontoTipoUsuario(String tipo, int qtd, String nome) {
		usuarios = recuperaUsuarios();
		Usuario usuario = usuarios.get(nome);
		if(usuario == null){
			usuario = new Usuario(nome);
		}
		usuario.addPonto(qtd,tipo);
		usuarios.put(nome, usuario);
	}

	@Override
	public int recuperaPontosTipoUsuario(String tipo, String nome) {
		usuarios = recuperaUsuarios();
		Usuario usuario = usuarios.get(nome);
		return usuario.getPonto(tipo);
	}

	@Override
	public Set<String> recuperaTiposPontos() {
		usuarios = recuperaUsuarios();
		Set<String> chaveUsuario = usuarios.keySet();
		Set<String> tipos = new HashSet<String>(); 
		for (String chaveU : chaveUsuario)
		{		
			if(chaveU != null){
				{
				Map<String,Integer> pontos = usuarios.get(chaveU).getPontos();
				Set<String> chavePontos = pontos.keySet();
				for (String tipo : chavePontos){					
					tipos.add(tipo);				
				}
				}
			}
		}
		return tipos;
	}
	
	private Map<String, Usuario> recuperaUsuarios(){       
		if (usuarios == null)
			return new HashMap<String,Usuario>();
		return usuarios;
	}

	@Override
	public Set<String> recuperaUsuariosComPontos() {
		return usuarios.keySet();
	}

}
