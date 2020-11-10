package com.demo.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.demo.graphql.entities.Contact;
import com.demo.graphql.entities.Person;
import com.demo.graphql.repositories.ContactRepository;
import com.demo.graphql.repositories.PersonRepository;

import java.util.List;

public class Query implements GraphQLQueryResolver {

    private final PersonRepository personRepository;
    private final ContactRepository contactRepository;

    public Query(PersonRepository personRepository, ContactRepository contactRepository) {
        this.personRepository = personRepository;
        this.contactRepository = contactRepository;
    }

    public Iterable<Person> findAllPerson() {
        return personRepository.findAll();
    }
    public Long countPersons() {
        return personRepository.count();
    }
    public Iterable<Person> findByName(String name) {
        return personRepository.findByName(name);
    }
    public Person findPersonById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    public Iterable<Contact> findAllContact() { return contactRepository.findAll(); }
    public Long countContacts() { return contactRepository.count(); }
    public Iterable<Contact> findByMobileNumber(String mobileNumber) {
        return contactRepository.findByMobileNumber(mobileNumber);
    }
    public Iterable<Contact> findByEmailId(String emailId) {
        return contactRepository.findByEmailId(emailId);
    }

}
