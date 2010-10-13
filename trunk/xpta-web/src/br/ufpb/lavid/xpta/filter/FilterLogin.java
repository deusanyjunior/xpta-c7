package br.ufpb.lavid.xpta.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufpb.lavid.xpta.model.Pessoa;

/**
 * Servlet Filter implementation class FilterLogin
 */
public class FilterLogin implements Filter {

	private FilterConfig filterConfig;
	
	public void destroy() {
		this.setfilterConfig(null);	
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse)response;

		/**
		 * Testa se o coordenador est� logado e bloqueia o acesso via URL das
		 * paginas abaixo caso n�o esteja logado de acordo com suas prioriades
		 */
		Pessoa userLogado = (Pessoa)req.getSession().getAttribute("user");
		
		if (userLogado == null){
			req.getRequestDispatcher("home.xpta").forward(req, resp);
		}
		
		
		if(userLogado!=null){
			if(req.getRequestURL().toString().contains("areaUsuario")){
				req.getRequestDispatcher("areaUsuario.xpta").forward(req, resp);
			}
				
		}
		

		
		
		if(userLogado!=null){
			if(req.getRequestURL().toString().contains("editarProjeto")){
				req.getRequestDispatcher("editarProjeto.xpta").forward(req, resp);
			}
				
		}
		
					
}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.setfilterConfig(filterConfig);

	}


	public void setfilterConfig(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	public FilterConfig getfConfig() {
		return filterConfig;
	}

}
