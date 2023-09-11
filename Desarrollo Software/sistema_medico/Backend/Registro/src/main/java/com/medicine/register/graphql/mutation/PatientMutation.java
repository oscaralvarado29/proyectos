package com.medicine.register.graphql.mutation;

import com.medicine.register.service.PatientService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PatientMutation implements GraphQLMutationResolver {
    private final PatientService patientService;

    public String createPatient (String id, String firstName, String secondName, String firstSurName,
                                 String secondSurName, String address, String email,
                                 String landline, String cellPhone, String residencesType,
                                 String descriptionResidence, String neighborhood, String password){
        return this.patientService.createPatient(id, firstName, secondName, firstSurName,
                secondSurName, address, email, landline, cellPhone, residencesType,
                descriptionResidence, neighborhood, password);
    }
}
