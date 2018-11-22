import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Game {

	private String playerName;
	private int[] gameNums;
	private int count;
	private Calendar startTime;
	private Calendar endTime;
	private int[][] lastChoices;
	private int[] lastChoice;

	public Game() {

		gameNums = new int[] { -1, -1, -1, -1 };
		count = 0;
		lastChoices = new int[21][6];
		lastChoice = new int[6];

	}

	// !
	public void setStartTime() {

		startTime = Calendar.getInstance();

	}

	public void setEndTime(Calendar end) {

		this.endTime = end;

	}

	public int getAttempts() {

		return count;

	}

	public String getPlayerName() {

		return this.playerName;

	}

	public String getGameNums() {

		return "" + gameNums[0] + gameNums[1] + gameNums[2] + gameNums[3];
	}
	
	public Calendar getStartTime() {
		
		return this.startTime;
	}
	
	public Calendar getEndTime() {
		
		return this.endTime;	
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

		return "Minutes:" + seconds / 60 + " Seconds:" + seconds % 60;

	}

	public void printLastChoice() {

		String pr = "";

		for (int i = 0; i < count; i++) {

			for (int j = 0; j < 4; j++) {

				pr = pr.concat("" + lastChoices[i][j]);

			}
			pr = pr.concat(" Cows:" + lastChoices[i][5] + " Bulls:" + lastChoices[i][4]);
			System.out.println(pr);
			pr = "";
		}

	}

	public void start() {

		Scanner sc = new Scanner(System.in);
		this.setStartTime();
		this.count = 0;
		this.lastChoices = new int[21][6];
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

	public void select() {

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

					lastChoice[i] = Integer.valueOf(split[i]);

				}
			} else {
				validInput = false;
			}

		}
		count++;

	}

	public int checkBulls() {
		// !!!
		int bulls = 0;

		for (int i = 0; i < 4; i++) {
			if (lastChoice[i] == gameNums[i]) {
				bulls++;
			}
		}
		lastChoice[4] = bulls; // !!!
		return bulls;

	}

	public int checkCows() {
		// !!!
		int cows = 0;
		for (int i = 0; i < 4; i++) {

			for (int j = 0; j < 4; j++) {

				if (lastChoice[i] == gameNums[j]) {
					cows++;
				}

			}

		}
		cows -= lastChoice[4];
		lastChoice[5] = cows; // !!!
		int a = 0;
		for (int b : lastChoice) {

			lastChoices[count - 1][a] = b;
			a++;
		}
		return cows;

	}

	public String choicesToString() {

		String str = "";
		for (int i = 0; i < count; i++) {
			if (i == (count - 1)) {
				str += "" + this.lastChoices[i][0] + this.lastChoices[i][1] + this.lastChoices[i][2]
						+ this.lastChoices[i][3];

			} else {
				str += "" + this.lastChoices[i][0] + this.lastChoices[i][1] + this.lastChoices[i][2]
						+ this.lastChoices[i][3] + "*";
				// +this.lastChoices[i][4]+this.lastChoices[i][5];
			}
		}
		return str;

	}

	public static boolean validate(String in) {

		Pattern p = Pattern.compile("^[0-9]{4}$");
		Matcher matcher = p.matcher(in);
		return matcher.matches();

	}

}
