package br.ufpb.lavid.xpta.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name="Pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int codigo;
	
	@ManyToOne
	private Usuario usuario;
	
	@ManyToOne
	private Projeto projeto;
	
	private boolean status = false ;
	
	 public Pedido() {
	 
	 }
	
	 public Pedido(Usuario usuario, Projeto projeto){
		 this.usuario = usuario;
		 this.projeto = projeto;
	 }

	
	// Getters and Setters
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Projeto getProjeto() {
		return projeto;
	}
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	
}
