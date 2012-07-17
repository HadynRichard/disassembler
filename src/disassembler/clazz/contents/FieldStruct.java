package disassembler.clazz.contents;

/**
 * FieldStruct
 * @author t4
 */
public class FieldStruct {
	//http://docs.oracle.com/javase/specs/jvms/se5.0/html/ClassFile.doc.html#2877
	public short access_flags;
	public short name_index;
	public short descriptor_index;
	public short attributes_count;
	public AttributeEntry[] attributes;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("access_flags: " + this.access_flags + ", ");
		builder.append("name_index: " + this.name_index + ", ");
		builder.append("descriptor_index: " + this.descriptor_index + ", ");
		builder.append("attributes_count: " + this.attributes_count + ", ");
		builder.append("attributes: ");
		for(AttributeEntry as : this.attributes)
			builder.append("\t" + as.toString());
		return builder.toString();
	}
}
