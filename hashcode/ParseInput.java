package hashcode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.chrono.MinguoChronology;

public class ParseInput {

	static char[][] arr;
	static int minIngredients;
	static int maxCells;
	
	public static void main(String[] args) {
		String pathDir = "C:/Users/Iris/Desktop/hashcode/";
		String fileNameA = "a_example.in";
		String fileNameB = "b_small.in";
		String fileNameC = "c_medium.in";
		String fileNameD = "d_big.in";
		File file = new File(pathDir + fileNameA);
		parseFile(file);
		arrPrint(arr);
	}
	
	
	public static void parseFile(File file) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String s = reader.readLine();
			String[] ss = s.split(" ");
			
			int rows = Integer.parseInt(ss[0]);
			int cols = Integer.parseInt(ss[1]);
			minIngredients = Integer.parseInt(ss[2]);
			maxCells = Integer.parseInt(ss[3]);
			arr = new char[rows][cols];
			
			for (int i = 0; i < rows; i++) {
				s = reader.readLine();
				arr[i] = s.toCharArray();
				}
			
			reader.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public static void arrPrint(char[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
	}
	
	public ArrayList<ArrayList<Integer>> optimize(char[][] arr, int minimumIng, int max){


		ArrayList<Point> mashroms = new ArrayList<>();
		ArrayList<Point> tomato = new ArrayList<>();

		for (int r=0; r<arr.length;r++){
		    for(int c=0;c<arr[0].length; c++){
			if (arr[r][c] == 'M'){
			    mashroms.add(new Point(r,c));
			}else {
			    tomato.add(new Point(r,c));
			}
		    }
		}
		char mainIng;
		if(mashroms.size() < tomato.size()){
		    mainIng = 'M';
		}else {
		    mainIng= 'T';
		}
		boolean[][] hasTaken;

    	}

	
	
}
