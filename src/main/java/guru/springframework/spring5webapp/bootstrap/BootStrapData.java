package guru.springframework.spring5webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;

@Component
public class BootStrapData implements CommandLineRunner {

  private final AuthorRepository authorRepository;
  private final BookRepository bookRepository;
  private final PublisherRepository publisherRepository;

  public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
    this.authorRepository = authorRepository;
    this.bookRepository = bookRepository;
    this.publisherRepository = publisherRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    Publisher penguin = new Publisher();
    penguin.setName("Penguin");
    penguin.setAddressLine1("51 Test Street");
    penguin.setState("CA");

    Author rowling = new Author("JK", "Rowling");
    Book potter = new Book("Harry Potter and the Prisoner of Azkaban", "123456789456123456789");

    rowling.getBooks().add(potter);
    potter.getAuthors().add(rowling);
    potter.setPublisher(penguin);
    penguin.getBooks().add(potter);

    authorRepository.save(rowling);
    bookRepository.save(potter);
    publisherRepository.save(penguin);

    Author king = new Author("Stephen", "King");
    Book it = new Book("It", "478965413476546918");
    Book misery = new Book("Misery", "47847589894786947");

    king.getBooks().add(it);
    king.getBooks().add(misery);
    it.getAuthors().add(king);
    misery.getAuthors().add(king);
    it.setPublisher(penguin);
    misery.setPublisher(penguin);
    penguin.getBooks().add(it);
    penguin.getBooks().add(misery);

    authorRepository.save(king);
    bookRepository.save(it);
    bookRepository.save(misery);
    publisherRepository.save(penguin);

    System.out.println("Started in Bootstrap");
    System.out.println("Number of Books: " + bookRepository.count());
    System.out.println("Number of publisher books: " + penguin.getBooks().size());
  }

}