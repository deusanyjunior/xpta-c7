package br.ufpb.lavid.xpta.bean;

import java.util.Date;

import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.servlet.http.HttpSession;

import br.ufpb.lavid.xpta.controller.ControllerProjeto;
import br.ufpb.lavid.xpta.controller.ControllerUsuario;
import br.ufpb.lavid.xpta.model.Projeto;
import br.ufpb.lavid.xpta.model.Usuario;

public class BeanProjeto {

	private ControllerProjeto controllerProjeto = new ControllerProjeto();
	private Projeto projeto;
	private ControllerUsuario controllerUsuario = new ControllerUsuario();
	private HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	private Usuario user = (Usuario)session.getAttribute("user");
	/* ******Chama o m�todo que cria o projeto e seta o usuario nele***** */

	public String invokeMethods(){
		novoProjeto();
		setarUsuarioProjeto();
		return "novoProjeto"; 
	}
	
	/* ******Instancia novo projeto****** */
	
	public String novoProjeto(){
		this.projeto = new Projeto();
		projeto.setDataCriacao(new Date());
		return "novoProjeto";
	}
	
	/* ******Seta usu�rio no projeto****** */
	
	public void setarUsuarioProjeto(){
		
		projeto.setAutor(user);
	}
	
	
	
	/* ******Chama o m�todo do controller para salvar o projeto***** */
	public String cadastrarProjeto(){
	
		controllerProjeto.salvarProjeto(this.projeto);
		criarPasta(this.projeto);
		addProjetosUsuario(this.projeto);
		return "projetosalvo";
	}
	
	/* ******Chama o m�todo do controller para editar o projeto***** */

	public String editarProjeto(){
		return controllerProjeto.editarProjeto(projeto);
	}
	
	/* ******Chama o m�todo do controller para excluir o projeto***** */

	public String excluirProjeto(){
		return controllerProjeto.excluirProjeto(projeto);
	}
	
	/* Adiciona o projeto a lista de projetos do usuário*/
	
	public void addProjetosUsuario(Projeto p){
		System.out.print("antes de chamar o bean");
		controllerUsuario.addProjeto(user, p);
		System.out.print("\n\n depois de chamar o bean " );
	}
	
	/* *******Cria uma nova pasta do projeto **********/
	
	public String criarPasta(Projeto projeto){
		
		
			java.io.File pasta = new java.io.File("/var/lib/tomcat6/webapps/Projetos/" + projeto.getCodigo());
			if (pasta.mkdir()){
				return "pasta criada";
			}
			else
				return "pasta nao criada";
	
	}
	
	public String retornaProjeto(){
		this.projeto = controllerProjeto.retornaProjeto();
		return "projetoSelecionado";
		
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
