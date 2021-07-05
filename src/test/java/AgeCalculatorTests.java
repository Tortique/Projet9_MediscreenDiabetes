import com.abernathyclinic.mediscreendiabetes.MediscreenDiabetes;
import com.abernathyclinic.mediscreendiabetes.service.AgeCalculator;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {MediscreenDiabetes.class})
public class AgeCalculatorTests {

    @Autowired
    private AgeCalculator ageCalculator = new AgeCalculator();

    @Test
    public void getAgeTest() {
        String birthDate = "01/07/2000";
        int age = ageCalculator.getAge(birthDate);

        assertEquals(21, age);
    }
}
