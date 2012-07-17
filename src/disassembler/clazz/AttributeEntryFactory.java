package disassembler.clazz;

import disassembler.clazz.contents.AttributeEntry;
import disassembler.clazz.contents.ConstantPoolEntry;
import disassembler.clazz.contents.attributes.*;
import disassembler.clazz.contents.attributes.Deprecated;

public class AttributeEntryFactory {
	
	public static AttributeEntry getAttributeEntry(ConstantPoolEntry[] constpool, String name) {
		if(name.equals("Code"))
			return new Code(constpool);
		if(name.equals("ConstantValue"))
			return new ConstantValue(constpool);
		if(name.equals("Deprecated"))
			return new Deprecated(constpool);
		if(name.equals("Exceptions"))
			return new Exceptions(constpool);
		if(name.equals("InnerClasses"))
			return new InnerClasses(constpool);
		if(name.equals("LineNumberTable"))
			return new LineNumberTable(constpool);
		if(name.equals("LocalVariableTable"))
			return new LocalVariableTable(constpool);
		if(name.equals("SourceFile"))
			return new SourceFile(constpool);
		if(name.equals("Synthetic"))
			return new Synthetic(constpool);
		return null;
	}
}
