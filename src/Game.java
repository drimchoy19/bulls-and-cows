import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Game {

	private String playerName;
	private int[] gameNums = new int[] { -1, -1, -1, -1 };
	int count = 0;
	Calendar startTime = Calendar.getInstance();
	Calendar endTime;
	int[][] lastChoices = new int[21][6];
	int[] lastChoice = new int[6];

	public String getPlayerName() {

		return this.playerName;

	}

	public String getGameNums() {

		return "" + gameNums[0] + gameNums[1] + gameNums[2] + gameNums[3];
	}

	public String initPlayer(Scanner inSc) {

		System.out.println("Enter Player Name");
		String name = inSc.nextLine();
		this.playerName = name;
		return name;

	}

	public String calcTime() {

		int startMinutes = this.startTime.get(Calendar.MINUTE);
		int startSeconds = this.startTime.get(Calendar.SECOND);
		int endMinutes = this.endTime.get(Calendar.MINUTE);
		int endSeconds = this.endTime.get(Calendar.SECOND);

		int seconds = (endMinutes - startMinutes) * 60 + (endSeconds - startSeconds);
		int minutes = seconds / 60;
		return "Minutes:" + minutes + " Seconds:" + seconds;

	}

	public void printLast() {

		String pr = "";

		for (int i = 0; i < count; i++) {

			for (int j = 0; j < 4; j++) {

				pr = pr.concat("" + lastChoices[i][j]);

			}
			pr = pr.concat(" Cows:" + lastChoices[i][4] + " Bulls:" + lastChoices[i][5]);
			System.out.println(pr);
			pr = "";
		}

	}

	public void start() {

		Scanner sc = new Scanner(System.in);

		System.out.println("Lets play " + this.playerName + " !");

		Random rnd = new Random();

		for (int i = 0; i < 4; i++) {

			int select = rnd.nextInt(9);
			boolean check = true;

			while (check) {
				check = false;
				// System.out.println(i+"num"+select);
				for (int j = 0; j <= i; j++) {
					if (gameNums[j] == select) {
						select = rnd.nextInt(9);
						check = true;
					}
				}

			}
			gameNums[i] = select;
		}

	}

	public int[] select() {

		// bez povtarqshti i ot 0-9
		Scanner sc = new Scanner(System.in);
		String in;
		boolean validInput = false;
		String[] split = null;
		while (!validInput) {
			System.out.println("Choose 4 numbers");
			in = sc.nextLine();
			validInput = Game.validate(in);
			split = in.split("");
			if (split.length < 4) {
				validInput = false;
			}
			if (split.length == 4) {
				for (int i = 0; i < 4; i++) {
					for (int j = i + 1; j < 4; j++) {

						if (split[i].equals(split[j])) {
							System.out.println("Invalid numbers");
							validInput = false;
						}

					}
				}

				for (int i = 0; i < 4; i++) {

					lastChoices[count][i] = lastChoice[i] = Integer.valueOf(split[i]);

				}
			} else {
				validInput = false;
			}

		}
		return lastChoice;

	}

	public int checkBulls(int[] nums) {

		int bulls = 0;

		for (int i = 0; i < 4; i++) {
			if (nums[i] == gameNums[i]) {
				bulls++;
			}
		}
		lastChoice[4] = bulls; // !!!
		return bulls;

	}

	public int checkCows(int[] nums) {

		int cows = 0;
		for (int i = 0; i < 4; i++) {

			for (int j = 0; j < 4; j++) {

				if (nums[i] == gameNums[j]) {
					cows++;
				}

			}

		}
		lastChoice[5] = cows - lastChoice[4]; // !!!
		return cows;

	}

	public static boolean validate(String in) {

		Pattern p = Pattern.compile("^[0-9]{4}$");
		Matcher matcher = p.matcher(in);
		return matcher.matches();

	}

}
