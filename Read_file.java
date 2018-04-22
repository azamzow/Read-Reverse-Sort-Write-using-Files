import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Read_file {
    private static Scanner input;
    private static PrintStream output;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //save current System Output
        PrintStream systemOut = System.out;
        //initialize scanner to read from console
        
        //display instruction
        input = new Scanner(System.in);
        System.out.println("Please enter five names: ");
        
        try{
            //redirect output
            System.setOut(new PrintStream(new FileOutputStream("file1.txt")));
            
            //write names to file 1
            for(int i = 0; i < 5; i++){
                System.out.println(reverse(input.next()));
            }
            System.out.flush();
            System.out.close();
            
            if(readReverseWrite("file1.txt","file2.txt")){
                if(!readReverseSortWrite("file2.txt","file3.txt")){
                    System.out.println("File 3 creation failed.");
                }
            }else{
                System.out.println("File 2 creation failed.");
            }            
        }catch(FileNotFoundException fileNotFoundException){
            System.out.println("File 1 creation failed.");
        }finally{
            System.setOut(systemOut);
        }
    }
    
    public static String reverse(String txt){
        String reversed = "";
        for(int j = txt.length() - 1; j >= 0; j--){
            reversed += txt.charAt(j);
        }
        return reversed;
    }
    
    public static boolean readReverseWrite(String inputFile, String outputFile){
        
        try{
            //redirect output
            input = new Scanner(new BufferedInputStream(new FileInputStream(inputFile)));
            System.setOut(new PrintStream(new FileOutputStream(outputFile)));
            //process 
            while(input.hasNext()){
                System.out.println(reverse(input.next()));
            }
            return true;
        }catch(FileNotFoundException fileNotFoundException){}
        return false;
    }
    
    public static boolean readReverseSortWrite(String inputFile, String outputFile){
        
        try{
            //redirect output
            input = new Scanner(new BufferedInputStream(new FileInputStream(inputFile)));
            System.setOut(new PrintStream(new FileOutputStream(outputFile)));
            //process 
            List<String> strs = new ArrayList<String>();
            while(input.hasNext()){
                strs.add(reverse(input.next()));
            }
            strs.sort(new Comparator<String>(){
                @Override
                public int compare(String t, String t1) {
                    return t1.compareTo(t);
                }
            });
            
            for(int i = strs.size() - 1; i >= 0; i--){
                System.out.println(strs.get(i));
            }
            return true;
        }catch(FileNotFoundException fileNotFoundException){}
        return false;
    }
    
}
