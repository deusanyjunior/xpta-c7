package br.ufpb.lavid.xpta.bean;

import java.util.Date;

import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.servlet.http.HttpSession;

import br.ufpb.lavid.xpta.controller.ControllerProjeto;
import br.ufpb.lavid.xpta.model.Projeto;
import br.ufpb.lavid.xpta.model.Usuario;

public class BeanProjeto {

	private ControllerProjeto controllerProjeto = new ControllerProjeto();
	private Projeto projeto;
	
	/* ******Chama o método que cria o projeto e seta o usuario nele***** */

	public String invokeMethods(){
		novoProjeto();
		setarUsuarioProjeto();
		return "novoProjeto"; 
	}
	
	/* ******Instancia novo projeto****** */
	
	public String novoProjeto(){
		this.projeto = new Projeto();
		projeto.setDataCriacao(new Date());
		System.out.print("instanciou novo projeto");
		return "novoProjeto";
	}
	
	/* ******Seta usuário no projeto****** */
	
	public void setarUsuarioProjeto(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		Usuario user = (Usuario)session.getAttribute("user");
		projeto.setAutor(user);
	}
	
	/* ******Chama o método do controller para salvar o projeto***** */
	public String cadastrarProjeto(){
		return controllerProjeto.salvarProjeto(projeto);
		
	}
	
	/* ******Chama o método do controller para editar o projeto***** */

	public String editarProjeto(){
		return controllerProjeto.editarProjeto(projeto);
	}
	
	/* ******Chama o método do controller para excluir o projeto***** */

	public String excluirProjeto(){
		return controllerProjeto.excluirProjeto(projeto);
	}
	
	/* ************** Getters and Setters ************** */

	public Projeto getProjeto() {
		return projeto;
	}
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	
	public DataModel getListaProjetosPublicos(){
		return controllerProjeto.listaProjetosPublicos();
	}
	public DataModel getListaProjeto(){
		return controllerProjeto.listaProjetos();
	}
	public ControllerProjeto getControllerProjeto() {
		return controllerProjeto;
	}

	public void setControllerProjeto(ControllerProjeto controllerProjeto) {
		this.controllerProjeto = controllerProjeto;
	}
	
}
