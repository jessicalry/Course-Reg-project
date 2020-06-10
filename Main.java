import java.io.*;
import java.util.*;

//nullpointerexception cannot execute 
public class Main implements Serializable{

	private static boolean isStudent;
	private static boolean isAdmin;
	private static String adminUsername = "Admin";
	private static String adminPassword = "Admin001";
	private ArrayList<String> stuPassword;
	private static ArrayList<Course> arrListCourse = new ArrayList<Course>();
	private static ArrayList<Student> arrListStu = new ArrayList<Student>();
	private static ArrayList<Course> fullCourses = new ArrayList<Course>();
	//	private String adminUserName;

	//Methods
	public static Student findStu(String first, String last){
		for (Student s : getArrListStu()){
			if (s.getFirst().equals(first) && s.getLast().equals(last)){
				return s;
			}
		}
		return null;
	}

	public static Student findStu(String username) {
		for (Student s : getArrListStu()){
			if (s.getUsername().equals(username)){
				return s;
			}
		}
		System.out.println("Invalid username or the student hasn't yet set the username.");
		return null;
	}

	public static Course findCourse(String cn, String sn) {
		try {
		for (Course c : arrListCourse){
			if(cn.equals(c.getCourseName())&& sn.equals(c.getSecNum())){
				return c;
			}
		}}
		catch(NullPointerException ne) {
			System.out.println("Course not found.");
		}
		return null;
	}

	public static Course findCourse(String ID) {
		for (Course c : arrListCourse){
			if(c.getCourseID().equals(ID)){
				System.out.println("Found it!");
				return c;
			}
		}
		return null;
	}

	public static void writeFullToFile() throws IOException{
		BufferedWriter writer = new BufferedWriter(new FileWriter("FullCourses.txt"));
		for (Course c : fullCourses) {
			writer.write(c.toString());
		}
		writer.close();
	}

	public static boolean adminLogIn(String password) {
		Scanner scn = new Scanner(System.in);
		boolean isLoggedIn = false;
		while (!isLoggedIn) {
			if(password.equals(adminPassword)) {
				System.out.println("You're logged in!");
				isLoggedIn = true;
			} else {
				System.out.println("Invalid username or password!");
				System.out.println("Try again or type [q] to exit: ");
				password = scn.next();
				if (password.equals("q")) {
					System.out.println("exiting");
					break;
				}
			}
		}
		return isLoggedIn;
	}

	public static void readFile(String inputFile) throws IOException{
		try {
		BufferedReader read = new BufferedReader(new FileReader(inputFile));
		String line = read.readLine();
		line = read.readLine();
		while(line!=null) {
			String[] attributes = line.split(",");
			Course course = new Course(attributes[0], attributes[1], Integer.parseInt(attributes[2]),Integer.parseInt(attributes[3]), new ArrayList<>() ,attributes[5],attributes[6],attributes[7]);
			arrListCourse.add(course);
			line = read.readLine();
		}
		read.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("File is not found.");
		}
	}
	
	public static void adminMainMenu() {
		System.out.println("Here are the things you can do:\n1 - Add a course\n2 - Delete a course\n"
				+ "3 - Edit a course\n"
				+ "4 - Register a student\n5 - Display info of a course\n6 - Display all courses\n"
				+ "7 - View full courses\n8 - Generate a full courses TXT file\n"
				+ "9 - Sort all courses according to the number of enrolled students\n"
				+ "10 - Display all registered students\n"
				+ "11 - Display all students in a course\n"
				+ "12 - Display all courses of a student\n"
				+ "13 - Enroll a student in a course\n"
				+ "14 - Save & Exit\n"
				+ "15 - Save & Go back to Main menu");
	}
	
	public static void stuMainMenu() {
		System.out.println("1 - View all courses\n2 - View all the non-full courses\n3 - Register in a course\n"
				+ "4 - Withdraw from a course\n5 - View all the courses registered in\n6 - Main menu\n7 - Exit");
	}

