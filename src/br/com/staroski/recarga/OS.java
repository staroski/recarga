package br.com.staroski.recarga;

import java.io.*;

/**
 * Classe com informações da máquina virtual e sistema operacional
 * 
 * @author Ricardo Artur Staroski
 */
public final class OS {

	/**
	 * Java Runtime Environment version
	 */
	public static final String JAVA_VERSION	=System.getProperty("java.version");
	
	/**
	 * Java Runtime Environment vendor
	 */
	public static final String JAVA_VENDOR=System.getProperty("java.vendor");
	
	/**
	 * Java vendor URL
	 */
	public static final String JAVA_VENDOR_URL=System.getProperty("java.vendor.url");
	
	/**
	 * Java installation directory
	 */
	public static final String JAVA_HOME=System.getProperty("java.home");
	
	/**
	 * Java Virtual Machine specification version
	 */
	public static final String JAVA_VM_SPECIFICATION_VERSION=System.getProperty("java.vm.specification.version");
	
	/**
	 * Java Virtual Machine specification vendor
	 */
	public static final String JAVA_VM_SPECIFICATION_VENDOR=System.getProperty("java.vm.specification.vendor");
	
//	public static final String =System.getProperty("java.vm.specification.name"); // 	Java Virtual Machine specification name
//	public static final String =System.getProperty("java.vm.version"); // 	Java Virtual Machine implementation version
//	public static final String =System.getProperty("java.vm.vendor"); // 	Java Virtual Machine implementation vendor
//	public static final String =System.getProperty("java.vm.name"); // 	Java Virtual Machine implementation name
//	public static final String =System.getProperty("java.specification.version"); // 	Java Runtime Environment specification version
//	public static final String =System.getProperty("java.specification.vendor"); // 	Java Runtime Environment specification vendor
//	public static final String =System.getProperty("java.specification.name"); // 	Java Runtime Environment specification name
//	public static final String =System.getProperty("java.class.version"); // 	Java class format version number
//	public static final String =System.getProperty("java.class.path"); // 	Java class path
//	public static final String =System.getProperty("java.library.path"); // 	List of paths to search when loading libraries
//	public static final String =System.getProperty("java.io.tmpdir"); // 	Default temp file path
//	public static final String =System.getProperty("java.compiler"); // 	Name of JIT compiler to use
//	public static final String =System.getProperty("java.ext.dirs"); // 	Path of extension directory or directories
//	public static final String =System.getProperty("os.name"); // 	Operating system name
//	public static final String =System.getProperty("os.arch"); // 	Operating system architecture
//	public static final String =System.getProperty("os.version"); // 	Operating system version
//	public static final String =System.getProperty("file.separator"); // 	File separator ("/" on UNIX)
//	public static final String =System.getProperty("path.separator"); // 	Path separator (":" on UNIX)
//	public static final String =System.getProperty("line.separator"); // 	Line separator ("\n" on UNIX)

	/**
	 * User's account name
	 */
	public static final String USER_NAME=System.getProperty("user.name");
	
	/**
	 * User's home directory
	 */
	public static final File USER_HOME =new File(System.getProperty("user.home"));
	
	/**
	 * User's current working directory
	 */
	public static final File USER_DIR =new File(System.getProperty("user.dir"));
	
	
	private OS(){}
}
