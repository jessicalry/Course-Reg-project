
import java.io.*;
import java.util.*;

public class Admin extends User implements AdminI{
	
//	private ArrayList<Student> stuArrList = new ArrayList<Student>();
	
	
	public Admin(String userName, String pw) {
		super();
		this.username = userName;
		this.password = pw;
	}

	//Course Management
	public Course addCourse(String courseName, String secNum) {//in main, add course into course arraylist
		Course c = new Course();
		c.setCourseName(courseName);
		c.setSecNum(secNum);
		System.out.println("Course "+c.getCourseName()+" is added.");
		return c;
	}

	public void editCourse(Course c) {
		Scanner scn = new Scanner(System.in);
		System.out.println("Which attribute do you want to alter? 1 - section number; 2 - "
				+ "maximum number of students; 3 - course "
				+ "instructor; 4 - course location.");
		String attri = scn.next();
		switch (attri) {
		case ("1"):
			scn = new Scanner(System.in);
			System.out.println("Change to: ");
			String to = scn.next();
			c.setSecNum(to);
			System.out.println("Course section is changed to "+to);
			break;
		case ("2"):
			scn = new Scanner(System.in);
			System.out.println("Change to: ");
			int newNum = scn.nextInt();
			c.setMaxNumStud(newNum);
			System.out.println("Max number of student is changed to "+newNum);
			break;
		case ("3"):
			scn = new Scanner(System.in);
			System.out.println("Change to: ");
			String newInstru = scn.nextLine();
			c.setCourseInstru(newInstru);
			System.out.println("Course instructor is changed to "+newInstru);
			break;
		case ("4"):
			scn = new Scanner(System.in);
			System.out.println("Change to: ");
			String newLoc = scn.nextLine();
			c.setLocation(newLoc);
			System.out.println("Course location is changed to "+newLoc);
			break;
		}
	}
	
	public void displayCourse(Course c) {
		System.out.printf("Course name: %s\nCourse ID: %s\nCourse section: %s\nMaximum number of students: %d"
				+ "\nCurrent number of students: %d\nCourse instructor: %s"
				+ "\nCourse location: %s", c.getCourseName(),c.getCourseID(),c.getSecNum(),c.getMaxNumStud(),
				c.getCurrentStud(),c.getCourseInstru(),c.getLocation());
	}
	
	public void sort(ArrayList<Course>arrSorted) {
		boolean sorted = false;
		while(!sorted) {
			sorted = true;
			for (int i =0;i<arrSorted.size()-1;i++) {
				if(arrSorted.get(i).compareTo(arrSorted.get(i+1))==0) {
					Collections.swap(arrSorted, i, i+1);
					sorted = false;
				}
			}
		}
		for(Course c:arrSorted) {
			System.out.println(c.toString());
		}
	}
}

