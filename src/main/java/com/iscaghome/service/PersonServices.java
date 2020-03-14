package com.iscaghome.service;

import com.iscaghome.exception.RecordNotFoundException;
import com.iscaghome.model.Person;
import com.iscaghome.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServices {

    @Autowired
    PersonRepository personRepository;

    public List<Person> retrieveAll(){
        List<Person> result = personRepository.findAll();
        if(result.size()>0){
            return result;
        }else{
            return new ArrayList<Person>();
        }
    }
    public Person retrieveById(Long id)throws RecordNotFoundException{
        Optional<Person> result = personRepository.findById(id);

        if(result.isPresent()){
            return result.get();
        }else{
            throw new RecordNotFoundException("Id does not exist", id);
        }
    }
    public Person createOrUpdate(Person person)throws RecordNotFoundException {
        if(person.getId()!=null){
            Optional<Person> result = personRepository.findById(person.getId());
            if(result.isPresent()){
                Person newPerson = result.get();
                newPerson.setFirstName(person.getFirstName());
                newPerson.setMiddleName(person.getMiddleName());
                newPerson.setLastName(person.getLastName());
                newPerson.setGender(person.getGender());
                newPerson.setContactNumber(person.getContactNumber());
                newPerson.setPresentAddress(person.getPresentAddress());
                newPerson.setHomeAddress(person.getHomeAddress());
                newPerson.setEmail(person.getEmail());
                newPerson.setImageData(person.getImageData());
                newPerson = personRepository.save(newPerson);

                return newPerson;
            }else{
                person = personRepository.save(person);
                return person;
            }

        }else{
            person = personRepository.save(person);
            return person;
        }

    }

    public void deleteById(Long id)throws RecordNotFoundException{
        Optional<Person> result = personRepository.findById(id);
        if(result.isPresent()){
            personRepository.deleteById(id);
        }else{
            throw new RecordNotFoundException("Id does not exist", id);
        }
    }
}
