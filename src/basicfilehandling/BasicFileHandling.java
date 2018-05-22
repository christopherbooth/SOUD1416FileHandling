/** 
 * Ver 1.1
 * 12/12/2017
 * Chris Booth
 */


//Here I am making some changes to codebase. Let's try commiting these...
package basicfilehandling;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;

public class BasicFileHandling 
{
    static File file;
    static List <String> lines = null;
    static FileWriter fileWriter;  

    public static void setFilePath()
    {
        //This should be set via user-input...
        //Students - can you refine please?
        file = new File("N:/DataSource.txt");
    }
    
    public static void readAllLinesFromFile()
    {
        try
        {
            lines = Files.readAllLines(file.toPath());
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        
        for (String line:lines)
        {
            System.out.println(line);
        }
    }
    
    public static void readSelectedLine(byte lineToRead)
    {
        System.out.println(lines.get(1));
    }
    
    public static void writeToFile(String dataToWrite, char appendMethod)
    {
        FileWriter writer = null;
        
        try
        {   
            file.createNewFile();            
            
            if (appendMethod == 'A')
            {
                writer = new FileWriter(file, true);
            }
            else if (appendMethod == 'W')
            {
                writer = new FileWriter(file);
            }
 
            writer.write(dataToWrite);
            //Line seperator - clunky but works. Can you refactor?
            writer.write(System.getProperty("line.separator"));
            writer.flush();
            writer.close();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }
       
    public static void createNewFile(String path)
    {
        File newFile = new File(path);
        newFile.mkdir();
    }

    public static void main(String[] args) 
    {
        int i = 1;
        while (i == 1)
        {
            Scanner input = new Scanner (System.in);
            
            System.out.println("Press enter to continue");
            input.nextLine();
            
            System.out.println("Please enter an option"
                    + "\n Opt 1. Read All Lines from File"
                    + "\n Opt 2. Read an invidual line number"
                    + "\n Opt 3. Write/Append data to the text file"
                    + "\n Opt 4. Create a new directory"
                    + "\n Opt 5. Exit program");
            String choice = input.nextLine();
            setFilePath();        

            switch (choice)
            {
                case "1":
                    readAllLinesFromFile();
                    break;
                case "2":
                    System.out.println("Please enter the line number to read");
                    byte lineNumber = input.nextByte();
                    readSelectedLine((byte)lineNumber);
                    break;
                case "3":
                    System.out.println("Do you want to append (A) or write (W)");
                    char method = input.next().charAt(0);
                    System.out.println("Please enter the data you want to write to the file");
                    //Below is duplicate line (but needed - line break bug)
                    input.nextLine();
                    String data = input.nextLine();
                    writeToFile(data, method);
                    break;
                case "4":
                    System.out.println("Please enter the file you wish to create...");
                    System.out.println("e.g. N:/newFolder/");
                    String newFolderPath = input.nextLine();
                    createNewFile(newFolderPath);
                    break;
                case "5":
                    //Jump outta loop
                    i = 0;
                    break;
                default:
                    System.out.println("Invalid Option - Please re-enter");
            }   
        }         
    }
    
}
