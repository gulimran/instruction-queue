package imran.service;

import imran.convert.Converter;
import imran.error.InvalidMessageException;
import imran.messaging.InstructionQueue;
import imran.model.Instruction;
import imran.messaging.InstructionMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstructionProcessorService implements InstructionProcessor {

    private InstructionQueue<InstructionMessage> instructionQueue;
    private Converter<Instruction, InstructionMessage> converter;

    @Autowired
    public InstructionProcessorService(
            InstructionQueue<InstructionMessage> instructionQueue,
            Converter<Instruction, InstructionMessage> converter) {

        this.instructionQueue = instructionQueue;
        this.converter = converter;
    }

    @Override
    public int getQueueMessageCount() {
        return instructionQueue.size();
    }

    @Override
    public void addMessageToQueue(Instruction message) throws InvalidMessageException {
        instructionQueue.enqueue(converter.convert(message));
    }

    @Override
    public Instruction pullMessageFromQueue() {
        return converter.transform(instructionQueue.dequeue());
    }

    @Override
    public void clearQueue() {
       instructionQueue.clear();
    }

    @Override
    public boolean isQueueEmpty() {
        return instructionQueue.isEmpty();
    }
}
