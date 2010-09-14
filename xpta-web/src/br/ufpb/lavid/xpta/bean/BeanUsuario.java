package br.ufpb.lavid.xpta.bean;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.ufpb.lavid.xpta.controller.ControllerUsuario;
import br.ufpb.lavid.xpta.dao.DaoPessoa;
import br.ufpb.lavid.xpta.model.Pessoa;
import br.ufpb.lavid.xpta.model.Usuario;

public class BeanUsuario {
	
	private ControllerUsuario controllerUsuario = new ControllerUsuario();
	private String login;
	private String senha;
	private Usuario usuario;
	
	/*Instancia novo usu�rio*/
	public String novoUsuario(){
		this.usuario = new Usuario();
		return "novoUsuario";
	}
	
	/*Chama o m�todo salvar do controller e cadastra um usu�rio*/
	public String cadastrarUsuario(){
		return controllerUsuario.salvarUsuario(usuario);
	}
	
	/*Chama o m�todo do controller e edita um usu�rio*/
	public String editarUsuario(){
		return controllerUsuario.editarUsuario(usuario);
	}
	
	/*Chama o m�todo do controller e exclui um usu�rio*/
	public String excluirUsuario(){
		return controllerUsuario.excluirUsuario(usuario);
	}
	
	/*public DataModel getListaUsuariosById(int id){
		return controllerUsuario.listarUsuarioById(id);
	}*/
	
	/* Faz o login */
	
	
	public String fazerLogin() throws IOException{
		
		return controllerUsuario.logar(login, senha);
	}
	/*Faz o logout*/
	public String fazerLogout() throws IOException{
		return controllerUsuario.logout();
	}
	
/* ************** Getters and Setters ************** */
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	
	public ControllerUsuario getControllerUsuario() {
		return controllerUsuario;
	}

	public void setControllerUsuario(ControllerUsuario controllerUsuario) {
		this.controllerUsuario = controllerUsuario;
	}

}
