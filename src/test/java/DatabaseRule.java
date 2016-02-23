import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/doctor_test", null, null);
   }

  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String sql_removeDoctors = "DELETE FROM doctors *;";
      String sql_removePatients = "DELETE FROM patients *;";
      con.createQuery(sql_removeDoctors).executeUpdate();
      con.createQuery(sql_removePatients).executeUpdate();
    }
  }
}
