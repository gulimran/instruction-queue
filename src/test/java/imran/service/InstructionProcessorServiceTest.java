package imran.service;

import imran.convert.Converter;
import imran.error.InvalidMessageException;
import imran.messaging.InstructionMessage;
import imran.messaging.InstructionQueue;
import imran.model.Instruction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class InstructionProcessorServiceTest {

    @InjectMocks
    private InstructionProcessorService instructionProcessor;

    @Mock
    private InstructionQueue<InstructionMessage> instructionQueue;

    @Mock
    private Converter<Instruction, InstructionMessage> converter;

    @Mock
    private InstructionMessage instructionMessage;

    @Mock
    private Instruction instruction;

    @Test
    public void shouldReturnSizeZero_WhenQueueIsEmpty() {
        // given
        given(instructionQueue.size()).willReturn(0);

        // then
        assertThat(instructionProcessor.getQueueMessageCount(), is(0));
    }

    @Test
    public void shouldReturnTrue_WhenQueueIsEmpty() {
        // given
        given(instructionQueue.isEmpty()).willReturn(true);

        // then
        assertThat(instructionProcessor.isQueueEmpty(), is(true));
    }

    @Test
    public void shouldReturnFalse_WhenQueueIsNotEmpty() {
        // given
        given(instructionQueue.isEmpty()).willReturn(false);

        // then
        assertThat(instructionProcessor.isQueueEmpty(), is(false));
    }

    @Test
    public void shouldVerifyConverterCall_WhenOneMessageIsAddedToQueue() {
        // when
        instructionProcessor.addMessageToQueue(instruction);

        // then
        verify(converter).convert(instruction);
    }

    @Test(expected = InvalidMessageException.class)
    public void shouldThrowException_WhenInvalidMessageIsAddedToQueue() {
        // given
        given(converter.convert(instruction)).willThrow(new InvalidMessageException("junit test - ignore"));

        // when
        instructionProcessor.addMessageToQueue(instruction);
    }

    @Test(expected = InvalidMessageException.class)
    public void shouldThrowException_WhenNullMessageIsAddedToQueue() {
        // given
        given(converter.convert(null)).willThrow(new InvalidMessageException("junit test - ignore"));

        // when
        instructionProcessor.addMessageToQueue(null);
    }

    @Test
    public void shouldVerifyQueueCall_WhenOneMessageIsAddedToQueue() {
        // given
        given(converter.convert(instruction)).willReturn(instructionMessage);

        // when
        instructionProcessor.addMessageToQueue(instruction);

        // then
        verify(instructionQueue).enqueue(instructionMessage);
    }

    @Test
    public void shouldVerifyConverterCall_WhenOneMessageIsPolledFromQueue() {
        // given
        given(instructionQueue.dequeue()).willReturn(instructionMessage);

        // when
        instructionProcessor.pullMessageFromQueue();

        // then
        verify(converter).transform(instructionMessage);
    }

    @Test
    public void shouldVerifyConverterCall_WhenQueueIsEmpty() {
        // when
        instructionProcessor.pullMessageFromQueue();

        // then
        verify(converter).transform(null);
    }

    @Test
    public void shouldVerifyClearQueueCall() {
        // when
        instructionProcessor.clearQueue();

        // then
        verify(instructionQueue).clear();
    }
}
