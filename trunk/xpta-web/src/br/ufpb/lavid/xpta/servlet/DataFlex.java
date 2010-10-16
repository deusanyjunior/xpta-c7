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
import br.ufpb.lavid.xpta.util.InforVideo;

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
			
			
			out.println("<tracks>");
			
			for (int i = 0; i < tracks.size(); i++) {

				track = tracks.get(i);
				
				out.println("<track duracao = \""+track.getDuracao()+"\" nome = \""+track.getNome()+"\" codigo = \""+track.getCodigo()+"\" trilha = \""+track.getTrilha()+"\">");
				
				out.println("</track>");

			}
			
			out.println("</tracks>");
			
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
			
			trackUsed.setSolo(Integer.parseInt(request.getParameter("solo")));
			trackUsed.setMute(Integer.parseInt(request.getParameter("mute")));
			
			
			ControllerTrackUsed cTU = new ControllerTrackUsed();
			
			int idTrackUsed = cTU.salvarTrackUsed(trackUsed);
			
			projeto.getTrackUseds().add(trackUsed);
			
			controllerProjeto.editarProjeto2(projeto);
			
			System.out.println("idTrackUsed = "+idTrackUsed);
			
			out.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			out.println("<idTrackUsed>"+idTrackUsed+"</idTrackUsed>");
			
		}else if(action.equals("salvaTrackUsed")){
			
			ControllerProjeto controllerProjeto = new ControllerProjeto();
			
			Projeto projeto = (Projeto) request.getSession().getAttribute("projetoAtual");
			
			TrackUsed trackUsed = new TrackUsed();
			
			
			trackUsed.setCodigo(Integer.parseInt(request.getParameter("codigo")));
			System.out.println("1");
			
			trackUsed.setTimeCodeIn(Integer.parseInt(request.getParameter("timeCodeIn")));
			System.out.println("2");
			
			trackUsed.setTimeCodeOut(Integer.parseInt(request.getParameter("timeCodeOut")));
			System.out.println("3");
			
			trackUsed.setTrack(Integer.parseInt(request.getParameter("idTrack")));
			System.out.println("4");
			
			trackUsed.setPan(Integer.parseInt(request.getParameter("pan")));
			System.out.println("5");
			
			trackUsed.setVolume(Integer.parseInt(request.getParameter("volume")));
			System.out.println("6");
			
			trackUsed.setSolo(Integer.parseInt(request.getParameter("solo")));
			System.out.println("7");
			
			trackUsed.setMute(Integer.parseInt(request.getParameter("mute")));
			System.out.println("8");
			
			for(int i = 0; i < projeto.getTrackUseds().size(); i++){
				
				TrackUsed trackUsedAux = projeto.getTrackUseds().get(i);
				
				if(trackUsedAux.getCodigo() == trackUsed.getCodigo()){
					
					 System.out.println("compara "+trackUsedAux.getCodigo()+" == "+trackUsed.getCodigo());
					
					 projeto.getTrackUseds().remove(i);
					 break;
					
				}
				
			}
			
			ControllerTrackUsed cTU = new ControllerTrackUsed();
			cTU.salvarTrackUsed(trackUsed);			
			
			
			projeto.getTrackUseds().add(trackUsed);
			
			controllerProjeto.editarProjeto2(projeto);
			
			out.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			out.println("<root>");
			out.println("</root>");
			
			System.out.println("passa aqui f");
			
		}else if(action.equals("retornaProjeto")){
			
			ControllerProjeto controllerProjeto = new ControllerProjeto();
			
			Projeto projeto = (Projeto) request.getSession().getAttribute("projetoAtual");
			
			out.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			
			out.println("<root>");
			
			out.println("<projeto codigo=\""+projeto.getCodigo()+"\">");
			
			out.println("<trackUseds>");
			
			for(int i = 0; i < projeto.getTrackUseds().size(); i++){
				
				TrackUsed trackUsedAux = projeto.getTrackUseds().get(i);
				
				out.println("<trackUsed mute=\""+trackUsedAux.getMute()+"\" volume=\""+trackUsedAux.getVolume()+"\" solo=\""+trackUsedAux.getSolo()+"\" pan=\""+trackUsedAux.getPan()+"\" idTrack=\""+trackUsedAux.getTrack()+"\" timeCodeOut=\""+trackUsedAux.getTimeCodeOut()+"\"  timeCodeIn=\""+trackUsedAux.getTimeCodeIn()+"\"     codigo=\""+trackUsedAux.getCodigo()+"\">");
				out.println("</trackUsed>");
				
			}
			
			out.println("</trackUseds>");
			
			out.println("</projeto>");	
			
			out.println("</root>");
			
		}else if(action.equals("deletarTrackUsed")){
			
			ControllerProjeto controllerProjeto = new ControllerProjeto();
			ControllerTrackUsed cTU = new ControllerTrackUsed();
			
			Projeto projeto = (Projeto) request.getSession().getAttribute("projetoAtual");
			
			for(int i = 0; i < projeto.getTrackUseds().size(); i++){
				
				TrackUsed trackUsedAux = projeto.getTrackUseds().get(i);
				
				if(trackUsedAux.getCodigo() == Integer.parseInt(request.getParameter("codigo"))){
					
					System.out.println("compara  = "+trackUsedAux.getCodigo()+" == "+Integer.parseInt(request.getParameter("codigo")));
					
					 cTU.excluirTrackUsed(trackUsedAux.getCodigo());
					
					 projeto.getTrackUseds().remove(i);
					 break;
					
				}
				
			}
			
			controllerProjeto.editarProjeto2(projeto);
			
			out.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			out.println("<root>");
			out.println("</root>");
			
		}else if(action.equals("extraiDuracao")){
			
			String caminhoBase = request.getParameter("caminhoAudio");
			String fileName = request.getParameter("fileName");
			
			InforVideo iV = new InforVideo();
			
			String duracao = "";
			
			try {
				duracao = iV.exec(caminhoBase+"/"+fileName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("DURACAO ================ = "+duracao);
			
			out.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			out.println("<duracao>"+duracao+"</duracao>");
			
		}
		
		
		

		out.close();

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);

	}

}