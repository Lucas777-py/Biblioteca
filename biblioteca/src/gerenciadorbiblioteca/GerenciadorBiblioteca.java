package gerenciadorbiblioteca;

import java.util.*;
public class GerenciadorBiblioteca {


	    private static List<Livro> livros = new ArrayList<>();
	    private static List<Usuario> usuarios = new ArrayList<>();

	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);

	        while (true) {
	            System.out.println("\n=== Gerenciador de Biblioteca ===");
	            System.out.println("1. Adicionar Livro");
	            System.out.println("2. Listar Livros");
	            System.out.println("3. Cadastrar Usuario");
	            System.out.println("4. Emprestar Livro");
	            System.out.println("5. Devolver Livro");
	            System.out.println("6. Listar Usuarios");
	            System.out.println("7. Sair");
	            System.out.print("Escolha uma opcao: ");

	            int opcao = scanner.nextInt();
	            scanner.nextLine(); // Consumir quebra de linha

	            switch (opcao) {
	                case 1:
	                    adicionarLivro(scanner);
	                    break;
	                case 2:
	                    listarLivros();
	                    break;
	                case 3:
	                    cadastrarUsuario(scanner);
	                    break;
	                case 4:
	                    emprestarLivro(scanner);
	                    break;
	                case 5:
	                    devolverLivro(scanner);
	                    break;
	                case 6:
	                    listarUsuarios();
	                    break;
	                case 7:
	                    System.out.println("Saindo...");
	                    scanner.close();
	                    return;
	                default:
	                    System.out.println("Opcao invalida!");
	            }
	        }
	    }

	    private static void adicionarLivro(Scanner scanner) {
	        System.out.print("Digite o titulo do livro: ");
	        String titulo = scanner.nextLine();
	        System.out.print("Digite o autor do livro: ");
	        String autor = scanner.nextLine();
	        System.out.print("Digite o genero do livro: ");
	        String genero = scanner.nextLine();

	        livros.add(new Livro(titulo, autor, genero));
	        System.out.println("Livro adicionado com sucesso!");
	    }

	    private static void listarLivros() {
	        if (livros.isEmpty()) {
	            System.out.println("Nenhum livro cadastrado.");
	        } else {
	            System.out.println("\n=== Lista de Livros ===");
	            for (Livro livro : livros) {
	                System.out.println(livro);
	            }
	        }
	    }

	    private static void cadastrarUsuario(Scanner scanner) {
	        System.out.print("Digite o nome do usuario: ");
	        String nome = scanner.nextLine();
	        usuarios.add(new Usuario(nome));
	        System.out.println("Usuario cadastrado com sucesso!");
	    }

	    private static void listarUsuarios() {
	        if (usuarios.isEmpty()) {
	            System.out.println("Nenhum usuario cadastrado.");
	        } else {
	            System.out.println("\n=== Lista de Usuarios ===");
	            for (Usuario usuario : usuarios) {
	                System.out.println(usuario);
	            }
	        }
	    }

	    private static void emprestarLivro(Scanner scanner) {
	        System.out.print("Digite o nome do usuario: ");
	        String nomeUsuario = scanner.nextLine();
	        Usuario usuario = usuarios.stream().filter(u -> u.getNome().equalsIgnoreCase(nomeUsuario)).findFirst().orElse(null);

	        if (usuario == null) {
	            System.out.println("Usuario nao encontrado.");
	            return;
	        }

	        System.out.print("Digite o titulo do livro: ");
	        String tituloLivro = scanner.nextLine();
	        Livro livro = livros.stream().filter(l -> l.getTitulo().equalsIgnoreCase(tituloLivro) && !l.isEmprestado()).findFirst().orElse(null);

	        if (livro == null) {
	            System.out.println("Livro nao encontrado ou ja esta emprestado.");
	            return;
	        }

	        livro.setEmprestado(true);
	        usuario.emprestarLivro(livro);
	        System.out.println("Livro emprestado com sucesso!");
	    }

	    private static void devolverLivro(Scanner scanner) {
	        System.out.print("Digite o nome do usuario: ");
	        String nomeUsuario = scanner.nextLine();
	        Usuario usuario = usuarios.stream().filter(u -> u.getNome().equalsIgnoreCase(nomeUsuario)).findFirst().orElse(null);

	        if (usuario == null) {
	            System.out.println("Usuario nao encontrado.");
	            return;
	        }

	        System.out.print("Digite o titulo do livro: ");
	        String tituloLivro = scanner.nextLine();
	        Livro livro = usuario.getLivrosEmprestados().stream().filter(l -> l.getTitulo().equalsIgnoreCase(tituloLivro)).findFirst().orElse(null);

	        if (livro == null) {
	            System.out.println("Livro nao encontrado entre os emprestados do usuario.");
	            return;
	        }

	        livro.setEmprestado(false);
	        usuario.devolverLivro(livro);
	        System.out.println("Livro devolvido com sucesso!");
	    
	}

}
