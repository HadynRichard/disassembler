package disassembler;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import disassembler.clazz.AttributeEntryFactory;
import disassembler.clazz.CONSTANT_TYPE;
import disassembler.clazz.ClassBuffer;
import disassembler.clazz.ClassStruct;
import disassembler.clazz.ConstantPoolEntryFactory;
import disassembler.clazz.contents.AttributeEntry;
import disassembler.clazz.contents.ConstantPoolEntry;
import disassembler.clazz.contents.FieldStruct;
import disassembler.clazz.contents.MethodStruct;
import disassembler.clazz.contents.constpool.*;

/**
 * ClassReader
 * @author t4
 */
public class ClassReader {
	
	/* Class methods  */
	public static String getThisClass(ClassStruct clazz) {
		ClassEntry entry = (ClassEntry) clazz.consant_pool[clazz.this_class - 1];
		Utf8Entry str = (Utf8Entry) clazz.consant_pool[entry.getNameIndex() - 1];
		return str.toString();
	}
	
	public static String getSuperClass(ClassStruct clazz) {
		if(clazz.super_class == 0)
			return "None";
		ClassEntry entry = (ClassEntry) clazz.consant_pool[clazz.super_class - 1];
		Utf8Entry str = (Utf8Entry) clazz.consant_pool[entry.getNameIndex() - 1];
		return str.toString();
	}
	
	public static ArrayList<String> getClassInterfaces(ClassStruct clazz) {
		ArrayList<String> list = new ArrayList<String>();
		if(clazz.interfaces == null)			
			return list;
		for(Short s : clazz.interfaces) {
			ClassEntry entry = (ClassEntry) clazz.consant_pool[s - 1];
			Utf8Entry str = (Utf8Entry) clazz.consant_pool[entry.getNameIndex() - 1];
			list.add(str.toString());
		}
		return list;
	}
	
	public static String getClassAccessFlags(ClassStruct clazz) {
		return Modifier.toString(clazz.access_flags);
	}
	
	//-----------------------------------------------------------------------------------
	/* Field  */
	
	public static String getFieldAccessFlags(ClassStruct clazz, int idx) {
		return Modifier.toString(clazz.fields[idx].access_flags);
	}
	
	public static String getFieldName(ClassStruct clazz, int idx) {
		Utf8Entry str = (Utf8Entry) clazz.consant_pool[clazz.fields[idx].name_index - 1];
		return str.toString();
	}
	
	public static String getFieldDescriptor(ClassStruct clazz, int idx) {
		Utf8Entry str = (Utf8Entry) clazz.consant_pool[clazz.fields[idx].descriptor_index - 1];
		return str.toString();
	}
	
	public static String getFieldAttributeName(ClassStruct clazz, int field_idx, int attr_idx) {
		short name_index = clazz.fields[field_idx].attributes[attr_idx].getAttributeNameIndex();
		Utf8Entry str = (Utf8Entry) clazz.consant_pool[name_index - 1];
		return str.toString();
	}
	
	public static void printFieldAttributeValue(ClassStruct clazz, int field_idx, int attr_idx) {
		clazz.fields[field_idx].attributes[attr_idx].print();
	}
	
	//-----------------------------------------------------------------------------------
	/* Methods */
	
	public static String getMethodAccessFlags(ClassStruct clazz, int idx) {
		return Modifier.toString(clazz.methods[idx].access_flags);
	}
	
	public static String getMethodName(ClassStruct clazz, int idx) {
		Utf8Entry str = (Utf8Entry) clazz.consant_pool[clazz.methods[idx].name_index - 1];
		return str.toString();
	}
	
	public static String getMethodDescriptor(ClassStruct clazz, int idx) {
		Utf8Entry str = (Utf8Entry) clazz.consant_pool[clazz.methods[idx].descriptor_index - 1];
		return str.toString();
	}
	
	public static String getMethodAttributeName(ClassStruct clazz, int method_idx, int attr_idx) {
		short name_index = clazz.methods[method_idx].attributes[attr_idx].getAttributeNameIndex();
		Utf8Entry str = (Utf8Entry) clazz.consant_pool[name_index - 1];
		return str.toString();
	}
	
	public static void printMethodAttributeValue(ClassStruct clazz, int method_idx, int attr_idx) {
		clazz.methods[method_idx].attributes[attr_idx].print();
	}
	
	//-----------------------------------------------------------------------------------
	/* Attributes */
	public static String getClassAttributeName(ClassStruct clazz, int attr_idx) {
		short name_index = clazz.attributes[attr_idx].getAttributeNameIndex();
		Utf8Entry str = (Utf8Entry) clazz.consant_pool[name_index - 1];
		return str.toString();
	}
	
