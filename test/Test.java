import java.io.IOException;
import disassembler.ClassReader;
import disassembler.clazz.ClassStruct;

public class Test {
	public static void main(String[] args) throws IOException {
		

		ClassStruct clazz = ClassReader.getClass("test.class");
			
		System.out.printf("Magic Number: %d\nMinor Version: %d\nMajor Version: %d\n\n", 
				clazz.magic_number, clazz.minor_version, clazz.major_version);
		
		System.out.printf("Class Name: %s\nSuper Class: %s\nAccess Flags: %s\n", ClassReader.getThisClass(clazz), ClassReader.getSuperClass(clazz), ClassReader.getClassAccessFlags(clazz));
		
		System.out.printf("Interfaces: %s\n\n", ClassReader.getClassInterfaces(clazz));
		
		System.out.printf("Fields: %d\n", clazz.fields_count);
		if(clazz.fields_count != 0) {
			for(int idx = 0; idx < clazz.fields.length; ++idx) {
				System.out.printf("\tName: %s\n\tAccess Flags: %s\n\tDescriptor: %s\n", 
								ClassReader.getFieldName(clazz, idx), 
								ClassReader.getFieldAccessFlags(clazz, idx), 
								ClassReader.getFieldDescriptor(clazz, idx));
				System.out.printf("\tAttributes:\n");
				for(int attr_idx = 0; attr_idx < clazz.fields[idx].attributes_count; ++attr_idx) {
					//System.out.printf("\t\tAttribute Name: %s\n\tValue: ", ClassReader.getFieldAttributeName(clazz, idx, attr_idx));
					ClassReader.printFieldAttributeValue(clazz, idx, attr_idx);
				}
				System.out.printf("\n");
			}
		}
		
		System.out.printf("\nMethods: %d\n", clazz.methods_count);
		if(clazz.methods_count != 0) {
			for(int idx = 0; idx < clazz.methods.length; ++idx) {
				System.out.printf("\tName: %s\n\tAccess Flags: %s\n\tDescriptor: %s\n", 
								ClassReader.getMethodName(clazz, idx), 
								ClassReader.getMethodAccessFlags(clazz, idx), 
								ClassReader.getMethodDescriptor(clazz, idx));
				System.out.printf("\tAttributes: %d\n", clazz.methods[idx].attributes_count);
				for(int attr_idx = 0; attr_idx < clazz.methods[idx].attributes_count; ++attr_idx) {
					//System.out.printf("\tAttribute Name: %s\n\tValue: ", ClassReader.getMethodAttributeName(clazz, idx, attr_idx));
					ClassReader.printMethodAttributeValue(clazz, idx, attr_idx);
				}
				System.out.printf("\n");
			}
		}
		
		System.out.printf("\nAttributes: %d\n", clazz.attributes_count);
		if(clazz.attributes_count != 0) {
			for(int attr_idx = 0; attr_idx < clazz.attributes.length; ++attr_idx) {
				//System.out.printf("\tAttribute Name: %s\n\tValue: ", ClassReader.getClassAttributeName(clazz, attr_idx));
				ClassReader.printClassAttributeValue(clazz, attr_idx);
			}
		}
		
	}
}
