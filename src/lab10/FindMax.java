package lab10;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * COMP 3021
 * 
This is a class that prints the maximum value of a given array of 90 elements

This is a single threaded version.

Create a multi-thread version with 3 threads:

one thread finds the max among the cells [0,29] 
another thread the max among the cells [30,59] 
another thread the max among the cells [60,89]

Compare the results of the three threads and print at console the max value.

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

	static int max=0;
	
	public static void main(String[] args) {
		
		new FindMax().printMax();
	}

	public void printMax() {
		// this is a single threaded version
//		int max = findMax(0, array.length - 1);
		ExecutorService executor = Executors.newFixedThreadPool(3);
		
		executor.execute(new PrintMaxTask(0,29));
		executor.execute(new PrintMaxTask(30,59));
		executor.execute(new PrintMaxTask(60,89));
		
		executor.shutdown();
		
		while(!executor.isTerminated()){
			
		}
		System.out.println("the max value is " + max);
	}
	
	class PrintMaxTask implements Runnable{
		int start;
		int end;
		
		public PrintMaxTask(int s, int e){
			start=s;
			end=e;
		}
		
		@Override
		public void run(){
		//things to do
			int temp=findMax(start,end);
//			System.out.println(temp);
			if( temp>max)
				max=temp;
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