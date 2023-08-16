package mypackage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mypackage.model.ExamDetails;

public interface ExamDetailsRepository extends JpaRepository<ExamDetails, Integer>{

}
