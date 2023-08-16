package mypackage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mypackage.model.ContentQuestion;

public interface ContentQuestionRepository extends JpaRepository<ContentQuestion, Integer>{

}
