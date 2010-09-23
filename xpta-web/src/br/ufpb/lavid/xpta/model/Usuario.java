package br.ufpb.lavid.xpta.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity(name ="Usuario")
@DiscriminatorValue("U")
public class Usuario extends Pessoa{
	
	@OneToMany(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
	private List<Pedido> pedidos = new ArrayList<Pedido>();
	
	@OneToMany(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
	private List<Projeto> projetosAutor;
	
	public Usuario(){
		
	}
	
	public Usuario(String nome, String email, String Login, String senha, String Perfil){
		super();
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public List<Projeto> getProjetosAutor() {
		return projetosAutor;
	}

	public void setProjetosAutor(List<Projeto> projetosAutor) {
		this.projetosAutor = projetosAutor;
	}
	
	public void addProjeto(Projeto p){
		this.projetosAutor.add(p);
	}
	
	public void addPedidoUsuario(Pedido pedido){
		this.pedidos.add(pedido);
	}

}
