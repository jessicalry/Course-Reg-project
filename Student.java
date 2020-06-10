

import java.util.*;

public class Student extends User implements StudentI{
	
	ArrayList<Course>coursesReg = new ArrayList<Course>();
	
	public Student(String first, String last) {
		super(first, last);
	}
	
	public Student(String username, String password, String first, String last) {
		this.username = username;
		this.password = password;
		this.first = first;
		this.last = last;
	}
	
	//Methods
	public void regACourse(Course co, ArrayList<Student>stu, ArrayList<Course>cou) {
			if(co.getCurrentStud()<co.getMaxNumStud()) {
				System.out.println("not full");
				co.getStuRegistered().add(this);
				System.out.println("successfully added");
				this.getCoursesReg().add(co);
				System.out.println("successfull");
				co.setCurrentStud(co.getStuRegistered().size());
				}
			}
	
	public void withdrawACourse(String courseName, String section, ArrayList<Student>stu, ArrayList<Course>cou) {
		for(Course c:cou) {
			if(c.getCourseName().equals(courseName)&&c.getSecNum().equals(section)) {
				c.getStuRegistered().remove(this);
				this.getCoursesReg().remove(c);
				c.setCurrentStud(c.getStuRegistered().size());
			}
		}
	}
	
	public String userToString() {
		return (this.getFirst()+" "+this.getLast());
	}
	
	public boolean studentLogIn(String username, ArrayList<Student>arrListStu) {
		boolean isLoggedIn = false;
		Scanner scn = new Scanner(System.in);
		while (!isLoggedIn) {
			for (Student s:arrListStu) {
				if (username.equals(s.getUsername())) {
					System.out.println("Hi, "+s.getFirst()+" "+s.getLast()+", please enter your password.");
					String p = scn.next();
					if (p.equals(s.getPassword())) {
						isLoggedIn = true;
						System.out.println("Welcome to the system!");
					}
					else {
						System.out.println("Invalid username or password! Try again: ");
						p = scn.next();
					}
				}
			}
		}
		return isLoggedIn;
		}
	//G and S
	/**
	 * @return the coursesReg
	 */
	public ArrayList<Course> getCoursesReg() {
		return coursesReg;
	}

	/**
	 * @param coursesReg the coursesReg to set
	 */
	public void setCoursesReg(ArrayList<Course> coursesReg) {
		this.coursesReg = coursesReg;
	}
	

}
