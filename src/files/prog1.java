package files;
import java.io.File;

public class prog1 {
    public static void main(String[] args) {
        File f=new File("Available_cars.txt");
        try{
            f.createNewFile();
            System.out.println("A file created");
        }catch(Exception e){
            System.out.println("An error occurred");
        }
    }
}