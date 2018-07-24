package com.jrt.spring5webapp.bootstrap;

import com.jrt.spring5webapp.model.Author;
import com.jrt.spring5webapp.model.Book;
import com.jrt.spring5webapp.model.Publisher;
import com.jrt.spring5webapp.repositories.AuthorRepository;
import com.jrt.spring5webapp.repositories.BookRepository;
import com.jrt.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        Author eric = new Author("Eric", "Evans");
        Publisher pub1 = new Publisher("Harper Collins", "A street");
        Book ddd = new Book("Domain Driven Design", "1234", pub1);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        publisherRepository.save(pub1);
        bookRepository.save(ddd);

        Author rod = new Author("Rod", "Johnson");
        Publisher pub2 = new Publisher("Worx", "B street");
        Book noEJB = new Book("J2EE Development without EJB", "23444", pub2);
        rod.getBooks().add(noEJB);

        authorRepository.save(rod);
        publisherRepository.save(pub2);
        bookRepository.save(noEJB);
    }
}
