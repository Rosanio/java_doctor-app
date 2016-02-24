import java.util.*;
import org.sql2o.*;

public class Doctor {
  private int id;
  private String name;
  private String specialty;

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getSpecialty() {
    return specialty;
  }

  public Doctor(String name, String specialty) {
    this.name=name;
    this.specialty=specialty;
  }

  public static List<Doctor> all() {
    String sql = "SELECT * FROM doctors ORDER BY name ASC";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Doctor.class);
    }
  }

  public List<Patient> viewPatients() {
    String sql = "SELECT * FROM patients WHERE doctor_id = :id";
      try(Connection con = DB.sql2o.open()) {
        return con.createQuery(sql).addParameter("id",id).executeAndFetch(Patient.class);
      }
  }

  @Override
  public boolean equals(Object otherDoctor) {
    if(!(otherDoctor instanceof Doctor)) {
      return false;
    } else {
      Doctor newDoctor = (Doctor) otherDoctor;
      return newDoctor.getName().equals(name) && newDoctor.getSpecialty().equals(specialty);
    }
  }

  public void save() {
    String sql = "INSERT INTO doctors (name, specialty) VALUES (:name , :specialty)";
    try(Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery(sql, true).addParameter("name", name).addParameter("specialty", specialty).executeUpdate().getKey();
    }
  }

  public static Doctor find(int id ) {
    String sql = "SELECT * FROM doctors WHERE id = :id";
      try(Connection con = DB.sql2o.open()) {
        return con.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Doctor.class);
      }
  }

  public void updateName(String name) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE doctors SET name = :name WHERE id = :id";
      con.createQuery(sql).addParameter("name", name).addParameter("id", id).executeUpdate();

    }
  }

  public void updateSpecialty(String specialty) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE doctors SET specialty = :specialty WHERE id = :id";
      con.createQuery(sql).addParameter("specialty", specialty).addParameter("id", id).executeUpdate();

    }
  }

  public void delete () {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM doctors WHERE id = :id";
      con.createQuery(sql).addParameter("id", id ).executeUpdate();
    }
  }

  // public int countPatients() {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "SELECT COUNT(doctor_id) FROM patients WHERE doctor_id = :id";
  //     return con.createQuery(sql).addParameter("id", id).executeAndFetchFirst(int.class);
  //   }
  // }


}
