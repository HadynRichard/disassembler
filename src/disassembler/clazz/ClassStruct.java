package disassembler.clazz;

import disassembler.clazz.contents.AttributeEntry;
import disassembler.clazz.contents.ConstantPoolEntry;
import disassembler.clazz.contents.FieldStruct;
import disassembler.clazz.contents.MethodStruct;

/**
 * ClassStruct
 * @author q
 */
public class ClassStruct {

	//http://docs.oracle.com/javase/specs/jvms/se5.0/html/ClassFile.doc.html
	public int magic_number;
	public short minor_version;
	public short major_version;

	public short constant_pool_count;
	public ConstantPoolEntry[] consant_pool;

	public short access_flags;

	public short this_class;
	public short super_class;

	public short interfaces_count;
	public short[] interfaces;
	
	public short fields_count;
	public FieldStruct[] fields;

	public short methods_count;
	public MethodStruct[] methods;

	public short attributes_count;
	public AttributeEntry[] attributes;
}
