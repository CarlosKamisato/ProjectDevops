package com.kibernumacademy.devops;

import com.kibernumacademy.devops.entitys.Student;
import com.kibernumacademy.devops.repositories.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DevopsApplication implements CommandLineRunner {
  private final IStudentRepository repository;
  @Autowired
  public DevopsApplication(IStudentRepository repository) {
    this.repository = repository;
  }
  public static void main(String[] args) {
    SpringApplication.run(DevopsApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
 Student student1 = new Student("Carlos", "Kamisato", "carloskamisato.ktd@orakle.cl");
 Student student2 = new Student("Yamile", "Ancan", "yamiletancan.ktd@orakle.cl");
 Student student3 = new Student("Manuel", "Carrasco", "manuelcarrasco.ktd@orakle.cl");
 Student student4 = new Student("Rene", "Jorquera", "renejorquera.ktd@orakle.cl");

    repository.save(student1);
    repository.save(student2);
    repository.save(student3);
    repository.save(student4);

  }
}
