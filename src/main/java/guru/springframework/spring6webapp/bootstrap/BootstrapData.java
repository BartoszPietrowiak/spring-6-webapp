package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    private final PublisherRepository publisherRepository;

    public BootstrapData(BookRepository bookRepository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        Book book = new Book();
        book.setTitle("Domain Driven Design");
        book.setIsbn("123456");

        Publisher john = new Publisher();
        john.setPublisherName("John");
        john.setCity("Poznan");
        john.setAddress("ul. Krysiewicza");
        john.setZip("60-651");
        john.setState("Wielkopolska");

        Author ericSaved = authorRepository.save(eric);
        Book bookSaved = bookRepository.save(book);
        Publisher johnSaved = publisherRepository.save(john);

        Author rod = new Author();
        eric.setFirstName("Rod");
        eric.setLastName("Johnson");

        Book book2 = new Book();
        book2.setTitle("J2EE Development without EJB");
        book2.setIsbn("556789");

        Publisher jan = new Publisher();
        john.setPublisherName("Jan");
        john.setCity("Poznan");
        john.setAddress("ul. Krysiewicza");
        john.setZip("60-651");
        john.setState("Wielkopolska");

        Publisher janSaved = publisherRepository.save(jan);

        Author rodSaved = authorRepository.save(rod);
        Book book2Saved = bookRepository.save(book2);

        ericSaved.getBooks().add(bookSaved);
        rodSaved.getBooks().add(book2Saved);

        authorRepository.save(ericSaved);
        authorRepository.save(rodSaved);

        System.out.println("Bootstrap data");
        System.out.println("Authors count: " + authorRepository.count());
        System.out.println("Books count: " + bookRepository.count());
        System.out.println("Publisher count: " + publisherRepository.count());
    }
}
