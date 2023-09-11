package com.medicine.register.service;

import com.google.gson.Gson;
import com.medicine.register.dao.entity.Patient;
import com.medicine.register.dao.repository.IPatientRepository;
import com.medicine.register.logger.ILoggerStrategy;
import com.medicine.register.util.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final IPatientRepository patientRepository;
    private final ILoggerStrategy logStrategy;
    @Transactional
    public String createPatient (String id, String firstName, String secondName, String firstSurName,
                                String secondSurName, String address, String email,
                                String landline, String cellPhone, String residencesType,
                                String descriptionResidence, String neighborhood, String password){
        Gson gson = new Gson();
        String passwordCifrada = "contraseña encriptada";
        Patient patient = new Patient();
        patient.setId(id);
        patient.setFirstName(firstName);
        if(!secondName.isEmpty() && !secondName.isBlank()) {
            patient.setSecondName(secondName);
        }
        patient.setFirstSurName(firstSurName);
        patient.setSecondSurName(secondSurName);
        patient.setAddress(address);
        patient.setEmail(email);
        if(!landline.isEmpty() && !landline.isBlank()) {
            patient.setLandline(landline);
        }
        patient.setCellPhone(cellPhone);
        patient.setResidencesType(residencesType);
        if(!descriptionResidence.isEmpty() && !descriptionResidence.isBlank()) {
            patient.setDescriptionResidence(descriptionResidence);
        }
        patient.setNeighborhood(neighborhood);
        patient.setPassword(passwordCifrada);

        logStrategy.logInfo("request body: " + gson.toJson(patient));
        patientRepository.save(patient);
        return String.format(Constants.MSG_CREATE_PATIENT, patient.getFirstName(), patient.getSecondName());
    }
}