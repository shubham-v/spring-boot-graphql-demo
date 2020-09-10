package com.demo.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.demo.graphql.entities.Contact;
import com.demo.graphql.entities.Person;
import com.demo.graphql.repositories.ContactRepository;
import com.demo.graphql.repositories.PersonRepository;

public class Mutation implements GraphQLMutationResolver {

    private final PersonRepository personRepository;
    private final ContactRepository contactRepository;

    public Mutation(PersonRepository personRepository, ContactRepository contactRepository) {
        this.personRepository = personRepository;
        this.contactRepository = contactRepository;
    }

    public Person newPerson(String name) {
        return personRepository.save(Person.builder().name(name).build());
    }
    public Boolean removePerson(Long id) {
        personRepository.deleteById(id);
        return true;
    }
    public Person updateName(Long id, String name) {
        return personRepository.findById(id).map(person -> {
                    person.setName(name);
                    return personRepository.save(person);
                }).orElse(null);
    }

    public Contact newContact(final String mobileNumber, final String emailId) {
        return contactRepository.save(Contact.builder().mobileNumber(mobileNumber).emailId(emailId).build());
    }
    public Contact updateMobileNumber(Long id, String mobileNumber) {
        return contactRepository.findById(id)
                .map(contact -> {
                    contact.setMobileNumber(mobileNumber);
                    return contactRepository.save(contact);
                }).orElse(null);
    }
    public Contact updateEmailId(Long id, String emailId) {
        return contactRepository.findById(id)
                .map(contact -> {
                    contact.setEmailId(emailId);
                    return contactRepository.save(contact);
                }).orElse(null);
    }
    public Boolean deleteContact(Long id) {
        contactRepository.deleteById(id);
        return true;
    }
}
