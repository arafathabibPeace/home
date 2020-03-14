package com.iscaghome.repository;

import com.iscaghome.model.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier(value = "personRepository")
public interface PersonRepository extends JpaRepository<Person, Long> {
}
