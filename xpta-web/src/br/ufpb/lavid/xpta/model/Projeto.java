package br.ufpb.lavid.xpta.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity(name="Projeto")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="Tipo",discriminatorType=DiscriminatorType.STRING)

public class Projeto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codigo;
	
	private String nome;
	private String descricaoFormato;
	private String permissao;
	
	@ManyToOne
	private Usuario autor;
	
	@Temporal(TemporalType.DATE)
	private Date dataCriacao;
	
	@Temporal(TemporalType.DATE)
	private Date dataUltimaModificacao;
	
	@OneToMany(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
	private List<Pedido> pedidos;
	
	@OneToMany(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
	private List<Track> trackDeAudio = new ArrayList<Track>();
	
	@OneToMany(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
	private List<TrackUsed> trackUseds = new ArrayList<TrackUsed>();
	
	public Projeto(){
		
	}
	
	public Projeto(String nome, String descricaoFormato, String permissao){
		
		this.nome = nome;
		this.descricaoFormato = descricaoFormato;
		this.permissao = permissao;
		
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricaoFormato() {
		return descricaoFormato;
	}
	public void setDescricaoFormato(String descricaoFormato) {
		this.descricaoFormato = descricaoFormato;
	}
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public Date getDataUltimaModificacao() {
		return dataUltimaModificacao;
	}
	public void setDataUltimaModificacao(Date dataUltimaModificacao) {
		this.dataUltimaModificacao = dataUltimaModificacao;
	}
	
	public String getPermissao() {
		return permissao;
	}

	public void setPermissao(String permissao) {
		this.permissao = permissao;
	}
	
	public List<Pedido> getPedidos() {
		return pedidos;
	}
	public void setUsuario(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	public List<Track> getTrackDeAudio() {
		return trackDeAudio;
	}
	public void setTrackDeAudio(List<Track> trackDeAudio) {
		this.trackDeAudio = trackDeAudio;
		
	}

	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}
	
	public void addPedidoProjeto(Pedido pedido){
		this.pedidos.add(pedido);
	}

	public List<TrackUsed> getTrackUseds() {
		return trackUseds;
	}

	public void setTrackUseds(List<TrackUsed> trackUseds) {
		this.trackUseds = trackUseds;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	
	
	
}
