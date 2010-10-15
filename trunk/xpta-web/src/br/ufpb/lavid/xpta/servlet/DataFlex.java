package br.ufpb.lavid.xpta.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufpb.lavid.xpta.controller.ControllerProjeto;
import br.ufpb.lavid.xpta.controller.ControllerTrackUsed;
import br.ufpb.lavid.xpta.model.Projeto;
import br.ufpb.lavid.xpta.model.Track;
import br.ufpb.lavid.xpta.model.TrackUsed;

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
			track.setProjeto(projeto.getCodigo());
			track.setTrilha(request.getParameter("caminhoAudio"));
			
			projeto.getTrackDeAudio().add(track);
			
			controllerProjeto.editarProjeto2(projeto);
			
			out.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			out.println("<root>");
			out.println("</root>");
			
			//System.out.println("ID PROJETO ATUAL = "+projeto.getCodigo());

		}else if (action.equals("listarAudios")) 
		{
		
			Projeto projeto = (Projeto) request.getSession().getAttribute("projetoAtual");
			
			Track track;
			
			List<Track> tracks = projeto.getTrackDeAudio();
			
			out.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			
			out.println("<root>");
			
			for (int i = 0; i < tracks.size(); i++) {

				track = tracks.get(i);
				
				out.println("<track nome = \""+track.getNome()+"\" codigo = \""+track.getCodigo()+"\" trilha = \""+track.getTrilha()+">");
				
				out.println("</track>");

			}
			
			out.println("</root>");
			
		}else if (action.equals("registraTrackUsed")) 
		{
			
			ControllerProjeto controllerProjeto = new ControllerProjeto();
			
			//Projeto projeto = controllerProjeto.retornaProjeto();
			
			Projeto projeto = (Projeto) request.getSession().getAttribute("projetoAtual");
			
			//projeto.setNome("novo nome");
			
			TrackUsed trackUsed = new TrackUsed();
			
			trackUsed.setTimeCodeIn(Integer.parseInt(request.getParameter("timeCodeIn")));
			trackUsed.setTimeCodeOut(Integer.parseInt(request.getParameter("timeCodeOut")));
			trackUsed.setTrack(Integer.parseInt(request.getParameter("idTrack")));
			trackUsed.setPan(Integer.parseInt(request.getParameter("pan")));
			trackUsed.setVolume(Integer.parseInt(request.getParameter("volume")));
			
			
			ControllerTrackUsed cTU = new ControllerTrackUsed();
			
			int idTrackUsed = cTU.salvarTrackUsed(trackUsed);
			
			projeto.getTrackUseds().add(trackUsed);
			
			controllerProjeto.editarProjeto2(projeto);
			
			System.out.println("idTrackUsed = "+idTrackUsed);
			
			out.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			out.println("<root>");
			out.println("<idTrackUsed>"+idTrackUsed+"</idTrackUsed>");
			out.println("</root>");
			
		}

		out.close();

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);

	}

}