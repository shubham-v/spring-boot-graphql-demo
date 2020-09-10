package com.demo.graphql.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "contacts")
public class Contact extends BaseAbstractEntity {

    @Column(name="contact_email_id")
    private String emailId;

    @Column(name="contact_mobile_number", nullable = false)
    private String mobileNumber;

    @OneToOne(mappedBy = "contact")
    private Person person;

}
