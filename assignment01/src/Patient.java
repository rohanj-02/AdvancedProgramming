

public class Patient {
    private static int COUNT = 0;
    private int id;
    private String name;
    private int age;
    private int oxygenLevel;
    private float bodyTemperature;
    private boolean admissionStatus;
    private HealthCareInstitute admittingInstitute;


    public static int getCOUNT() {
        return COUNT;
    }

    public static void setCOUNT(int COUNT) {
        Patient.COUNT = COUNT;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getOxygenLevel() {
        return oxygenLevel;
    }

    public void setOxygenLevel(int oxygenLevel) {
        this.oxygenLevel = oxygenLevel;
    }

    public float getBodyTemperature() {
        return bodyTemperature;
    }

    public void setBodyTemperature(float bodyTemperature) {
        this.bodyTemperature = bodyTemperature;
    }

    public boolean isAdmissionStatus() {
        return admissionStatus;
    }

    public void setAdmissionStatus(boolean admissionStatus) {
        this.admissionStatus = admissionStatus;
    }

    public HealthCareInstitute getAdmittingInstitute() {
        return admittingInstitute;
    }

    public void setAdmittingInstitute(HealthCareInstitute admittingInstitute) {
        this.admittingInstitute = admittingInstitute;
        this.admissionStatus = true;
    }

    @Override
    public String toString() {
        return "ID : " + this.getId() + "\nName: "
                + this.getName() + "\nAge : " +
                this.getAge() + "\nTemperature : "
                + this.getBodyTemperature() +
                "\nOxygen Level : " + this.getOxygenLevel()
                + "\nAdmission Status : " +
                this.getAdmissionStatusString() +
                "\nAdmitting Institute : " +
                this.getInstituteName();
    }

    public Patient(String name, int age, int oxygenLevel, float bodyTemperature) {
        this.name = name;
        this.age = age;
        this.oxygenLevel = oxygenLevel;
        this.bodyTemperature = bodyTemperature;
        COUNT++;
        this.id = COUNT;
        this.admissionStatus = false;
        this.admittingInstitute = null;
    }

    public boolean matchesOxygenCriteria(int lowestOxygenLevel) {
        return this.oxygenLevel >= lowestOxygenLevel;
    }

    public boolean matchesTemperatureCriteria(float highestTemperatureLevel) {
        return this.bodyTemperature <= highestTemperatureLevel;
    }

    public String getInstituteName() {
        if (this.getAdmittingInstitute() == null) {
            return "No institute assigned";
        }
        return this.getAdmittingInstitute().getName();
    }

    public String getAdmissionStatusString() {
        return this.isAdmissionStatus() ? "Admitted" : "Not admitted";
    }
}

// Code written by Rohan Jain
// 2019095
