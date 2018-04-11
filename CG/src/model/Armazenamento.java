package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Armazenamento {
	private final String caminho;
	Map<String,Usuario> usuarios;

	public Armazenamento(String nomeArquivo) {
	  caminho = "./"+nomeArquivo+".sav";
	}
	public void armazenaPontoTipoUsuario(String tipo, int qtd, String nome) {
		usuarios = recuperaUsuarios();
		Usuario usuario = usuarios.get(nome);
		if(usuario == null){
			usuario = new Usuario(nome);
		}
		usuario.addPonto(qtd,tipo);
		usuarios.put(nome, usuario);
		try {            
			FileOutputStream saveFile = new FileOutputStream(caminho);

			ObjectOutputStream stream = new ObjectOutputStream(saveFile);

			// salva o objeto
			stream.writeObject(usuarios);

			stream.close();
		} catch (Exception exc) {
			exc.printStackTrace();
		}

	}

	public int recuperaPontosTipoUsuario(String tipo, String nome) {
		usuarios = recuperaUsuarios();
		Usuario usuario = usuarios.get(nome);
		return usuario.getPonto(tipo);
	}

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

		try {
			FileInputStream restFile = new FileInputStream(caminho);
			ObjectInputStream stream = new ObjectInputStream(restFile);

			// recupera o objeto
			usuarios = (Map<String,Usuario>) stream.readObject();
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (usuarios == null)
			return new HashMap<String,Usuario>();
		return usuarios;
	}
	public Set<String> recuperaUsuariosComPontos() {
		usuarios = recuperaUsuarios();
		return usuarios.keySet();

	}	
	public void limpaArquivo() {
		usuarios = null;
		try {            
			FileOutputStream saveFile = new FileOutputStream(caminho);

			ObjectOutputStream stream = new ObjectOutputStream(saveFile);

			// salva o objeto
			stream.writeObject(usuarios);

			stream.close();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

}
