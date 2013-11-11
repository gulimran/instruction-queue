package imran.validation;

import imran.error.InvalidMessageException;
import imran.messaging.InstructionMessage;
import org.junit.Before;
import org.junit.Test;

import static imran.validation.InstructionValidator.HIGH_INSTRUCTION_TYPE;
import static imran.validation.InstructionValidator.HIGH_UOM;

public class InstructionValidatorTest {

    private InstructionValidator validator;

    private InstructionMessage message;

    @Before
    public void setup() {
        validator = new InstructionValidator();
    }

    @Test(expected = InvalidMessageException.class)
    public void shouldThrowException_WhenInstructionTypeIsNull() {
        // given
        message = new InstructionMessage(null, null, null, null, null);

        // when
        validator.validate(message);
    }

    @Test(expected = InvalidMessageException.class)
    public void shouldThrowException_WhenProductCodeIsNull() {
        // given
        message = new InstructionMessage(50, null, null, null, null);

        // when
        validator.validate(message);
    }

    @Test(expected = InvalidMessageException.class)
    public void shouldThrowException_WhenQuantityIsNull() {
        // given
        message = new InstructionMessage(50, 33, null, null, null);

        // when
        validator.validate(message);
    }

    @Test(expected = InvalidMessageException.class)
    public void shouldThrowException_WhenUomIsNull() {
        // given
        message = new InstructionMessage(50, 33, 144, null, null);

        // when
        validator.validate(message);
    }

    @Test(expected = InvalidMessageException.class)
    public void shouldThrowException_WhenTimestampIsNull() {
        // given
        message = new InstructionMessage(50, 33, 144, 25, null);

        // when
        validator.validate(message);
    }

    @Test(expected = InvalidMessageException.class)
    public void shouldThrowException_WhenInstructionTypeIsNegative() {
        // given
        message = new InstructionMessage(-50, 33, 144, 25, 1384125039);

        // when
        validator.validate(message);
    }

    @Test(expected = InvalidMessageException.class)
    public void shouldThrowException_WhenProductCodeIsNegative() {
        // given
        message = new InstructionMessage(50, -33, 144, 25, 1384125039);

        // when
        validator.validate(message);
    }

    @Test(expected = InvalidMessageException.class)
    public void shouldThrowException_WhenQuantityIsNegative() {
        // given
        message = new InstructionMessage(50, 33, -144, 25, 1384125039);

        // when
        validator.validate(message);
    }

    @Test(expected = InvalidMessageException.class)
    public void shouldThrowException_WhenUomIsNegative() {
        // given
        message = new InstructionMessage(50, 33, 144, -25, 1384125039);

        // when
        validator.validate(message);
    }

    @Test(expected = InvalidMessageException.class)
    public void shouldThrowException_WhenTimestampIsNegative() {
        // given
        message = new InstructionMessage(50, 33, 144, 25, -1384125039);

        // when
        validator.validate(message);
    }

    @Test(expected = InvalidMessageException.class)
    public void shouldThrowException_WhenInstructionTypeIsZero() {
        // given
        message = new InstructionMessage(0, 33, 144, 25, 1384125039);

        // when
        validator.validate(message);
    }

    @Test(expected = InvalidMessageException.class)
    public void shouldThrowException_WhenProductCodeIsZero() {
        // given
        message = new InstructionMessage(50, 0, 144, 25, 1384125039);

        // when
        validator.validate(message);
    }

    @Test(expected = InvalidMessageException.class)
    public void shouldThrowException_WhenQuantityIsZero() {
        // given
        message = new InstructionMessage(50, 33, 0, 25, 1384125039);

        // when
        validator.validate(message);
    }

    @Test
    public void shouldBeValid_WhenUomIsZero() {
        // given
        message = new InstructionMessage(50, 33, 144, 0, 1384125039);

        // when
        validator.validate(message);

        // then
        // pass
    }

    @Test(expected = InvalidMessageException.class)
    public void shouldThrowException_WhenTimestampIsZero() {
        // given
        message = new InstructionMessage(50, 33, 144, 25, 0);

        // when
        validator.validate(message);
    }

    @Test(expected = InvalidMessageException.class)
    public void shouldThrowException_WhenInstructionTypeIsGreaterThanHighThreshold() {
        // given
        message = new InstructionMessage(HIGH_INSTRUCTION_TYPE, 33, 144, 25, 1384125039);

        // when
        validator.validate(message);
    }

    @Test(expected = InvalidMessageException.class)
    public void shouldThrowException_WhenUomIsGreaterThanHighThreshold() {
        // given
        message = new InstructionMessage(50, 33, 144, HIGH_UOM, 1384125039);

        // when
        validator.validate(message);
    }
}
