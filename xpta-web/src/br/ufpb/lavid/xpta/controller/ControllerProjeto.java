package br.ufpb.lavid.xpta.controller;

import java.util.Date;

import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;

import br.ufpb.lavid.xpta.dao.DaoProjeto;
import br.ufpb.lavid.xpta.model.Pessoa;
import br.ufpb.lavid.xpta.model.Projeto;

public class ControllerProjeto {

	private DaoProjeto daoProjeto = new DaoProjeto();
	private Projeto projeto;
	private DataModel dataModel;
	private int codDono;
	
	public String invokeMethods(){
		novoProjeto();
		setarUsuarioProjeto();
		return "novoProjeto"; 
	}
	
	public String novoProjeto(){
		this.projeto = new Projeto();
		projeto.setDataCriacao(new Date());
		System.out.print("instanciou novo projeto");
		return "novoProjeto";
	}
	public String salvarProjeto(){
		System.out.print("metodo salvar");
		daoProjeto.begin();
		if (projeto.getCodigo() == 0){
			
			projeto.setDataCriacao(new Date());
			daoProjeto.persist(projeto);
			daoProjeto.commit();
			System.out.print("SALVOU");
		}else{
			projeto.setDataUltimaModificacao(new Date());
			daoProjeto.merge(projeto);
			daoProjeto.commit();
			System.out.print("atualizou");
		}
		daoProjeto.close();
		return "projetosalvo";
	}
	
/* ***************Listar Todos os Projetos ****************** */

	public DataModel getListaProjetos(){
		
		dataModel = new ListDataModel(daoProjeto.findAll());
		return dataModel;
	}
	
/* ***************Listar Todos os Projetos Públicos ****************** */

	public DataModel getListaProjetosPublicos(){
		
		dataModel = new ListDataModel(daoProjeto.findProjectByPermission());
		return dataModel;
	}
		

/* ***************Editar Projeto ******************* */
	public String editarProjeto(){
		projeto = (Projeto) dataModel.getRowData();
		setProjeto(projeto);
		return "projetoEditado";
	}
/* ***************Excluir Projeto ****************** */
	public String excluirProjeto(){
		projeto = (Projeto) dataModel.getRowData();
		daoProjeto.begin();
		daoProjeto.remove(projeto);
		daoProjeto.commit();
		daoProjeto.close();
		return "projetoExcluido";
	}
	
	public void setarUsuarioProjeto(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		Pessoa user = (Pessoa)session.getAttribute("user");
		projeto.setDono(user.getCodigo());
	}
/* ************** Getters and Setters ************** */

	public Projeto getProjeto() {
		return projeto;
	}
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	public int getCodDono() {
		return codDono;
	}
	public void setCodDono(int codDono) {
		this.codDono = codDono;
	}
	

}
