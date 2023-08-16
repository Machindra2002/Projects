package mypackage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mypackage.model.ExamQuestions;

public interface ExamQuestionsRepository extends JpaRepository<ExamQuestions, Integer>{

}
