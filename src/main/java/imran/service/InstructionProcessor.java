package imran.service;

import imran.error.InvalidMessageException;
import imran.model.Instruction;

public interface InstructionProcessor {

    int getQueueMessageCount();

    void addMessageToQueue(Instruction message) throws InvalidMessageException;

    Instruction pullMessageFromQueue();

    void clearQueue();

    boolean isQueueEmpty();
}
