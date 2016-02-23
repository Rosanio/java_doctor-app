import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;

public class PatientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void checkIfReturnsEmptyArray() {
    assertEquals(Patient.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfPatientsAretheSame() {
    Patient firstPatient = new Patient ("David", "1991-12-24", 1);
    Patient secondPatient = new Patient ("David", "1991-12-24", 1);
    assertTrue(firstPatient.equals(secondPatient));
  }

  @Test
  public void save_savesPatientToDatabase() {
    Patient newPatient = new Patient("David", "1991-12-24", 1);
    newPatient.save();
    assertTrue(Patient.all().get(0).equals(newPatient));
  }

  @Test
  public void find_findsPatientInDatabase_true() {
    Patient myPatient = new Patient ("David", "1991-12-24", 1);
    myPatient.save();
    Patient savedPatient= Patient.find(myPatient.getId());
    assertEquals(savedPatient.getName(), "David");
  }
}
