package br.ufpb.lavid.xpta.facade;

import br.ufpb.lavid.xpta.controller.ControllerProjeto;
import br.ufpb.lavid.xpta.controller.ControllerTrack;
import br.ufpb.lavid.xpta.controller.ControllerUsuario;

public class FacadeExcluir {
	
	private static FacadeExcluir facadeExcluir;
	private ControllerProjeto controllerProjeto;
	private ControllerTrack controllerTrack;
	private ControllerUsuario controllerUsuario;
	
	public static FacadeExcluir getInstance(){
		if(facadeExcluir == null){
			facadeExcluir =  new FacadeExcluir();
		}
		return facadeExcluir;
	}

	public String excluirTrack(){
		return controllerTrack.excluirTrack();
	}
	
	public String excluirProjeto(){
		return controllerProjeto.excluirProjeto();
	}
	
	public String excluirUsuario(){
		return controllerUsuario.excluirUsuario();
	}
}
