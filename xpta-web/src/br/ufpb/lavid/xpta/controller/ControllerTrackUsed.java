package br.ufpb.lavid.xpta.controller;

import javax.faces.model.DataModel;

import br.ufpb.lavid.xpta.dao.DaoTrackUsed;
import br.ufpb.lavid.xpta.model.TrackUsed;

public class ControllerTrackUsed {
	
		private DaoTrackUsed daoTrackUsed = new DaoTrackUsed();
		private TrackUsed trackUsed;
		private DataModel dataModel;
			
		public int salvarTrackUsed(TrackUsed trackUsed){
			
			daoTrackUsed.begin();
			
			if (trackUsed.getCodigo() == 0)
			{
				
				daoTrackUsed.persist(trackUsed);
				daoTrackUsed.commit();
				
			}else
			{
				
				daoTrackUsed.merge(trackUsed);
				daoTrackUsed.commit();

			}
			
			daoTrackUsed.close();
			
			return trackUsed.getCodigo();
			
		}
		
	/* ***************Listar Todas as Track ****************** */

//		public DataModel getListaTrack(){
//			
//			dataModel = new ListDataModel(daoTrack.findAll());
//			return dataModel;
//		}

	/* ***************Editar Track ******************* */
//		public String editarTrack(){
//			track = (Track) dataModel.getRowData();
//			setTrack(track);
//			return "trackEditada";
//		}
	/* ***************Excluir Track ****************** */
//		public String excluirTrack(){
//			track = (Track) dataModel.getRowData();
//			daoTrack.begin();
//			daoTrack.remove(track);
//			daoTrack.commit();
//			daoTrack.close();
//			
//			return "trackExcluida";
//		}
	/* ************** Getters and Setters ************** */
//		public Track getTrack() {
//			return track;
//		}
//
//		public void setTrack(Track track) {
//			this.track = track;
//		}
		
}
