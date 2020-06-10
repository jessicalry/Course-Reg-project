

import java.util.ArrayList;

public interface StudentI {

	public void regACourse(Course co, ArrayList<Student>stu, ArrayList<Course>cou);
	public void withdrawACourse(String courseName, String section, ArrayList<Student>stu, ArrayList<Course>cou);
	public String userToString();
	public boolean studentLogIn(String username, ArrayList<Student>arrListStu);
}
