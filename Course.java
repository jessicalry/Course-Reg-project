
import java.io.Serializable;
import java.util.*;

public class Course implements Comparable<Course>, Serializable{
	//course info
	private String courseName;
	private String courseID;
	private int maxNumStud;
	private int currentStud;
	private ArrayList<Student> stuRegistered = new ArrayList<Student>();
	private String courseInstru;
	private String location;
	private String secNum;


	//Getters and Setters
	/**
	 * @return the courseName
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * @param courseName the courseName to set
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * @return the courseID
	 */
	public String getCourseID() {
		return courseID;
	}

	/**
	 * @param courseID the courseID to set
	 */
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	/**
	 * @return the maxNumStud
	 */
	public int getMaxNumStud() {
		return maxNumStud;
	}

	/**
	 * @param maxNumStud the maxNumStud to set
	 */
	public void setMaxNumStud(int maxNumStud) {
		this.maxNumStud = maxNumStud;
	}

	/**
	 * @return the currentStud
	 */
	public int getCurrentStud() {
		return currentStud;
	}

	/**
	 * @param newStuNum the currentStud to set
	 */
	public void setCurrentStud(int newStuNum) {
		this.currentStud = newStuNum;
	}

	/**
	 * @return the stuRegistered
	 */
	public ArrayList<Student> getStuRegistered() {
		return stuRegistered;
	}

	/**
	 * @param stuRegistered the stuRegistered to set
	 */
	public void setStuRegistered(ArrayList<Student> stuRegistered) {
		this.stuRegistered = stuRegistered;
	}

	/**
	 * @return the courseInstru
	 */
	public String getCourseInstru() {
		return courseInstru;
	}

	/**
	 * @param courseInstru the courseInstru to set
	 */
	public void setCourseInstru(String courseInstru) {
		this.courseInstru = courseInstru;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the secNum
	 */
	public String getSecNum() {
		return secNum;
	}

	/**
	 * @param secNum the secNum to set
	 */
	public void setSecNum(String secNum) {
		this.secNum = secNum;
	}

	//Constructors
	public Course() {
	}
	
	public Course(String name, String ID, int max, int currentStud, ArrayList<Student> students, String instru, String 
			sec, String loc) {
		this.courseName = name;
		this.secNum = sec;
		this.courseID = ID;
		this.maxNumStud = max;
		this.currentStud = currentStud;
		this.stuRegistered = students;
		this.location = loc;
		
	}
	
	//Methods
	
	public String toString() {
		return(getCourseName()+", course section: "+getSecNum());//It's printing out the secnum wrong.
	}
	
	public void stuToString() {
		for(Student s:this.getStuRegistered()) {
			System.out.println(s.getFirst()+" "+s.getLast());
		}
	}


	public int compareTo(Course c) {
		if(c.getCurrentStud()>this.getCurrentStud()) {
			return 0;
		}
//		else if(c.getCurrentStud()<this.getCurrentStud()){
//			return 1;
//		}
		else {
			return -1;
		}
	}
}


