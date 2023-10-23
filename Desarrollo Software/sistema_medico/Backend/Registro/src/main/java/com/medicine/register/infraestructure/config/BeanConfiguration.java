package com.medicine.register.infraestructure.config;

import com.medicine.register.domain.api.IPatientServicePort;
import com.medicine.register.domain.spi.IPatientPersistencePort;
import com.medicine.register.domain.usecase.PatientUseCase;
import com.medicine.register.infraestructure.output.dynamo.Adapter.PatientDynamoAdapter;
import com.medicine.register.infraestructure.output.dynamo.mapper.IPatientEntityMapper;
import com.medicine.register.infraestructure.output.dynamo.repository.IPatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@ComponentScan(basePackages = "com.medicine.register.infraestructure.output.dynamo.repository")
public class BeanConfiguration {
    private final IPatientRepository patientRepository;
    private final IPatientEntityMapper patientMapper;
    @Bean
    public IPatientPersistencePort patientPersistencePort(){
        return new PatientDynamoAdapter(patientRepository,patientMapper);
    }

    @Bean
    public IPatientServicePort patientServicePort(){
        return new PatientUseCase(patientPersistencePort());
    }


}
