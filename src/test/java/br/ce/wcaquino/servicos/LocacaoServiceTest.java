package br.ce.wcaquino.servicos;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoServiceTest {

	@Test
	public void TesteLocacao() {
		// cenario - inicializacao das variaveis
		LocacaoService service = new LocacaoService();

		Usuario usuario = new Usuario("usuario ");
		Filme filme = new Filme("um filme", 2, 5.0);

		// acao - execucao
		Locacao locacao = service.alugarFilme(usuario, filme);

		// validacao
		Assert.assertEquals(5.0, locacao.getValor(), 0.1);
		Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
		Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
	}
}