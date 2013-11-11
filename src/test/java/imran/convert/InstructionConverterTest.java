package imran.convert;

import imran.messaging.InstructionMessage;
import imran.model.Instruction;
import imran.representation.InstructionRepresentation;
import imran.validation.Validator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class InstructionConverterTest {

    @InjectMocks
    private InstructionConverter converter;

    @Mock
    private Validator<InstructionMessage> validator;

    @Test
    public void shouldConvertToInstructionMessage_GivenValidInstruction() {
        // given
        InstructionMessage expected = new InstructionMessage(1, 1, 1, 1, 1);

        // when
        InstructionMessage message = converter.convert(InstructionRepresentation.getInstance(1, 1, 1, 1, 1));

        // then
        assertThat(expected.equals(message), is(true));
    }

    @Test
    public void shouldTransformToInstruction_GivenValidInstructionMessage() {
        // given
        Instruction expected = InstructionRepresentation.getInstance(10, 10, 10, 10, 10);

        // when
        Instruction instruction = converter.transform(new InstructionMessage(10, 10, 10, 10, 10));

        // then
        assertEquals(expected.getInstructionType(), instruction.getInstructionType());
        assertEquals(expected.getProductCode(), instruction.getProductCode());
        assertEquals(expected.getQuantity(), instruction.getQuantity());
        assertEquals(expected.getUom(), instruction.getUom());
        assertEquals(expected.getTimestamp(), instruction.getTimestamp());
    }
}
