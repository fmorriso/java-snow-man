import java.awt.Dimension;

public class Main {

    public static void main(String[] args) {

        String title = String.format("Java Snowman using java version %s", getJavaVersion());
        System.out.println(title);
        Dimension scaledSize = SwingScreenUtilities.getScaledSize(0.5, 100);
        createAndShowGUI(scaledSize, title);
    }

    private static void createAndShowGUI(Dimension scaledSize, String title) {
        SnowManGUI gui = new SnowManGUI(scaledSize, title);
        javax.swing.SwingUtilities.invokeLater(gui);
    }

    private static String getJavaVersion() {
        Runtime.Version runTimeVersion = Runtime.version();
        return String.format("%s.%s.%s.%s", runTimeVersion.feature(), runTimeVersion.interim(), runTimeVersion.update(), runTimeVersion.patch());
    }

}