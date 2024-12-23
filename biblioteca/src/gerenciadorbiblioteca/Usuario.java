package gerenciadorbiblioteca;

import java.util.*;
      class Usuario {
	    private String nome;
	    private List<Livro> livrosEmprestados;

	    public Usuario(String nome) {
	        this.nome = nome;
	        this.livrosEmprestados = new ArrayList<>();
	    }

	    public String getNome() {
	        return nome;
	    }

	    public List<Livro> getLivrosEmprestados() {
	        return livrosEmprestados;
	    }

	    public void emprestarLivro(Livro livro) {
	        livrosEmprestados.add(livro);
	    }

	    public void devolverLivro(Livro livro) {
	        livrosEmprestados.remove(livro);
	    }

	    @Override
	    public String toString() {
	        return "Nome: " + nome + ", Livros emprestados: " + livrosEmprestados.size();
	    }
	}

