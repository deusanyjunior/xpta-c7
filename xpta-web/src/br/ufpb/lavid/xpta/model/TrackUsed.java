package br.ufpb.lavid.xpta.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class TrackUsed {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;
	
	private int volume;
	private int pan;
	
	private int timeCodeIn;
	private int timeCodeOut;
	
	private int track;

	public TrackUsed() {

	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getTimeCodeIn() {
		return timeCodeIn;
	}

	public void setTimeCodeIn(int timeCodeIn) {
		this.timeCodeIn = timeCodeIn;
	}

	public int getTimeCodeOut() {
		return timeCodeOut;
	}

	public void setTimeCodeOut(int timeCodeOut) {
		this.timeCodeOut = timeCodeOut;
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

	public int getTrack() {
		return track;
	}

	public void setTrack(int track) {
		this.track = track;
	}
	
	
	
	
}
