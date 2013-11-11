Feature: Instruction Queue
    As a client to the instruction queue
    I want to access this service
    So that I can work with instruction messages


    Scenario:  Zero instruction messages on Instruction queue
            Given that the instruction queue app is running
            When I retrieve the number of InstructionMessages on it
            Then I get a 0 count of the number of InstructionMessages

    Scenario:  Add instruction message to instruction queue
            Given that the instruction queue app is running
            When I add a valid instruction message
            And I retrieve the number of InstructionMessages on it
            Then I get a 1 count of the number of InstructionMessages

    Scenario:  Add invalid instruction message to instruction queue
            Given that the instruction queue app is running
            When I add an invalid instruction message
            Then I get an error response

    Scenario:  Remove instruction messages from instruction queue
            Given that the instruction queue app is running
            And I add a valid instruction message
            When I remove all instruction messages from the instruction queue
            And I retrieve the number of InstructionMessages on it
            Then I get a 0 count of the number of InstructionMessages

    Scenario:  Instruction queue is empty when no instruction messages on it
            Given that the instruction queue app is running
            When I poll an instruction message from the instruction queue
            Then I get an empty queue response

    Scenario:  Retrieve instruction messages in appropriate order
            Given that the instruction queue app is running
            And I add multiple valid instruction message
            When I poll an instruction message from the instruction queue
            Then I get messages based on priority of instruction type