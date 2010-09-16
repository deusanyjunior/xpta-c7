package br.ufpb.lavid.xpta.bean;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.lang.Object;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.ajax4jsf.util.base64.EncoderException;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import br.ufpb.lavid.xpta.model.File2;
import br.ufpb.lavid.xpta.model.Projeto;

public class BeanFile{

	private ArrayList<File2> files = new ArrayList<File2>();
	private int uploadsAvailable = 1;
	private boolean autoUpload = false;
	private boolean useFlash = false;
	private static final String separator = System.getProperty("file.separator");
	private BeanProjeto beanProjeto;
	private BeanTrack beanTrack;
	
	public BeanFile() {
		
	}

	public int getSize() {
		if (getFiles().size()>0){
			return getFiles().size();
		}else 
		{
			return 0;
		}
	}
	
	public BeanProjeto getBeanProjeto() {
		return beanProjeto;
	}

	public void setBeanProjeto(BeanProjeto beanProjeto) {
		this.beanProjeto = beanProjeto;
	}

	public void paint(OutputStream stream, Object object) throws IOException {
		stream.write(getFiles().get((Integer)object).getData());
	}
	public void listener(UploadEvent event) throws Exception{


		UploadItem item = event.getUploadItem();
		
		java.io.File file = item.getFile();
		
		gravarDadoOriginal(new FileInputStream(file), item.getFileName(), file.length());
		System.out.println("file add");
		uploadsAvailable--;
		System.out.println("fim do metodo");
		System.out.print("nome: "+ file.getName());
		
	}  
	
	public void gravarDadoOriginal(FileInputStream fis, String nomeDoArquivo, long tamanhoDoArquivo) throws IOException,
	FileNotFoundException, IllegalArgumentException,
	IllegalFormatException, EncoderException {
		
		ServletContext sc = (ServletContext) FacesContext.getCurrentInstance()
				.getExternalContext().getContext();
		
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        int id = beanProjeto.getProjeto().getCodigo();
        System.out.print("id!!!!!! ====== " + id);
        //Projeto projeto = ((BeanProjeto) session. getComponentBean("beanProjeto", BeanProjeto.class)).getProjeto();
		
		String filepath = System.getProperty("catalina.base")+separator+"webapps"+separator+"Projetos"+separator + id;
		filepath = filepath + separator;
		
		FileOutputStream fos = new FileOutputStream(filepath + nomeDoArquivo);
		FileChannel fcIn = fis.getChannel();
		FileChannel fcOut = fos.getChannel();
		long totalLength = tamanhoDoArquivo;//video.length();
		int chunkSize = 8 * 1024 * 1024;
		long position = 0;
		long bytesLeft = totalLength;
		long totalWritten = 0;
		for (position = 0; position < totalLength; position += chunkSize) {
			long bytesToWrite = Math.min(bytesLeft, chunkSize);
			fcOut.transferFrom(fcIn, position, bytesToWrite);
			totalWritten = position + bytesToWrite;
			bytesLeft -= chunkSize;
		}
		fos.close();
		fis.close();
		
		
}

	public String clearUploadData() {
		files.clear();
		setUploadsAvailable(1);
		return null;
	}

	public long getTimeStamp(){
		return System.currentTimeMillis();
	}

	public ArrayList<File2> getFiles() {
		return files;
	}

	public void setFiles(ArrayList<File2> files) { 
		this.files = files;
	}

	public int getUploadsAvailable() {
		return uploadsAvailable;
	}

	public void setUploadsAvailable(int uploadsAvailable) {
		this.uploadsAvailable = uploadsAvailable;
	}

	public boolean isAutoUpload() {
		return autoUpload;
	}

	public void setAutoUpload(boolean autoUpload) {
		this.autoUpload = autoUpload;
	}

	public boolean isUseFlash() {
		return useFlash;
	}

	public void setUseFlash(boolean useFlash) {
		this.useFlash = useFlash;
	}

}