package mypackage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mypackage.model.Topic;

public interface TopicRepository extends JpaRepository<Topic, Integer>{

}
