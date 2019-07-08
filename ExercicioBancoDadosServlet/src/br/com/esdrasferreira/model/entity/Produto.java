package br.com.esdrasferreira.model.entity;

public class Produto {

	private int id;
	private String produto;
	private int usuario_fk;

	public Produto() {
	}

	public Produto(int id, String nome) {
		this.id = id;
		this.produto = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String nome) {
		this.produto = nome;
	}

	public int getUsuario_fk() {
		return usuario_fk;
	}

	public void setUsuario_fk(int usuario_fk) {
		this.usuario_fk = usuario_fk;
	}

}
