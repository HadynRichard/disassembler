package disassembler.clazz;

/**
 * ClassBuffer
 * @author t4
 */
public class ClassBuffer {

	private int ptr;
	private byte[] buffer;
	
	public ClassBuffer(byte[] buffer) {
		this.ptr = 0;
		this.buffer = buffer;
	}
	
	public void garr_b(byte[] array) {
		for(int i = 0; i < array.length; ++i)
			array[i] = this.buffer[this.ptr++];
	}
	
	public void garr_s(short[] array) {
		for(int i = 0; i < array.length; ++i)
			array[i] = this.g2();
	}
	
	public void garr_i(int[] array) {
		for(int i = 0; i < array.length; ++i)
			array[i] = this.g4();
	}
	
	public byte g1() {
		return (this.buffer[this.ptr++]);
	}
	
	public short g2() {
		this.ptr += 2;
		return (short) (((this.buffer[this.ptr - 2] & 0xff) << 8) + 
						(this.buffer[this.ptr - 1] & 0xff));		
	}
	
	public int g4() {
		this.ptr += 4;
		return ((this.buffer[this.ptr - 4] & 0xff) << 24) + 
		       ((this.buffer[this.ptr - 3] & 0xff) << 16) +
		       ((this.buffer[this.ptr - 2] & 0xff) << 8) +
		       ((this.buffer[this.ptr - 1] & 0xff));	
	}
	
	public long g8() {
		this.ptr += 8;		
		return (((this.buffer[this.ptr - 8] & 0xff) << 56) +
				((this.buffer[this.ptr - 7] & 0xff) << 48) +
				((this.buffer[this.ptr - 6] & 0xff) << 40) +
				((this.buffer[this.ptr - 5] & 0xff) << 32) +
				((this.buffer[this.ptr - 4] & 0xff) << 24) + 
		        ((this.buffer[this.ptr - 3] & 0xff) << 16) +
		        ((this.buffer[this.ptr - 2] & 0xff) << 8) +
		        (this.buffer[this.ptr - 1] & 0xff));	
	}

}
