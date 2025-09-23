import java.time.LocalDate;
import java.util.*;

// ===================== Immutable MedicalRecord =====================
final class MedicalRecord {
    private final String recordId;
    private final String patientDNA;
    private final String[] allergies;
    private final String[] medicalHistory;
    private final LocalDate birthDate;
    private final String bloodType;

    public MedicalRecord(String recordId, String patientDNA, String[] allergies, String[] medicalHistory,
                         LocalDate birthDate, String bloodType) {
        if (recordId == null || patientDNA == null || birthDate == null || bloodType == null) {
            throw new IllegalArgumentException("Invalid medical record data");
        }
        this.recordId = recordId;
        this.patientDNA = patientDNA;
        this.allergies = allergies != null ? allergies.clone() : new String[0];
        this.medicalHistory = medicalHistory != null ? medicalHistory.clone() : new String[0];
        this.birthDate = birthDate;
        this.bloodType = bloodType;
    }

    public String getRecordId() { return recordId; }
    public String getPatientDNA() { return patientDNA; }
    public String[] getAllergies() { return allergies.clone(); }
    public String[] getMedicalHistory() { return medicalHistory.clone(); }
    public LocalDate getBirthDate() { return birthDate; }
    public String getBloodType() { return bloodType; }

    public final boolean isAllergicTo(String substance) {
        for (String allergy : allergies) {
            if (allergy.equalsIgnoreCase(substance)) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "recordId='" + recordId + '\'' +
                ", birthDate=" + birthDate +
                ", bloodType='" + bloodType + '\'' +
                '}';
    }
}

// ===================== Patient Class =====================
class Patient {
    private final String patientId;
    private final MedicalRecord medicalRecord;

    private String currentName;
    private String emergencyContact;
    private String insuranceInfo;
    private int roomNumber;
    private String attendingPhysician;

    // Emergency admission constructor
    public Patient(String name) {
        this.patientId = "TEMP-" + UUID.randomUUID();
        this.currentName = name;
        this.medicalRecord = null;
        System.out.println("Emergency admission for " + name);
    }

    // Standard admission constructor
    public Patient(String patientId, String name, String emergencyContact,
                   String insuranceInfo, int roomNumber, String attendingPhysician,
                   MedicalRecord record) {
        this.patientId = patientId;
        this.currentName = name;
        this.emergencyContact = emergencyContact;
        this.insuranceInfo = insuranceInfo;
        this.roomNumber = roomNumber;
        this.attendingPhysician = attendingPhysician;
        this.medicalRecord = record;
    }

    // Transfer admission
    public Patient(String patientId, String name, MedicalRecord record) {
        this.patientId = patientId;
        this.currentName = name;
        this.medicalRecord = record;
    }

    // Getters and setters
    public String getPatientId() { return patientId; }
    public MedicalRecord getMedicalRecord() { return medicalRecord; }
    public String getCurrentName() { return currentName; }
    public void setCurrentName(String currentName) { this.currentName = currentName; }
    public String getEmergencyContact() { return emergencyContact; }
    public void setEmergencyContact(String emergencyContact) { this.emergencyContact = emergencyContact; }
    public String getInsuranceInfo() { return insuranceInfo; }
    public void setInsuranceInfo(String insuranceInfo) { this.insuranceInfo = insuranceInfo; }
    public int getRoomNumber() { return roomNumber; }
    public void setRoomNumber(int roomNumber) { this.roomNumber = roomNumber; }
    public String getAttendingPhysician() { return attendingPhysician; }
    public void setAttendingPhysician(String attendingPhysician) { this.attendingPhysician = attendingPhysician; }

    // Package-private info for staff
    String getBasicInfo() {
        return "Patient: " + currentName + ", Room: " + roomNumber;
    }

    // Public non-sensitive info
    public String getPublicInfo() {
        return "Patient: " + currentName + ", Room: " + roomNumber;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId='" + patientId + '\'' +
                ", name='" + currentName + '\'' +
                ", roomNumber=" + roomNumber +
                '}';
    }
}

// ===================== Medical Staff Classes =====================
class Doctor {
    private final String licenseNumber;
    private final String specialty;
    private final Set<String> certifications;

    public Doctor(String licenseNumber, String specialty, Set<String> certifications) {
        this.licenseNumber = licenseNumber;
        this.specialty = specialty;
        this.certifications = new HashSet<>(certifications);
    }

    public String getLicenseNumber() { return licenseNumber; }
    public String getSpecialty() { return specialty; }
    public Set<String> getCertifications() { return new HashSet<>(certifications); }
}

class Nurse {
    private final String nurseId;
    private final String shift;
    private final List<String> qualifications;

    public Nurse(String nurseId, String shift, List<String> qualifications) {
        this.nurseId = nurseId;
        this.shift = shift;
        this.qualifications = new ArrayList<>(qualifications);
    }

    public String getNurseId() { return nurseId; }
    public String getShift() { return shift; }
    public List<String> getQualifications() { return new ArrayList<>(qualifications); }
}

class Administrator {
    private final String adminId;
    private final List<String> accessPermissions;

    public Administrator(String adminId, List<String> accessPermissions) {
        this.adminId = adminId;
        this.accessPermissions = new ArrayList<>(accessPermissions);
    }

    public String getAdminId() { return adminId; }
    public List<String> getAccessPermissions() { return new ArrayList<>(accessPermissions); }
}

// ===================== HospitalSystem =====================
class HospitalSystem {
    private final Map<String, Object> patientRegistry = new HashMap<>();
    public static final int MAX_PATIENTS = 100;

    public boolean admitPatient(Object patient, Object staff) {
        if (patient instanceof Patient p) {
            if (validateStaffAccess(staff, p)) {
                patientRegistry.put(p.getPatientId(), p);
                System.out.println("Patient admitted: " + p.getCurrentName());
                return true;
            } else {
                System.out.println("Staff not authorized to admit patient.");
                return false;
            }
        }
        return false;
    }

    private boolean validateStaffAccess(Object staff, Patient patient) {
        if (staff instanceof Doctor || staff instanceof Nurse) return true;
        if (staff instanceof Administrator admin) {
            return admin.getAccessPermissions().contains("ADMIT_PATIENT");
        }
        return false;
    }

    Map<String, Object> getRegistry() {
        return patientRegistry;
    }
}

// ===================== Main Test =====================
public class HospitalManagementSystem {
    public static void main(String[] args) {
        MedicalRecord record1 = new MedicalRecord(
                "REC001",
                "DNA12345",
                new String[]{"Peanuts"},
                new String[]{"Flu"},
                LocalDate.of(1990, 5, 15),
                "O+"
        );

        Patient patient1 = new Patient("PAT001", "John Doe", "999-888-777", "ABC Insurance", 101, "Dr. Smith", record1);
        Doctor doctor1 = new Doctor("LIC123", "Cardiology", Set.of("ACLS", "BLS"));
        Nurse nurse1 = new Nurse("NUR456", "Day", List.of("First Aid", "Patient Care"));
        Administrator admin1 = new Administrator("ADM789", List.of("ADMIT_PATIENT"));

        HospitalSystem hospital = new HospitalSystem();
        hospital.admitPatient(patient1, doctor1);
        hospital.admitPatient(patient1, nurse1);
        hospital.admitPatient(patient1, admin1);

        System.out.println("\n--- Patient Registry ---");
        for (Object p : hospital.getRegistry().values()) {
            System.out.println(p);
        }
    }
}
