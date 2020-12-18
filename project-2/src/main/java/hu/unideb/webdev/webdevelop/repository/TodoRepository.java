package hu.unideb.webdev.webdevelop.repository;

import hu.unideb.webdev.webdevelop.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

}
