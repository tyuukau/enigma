import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;
import java.util.stream.Collectors;

import model.AbstractScheduler;
import model.CLOOKScheduler;
import model.CSCANScheduler;
import model.FCFSScheduler;
import model.LIFOScheduler;
import model.LOOKScheduler;
import model.SCANScheduler;
import model.SSTFScheduler;

public class ConsoleDriver {

	private final static int diskSize = 200;
	private static Scanner scanner = new Scanner(System.in);

	private FCFSScheduler fcfsScheduler;
	private LIFOScheduler lifoScheduler;
	private SSTFScheduler sstfScheduler;
	private SCANScheduler scanSchedulerLeft;
	private SCANScheduler scanSchedulerRight;
	private LOOKScheduler lookSchedulerLeft;
	private LOOKScheduler lookSchedulerRight;
	private CSCANScheduler cscanSchedulerLeft;
	private CSCANScheduler cscanSchedulerRight;
	private CLOOKScheduler clookSchedulerLeft;
	private CLOOKScheduler clookSchedulerRight;

	public static void main(String[] args) throws Exception {
		System.out.println("Disk Accessing Scheduler: Console\n"
						 + "---------------------------------\n");
		ConsoleDriver mainInstance = getMain();
		mainMenu(mainInstance);				 
	}

	private static void mainMenu(ConsoleDriver mainInstance) {
		System.out.println("Choose one of the following options:\n"
						 + "\t1. FCFS \n"
						 + "\t2. LIFO \n"
						 + "\t3. SSTF \n"
						 + "\t4. SCAN \n"
						 + "\t5. CSCAN\n"
						 + "\t6. LOOK \n"
						 + "\t7. CLOOK\n"
						 + "\t8. Compare algorithms\n"
						 + "\t0. Exit\n");
		int choice = getChoice(8);

		switch (choice) {
			case 1:
				mainInstance.fcfsScheduler.printResult();
				break;
			case 2:
				mainInstance.lifoScheduler.printResult();
				break;
			case 3:
				mainInstance.sstfScheduler.printResult();
				break;
			case 4:
				mainInstance.scanSchedulerLeft.printResult();
				mainInstance.scanSchedulerRight.printResult();
				break;
			case 5:
				mainInstance.cscanSchedulerLeft.printResult();
				mainInstance.cscanSchedulerRight.printResult();
				break;
			case 6:
				mainInstance.lookSchedulerLeft.printResult();
				mainInstance.lookSchedulerRight.printResult();
				break;
			case 7:
				mainInstance.clookSchedulerLeft.printResult();
				mainInstance.clookSchedulerRight.printResult();
				break;
			case 8:
				compareMenu(mainInstance);
				break;
			case 0:
				break;
		}
	};

	private static void compareMenu(ConsoleDriver mainInstance) {
		System.out.println("Choose one of the following options:\n"
						 + "\t 1. FCFS \n"
						 + "\t 2. LIFO \n"
						 + "\t 3. SSTF \n"
						 + "\t 4. SCAN (Left) \n"
						 + "\t 5. SCAN (Right) \n"
						 + "\t 6. CSCAN (Left)\n"
						 + "\t 7. CSCAN (Right)\n"
						 + "\t 8. LOOK (Left)\n"
						 + "\t 9. LOOK (Right)\n"
						 + "\t10. CLOOK (Left)\n"
						 + "\t11. CLOOK (Right)\n");
		
		System.out.println("Input algorithms that you want to compare:");
		int[] choices = getAlgorithms();

		AbstractScheduler[] algorithms = {mainInstance.fcfsScheduler, mainInstance.lifoScheduler, mainInstance.sstfScheduler, 
			mainInstance.scanSchedulerLeft,  mainInstance.scanSchedulerRight,
			mainInstance.cscanSchedulerLeft, mainInstance.cscanSchedulerRight,
			mainInstance.lookSchedulerLeft,  mainInstance.lookSchedulerRight,
			mainInstance.clookSchedulerLeft, mainInstance.clookSchedulerRight};
		System.out.println("\n   Algorithms | Seek count | Sequence");
		for (int i = 0; i < choices.length; i++) {
			System.out.printf("%13.13s | %10d | %s\n", 
							  algorithms[choices[i] - 1].getName(), 
							  algorithms[choices[i] - 1].getTotalSeekCount(),
							  algorithms[choices[i] - 1].getSchedule()
							  );
		}
		
		System.out.println("\n"
		                 + "Compare other algorithms or go back?\n"
						 + "1. Compare other algorithms\n"
						 + "0. Go back\n");
		int choice = getChoice(1);

		switch (choice) {
			case 1:
				compareMenu(mainInstance);
				break;
			case 0:
				mainMenu(mainInstance);
				break;
		}
	};

