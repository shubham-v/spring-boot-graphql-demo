package com.demo.graphql.beans;

import com.demo.graphql.exceptions.GraphQLErrorAdapter;
import com.demo.graphql.repositories.ContactRepository;
import com.demo.graphql.repositories.PersonRepository;
import com.demo.graphql.resolvers.Mutation;
import com.demo.graphql.resolvers.PersonResolver;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.GraphQLErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class BeansConfiguration {

    @Bean
    public com.demo.graphql.resolvers.Query query(final PersonRepository personRepository, final ContactRepository contactRepository) {
        return new com.demo.graphql.resolvers.Query(personRepository, contactRepository);
    }

    @Bean
    public Mutation mutation(final PersonRepository personRepository, final ContactRepository contactRepository) {
        return new Mutation(personRepository, contactRepository);
    }

    @Bean
    public PersonResolver personResolver(final ContactRepository contactRepository) {
        return new PersonResolver(contactRepository);
    }

    @Bean
    public GraphQLErrorHandler errorHandler() {
        return new GraphQLErrorHandler() {
            @Override
            public List<GraphQLError> processErrors(List<GraphQLError> errors) {
                List<GraphQLError> clientErrors = errors.stream()
                        .filter(this::isClientError)
                        .collect(Collectors.toList());

                List<GraphQLError> serverErrors = errors.stream()
                        .filter(e -> !isClientError(e))
                        .map(GraphQLErrorAdapter::new)
                        .collect(Collectors.toList());

                List<GraphQLError> e = new java.util.ArrayList<>();
                e.addAll(clientErrors);
                e.addAll(serverErrors);
                return e;
            }

            protected boolean isClientError(GraphQLError error) {
                return !(error instanceof ExceptionWhileDataFetching || error instanceof Throwable);
            }
        };
    }

}
