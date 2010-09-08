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
import javax.persistence.ManyToMany;
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
	private int dono;
	
	@Temporal(TemporalType.DATE)
	private Date dataCriacao;
	
	@Temporal(TemporalType.DATE)
	private Date dataUltimaModificacao;
	
	@ManyToMany(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
	private List<Usuario> usuario;
	
	@OneToMany(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
	private List<Track> trackDeAudio = new ArrayList<Track>();
	
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
	
	public List<Usuario> getUsuario() {
		return usuario;
	}
	public void setUsuario(List<Usuario> usuario) {
		this.usuario = usuario;
	}
	public List<Track> getTrackDeAudio() {
		return trackDeAudio;
	}
	public void setTrackDeAudio(List<Track> trackDeAudio) {
		this.trackDeAudio = trackDeAudio;
		
	}
	public int getDono() {
		return dono;
	}
	public void setDono(int dono) {
		this.dono = dono;
	}
	
}
