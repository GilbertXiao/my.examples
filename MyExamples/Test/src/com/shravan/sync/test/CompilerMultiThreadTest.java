package com.shravan.sync.test;

public class CompilerMultiThreadTest {
	public static void main(String[] args) {

		int tdCnt = 5;
		Thread[] threads = new Thread[tdCnt];

		for (int i = 0; i < tdCnt; i++) {
			try {
				threads[i] = new CompilerThread("Thread-" + i);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		for (int i = 0; i < tdCnt; i++) {
			try {
				threads[i].start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		for (int i = 0; i < tdCnt; i++) {
			try {
				threads[i].join();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		System.out.println("Main Finished.");
	}
}

class CompilerThread extends Thread {

	public CompilerThread(String name) {
		super(name);
	}

	public void run() {

		if (getName().equals("Thread2")) {
			try {
				Thread.sleep(200);
			} catch (Exception e) {
			}
		}
		CompositeServiceCompiler compiler = new CompositeServiceCompiler();
		String javaCode = "class Test{"
			+ "   public static void main (String [] args){"
			+ "      System.out.println (\"Hello, World\");"
			+ "      System.out.println (args.length);" + "   }" + "}";
		String procName = "Test";
		compiler.compile(javaCode, procName);

	}
}