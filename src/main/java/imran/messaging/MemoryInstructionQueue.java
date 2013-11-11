package imran.messaging;

import imran.error.InvalidMessageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.PriorityQueue;

@Service
public class MemoryInstructionQueue implements InstructionQueue<InstructionMessage> {

    private PriorityQueue<InstructionMessage> queue;

    @Autowired
    public MemoryInstructionQueue(PriorityQueue<InstructionMessage> queue) {
        this.queue = queue;
    }

    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public void enqueue(InstructionMessage instructionMessage) throws InvalidMessageException {
        this.queue.add(instructionMessage);
    }

    @Override
    public InstructionMessage dequeue() {
        return queue.poll();
    }

    @Override
    public void clear() {
        queue.clear();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
