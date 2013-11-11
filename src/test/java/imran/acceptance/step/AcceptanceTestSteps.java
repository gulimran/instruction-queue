package imran.acceptance.step;


import imran.controller.InstructionQueueController;
import imran.model.Instruction;
import imran.representation.InstructionRepresentation;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

public class AcceptanceTestSteps {

    @Autowired
    private InstructionQueueController controller;

    private static final Integer HIGH_PRIORITY_INSTRUCTION_TYPE = 95;

    private ModelMap modelMap = new ModelMap();

    @Given("^that the instruction queue app is running$")
    public void appIsRunning() {
        controller.index(modelMap);
    }

    @When("^I retrieve the number of InstructionMessages on it$")
    public void retrieveNumberOfInstructionMessages() {
        controller.getNumberOfInstructionMessagesOnQueue(modelMap);
    }

    @When("^I add a valid instruction message$")
    public void addOneInstructionMessageToQueue() {
        Instruction instruction = InstructionRepresentation.getInstance(20, 2, 2, 222, 22222);
        controller.addInstructionMessageToQueue(modelMap, (InstructionRepresentation) instruction);
    }

    @When("^I add an invalid instruction message$")
    public void addInvalidInstructionMessageToQueue() {
        Instruction instruction = InstructionRepresentation.getInstance(-200, 2, 2, 222, 22222);
        controller.addInstructionMessageToQueue(modelMap, (InstructionRepresentation) instruction);
    }

    @When("^I add multiple valid instruction message$")
    public void addMultipleInstructionMessageToQueue() {
        Instruction instruction1 = InstructionRepresentation.getInstance(20, 2, 2, 222, 22222);
        Instruction instruction2 = InstructionRepresentation.getInstance(HIGH_PRIORITY_INSTRUCTION_TYPE, 2, 2, 222, 22222);
        controller.addInstructionMessageToQueue(modelMap, (InstructionRepresentation) instruction1);
        controller.addInstructionMessageToQueue(modelMap, (InstructionRepresentation) instruction2);
    }

    @When("^I remove all instruction messages from the instruction queue$")
    public void removeAllInstructionMessagesFromQueue() {
        controller.clearInstructionMessagesFromQueue(modelMap);
    }

    @When("^I check whether Instruction queue is empty$")
    public void checkIfQueueIsEmpty() {
        controller.getNumberOfInstructionMessagesOnQueue(modelMap);
    }

    @When("^I poll an instruction message from the instruction queue$")
    public void pollInstructionMessagesFromQueue() {
        controller.pollInstructionMessagesFromQueue(modelMap);
    }

    @Then("^I get a (\\d+) count of the number of InstructionMessages$")
    public void verifySizeIsEqualToGivenCount(Integer count) {
        assertThat((Integer)modelMap.get("size"), is(count));
    }

    @Then("^I get an empty queue response$")
    public void verifyQueueIsEmpty() {
        assertThat((String)modelMap.get("message"), is("Queue is empty"));
    }

    @Then("^I get an error response$")
    public void verifySizeIsEqualToGivenCount() {
        assertNotNull(modelMap.get("error"));
    }

    @Then("^I get messages based on priority of instruction type")
    public void verifyHighPriorityInstructionTypeIsPolledFirst() {
        Instruction instruction = (Instruction)modelMap.get("message");
        assertThat(instruction.getInstructionType(), is(HIGH_PRIORITY_INSTRUCTION_TYPE));
    }
}



