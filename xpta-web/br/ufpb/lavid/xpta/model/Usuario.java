package br.ufpb.lavid.xpta.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

@Entity(name ="Usuario")
@DiscriminatorValue("U")
public class Usuario extends Pessoa{
	
	@ManyToMany(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
	private List<Projeto> projetos = new ArrayList<Projeto>();
	
	public Usuario(){
		
	}
	
	public Usuario(String nome, String email, String Login, String senha, String Perfil){
		super();
	}

	public List<Projeto> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}
	
	
}
