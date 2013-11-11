package imran.messaging;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.PriorityQueue;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class MemoryInstructionQueueTest {

    private static int INSTRUCTION_TYPE = 65;
    private static int PRODUCT_CODE = 12345;
    private static int QUANTITY = 2;
    private static int UOM = 255;
    private static int TIMESTAMP = 1383728893;
    private static InstructionMessage TEST_MESSAGE = InstructionMessageTest.build(INSTRUCTION_TYPE, PRODUCT_CODE, QUANTITY, UOM, TIMESTAMP);

    @InjectMocks
    private MemoryInstructionQueue instructionQueue;

    @Mock
    private InstructionMessage instructionMessage;

    @Mock
    private PriorityQueue<InstructionMessage> mockQueue;


    @Test
    public void shouldReturnSizeZero_WhenQueueIsEmpty() {
        assertThat(instructionQueue.size(), is(0));
    }

    @Test
    public void shouldReturnTrue_WhenQueueIsEmpty() {
        // given
        given(mockQueue.isEmpty()).willReturn(true);

        // then
        assertThat(instructionQueue.isEmpty(), is(true));
    }

    @Test
    public void shouldReturnSize_WhenOneMessageIsAddedToQueue() {
        // given
        given(mockQueue.size()).willReturn(1);

        // when
        instructionQueue.enqueue(instructionMessage);

        // then
        assertThat(instructionQueue.size(), is(1));
    }

    @Test
    public void shouldReturnMessage_WhenOneMessageIsAddedToQueue() {
        // given
        given(mockQueue.poll()).willReturn(TEST_MESSAGE);
        instructionQueue = new MemoryInstructionQueue(mockQueue);

        // when
        InstructionMessage actualMessage = instructionQueue.dequeue();

        // then
        assertThat(actualMessage, is(TEST_MESSAGE));
    }

    @Test
    public void shouldReturnSizeZero_WhenQueueIsCleared() {
        // given
        instructionQueue.enqueue(instructionMessage);

        // when
        instructionQueue.clear();

        // then
        assertThat(instructionQueue.size(), is(0));
    }
}
