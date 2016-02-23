import java.util.*;
import org.sql2o.*;

public class Patient {
  private int id;
  private String name;
  private String birthday;
  private int doctor_id;

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getBirthday() {
    return birthday;
  }

  public int getDoctorId() {
    return doctor_id;
  }

  public Patient(String name, String birthday, int doctor_id) {
    this.name=name;
    this.birthday=birthday;
    this.doctor_id = doctor_id;
  }

  public static List<Patient> all() {
    String sql = "SELECT * FROM patients";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Patient.class);
    }
  }

  @Override
  public boolean equals(Object otherPatient) {
    if(!(otherPatient instanceof Patient)) {
      return false;
    } else {
      Patient newPatient = (Patient) otherPatient;
      return newPatient.getName().equals(name) && newPatient.getBirthday().equals(birthday);
    }
  }

  public void save() {
    String sql = "INSERT INTO patients (name, birthday, doctor_id) VALUES (:name , :birthday, :doctor_id)";
    try(Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery(sql, true).addParameter("name", name).addParameter("birthday", birthday).addParameter("doctor_id", doctor_id).executeUpdate().getKey();
    }
  }

  public static Patient find(int id ) {
    String sql = "SELECT * FROM patients WHERE id = :id";
      try(Connection con = DB.sql2o.open()) {
        return con.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Patient.class);
      }
  }

  public void updateName(String name) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE patients SET name = :name WHERE id = :id";
      con.createQuery(sql).addParameter("name", name).addParameter("id", id).executeUpdate();

    }
  }

  public void updateBirthday(String birthday) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE patients SET birthday = :birthday WHERE id = :id";
      con.createQuery(sql).addParameter("birthday", birthday).addParameter("id", id).executeUpdate();
    }
  }

  public void delete () {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM patients WHERE id = :id";
      con.createQuery(sql).addParameter("id", id ).executeUpdate();
    }
  }
}
