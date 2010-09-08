package br.ufpb.lavid.xpta.facade;

import br.ufpb.lavid.xpta.controller.ControllerProjeto;
import br.ufpb.lavid.xpta.controller.ControllerTrack;
import br.ufpb.lavid.xpta.controller.ControllerUsuario;

public class FacadeEditar {

	private static FacadeEditar facadeEditar;
	private ControllerTrack controllerTrack;
	private ControllerProjeto controllerProjeto;
	private ControllerUsuario controllerUsuario;
	
	public static FacadeEditar getInstance(){
		if (facadeEditar == null){
			facadeEditar = new FacadeEditar(); 
		}
		return facadeEditar;
	}
	
	/* **************** Track ***************/
	
	public String editarTrack(){
		return controllerTrack.editarTrack();
	}

	/* **************** Projeto ***************/
	
	public String editarProjeto(){
		return controllerProjeto.editarProjeto();
	}
	/* **************** Usuario ***************/
	
	public String editarUsurio(){
		return controllerUsuario.editarUsuario();
	}
}
