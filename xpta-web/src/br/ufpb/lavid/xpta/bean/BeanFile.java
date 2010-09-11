package br.ufpb.lavid.xpta.bean;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.IllegalFormatException;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.ajax4jsf.util.base64.EncoderException;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import br.ufpb.lavid.xpta.model.File;

public class BeanFile{

	private ArrayList<File> files = new ArrayList<File>();
	private int uploadsAvailable = 1;
	private boolean autoUpload = false;
	private boolean useFlash = false;
	private static final String separator = System.getProperty("file.separator");
	
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

	public void paint(OutputStream stream, Object object) throws IOException {
		stream.write(getFiles().get((Integer)object).getData());
	}
	public void listener(UploadEvent event) throws Exception{
		UploadItem item = event.getUploadItem();
		java.io.File file = item.getFile();
		System.out.println("instanciou o arquivo");
//		file.setLength(item.getFile().length());
//		file.setName(item.getFileName());
//		file.setData(item.getData());
//		files.add(file);
		gravarDadoOriginal(new FileInputStream(file), item.getFileName(), file.length());
		System.out.println("file add");
		uploadsAvailable--;
		System.out.println("fim do metodo");
	}  
	
	public void gravarDadoOriginal(FileInputStream fis, String nomeDoArquivo, long tamanhoDoArquivo) throws IOException,
	FileNotFoundException, IllegalArgumentException,
	IllegalFormatException, EncoderException {
		
		ServletContext sc = (ServletContext) FacesContext.getCurrentInstance()
				.getExternalContext().getContext();
		
//		String filepath = System.getProperty("catalina.base") + separator + "webapps" + separator + "xptafiles" + separator;
//		filepath = filepath + separator;
		
		String filepath = System.getProperty("user.home")+separator+"xptafiles"+separator;
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

	public ArrayList<File> getFiles() {
		return files;
	}

	public void setFiles(ArrayList<File> files) { 
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