package disassembler.clazz.contents.attributes;

import disassembler.clazz.AttributeEntryFactory;
import disassembler.clazz.ClassBuffer;
import disassembler.clazz.contents.AttributeEntry;
import disassembler.clazz.contents.ConstantPoolEntry;
import disassembler.clazz.instructionset.InstructionParser;

public class Code implements AttributeEntry {
	
	private short attribute_name_index;
	private int attribute_length;
	private byte[] info;
	private ConstantPoolEntry[] constpool;
	
	public Code(ConstantPoolEntry[] constpool){
		this.constpool = constpool;
	}

	@Override
	public short getAttributeNameIndex() {
		return this.attribute_name_index;
	}
	
	@Override
	public byte[] getInfo() {
		return this.info;
	}
	
	@Override
	public void setAttributeLength(int length) {
		if(this.attribute_length == 0)
			this.attribute_length = length;
	}
	
	@Override
	public void setAttributeNameIndex(short idx) {
		if(this.attribute_name_index == 0)
			this.attribute_name_index = idx;		
	}
	
	@Override
	public void setInfo(byte[] info) {
		if(this.info == null)
			this.info = info;		
	}

	@Override
	public int getAttributeLength() {
		return this.attribute_length;
	}
	
	@Override
	public void print() {
		ClassBuffer buff = new ClassBuffer(this.info);
		short max_stack = buff.g2();
		short max_locals = buff.g2();
		int code_length = buff.g4();
		byte[] code = new byte[code_length];
		buff.garr_b(code);
			
		short exception_table_length = buff.g2();
		for(int i = 0; i < exception_table_length; ++i, buff.g1()); //ignore exceptions for now
		
		short attribute_count = buff.g2();
		AttributeEntry[] attributes = new AttributeEntry[attribute_count];
		for(int ac = 0; ac < attribute_count; ++ac) {
			short attr_name_idx = buff.g2();
			String attr_name = constpool[attr_name_idx - 1].toString();					
			attributes[ac] = AttributeEntryFactory.getAttributeEntry(constpool, attr_name);
			attributes[ac].setAttributeNameIndex(attr_name_idx);
			attributes[ac].setAttributeLength(buff.g4());
			attributes[ac].setInfo(new byte[attributes[ac].getAttributeLength()]);
			buff.garr_b(attributes[ac].getInfo());
		}
		System.out.printf("\tCode:\n");
		ClassBuffer codebuff = new ClassBuffer(code);
		System.out.printf("\t\t");
		for(int i = 0; i < code.length; ++i) {
			System.out.printf("%s ", Integer.toHexString(codebuff.g1() & 0xff)); //TODO parse this...
		}
		System.out.printf("\n\t\t%s\n", InstructionParser.getInstructions(constpool, code));

	}
	

}
