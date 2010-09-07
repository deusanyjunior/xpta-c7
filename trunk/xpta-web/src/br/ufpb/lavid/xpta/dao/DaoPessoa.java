package br.ufpb.lavid.xpta.dao;

import br.ufpb.lavid.xpta.model.Pessoa;
import br.ufpb.lavid.xpta.model.Usuario;

public class DaoPessoa extends DAOJPA<Pessoa> {
	
	public DaoPessoa(){
		super();
	}
	
	public Pessoa validaLogin(String login, String senha){
		return (Pessoa)super.findByQuery("SELECT p from Pessoa p where p.login = '"+login+"' and p.senha = '"+senha+"'");
	}
}
