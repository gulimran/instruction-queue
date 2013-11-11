package imran.messaging;

import imran.error.InvalidMessageException;

public interface InstructionQueue<T> {

    int size();

    void enqueue(T message) throws InvalidMessageException;

    T dequeue();

    void clear();

    boolean isEmpty();
}
