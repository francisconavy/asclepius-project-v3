package asclepius.components.Engine.classes.Patient;
import asclepius.components.Engine.interfaces.all.*;
import asclepius.components.Engine.interfaces.Patient.*;
import asclepius.components.Hermes.interfaces.IHermes;

public class Patient implements IPatient {
    private int patientN = 0;
    private int actualSym = 0;

    private ITableProducer producer;
    private IEnquirer enquirer;
    private IHermes hermes;

    private String attributes[];
    private String patientInstance[];

    public void connect(ITableProducer producer) {
        this.producer = producer;

        attributes = producer.requestAttributes();
        patientInstance = new String[attributes.length -1];
//        String instances[][] = producer.requestInstances();
//
//        patientN = (int)(Math.random() * instances.length);
//        patientInstance = instances[patientN];
//
//        System.out.println("Patient selected: " + patientN);
//        System.out.println("Patient's disease: " + patientInstance[attributes.length - 1]);
    }

    public void connect(IEnquirer enquirer){
        this.enquirer = enquirer;
    }

    public void connect(IHermes hermes){
        this.hermes = hermes;
    }

    public String[] getAttributes(){
        //System.out.println(attributes[0]);
        return attributes;
    }

    public int getActualSym() {
        return this.actualSym;
    }

    public void hi(){
        enquirer.startInterview();
    }

    public void isReady() {
        enquirer.getDiagnosis();
    }

    public void symAnswer(String answer) {
        enquirer.getDiagnosis(answer);
    }

    public void takeNote(String answer){
        System.out.println(answer);
        patientInstance[actualSym] = answer;
        actualSym++;
    }

    public String[] getPatInst(){
        return patientInstance;
    }

    public void ask(String question) {
        //hermes.takeIn(question);
    }

    public void tellDisease(String disease){
        //hermes.takeIn(disease);
    }

    public boolean finalAnswer(String answer) {
        boolean result = false;
        if (answer.equalsIgnoreCase(patientInstance[attributes.length - 1]))
            result = true;
        return result;
    }
}