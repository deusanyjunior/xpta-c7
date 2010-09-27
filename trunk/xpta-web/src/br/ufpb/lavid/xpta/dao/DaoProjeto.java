package br.ufpb.lavid.xpta.dao;

import java.util.List;

import br.ufpb.lavid.xpta.model.Projeto;
import br.ufpb.lavid.xpta.model.Usuario;

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
	
	public List<Projeto> findProjectByAutor(int id){
		return (List<Projeto>) super.findAllByQuery("select p from Projeto p where p.autor.codigo = " + id);
	}
	
	public List<Projeto> findProjectByPermission(){
		return (List<Projeto>) super.findAllByQuery("select p from Projeto p where p.permissao = 'Publicado'");
	}
	
	public List<Projeto> findProjectEditors(Usuario user){
		return (List<Projeto>) super.findAllByQuery("select p from Pedido p where p.usuario.codigo = " + user.getCodigo() + "and status = true");
	}
	
	public List<Projeto> findProjectEdition(Usuario user){
		return (List<Projeto>) super.findAllByQuery("select p from Pedido p where p.usuario.codigo = " + user.getCodigo() + "and status = false");
	}
	
	public List<Projeto> findOtherProject(Usuario user){
		return (List<Projeto>) super.findAllByQuery("Select p from Pedido p where p.usuario.codigo <> " + user.getCodigo() + "and p.projeto.autor.codigo <> " + user.getCodigo());
	}
}
