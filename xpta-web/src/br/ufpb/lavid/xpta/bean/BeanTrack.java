package br.ufpb.lavid.xpta.bean;

import br.ufpb.lavid.xpta.controller.ControllerTrack;
import br.ufpb.lavid.xpta.model.Track;

public class BeanTrack {

	private ControllerTrack controllerTrack = new ControllerTrack();
	private Track track;
	
	
	public String novaTrack(){
		this.track = new Track();
		return "novaTrack";
	}
	
	/* Salva a track, caso ela já exista, atualiza*/
	public String salvarTrack(){
		return controllerTrack.salvarTrack(this.track);
		
	}
}
