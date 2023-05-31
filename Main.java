import java.io.IOException;
import java.util.Scanner;

class WrongStudentName extends Exception { }
class WrongAge extends Exception { }
class Wrongdate extends Exception { }

class Main {
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        while(true) {
            try {
                int ex = menu();
                switch(ex) {
                    case 1: exercise1(); break;
                    case 2: exercise2(); break;
                    case 3: exercise3(); break;
                    default: return;
                }
            } catch(IOException e) {

            } catch(WrongStudentName e) {
                System.out.println("Błędne imie studenta!");
            }
              catch(WrongAge e) {
                System.out.println("Błędny wiek!");
            }
          catch(Wrongdate e) {
                System.out.println("Błędna data!");
            }
        }
    }

    public static int menu() {
        System.out.println("Wciśnij:");
        System.out.println("1 - aby dodać studenta");
        System.out.println("2 - aby wypisać wszystkich studentów");
        System.out.println("3 - aby wyszukać studenta po imieniu");
        System.out.println("0 - aby wyjść z programu");
        return scan.nextInt();
    }

    public static String ReadName() throws WrongStudentName {
        scan.nextLine();
        System.out.println("Podaj imie: ");
        String name = scan.nextLine();
        if(name.contains(" "))
            throw new WrongStudentName();
        return name;
    }

    public static int ReadAge() throws WrongAge {
          //scan.nextInt();
          System.out.println("Podaj wiek: ");
          int age = scan.nextInt();
          if(age > 110 || age < 1)
              throw new WrongAge();
          return age;
      }

   public static String ReadData() throws Wrongdate {
        scan.nextLine();
        System.out.println("Podaj date: ");
        String date = scan.nextLine();

            //!!!!!!!!!!!!!!(DD-MM-RRRR)!!!!!!!!!
     String data_dzien = date.substring(0, 2);
     String data_miesiac = date.substring(3, 5);
     String data_rok = date.substring(6, 10);

      int int_dzien = Integer.parseInt(data_dzien);
      int int_miesiac = Integer.parseInt(data_miesiac);
      int int_rok = Integer.parseInt(data_rok);
      
     
    System.out.println("Wpisane: dzień " + int_dzien + ",  miesiąc " + int_miesiac + ",  rok "+ int_rok);


     
        if(date.contains(" ") || date.length() != 10 || date.charAt(2) != '-'|| date.charAt(5) != '-'|| int_dzien > 31 || int_miesiac> 12 || int_rok > 2050  ) 
            throw new Wrongdate();
        return date;
    }

  
  
    public static void exercise1() throws IOException, WrongStudentName,WrongAge,Wrongdate {
        var name = ReadName();
      // System.out.println("Podaj wiek: ");
     //   var age = scan.nextInt();
        var age = ReadAge();
      
       // scan.nextLine(); 
      //  System.out.println("Podaj datę urodzenia DD-MM-YYY");
        var date = ReadData();
      //
        (new Service()).addStudent(new Student(name, age, date));
    }






  
    public static void exercise2() throws IOException {
        var students = (new Service()).getStudents();
        for(Student current : students) {
            System.out.println(current.ToString());
        }
    }

    public static void exercise3() throws IOException {
        scan.nextLine();
        System.out.println("Podaj imie: ");
        var name = scan.nextLine();
        var wanted = (new Service()).findStudentByName(name);
        if(wanted == null)
            System.out.println("Nie znaleziono...");
        else {
            System.out.println("Znaleziono: ");
            System.out.println(wanted.ToString());

        }
    }
}
