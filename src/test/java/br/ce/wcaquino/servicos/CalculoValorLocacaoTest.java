package br.ce.wcaquino.servicos;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import br.ce.wcaquino.builders.FilmeBuilder;
import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;

@RunWith(Parameterized.class)
public class CalculoValorLocacaoTest {
	// DDT

	private LocacaoService service;

	@Parameter
	public List<Filme> filmes;

	@Parameter(value = 1)
	public Double valorLocacao;

	@Parameter(value = 2)
	public String cenario;

	@Before
	public void setup() {
		service = new LocacaoService();
	}

	private static Filme filme1 = FilmeBuilder.umfilme().agora();
	private static Filme filme2 = FilmeBuilder.umfilme().agora();
	private static Filme filme3 = FilmeBuilder.umfilme().agora();
	private static Filme filme4 = FilmeBuilder.umfilme().agora();
	private static Filme filme5 = FilmeBuilder.umfilme().agora();
	private static Filme filme6 = FilmeBuilder.umfilme().agora();
	private static Filme filme7 = FilmeBuilder.umfilme().agora();

	@Parameters(name = "{2}")

	// informa que é a colecao de dados utilizada pra os testess
	public static Collection<Object[]> getParametros() {
		return Arrays.asList(new Object[][] { { Arrays.asList(filme1, filme2), 8.0, "filme sem desconto" },
				{ Arrays.asList(filme1, filme2, filme3), 11.0, "3 filme: 25%" },
				{ Arrays.asList(filme1, filme2, filme3, filme4), 13.0, "4 filme: 50%" },
				{ Arrays.asList(filme1, filme2, filme3, filme4, filme5), 14.0, "5 filme: 75%" },
				{ Arrays.asList(filme1, filme2, filme3, filme4, filme5, filme6), 14.0, "6 filme: 100%" },
				{ Arrays.asList(filme1, filme2, filme3, filme4, filme5, filme6, filme7), 18.0, "7 filme: 100%" }, });

	}

	@Test
	public void deveCalcularValorLocacaoConsiderandoDescontos() throws FilmeSemEstoqueException, LocadoraException {
		// cenario - o que preciso
		Usuario usuario = new Usuario("usuario 1");

		// acao
		Locacao resultado = service.alugarFilme(usuario, filmes);

		// verificacao
		assertThat(resultado.getValor(), is(valorLocacao));
	}
}
