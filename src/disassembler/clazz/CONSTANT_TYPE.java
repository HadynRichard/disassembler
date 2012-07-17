package disassembler.clazz;

/**
 * CONSTANT_TYPE
 * @author t4
 */
public enum CONSTANT_TYPE { 
	UNUSED0(-1),
	Utf8(-1),
	UNUSED2(-1),
	Interger(4), 
	Float(4), 
	Long(8), 
	Double(8), 
	Class(2), 
	String(2), 
	Fieldref(4), 
	Methodref(4),
	InterfactMethodref(4), 
	NameAndType(4);
	
	private int length;
	CONSTANT_TYPE(int length) {
		this.length = length;
	}
	public int getLength() {
		return length;
	}
}