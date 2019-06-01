package asclepius.components.Engine.classes.TBC;

public class DataTree{    
    public static TreeBuilderComponent generate(String dataSource){
        return new TreeBuilderComponent(dataSource);
    }
}
