package br.ufpb.lavid.xpta.dao;

import java.util.List;

import br.ufpb.lavid.xpta.model.Pedido;
import br.ufpb.lavid.xpta.model.Usuario;

public class DaoPedido extends DAOJPA<Pedido>{

	public DaoPedido(){
		super();
	}
	
	public List<Pedido> findProjectPending(Usuario user){
		return (List<Pedido>) super.findAllByQuery("Select pe from Pedido pe where pe.projeto.autor.codigo = " + user.getCodigo() + "and pe.status = FALSE");
	}
}
