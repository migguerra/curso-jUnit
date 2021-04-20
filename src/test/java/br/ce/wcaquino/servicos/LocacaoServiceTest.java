package br.ce.wcaquino.servicos;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import java.util.Date;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoServiceTest {

	@Rule
	// Anotacao que permite mudar o comportamento dos testes
	public ErrorCollector error = new ErrorCollector();

	@Test
	public void TesteLocacao() {
		// cenario - inicializacao das variaveis
		LocacaoService service = new LocacaoService();

		Usuario usuario = new Usuario("usuario ");
		Filme filme = new Filme("um filme", 2, 5.0);

		// acao - execucao
		Locacao locacao = service.alugarFilme(usuario, filme);

		// verificacao
		// Para o teste ser isolado deve criar 3 teste com cenario e ação para rastrear
		// o erro

		error.checkThat(locacao.getValor(), is(equalTo(6.0)));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)),
				is(false));
	}
}
