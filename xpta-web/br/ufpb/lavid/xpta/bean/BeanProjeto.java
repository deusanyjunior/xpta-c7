package br.ufpb.lavid.xpta.bean;

import br.ufpb.lavid.xpta.controller.ControllerProjeto;

public class BeanProjeto {

	ControllerProjeto controllerProjeto = new ControllerProjeto();
	
	public String invokeMethods(){
		return controllerProjeto.invokeMethods();
	}
	
	public String salvarProjeto(){
		//System.out.print("chegou no bean");
		return controllerProjeto.salvarProjeto();
	}

	public ControllerProjeto getControllerProjeto() {
		return controllerProjeto;
	}

	public void setControllerProjeto(ControllerProjeto controllerProjeto) {
		this.controllerProjeto = controllerProjeto;
	}
	
}
