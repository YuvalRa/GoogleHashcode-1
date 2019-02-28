package hashcode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.chrono.MinguoChronology;
import java.util.*;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;

public class ParseInput {

	static int N;
	static SpecialImage[] allPhotos;
	static List<int[]> solution = new ArrayList<>();
	static List<SpecialImage> verticals = new ArrayList<>();
	static List<SpecialImage> horizontals = new ArrayList<>();

	public static void main(String[] args) {
		String pathDir = "C:/Users/Iris/Desktop/hashcode/";
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
		
		        for (int i=0; i< hist.size()/2; i++) {
            SpecialImage[] imagesToNextLevel = getImgsWithTags(
                    (ArrayList<String>) hist,
                    allPhotos,
                    1,
                    2
            );
            HashMap<String, ArrayList<Integer>> newTable = lookUpTable(imagesToNextLevel);
            List<String> newHist = histograma(newTable);
            SpecialImage[] imagesToNextNextLevel = getImgsWithTags(
                    (ArrayList<String>) newHist,
                    imagesToNextLevel,
                    2,
                    0
            );
            hist.remove(0);
            hist.remove(hist.size()-1);
            i--;
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

				SpecialImage image = new SpecialImage(ss[0].charAt(0), tags, i);
				allPhotos[i] = image;
				int numOfTags = Integer.parseInt(ss[1]);
				for (int j = 2; j < numOfTags+2; j++) {
					tags.add(ss[j]);
				}
				
				if (image.imgType == 'H') {
					horizontals.add(image);
				}
				else {
					verticals.add(image);
				}

			}

			reader.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	public static void createOutput() {
		String pathDir = "C:/Users/Iris/Desktop/hashcode/";
		String fileName = "solution";
		String problemSymbol = "B";
		File file = new File(pathDir + fileName + problemSymbol + ".txt");
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));

			writer.write(solution.size());
			for (int i = 0; i < solution.size(); i++) {
				int[] slide = solution.get(i);
				if (slide.length == 1) {
					writer.write(slide[0]+"\n");
				}
				else {
					writer.write(slide[0] + " " + slide[1] +"\n");
				}
//					System.out.print(pic + " ");
//				System.out.println();
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

	
    public static SpecialImage[] getImgsWithTags(
            ArrayList<String> tagsToFind,
            SpecialImage[] imgs,
            int numOfCommonToFind,
            int numOfRareToFind
    )  {
        
        String[] commonTags = new String[numOfCommonToFind];
        String[] rareTags = new String[numOfRareToFind];

        for (int i = 0; i < numOfCommonToFind; i++) {
            commonTags[i] = tagsToFind.get(i);
        }

        for (int i = 0; i < numOfRareToFind; i++) {
            rareTags[i] = tagsToFind.get(tagsToFind.size()-i-1);
        }

        ArrayList<SpecialImage> wantedImages = new ArrayList<>();
        for (SpecialImage image : imgs) {
            int counter = 0;
            for (String tag : commonTags) {
                if (image.tags.contains(tag)) {
                    counter++;
                }
            }
            if (counter == commonTags.length) {
                wantedImages.add(image);
            }
        }

        for (int i = 0; i < wantedImages.size(); i++) {
            boolean hasRare = false;
            for (String tag : rareTags) {
                if (wantedImages.get(i).tags.contains(tag)) {
                    hasRare = true;
                }
            }
            if (!hasRare) {
                wantedImages.remove(i);
                i--;
            }
        }

        SpecialImage[] returnedImgs = new SpecialImage[wantedImages.size()];
        returnedImgs = wantedImages.toArray(returnedImgs);
        return returnedImgs;
    }


}
