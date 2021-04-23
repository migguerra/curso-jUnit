package br.ce.wcaquino.servicos;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
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

	private LocacaoService service;

	@Rule
	// Anotacao que permite mudar o comportamento dos testes
	public ErrorCollector error = new ErrorCollector();

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Before
	public void setup() {
		service = new LocacaoService();
	}

	@Test
	public void TesteLocacao() throws Exception {

		// cenario - inicializacao das variaveis
		Usuario usuario = new Usuario("usuario ");
		List<Filme> filme = Arrays.asList(new Filme("um filme", 2, 5.0));

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
		Usuario usuario = new Usuario("usuario ");
		List<Filme> filmes = Arrays.asList(new Filme("um filme", 0, 5.0));

		// acao - execucao
		service.alugarFilme(usuario, filmes);
	}

	// forma que se obtem mais controle para se tratar exception
	@Test
	public void testeLocacaoUsuarioVazio() throws FilmeSemEstoqueException {
		// cenario
		List<Filme> filme = Arrays.asList(new Filme("um filme", 1, 5.0));

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
		Usuario usuario = new Usuario("usuario ");

		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme vazio");
		// acao
		service.alugarFilme(usuario, null);

	}
}
