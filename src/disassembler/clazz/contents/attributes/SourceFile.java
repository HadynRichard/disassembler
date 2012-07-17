package disassembler.clazz.contents.attributes;

import disassembler.clazz.ClassBuffer;
import disassembler.clazz.contents.AttributeEntry;
import disassembler.clazz.contents.ConstantPoolEntry;

public class SourceFile implements AttributeEntry {
	
	private short attribute_name_index;
	private int attribute_length;
	private byte[] info;
	private ConstantPoolEntry[] constpool;
	
	public SourceFile(ConstantPoolEntry[] constpool){
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
		System.out.printf("\tSourceFile: %s\n", constpool[getSourceFileIndex() - 1].toString());
	}
	
	public short getSourceFileIndex() {
		ClassBuffer buff = new ClassBuffer(this.info);
		return buff.g2();
	}
}