	//Main method
	public static void main (String[]args) throws IOException{
		Scanner scn = new Scanner(System.in);
		//deserializing admin...
		DataContainer dc = DataContainer.fromDisk("output.ser");
		
		Admin admin01;
		if (dc == null) {
			//create new dc
			admin01 = new Admin(adminUsername, adminPassword);
			arrListStu = new ArrayList<Student>();
			arrListCourse = new ArrayList<Course>();
			dc = new DataContainer(arrListCourse, arrListStu, admin01);
		} else {
			admin01 = dc.adminObject;
			arrListCourse = dc.courses;
			arrListStu = dc.students;
		}
		boolean exit = false;
		boolean mainMenu = false;
		try {
			readFile("MyUniversityCourses.csv");}
		catch (FileNotFoundException ex) {
			System.out.println("The file is not found.");
		}
		while (!exit) {
			mainMenu = false;
			System.out.println("Hi! Welcome to Jeje's Course Registration System!");
			System.out.println("Press 1 if you are an admin; press 2 if you are a student.");
			String ans = scn.next();
			if (Integer.parseInt(ans)==1) {
				isAdmin = true;
			}
			else if(Integer.parseInt(ans)==2){
				isStudent = true;
			}
			else {
				System.out.println("Please enter 1 or 2 only.");
			}
			if (isAdmin) {
				//log in
				System.out.println("Hi Admin!");
				System.out.println("Please enter your password: ");
				String pw = scn.next();
				if (adminLogIn(pw) == false) {
					continue;
				}
				
	
				//operations 
				System.out.println("Welcome to the system!");
				adminMainMenu();
				while(!mainMenu) {
				System.out.println("Please enter a number of your command: ");
				int action = 0;
				try {
					action = scn.nextInt();
				} 
				catch(InputMismatchException e) {
					System.out.println("Please enter numbers only.");
					scn.nextLine();
				}
				switch (action) {
					//course managament 
						case 1:
							scn = new Scanner(System.in);
							System.out.println("What is the course name?");
							String courseName = scn.next();
							System.out.println("What is the course section number?");
							String secNum = scn.next();
							Course c = admin01.addCourse(courseName, secNum);
							arrListCourse.add(c);
							break;
						case 2:
							scn = new Scanner(System.in);
							System.out.println("What is the course name?");
							String b = scn.next();
							System.out.println("What is the course section number?");
							String i = scn.next();
							arrListCourse.remove(findCourse(b,i));
							System.out.println("the course is removed!");
							break;
						case 3:
							scn = new Scanner(System.in);
							System.out.println("Please enter the course name and section number seperated by ONE COMMA");
							String[] iden = scn.nextLine().split(",");
							Course cc = findCourse(iden[0],iden[1]);
							if(findCourse(iden[0],iden[1])==null) {
								System.out.println("Course not found");
							}
							admin01.editCourse(cc);
							break;
						case 4:
							scn = new Scanner(System.in);
							System.out.println("What is the student first name?");
							String first = scn.next();
							System.out.println("What is the student last name?");
							String last = scn.next();
							Student s= new Student(first,last);
							arrListStu.add(s);
							//serializing student s...

//							System.out.println("student s has been serialized.");
							//deserializing student s...

							System.out.println("Student registered!");
							break;
						case 5:
							scn = new Scanner(System.in);
							System.out.println("What is the course ID of the intended course?");
							String ID = scn.next();
							Course cou =findCourse(ID); 
							admin01.displayCourse(cou);
							break;//Why does the program always execute this case after other cases? 
							//Reports
						case 6: 
							scn = new Scanner(System.in);
							for(Course course : arrListCourse) {
								System.out.println(course.toString());
							}
							break;
						case 7: 
							scn = new Scanner(System.in);
							for (Course co : arrListCourse) {
								if(co.getMaxNumStud()<=co.getCurrentStud()) {
									fullCourses.add(co);
									System.out.println(co.toString());
								}
							}
							break;
						case 8://can't find the file on my laptop
							scn = new Scanner(System.in);
							for(Course c2:arrListCourse) {
								if (c2.getCurrentStud() == c2.getMaxNumStud()) {
									fullCourses.add(c2);
									writeFullToFile();
									System.out.println("Action completed");
								}
							}
							System.out.println("Success! Going back to the admin menu.");
							break;
						case 9:
							scn = new Scanner(System.in);
							ArrayList<Course> arrSorted = new ArrayList<Course>();
							for (int k =0;k<arrListCourse.size();k++) {
								arrSorted.add(arrListCourse.get(k));
							}
							admin01.sort(arrSorted);
							break;
						case 10:
							scn = new Scanner(System.in);
							for(Student stu:arrListStu) {
								System.out.println(stu.userToString());	
							}
							break;
						case 11:
							scn = new Scanner(System.in);
							System.out.println("Please enter the course ID: ");
							String Id = scn.next();
							Course cour = findCourse(Id);
							cour.stuToString();
							break;
						case 12:
							scn = new Scanner(System.in);
							System.out.println("Enter the student first name ");
							String f1 = scn.next();
							System.out.println("Enter the student last name ");
							String l1 = scn.next();
							Student std1 = findStu(f1,l1);
							if(std1!=null) {
								for(Course cr:std1.getCoursesReg()) {
								System.out.println(cr.toString());
	}
							}
							break;
						case 13:
							scn = new Scanner(System.in);
							System.out.println("Enter the student first name ");
							String f = scn.next();
							System.out.println("Enter the student last name ");
							String l = scn.next();
							Student std = findStu(f,l);
							if(std!=null) {
								scn = new Scanner(System.in);
								System.out.println("Enter the course name: ");
								String cn = scn.next();
								System.out.println("Enter the section number: ");
								String sn = scn.next();
								Course c4 = findCourse(cn,sn);
								c4.getStuRegistered().add(std);
								std.getCoursesReg().add(c4);
								c4.setCurrentStud(c4.getStuRegistered().size());
								
							}
							break;
						case 14:
							scn = new Scanner(System.in);
							System.out.println("Thank you for using Jeje's system.");
							isAdmin = false;
							exit = true;
							mainMenu = true;
							dc.saveObjectsToDisk("output.ser");
							break;
						case 15:
							scn = new Scanner(System.in);
							System.out.println("Going back to the main menu...");
							mainMenu = true;
							isAdmin = false;
							dc.saveObjectsToDisk("output.ser");
							break;
						}
					}
			}
	
			if (isStudent) {
				//log in
				boolean isLoggedIn = true;
				System.out.println("Please enter your first name: ");
				String first = scn.next();
				System.out.println("Please enter your last name: ");
				String last = scn.next();
				Student c = new Student(first,last);
				c = findStu(first,last);
				if(c == null) {
					System.out.println("You are not registered yet!");
					continue;
				}
				if(c.getUsername()==null) {
					System.out.println("Please set your username: ");
					String un = scn.next();
					for(Student s:arrListStu) {
						if(un.equals(s.getUsername())) {
							System.out.println("This username has been taken. Try again: ");
							un = scn.next();
						}
					}
					System.out.println("Please set your password: ");
					String pw = scn.next();
					c.setUsername(un);
					c.setPassword(pw);
					System.out.println("Username and password set! Now please log in. ");
				}
				isLoggedIn = c.studentLogIn(c.getUsername(), arrListStu);
				stuMainMenu();
				if (isLoggedIn) {
					while(!mainMenu) {
						int ansr = scn.nextInt();
						switch(ansr) {
							case 1:
								for(Course c2:arrListCourse) {
									System.out.println(c2.toString());
								}
								break;
							case 2: 
								for(Course c3:arrListCourse) {
									if(c3.getMaxNumStud()>c3.getCurrentStud()) {
										System.out.println(c3.toString());
									}
								}
								break;
							case 3:
								scn = new Scanner(System.in);
								System.out.println("Please enter the course name: ");
								String n2 = scn.nextLine();
								System.out.println("Please enter the section number: ");
								String s2 = scn.next();
								System.out.println("intake done");
								Course co = findCourse(n2,s2);
								c.regACourse(co, arrListStu, arrListCourse);
								break;
							case 4: 
								scn = new Scanner(System.in);
								System.out.println("Please enter the course name: ");
								String n3 = scn.nextLine();
								System.out.println("Please enter the section number: ");
								String s3 = scn.next();
								c.withdrawACourse(n3, s3, arrListStu, arrListCourse);
								break;
							case 5:
								for(Course crs: c.getCoursesReg()) {
									System.out.println(crs.toString());
								}
								break;
							case 6:
								mainMenu = true;
								isStudent = false;
								break;
							case 7:
								isStudent = false;
								exit = true;
								mainMenu = true;
								dc.saveObjectsToDisk("output.ser");
								break;
						}
					}
				}
			}
	}
}




