package br.ufpb.lavid.xpta.controller;



import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import br.ufpb.lavid.xpta.dao.DaoTrack;
import br.ufpb.lavid.xpta.model.Track;

public class ControllerTrack {
	
		private DaoTrack daoTrack = new DaoTrack();
		private Track track;
		private DataModel dataModel;
		
		public String novaTrack(){
			this.track = new Track();
			
			return "novaTrack";
		}
		
		/* Salva a track, caso ela já exista, atualiza*/
		
		public String salvarTrack(){
			daoTrack.begin();
			if (track.getCodigo() == 0){
				daoTrack.persist(track);
				daoTrack.commit();
			}else{
				
				daoTrack.merge(track);
				daoTrack.commit();

			}
			daoTrack.close();
			return "tracksalva";
		}
		
	/* ***************Listar Todas as Track ****************** */

		public DataModel getListaTrack(){
			
			dataModel = new ListDataModel(daoTrack.findAll());
			return dataModel;
		}

	/* ***************Editar Track ******************* */
		public String editarTrack(){
			track = (Track) dataModel.getRowData();
			setTrack(track);
			return "trackEditada";
		}
	/* ***************Excluir Track ****************** */
		
		public String excluirTrack(){
			track = (Track) dataModel.getRowData();
			daoTrack.begin();
			daoTrack.remove(track);
			daoTrack.commit();
			daoTrack.close();
			
			return "trackExcluida";
		}
		
	/* ************** Getters and Setters ************** */
		public Track getTrack() {
			return track;
		}

		public void setTrack(Track track) {
			this.track = track;
		}
}
