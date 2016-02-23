import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;

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
}
