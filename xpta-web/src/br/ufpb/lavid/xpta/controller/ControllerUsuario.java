package br.ufpb.lavid.xpta.controller;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;

import br.ufpb.lavid.xpta.dao.DaoUsuario;
import br.ufpb.lavid.xpta.model.Pedido;
import br.ufpb.lavid.xpta.model.Projeto;
import br.ufpb.lavid.xpta.model.Usuario;

public class ControllerUsuario {

	private DaoUsuario daoUsuario = new DaoUsuario();
	private DataModel dataModel;
	
	/*Salva o usu�rio o banco*/
	public boolean salvarUsuario(Usuario user){
		
		try {
			daoUsuario.begin();
			if (user.getCodigo() == 0){
				user.setPerfil("Usuario");
				daoUsuario.persist(user);
				daoUsuario.commit();
			}else{
				daoUsuario.merge(user);
				daoUsuario.commit();

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{

			daoUsuario.close();
		}
		
		return true;
	}
	

/* ***************Listar Todos os Usuarios****************** */

	public DataModel getListaUsuario(){
		
		dataModel = new ListDataModel(daoUsuario.findAll());
		return dataModel;
	}
	
	/*public DataModel listarUsuarioById(int id){
		dataModel = new ListDataModel(daoUsuario.findUsuarioById(id));
		return dataModel;
	}*/

/* ***************Editar Usuario ******************* */
	public String editarUsuario(Usuario user){
		try {
			user = (Usuario) dataModel.getRowData();
			daoUsuario.begin();
			daoUsuario.merge(user);
			daoUsuario.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			daoUsuario.close();
		}
		
		return "usuarioEditado";
	}
/* ***************Excluir Usuario ****************** */
	public String excluirUsuario(Usuario user){
		try {
			user = (Usuario) dataModel.getRowData();
			daoUsuario.begin();
			daoUsuario.remove(user);
			daoUsuario.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			daoUsuario.close();
		}
		
		return "excluidoUsuario";
	}
	
	/* Faz o login */
	public String logar(String login, String senha) throws IOException{

        //DaoPessoa daoPessoa = new DaoPessoa();
        Usuario user =  daoUsuario.validaLogin(login,senha);
        
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        
        if(user!= null){           
          session.setAttribute("user", user); 
          daoUsuario.close();     
          return "sucessoUsuario"; 
        } 
         else{
        	 daoUsuario.close();
        	 return "sair";  
           }  
    }
	
	/* Faz o logout */
	public String logout() throws IOException{

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.removeAttribute("user"); 
        return "sair"; 
    }
	
	public void addProjeto(Usuario  u, Projeto p){
		u.addProjeto(p);
		System.out.print("dentro do controller");
		salvarUsuario(u);
		System.out.print("depois de salvar o usuario");
	}
	
	public void addPedidoUsuario(Usuario user ,Pedido pedido){
		user.addPedidoUsuario(pedido);
		salvarUsuario(user);
	}
	
	public Usuario retornaUsuario(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		Usuario user = (Usuario)session.getAttribute("user");
		return user;
	}
}
