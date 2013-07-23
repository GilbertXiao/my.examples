package com.shravan.sync.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;

public class CompositeServiceCompiler {
	
	private final static JavaCompiler COMPILER = ToolProvider.getSystemJavaCompiler();

	/**
	 * (non-Javadoc)
	 *
	 * @see ICompositeServiceCompiler#compile()
	 * @wasgenerated "UML to Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void compile(String program,String fileName) {
		// begin-user-code
		Iterable<? extends JavaFileObject> fileObjects = getJavaSourceFromString(
				program, fileName);
		String classpath = System.getProperty("java.class.path");
		System.out.println("Classpath:::"+System.getProperties());
		List<String> optionList = new ArrayList<String>();
		optionList.addAll(Arrays.asList("-classpath",classpath));
		optionList.addAll(Arrays.asList("-target","1.6")); 
		synchronized(COMPILER)
		{
			CompilationTask task = COMPILER.getTask(null, null, null, optionList, null, fileObjects);
			task.call();
		}
		// end-user-code
	}
	
	
	private static Iterable<StringBasedJavaSource> getJavaSourceFromString(
			String code, String fileName) {
		final StringBasedJavaSource jsfs;
		jsfs = new StringBasedJavaSource(fileName, code);
		return new Iterable<StringBasedJavaSource>() {
			public Iterator<StringBasedJavaSource> iterator() {
				return new Iterator<StringBasedJavaSource>() {
					boolean isNext = true;

					public boolean hasNext() {
						return isNext;
					}

					public StringBasedJavaSource next() {
						if (!isNext)
							throw new NoSuchElementException();
						isNext = false;
						return jsfs;
					}

					public void remove() {
						throw new UnsupportedOperationException();
					}
				};
			}
		};
	}
}
