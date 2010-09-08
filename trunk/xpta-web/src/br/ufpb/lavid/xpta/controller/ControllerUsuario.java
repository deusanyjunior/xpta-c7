package br.ufpb.lavid.xpta.controller;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;

import br.ufpb.lavid.xpta.dao.DaoPessoa;
import br.ufpb.lavid.xpta.dao.DaoUsuario;
import br.ufpb.lavid.xpta.model.Pessoa;
import br.ufpb.lavid.xpta.model.Usuario;

public class ControllerUsuario {

	private DaoUsuario daoUsuario = new DaoUsuario();
	private Usuario usuario = new Usuario();
	private DataModel dataModel;
	private String login;
	private String senha;
	
	public String novoUsuario(){
		this.usuario = new Usuario();
		return "novoUsuario";
	}
	public String salvarUsuario(){
		daoUsuario.begin();
		if (usuario.getCodigo() == 0){
			usuario.setPerfil("Usuario");
			daoUsuario.persist(usuario);
			daoUsuario.commit();
		}else{
			daoUsuario.merge(usuario);
			daoUsuario.commit();

		}
		daoUsuario.close();
		return "usuarioSalvo";
	}
	

/* ***************Listar Todos os Usuarios****************** */

	public DataModel getListaUsuario(){
		
		dataModel = new ListDataModel(daoUsuario.findAll());
		return dataModel;
	}

/* ***************Editar Usuario ******************* */
	public String editarUsuario(){
		usuario = (Usuario) dataModel.getRowData();
		setUsuario(usuario);
		return "usuarioEditado";
	}
/* ***************Excluir Usuario ****************** */
	public String excluirUsuario(){
		usuario = (Usuario) dataModel.getRowData();
		daoUsuario.begin();
		daoUsuario.remove(usuario);
		daoUsuario.commit();
		daoUsuario.close();
		return "excluidoUsuario";
	}
	
	public String logar() throws IOException{

        DaoPessoa daoPessoa = new DaoPessoa();
        Pessoa logado =  daoPessoa.validaLogin(login,senha);
        
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        
        if(logado!= null){           
          session.setAttribute("user", logado); 
          daoPessoa.close();     
          return "sucessoPessoa"; 
        } 
         else{
        	 daoPessoa.close();
        	 return "sair";  
           }  
    }
	
	public String logout() throws IOException{

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        
          session.removeAttribute("user"); 
          return "sair"; 
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
}
