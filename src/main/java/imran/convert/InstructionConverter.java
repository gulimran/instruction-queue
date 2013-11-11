package imran.convert;

import imran.model.Instruction;
import imran.messaging.InstructionMessage;
import imran.representation.InstructionRepresentation;
import imran.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InstructionConverter implements Converter<Instruction, InstructionMessage> {

    private Validator<InstructionMessage> validator;

    @Autowired
    public InstructionConverter(Validator<InstructionMessage> validator) {
        this.validator = validator;
    }

    @Override
    public InstructionMessage convert(Instruction in) {
        InstructionMessage instructionMessage = new InstructionMessage(
                in.getInstructionType(),
                in.getProductCode(),
                in.getQuantity(),
                in.getUom(),
                in.getTimestamp());
        validator.validate(instructionMessage);
        return instructionMessage;
    }

    @Override
    public Instruction transform(InstructionMessage in) {
        if (in == null) return null;

        InstructionRepresentation instruction = new InstructionRepresentation();
        instruction.setInstructionType(in.getInstructionType());
        instruction.setProductCode(in.getProductCode());
        instruction.setQuantity(in.getQuantity());
        instruction.setUom(in.getUom());
        instruction.setTimestamp(in.getTimestamp());
        return instruction;
    }
}
