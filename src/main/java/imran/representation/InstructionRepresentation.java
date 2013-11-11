package imran.representation;

import imran.model.Instruction;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class InstructionRepresentation implements Instruction {

    private Integer instructionType;
    private Integer productCode;
    private Integer quantity;
    private Integer uom;
    private Integer timestamp;

    public void setInstructionType(Integer instructionType) {
        this.instructionType = instructionType;
    }

    public void setProductCode(Integer productCode) {
        this.productCode = productCode;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setUom(Integer uom) {
        this.uom = uom;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getInstructionType() {
        return instructionType;
    }

    public Integer getProductCode() {
        return productCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getUom() {
        return uom;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public static Instruction getInstance(
            Integer instructionType,
            Integer productCode,
            Integer quantity,
            Integer uom,
            Integer timestamp) {

        InstructionRepresentation instruction = new InstructionRepresentation();
        instruction.setInstructionType(instructionType);
        instruction.setProductCode(productCode);
        instruction.setQuantity(quantity);
        instruction.setUom(uom);
        instruction.setTimestamp(timestamp);
        return instruction;
    }
}
