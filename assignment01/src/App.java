import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Camp myCamp = new Camp();
        System.out.println("COVID Camp Software");
        myCamp.getPatients();
        while (myCamp.isNotEmpty()) {
            System.out.print("Enter query number : ");
            int query = s.nextInt();
            switch (query) {
                case 1:
                    System.out.print("Enter name of institute : ");
                    String name = s.next();
                    System.out.print("Enter temperature criteria : ");
                    float temp = s.nextFloat();
                    System.out.print("Enter oxygen criteria : ");
                    int oxygen = s.nextInt();
                    System.out.print("Enter number of available beds : ");
                    int beds = s.nextInt();
                    myCamp.addInstitute(name, oxygen, temp, beds);
                    break;
                case 2:
                    myCamp.removeAdmittedPatients();
                    break;
                case 3:
                    myCamp.removeClosedInstitutes();
                    break;
                case 4:
                    myCamp.getPatientsInCamp();
                    break;
                case 5:
                    myCamp.getOpenInstitutes();
                    break;
                case 6:
                    String instituteName = s.next();
                    myCamp.displayInstitute(instituteName);
                    break;
                case 7:
                    int patientID = s.nextInt();
                    myCamp.displayPatient(patientID);
                    break;
                case 8:
                    myCamp.displayAllPatients();
                    break;
                case 9:
                    String tempName = s.next();
                    myCamp.displayPatientsInInstitute(tempName);
                    break;
                default:
                    System.out.println("Wrong input");
            }
        }
        System.out.println("All the patients have been admitted in health care institutes or have been deleted.");
        System.out.println("Ending the program");
    }


}