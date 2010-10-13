package br.ufpb.lavid.xpta.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufpb.lavid.xpta.controller.ControllerProjeto;
import br.ufpb.lavid.xpta.model.Projeto;
import br.ufpb.lavid.xpta.model.Track;

public class DataFlex extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public DataFlex() {

		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		ServletOutputStream out = null;

		response.setContentType("text/xml");

		out = response.getOutputStream();

		String action = request.getParameter("action");

		if (action.equals("registraAudio")) {
			
			ControllerProjeto controllerProjeto = new ControllerProjeto();
			
			//Projeto projeto = controllerProjeto.retornaProjeto();
			
			Projeto projeto = (Projeto) request.getSession().getAttribute("projetoAtual");
			
			//projeto.setNome("novo nome");
			
			Track track = new Track();
			track.setNome(request.getParameter("tituloAudio"));
			track.setPan(0);
			track.setProjeto(projeto.getCodigo());
			track.setTrilha(request.getParameter("caminhoAudio"));
			track.setVolume(0);
			
			projeto.getTrackDeAudio().add(track);
			
			controllerProjeto.editarProjeto2(projeto);
			
			System.out.println("ID PROJETO ATUAL = "+projeto.getCodigo());

		}

		out.close();

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);

	}

}