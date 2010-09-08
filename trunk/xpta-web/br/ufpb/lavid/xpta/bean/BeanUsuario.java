package br.ufpb.lavid.xpta.bean;

import br.ufpb.lavid.xpta.controller.ControllerUsuario;

public class BeanUsuario {
	
	private ControllerUsuario controllerUsuario = new ControllerUsuario();
	
	
	public ControllerUsuario getControllerUsuario() {
		return controllerUsuario;
	}


	public void setControllerUsuario(ControllerUsuario controllerUsuario) {
		this.controllerUsuario = controllerUsuario;
	}

}
