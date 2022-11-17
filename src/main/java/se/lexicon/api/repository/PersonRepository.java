package se.lexicon.api.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.api.model.entity.Person;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Integer> {

    List<Person> findAll();

    Optional<Person> findByEmail(String email);

}
