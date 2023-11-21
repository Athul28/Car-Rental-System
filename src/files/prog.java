package files;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

class CarRentalSystem {
    Scanner sc = new Scanner(System.in);

    void show_cars(String f) {
        try {
            File obj = new File(f);
            Scanner avail_cars_obj = new Scanner(obj);
            while (avail_cars_obj.hasNextLine()) {
                String[] words = (avail_cars_obj.nextLine()).split("\\s+");
                //here \\s+ represents white space characters, used to separate each word in a line
                System.out.println(words[0]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    void add_car() {
        try {
            FileWriter obj = new FileWriter("Available_cars.txt", true);
            System.out.println("Enter the car name : ");
            String car_name = sc.next();
            System.out.println("Enter the car price/hour : ");
            int price = sc.nextInt();
            obj.write("\n" + car_name + " " + price);
            obj.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing into the file");
        }
    }

    void rent_car() {
        System.out.println("Enter the car name : ");
        String car_name = sc.next();
        System.out.println("Enter the hours you want to rent the car : ");
        int hour = sc.nextInt();
        try {
            File avail_obj = new File("Available_cars.txt");
            File temp_obj = new File("temp_file.txt");
            Scanner avail_cars_obj = new Scanner(avail_obj);
            Scanner temp_car_obj=new Scanner(temp_obj);
            boolean flag = true;
            while (avail_cars_obj.hasNextLine()) {
                String[] words = (avail_cars_obj.nextLine()).split("\\s+");
                add_car_rent(words,"temp_file.txt");
                if (Objects.equals(words[0], car_name)) {
                    System.out.println("Total price would be : " + (hour * Integer.parseInt(words[1])));
                    flag = false;
                    add_car_rent(words,"Rented_cars.txt");
                }
            }
            FileWriter avail_car=new FileWriter("Available_cars.txt",false);
            while(temp_car_obj.hasNextLine()){
                avail_car.write(temp_car_obj.nextLine());
            }
            avail_car.close();
            FileWriter temp_f=new FileWriter("temp_file.txt",false);
            temp_f.write("");
            temp_f.close();
            if (flag) {
                System.out.println("Car not available, please choose some other car");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error while writing into the file");
        }
    }

    void add_car_rent(String[] car,String f){
        try{
            FileWriter obj=new FileWriter(f);
            obj.write(car[0]+" "+car[1]+"\n");
            obj.close();
        }catch(IOException e){
            System.out.println("Couldn't add car to the file");
        }
    }
}
    public class prog {
        public static void main(String[] args) {
            CarRentalSystem obj = new CarRentalSystem();
            Scanner sc = new Scanner(System.in);
            //Scanner avail_cars_obj=new Scanner("Available_cars.txt");
            System.out.println("Car Rental System");
            System.out.println("--------------------");
            System.out.println("1.Rent a car\n2.Show available cars\n3.Add a car\n4.Show rented cars\n0.Exit");
            System.out.println("--------------------");
            int choice;
            do {
                System.out.print("Enter your choice : ");
                choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        obj.rent_car();
                        break;
                    case 2:
                        obj.show_cars("Available_cars.txt");
                        break;
                    case 3:
                        obj.add_car();
                        break;
                    case 4:
                        obj.show_cars("Rented_cars.txt");
                        break;
                    case 0:
                        System.out.println("Exiting the program");
                        break;
                }
                System.out.println("--------------------");
            } while (choice != 0);
        }
    }
