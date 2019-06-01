package asclepius.components.Engine.classes.TBC;

import asclepius.components.Engine.interfaces.TBC.ITree;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TreeBuilderComponent implements ITree {
    private TreeNode insTree = null;
    private String[] attributes = null;
    private String[][] instances = null;
    
    public TreeBuilderComponent(String dataSource){ //"dataSource" é o caminho para o arquivo CSV que gerará a árvore.
        setDataSource(dataSource);
        this.insTree = buildTree();
    }
    
    public TreeNode buildNode(int curAttIndex){ //Usado por buildTree() para construir um nó da árvore de busca. Comece executando com curAttIndex = 0.
        TreeNode newNode = new TreeNode(attributes[curAttIndex]);
        
        if(curAttIndex < (attributes.length - 2)){
            newNode.setL(buildNode(curAttIndex + 1));
            newNode.setR(buildNode(curAttIndex + 1));
        }
        
        return newNode;
    }
    
    public TreeNode buildTree(){ //Constrói a árvore e retorna o nó cabeça dela.
        TreeNode headNode = buildNode(0);
        
        for(int x = 0; x < instances.length; x++){
            TreeNode curNode = headNode;
            for(int y = 0; y < instances[x].length - 1; y++){
                if(y == ((instances[x].length - 1) - 1)){
                    if(instances[x][y].equalsIgnoreCase("f")){
                        curNode.addToListL(instances[x][instances[x].length - 1]);
                    }else if(instances[x][y].equalsIgnoreCase("t")){
                        curNode.addToListR(instances[x][instances[x].length - 1]);
                    }
                }else{
                    if(instances[x][y].equalsIgnoreCase("f")){
                        curNode = curNode.getL();
                    }else if(instances[x][y].equalsIgnoreCase("t")){
                        curNode = curNode.getR();
                    }else{
                        System.out.println("ERROR: the buildTree() method has crashed.");
                        break;
                    }   
                }
            }
        }
        return headNode;
    }
    
    public void treePrint(TreeNode treeHead){
        if(treeHead != null){
            System.out.printf("%s ", treeHead.getStrValue());
            treePrint(treeHead.getL());
            treePrint(treeHead.getR());
        }else{
            System.out.printf(". "); //Árvore vazia ou fim da recursão.
        }
    }
    
    @Override
    public void treePrint(){ //Imprime a árvore. Funcionalidade para testes.
        treePrint(insTree);
    }
    
    @Override
    public TreeNode getTreeHead(){
        if(insTree != null){
            return insTree;
        }else{
            System.out.println("WARNING: empty/null tree.");
            return insTree;
        }
    }
    
    public String[] requestAttributes(){
        return this.attributes;
    }
    public String[][] requestInstances(){
        return this.instances;
    }
    
    @Override
    public ArrayList diagCheck(){
        ArrayList<ArrayList<String>> diag = new ArrayList<>();
        //diag é um array com os arrays do último andar da árvore, em InOrder (esquerda p/ direita)
        
        diagArrayBuild(getTreeHead(), diag);
        
        /*for(int x = 0; x < diag.size(); x++){
            System.out.println(diag.get(x));
        }*/
        
        return diag;
    }
    
    private void diagArrayBuild(TreeNode treeHead, ArrayList<ArrayList<String>> diag){
    //Constrói o array retornado por diagCheck()
        if(treeHead != null){
            if(treeHead.getL() != null && treeHead.getR() != null){
                diagArrayBuild(treeHead.getL(), diag);
                diagArrayBuild(treeHead.getR(), diag);
            }else if(treeHead.getL() == null && treeHead.getR() == null){
                diag.add(treeHead.getListL());
                diag.add(treeHead.getListR());
            }else{
                System.out.println("ERROR: method diagArrayBuild() has crashed - bad tree."); //Colocar exceção aqui depois?
            }
        }
    }
    
    private void readDS(String dataSource) throws FileNotFoundException, IOException{
        ArrayList<String[]> instArray = new ArrayList<>();
        try (BufferedReader file = new BufferedReader(new FileReader(dataSource))) {
            String line = file.readLine();
            
            if(line != null){
                this.attributes = line.split(",");
                line = file.readLine();
                while(line != null){
                    String[] instLine = line.split(",");
                    instArray.add(instLine);
                    line = file.readLine();
                }
                this.instances = instArray.toArray(new String[0][]);
            }
        }
    }
    
    public void setDataSource(String dataSource){
        if (dataSource == null) {
            this.attributes = null;
            this.instances = null;
        }else{
            try {
                readDS(dataSource);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(TreeBuilderComponent.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(TreeBuilderComponent.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
