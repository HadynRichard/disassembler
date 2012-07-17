package disassembler.clazz.instructionset;

public class Instruction {

	private byte opcode;
	private int arg_len;
	private String opcode_tanslation;
	
	public Instruction(byte opcode, int arg_len, String opcode_tanslation) {
		this.opcode = opcode;
		this.arg_len = arg_len;
		this.opcode_tanslation = opcode_tanslation;
	}
	public String opcode_tanslation() {
		return opcode_tanslation;
	}

	public int arg_len() {
		return arg_len;
	}

	public byte opcode() {
		return opcode;
	}
	
}
