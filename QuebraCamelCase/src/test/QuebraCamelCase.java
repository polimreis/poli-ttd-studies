package test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class QuebraCamelCase {

	List<String> listaString;

	public QuebraCamelCase() {
		super();
		listaString = new ArrayList<String>();
	}

	public List<String> converterCamelCase(String original) {

		boolean primeiraLetraMinuscula = false;
		boolean existeMaiuscula = false;
		int count = 0;

		// Patterns Regex
		String letraMin = "([a-z]+)";
		Pattern patternLetraMin = Pattern.compile(letraMin);

		String apenasNumeros = "([0-9]+)";
		Pattern patternApenasNumeros = Pattern.compile(apenasNumeros);

		String regexCompleto = "([0-9]*)([A-Z]{1})([a-z]+)|([0-9]*)([A-Z]{2,})([a-z]*)";
		Pattern patternCompleto = Pattern.compile(regexCompleto);

		String variasMaiusMin = "([A-Z]{2,})([a-z]*)";
		Pattern patternVariasMaiusMin = Pattern.compile(variasMaiusMin);

		String apenasMaiusculas = "([A-Z]{2,})";
		Pattern patternApenasMaiusculas = Pattern.compile(apenasMaiusculas);

		String charEspecial = "([^a-zA-Z0-9])";
		Pattern patternCharEspecial = Pattern.compile(charEspecial);

		// verifica se a string passada possui algum caracter especial
		java.util.regex.Matcher matcherCharEspecial = patternCharEspecial.matcher(original);
		
		if (matcherCharEspecial.find()) {
			throw new FormatInvalidException("A entrada nao aceita cacteres especiais");
		}

		// Primeira letra da string
		String primeiraLetra = original.substring(0, 1);

		// verifica se a primeira letra é minuscula
		Matcher matcherPrimeiraLetra = patternLetraMin.matcher(primeiraLetra);
		if (matcherPrimeiraLetra.matches()) {
			primeiraLetraMinuscula = true;
		}

		// verifica se o primeiro caracter é numero
		Matcher matcherApenasNumeros = patternApenasNumeros.matcher(primeiraLetra);
		if (matcherApenasNumeros.matches()) {
			throw new FormatInvalidException("O primeira character não pode ser número!");
		}

		Matcher matcherCompleto = patternCompleto.matcher(original);

		// valida se encontra o padrão
		while (matcherCompleto.find()) {
			existeMaiuscula = true;

			int indexStartInicial = matcherCompleto.start();
			int indexEndInicial = matcherCompleto.end();
			int indexEndSemPrimeiraPalavra = indexEndInicial;

			// Verifica se a primeira palavra começa com a letra minuscula, se sim guarda na
			// lista
			if (primeiraLetraMinuscula && count == 0) {
				String primeiraPalavra = original.substring(0, indexStartInicial);
				listaString.add(primeiraPalavra);
				indexEndSemPrimeiraPalavra = indexEndInicial - primeiraPalavra.length();
				count++;
			}

			// obtem a palavra encontrada que se encaixa no regex completo
			String token = original.substring(indexStartInicial, indexEndInicial);

			// verifica se no token encontrado possui uma parte com apenas números
			matcherApenasNumeros = patternApenasNumeros.matcher(token);
			if (matcherApenasNumeros.find()) {

				int tamanhoTokenApenasNum = matcherApenasNumeros.end();

				// valida se o tamanho do token que tem apenas número é igual ao token inicial
				if (indexEndSemPrimeiraPalavra == tamanhoTokenApenasNum) {
					listaString.add(token);
				} else { // caso o token seja menor que o inicial, devemos quebrar em dois itens da lista
					// um item com apenas números e outro com a proxima palavra
					String sequenciaNumeros = matcherApenasNumeros.group(1);
					sequenciaNumeros = sequenciaNumeros.substring(0, tamanhoTokenApenasNum);
					listaString.add(sequenciaNumeros);

					String proximaPalavra = token.substring(tamanhoTokenApenasNum, indexEndSemPrimeiraPalavra);
					listaString.add(proximaPalavra.toLowerCase());
				}
			}

			// valida se o token encontrado possui uma sequencia de letras maíusculas
			Matcher matcherVariasMaiusMin = patternVariasMaiusMin.matcher(token);
			Matcher matcherApenasMaiusculas = patternApenasMaiusculas.matcher(token);
			if (matcherVariasMaiusMin.matches()) {
				// verifica se existe uma sequencia apenas com maiuscula
				if (matcherApenasMaiusculas.find()) {
					int tamanhoIndexMaisDeUmaMai = matcherVariasMaiusMin.end();
					int tamanhoTokenApenasMaiusc = matcherApenasMaiusculas.end();

					if (tamanhoIndexMaisDeUmaMai == tamanhoTokenApenasMaiusc) {
						listaString.add(token);
					} else {// caso o token seja menor que o inicial, devemos quebrar em dois itens da lista
						// um item com apenas números e outro com a proxima palavra

						String sequenciaMai = matcherApenasMaiusculas.group(1);
						sequenciaMai = sequenciaMai.substring(0, tamanhoTokenApenasMaiusc - 1);
						listaString.add(sequenciaMai);

						String proximaPalavra = token.substring(tamanhoTokenApenasMaiusc - 1, tamanhoIndexMaisDeUmaMai);
						listaString.add(proximaPalavra.toLowerCase());
					}
				}
			} else {
				listaString.add(token.toLowerCase());
			}
		}

		if (primeiraLetraMinuscula && !existeMaiuscula) {
			listaString.add(original);
		}

		return listaString;
	}

}
