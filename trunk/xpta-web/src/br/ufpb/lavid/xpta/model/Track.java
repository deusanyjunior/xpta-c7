package br.ufpb.lavid.xpta.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Track {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;

	private String nome;
	
	private String trilha;
	
	
	private int projeto;

	public Track() {

	}
	
	public Track(String nome){
		this.nome = nome;
				
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	

	public String getTrilha() {
		return trilha;
	}

	public void setTrilha(String trackDir) {
		this.trilha = trackDir;
	}

	public int getProjeto() {
		return projeto;
	}

	public void setProjeto(int projeto) {
		this.projeto = projeto;
	}

	
}
