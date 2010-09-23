package br.ufpb.lavid.xpta.bean;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
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
	private HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	private Usuario user = (Usuario)session.getAttribute("user");
	private Projeto projeto = beanProjeto.getProjeto();
	private Pedido pedido;
	
	
	public String invokeMethods(){
		novoPedido();
		if (setarUsuarioPedido() == "usuarioLogado"){
			setarProjetoPedido();
			salvarPedido(this.pedido);
			return "pedidoSalvo";
		
		}else{
			return "usuarioNaoLogado";
		}
		
	}
	
	public String novoPedido(){
		this.pedido = new Pedido();
		return "novoPedido";
	}
	
	public String salvarPedido(Pedido pedido){
		this.pedido = pedido;
		controllerPedido.salvarPedido(pedido);
		addPedidoUsuario(pedido);
		addPedidoProjeto(pedido);
		return "salvaPedido";
	}
	
	public void setarProjetoPedido(){
		this.pedido.setProjeto(projeto);
	}
				
	public String setarUsuarioPedido(){
		if (user == null){
			return "usuarioNaoLogado";
		}else{
			this.pedido.setUsuario(user);
			return "usuarioLogado";
		}
		
	}
	
	public void addPedidoUsuario(Pedido pedido){
		controllerUsuario.addPedidoUsuario(user, pedido);
	}
	
	public void addPedidoProjeto(Pedido pedido){
		controllerProjeto.addPedidoProjeto(projeto, pedido);
	}

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

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
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
