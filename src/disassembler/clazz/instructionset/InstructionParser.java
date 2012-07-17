package disassembler.clazz.instructionset;

import java.util.ArrayList;
import java.util.HashMap;
import disassembler.clazz.contents.ConstantPoolEntry;

public class InstructionParser {

	private static HashMap<Byte, Instruction> opcode_table;
	static {
		//TODO: parse these out via XML or something...
		opcode_table = new HashMap<Byte, Instruction>();
		opcode_table.put((byte) (0xb2), new Instruction((byte) (0xb2), 2, "getstatic"));
		opcode_table.put((byte) (0x12), new Instruction((byte) (0x12), 1, "ldc"));
		opcode_table.put((byte) (0xb6), new Instruction((byte) (0xb6), 2, "invokevirtual"));
		opcode_table.put((byte) (0xb1), new Instruction((byte) (0xb1), 0, "return"));

		opcode_table.put((byte) (0x2a), new Instruction((byte) (0x2a), 0, "aload_0"));
		opcode_table.put((byte) (0xb7), new Instruction((byte) (0xb7), 2, "invokespecial"));

	}
	public static ArrayList<String> getInstructions(ConstantPoolEntry[] constpool, byte[] code) {
		ArrayList<String> instructions = new ArrayList<String>();
		for(int offset = 0; offset < code.length; ++offset) {
			byte current = code[offset];
			Instruction opcode = opcode_table.get(current);
			instructions.add(opcode.opcode_tanslation());
			offset += opcode.arg_len();
		}
		return instructions;
	}
}
