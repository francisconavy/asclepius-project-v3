package asclepius.components.Engine.classes.Patient;
import asclepius.components.Engine.interfaces.all.*;
import asclepius.components.Engine.interfaces.Patient.*;
import asclepius.components.Hermes.interfaces.IHermes;

public class Patient implements IPatient {
    private int patientN = 0;

    private ITableProducer producer;
    private IEnquirer enquirer;
    private IHermes hermes;

    private String attributes[];
    private String patientInstance[];

    public void connect(ITableProducer producer) {
        this.producer = producer;

        attributes = producer.requestAttributes();
        String instances[][] = producer.requestInstances();

        patientN = (int)(Math.random() * instances.length);
        patientInstance = instances[patientN];

        System.out.println("Patient selected: " + patientN);
        System.out.println("Patient's disease: " + patientInstance[attributes.length - 1]);
    }

    public void connect(IEnquirer enquirer){
        this.enquirer = enquirer;
    }

    public void connect(IHermes hermes){
        this.hermes = hermes;
    }

    public void isReady(){
        System.out.println("Minha mãe disse que posso começar a consulta");
        enquirer.startInterview();
    }

    public String ask(String question) {
        String result = "unknown";

        hermes.takeIn(question);
//        for (int a = 0; a < attributes.length - 1; a++)
//            if (question.equalsIgnoreCase(attributes[a]))
//                result = (patientInstance[a].equals("t")) ? "t" : "f";

        return result;
    }

    public boolean finalAnswer(String answer) {
        boolean result = false;
        if (answer.equalsIgnoreCase(patientInstance[attributes.length - 1]))
            result = true;
        return result;
    }
}