	private static ConsoleDriver getMain() {
		// Input number of requests
		System.out.println("Input waiting track:");
		int[] arr = getRequests();

		// Input head value
		System.out.println("Input head:");
		int head = scanner.nextInt();

		System.out.println("\nWaiting track: " + Arrays.toString(arr));
		System.out.println("Head: " + head + "\n");

		return new ConsoleDriver(arr, head);
	}

	private static int[] getAlgorithms() {
		int[] numbers = scanInt();
		while (numbers.length < 2) {
			System.out.println("Please input a valid array of minimum two integers.");
			numbers = scanInt();

			Set<Integer> numbersList = new HashSet<Integer>();
			for (int i = 0; i < numbers.length; i++) {
				if (0 < numbers[i] && numbers[i] < 12) {
					numbersList.add(numbers[i]);
				}
			}
			numbers = numbersList.stream().mapToInt(Number::intValue).toArray();
		}
		return numbers;
	}

	private static int[] getRequests() {
		int[] numbers = scanInt();
		while (numbers.length < 1) {
			System.out.println("Please input a valid array havign elements larger than 0 but smaller than " + diskSize);
			numbers = scanInt();

			Set<Integer> numbersList = new HashSet<Integer>();
			for (int i = 0; i < numbers.length; i++) {
				if (-1 < numbers[i] && numbers[i] < diskSize) {
					numbersList.add(numbers[i]);
				}
			}
			numbers = numbersList.stream().mapToInt(Number::intValue).toArray();
		}
		return numbers;
	}

	private static int getChoice(int max) {
		Integer input = scanner.nextInt();
		scanner.nextLine();
		while (0 > input || input > max) {
			System.out.println("Please choose a valid number.");
			input = scanner.nextInt();
			scanner.nextLine();
		}
		return input;
	}

	private static int[] scanInt() {
		String line = scanner.nextLine();
		String[] numberStrs = line.split(" ");
		int[] numbers = new int[numberStrs.length];

		int index = 0;
		for (int i = 0; i < numberStrs.length; i++) {
			try {
				numbers[index] = Integer.parseInt(numberStrs[i]);
				index++;
			} catch (NumberFormatException nfe) {
				System.out.println(numberStrs[i] + " is not a valid number, will be discarded.");
			}
		}

		// Now there will be a number of 'invalid' elements at the end which will need to be trimmed
		numbers = Arrays.copyOf(numbers, index);
		Set<Integer> set = Arrays.stream(numbers).boxed().collect(Collectors.toSet());
		numbers = set.stream().mapToInt(Number::intValue).toArray();
		return numbers;
	}

	public ConsoleDriver(int[] arr, int head) {
		this.fcfsScheduler = new FCFSScheduler(arr, head);
		this.lifoScheduler = new LIFOScheduler(arr, head);
		this.sstfScheduler = new SSTFScheduler(arr, head);
		this.scanSchedulerLeft = new SCANScheduler(arr, head, false);
		this.scanSchedulerRight = new SCANScheduler(arr, head, true);
		this.lookSchedulerLeft = new LOOKScheduler(arr, head, false);
		this.lookSchedulerRight = new LOOKScheduler(arr, head, true);
		this.cscanSchedulerLeft = new CSCANScheduler(arr, head, false);
		this.cscanSchedulerRight = new CSCANScheduler(arr, head, true);
		this.clookSchedulerLeft = new CLOOKScheduler(arr, head, false);
		this.clookSchedulerRight = new CLOOKScheduler(arr, head, true);
	}

}