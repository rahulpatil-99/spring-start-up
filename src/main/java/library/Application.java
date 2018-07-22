package library;

import library.domain.Reader;
import library.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {


    @Autowired
    private ReaderRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        repository.deleteAll();

        // save a couple of customers
        repository.save(new Reader("Rahul", "Patil"));
        repository.save(new Reader("Vijay", "Patil"));
        repository.save(new Reader("Rahul", "Pandey"));

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Reader reader : repository.findAll()) {
            System.out.println(reader);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("Reader found with findByFirstName('Rahul'):");
        System.out.println("--------------------------------");
        for (Reader reader : repository.findByFirstName("Rahul")) {
            System.out.println(reader);
        }

        System.out.println("Customers found with findByLastName('Patil'):");
        System.out.println("--------------------------------");
        for (Reader reader : repository.findByLastName("Patil")) {
            System.out.println(reader);
        }
    }
}
