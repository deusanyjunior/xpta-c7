package br.ufpb.lavid.xpta.controller;

import java.util.Date;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.xml.crypto.Data;

import br.ufpb.lavid.xpta.dao.DaoProjeto;
import br.ufpb.lavid.xpta.model.Pedido;
import br.ufpb.lavid.xpta.model.Projeto;
import br.ufpb.lavid.xpta.model.Usuario;

public class ControllerProjeto {

	private DaoProjeto daoProjeto = new DaoProjeto();
	private DataModel dataModel;

	public String salvarProjeto(Projeto p){
		try {
			
			daoProjeto.begin();
			if (p.getCodigo() == 0){
				p.setDataCriacao(new Date());
				daoProjeto.persist(p);
				daoProjeto.commit();
				
			}else{
				p.setDataUltimaModificacao(new Date());
				daoProjeto.merge(p);
				daoProjeto.commit();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			daoProjeto.close();
		}
	
		return "projetosalvo";
	}
	
/* ***************Listar Todos os Projetos ****************** */

	public DataModel listaProjetos(){
		
		dataModel = new ListDataModel(daoProjeto.findAll());
		return dataModel;
	}
	
/* ***************Listar Todos os Projetos P�blicos ****************** */

	public DataModel listaProjetosPublicos(){
		
		dataModel = new ListDataModel(daoProjeto.findProjectByPermission());
		return dataModel;
	}
		
/* ***************Editar Projeto ******************* */
	public String editarProjeto(Projeto p){
		try {
			p = (Projeto) dataModel.getRowData();
			daoProjeto.begin();
			daoProjeto.merge(p);
			daoProjeto.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			daoProjeto.close();
		}
	
		return "projetoEditado";
	}
	
/* ***************Excluir Projeto ****************** */
	public String excluirProjeto(Projeto p){
		try {
			p = (Projeto) dataModel.getRowData();
			daoProjeto.begin();
			daoProjeto.remove(p);
			daoProjeto.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			daoProjeto.close();
		}
	
		return "projetoExcluido";
	}
	
	public void addPedidoProjeto(Projeto p, Pedido pedido){
		p.addPedidoProjeto(pedido);
		salvarProjeto(p);
		
	}
	
	public Projeto retornaProjeto(){
		Projeto projeto = (Projeto) dataModel.getRowData();
		return projeto;
	}
	
	public DataModel listaMeusProjetos(Usuario user){
		System.out.print("\nAcessou o controller e chamou o dao");
		dataModel = new ListDataModel(daoProjeto.findProjectByAutor(user.getCodigo()));
		System.out.print("\ndepois do dao");
		return dataModel;
	}
	
	public DataModel listaProjetosParaEdicao(Usuario user){
		dataModel = new ListDataModel(daoProjeto.findProjectEdition(user));
		return dataModel;
	}
	
	public DataModel listaOutrosProjetos(Usuario user){
		dataModel = new ListDataModel(daoProjeto.findOtherProject(user));
		return dataModel;
	}
}
