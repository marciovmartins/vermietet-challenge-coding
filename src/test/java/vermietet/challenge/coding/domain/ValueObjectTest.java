package vermietet.challenge.coding.domain;

import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

public class ValueObjectTest {
    @Test
    public void testNotInstanceOf() {
        // setup
        ValueObject valueObject = new ValueObject("Brevis candidatus rare talems itineris tramitem est.") {
        };
        Object notValueObject = new Object();

        // assertions
        assertNotEquals(valueObject, notValueObject);
    }

    @Test
    public void testNotEquals() {
        // setup
        ValueObject valueObject1 = new ValueObject("Urbss velum in gandavum!") {
        };
        ValueObject valueObject2 = new ValueObject("Cur fluctui mori?") {
        };

        // execution
        assertNotEquals(valueObject1, valueObject2);
    }
}