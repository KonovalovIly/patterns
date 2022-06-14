package Lab_1.Singleton;

public class Lab_1_1 {

    public static void main(String[] args) {
        startLab_1();
    }

    private static void startLab_1(){
        ConfigurationManager configurationManager = ConfigurationManager.getInstance();
        System.out.println("Reading from config.properties...");
        int property = configurationManager.getPropertyFromConfig();
        String hashCode = configurationManager.toString();
        System.out.println("Property: " + property + ", HashCode ConfigManager: " + hashCode);
        ConfigurationManager configurationManager2 = ConfigurationManager.getInstance();
        int property2 = configurationManager2.getPropertyFromConfig();
        String hashCode2 = configurationManager2.toString();
        System.out.println("Property: " + property2 + ", HashCode ConfigManager: " + hashCode2);
    }

}
