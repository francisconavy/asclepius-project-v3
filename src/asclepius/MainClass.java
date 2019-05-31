
import asclepius.components.Engine.interfaces.DSC.*;
import asclepius.components.Engine.interfaces.Patient.*;
import asclepius.components.Engine.classes.Patient.*;
import asclepius.components.Engine.classes.DSC.*;
import asclepius.components.Engine.interfaces.Doctor.*;
import asclepius.components.Engine.classes.Doctor.*;

public class MainClass{
    public static void main(String[] args) {
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