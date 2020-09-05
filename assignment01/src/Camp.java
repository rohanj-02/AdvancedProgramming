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
        System.out.print("Enter number of patients : ");
        Scanner s = new Scanner(System.in);
        int numberOfPatients = s.nextInt();
        for(int i = 0; i < numberOfPatients; i++) {
            System.out.print("Enter details of patient " + (i + 1) + " : ");
            String name = s.next();
            float temp = s.nextFloat();
            int oxygenLevel = s.nextInt();
            int age = s.nextInt();
            this.patients.add(new Patient(name, age, oxygenLevel, temp));
            System.out.println("Patient " + (i+1) + " added.");
        }
    }

    public void addInstitute(String name, int oxygen, float temp, int beds) {
        HealthCareInstitute newInstitute = new HealthCareInstitute(oxygen, temp, name, beds);
        newInstitute.display();
        newInstitute.addPatients(this.patients);
        newInstitute.printPatients();
        this.institutes.add(newInstitute);
    }

    public void removeAdmittedPatients() {
        ArrayList<Patient> newList = new ArrayList<>();
        boolean flag = false;
        for (Patient patient : patients) {
            if (patient.isAdmissionStatus()) {
                if(!flag){
                    System.out.println("Deleted accounts with ID : ");
                    flag = true;
                }
                System.out.println(patient.getId());
            } else {
                newList.add(patient);
            }
        }
        this.patients = newList;
        if(!flag){
            System.out.println("No patients were admitted in health care institutes!");
        }
    }

    public void removeClosedInstitutes() {
        ArrayList<HealthCareInstitute> newList = new ArrayList<>();
        boolean flag = false;
        for(HealthCareInstitute institute: institutes){
            if(institute.getStatus()){
                newList.add(institute);
            }
            else{
                if(!flag){
                    System.out.println("Deleted Institutes : ");
                    flag = true;
                }
                System.out.println(institute.getName());
            }
        }
        this.institutes = newList;
        if(!flag){
            System.out.println("No institutes have closed status!");
        }
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
        boolean flag = false;
        for(HealthCareInstitute institute: institutes){
            if(institute.getName().equals(name)){
                institute.display();
                flag = true;
            }
        }
        if(!flag){
            System.out.println("The Institute does not exist or has been deleted.");
        }
    }

    public void displayPatient(int id) {
        boolean flag = false;
        for(Patient patient: patients){
            if(patient.getId() == id){
                System.out.println(patient);
                flag = true;
            }
        }
        if(!flag){
            System.out.println("Patient with id " + id + " does not exist or has been deleted.");
        }
    }

    public void displayAllPatients() {
        for(Patient patient: patients){
            System.out.println(patient.getId() + "   " + patient.getName());
        }
    }

    public void displayPatientsInInstitute(String name){
        boolean flag = false;
        for(HealthCareInstitute institute: institutes){
            if(institute.getName().equals(name)){
                institute.printPatients();
                flag = true;
            }
        }
        if(!flag){
            System.out.println("The institute does not exist or has been deleted.");
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

// Code written by Rohan Jain
// 2019095
