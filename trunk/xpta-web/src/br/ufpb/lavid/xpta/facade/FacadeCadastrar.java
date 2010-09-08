package br.ufpb.lavid.xpta.facade;

import br.ufpb.lavid.xpta.controller.ControllerProjeto;
import br.ufpb.lavid.xpta.controller.ControllerTrack;
import br.ufpb.lavid.xpta.controller.ControllerUsuario;

public class FacadeCadastrar {

	private static FacadeCadastrar facadeCadastrar;
	private ControllerTrack controllerTrack;
	private ControllerProjeto controllerProjeto;
	private ControllerUsuario controllerUsuario;
	
	public static FacadeCadastrar getInstance(){
		if(facadeCadastrar == null){
			facadeCadastrar = new FacadeCadastrar();
		}
		return facadeCadastrar;	
	}
		
	/* *************** Track *****************/
	
	public String novaTrack(){
		return controllerTrack.novaTrack();
	}
	
	public String cadastrarTrack(){
		return controllerTrack.salvarTrack();
	}
	
	/* **************** Projeto ***************/
	
	public String novoProjeto(){
		return controllerProjeto.novoProjeto();
	}
	
	public String cadastrarProjeto(){
		return controllerProjeto.salvarProjeto();
	}
	
	/* **************** Usuario ***************/
	
	public String novoUsuario(){
		return controllerUsuario.novoUsuario();
	}
	
	public String cadastrarUsuario(){
		return controllerUsuario.salvarUsuario();
		
	}
	
}

