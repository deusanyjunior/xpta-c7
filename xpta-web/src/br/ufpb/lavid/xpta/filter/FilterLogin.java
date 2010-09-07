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
		
		if (userLogado != null ) {  
			if(userLogado.getPerfil().equals("Administrador"))
						chain.doFilter(req, resp);
			
			//tentando acessar paginas de coordenador
			if(	userLogado.getPerfil().equals("Usuario")){
			//else{
					if(req.getRequestURL().toString().contains("cadastroProjetos") || req.getRequestURL().toString().contains("userArea"))
					 		req.getRequestDispatcher("login.xhtml").forward(req, resp);					
					else chain.doFilter(req, resp);							
			}
		}
		else {
			req.getRequestDispatcher("login.xhtml").forward(req, resp);
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
