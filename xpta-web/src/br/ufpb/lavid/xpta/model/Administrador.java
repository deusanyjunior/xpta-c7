package br.ufpb.lavid.xpta.model;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name="Administrador")
@DiscriminatorValue("A")
public class Administrador extends Pessoa {

	public Administrador(){
		
	}
	
	public Administrador(String nome, String email, String login, String senha, String perfil){
		super();
	}
}
