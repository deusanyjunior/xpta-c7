package br.ufpb.lavid.xpta.bean;

import javax.faces.model.DataModel;

import br.ufpb.lavid.xpta.controller.ControllerPedido;
import br.ufpb.lavid.xpta.controller.ControllerProjeto;
import br.ufpb.lavid.xpta.controller.ControllerUsuario;
import br.ufpb.lavid.xpta.model.Pedido;
import br.ufpb.lavid.xpta.model.Projeto;
import br.ufpb.lavid.xpta.model.Usuario;



public class BeanPedido {
	
	private ControllerPedido controllerPedido = new ControllerPedido();
	private ControllerUsuario controllerUsuario = new ControllerUsuario();
	private ControllerProjeto controllerProjeto = new ControllerProjeto();
	private BeanProjeto beanProjeto;
	private Pedido pedido;
	private Projeto projeto;
	
	public String invokeMethods(){
		System.out.print("antes de instanciar o novo pedido");
		novoPedido();
		String resultado = setarUsuarioPedido();
		System.out.print("resultado: " + resultado);
		if ( resultado == "usuarioLogado"){
			
			setarProjetoPedido();
			salvarPedido(this.pedido);
			return "pedidoSalvo";
		
		}else{
			return "usuarioNaoLogado";
		}
		
	}
	
	public void novoPedido(){
		this.pedido = new Pedido();
		//return "novoPedido";
	}
	
	public String salvarPedido(Pedido pedido){
		this.pedido = pedido;
		controllerPedido.salvarPedido(pedido);
		addPedidoUsuario(pedido);
		addPedidoProjeto(pedido);
		return "salvaPedido";
	}
	
	public void setarProjetoPedido(){
		this.pedido.setProjeto(retornaProjeto());
	}
				
	public String setarUsuarioPedido(){
		if (retornaUsuario() == null){
			System.out.print("Usuario não logado!!!");
			return "usuarioNaoLogado";
		}else{
			this.pedido.setUsuario(retornaUsuario());
			return "usuarioLogado";
		}
		
	}
	
	public Usuario retornaUsuario(){
		return controllerUsuario.retornaUsuario();
	}
	
	public Projeto retornaProjeto(){
		return this.projeto = beanProjeto.getProjeto();

	}
	
	// ***** Add Pedido ao usuario ******
	public void addPedidoUsuario(Pedido pedido){
		controllerUsuario.addPedidoUsuario(retornaUsuario(), pedido);
	}
	
	// ***** Add Pedido ao Projeto ****** 
	public void addPedidoProjeto(Pedido pedido){
		controllerProjeto.addPedidoProjeto(retornaProjeto(), pedido);
	}
	
	// ***** Lista os pedidos pendentes deste usuário *****
	public DataModel getListaPedidosPendentes(){
		Usuario user = retornaUsuario();
		System.out.print("|||||||" + user.getNome() + "||||||||$$$$");
		
		return controllerPedido.listaPedidosPendentes(user);
	}
	
	// **** Modifica o Status de False para True no banco *****
	public String permitirEdicaoProjeto(){
		controllerPedido.permissionEditionProject();
		
		return "";
	}

	
	// **** Getters and Setters... *****
	
	public ControllerPedido getControllerPedido() {
		return controllerPedido;
	}

	public void setControllerPedido(ControllerPedido controllerPedido) {
		this.controllerPedido = controllerPedido;
	}

	public ControllerUsuario getControllerUsuario() {
		return controllerUsuario;
	}

	public void setControllerUsuario(ControllerUsuario controllerUsuario) {
		this.controllerUsuario = controllerUsuario;
	}

	public ControllerProjeto getControllerProjeto() {
		return controllerProjeto;
	}

	public void setControllerProjeto(ControllerProjeto controllerProjeto) {
		this.controllerProjeto = controllerProjeto;
	}

	public BeanProjeto getBeanProjeto() {
		return beanProjeto;
	}

	public void setBeanProjeto(BeanProjeto beanProjeto) {
		this.beanProjeto = beanProjeto;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
}
