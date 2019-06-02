

import asclepius.components.Engine.classes.DSC.DataSetComponent;
import asclepius.components.Engine.classes.Doctor.Doctor;
import asclepius.components.Engine.classes.Patient.Patient;
import asclepius.components.Engine.interfaces.DSC.IDataSet;
import asclepius.components.Engine.interfaces.Doctor.IDoctor;
import asclepius.components.Engine.interfaces.Patient.IPatient;
import asclepius.components.TLG.classes.StartTLG;
import asclepius.components.Hermes.classes.Hermes;
import asclepius.components.Hermes.interfaces.IHermes;

public class MainClass{
    public static void main(String[] args) {

        Hermes teste = new Hermes();

        //Iniciando a interface do telegram
        StartTLG telegrambot = new StartTLG();
        telegrambot.start(teste);

        // instanciando o componente DataSet
        IDataSet dataset = new DataSetComponent();
        dataset.setDataSource("resources/zombie-health-spreadsheet-ml-training.csv");

        // instanciando o componente paciente
        IPatient aPatient = new Patient();

        // conectando-o no componente DataSet
        aPatient.connect(dataset);

        // instanciando o componente doutor louco
        IDoctor cDoctor = new Doctor();

        // conectando-o ao componente DataSet
        cDoctor.connect(dataset);

        // conectando-o ao componente paciente
        cDoctor.connect(aPatient);

        // executando a entrevista
        cDoctor.startInterview();

    }
}