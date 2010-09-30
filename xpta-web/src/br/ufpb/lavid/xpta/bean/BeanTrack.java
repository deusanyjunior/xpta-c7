package br.ufpb.lavid.xpta.bean;

import br.ufpb.lavid.xpta.controller.ControllerTrack;
import br.ufpb.lavid.xpta.model.Projeto;
import br.ufpb.lavid.xpta.model.Track;

public class BeanTrack {

	private ControllerTrack controllerTrack = new ControllerTrack();
	private Track track;
	
	
	public String invokeMethods(String nome, int volume, int pan, Projeto projeto, byte[] trackByte){
		try {
			novaTrack();
			this.track.setNome(nome);
			this.track.setVolume(volume);
			this.track.setPan(pan);
			this.track.setProjeto(projeto.getCodigo());
			this.track.setTrilha(trackByte);
			salvarTrack(this.track);
		return "sucesso";
		} catch (Exception e) {
			e.printStackTrace();
			return "erro";
		}
	}
	public String novaTrack(){
		this.track = new Track();
		return "novaTrack";
	}
	
	/* Salva a track, caso ela já exista, atualiza*/
	public String salvarTrack(Track track){
		return controllerTrack.salvarTrack(this.track);
		
	}

	public ControllerTrack getControllerTrack() {
		return controllerTrack;
	}

	public void setControllerTrack(ControllerTrack controllerTrack) {
		this.controllerTrack = controllerTrack;
	}

	public Track getTrack() {
		return track;
	}

	public void setTrack(Track track) {
		this.track = track;
	}
	
	
}
