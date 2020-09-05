import java.util.ArrayList;
import java.util.Scanner;

public class Camp {
    private ArrayList<Patient> patients;
    private ArrayList<HealthCareInstitute> institutes;
    private boolean isEmpty;

    public Camp() {
        this.patients = new ArrayList<>();
        this.institutes = new ArrayList<>();
    }

    public void getPatients(){
        Scanner s = new Scanner(System.in);
        int numberOfPatients = s.nextInt();
        for(int i = 0; i < numberOfPatients; i++) {
            String name = s.next();
            float temp = s.nextFloat();
            int oxygenLevel = s.nextInt();
            int age = s.nextInt();
            this.patients.add(new Patient(name, age, oxygenLevel, temp));
        }
    }

    public void addInstitute(String name, int oxygen, float temp, int beds) {
        HealthCareInstitute newInstitute = new HealthCareInstitute(oxygen, temp, name, beds, patients);
        this.institutes.add(newInstitute);
        newInstitute.display();

        newInstitute.printPatients();
    }

    public void removeAdmittedPatients() {
        ArrayList<Patient> newList = new ArrayList<>();
        for (Patient patient : patients) {
            if (patient.isAdmissionStatus()) {
                System.out.println(patient.getId());
            } else {
                newList.add(patient);
            }
        }
        this.patients = newList;
    }

    public void removeClosedInstitutes() {
        ArrayList<HealthCareInstitute> newList = new ArrayList<>();
        for(HealthCareInstitute institute: institutes){
            if(institute.getStatus()){
                newList.add(institute);
            }
            else{
                System.out.println(institute.getName());
            }
        }
        this.institutes = newList;
    }

    public void getPatientsInCamp() {
        int count = 0;
        for(Patient patient: patients){
            if(!patient.isAdmissionStatus()){
                count++;
            }
        }
        System.out.println("The number of patients not admitted : " + count);
    }

    public void getOpenInstitutes() {
        int count = 0;
        for(HealthCareInstitute institute: institutes){
            if(institute.getStatus()){
                count++;
            }
        }
        System.out.println("The number of open institutes : " + count);
    }

    public void displayInstitute(String name) {
        for(HealthCareInstitute institute: institutes){
            if(institute.getName().equals(name)){
                institute.display();
            }
        }
    }

    public void displayPatient(int id) {
        for(Patient patient: patients){
            if(patient.getId() == id){
                System.out.println(patient);
            }
        }
    }

    public void displayAllPatients() {
        for(Patient patient: patients){
            System.out.println(patient.getId() + "   " + patient.getName());
        }
    }

    public void displayPatientsInInstitute(String name){
        for(HealthCareInstitute institute: institutes){
            if(institute.getName().equals(name)){
                institute.printPatients();
            }
        }
    }

    public boolean isNotEmpty(){
        for(Patient patient: patients){
            if(!patient.isAdmissionStatus()) {
                return true;
            }
        }
        return false;
    }
}


//Heealtcare delete then show patients
//Patient delete then show in institute