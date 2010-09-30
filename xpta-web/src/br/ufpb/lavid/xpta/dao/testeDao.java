package br.ufpb.lavid.xpta.dao;

import java.util.ArrayList;
import java.util.List;

import br.ufpb.lavid.xpta.model.Projeto;

public class testeDao {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DaoProjeto daoProjeto = new DaoProjeto();
		
		List<Projeto> projetos = new ArrayList<Projeto>();
		projetos = daoProjeto.findOtherProject(2);
		
		for(Projeto p: projetos){
			System.out.print("\nNome:!!!" + p.getNome());
		}
		

	}

}
