package com.medicine.register.graphql.query;

import com.medicine.register.dao.entity.Patient;
import com.medicine.register.service.PatientService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PatientQuery implements GraphQLQueryResolver {
    private final PatientService patientService;

    public Optional<Patient> getPatient(String id){
        return patientService.getPatient(id);
    }
}
