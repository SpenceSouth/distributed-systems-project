import java.io.*;

/**
 * Created by Brian on 1/18/2016.
 */
public class DatabaseStructure {

    //holds address lines
    String[] dataLines;
    int numberOfLines;

    private final static String fileName = "data.dat";

    //constructor
    public DatabaseStructure(){
        loadFromDataFile();
    }

    //Return specific line with input of index
    public String getLine(int index){
        return dataLines[index];
    }

    //return full listing of lines
    public String getList(){
        String fullLine = "";
        int size = getSize();
        for(int i = 0; i< size; i++)
            fullLine +=dataLines[i] + "\n";
        return fullLine;
    }

    //return number of lines in database Structure
    public int getSize(){
        return numberOfLines;
    }

    //update a line in the data structure
    public void updateLine(int index, String newLine) {
        dataLines[index] = newLine;
    }

    //load data to data structure form data file
    public void loadFromDataFile(){
        try{
            FileInputStream in = new FileInputStream(fileName);
            String line = in.toString();
            dataLines = line.split("\n");
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    //writes full data structure to data file
    public void writeFullDataFile(){
        try{
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName),"utf-8"));
            for(int index = 0; index < numberOfLines;index++){
                writer.write(dataLines[index] + "\n");
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
