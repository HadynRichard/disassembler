package disassembler.clazz;

import disassembler.clazz.contents.ConstantPoolEntry;
import disassembler.clazz.contents.constpool.*;

/**
 * ConstantPoolEntryFactory
 * @author t4
 */
public class ConstantPoolEntryFactory {

	public static ConstantPoolEntry getConstantPoolEntry(byte tag) {
		CONSTANT_TYPE type = CONSTANT_TYPE.values()[tag];
		switch(type) {
			case Utf8:
				return new Utf8Entry();
			case Interger:			
				return new IntegerEntry();
			case Float:
				return new FloatEntry();
			case Long:
				return new LongEntry();
			case Double:
				return new DoubleEntry();
			case Class:
				return new ClassEntry();
			case String:
				return new StringEntry();				
			case Fieldref:
				return new FieldrefEntry();
			case Methodref:
				return new MethodrefEntry();				
			case InterfactMethodref:
				return new InterfactMethodrefEntry();
			case NameAndType:
				return new NameAndTypeEntry();
		}
		return null;
	}
}
