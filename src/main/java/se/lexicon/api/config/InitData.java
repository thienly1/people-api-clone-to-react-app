package se.lexicon.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.lexicon.api.model.entity.Person;
import se.lexicon.api.repository.PersonRepository;

@Component
public class InitData implements CommandLineRunner {
    @Autowired
    PersonRepository repository;

    @Override
    public void run(String... args) throws Exception {
        repository.save(new Person("Åsa","Jonsoon","asa.Jonsson@lexicon.se","C# Teacher"));
        repository.save(new Person("Simon","Elbrink","simon.elbrink@lexicon.se","Java Teacher"));
        repository.save(new Person("Mehrdad","Javan","mehrdad.javan@lexicon.se","Java Teacher"));
    }
}
