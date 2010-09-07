package br.ufpb.lavid.xpta.dao;

import java.util.List;

import br.ufpb.lavid.xpta.model.Track;

public class DaoTrack extends DAOJPA<Track> {

	public DaoTrack(){
		super();
	}
	
	public List<Track> findAllTrack(){
		return (List<Track>) super.findAllByQuery("selec t from Track t");
		
	}
	
	public List<Track> findTrackByame(String nome){
		return (List<Track>) super.findAllByQuery("select t from Track t where t.nome ='"+ nome +"'");
	}

	//Fazer pesquisa das track pelo projeto.
}

