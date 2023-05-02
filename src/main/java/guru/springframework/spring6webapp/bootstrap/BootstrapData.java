package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BootstrapData(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        Book book = new Book();
        book.setTitle("Domain Driven Design");
        book.setIsbn("123456");

        Author ericSaved = authorRepository.save(eric);
        Book bookSaved = bookRepository.save(book);

        Author rod = new Author();
        eric.setFirstName("Rod");
        eric.setLastName("Johnson");

        Book book2 = new Book();
        book2.setTitle("J2EE Development without EJB");
        book2.setIsbn("556789");

        Author rodSaved = authorRepository.save(rod);
        Book book2Saved = bookRepository.save(book2);

        ericSaved.getBooks().add(bookSaved);
        rodSaved.getBooks().add(book2Saved);

        authorRepository.save(ericSaved);
        authorRepository.save(rodSaved);

        System.out.println("Bootstrap data");
        System.out.println("Authors count: " + authorRepository.count());
        System.out.println("Books count: " + bookRepository.count());
    }
}
