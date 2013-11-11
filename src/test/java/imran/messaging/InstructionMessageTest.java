package imran.messaging;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public class InstructionMessageTest {

    @Test
    public void shouldFulfillEqualsHashCodeContract() {
        InstructionMessage message = build(65, 12345, 2, 255, 1383728893);

        EqualsVerifier.forClass(InstructionMessage.class)
                .withPrefabValues(InstructionMessage.class, message, build(8, 54321, 1, 128, 1383728893))
                .verify();
    }

    public static InstructionMessage build(Integer instructionType, Integer productCode, Integer quantity, Integer uom, Integer timestamp) {
        return new InstructionMessage(instructionType, productCode, quantity, uom, timestamp);
    }
}
