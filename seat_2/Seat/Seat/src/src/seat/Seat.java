package src.seat;

import java.util.Scanner;
import java.util.Random;

public class Seat {

	public static void main(String[] args) {
		// 40
		int all_student[] = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		int fixed_student = 0;
		int fixed_seat[] = new int[] {  0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		int check = 0;
		int check2 = 0;
		e l = new e();
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		Random generator = new Random();

		System.out.print("학생수를 입력해 주세요. : ");
		int student_num = reader.nextInt();

		while (true) {
			System.out.print("고정좌석의 번호를 입력해 주십시오. (없다면 0을 눌러주세요) : ");
			check = reader.nextInt();
			if (check != 0) {
				System.out.print("고정 좌석의 학생 번호를 입력하십시오. : ");
				fixed_student = reader.nextInt();// 1
				fixed_seat[check - 1] = fixed_student;
				check2++;
			} else
				break;
		}
		// 정렬
		for (int i = 0; i < student_num; i++) {
			all_student[i] = generator.nextInt(student_num) + 1;
			for (int j = 0; j < i; j++) {
				if (all_student[i] == all_student[j]) {
					i--;
					break;
				}
			}
		}
		change c = new change();
		// 고정 좌표
		if (check2 != 0) {
			while (check2 != 0) {
				int f_where = 0;

				int where = 0;

				for (int i = 0; i < 40; ++i) {
					if (fixed_seat[i] != 0) {
						f_where = i;
						fixed_student = fixed_seat[i];
						break;
					}
				}

				for (int i = 0; i < student_num; ++i) {
					if (all_student[i] == fixed_student) {
						where = i;
					}

				}

				all_student[where] = c.swap(all_student[f_where]);
				all_student[f_where] = c.swap(fixed_student);
				fixed_seat[f_where] = 0;
				check2--;
			}
		}
		System.out.print("=====================교탁=====================");
		l.c();
		for (int i = 0; i < student_num; ++i) {
			if (i % 6 == 0)
				l.c();
			System.out.print((i + 1) + ". " + all_student[i] + "\t");
		}
		l.c();

		// 바꾸기
		int changed = 0, changer = 0, ysc = 0, t=0;
		while (true) {
			System.out.print("바꿀 자리가 있습니까? (있다면 1, 없다면 0) : ");
			int yesno = reader.nextInt();
			if (yesno == 1) {
				ysc++;
				System.out.print("바꿀 자리를 입력해 주세요. : ");
				changed = reader.nextInt();

				System.out.print("바꿀 자리를 입력해 주세요. : ");
				changer = reader.nextInt();

				t=all_student[changed-1];
				all_student[changed - 1] = c.swap(all_student[changer - 1]);
				all_student[changer - 1] = c.swap(t);

			} else
				break;
		}

		if (ysc != 0) {
			System.out.print("=====================교탁=====================");
			l.c();
			for (int i = 0; i < student_num; ++i) {
				if (i % 6 == 0)
					l.c();
				System.out.print((i + 1) + ". " + all_student[i] + "\t");
			}
			l.c();
		}

	}

}

class change implements fix {

	public int swap(int a) {
		return a;
	}
}

class e extends clear {

	void c() {
		System.out.println("");
	}

}

abstract class clear {
	abstract void c();
}

interface fix {
	int swap(int a);
}
