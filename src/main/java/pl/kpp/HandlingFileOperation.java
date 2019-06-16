package pl.kpp;

import pl.kpp.controllers.materialsControllers.ShowTransactionScreenController;

import javax.swing.text.html.ListView;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HandlingFileOperation {
    private static List<String> tableString = new ArrayList<>();
    private final String PATHTOCONFIG = "config.txt";
    public static List<String> getTableString(){return tableString;}
    public void saveFile(String textToSave){
        File file = new File(PATHTOCONFIG);
        try{
            FileWriter write = new FileWriter(file,true);
            BufferedWriter bufferWrite = new BufferedWriter(write);
            bufferWrite.write(textToSave);
            bufferWrite.newLine();
            bufferWrite.close();
        }catch (IOException e){System.out.println("Błąd tworzenia pliku  " + e);}
    }

    public void readFile(){
        String line;
        try(BufferedReader read = new BufferedReader(new FileReader(PATHTOCONFIG))){
            while ((line=read.readLine())!= null){
                tableString.add(line);
            }
            ShowTransactionScreenController.setPath(tableString.get(0));
            tableString.remove(0);
        } catch (IOException e){ System.out.println("Błąd opdczytu "+e);
        }
    }
}
