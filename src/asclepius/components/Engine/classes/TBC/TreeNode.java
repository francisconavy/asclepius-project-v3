package asclepius.components.Engine.classes.TBC;

import java.util.ArrayList;

public class TreeNode { //Cada "TreeNode" é um nó da árvore.
    private String strValue;
    private TreeNode L; //Usado como o 'false'
    private TreeNode R; //Usado como o 'true'
    private ArrayList<String> diagListL = new ArrayList<>();
    private ArrayList<String> diagListR = new ArrayList<>(); 

    public TreeNode(String strValue){
        this.strValue = strValue;
        this.L = null;
        this.R = null;
    }

    public String getStrValue(){
        return strValue;
    }
    
    public TreeNode getL(){
        return L;
    }
    
    public TreeNode getR(){
        return R;
    }
    
    public ArrayList getListL(){
        return diagListL;
    }
    
    public ArrayList getListR(){
        return diagListR;
    }
    
    public void setStrValue(String strValue){
        this.strValue = strValue;
    }
    
    public void setL(TreeNode L){
        this.L = L;
    }
    
    public void setR(TreeNode R){
        this.R = R;
    }
    
    public void addToListL(String symptom){
        diagListL.add(symptom);
    }
    
    public void addToListR(String symptom){
        diagListR.add(symptom);
    }
}
