package poliana.com.br.gamificacao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import poliana.com.br.gamificacao.pontuacao.TipoDePontoEnum;

public class Armazenamento implements ArmazenamentoInterface{

	private File diretorio;

	public Armazenamento(String dirPath) throws IOException {
		diretorio = new File(dirPath);
		boolean existe = diretorio.exists();

		if (!existe) {
			diretorio.mkdir();
		}
	}

	public void armazenaPontosParaUsuario(String nomeUsuario, int qtd, TipoDePontoEnum tipoPonto) throws IOException {
		String texto = tipoPonto.toString() + " " + qtd + ",";
		String nomeArquivo = "/" + nomeUsuario.toUpperCase() + ".txt";
		escreveEmArquivo(nomeArquivo, texto);
	}

	public int recuperarPontosDeUmTipoPorUsuario(String nomeUsuario, TipoDePontoEnum tipoPonto) throws IOException {
		String nomeArquivo = "/" + nomeUsuario.toUpperCase() + ".txt";
		return getPontosPorUsuario(nomeArquivo, tipoPonto.toString());

	}

	public String recuperaListaUsuariosComPontos() {
		String listaUsuarios = Arrays.toString(diretorio.list()); 
		listaUsuarios = listaUsuarios.replaceAll(".txt", " ");
		listaUsuarios = listaUsuarios.replaceAll("\\[", "");
		listaUsuarios = listaUsuarios.replaceAll("\\]", "");   
		listaUsuarios =listaUsuarios.replaceAll("\\,", "");
	     return listaUsuarios;
	}

	public List<TipoDePontoEnum> recuperarTodosTiposPontosDoUsuario(String nomeUsuario) throws IOException {
		String nomeArquivo = "/" + nomeUsuario.toUpperCase() + ".txt";
		String texto = lerArquivoCompleto(nomeArquivo);
		
		List<TipoDePontoEnum> listaTiposPontos = new ArrayList<TipoDePontoEnum>();
		
		if(texto.contains(TipoDePontoEnum.COMENTARIO.toString() )) {
			listaTiposPontos.add(TipoDePontoEnum.COMENTARIO);
		}
		if(texto.contains(TipoDePontoEnum.CURTIDA.toString())) {
			listaTiposPontos.add(TipoDePontoEnum.CURTIDA);
		}
		if(texto.contains(TipoDePontoEnum.ESTRELA.toString())) {
			listaTiposPontos.add(TipoDePontoEnum.ESTRELA);
		}
		if(texto.contains(TipoDePontoEnum.MOEDA.toString())) {
			listaTiposPontos.add(TipoDePontoEnum.MOEDA);
		}
		if(texto.contains(TipoDePontoEnum.TOPICO.toString())) {
			listaTiposPontos.add(TipoDePontoEnum.TOPICO);
		}
		
		return listaTiposPontos;
		
	}

	private void escreveEmArquivo(String nomeArquivo, String texto) throws IOException {
		File file = new File(diretorio.getAbsolutePath() + nomeArquivo);
		FileWriter fileW = new FileWriter(file, true);
		BufferedWriter writer = new BufferedWriter(fileW);
		writer.write(texto);
		writer.newLine();
		writer.flush();
		writer.close();
	}

	private String lerArquivoCompleto(String nomeArquivo) throws IOException {
		File file = new File(diretorio.getAbsolutePath() + nomeArquivo);
		String dados = new String(Files.readAllBytes(file.toPath()));
		return dados;
	}

	private int getPontosPorUsuario(String nomeArquivo, String tipoPonto ) throws IOException {
		File file = new File(diretorio.getAbsolutePath() + nomeArquivo);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		int qtd = 0 ;

		while (br.ready()) {
			String linha = br.readLine();
			if (linha.contains(tipoPonto)) {
				int indexInicial = linha.indexOf(tipoPonto) + tipoPonto.length();
				int indexFinal = linha.indexOf(",");
				String qtdStr = linha.substring(indexInicial+1, indexFinal );
				qtd += Integer.valueOf(qtdStr); 
			}
		}
		
		br.close();
		fr.close();
		return qtd;
	}

}
