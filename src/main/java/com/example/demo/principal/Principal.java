package com.example.demo.principal;

import com.example.demo.model.*;
import com.example.demo.repository.BooksRepository;
import com.example.demo.service.ConsumoApi;
import com.example.demo.service.ConverterData;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverterData conversor = new ConverterData();
    private final String ENDERECO = "http://gutendex.com/books/?search=";

    private BooksRepository repositorio;

    public Principal(BooksRepository repository) {
        this.repositorio = repository;
    }

    public void exibeMenu() {
        var opcao = -1;
        while(opcao != 0) {
            var menu = """
                    1 - Buscar séries
                    2 - listar livros registrados
                    3 - listar autores registrados
                    4 - lsitar autores vivos em umm derterminado ano
                    5 - listar livros em um determinado idioma
                    0 - Sair""";

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    getDadosSerie();
                    break;
                case 2:
                    getAllBooks();
                    break;
                case 3:
                    getAllAuthors();
                    break;
                case 4:
                    getAuthorByYear();
                    break;
                case 5:
                    getBookByLanguage();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private BookData getDadosSerie() {
        System.out.println("Digite o nome da livro para busca");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+"));
        BookData data = conversor.obterDados(json, BookData.class);

        Book book = new Book(data);

        List<Author> authors = data.results().get(0).authors().stream().map(a -> new Author(a.name(), a.birthYear(), a.deathYear(), book))
                .toList();

        List<Language> Languages = data.results().get(0).languages().stream().map(a -> new Language(a, book))
                .toList();

        book.setAuthors(authors);
        book.setLanguages(Languages);

        repositorio.save(book);
        return data;
    }

    private void getAllBooks() {
        var books = repositorio.findAll();
        books.stream()
                .sorted(Comparator.comparing(Book::getTitle))
                .forEach(System.out::println);
    }

    private void getAllAuthors() {
        List<Author> authors = repositorio.getAllAuthor();
        authors.stream()
                .sorted(Comparator.comparing(Author::getName))
                .forEach(System.out::println);
    }

    private void getAuthorByYear() {
        System.out.println("Digite o ano que deseja ver os autores que estão vivos nesse ano:");
        int year = leitura.nextInt();

        List<Author> authors = repositorio.getAuthorsByYearOfLife(year);

        authors.stream()
                .sorted(Comparator.comparing(Author::getName))
                .forEach(System.out::println);
    }

    private void getBookByLanguage() {
        System.out.println("Digite a linguagem do livro que deseja escolher:");
        var language = leitura.nextLine();

        List<Book> books = repositorio.findByLanguageIgnoreCase(language);

        books.stream()
                .sorted(Comparator.comparing(Book::getTitle))
                .forEach(System.out::println);
    }
}
