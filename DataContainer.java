

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class DataContainer implements Serializable {
	public ArrayList<Course> courses;
	public ArrayList<Student> students;
	public Admin adminObject;
	
	public DataContainer(ArrayList<Course> courses, ArrayList<Student> students, Admin adminObject) {
		this.courses = courses;
		this.students = students;
		this.adminObject = adminObject;
	}
	
	public void saveObjectsToDisk(String filename) {
		try {
			FileOutputStream fos = new FileOutputStream(filename);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this);
			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			System.out.println("Saving failed!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Saving failed!");
			e.printStackTrace();
		}
		
	}
	
	public static DataContainer fromDisk(String filename) {
		try {
			FileInputStream fis = new FileInputStream(filename);
			ObjectInputStream ois = new ObjectInputStream(fis);
			DataContainer dc = (DataContainer) ois.readObject();
			fis.close();
			ois.close();
			return dc;
		} catch (FileNotFoundException e) {
			return null; // return null to indicate file doesn't exist
		} catch (IOException e) {
			return null; // return null to indicate file was found but could not load object
		} catch (ClassNotFoundException e) {
			return null; // return null to indicate file was found but could not load object
		}
		
	}
}
