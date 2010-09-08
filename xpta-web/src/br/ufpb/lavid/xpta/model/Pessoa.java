package br.ufpb.lavid.xpta.model;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


@Entity(name="Pessoa")
@SuppressWarnings("unchecked")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorValue("U")
@DiscriminatorColumn(name="Status",discriminatorType=DiscriminatorType.STRING)
public class Pessoa {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codigo;
	
	private String nome;
	private String email;
	private String login;
	private String senha;
	private String perfil;
	
	public Pessoa(){
		
	}
	
	public Pessoa(String nome, String email, String login, String senha, String perfil){
		this.nome = nome;
		this.email = email;
		this.login = login;
		this.senha = senha;
		this.perfil = perfil;
		
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	

}

