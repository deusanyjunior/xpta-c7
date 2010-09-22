package br.ufpb.lavid.xpta.dao;

import java.util.List;

import br.ufpb.lavid.xpta.model.Projeto;

public class DaoProjeto extends DAOJPA<Projeto>{

	public DaoProjeto(){
		super();
	}
	
	public List<Projeto> findAllProject(){
		return (List<Projeto>) super.findAllByQuery("select p from Projeto p");
	}
	
	public Projeto findProjectById(int id){
		return (Projeto) super.findByQuery("select p from Projeto p where p.codigo = " + id);
	}
	public List<Projeto> findProjectByName(String nome){
		return (List<Projeto>) super.findAllByQuery("select p from Projeto p where p.nome = '" + nome + "'");
	}
	
	public List<Projeto> findProjectByPermission(String permissao){
		return (List<Projeto>) super.findAllByQuery("select p from Projeto p where p.permissao = '" + permissao + "'");
	}
	
	public List<Projeto> findProjectByDono(int id){
		return (List<Projeto>) super.findAllByQuery("select p from Projeto p where p.dono = "+ id);
	}
	
	public List<Projeto> findProjectByPermission(){
		return (List<Projeto>) super.findAllByQuery("select p from Projeto p where p.permissao = 'Publicado'");
	}
	
}
