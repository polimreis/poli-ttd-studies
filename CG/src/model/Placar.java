package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

public class Placar {

	Armazenamento a;

	public Placar(Armazenamento a){
		this.a = a;
	}
	public void registraTipoPonto(String nome,int qtd,String tipo){
		a.armazenaPontoTipoUsuario(tipo, qtd, nome);
	}

	public Map<String, Integer>  getPontos(String nome){
		Map<String,Integer> pontos = new HashMap<String,Integer>();

		Set<String> tipos = a.recuperaTiposPontos();
		for (String tipo : tipos) {
			int qtd = a.recuperaPontosTipoUsuario(tipo, nome);
			pontos.put(tipo, qtd);		    
		}		
		return pontos;
	}

	public Map<String, Integer> getRanking(String tipo){		
		Map<String,Integer> rank = new HashMap<String,Integer>();
		Set<String> usuarios = a.recuperaUsuariosComPontos();
		for (String usuario: usuarios){
			int qtd = a.recuperaPontosTipoUsuario(tipo, usuario);
			if (qtd>0){
				rank.put(usuario, qtd);
			}
		}
		return ordenaMap(rank);
	}
	private Map<String, Integer> ordenaMap(Map<String, Integer> map){
		Map<String,Integer> mapOrdenado = new LinkedHashMap<String,Integer>();
		TreeMap<Integer, String> temp =  new TreeMap<Integer, String>();
		for(Entry<String, Integer> m: map.entrySet()){
			temp.put(m.getValue(), m.getKey());
		}
		for(Entry<Integer, String> t: temp.entrySet()){
			mapOrdenado.put(t.getValue(), t.getKey());
		}
		mapOrdenado = inverteMap(mapOrdenado);
		return mapOrdenado;
	}

	public static <String, Integer> Map<String, Integer> inverteMap(Map<String, Integer> map)
	{
		LinkedHashMap<String, Integer> mapInvertido = new LinkedHashMap<>();
		List<String> chavesInvertidas = new ArrayList<>(map.keySet());
		Collections.reverse(chavesInvertidas);
		chavesInvertidas.forEach((key)->mapInvertido.put(key,map.get(key)));
		return mapInvertido;
	}

}
