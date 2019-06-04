package asclepius.components.Engine.constants;

public class SetConstants {
    private static final String sim = "1";
    private static final String nao = "0";
    private static final String naoSei = "unknown";
    private static final String defaultSource = "resources\\data\\test-cases-500.csv";

    public static String getTrue(){
        return sim;
    }

    public static String getFalse(){
        return nao;
    }

    public static String getUnknown(){
        return naoSei;
    }

    public static String getDefaultSource(){
        return defaultSource;
    }
}
