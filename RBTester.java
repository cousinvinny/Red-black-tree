/*
 * Author: Vinh Nguyen
 * Project 4
 * Solo project
 */

package cs146F22.Nguyen.project4;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import org.junit.Test;

import cs146F22.Nguyen.project4.RedBlackTree.Node;
import cs146F22.Nguyen.project4.RedBlackTree.Visitor;


public class RBTTester {
	@Test
	// Test the Red Black Tree
	public void test() {
		RedBlackTree rbt = new RedBlackTree();
		rbt.insert("D");
		rbt.insert("B");
		rbt.insert("A");
		rbt.insert("C");
		rbt.insert("F");
		rbt.insert("E");
		rbt.insert("H");
		rbt.insert("G");
		rbt.insert("I");
		rbt.insert("J");
		assertEquals("DBACFEHGIJ", makeString(rbt));
		String str = "Color: 1, Key:D Parent: \n" + "Color: 1, Key:B Parent: D\n" + "Color: 1, Key:A Parent: B\n"
				+ "Color: 1, Key:C Parent: B\n" + "Color: 1, Key:F Parent: D\n" + "Color: 1, Key:E Parent: F\n"
				+ "Color: 0, Key:H Parent: F\n" + "Color: 1, Key:G Parent: H\n" + "Color: 1, Key:I Parent: H\n"
				+ "Color: 0, Key:J Parent: I\n";
		// assertEquals(str, makeStringDetails(rbt));

		/*
		long startTime, endTime;
		File filename = new File("dictionary.txt");
		startTime = System.currentTimeMillis();
		RedBlackTree dictionary = new RedBlackTree();
		int count = 0;
		try {
			FileReader file = new FileReader(filename.getAbsolutePath()); // Open the file using FileReader Object.
			BufferedReader buff = new BufferedReader(file);
			boolean eof = false;
			while (!eof) {
				String line = buff.readLine(); // In a loop read a line using readLine method.
				System.out.println(count);
				count++;
				if (line == null)
					eof = true;
				else {
					StringTokenizer st = new StringTokenizer(line);
					while (st.hasMoreTokens()) {
						dictionary.insert(st.nextToken());
					}
				}
			}
			buff.close();
		} catch (IOException e) {
			System.out.println("Error -- " + e.toString());
		}
		endTime = System.currentTimeMillis();
		System.out.println("Time to make dictionary: " + (endTime - startTime) + "ms");
		*/
	}

	// add tester for spell checker

	public static String makeString(RedBlackTree t) {
		class MyVisitor implements RedBlackTree.Visitor {
			String result = "";

			public void visit(RedBlackTree.Node n) {
				result = result + n.key;
			}
		}
		;
		MyVisitor v = new MyVisitor();
		t.preOrderVisit(v);
		return v.result;
	}

	public static String makeStringDetails(RedBlackTree t) {
		{
			class MyVisitor implements RedBlackTree.Visitor {
				String result = "";

				public void visit(RedBlackTree.Node n) {
					if (!(n.key).equals(""))
						result = result + "Color: " + n.color + ", Key:" + n.key + " Parent: " + n.parent.key + "\n";

				}
			}
			;
			MyVisitor v = new MyVisitor();
			t.preOrderVisit(v);
			return v.result;
		}
	}

}
