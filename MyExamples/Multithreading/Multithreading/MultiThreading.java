import java.io.*;
import java.util.*;
import java.sql.*;
import java.nio.file.Files;

public class MultiThreading {
	public static void main(String[] args) {

		int tdCnt = 3;
		Thread[] threads = new Thread[tdCnt];

		for (int i = 0; i < tdCnt; i++) {
			try {
				threads[i] = new FileWriterThread("Thread-" + i);
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

		verifyFileLengths();

		System.out.println("Main Finished.");
	}

	public static void verifyFileLengths() {
		File dir = new File(
				"C:/Users/stalupula/Documents/0Trash/Issues/Petco/Writer/");
		for (File child : dir.listFiles(new FilenameFilter() {
			public boolean accept(File directory, String fileName) {
				return fileName.endsWith(".xml");
			}
		})) {
			if (child.exists()) {
				double bytes = child.length();
				if (bytes != "ABC   123  !@#$%^&*()_+{}[]".length())
					System.out.println("File(" + child.getName()
							+ ") String Length: " + bytes);
				/*
				 * double kilobytes = (bytes / 1024); double megabytes =
				 * (kilobytes / 1024); if(megabytes!=2.094034194946289){
				 * System.out
				 * .println("File("+child.getName()+") String Length: "
				 * +megabytes); }
				 */
			}

		}

	}
}

class FileWriterThread extends Thread {

	public FileWriterThread(String name) {
		super(name);
	}

	public void run() {
		for (int i = 0; i < 10; i++) {
			try {
				String fileLocation = "C:/Users/stalupula/Documents/0Trash/Issues/Petco/Writer/";
				String origFileName = getName() + "_" + i + "_" + getSeqVal()
						+ ".xml";
				// String message =
				// readFile("C:/Users/stalupula/Documents/0Trash/Issues/Petco/PIX_201307172150_139524_810.XML");
				String message = readFile("C:/Users/stalupula/Documents/0Trash/Issues/Petco/SimpleText.xml");
				File writeFile = new File(fileLocation, origFileName + ".tmp");
				writeFile.createNewFile();
				writeFile.setWritable(true, false);
				FileOutputStream fos = new FileOutputStream(writeFile);
				fos.write(((String) message).getBytes());
				fos.flush();
				fos.close();
				if (!writeFile.renameTo(new File(fileLocation, origFileName))) {
					System.out.println("Rename failed");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	String readFile(String filename) {
		if(1==1)
		return "ABC   123  !@#$%^&*()_+{}[]";
		
		File f = new File(filename);
		try {
			byte[] bytes = Files.readAllBytes(f.toPath());
			return new String(bytes, "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	String getSeqVal() {
		String seqVal = null;
		try {
			String url = "jdbc:oracle:thin:@intg-mif-db:1521:INTMIFVM";
			Properties p = new Properties();
			p.put("user", "DROP13MDA");
			p.put("password", "DROP13MDA");

			Class.forName("oracle.jdbc.OracleDriver");

			Connection conn = DriverManager.getConnection(url, p);

			Statement stm = conn.createStatement();

			String myQuery = "SELECT SEQ_CROSSREF_ID.nextval FROM dual ";
			ResultSet rs = stm.executeQuery(myQuery);

			while (rs.next()) {
				seqVal = rs.getString(1);
			}

			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return seqVal;
	}
}

class CommMgrClientThread extends Thread {

	public CommMgrClientThread(String name) {
		super(name);
	}

	public void run() {

		System.out.println("Reading::" + getName());
		if (getName().equals("Thread2")) {
			try {
				Thread.sleep(200);
			} catch (Exception e) {
			}
		}

	}
}