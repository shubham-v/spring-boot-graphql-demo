package com.demo.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.demo.graphql.entities.Contact;
import com.demo.graphql.entities.Person;
import com.demo.graphql.repositories.ContactRepository;

public class PersonResolver implements GraphQLResolver<Person> {
    private final ContactRepository contactRepository;
    public PersonResolver(final ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }
    public Contact getContact(final Person person) {
        return contactRepository.findById(person.getId()).orElse(null);
    }
}
