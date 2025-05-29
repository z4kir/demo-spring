package com.example.demo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.imageio.ImageIO;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.service.CheckPrint;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	public class LinkedList<K, T> {
		K key;
		T value;

		public LinkedList(K key, T value) {
			this.key = key;
			this.value = value;
		}

		public LinkedList nextNode;
	}

	@Override
	public void run(String... args) throws Exception {
		checkPrint();
//		Component comp = new Component();
//		comp.test();
//		Integer[] arr = { 8, 3, 5, 7, 1 };
//		Integer[] sortArr=quickSort(arr, 0,arr.length - 1);
//		for (Integer integer : sortArr) {
//			System.out.print(integer+",");
//		}
//		System.out.println(arr.length);
//		smallestPositiveMissing(arr);
//		longestValidParentheses(")()())");
////		longestValidParentheses("((((()()))");
//		LinkedList<String, Integer> li=new LinkedList<String, Integer>("farhan",1) ;
//		li.nextNode=new LinkedList<String, Integer>("zakir",2) ;
	}

	private void checkPrint() {
		String name = "John Doe"; // recipient name

		PrinterJob job = PrinterJob.getPrinterJob();
		job.setPrintable(new CheckPrint(name));

//		boolean doPrint = job.printDialog(); // Show system print dialog
//		if (doPrint) {
		try {
			job.print();
		} catch (PrinterException e) {
			e.printStackTrace();
		}
//		}

	}

	public void createCheckImage() {
		String name = "John Doe"; // Name to print on check
		int width = 600;
		int height = 250;

		// Create a buffered image
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		// Get Graphics2D context
		Graphics2D g2d = image.createGraphics();

		// Set background and fill
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, width, height);

		// Draw border
		g2d.setColor(Color.BLACK);
		g2d.drawRect(10, 10, width - 20, height - 20);

		// Set font for name
		g2d.setFont(new Font("Serif", Font.BOLD, 20));
		g2d.setColor(Color.BLACK);

		// Draw name on the check
		g2d.drawString("Pay to the Order of: " + name, 40, 80);

		// Optional: Draw other parts like date, amount, signature line
		g2d.drawString("Amount: ____________", 40, 120);
		g2d.drawString("Date: ____________", 400, 40);
		g2d.drawLine(40, 180, 300, 180); // Signature line
		g2d.drawString("Signature", 40, 195);

		// Dispose graphics
		g2d.dispose();

		try {
			// Save as PNG
			ImageIO.write(image, "png", new File("check.png"));
			System.out.println("Check image generated as 'check.png'");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void smallestPositiveMissing(int[] nums) {
		int count = 1;
		while (true) {
			boolean isMatched = false;
			for (int i = 0; i < nums.length; i++) {
				if (count == nums[i]) {
					isMatched = true;
					break;
				}
			}
			if (!isMatched) {
				break;
			}
			count++;
		}
		System.out.println("count " + count);
	}

	public int longestValidParentheses(String s) {
		Stack<Integer> stack = new Stack<>();
		stack.push(-1);
		int maxLength = 0;

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				stack.push(i);
			} else {
				stack.pop();
				if (stack.isEmpty()) {
					stack.push(i);
				} else {
					maxLength = Math.max(maxLength, i - stack.peek());
				}
			}
		}
		System.out.println(maxLength);
		return maxLength;
//		int res = 0;
////		StringBuilder sb=new StringBuilder(s);
//		int starts = s.indexOf("(");
//		int ends = s.lastIndexOf(")");
//		if (starts == -1 || ends == -1) {
//			return res;
//		}
//		s = s.substring(starts, ends + 1);
//		char[] charArr = s.toCharArray();
//
//		int opens = 0;
//		int closed = 0;
//		for (int i = 0; i < charArr.length-1; i++) {
////			p-1 ((()))
//
//			if (charArr[i] == '(') {
//				if (opens >= closed && closed > 0) {
//					s = s.substring(1, s.length() - 1);
//					charArr = s.toCharArray();
//					i = 0;
//					opens = 0;
//					closed = 0;
//
//					continue;
//				}
//				if (opens == 0) {
//					starts = i;
//				}
//				opens += 1;
//				continue;
//			}
//
//			if (opens > 0 && charArr[i+1] == ')') {
////				opens-=1;
//				ends = i+1;
//				closed += 1;
//				continue;
//			}
//		}
//
//		res = ends - starts + 1;
//
//		System.out.println(res);
//		return res;
	}

	public int romanToInt(String s) {
		if (1 <= s.length() && s.length() <= 15) {
			char[] charArr = s.toCharArray();
			List<Character> characterList = new ArrayList<>();

			int result = 0;
			int count = 0;
			for (char c : charArr) {
				characterList.add(c);
				switch (c) {
				case 'I':
					result += 1;
					break;
				case 'V':
					if (count > 0 && charArr[count - 1] == 'I') {
						result += 3;
					} else {

						result += 5;
					}
					break;
				case 'X':
					if (count > 0 && charArr[count - 1] == 'I') {
						result += 8;
					} else {

						result += 10;
					}

					break;
				case 'L':
					if (count > 0 && charArr[count - 1] == 'X') {
						result += 30;
					} else {

						result += 50;
					}
					break;
				case 'C':
					if (count > 0 && charArr[count - 1] == 'X') {
						result += 80;
					} else {

						result += 100;
					}
					break;
				case 'D':
					if (count > 0 && charArr[count - 1] == 'C') {
						result += 300;
					} else {

						result += 500;
					}
					break;
				case 'M':
					if (count > 0 && charArr[count - 1] == 'C') {
						result += 800;
					} else {

						result += 1000;
					}
					break;
				default:
					return -1;
				}
				count++;
			}

			System.out.println(result);
		}
		return -1;
	}

	public Integer[] quickSort(Integer[] array, Integer low, Integer high) {
		if (low < high) {
			int mid = partition(array, low, high);
			quickSort(array, low, mid - 1); // left
			quickSort(array, mid + 1, high); // right

		}
		return array;
	}

	public Integer partition(Integer[] array, Integer low, Integer high) {
		int pivot = array[high];
		int i = low;
		for (int j = low; j < high; j++) {
			if (array[j] < pivot) {
				int temp = array[j];
				array[j] = array[i];
				array[i] = temp;
				i++;
			}
		}
		int temp = array[high];
		array[high] = array[i];
		array[i] = temp;
		return i;
	}
}
