package imran.controller;

import imran.representation.InstructionRepresentation;
import imran.service.InstructionProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;

@Controller
@RequestMapping("/instruction-queue")
public class InstructionQueueController {

    private InstructionProcessor instructionProcessor;

    @Autowired
    public InstructionQueueController(InstructionProcessor instructionProcessor) {
        this.instructionProcessor = instructionProcessor;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap model) {
        return "index";
    }

    @RequestMapping(value = "/size", method = RequestMethod.GET)
    public String getNumberOfInstructionMessagesOnQueue(ModelMap model) {
        model.addAttribute("size", instructionProcessor.getQueueMessageCount());
        return "size";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String displayAddInstructionMessageToQueueForm(ModelMap model) {
        model.addAttribute("message", new InstructionRepresentation());
        return "add";

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addInstructionMessageToQueue(ModelMap model, InstructionRepresentation message) {
        try {
            instructionProcessor.addMessageToQueue(message);
            model.addAttribute("size", instructionProcessor.getQueueMessageCount());
            return "size";
        }
        catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value = "/clear", method = RequestMethod.GET)
    public String clearInstructionMessagesFromQueue(ModelMap model) {
        instructionProcessor.clearQueue();
        model.addAttribute("size", instructionProcessor.getQueueMessageCount());
        return "size";
    }

    @RequestMapping(value = "/poll", method = RequestMethod.GET)
    public String pollInstructionMessagesFromQueue(ModelMap model) {
        if (instructionProcessor.isQueueEmpty()) {
            model.addAttribute("message", "Queue is empty");
        }
        else {
            model.addAttribute("message", instructionProcessor.pullMessageFromQueue());
        }

        return "poll";
    }
}