package com.medicine.register.infrastructure.exception;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;

@Component
public class ExceptionHandlers extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        if (ex instanceof PatientNotFoundException) {
            ErrorType errorType;
            errorType = ErrorType.NOT_FOUND;
            return graphQLError(errorType, (PatientNotFoundException) ex, env);

        }
            return GraphqlErrorBuilder.newError().build();
        }


    private GraphQLError graphQLError(ErrorType errorType, Exception ex, DataFetchingEnvironment env){
        return GraphqlErrorBuilder.newError()
                .errorType(errorType)
                .message(ex.getMessage())
                .path(env.getExecutionStepInfo().getPath())
                .location(env.getField().getSourceLocation())
                .build();
    }
}

