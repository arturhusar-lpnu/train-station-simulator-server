package dev.test_generators;

import com.simulation.exceptions.InvalidArgumentException;
import com.simulation.generators.PrivilegeGenerator;
import com.simulation.models.privileges.PrivilegeType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PrivilegeGeneratorTest {

    @Test
    public void testConstructorWithValidProbability() throws InvalidArgumentException {
        PrivilegeGenerator generator = new PrivilegeGenerator(0.5);
        assertNotNull(generator);
    }

    @Test
    public void testConstructorWithInvalidProbability() {
        assertThrows(InvalidArgumentException.class, () -> new PrivilegeGenerator(-0.1));
        assertThrows(InvalidArgumentException.class, () -> new PrivilegeGenerator(1.1));
        assertThrows(InvalidArgumentException.class, () -> new PrivilegeGenerator(-0.0001));
        assertThrows(InvalidArgumentException.class, () -> new PrivilegeGenerator(1.0001));
    }

    @Test
    public void testGetPrivilegeWithHighProbability() throws InvalidArgumentException {
        PrivilegeGenerator generator = new PrivilegeGenerator(0.9);

        int privilegeCount = 0;
        int testCount = 1000;

        for (int i = 0; i < testCount; i++) {
            PrivilegeType privilege = generator.getPrivilege();
            if (privilege != PrivilegeType.None) {
                privilegeCount++;
            }
        }

        assertTrue(privilegeCount > testCount * 0.85, "Privilege should occur more than 85% of the time.");
    }

    @Test
    public void testGetPrivilegeWithLowProbability() throws InvalidArgumentException {
        PrivilegeGenerator generator = new PrivilegeGenerator(0.1);

        int privilegeCount = 0;
        int testCount = 1000;

        for (int i = 0; i < testCount; i++) {
            PrivilegeType privilege = generator.getPrivilege();
            if (privilege != PrivilegeType.None) {
                privilegeCount++;
            }
        }

        assertTrue(privilegeCount < testCount * 0.2, "Privilege should occur less than 20% of the time.");
    }

    @Test
    public void testGetPrivilegeReturnsNone() throws InvalidArgumentException {
        PrivilegeGenerator generator = new PrivilegeGenerator(0.0);

        int privilegeCount = 0;
        int testCount = 1000;

        for (int i = 0; i < testCount; i++) {
            PrivilegeType privilege = generator.getPrivilege();
            if (privilege != PrivilegeType.None) {
                privilegeCount++;
            }
        }

        assertEquals(0, privilegeCount, "No privilege should be assigned when probability is 0.");
    }

    @Test
    public void testGetPrivilegeReturnsValidPrivilege() throws InvalidArgumentException {
        PrivilegeGenerator generator = new PrivilegeGenerator(1.0);

        int testCount = 1000;

        for (int i = 0; i < testCount; i++) {
            PrivilegeType privilege = generator.getPrivilege();
            assertNotEquals(PrivilegeType.None, privilege, "Privilege should not be None when probability is 1.");
            assertTrue(privilege == PrivilegeType.fromInt(1) || privilege == PrivilegeType.fromInt(2) || privilege == PrivilegeType.fromInt(3), "Privilege should be a valid PrivilegeType.");
        }
    }

    @Test
    // Тест для того, щоб перевірити, чи кожен тип ривілеї хоча б раз генериться
    public void testPrivilegeGenerationAtProbabilityOne() throws InvalidArgumentException {
        PrivilegeGenerator generator = new PrivilegeGenerator(1.0);

        int testCount = 1000;
        boolean[] privilegesGenerated = new boolean[PrivilegeType.values().length]; // масив для перевірки кожного типу

        for (int i = 0; i < testCount; i++) {
            PrivilegeType privilege = generator.getPrivilege();

            if (privilege != PrivilegeType.None) {
                privilegesGenerated[privilege.ordinal()] = true;
            }
        }

        // privilegesGenerated.length - 1, бо Interrupted не має генеритись
        // з 1, щоб None пропустити
        for (int i = 1; i < privilegesGenerated.length - 1; i++) {
            assertTrue(privilegesGenerated[i], "Privilege " + PrivilegeType.fromInt(i) + " should have been generated");
        }
    }
}
