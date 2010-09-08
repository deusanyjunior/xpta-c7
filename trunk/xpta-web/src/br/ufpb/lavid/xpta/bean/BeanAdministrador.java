package br.ufpb.lavid.xpta.bean;

import br.ufpb.lavid.xpta.controller.ControllerAdministrador;

public class BeanAdministrador {

	ControllerAdministrador controllerAdministrador = new ControllerAdministrador();
	
	public void criarBanco(){
		controllerAdministrador.criaAdm();
	}
	
}
