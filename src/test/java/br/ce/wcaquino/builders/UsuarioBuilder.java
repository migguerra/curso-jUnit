package br.ce.wcaquino.builders;

import br.ce.wcaquino.entidades.Usuario;

public class UsuarioBuilder {

	private Usuario usuario;

	// construtor privado para nao se criar instancia do builder fora dele
	private UsuarioBuilder() {
	}

	// para ser acessado externamente
	public static UsuarioBuilder umUsuario() {
		UsuarioBuilder builder = new UsuarioBuilder();
		builder.usuario = new Usuario();
		builder.usuario.setNome("Usuario 1");
		return builder;
	}

	public UsuarioBuilder comNome(String nome) {
		usuario.setNome(nome);
		return this;
	}

	public Usuario agora() {
		return usuario;
	}
}
