package br.com.esdrasferreira.model.entity;

public class Produto {

	private int id;
	private String produto;
	private int usuario_fk;
	private String imagem;

	public Produto(int id, String produto, int usuario_fk, String imagem) {
	
		this.id = id;
		this.produto = produto;
		this.usuario_fk = usuario_fk;
		this.imagem = imagem;
	}
	
	public Produto(int id, String produto) {
		
		this.id = id;
		this.produto = produto;
		
		
	}
	
	public Produto(int id, String produto, String imagem) {
		this.id = id;
		this.produto = produto;
		this.imagem = imagem;
	}

	public Produto() {
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

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public int getUsuario_fk() {
		return usuario_fk;
	}

	public void setUsuario_fk(int usuario_fk) {
		this.usuario_fk = usuario_fk;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	
}
