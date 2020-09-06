
// DONE
public class Patient {
    private static int COUNT = 0;
    private final int id;
    private final String name;
    private final int age;
    private final int oxygenLevel;
    private final float bodyTemperature;
    private boolean admissionStatus;
    private HealthCareInstitute admittingInstitute;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getOxygenLevel() {
        return oxygenLevel;
    }

    public float getBodyTemperature() {
        return bodyTemperature;
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
        this.setAdmissionStatus(true);
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
        return this.getOxygenLevel() >= lowestOxygenLevel;
    }

    public boolean matchesTemperatureCriteria(float highestTemperatureLevel) {
        return this.getBodyTemperature() <= highestTemperatureLevel;
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
