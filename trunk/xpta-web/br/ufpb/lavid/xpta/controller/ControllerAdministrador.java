package br.ufpb.lavid.xpta.controller;

import br.ufpb.lavid.xpta.dao.DaoAdministrador;

public class ControllerAdministrador {

	DaoAdministrador daoAdm = new DaoAdministrador();
	
	public String criaAdm(){
		try {
			daoAdm.begin();
			return "criado";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		
		}finally{
			daoAdm.close();
		}
		
	}
}
