package br.com.calebematos.api.model.ibge;

public class Mesorregiao {

	private Long id;
	private String nome;
	private Uf UF;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Uf getUF() {
		return UF;
	}

	public void setUF(Uf uF) {
		UF = uF;
	}

}
