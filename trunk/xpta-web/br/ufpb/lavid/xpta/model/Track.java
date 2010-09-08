package br.ufpb.lavid.xpta.model;


import java.util.HashMap;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@SuppressWarnings("unchecked")
@Entity(name = "Track")
public class Track {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;

	private String nome;
	private int volume;
	private int pan;
	private byte[] trilha ={};
	private HashMap audio = new HashMap();
	
	@ManyToOne(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
	private Projeto projetos = new Projeto();

	public Track() {

	}
	
	public Track(String nome, int volume, int pan){
		this.nome = nome;
		this.volume = volume;
		this.pan = pan;
				
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

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public int getPan() {
		return pan;
	}

	public void setPan(int pan) {
		this.pan = pan;
	}

	public byte[] getTrilha() {
		return trilha;
	}

	public void setTrilha(byte[] trilha) {
		this.trilha = trilha;
	}

	public HashMap getAudio() {
		return audio;
	}

	public void setAudio(HashMap audio) {
		this.audio = audio;
	}

	public Projeto getProjetos() {
		return projetos;
	}

	public void setProjetos(Projeto projetos) {
		this.projetos = projetos;
	}	
	
}
