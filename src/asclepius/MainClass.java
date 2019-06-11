package asclepius;

import asclepius.components.Engine.classes.ADoctor.ADoctor;
import asclepius.components.Engine.classes.APatient.APatient;
import asclepius.components.Engine.interfaces.ADoctor.IADoctor;
import asclepius.components.Engine.interfaces.APatient.IAPatient;
import asclepius.components.Hermes.classes.Hermes;
import asclepius.components.TLG.classes.StartTLG;
import asclepius.components.TLG.interfaces.ITLG;


public class MainClass{
    public static void main(String[] args){

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
        //IDataSet dataset = new DataSetComponent();
        //dataset.setDataSource("resources/data/zombie-health-spreadsheet-ml-training.csv");

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