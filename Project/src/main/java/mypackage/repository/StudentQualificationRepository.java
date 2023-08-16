package mypackage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mypackage.model.StudentQualification;

public interface StudentQualificationRepository extends JpaRepository<StudentQualification, Integer>{

}
