package JDBC.demo.repository;

import JDBC.demo.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository <Student , Integer> {
    List<Student> findAllById(Integer id);
    List<Student> findAllByName (String name);

    Student deleteStudentByName (String name);

}
