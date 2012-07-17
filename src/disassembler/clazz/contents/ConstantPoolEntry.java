package disassembler.clazz.contents;

/**
 * ConstantPoolStruct
 * @author t4
 */
public interface ConstantPoolEntry {	
	public abstract byte getTag();
	public abstract byte[] getInfo();
	public abstract void setTag(byte tag);
	public abstract void setInfo(byte[] array);
	public abstract String toString();
}
