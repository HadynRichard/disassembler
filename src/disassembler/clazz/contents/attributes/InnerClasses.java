package disassembler.clazz.contents.attributes;

import disassembler.clazz.contents.AttributeEntry;
import disassembler.clazz.contents.ConstantPoolEntry;

public class InnerClasses implements AttributeEntry {
	
	private short attribute_name_index;
	private int attribute_length;
	private byte[] info;
	
	public InnerClasses(ConstantPoolEntry[] constpool){
		// TODO Auto-generated constructor stub
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
		System.out.printf("\tNot Implemented\n");		
	}
	
}
