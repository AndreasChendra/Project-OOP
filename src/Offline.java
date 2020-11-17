
public class Offline extends Student {

	protected int absence;

	public Offline(String name, String birth, String id, String course, String course1, String grade, int absence) {
		super(name, birth, id, course, course1, grade);
		this.absence = absence;
	}

	public int getAbsence() {
		return absence;
	}

	public void setAbsence(int absence) {
		this.absence = absence;
	}

	public Offline() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
