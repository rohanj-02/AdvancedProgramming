//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//
//public class Patient {
//    String name;
//    int age;
//    char tower;
//    LocalDate reporting;
//    LocalDate recovery;
//
//    private static final String DATE_FORMAT = "dd/MM/yyyy";
//
//    Patient(String name, int age, char tower, LocalDate reporting){
//        this.name = name;
//        this.age = age;
//        this.tower = tower;
//        this.reporting = reporting;
//        this.recovery = reporting.plusDays(21);
//    }
//
//    Patient(){
//        this("John Doe", 10, 'A', LocalDate.now());
//    }
//
//    String getFormattedReportingDate(){
//        return this.reporting.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
//    }
//
//    String getFormattedRecoveryDate(){
//        return this.recovery.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
//    }
//
//    public static void main(String[] args) {
//
//    }
//}
//
//
