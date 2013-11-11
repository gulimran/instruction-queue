package imran.validation;

import imran.error.InvalidMessageException;
import imran.messaging.InstructionMessage;
import org.springframework.stereotype.Component;

@Component
public class InstructionValidator implements Validator<InstructionMessage> {

    final static Integer LOW_INSTRUCTION_TYPE = 0;
    final static Integer LOW_PRODUCTION_CODE = 0;
    final static Integer LOW_QUANTITY = 0;
    final static Integer LOW_UOM = -1;
    final static Integer LOW_TIMESTAMP = 0;
    final static Integer HIGH_INSTRUCTION_TYPE = 100;
    final static Integer HIGH_UOM = 256;

    @Override
    public void validate(InstructionMessage message) {
        checkForNull(message);
        checkForLowValue(message);
        checkForHighValue(message);
    }

    private void checkForLowValue(InstructionMessage message) {
        checkForLowValue("InstructionType", message.getInstructionType(), LOW_INSTRUCTION_TYPE);
        checkForLowValue("ProductCode", message.getProductCode(), LOW_PRODUCTION_CODE);
        checkForLowValue("Quantity", message.getQuantity(), LOW_QUANTITY);
        checkForLowValue("UOM", message.getUom(), LOW_UOM);
        checkForLowValue("Timestamp", message.getTimestamp(), LOW_TIMESTAMP);
    }

    private void checkForHighValue(InstructionMessage message) {
        checkForHighValue("InstructionType", message.getInstructionType(), HIGH_INSTRUCTION_TYPE);
        checkForHighValue("UOM", message.getUom(), HIGH_UOM);
    }

    private void checkForNull(InstructionMessage message) {
        checkForNull("InstructionType", message.getInstructionType());
        checkForNull("ProductCode", message.getProductCode());
        checkForNull("Quantity", message.getQuantity());
        checkForNull("UOM", message.getUom());
        checkForNull("Timestamp", message.getTimestamp());
    }

    private void checkForLowValue(String name, Integer value, Integer threshold) {
        if (value <= threshold) {
            throw new InvalidMessageException(name+" value must be greater than "+threshold);
        }
    }

    private void checkForHighValue(String name, Integer value, Integer threshold) {
        if (value >= threshold) {
            throw new InvalidMessageException(name+" value must be less than "+threshold);
        }
    }

    private void checkForNull(String name, Integer value) {
        if (value == null) {
            throw new InvalidMessageException(name + " cannot be null");
        }
    }
}
