package br.ufpb.lavid.xpta.dao;

import java.util.List;

import br.ufpb.lavid.xpta.model.Pessoa;
import br.ufpb.lavid.xpta.model.Usuario;

public class DaoUsuario extends DAOJPA<Usuario>{

	public DaoUsuario(){
		super();
		
	}

	public List<Usuario> findAllUsuario(){
		return (List<Usuario>) super.findAllByQuery("select u from Usuario u");
	}
	
	public Usuario findUsuarioById(int id){
		return (Usuario) super.findByQuery("select u from Usuario u where u.codigo = " + id);
		
	}
	
	public List<Usuario> findUsuarioByName(String nome){
		return (List<Usuario>) super.findAllByQuery("select u from Usuario u where u.nome = '"+ nome + "'");
	}
	
	public Usuario validaLogin(String login, String senha){
		return (Usuario)super.findByQuery("SELECT u from Usuario u where u.login = '"+login+"' and u.senha = '"+senha+"'");
	}
	//Fazer pesquisa do usuário através do projeto
}

