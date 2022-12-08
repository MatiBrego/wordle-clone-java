package wordleclone.application.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class WordGetter {

    private final ArrayList<String> dictionary = buildDictionary();

    public String getWord(){
        return dictionary.get(new Random().nextInt(dictionary.size()));
    }

    public ArrayList<String> buildDictionary() {
        ArrayList<String> dic = new ArrayList<>();

        try {
            Scanner sc = new Scanner(new File("src//main//resources//dictionary.txt"));
            while(sc.hasNext()){
                dic.add(sc.nextLine());
            }

        }catch (IOException e) {
            System.out.println("File not found");}

        return dic;
    }
}
