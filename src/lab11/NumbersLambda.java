package lab11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class NumbersLambda {

	// Find numbers with certain properties in a unified form
	// The property is specified in Predicate

	public static List<Integer> findNumbers(List<Integer> list,
			Predicate<Integer> predicate) {
		List<Integer> results = new ArrayList<Integer>();
		for (int n : list) {
			if (predicate.test(n))
				results.add(n);
		}
		return results;
	}

	public static List<Integer> calculateScore(List<Integer> list,
			Function<Integer, Integer> function) {
		// TODO: Task 3

		List<Integer> results = new ArrayList<Integer>();
		for (int n : list) {
			results.add(function.apply(n));
		}
		return results;
	}

	public static Function<Integer, Integer> policy() {
		// TODO: Task 3
		return x -> Boolean.compare(isOdd().test(x), false) * 1
				+ Boolean.compare(isPrime().test(x), false) * 2
				+ Boolean.compare(isPalindrome().test(x), false) * 4;
	}

	//
	public static Predicate<Integer> isOdd() {
		// TODO: Task 2
		return x -> x % 2 != 0;
	}

	public static Predicate<Integer> isPrime() {
		// TODO: Task 2
		// return x -> x % 2 != 0
		// && IntStream.range(2, x - 1).noneMatch(
		// divisor -> x % divisor == 0);

		return x -> {
			if (x % 2 == 0)
				return false;
			// if not, then just check the odds
			for (int i = 3; i * i <= x; i += 2) {
				if (x % i == 0)
					return false;
			}
			return true;
		};
	}

	public static Predicate<Integer> isPalindrome() {
		// TODO: Task 2
		// return x -> Integer.toString(x).equals( new
		// StringBuilder(Integer.toString(x)).reverse().toString());
		return x -> {
			int origin = x;
			int reverse = 0;

			while (x > 0) {
				int r = x % 10;
				reverse = reverse * 10 + r;
				x = x / 10;
			}

			if (origin == reverse)
				return true;
			else
				return false;
		};

	}

	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(480, 514, 484, 389, 709, 935,
				328, 169, 649, 300, 685, 429, 243, 532, 308, 87, 25, 282, 91,
				415);

		System.out.println("The odd numbers are : "
				+ findNumbers(numbers, isOdd()));
		System.out.println("The prime numbers are : "
				+ findNumbers(numbers, isPrime()));
		System.out.println("The palindrome numbers are : "
				+ findNumbers(numbers, isPalindrome()));

		System.out.println("The score of the all numbers are :"
				+ calculateScore(numbers, policy()));
	}
}