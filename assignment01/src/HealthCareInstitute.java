import java.util.ArrayList;
import java.util.Scanner;

public class HealthCareInstitute {
    private final int oxygenCriteria;
    private final float temperatureCriteria;
    private final String name;
    private boolean status; // true when open, false when closed
    private int numberOfBedsUnoccupied;
    private final ArrayList<PatientInfo> patientList;

    private static class PatientInfo {
        private final String patientName;
        private final int patientID;
        private final int recoveryDays;

        public String getPatientName() {
            return patientName;
        }

        public int getPatientID() {
            return patientID;
        }

        public int getRecoveryDays() {
            return recoveryDays;
        }

        public PatientInfo(String patientName, int patientID, int recoveryDays) {
            this.patientName = patientName;
            this.patientID = patientID;
            this.recoveryDays = recoveryDays;
        }
    }

    public int getOxygenCriteria() {
        return oxygenCriteria;
    }

    public float getTemperatureCriteria() {
        return temperatureCriteria;
    }

    public String getName() {
        return name;
    }

    public boolean getStatus() {
        return status;
    }

    public int getNumberOfBedsUnoccupied() {
        return numberOfBedsUnoccupied;
    }

    public ArrayList<PatientInfo> getPatientList() {
        return patientList;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setNumberOfBedsUnoccupied(int numberOfBedsUnoccupied) {
        this.numberOfBedsUnoccupied = numberOfBedsUnoccupied;
    }

    private boolean hasUnoccupiedBeds() {
        return this.getNumberOfBedsUnoccupied() > 0;
    }

    private void decreaseNumberOfBedsUnoccupied() {
        this.setNumberOfBedsUnoccupied(this.getNumberOfBedsUnoccupied() - 1);
    }

    private void addToPatientList(PatientInfo patient) {
        this.getPatientList().add(patient);
    }

    @Override
    public String toString() {
        return "HealthCareInstitute{" +
                "oxygenCriteria=" + oxygenCriteria +
                ", temperatureCriteria=" + temperatureCriteria +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", numberOfBedsUnoccupied=" + numberOfBedsUnoccupied +
                ", patientList=" + patientList +
                '}';
    }

    public HealthCareInstitute(int oxygenCriteria, float temperatureCriteria, String name, int numberOfBedsUnoccupied) {
        this.oxygenCriteria = oxygenCriteria;
        this.temperatureCriteria = temperatureCriteria;
        this.name = name;
        this.numberOfBedsUnoccupied = numberOfBedsUnoccupied;
        this.patientList = new ArrayList<>();
        this.status = true;
    }

    public void addPatients(ArrayList<Patient> patients) {
        // Dependent on Patient
        for (Patient patient : patients) {
            if (!patient.isAdmissionStatus() && this.hasUnoccupiedBeds()) {
                if (patient.matchesOxygenCriteria(this.getOxygenCriteria())) {
//                    this.patientList.add(this.getRecoveryDateInput(patient));
                    this.addToPatientList(this.getRecoveryDateInput(patient));
                    this.decreaseNumberOfBedsUnoccupied();
                    patient.setAdmittingInstitute(this);
                }
            }

        }
        for (Patient patient : patients) {
            if (!patient.isAdmissionStatus() && this.hasUnoccupiedBeds()) {
                if (patient.matchesTemperatureCriteria(this.getTemperatureCriteria())) {
//                    this.patientList.add(this.getRecoveryDateInput(patient));
                    this.addToPatientList(this.getRecoveryDateInput(patient));
                    this.decreaseNumberOfBedsUnoccupied();
                    patient.setAdmittingInstitute(this);
                }
            }
        }

        if (this.getNumberOfBedsUnoccupied() == 0) {
            this.setStatus(false);
        }

    }

    private PatientInfo getRecoveryDateInput(Patient obj) {
        // Dependent on Patient
        Scanner s = new Scanner(System.in);
        System.out.print("Enter recovery days for patient ID " + obj.getId() + " : ");
        int recoveryDays = s.nextInt();
        return new PatientInfo(obj.getName(), obj.getId(), recoveryDays);
    }

    public void display() {
        System.out.println(this.getName());
        System.out.println("Oxygen Level >= " + this.getOxygenCriteria());
        System.out.println("Temperature Level <= " + this.getTemperatureCriteria());
        System.out.println("Available Beds : " + this.getNumberOfBedsUnoccupied());
        String statusPrint = this.getStatus() ? "Open" : "Closed";
        System.out.println("Admission Status : " + statusPrint);
    }

    public void printPatients() {
        System.out.println("Name\tID\tRecovery Days");
        for (PatientInfo patientGroup : this.getPatientList()) {
            System.out.println(patientGroup.getPatientName() + "\t\t" + patientGroup.getPatientID() + "\t" + patientGroup.getRecoveryDays());
        }
    }

}

// Code written by Rohan Jain
// 2019095
