package com.medicine.register.infraestructure.exception;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Component
@ControllerAdvice
public class ExceptionHandlers extends DataFetcherExceptionResolverAdapter {
    public enum DefaultErrorClassification implements ErrorClassification {
        SERVER_ERROR
    }

    public GraphQLError exceptionHandler(Throwable ex, DataFetchingEnvironment env) {
        if (ex instanceof PatientNotFoundException patientNotFoundException) {
            return exceptionHandler(patientNotFoundException,env);
        } else {
            return GraphqlErrorBuilder.newError().message("Internal Server Error(s) while executing query").build();
        }
    }

    @ExceptionHandler(PatientNotFoundException.class)
    public GraphQLError exceptionHandler(PatientNotFoundException exception, DataFetchingEnvironment env){
        return GraphqlErrorBuilder.newError().message(exception.getMessage()).errorType(ErrorType.NOT_FOUND).path(env.getExecutionStepInfo().getPath()).location(env.getField().getSourceLocation()).build();
    }
}

