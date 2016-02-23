import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;

import java.util.*;

public class DoctorTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void checkIfReturnsEmptyArray() {
    assertEquals(Doctor.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfDoctorsAretheSame() {
    Doctor firstDoctor = new Doctor ("David", "pediatrian");
    Doctor secondDoctor = new Doctor ("David", "pediatrian");
    assertTrue(firstDoctor.equals(secondDoctor));
  }

  @Test
  public void save_savesDoctorToDatabase() {
    Doctor newDoctor = new Doctor("David", "pediatrian");
    newDoctor.save();
    assertTrue(Doctor.all().get(0).equals(newDoctor));
  }

  @Test
  public void find_findsDoctorInDatabase_true() {
    Doctor myDoctor = new Doctor ("David", "pediatrian");
    myDoctor.save();
    Doctor savedDoctor= Doctor.find(myDoctor.getId());
    assertEquals(savedDoctor.getName(), "David");
  }

  @Test
  public void viewPatients_returnsListOfPatients() {
    Doctor myDoctor = new Doctor("David", "pediatrian");
    myDoctor.save();
    Patient newPatient = new Patient("Wes", "1991-12-24", myDoctor.getId());
    Patient newPatient2 = new Patient("Dave", "1988-05-06", myDoctor.getId());
    newPatient.save();
    newPatient2.save();
    Patient[] patients = new Patient[] { newPatient, newPatient2 };
    assertTrue(myDoctor.viewPatients().containsAll(Arrays.asList(patients)));
  }
}