	public static void printClassAttributeValue(ClassStruct clazz, int attr_idx) {
		clazz.attributes[attr_idx].print();
	}

	
	//-----------------------------------------------------------------------------------
	/* Parser */
	public static ClassStruct getClass(String filename) throws IOException {
		/* Read file's stream into a buffer of exact size */
		FileInputStream is = new FileInputStream(filename);
		ArrayList<Byte> bytes = new ArrayList<Byte>();
		byte b;
		while((b = (byte) is.read()) != -1) {
			bytes.add(b);
		}
		byte[] buffer = new byte[bytes.size()];
		for(int i = 0; i < buffer.length; ++i)
			buffer[i] = bytes.get(i);

		/* Encapsulate the read stream into a helper class */
		ClassBuffer stream = new ClassBuffer(buffer);
		ClassStruct c = new ClassStruct();
		c.magic_number = stream.g4();
		c.minor_version = stream.g2();
		c.major_version = stream.g2();
		
		//System.out.printf("magic_number: %d, minor_version: %d, major_version: %d\n", c.magic_number, c.minor_version, c.major_version);
				
		c.constant_pool_count = stream.g2();
		//System.out.printf("constant_pool_count: %d\n", c.constant_pool_count);

		c.consant_pool = new ConstantPoolEntry[c.constant_pool_count];
		for(int cpc = 0; cpc < c.constant_pool_count - 1; ++cpc) {
			byte tag = stream.g1();
			c.consant_pool[cpc] = ConstantPoolEntryFactory.getConstantPoolEntry(tag);
			c.consant_pool[cpc].setTag(tag);
			if(tag != CONSTANT_TYPE.Utf8.ordinal()) {
				//Normal procedure
				c.consant_pool[cpc].setInfo(new byte[CONSTANT_TYPE.values()[tag].getLength()]);
				stream.garr_b(c.consant_pool[cpc].getInfo());
			} else {
				//Utf8 procedure
				short read_len = stream.g2();
				c.consant_pool[cpc].setInfo(new byte[read_len]);
				stream.garr_b(c.consant_pool[cpc].getInfo());
			}	
			//System.out.printf("\tIndex: %d\tType: %s\t%sPayload: %s\n", cpc + 1, CONSTANT_TYPE.values()[tag], (CONSTANT_TYPE.values()[tag].toString().length() < 12 ? "\t" : ""), c.consant_pool[cpc]);
		}
		
		
		c.access_flags = stream.g2();
		c.this_class = stream.g2();
		c.super_class = stream.g2();
		
		//System.out.printf("access_flags: %d, this_class: %d, super_class: %d\n", c.access_flags, c.this_class, c.super_class);

	
		c.interfaces_count = stream.g2();
		//System.out.printf("Interfaces count: %d\n", c.interfaces_count);

		if(c.interfaces_count > 0) {
			c.interfaces = new short[c.interfaces_count];
			stream.garr_s(c.interfaces);
			//System.out.printf("\tInterfaces: %d", c.interfaces);
		}
		
		////System.out.printf("%s\n", SEPSTR);
		
		c.fields_count = stream.g2();
		//System.out.printf("Fields count: %d\n", c.fields_count);

		if(c.fields_count > 0) {
			c.fields = new FieldStruct[c.fields_count];
			for(int fc = 0; fc < c.fields_count; ++fc) {
				c.fields[fc] = new FieldStruct();
				c.fields[fc].access_flags = stream.g2();
				c.fields[fc].name_index = stream.g2();
				c.fields[fc].descriptor_index = stream.g2();
				c.fields[fc].attributes_count = stream.g2();				
				c.fields[fc].attributes = new AttributeEntry[c.fields[fc].attributes_count];
				for(int ac = 0; ac < c.fields[fc].attributes_count; ++ac) {
					short attr_name_idx = stream.g2();
					String attr_name = c.consant_pool[attr_name_idx - 1].toString();	
					c.fields[fc].attributes[ac] = AttributeEntryFactory.getAttributeEntry(c.consant_pool, attr_name);
					c.fields[fc].attributes[ac].setAttributeNameIndex(attr_name_idx);
					c.fields[fc].attributes[ac].setAttributeLength(stream.g4());
					c.fields[fc].attributes[ac].setInfo(new byte[c.fields[fc].attributes[ac].getAttributeLength()]);
					stream.garr_b(c.fields[fc].attributes[ac].getInfo());
				}
				//System.out.printf("%s\n", c.fields[fc]);
			}
		}

		c.methods_count = stream.g2();	
		//System.out.printf("Methods count: %d\n", c.methods_count);

		if(c.methods_count > 0) {
			c.methods = new MethodStruct[c.methods_count];
			for(int mc = 0; mc < c.methods_count; ++mc) {
				c.methods[mc] = new MethodStruct();
				c.methods[mc].access_flags = stream.g2();
				c.methods[mc].name_index = stream.g2();
				c.methods[mc].descriptor_index = stream.g2();
				c.methods[mc].attributes_count = stream.g2();
				c.methods[mc].attributes = new AttributeEntry[c.methods[mc].attributes_count];
				for(int ac = 0; ac < c.methods[mc].attributes_count; ++ac) {
					short attr_name_idx = stream.g2();
					String attr_name = c.consant_pool[attr_name_idx - 1].toString();					
					c.methods[mc].attributes[ac] = AttributeEntryFactory.getAttributeEntry(c.consant_pool, attr_name);
					c.methods[mc].attributes[ac].setAttributeNameIndex(attr_name_idx);
					c.methods[mc].attributes[ac].setAttributeLength(stream.g4());
					c.methods[mc].attributes[ac].setInfo(new byte[c.methods[mc].attributes[ac].getAttributeLength()]);
					stream.garr_b(c.methods[mc].attributes[ac].getInfo());
				}
				//System.out.printf("%s\n", c.methods[mc]);
			}
		}
		
		c.attributes_count = stream.g2();
		//System.out.printf("Attribute count: %d\n", c.attributes_count);

		if(c.attributes_count > 0) {
			c.attributes = new AttributeEntry[c.attributes_count];
			for(int ac = 0; ac < c.attributes_count; ++ac) {
				short attr_name_idx = stream.g2();
				String attr_name = c.consant_pool[attr_name_idx - 1].toString();
				c.attributes[ac] = AttributeEntryFactory.getAttributeEntry(c.consant_pool, attr_name);
				c.attributes[ac].setAttributeNameIndex(attr_name_idx);
				c.attributes[ac].setAttributeLength(stream.g4());
				c.attributes[ac].setInfo(new byte[c.attributes[ac].getAttributeLength()]);
				stream.garr_b(c.attributes[ac].getInfo());
				//System.out.printf("%s\n", c.attributes[ac]);
			}
		}

		return c;
	}
}
