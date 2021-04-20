package br.ce.wcaquino.servicos;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoServiceTest {

	@Rule
	// Anotacao que permite mudar o comportamento dos testes
	public ErrorCollector error = new ErrorCollector();

	@Rule
	public ExpectedException exception = ExpectedException.none();

	// se o teste nao esta esperando exception alguma deixa o JUnit tratar

	@Test
	public void TesteLocacao() throws Exception {

		// cenario - inicializacao das variaveis
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("usuario ");
		Filme filme = new Filme("um filme", 2, 5.0);

		// acao - execucao
		Locacao locacao = service.alugarFilme(usuario, filme);

		// verificacao

		error.checkThat(locacao.getValor(), is(equalTo(5.0)));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)),
				is(true));
	}

	@Test(expected = FilmeSemEstoqueException.class)
	public void TesteLocacaoFilmeSemEstoqueElegante() throws Exception {
		// cenario - inicializacao das variaveis
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("usuario ");
		Filme filme = new Filme("um filme", 0, 5.0);

		// acao - execucao
		service.alugarFilme(usuario, filme);
	}

	// forma que se obtem mais controle para se tratar exception
	@Test
	public void testeLocacaoUsuarioVazio() throws FilmeSemEstoqueException {
		// cenario
		LocacaoService service = new LocacaoService();
		Filme filme = new Filme("um filme", 1, 5.0);

		// acao
		try {
			service.alugarFilme(null, filme);
			Assert.fail();
		} catch (LocadoraException e) {
			assertThat(e.getMessage(), is("Usuario vazio"));
		}
	}

	@Test
	public void testeLocacaoFilmeVazio() throws FilmeSemEstoqueException, LocadoraException {
		// cenario - inicializacao das variaveis
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("usuario ");

		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme vazio");
		// acao
		service.alugarFilme(usuario, null);

	}
}
