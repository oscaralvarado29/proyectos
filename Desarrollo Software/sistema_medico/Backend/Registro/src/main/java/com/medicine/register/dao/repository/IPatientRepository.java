package com.medicine.register.dao.repository;

import com.medicine.register.dao.entity.Patient;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@EnableScan
@Repository
public interface IPatientRepository extends CrudRepository<Patient,String> {
}