	/**
	 * @return the isStudent
	 */
	public static boolean isStudent() {
		return isStudent;
	}



	/**
	 * @param isStudent the isStudent to set
	 */
	public static void setStudent(boolean isStudent) {
		Main.isStudent = isStudent;
	}



	/**
	 * @return the isAdmin
	 */
	public static boolean isAdmin() {
		return isAdmin;
	}



	/**
	 * @param isAdmin the isAdmin to set
	 */
	public static void setAdmin(boolean isAdmin) {
		Main.isAdmin = isAdmin;
	}



	/**
	 * @return the adminUsername
	 */
	public String getAdminUsername() {
		return adminUsername;
	}



	/**
	 * @param adminUsername the adminUsername to set
	 */
	public void setAdminUsername(String adminUsername) {
		this.adminUsername = adminUsername;
	}



	/**
	 * @return the adminPassword
	 */
	public String getAdminPassword() {
		return adminPassword;
	}



	/**
	 * @param adminPassword the adminPassword to set
	 */
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	/**
	 * @return the stuPassword
	 */
	public ArrayList<String> getStuPassword() {
		return stuPassword;
	}



	/**
	 * @param stuPassword the stuPassword to set
	 */
	public void setStuPassword(ArrayList<String> stuPassword) {
		this.stuPassword = stuPassword;
	}



	/**
	 * @return the arrListCourse
	 */
	public ArrayList<Course> getArrListCourse() {
		return arrListCourse;
	}



	/**
	 * @param arrListCourse the arrListCourse to set
	 */
	public void setArrListCourse(ArrayList<Course> arrListCourse) {
		this.arrListCourse = arrListCourse;
	}


	/**
	 * @return the stuArrList
	 */
	public static ArrayList<Student> getArrListStu() {
		return arrListStu;
	}



	/**
	 * @param stuArrList the stuArrList to set
	 */
	public void setArrListStu(ArrayList<Student> setArrListStu) {
		this.arrListStu = arrListStu;
	}
}
