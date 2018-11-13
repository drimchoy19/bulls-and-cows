import java.util.Calendar;
import java.util.Scanner;

public class Testing {

	public static void main(String[] args) {

		Game g = new Game();
		Scanner sc = new Scanner(System.in);
		g.initPlayer(sc);
		g.start();
		System.out.println(g.getGameNums());
		System.out.println(g.startTime.getTime() + " ");
		boolean isEnd = false;
		while (!isEnd) {
			if (g.count == 20) {
				System.out.println("Game end reached max attempts : 21 !");
				isEnd = true;
			}
			int[] n = g.select();

			int bulls = g.checkBulls(n); // 1!
			int cows = g.checkCows(n) - bulls; // 2!
			g.lastChoices[g.count][4] = cows;
			g.lastChoices[g.count][5] = bulls;

			g.count++;
			g.printLast();

			if (bulls == 4) {
				g.endTime = Calendar.getInstance();
				System.out.println("Congrats " + g.getPlayerName() + " you have " + bulls + " bulls!");
				System.out.println(g.calcTime());
				System.out.println("Attempts :" + g.count);
				isEnd = true;
			} else {
				g.endTime = Calendar.getInstance();
				System.out.println("You have " + (cows) + " cows!");
				System.out.println("You have " + bulls + " bulls!");
				System.out.println("Elapsed time :" + g.calcTime());
			}

		}

	}

}
