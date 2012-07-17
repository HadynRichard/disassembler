Compiled from "test.java"
public class test extends java.lang.Object
  SourceFile: "test.java"
  minor version: 0
  major version: 51
  Constant pool:
const #1 = class	#2;	//  test
const #2 = Asciz	test;
const #3 = class	#4;	//  java/lang/Object
const #4 = Asciz	java/lang/Object;
const #5 = Asciz	<init>;
const #6 = Asciz	()V;
const #7 = Asciz	Code;
const #8 = Method	#3.#9;	//  java/lang/Object."<init>":()V
const #9 = NameAndType	#5:#6;//  "<init>":()V
const #10 = Asciz	LineNumberTable;
const #11 = Asciz	LocalVariableTable;
const #12 = Asciz	this;
const #13 = Asciz	Ltest;;
const #14 = Asciz	main;
const #15 = Asciz	([Ljava/lang/String;)V;
const #16 = Field	#17.#19;	//  java/lang/System.out:Ljava/io/PrintStream;
const #17 = class	#18;	//  java/lang/System
const #18 = Asciz	java/lang/System;
const #19 = NameAndType	#20:#21;//  out:Ljava/io/PrintStream;
const #20 = Asciz	out;
const #21 = Asciz	Ljava/io/PrintStream;;
const #22 = String	#23;	//  Hello World!
const #23 = Asciz	Hello World!;
const #24 = Method	#25.#27;	//  java/io/PrintStream.println:(Ljava/lang/String;)V
const #25 = class	#26;	//  java/io/PrintStream
const #26 = Asciz	java/io/PrintStream;
const #27 = NameAndType	#28:#29;//  println:(Ljava/lang/String;)V
const #28 = Asciz	println;
const #29 = Asciz	(Ljava/lang/String;)V;
const #30 = Asciz	args;
const #31 = Asciz	[Ljava/lang/String;;
const #32 = Asciz	SourceFile;
const #33 = Asciz	test.java;

{
public test();
  Code:
   Stack=1, Locals=1, Args_size=1
   0:	aload_0
   1:	invokespecial	#8; //Method java/lang/Object."<init>":()V
   4:	return
  LineNumberTable: 
   line 2: 0

  LocalVariableTable: 
   Start  Length  Slot  Name   Signature
   0      5      0    this       Ltest;


public static void main(java.lang.String[]);
  Code:
   Stack=2, Locals=1, Args_size=1
   0:	getstatic	#16; //Field java/lang/System.out:Ljava/io/PrintStream;
   3:	ldc	#22; //String Hello World!
   5:	invokevirtual	#24; //Method java/io/PrintStream.println:(Ljava/lang/String;)V
   8:	return
  LineNumberTable: 
   line 4: 0
   line 5: 8

  LocalVariableTable: 
   Start  Length  Slot  Name   Signature
   0      9      0    args       [Ljava/lang/String;


}

