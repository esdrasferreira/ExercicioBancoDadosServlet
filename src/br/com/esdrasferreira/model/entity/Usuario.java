package br.com.esdrasferreira.model.entity;

public class Usuario {
	
	public Usuario(int id, String usuario, int senha) {
		this.id = id;
		this.usuario = usuario;
		this.senha = senha;
	}
	
	public Usuario() {}
	private int id;
	private String usuario;
	private int senha;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public int getSenha() {
		return senha;
	}
	public void setSenha(int senha) {
		this.senha = senha;
	}
	
	
	

}
