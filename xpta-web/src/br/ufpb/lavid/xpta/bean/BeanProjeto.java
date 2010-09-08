package br.ufpb.lavid.xpta.bean;

import javax.faces.model.DataModel;

import br.ufpb.lavid.xpta.controller.ControllerProjeto;

public class BeanProjeto {

	ControllerProjeto controllerProjeto = new ControllerProjeto();
	
	public String invokeMethods(){
		return controllerProjeto.invokeMethods();
	}
	
	public String salvarProjeto(){
		return controllerProjeto.salvarProjeto();
	}
	
	public DataModel getListaProjetosPublicos(){
		return controllerProjeto.getListaProjetosPublicos();
	}
	public DataModel getListaProjeto(){
		return controllerProjeto.getListaProjetos();
	}
	public ControllerProjeto getControllerProjeto() {
		return controllerProjeto;
	}

	public void setControllerProjeto(ControllerProjeto controllerProjeto) {
		this.controllerProjeto = controllerProjeto;
	}
	
}
