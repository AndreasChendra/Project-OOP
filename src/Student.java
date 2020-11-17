
public class Student {

	protected String name;
	protected String birth;
	protected String id;
	protected String course;
	protected String course1;
	protected String grade;
	
	public Student(String name, String birth, String id, String course, String course1, String grade) {
		super();
		this.name = name;
		this.birth = birth;
		this.id = id;
		this.course = course;
		this.course1 = course1;
		this.grade = grade;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getCourse1() {
		return course1;
	}

	public void setCourse1(String course1) {
		this.course1 = course1;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	public Student() {
		// TODO Auto-generated constructor stub
	}

}
