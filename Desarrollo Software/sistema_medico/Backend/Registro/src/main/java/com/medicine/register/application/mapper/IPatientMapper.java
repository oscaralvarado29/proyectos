package com.medicine.register.application.mapper;

import com.medicine.register.application.dto.PatientRequest;
import com.medicine.register.application.dto.RequestResponse;
import com.medicine.register.domain.model.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPatientMapper {
    default Patient toPatient (PatientRequest patientRequest, String password){
        Patient patient = new Patient();
        patient.setId(patientRequest.getId());
        patient.setFirstName(patientRequest.getFirstName());
        if(patientRequest.getSecondName() != null && !patientRequest.getSecondName().isBlank() && !patientRequest.getSecondName().isEmpty()) {
            patient.setSecondName(patientRequest.getSecondName());
        }
        patient.setFirstSurName(patientRequest.getFirstSurName());
        if (patientRequest.getSecondSurName() != null && !patientRequest.getSecondSurName().isEmpty() && !patientRequest.getSecondSurName().isBlank()) {
            patient.setSecondSurName(patientRequest.getSecondSurName());
        }
        patient.setAddress(patientRequest.getAddress());
        patient.setEmail(patientRequest.getEmail());
        if (patientRequest.getLandline() != null && !patientRequest.getLandline().isBlank() && !patientRequest.getLandline().isEmpty()) {
            patient.setLandline(patientRequest.getLandline());
        }
        patient.setCellPhone(patientRequest.getCellPhone());
        patient.setResidencesType(patientRequest.getResidencesType());
        if(patientRequest.getDescriptionResidence() != null && !patientRequest.getDescriptionResidence().isEmpty() && !patientRequest.getDescriptionResidence().isBlank()) {
            patient.setDescriptionResidence(patientRequest.getDescriptionResidence());
        }
        patient.setNeighborhood(patientRequest.getNeighborhood());
        patient.setPassword(password);
        return patient;
    }

    RequestResponse toRequestResponse (Patient patient);
}
