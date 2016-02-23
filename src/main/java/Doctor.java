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
    String sql = "SELECT * FROM doctors";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Doctor.class);
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


}
