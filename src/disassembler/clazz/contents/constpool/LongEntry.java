package disassembler.clazz.contents.constpool;

import disassembler.clazz.contents.ConstantPoolEntry;

/**
 * ConstantPoolEntry impl
 * @author t4 
 */
public class LongEntry implements ConstantPoolEntry {
	
	private byte tag;
	private byte[] info;
	
	@Override
	public void setInfo(byte[] array) {
		if(this.info == null)
			this.info = array;
	}
	
	@Override
	public void setTag(byte tag) {
		if(this.tag == 0)
			this.tag = tag;		
	}

	@Override
	public byte[] getInfo() {
		return this.info;
	}

	@Override
	public byte getTag() {
		return this.tag;
	}
}
