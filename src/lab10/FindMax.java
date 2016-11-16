package lab10;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * COMP 3021
 * 
 * This is a class that prints the maximum value of a given array of 90 elements
 * 
 * This is a single threaded version.
 * 
 * Create a multi-thread version with 3 threads:
 * 
 * one thread finds the max among the cells [0,29] another thread the max among
 * the cells [30,59] another thread the max among the cells [60,89]
 * 
 * Compare the results of the three threads and print at console the max value.
 * 
 * 
 * @author valerio
 *
 */
public class FindMax {
	// this is an array of 90 elements
	// the max value of this array is 9999
	static int[] array = { 1, 34, 5, 6, 343, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2, 3, 4543,
			234, 3, 454, 1, 2, 3, 1, 9999, 34, 5, 6, 343, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2,
			3, 4543, 234, 3, 454, 1, 2, 3, 1, 34, 5, 6, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2,
			3, 4543, 234, 3, 454, 1, 2, 3 };

	static int max = 0;

	public static void main(String[] args) {

		new FindMax().printMax();
	}

	public void printMax() {
		// this is a single threaded version
		// int max = findMax(0, array.length - 1);
		ExecutorService executor = Executors.newFixedThreadPool(3);

		
		 PrintMaxTask task1 = new PrintMaxTask(0, 29);
		PrintMaxTask task2 = new PrintMaxTask(30, 59);
		PrintMaxTask task3 = new PrintMaxTask(60, 89);
		
		executor.execute(task1);
		executor.execute(task2);
		executor.execute(task3);

		executor.shutdown();

		while (!executor.isTerminated()) {

		}
		
		max=task1.getOutput();
		int temp2=task2.getOutput();
		int temp3=task3.getOutput();
		if (temp2>max){
			max=temp2;
		}
		if(temp3>max){
			max=temp3;
		}
		
		

		// PrintMaxTask task1 = new PrintMaxTask(0, 29);
		// PrintMaxTask task2 = new PrintMaxTask(30, 59);
		// PrintMaxTask task3 = new PrintMaxTask(60, 89);
		//
		// Thread t1 = new Thread(task1);
		// Thread t2 = new Thread(task2);
		// Thread t3 = new Thread(task3);
		//
		// t1.start();
		// t2.start();
		// t3.start();
		//
		// try {
		// t1.join(); // wait until t1 is done
		//
		// } catch (InterruptedException ie) {
		// ie.printStackTrace();
		// }
		//
		// try {
		// t2.join(); // wait until t1 is done
		//
		// } catch (InterruptedException ie) {
		// ie.printStackTrace();
		// }
		//
		// try {
		// t3.join(); // wait until t1 is done
		//
		// } catch (InterruptedException ie) {
		// ie.printStackTrace();
		// }
		//
		// int temp = task1.getOutput();
		// int temp2 = task2.getOutput();
		// int temp3 = task3.getOutput();
		//
		// max = temp;
		// if (temp2 > max) {
		// max = temp2;
		// }
		// if (temp3 > max) {
		// max = temp3;
		// }

		System.out.println("the max value is " + max);
	}

	class PrintMaxTask implements Runnable {
		int start;
		int end;
		int output;

		public PrintMaxTask(int s, int e) {
			start = s;
			end = e;
		}

		 public int getOutput() {
		 return output;
		 }

		@Override
		public void run() {
			// things to do
			output = findMax(start, end);
			// System.out.println(temp);
		}
	}

	/**
	 * returns the max value in the array within a give range [begin,range]
	 * 
	 * @param begin
	 * @param end
	 * @return
	 */
	private int findMax(int begin, int end) {
		// you should NOT change this function
		int max = array[begin];
		for (int i = begin + 1; i <= end; i++) {
			if (array[i] > max) {
				max = array[i];
			}
		}
		return max;
	}
}