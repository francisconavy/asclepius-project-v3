package asclepius.components.Engine.constants;

import java.util.ArrayList;
import java.util.Arrays;

public class SetConstants {
    private static final String sim = "1";
    private static final String nao = "0";
    private static final String naoSei = "unknown";
    private static final String defaultSource = "resources\\data\\test-cases-500-v2.csv";
    private static final ArrayList<String> affirmative = new ArrayList<>(Arrays.asList("vamos", "sim", "não", "nao", "bora", "vamo", "afirmativo"));

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

    public static ArrayList<String> getAffirmative() { return affirmative; }
}
