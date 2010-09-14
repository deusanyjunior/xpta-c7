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
	private BeanFile upFile = new BeanFile();
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
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		Usuario user = (Usuario)session.getAttribute("user");
		projeto.setAutor(user);
	}
	
	/* ******Chama o m�todo do controller para salvar o projeto***** */
	public String cadastrarProjeto(){
	
		controllerProjeto.salvarProjeto(projeto);
		criarPasta(projeto);
		upFile.retornaCodigoProjeto(projeto);
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
	
	public String criarPasta(Projeto projeto){
		System.out.print("antes de criar a pasta ");
		
			java.io.File pasta = new java.io.File("/var/lib/tomcat6/webapps/Projetos/" + projeto.getCodigo());
			if (pasta.mkdir()){
				return "pasta criada";
			}
			else
				return "pasta nao criada";
	
		
		
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
