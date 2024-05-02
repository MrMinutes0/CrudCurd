package JDBC.demo.controller;
import JDBC.demo.entities.Student;
import JDBC.demo.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

   @Autowired
   private StudentRepo studentRepo;

   @PostMapping("/addStudent")
   public ResponseEntity<Student> createStudent(@RequestBody Student student) {
      Student studentObj = studentRepo.save(student);

      return new ResponseEntity<>(studentObj , HttpStatus.OK);
   }

   @GetMapping("/getStudentById/{id}")
   public ResponseEntity<Student> getStudentById(@PathVariable Integer id) {
      Optional<Student> studentData = studentRepo.findById(id);
      if (studentData.isPresent()) {
         return new ResponseEntity<>(studentData.get() , HttpStatus.OK);
      }

      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }

   @GetMapping("/getAllStudents")
   public ResponseEntity<List<Student>> getAllStudents() {
      try{
         List<Student> studentList = new ArrayList<>();
         studentRepo.findAll().forEach(studentList::add);
         if (studentList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
         }

         return new ResponseEntity<>(studentList , HttpStatus.OK);
      } catch (Exception exp) {
         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }

   @DeleteMapping("/deleteStudent/{id}")
   public ResponseEntity<HttpStatus> deleteBookByID (@PathVariable Integer id) {
      studentRepo.deleteById(id);
      return new ResponseEntity<>(HttpStatus.OK);
   }

   @PutMapping ("/updateStudentById/{id}")
   public ResponseEntity<Student> updateBookById (@PathVariable Integer id , @RequestBody Student newStudentData) {
      Optional<Student> studentData = studentRepo.findById(id);
      if (studentData.isPresent()) {
         Student newStudent = studentData.get();
         if (newStudentData.getName()!= null) newStudent.setName(newStudentData.getName());
         if (newStudentData.getRollNo() != null) newStudent.setRollNo(newStudentData.getRollNo());
         if (newStudentData.getCourse() != null) newStudent.setCourse(newStudentData.getCourse());
         if (newStudentData.getIsFeePaid() != null) newStudent.setIsFeePaid(newStudentData.getIsFeePaid());
         Student newStudentObj = studentRepo.save(newStudent);
         return new ResponseEntity<>(newStudentObj, HttpStatus.OK);
      } else {
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
   }
}
