package br.ufpb.lavid.xpta.servlet;



import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileUpload extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public FileUpload() {
		
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
		
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String contentType = request.getContentType();

		String caminhoRepositorio = request.getParameter("caminhoRepositorio");
		
		String nomeArquivo = request.getParameter("nomeArquivo");

		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		factory.setSizeThreshold(4096);
		
		factory.setRepository(new File("/tmp"));

		ServletFileUpload upload = new ServletFileUpload(factory);

		upload.setSizeMax(100000000);

		List fileItems;

		try {
			
			fileItems = upload.parseRequest(request);

			Iterator i = fileItems.iterator();

			String comment = ((FileItem) i.next()).getString();

			FileItem fi = (FileItem) i.next();

			String fileName = nomeArquivo;

			fi.write(new File(caminhoRepositorio, fileName));

		} catch (FileUploadException e) {

			e.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}
	
}