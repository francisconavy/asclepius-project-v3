package asclepius;

import asclepius.components.ClownCare.classes.ClownCare;
import asclepius.components.Engine.classes.ADoctor.ADoctor;
import asclepius.components.Engine.classes.APatient.APatient;
import asclepius.components.Engine.classes.DSC.DataSetComponent;
import asclepius.components.Engine.interfaces.ADoctor.IADoctor;
import asclepius.components.Engine.interfaces.APatient.IAPatient;
import asclepius.components.Engine.interfaces.DSC.IDataSet;
import asclepius.components.Hermes.classes.Hermes;
import asclepius.components.TLG.classes.StartTLG;
import asclepius.components.TLG.interfaces.ITLG;

public class MainClass{
    public static void main(String[] args){
        ClownCare palhaco = new ClownCare();
        for(int i=0; i<10; i++) {
            String piada = palhaco.randJoke(10);
            if (piada != null) {
                System.out.println((i+1)+" - "+piada);
            } else {
                System.out.println((i+1)+" - "+"sem piada hoje");
            }
        }

        //Iniciando a interface do telegram
        StartTLG telegramStarter = new StartTLG();
        ITLG telegrambot = telegramStarter.start();

        //Instanciando o hermes
        Hermes hermes = new Hermes();

        //conecta telegram ao hermes
        telegrambot.connect(hermes);
        //conecta hermes ao telegram
        hermes.connect(telegrambot);

        // instanciando o componente DataSet
        IDataSet dataset = new DataSetComponent();
        dataset.setDataSource("resources/data/zombie-health-spreadsheet-ml-training.csv");

        // instanciando o componente paciente
        IAPatient aPatient = new APatient();

        //conecta hermes ao paciente;
        hermes.connect(aPatient);

        // conectando-o no componente DataSet
        //aPatient.connect(dataset);

        //conecta o paciente ao hermes
        aPatient.connect(hermes);

        // instanciando o componente doutor louco
        IADoctor cDoctor = new ADoctor();

        // conectando-o ao componente DataSet
        //cDoctor.connect(dataset);

        // conectando-o ao componente paciente
        cDoctor.connect(aPatient);

        //conectando o paciente ao doutor
        aPatient.connect(cDoctor);
    }
}