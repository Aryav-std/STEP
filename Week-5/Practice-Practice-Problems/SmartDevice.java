import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SmartDevice {

    // ✅ Read-only properties
    private final String deviceId;
    private final LocalDateTime manufacturingDate;
    private final String serialNumber;

    // ✅ Write-only properties (internally hashed)
    private int hashedEncryptionKey;
    private int hashedAdminPassword;

    // ✅ Read-write properties
    private String deviceName;
    private boolean isEnabled;

    // ✅ Computed read-only properties
    private final LocalDateTime startupTime;

    // 🔒 Constructor
    public SmartDevice(String deviceName) {
        this.deviceId = UUID.randomUUID().toString();
        this.manufacturingDate = LocalDateTime.now();
        this.serialNumber = "SN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.startupTime = LocalDateTime.now();
        this.deviceName = deviceName;
        this.isEnabled = true; // default enabled
    }

    // ✅ Read-only property getters
    public String getDeviceId() { return deviceId; }
    public LocalDateTime getManufacturingDate() { return manufacturingDate; }
    public String getSerialNumber() { return serialNumber; }
    public long getUptime() { return ChronoUnit.SECONDS.between(startupTime, LocalDateTime.now()); }
    public int getDeviceAge() { return (int) ChronoUnit.YEARS.between(manufacturingDate, LocalDateTime.now()); }

    // ✅ Write-only property setters
    public void setEncryptionKey(String key) {
        if (key != null && key.length() >= 8) {
            this.hashedEncryptionKey = key.hashCode();
        } else {
            throw new IllegalArgumentException("Encryption key must be at least 8 characters");
        }
    }

    public void setAdminPassword(String password) {
        if (password != null && password.matches("(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z]).{6,}")) {
            this.hashedAdminPassword = password.hashCode();
        } else {
            throw new IllegalArgumentException("Password must be 6+ chars with upper, lower, and digit");
        }
    }

    // ✅ Validation methods (indirect checking)
    public boolean validateEncryptionKey(String key) {
        return key != null && key.hashCode() == hashedEncryptionKey;
    }

    public boolean validateAdminPassword(String password) {
        return password != null && password.hashCode() == hashedAdminPassword;
    }

    // ✅ Read-Write property methods
    public String getDeviceName() { return deviceName; }
    public void setDeviceName(String deviceName) { this.deviceName = deviceName; }

    public boolean isEnabled() { return isEnabled; }
    public void setEnabled(boolean enabled) { isEnabled = enabled; }

    // ✅ Utility methods
    public Map<String, String> getPropertyInfo() {
        Map<String, String> info = new HashMap<>();
        info.put("deviceId", "Read-Only");
        info.put("manufacturingDate", "Read-Only");
        info.put("serialNumber", "Read-Only");
        info.put("uptime", "Computed Read-Only");
        info.put("deviceAge", "Computed Read-Only");
        info.put("encryptionKey", "Write-Only");
        info.put("adminPassword", "Write-Only");
        info.put("deviceName", "Read-Write");
        info.put("isEnabled", "Read-Write");
        return info;
    }

    public void resetDevice() {
        this.hashedEncryptionKey = 0;
        this.hashedAdminPassword = 0;
        this.isEnabled = false;
    }

    // ✅ Main demo
    public static void main(String[] args) {
        SmartDevice device1 = new SmartDevice("Thermostat");
        SmartDevice device2 = new SmartDevice("Security Camera");

        // 🔹 Read-only properties
        System.out.println("Device1 ID: " + device1.getDeviceId());
        System.out.println("Device1 Manufacturing Date: " + device1.getManufacturingDate());
        System.out.println("Device1 Serial: " + device1.getSerialNumber());
        System.out.println("Device1 Uptime: " + device1.getUptime() + " seconds");
        System.out.println("Device1 Age: " + device1.getDeviceAge() + " years");

        // 🔹 Write-only properties
        device1.setEncryptionKey("SecureKey123");
        device1.setAdminPassword("StrongP4ss");

        // Can’t read them, only validate
        System.out.println("Validate encryption key: " + device1.validateEncryptionKey("SecureKey123"));
        System.out.println("Validate admin password: " + device1.validateAdminPassword("StrongP4ss"));

        // 🔹 Read-write properties
        System.out.println("Device1 Name: " + device1.getDeviceName());
        device1.setDeviceName("Smart Thermostat");
        System.out.println("Device1 New Name: " + device1.getDeviceName());

        System.out.println("Device1 Enabled? " + device1.isEnabled());
        device1.setEnabled(false);
        System.out.println("Device1 Enabled after change? " + device1.isEnabled());

        // 🔹 Utility method
        System.out.println("\nProperty Access Info:");
        System.out.println(device1.getPropertyInfo());

        // 🔹 Reset device
        device1.resetDevice();
        System.out.println("Device1 Enabled after reset? " + device1.isEnabled());

        // 🔹 Independence of multiple devices
        System.out.println("\nDevice2 ID: " + device2.getDeviceId());
        System.out.println("Device2 Name: " + device2.getDeviceName());
    }
}
