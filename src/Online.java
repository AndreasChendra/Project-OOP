
public class Online extends Student{

	protected String location;

	public Online(String name, String birth, String id, String course, String course1, String grade, String location) {
		super(name, birth, id, course, course1, grade);
		this.location = location;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Online() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
