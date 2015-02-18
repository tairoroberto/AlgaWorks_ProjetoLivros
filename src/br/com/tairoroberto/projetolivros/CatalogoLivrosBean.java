package br.com.tairoroberto.projetolivros;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import br.com.tairoroberto.domain.Livro;

@ManagedBean
@SessionScoped
public class CatalogoLivrosBean  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6146717505981258654L;
	private String livroPesquisa;
	private Livro livro;
	private Livro livroSelecionado;
	private List<Livro> livros;
	private List<Livro> livrosFiltrados;
	
	public CatalogoLivrosBean() {
		this.livros = new ArrayList<Livro>();
		this.livrosFiltrados = new ArrayList<Livro>();
		this.livro = new Livro();
	}
	
	//method to save book
	public void incluirLivro() {
		if (this.livro.getTitle() == null || this.livro.getTitle().length() < 3) {
			this.adicionarMessagem("formLivros:title", FacesMessage.SEVERITY_ERROR, 
					"Título incompleto", "Por favor informar o título completo do livro.");
		}
		if (this.livro.getAuthor() == null || this.livro.getAuthor().length() < 3) {
			this.adicionarMessagem("formLivros:title", FacesMessage.SEVERITY_ERROR, 
					"Nome do Autor incompleto", "Por favor informar o nome do autor.");
		}
		if (this.livro.getTitle() == null || this.livro.getTitle().length() < 3) {
			this.adicionarMessagem("formLivros:title", FacesMessage.SEVERITY_ERROR, 
					"Descrição inválida", "Por favor informar uma descrição para o livro.");
		}
		
		FacesContext context = FacesContext.getCurrentInstance();
		if (!context.getMessages().hasNext()) {
			//Aqui poderia gravar o livro no banco de dados
			this.livros.add(this.livro);
			this.livro = new Livro();
			atualizaListaLivros(null);
			
			this.adicionarMessagem(null, FacesMessage.SEVERITY_INFO, 
					"Livro cadastrado", "Livro cadastrado com sucesso...!");
		}
		
		
	}
	
	//method to generate a message
	private void adicionarMessagem(String clienteId, Severity severity, String sumary, String detail){
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(severity, sumary, detail);
		
		//add message to queue
		context.addMessage(clienteId, message);
	}
	
	//methid to delete book
	public void excluir() {
		this.livros.remove(this.livroSelecionado);
		this.adicionarMessagem(null, FacesMessage.SEVERITY_INFO, 
				"Livro deletado", "Livro deletado com sucesso...!");
		 atualizaListaLivros(null);
	}
		
	//method call when is change input value
		public void livroPesquisaAlterado(ValueChangeEvent event) {
			System.out.println("Valor atual: "+ this.livroPesquisa);
			System.out.println("Novo Valor: "+ event.getNewValue());
			
			atualizaListaLivros(event.getNewValue().toString()); 
		}
		
		
	//method to refresh list	
	public void atualizaListaLivros(String busca) {
		//clear list
		this.livrosFiltrados.clear();
		
		for (Livro livro : this.livros) {
			if (busca != null) {
				if (livro.getTitle() != null && livro.getTitle().toLowerCase().startsWith(busca.toLowerCase())) {
					this.livrosFiltrados.add(livro);
				}
			}
		}			
	}
		
	//method to redirect link
	public String  obterAjuda() {
		if (livros.isEmpty()) {
			return "AjudaGestaoLivros?faces-redirect=true";
		}else{
			return "AjudaGestaoLivrosTelefone?faces-redirect=true";
		}		
	}
		
	//Getters and Setters	

	public Livro getLivro() {
		return livro;
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public Livro getLivroSelecionado() {
		return livroSelecionado;
	}

	public void setLivroSelecionado(Livro livroSelecionado) {
		this.livroSelecionado = livroSelecionado;
	}

	public List<Livro> getLivrosFiltrados() {
		return livrosFiltrados;
	}

	public void setLivrosFiltrados(List<Livro> livrosFiltrados) {
		this.livrosFiltrados = livrosFiltrados;
	}

	public String getLivroPesquisa() {
		return livroPesquisa;
	}

	public void setLivroPesquisa(String livroPesquisa) {
		this.livroPesquisa = livroPesquisa;
	}	
	
	
	
	
}
