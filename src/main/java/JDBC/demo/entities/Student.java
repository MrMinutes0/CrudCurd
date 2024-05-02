package JDBC.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "student_table")
@Getter
@Setter
public class Student {

    private
    @Id
    Integer id;
    String name;
    Boolean isFeePaid;
    Integer rollNo;
    String course;
}