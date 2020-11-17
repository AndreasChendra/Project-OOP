import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

// Data Kelompok
// Andreas Chendra (2201744084)
// Sin Luis (2201749034)
// Yohanes Reynaldi (2201738043)

public class Main {

	Scanner scan = new Scanner(System.in);
	Vector<Student> stud = new Vector<>();

	public void menu() {
		System.out.println("Student Information System");
		System.out.println("1. Input Data");
		System.out.println("2. Search Data");
		System.out.println("3. Delete Data");
		System.out.println("4. Update Data");
		System.out.println("5. Exit");
		System.out.print("Choose >> ");
	}

	public void enter() {
		for (int i = 0; i < 27; i++) {
			System.out.println();
		}
	}

	public boolean word(String word) {
		if (word.matches("[a-zA-Z]+")) {
			return true;
		} else {
			return false;
		}
	}

	public void case1() {
		String name = "";
		String birth = "";
		String id = "";
		String course = "";
		String course1 = "";
		String grade = "";
		int date = 0;
		String month = "";
		int year = 0;
		String tahun = "";
		String option = "";
		String location = "";
		int absen = 0;
		boolean flag = true;

		do {

			do {
				System.out.print("Input your name [2..30] : ");
				name = scan.nextLine();
				if (!name.contains(" ")) {
					System.out.println("Input must be 2 words!");
				}
			} while (!name.contains(" "));
		} while (name.length() < 2 || name.length() > 30);

		System.out.println("Date of birth");
		do {
			System.out.print("Date [1..31] : ");
			try {

				date = scan.nextInt();
				scan.nextLine();
			} catch (Exception e) {
				System.out.println("Input must be a number!");
				scan.nextLine();
			}
		} while (date < 1 || date > 31);

		do {
			flag = true;
			System.out.print("Month [January..December] : ");
			month = scan.nextLine();
			if (word(month) == false) {
				System.out.println("Input must be a word!");
			} else {
				if (month.equalsIgnoreCase("January")) {
					flag = false;
				} else if (month.equalsIgnoreCase("February")) {
					flag = false;
				} else if (month.equalsIgnoreCase("March")) {
					flag = false;
				} else if (month.equalsIgnoreCase("April")) {
					flag = false;
				} else if (month.equalsIgnoreCase("May")) {
					flag = false;
				} else if (month.equalsIgnoreCase("June")) {
					flag = false;
				} else if (month.equalsIgnoreCase("July")) {
					flag = false;
				} else if (month.equalsIgnoreCase("August")) {
					flag = false;
				} else if (month.equalsIgnoreCase("September")) {
					flag = false;
				} else if (month.equalsIgnoreCase("October")) {
					flag = false;
				} else if (month.equalsIgnoreCase("November")) {
					flag = false;
				} else if (month.equalsIgnoreCase("December")) {
					flag = false;
				}
			}
		} while (flag == true || word(month) == false);

		do {
			System.out.print("Year [minimum number of 4 digits] : ");
			year = scan.nextInt();
			scan.nextLine();
			tahun = "" + year;
		} while (tahun.length() < 4 || tahun.length() > 4);
		birth = date + " " + month + " " + year;

		do {
			System.out.print("Student ID [8..10] : ");
			id = scan.nextLine();
		} while (id.length() < 8 || id.length() > 10);

		System.out.print("What Courses do you take in current semester : ");
		course = scan.nextLine();

		System.out.print("Input Courses you want to take : ");
		course1 = scan.nextLine();

		System.out.print("Grade [A..F] : ");
		grade = scan.nextLine();

		do {
			flag = true;
			System.out.print("Online or Offline : ");
			option = scan.nextLine();
			if (option.equalsIgnoreCase("Online")) {
				flag = false;
				System.out.print("Your Location : ");
				location = scan.nextLine();
				stud.add(new Online(name, birth, id, course, course1, grade, location));
			} else if (option.equalsIgnoreCase("Offline")) {
				flag = false;
				System.out.print("Absence : ");
				try {
					absen = scan.nextInt();
					scan.nextLine();
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("input must be a number!");
				}
				stud.add(new Offline(name, birth, id, course, course1, grade, absen));
			}
		} while (flag == true);

		try {
			FileWriter fw = new FileWriter("StudentData.txt");
			BufferedWriter bw = new BufferedWriter(fw);

			for (int i = 0; i < stud.size(); i++) {
				if (stud.get(i) instanceof Online) {
					bw.write(stud.get(i).getId() + "#" + stud.get(i).getName() + "#" + stud.get(i).getBirth() + "#"
							+ stud.get(i).getCourse() + "#" + stud.get(i).getCourse1() + "#" + stud.get(i).getGrade()
							+ "#Online#" + ((Online) stud.get(i)).getLocation());
					bw.newLine();
				} else if (stud.get(i) instanceof Offline) {
					bw.write(stud.get(i).getId() + "#" + stud.get(i).getName() + "#" + stud.get(i).getBirth() + "#"
							+ stud.get(i).getCourse() + "#" + stud.get(i).getCourse1() + "#" + stud.get(i).getGrade()
							+ "#Offline#" + ((Offline) stud.get(i)).getAbsence());
					bw.newLine();
				}
			}

			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Success...");
	}

	public void case2() {

		String id = "";
		int temp = 0;
		int input = 0;
		boolean flag = false;

		if (stud.isEmpty()) {
			System.out.println("There is no data!");
		} else {

			do {
				System.out.println("1. Search ID Student");
				System.out.println("2. List student who cannot join exam");
				System.out.print("Choose : ");
				input = scan.nextInt();
				scan.nextLine();
			} while (input < 1 || input > 2);

			if (input == 1) {
				enter();
				viewAll();
				do {
					System.out.print("ID you want to search [8..10]: ");
					id = scan.nextLine();
				} while (id.length() < 8 || id.length() > 10);

				for (int i = 0; i < stud.size(); i++) {
					if (id.equals(stud.get(i).getId())) {
						temp = i;
						flag = true;
						break;
					}
				}
				System.out.println("\n");
				if (flag == true) {

					if (stud.get(temp) instanceof Online) {

						System.out.println(
								"+------------+-----------------+-------------------+------------------+-----------------+-------------------+-------+");
						System.out.println(
								"|     ID     |   Name          | Birth             | Location         | Course Current  | Course Taken      | Grade |");
						System.out.println(
								"+------------+-----------------+-------------------+------------------+-----------------+-------------------+-------+");
						System.out.printf("| %10s | %-15s | %-17s | %-16s | %-15s | %-17s | %-5s |\n",
								stud.get(temp).getId(), stud.get(temp).getName(), stud.get(temp).getBirth(),
								((Online) stud.get(temp)).getLocation(), stud.get(temp).getCourse(),
								stud.get(temp).getCourse1(), stud.get(temp).getGrade());
						System.out.println(
								"+------------+-----------------+-------------------+------------------+-----------------+-------------------+-------+");
					} else if (stud.get(temp) instanceof Offline) {

						System.out.println(
								"+------------+-----------------+-------------------+-----------------+-------------------+-------+---------+");
						System.out.println(
								"|     ID     |   Name          | Birth             | Course Current  | Course Taken      | Grade | Absence |");
						System.out.println(
								"+------------+-----------------+-------------------+-----------------+-------------------+-------+---------+");
						System.out.printf("| %10s | %-15s | %-17s | %-15s | %-17s | %-5s | %-7d |\n",
								stud.get(temp).getId(), stud.get(temp).getName(), stud.get(temp).getBirth(),
								stud.get(temp).getCourse(), stud.get(temp).getCourse1(), stud.get(temp).getGrade(),
								((Offline) stud.get(temp)).getAbsence());
						System.out.println(
								"+------------+-----------------+-------------------+-----------------+-------------------+-------+---------+");
					}
				} else {
					System.out.println("There is no data!");
				}
			} else if (input == 2) {

				int tempo = 0;

				for (int i = 0; i < stud.size(); i++) {
					if (stud.get(i) instanceof Offline) {
						if (((Offline) stud.get(i)).getAbsence() > 3) {
							tempo++;
							if (tempo == 1) {
								System.out.println(
										"+----+------------+--------------------+-------------------+-----------------+-------------------+-------+---------+");
								System.out.println(
										"| No |     ID     |   Name             | Birth             | Course Current  | Course Taken      | Grade | Absence |");
								System.out.println(
										"+----+------------+--------------------+-------------------+-----------------+-------------------+-------+---------+");

							}
							System.out.printf("| %2d | %10s | %-18s | %-17s | %-15s | %-17s | %-5s | %-7d |\n", tempo,
									stud.get(i).getId(), stud.get(i).getName(), stud.get(i).getBirth(),
									stud.get(i).getCourse(), stud.get(i).getCourse1(), stud.get(i).getGrade(),
									((Offline) stud.get(i)).getAbsence());
						}
					}
				}
				System.out.println(
						"+----+------------+--------------------+-------------------+-----------------+-------------------+-------+---------+");
				if (tempo == 0) {
					System.out.println("No Student!");
				}

			}
		}

	}

	public void case3() {

		int delete = 0;

		if (stud.isEmpty()) {
			System.out.println("There is no data!");
		} else {
			viewAll();

			do {
				System.out.printf("Choose data want to delete [1..%d] : ", stud.size());
				delete = scan.nextInt();
				scan.nextLine();
			} while (delete < 1 || delete > stud.size());
			stud.remove(delete);
			System.out.println("Delete Success...");

			try {
				FileWriter fw = new FileWriter("StudentData.txt");
				BufferedWriter bw = new BufferedWriter(fw);

				for (int i = 0; i < stud.size(); i++) {
					if (stud.get(i) instanceof Online) {
						bw.write(stud.get(i).getId() + "#" + stud.get(i).getName() + "#" + stud.get(i).getBirth() + "#"
								+ stud.get(i).getCourse() + "#" + stud.get(i).getCourse1() + "#"
								+ stud.get(i).getGrade() + "#Online#" + ((Online) stud.get(i)).getLocation());
						bw.newLine();
					} else if (stud.get(i) instanceof Offline) {
						bw.write(stud.get(i).getId() + "#" + stud.get(i).getName() + "#" + stud.get(i).getBirth() + "#"
								+ stud.get(i).getCourse() + "#" + stud.get(i).getCourse1() + "#"
								+ stud.get(i).getGrade() + "#Offline#" + ((Offline) stud.get(i)).getAbsence());
						bw.newLine();
					}
				}

				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void case4() {

		String update = "";
		int temp = 0;
		String name = "";
		String birth = "";
		String id = "";
		String course = "";
		String course1 = "";
		String grade = "";
		int date = 0;
		String month = "";
		int year = 0;
		String tahun = "";
		String option = "";
		String location = "";
		int absen = 0;
		boolean flag = true;
		boolean flag1 = false;

		if (stud.isEmpty()) {
			System.out.println("There is no data!");
		} else {
			viewAll();

			do {
				System.out.print("Choose ID want to update [8..10]: ");
				update = scan.nextLine();
			} while (update.length() < 8 || update.length() > 10);

			for (int i = 0; i < stud.size(); i++) {
				if (update.equals(stud.get(i).getId())) {
					temp = i;
					flag1 = true;
					break;
				}
			}

			if (flag1 == true) {
				do {

					do {
						System.out.print("Input your name [2..30] : ");
						name = scan.nextLine();
						if (!name.contains(" ")) {
							System.out.println("Input must be 2 words!");
						}
					} while (!name.contains(" "));
				} while (name.length() < 2 || name.length() > 30);

				System.out.println("Date of birth");
				do {
					System.out.print("Date [1..31] : ");
					try {

						date = scan.nextInt();
						scan.nextLine();
					} catch (Exception e) {
						System.out.println("Input must be a number!");
						scan.nextLine();
					}
				} while (date < 1 || date > 31);

				do {
					flag = true;
					System.out.print("Month [January..December] : ");
					month = scan.nextLine();
					if (word(month) == false) {
						System.out.println("Input must be a word!");
					} else {
						if (month.equalsIgnoreCase("January")) {
							flag = false;
						} else if (month.equalsIgnoreCase("February")) {
							flag = false;
						} else if (month.equalsIgnoreCase("March")) {
							flag = false;
						} else if (month.equalsIgnoreCase("April")) {
							flag = false;
						} else if (month.equalsIgnoreCase("May")) {
							flag = false;
						} else if (month.equalsIgnoreCase("June")) {
							flag = false;
						} else if (month.equalsIgnoreCase("July")) {
							flag = false;
						} else if (month.equalsIgnoreCase("August")) {
							flag = false;
						} else if (month.equalsIgnoreCase("September")) {
							flag = false;
						} else if (month.equalsIgnoreCase("October")) {
							flag = false;
						} else if (month.equalsIgnoreCase("November")) {
							flag = false;
						} else if (month.equalsIgnoreCase("December")) {
							flag = false;
						}
					}
				} while (flag == true || word(month) == false);

				do {
					System.out.print("Year [minimum number of 4 digits] : ");
					year = scan.nextInt();
					scan.nextLine();
					tahun = "" + year;
				} while (tahun.length() < 4 || tahun.length() > 4);
				birth = date + " " + month + " " + year;

				do {
					System.out.print("Student ID [8..10] : ");
					id = scan.nextLine();
				} while (id.length() < 8 || id.length() > 10);

				System.out.print("What Courses do you take in current semester : ");
				course = scan.nextLine();

				System.out.print("Input Courses you want to take : ");
				course1 = scan.nextLine();

				System.out.print("Grade [A..F] : ");
				grade = scan.nextLine();

				do {
					flag = true;
					System.out.print("Online or Offline : ");
					option = scan.nextLine();
					if (option.equalsIgnoreCase("Online")) {
						flag = false;
						System.out.print("Your Location : ");
						location = scan.nextLine();
						stud.set(temp, new Online(name, birth, id, course, course1, grade, location));
						System.out.println("Update Success...");
					} else if (option.equalsIgnoreCase("Offline")) {
						flag = false;
						System.out.print("Absence : ");
						try {
							absen = scan.nextInt();
							scan.nextLine();
						} catch (Exception e) {
							// TODO: handle exception
							System.out.println("input must be a number!");
						}
						stud.set(temp, new Offline(name, birth, id, course, course1, grade, absen));
						System.out.println("Update Success...");
					}
				} while (flag == true);

				try {
					FileWriter fw = new FileWriter("StudentData.txt");
					BufferedWriter bw = new BufferedWriter(fw);

					for (int i = 0; i < stud.size(); i++) {
						if (stud.get(i) instanceof Online) {
							bw.write(stud.get(i).getId() + "#" + stud.get(i).getName() + "#" + stud.get(i).getBirth()
									+ "#" + stud.get(i).getCourse() + "#" + stud.get(i).getCourse1() + "#"
									+ stud.get(i).getGrade() + "#Online#" + ((Online) stud.get(i)).getLocation());
							bw.newLine();
						} else if (stud.get(i) instanceof Offline) {
							bw.write(stud.get(i).getId() + "#" + stud.get(i).getName() + "#" + stud.get(i).getBirth()
									+ "#" + stud.get(i).getCourse() + "#" + stud.get(i).getCourse1() + "#"
									+ stud.get(i).getGrade() + "#Offline#" + ((Offline) stud.get(i)).getAbsence());
							bw.newLine();
						}
					}

					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("ID not found!");
			}

		}
	}

	public void bacaFile() {
		String name = "";
		String birth = "";
		String id = "";
		String course = "";
		String course1 = "";
		String grade = "";
		String check = "";
		String check1 = "";

		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("StudentData.txt"));
			String str = null;
			int i = 1;
			if (reader.ready()) {
				while ((str = reader.readLine()) != null) {
					String[] splited = str.split("#");
					id = splited[0];
					name = splited[1];
					birth = splited[2];
					course = splited[3];
					course1 = splited[4];
					grade = splited[5];
					check = splited[6];
					check1 = splited[7];
					if (check.equals("Online")) {
						stud.add(new Online(name, birth, id, course, course1, grade, check1));
					} else if (check.equals("Offline")) {
						int absence = Integer.parseInt(check1);
						stud.add(new Offline(name, birth, id, course, course1, grade, absence));
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void viewAll() {

		String name = "";
		String birth = "";
		String id = "";
		String course = "";
		String course1 = "";
		String grade = "";
		String check = "";
		String check1 = "";

		BufferedReader reader;
		BufferedReader baca;
		try {
			reader = new BufferedReader(new FileReader("StudentData.txt"));
			baca = new BufferedReader(new FileReader("StudentData.txt"));
			String str = null;
			String str1 = null;
			int hitungOn = 0;
			int hitungOff = 0;
			int i = 1;
			if (reader.ready()) {

				for (int j = 0; j < stud.size(); j++) {
					if (stud.get(j) instanceof Online) {
						hitungOn++;
					} else if (stud.get(j) instanceof Offline) {
						hitungOff++;
					}
				}

				if (hitungOn == 0 && hitungOff == 0) {
					System.out.println("There is no data!");
				} else if (hitungOn != 0 && hitungOff == 0) {

					System.out.println("Online Student");
					System.out.println("==============");
					System.out.println(
							"+----+------------+-----------------+-------------------+------------------+-----------------+-------------------+-------+");
					System.out.println(
							"| No |     ID     |   Name          | Birth             | Location         | Course Current  | Course Taken      | Grade |");
					System.out.println(
							"+----+------------+-----------------+-------------------+------------------+-----------------+-------------------+-------+");
					while ((str = reader.readLine()) != null) {
						String[] splited = str.split("#");
						id = splited[0];
						name = splited[1];
						birth = splited[2];
						course = splited[3];
						course1 = splited[4];
						grade = splited[5];
						check = splited[6];
						check1 = splited[7];
						if (check.equals("Online")) {
							System.out.printf("| %2d | %10s | %-15s | %-17s | %-16s | %-15s | %-17s | %-5s |\n", i++,
									id, name, birth, check1, course, course1, grade);
						}
					}
					System.out.println(
							"+----+------------+-----------------+-------------------+------------------+-----------------+-------------------+-------+\n");
					System.out.println("Offline Student");
					System.out.println("===============");
					System.out.println("There is no data student!");

				} else if (hitungOn == 0 && hitungOff != 0) {

					System.out.println("Online Student");
					System.out.println("==============");
					System.out.println("There is no data student!\n\n\n");
					System.out.println("Offline Student");
					System.out.println("===============");
					System.out.println(
							"+----+------------+--------------------+-------------------+-----------------+-------------------+-------+---------+");
					System.out.println(
							"| No |     ID     |   Name             | Birth             | Course Current  | Course Taken      | Grade | Absence |");
					System.out.println(
							"+----+------------+--------------------+-------------------+-----------------+-------------------+-------+---------+");
					while ((str1 = baca.readLine()) != null) {
						String[] splited = str1.split("#");
						id = splited[0];
						name = splited[1];
						birth = splited[2];
						course = splited[3];
						course1 = splited[4];
						grade = splited[5];
						check = splited[6];
						check1 = splited[7];
						if (check.equals("Offline")) {
							int absence = Integer.parseInt(check1);
							System.out.printf("| %2d | %10s | %-18s | %-17s | %-15s | %-17s | %-5s | %-7d |\n", i++, id,
									name, birth, course, course1, grade, absence);
						}
					}
					System.out.println(
							"+----+------------+--------------------+-------------------+-----------------+-------------------+-------+---------+");
				} else if (hitungOn != 0 && hitungOff != 0) {

					System.out.println("Online Student");
					System.out.println("==============");
					System.out.println(
							"+----+------------+-----------------+-------------------+------------------+-----------------+-------------------+-------+");
					System.out.println(
							"| No |     ID     |   Name          | Birth             | Location         | Course Current  | Course Taken      | Grade |");
					System.out.println(
							"+----+------------+-----------------+-------------------+------------------+-----------------+-------------------+-------+");
					while ((str = reader.readLine()) != null) {
						String[] splited = str.split("#");
						id = splited[0];
						name = splited[1];
						birth = splited[2];
						course = splited[3];
						course1 = splited[4];
						grade = splited[5];
						check = splited[6];
						check1 = splited[7];
						if (check.equals("Online")) {
							System.out.printf("| %2d | %10s | %-15s | %-17s | %-16s | %-15s | %-17s | %-5s |\n", i++,
									id, name, birth, check1, course, course1, grade);
						}
					}
					System.out.println(
							"+----+------------+-----------------+-------------------+------------------+-----------------+-------------------+-------+\n");
					System.out.println("Offline Student");
					System.out.println("===============");
					System.out.println(
							"+----+------------+--------------------+-------------------+-----------------+-------------------+-------+---------+");
					System.out.println(
							"| No |     ID     |   Name             | Birth             | Course Current  | Course Taken      | Grade | Absence |");
					System.out.println(
							"+----+------------+--------------------+-------------------+-----------------+-------------------+-------+---------+");
					while ((str1 = baca.readLine()) != null) {
						String[] split = str1.split("#");
						id = split[0];
						name = split[1];
						birth = split[2];
						course = split[3];
						course1 = split[4];
						grade = split[5];
						check = split[6];
						check1 = split[7];
						if (check.equals("Offline")) {
							int absence = Integer.parseInt(check1);
							System.out.printf("| %2d | %10s | %-18s | %-17s | %-15s | %-17s | %-5s | %-7d |\n", i++, id,
									name, birth, course, course1, grade, absence);
						}
					}
					System.out.println(
							"+----+------------+--------------------+-------------------+-----------------+-------------------+-------+---------+");
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Main() {
		int choose = 0;
		bacaFile();
		do {
			enter();
			menu();
			choose = scan.nextInt();
			scan.nextLine();
			switch (choose) {
			case 1:
				enter();
				case1();
				scan.nextLine();
				break;
			case 2:
				enter();
				case2();
				scan.nextLine();
				break;
			case 3:
				enter();
				case3();
				scan.nextLine();
				break;
			case 4:
				enter();
				case4();
				scan.nextLine();
				break;
			}
		} while (choose != 5);
	}

	public static void main(String[] args) {
		new Main();
	}

}
