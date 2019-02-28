
import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.chrono.MinguoChronology;
import java.util.*;
import java.util.stream.Collectors;

public class ParseInput {

	static int N;
	static SpecialImage[] allPhotos;
	
	public static void main(String[] args) {
		String pathDir = "";
		String fileNameA = "a_example.txt";
		String fileNameB = "b_lovely_landscapes.txt";
		String fileNameC = "c_memorable_moments.txt";
		String fileNameD = "d_pet_pictures.txt";
		String fileNameE = "e_shiny_selfies.txt";
		File file = new File(pathDir + fileNameC);
		parseFile(file);
//		printImages();
		HashMap<String, ArrayList<Integer>> table = lookUpTable(allPhotos);
		List<String> hist = histograma(table);
		System.out.println(hist.get(0));
		System.out.println(table.get(hist.get(0)).size());
		for(String tag: hist){
			System.out.print(tag+" ");
			System.out.println(table.get(tag).size());
		}

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


	public static HashMap<String, ArrayList<Integer>> lookUpTable(SpecialImage[] lst){
		HashMap<String, ArrayList<Integer>> lookup = new HashMap<>();
		for(SpecialImage image: lst){
			for (String tag:image.tags){
				if(lookup.containsKey(tag)){
					lookup.get(tag).add(image.id);
				}else {
					ArrayList<Integer> arr = new ArrayList<>();
					arr.add(image.id);
					lookup.put(tag, arr);
				}
			}
		}
		return lookup;
	}

	public static ArrayList<String> histograma(HashMap<String, ArrayList<Integer>> lookup){
		Comparator<Map.Entry<String, ArrayList<Integer>>> comparator  = new Comparator<Map.Entry<String, ArrayList<Integer>>>() {
			@Override
			public int compare(Map.Entry<String, ArrayList<Integer>> i1, Map.Entry<String, ArrayList<Integer>> i2) {
				if (i1.getValue().size()> i2.getValue().size()){
					return -1;
				}else if (i1.getValue().size() ==  i2.getValue().size()){
					return 0;
				}
				return 1;
			}
		};
		Collection<String> arr = lookup
				.entrySet()
				.stream()
				.sorted(comparator)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getKey,(oldv, newv) ->oldv, LinkedHashMap::new)).values();
		return new ArrayList<>(arr);

	}




	public SpecialImage findTopImage(SpecialImage[] lst){
		SpecialImage topImage = lst[0];
		for(SpecialImage image: lst)
			if (image.tags.size() > topImage.tags.size())
				topImage = image;
		return topImage;
	}


}
