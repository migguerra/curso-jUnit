package br.ce.wcaquino.servicos;

import br.ce.wcaquino.exceptions.NaoPodeDividirPorZeroException;

public class Calculadora {

	public int somar(int num1, int num2) {
		return num1 + num2;
	}

	public int subtratir(int num1, int num2) {
		return num1 - num2;
	}

	public int dividir(int num1, int num2) throws NaoPodeDividirPorZeroException {
		if (num2 == 0) {
			throw new NaoPodeDividirPorZeroException();
		}
		return num1 / num2;
	}

	public int multiplicar(int num1, int num2) {
		return num1 * num2;
	}

}
