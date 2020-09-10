package com.demo.graphql.repositories;

import com.demo.graphql.entities.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {
    Iterable<Contact> findByMobileNumber(String mobileNumber);
    Iterable<Contact> findByEmailId(String emailId);
}
