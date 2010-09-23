package br.ufpb.lavid.xpta.controller;

import br.ufpb.lavid.xpta.dao.DaoPedido;
import br.ufpb.lavid.xpta.model.Pedido;

public class ControllerPedido {

	private DaoPedido daoPedido =  new DaoPedido();
	
	public String salvarPedido(Pedido pedido){
		try {
			if(pedido.getCodigo() == 0){
				daoPedido.begin();
				daoPedido.persist(pedido);
				daoPedido.commit();
				
			}else{
				daoPedido.merge(pedido);
				daoPedido.commit();
			}
				
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			daoPedido.close();
		}
		return "salvaPedido";
	}
	
}
