import java.util.Calendar;
import java.util.Scanner;

public class Testing {

	public static void main(String[] args) {
		
		Game g = new Game();
		FileOperator fo = new FileOperator();
		fo.createFile();
		Scanner sc = new Scanner(System.in);
		g.initPlayer(sc);
		g.start();
		System.out.println(g.getGameNums());
		boolean isEnd = false;
		while (!isEnd) {
			if (g.getAttempts() == 20) {
				System.out.println("Game end reached max attempts : 21 !");
				isEnd = true;
			}

			g.select();
			
			int bulls = g.checkBulls(); // 1!
			int cows = g.checkCows(); // 2!
			

			g.printLastChoice();

			if (bulls == 4) {
				g.setEndTime(Calendar.getInstance());
				System.out.println("Congrats " + g.getPlayerName() + " you have " + bulls + " bulls!");
				System.out.println("Time :"+g.calcTime());
				System.out.println("Attempts :" + g.getAttempts());
				
				System.out.println("To play again enter 1 else anykey !");
				int a = sc.nextInt();
				isEnd = !(a==1);
				if(!isEnd) {
					
					g.start();
					System.out.println(g.getGameNums());
					
				}else {
					fo.writeFile(g);
					System.out.println("Good game/s!");
					
				}
			} else {
				g.setEndTime(Calendar.getInstance());

				System.out.println("You have " + (cows) + " cows!");
				System.out.println("You have " + bulls + " bulls!");
				System.out.println("Elapsed time :" + g.calcTime());
				
			}

		}

	}

}
