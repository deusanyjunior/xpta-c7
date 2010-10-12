package br.ufpb.lavid.xpta.controller;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.ufpb.lavid.xpta.dao.DaoPedido;
import br.ufpb.lavid.xpta.model.Pedido;
import br.ufpb.lavid.xpta.model.Usuario;

public class ControllerPedido {

	private DaoPedido daoPedido =  new DaoPedido();
	private DataModel dataModel;
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
			daoPedido.close();
		}finally{
			daoPedido.close();
		}
		return "salvaPedido";
	}
	
	public DataModel listaPedidosPendentes(Usuario user){
		dataModel = new ListDataModel(daoPedido.findProjectPending(user));
		System.out.print("|||| Fez a consulta do dao ||||");
		return dataModel;
	}
	
	public String permissionEditionProject(){
		Pedido pedido = (Pedido) dataModel.getRowData();
		pedido.setStatus(true);
		salvarPedido(pedido);
		return "pedidoSalvo";
	}
	
}
