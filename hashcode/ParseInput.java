package hashcode;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.chrono.MinguoChronology;
import java.util.ArrayList;
import java.util.List;

public class ParseInput {

	static int N;
	static SpecialImage[] allPhotos;
	
	public static void main(String[] args) {
		String pathDir = "C:/Users/Iris/Desktop/hashcode/";
		String fileNameA = "a_example.txt";
		String fileNameB = "b_lovely_landscapes.txt";
		String fileNameC = "c_memorable_moments.txt";
		String fileNameD = "d_pet_pictures.txt";
		String fileNameE = "e_shiny_selfies.txt";
		File file = new File(pathDir + fileNameB);
		parseFile(file);
		printImages();
	}


	public static void parseFile(File file) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			N = Integer.parseInt(reader.readLine());
			allPhotos = new SpecialImage[N];
			
			for (int i = 0; i < N; i++) {
				String s = reader.readLine();
				String[] ss = s.split(" ");
				List<String> tags = new ArrayList<>();
				
				allPhotos[i] = new SpecialImage(ss[0].charAt(0), tags, i);
				int numOfTags = Integer.parseInt(ss[1]);
				for (int j = 2; j < numOfTags+2; j++) {
					tags.add(ss[j]);
				}
				
			}

			reader.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public static void createOutput(int[][] solution) {
		String pathDir = "C:/Users/Iris/Desktop/hashcode/";
		String fileName = "solution";
		File file = new File(pathDir + fileName);
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			
			writer.write(solution.length);
			for (int i = 0; i < solution.length; i++) {
				for (int j = 0; j < solution[1].length; j++) {
					writer.write(solution[i][j]);
				}
				writer.newLine();
			}
			writer.close();
		} catch (IOException e) {
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
	
	public static void printImages() {
		for (int i = 0; i < N; i++) {
			System.out.print("image " +  i + ": ");
			for (String s : allPhotos[i].tags) {
				System.out.print(s + " ");
			}
			System.out.println();
		}
	}
}
