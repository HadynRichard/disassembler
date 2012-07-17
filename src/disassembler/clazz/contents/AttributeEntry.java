package disassembler.clazz.contents;

/**
 * AttributeStruct
 * @author t4
 */
public interface AttributeEntry {
	//http://docs.oracle.com/javase/specs/jvms/se5.0/html/ClassFile.doc.html#43817
	public abstract short getAttributeNameIndex();
	public abstract byte[] getInfo();
	public abstract void setAttributeNameIndex(short idx);
	public abstract void setAttributeLength(int length);
	public abstract int getAttributeLength();
	public abstract void setInfo(byte[] info);
	public abstract String toString();
	public abstract void print();
}
