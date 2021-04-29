package br.ce.wcaquino.servicos;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.ce.wcaquino.exceptions.NaoPodeDividirPorZeroException;

public class CalculadoraTest {

	// TDD primeiro o test depois o codigo
	private Calculadora calc;

	@Before
	public void setup() {
		calc = new Calculadora();
	}

	@Test
	public void deveSomarDoisValores() {
		// cenario - o que preciso

		int num1 = 5;
		int num2 = 3;

		// acao
		int resultado = calc.somar(num1, num2);

		// verificacao
		assertEquals(8, resultado);
	}

	@Test
	public void deveSubtrarirDoisValores() {
		// cenario - o que preciso

		int num1 = 8;
		int num2 = 5;

		// acao
		int resultado = calc.subtratir(num1, num2);

		// verificacao
		assertEquals(3, resultado);
	}

	@Test
	public void deveDividirDoisValores() throws NaoPodeDividirPorZeroException {
		// cenario - o que preciso

		int num1 = 6;
		int num2 = 3;

		// acao
		int resultado = calc.dividir(num1, num2);

		// verificacao
		assertEquals(2, resultado);

	}

	@Test
	public void deveMultiplicarDoisNumeros() {
		// cenario - o que preciso
		int num1 = 2;
		int num2 = 2;

		// acao
		int resultado = calc.multiplicar(num1, num2);

		// verificacao
		assertEquals(4, resultado);
	}

	@Test(expected = NaoPodeDividirPorZeroException.class)
	public void deveLancarExcecaoAoDividirPorZero() throws NaoPodeDividirPorZeroException {
		// cenario
		int num1 = 2;
		int num2 = 0;

		// acao
		calc.dividir(num1, num2);

	}
}
