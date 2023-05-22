package one.udayyadav.pages.data;

import com.github.javafaker.Faker;
import one.udayyadav.pages.model.Address;
import one.udayyadav.pages.model.Person;
import one.udayyadav.pages.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;

@Component
public class SampleDataLoader implements CommandLineRunner {

    private final PersonRepository repository;
    private final Faker faker;

    public SampleDataLoader(PersonRepository repository, Faker faker) {
        this.repository = repository;
        this.faker = faker;
    }

    @Override
    public void run(String... args) throws Exception {

        // create 100 rows of people in the database
        List<Person> people = IntStream.rangeClosed(1, 10000)
                .mapToObj(i -> new Person(
                        faker.name().firstName(),
                        faker.name().lastName(),
                        faker.phoneNumber().cellPhone(),
                        faker.internet().emailAddress(),
                        new Address(
                                faker.address().streetAddress(),
                                faker.address().city()
                        )
                )).toList();

        repository.saveAll(people);
    }
}