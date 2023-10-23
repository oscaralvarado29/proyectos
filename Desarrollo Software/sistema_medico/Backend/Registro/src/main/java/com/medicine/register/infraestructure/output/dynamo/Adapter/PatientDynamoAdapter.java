package com.medicine.register.infraestructure.output.dynamo.Adapter;

import com.medicine.register.domain.model.Patient;
import com.medicine.register.domain.spi.IPatientPersistencePort;
import com.medicine.register.infraestructure.exception.PatientNotFoundException;
import com.medicine.register.infraestructure.output.dynamo.entity.PatientEntity;
import com.medicine.register.infraestructure.output.dynamo.mapper.IPatientEntityMapper;
import com.medicine.register.infraestructure.output.dynamo.repository.IPatientRepository;
import com.medicine.register.infraestructure.util.Constants;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PatientDynamoAdapter implements IPatientPersistencePort {
    private final IPatientRepository patientRepository;
    private final IPatientEntityMapper patientMapper;

    /**
     * @param patient to save
     */
    @Override
    public void createPatient(Patient patient) {
        PatientEntity patientEntity = patientMapper.toPatientEntity(patient);
        patientRepository.save(patientEntity);
    }

    /**
     * @param id patient to get
     * @return patient
     */
    @Override
    public Patient getPatient(String id) {
        String exceptionMessage = String.format(Constants.PATIENT_NOT_FOUND,id);
        PatientEntity patientEntity = patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException(exceptionMessage));
        return patientMapper.toPatient(patientEntity);
    }
}