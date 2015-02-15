package br.com.tairoroberto.projetolivros;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.tairoroberto.domain.Livro;

@ManagedBean
@SessionScoped
public class CatalogoLivrosBean  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6146717505981258654L;
	private Livro livro;
	private List<Livro> livros;
	
	public CatalogoLivrosBean() {
		this.livros = new ArrayList<Livro>();
		this.livro = new Livro();
	}
	
	public void incluirLivro() {
		this.livros.add(this.livro);
		this.livro = new Livro();
	}

	public Livro getLivro() {
		return livro;
	}

	public List<Livro> getLivros() {
		return livros;
	}	
	
}
