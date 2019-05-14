package pl.kpp;

import pl.kpp.controllers.materialsControllers.ShowTransactionScreenController;

import java.io.*;

public class HandlingFileOperation {
    private final String PATHTOCONFIG = "config.txt";
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
                ShowTransactionScreenController.setPath(line);
            }
        } catch (IOException e){ System.out.println("Błąd opdczytu "+e);
        }
    }
}
