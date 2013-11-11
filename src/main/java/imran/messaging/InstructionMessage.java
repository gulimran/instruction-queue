package imran.messaging;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class InstructionMessage implements Comparable {

    private final Integer instructionType;
    private final Integer productCode;
    private final Integer quantity;
    private final Integer uom;
    private final Integer timestamp;

    public InstructionMessage(Integer instructionType,
                              Integer productCode,
                              Integer quantity,
                              Integer uom,
                              Integer timestamp) {

        this.instructionType = instructionType;
        this.productCode = productCode;
        this.quantity = quantity;
        this.uom = uom;
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
    public int compareTo(Object o) {
        InstructionMessage that = (InstructionMessage) o;

        if (this.instructionType < that.instructionType) {
            return 1;
        }
        else if (this.instructionType > that.instructionType) {
            return -1;
        }

        return  0;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InstructionMessage)) return false;

        InstructionMessage message = (InstructionMessage) o;

        if (instructionType != null ? !instructionType.equals(message.instructionType) : message.instructionType != null) return false;
        if (productCode != null ? !productCode.equals(message.productCode) : message.productCode != null) return false;
        if (quantity != null ? !quantity.equals(message.quantity) : message.quantity != null) return false;
        if (timestamp != null ? !timestamp.equals(message.timestamp) : message.timestamp != null) return false;
        if (uom != null ? !uom.equals(message.uom) : message.uom != null) return false;

        return true;
    }

    @Override
    public final int hashCode() {
        int result = instructionType != null ? instructionType.hashCode() : 0;
        result = 31 * result + (productCode != null ? productCode.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (uom != null ? uom.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
