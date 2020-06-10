

import java.io.IOException;

//some admin's behaviors are written in Course class for convenience. 
public interface AdminI {
	public Course addCourse(String courseName, String secNum);
	public void editCourse(Course c);
	public void displayCourse(Course c);
}
