package mypackage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mypackage.model.StudentDetails;

public interface StudentDetailsRepository extends JpaRepository<StudentDetails, Integer>{

}
