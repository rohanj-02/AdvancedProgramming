import java.util.ArrayList;
import java.util.Scanner;

public class HealthCareInstitute {
    private int oxygenCriteria;
    private float temperatureCriteria;
    private String name;
    private boolean status; // true when open, false when closed
    private int numberOfBedsTotal;
    private int numberOfBedsUnoccupied;
    private ArrayList<PatientInfo> patientList;

    static class PatientInfo {
        Patient patient;
        int recoveryDays;

        public Patient getPatient() {
            return patient;
        }

        public void setPatient(Patient patient) {
            this.patient = patient;
        }

        public int getRecoveryDays() {
            return recoveryDays;
        }

        public void setRecoveryDays(int recoveryDays) {
            this.recoveryDays = recoveryDays;
        }

        public PatientInfo(Patient patient, int recoveryDays) {
            this.patient = patient;
            this.recoveryDays = recoveryDays;
        }

        public PatientInfo(Patient patient) {
            this.patient = patient;
            this.recoveryDays = 0;
        }

        public int getPatientId(){
            return this.patient.getId();
        }

        public String getPatientName(){
            return this.patient.getName();
        }
    }

    public int getOxygenCriteria() {
        return oxygenCriteria;
    }

    public void setOxygenCriteria(int oxygenCriteria) {
        this.oxygenCriteria = oxygenCriteria;
    }

    public float getTemperatureCriteria() {
        return temperatureCriteria;
    }

    public void setTemperatureCriteria(float temperatureCriteria) {
        this.temperatureCriteria = temperatureCriteria;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getNumberOfBedsTotal() {
        return numberOfBedsTotal;
    }

    public void setNumberOfBedsTotal(int numberOfBedsTotal) {
        this.numberOfBedsTotal = numberOfBedsTotal;
    }

    public int getNumberOfBedsUnoccupied() {
        return numberOfBedsUnoccupied;
    }

    public void setNumberOfBedsUnoccupied(int numberOfBedsUnoccupied) {
        this.numberOfBedsUnoccupied = numberOfBedsUnoccupied;
    }

    public ArrayList<PatientInfo> getPatientList() {
        return patientList;
    }

    public void decreaseNumberOfBedsUnoccupied() {
        this.numberOfBedsUnoccupied--;
    }

    public void setPatientList(ArrayList<PatientInfo> patientList) {
        this.patientList = patientList;
    }

    @Override
    public String toString() {
        return "HealthCareInstitute{" +
                "oxygenCriteria=" + oxygenCriteria +
                ", temperatureCriteria=" + temperatureCriteria +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", numberOfBedsTotal=" + numberOfBedsTotal +
                ", numberOfBedsUnoccupied=" + numberOfBedsUnoccupied +
                ", patientList=" + patientList +
                '}';
    }

    public HealthCareInstitute(int oxygenCriteria, float temperatureCriteria, String name, int numberOfBedsTotal) {
        this.oxygenCriteria = oxygenCriteria;
        this.temperatureCriteria = temperatureCriteria;
        this.name = name;
        this.numberOfBedsTotal = numberOfBedsTotal;
        this.numberOfBedsUnoccupied = numberOfBedsTotal;
        this.patientList = new ArrayList<>();
        this.status = true;
    }

    public void addPatients(ArrayList<Patient> patients) {
        for (Patient patient : patients) {
            if (!patient.isAdmissionStatus() && this.numberOfBedsUnoccupied > 0) {
                if (patient.matchesOxygenCriteria(this.oxygenCriteria)) {
                    this.patientList.add(this.getRecoveryDateInput(patient));
                    this.decreaseNumberOfBedsUnoccupied();
                    patient.setAdmittingInstitute(this);
                }
            }

        }
        for (Patient patient : patients) {
            if (!patient.isAdmissionStatus() && this.numberOfBedsUnoccupied > 0) {
                if (patient.matchesTemperatureCriteria(this.temperatureCriteria)) {
                    this.patientList.add(this.getRecoveryDateInput(patient));
                    this.decreaseNumberOfBedsUnoccupied();
                    patient.setAdmittingInstitute(this);
                }
            }
        }

        if(this.getNumberOfBedsUnoccupied() == 0){
            this.setStatus(false);
        }

    }

    private PatientInfo getRecoveryDateInput(Patient obj) {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter recovery days for patient ID " + obj.getId() + " : ");
        int recoveryDays = s.nextInt();
        return new PatientInfo(obj, recoveryDays);
    }

    public void display(){
        //addInstitute display
        System.out.println(this.getName());
        System.out.println("Oxygen Level >= "+ this.getOxygenCriteria());
        System.out.println("Temperature Level <= " + this.getTemperatureCriteria());
        System.out.println("Available Beds : " + this.getNumberOfBedsUnoccupied());
        String statusPrint = this.getStatus() ? "Open" : "Closed";
        System.out.println("Admission Status : " + statusPrint);
    }

    public void printPatients(){
        //name and recovery days
        System.out.println("Name\tID\tRecovery Days");
        for(PatientInfo patientGroup: patientList){
            System.out.println(patientGroup.getPatientName() + "\t\t" + patientGroup.getPatientId() + "\t" + patientGroup.getRecoveryDays());
        }
    }

}
