import edu.duke.*;
import org.apache.commons.csv.*;
/**
 * Write a description of Names here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Names {

    
    void totalBirths(CSVParser parser) {
        
        // create variables to track total, girl , boys birth 
        // loop trough the parser 
            // update the count base on the sepcific gender 
        
        // print the girl, boys and the total birth in a given year 
        
        int totalBirth = 0 ;
        int girls = 0;
        int boys = 0;
        
        for(CSVRecord currentRecord: parser){
        
            String gender = currentRecord.get(1);
            
            if(gender.equals("F")) {
                girls += Integer.parseInt(currentRecord.get(2));
            } else {
                boys += Integer.parseInt(currentRecord.get(2));
            }
        
        }
    
        totalBirth = boys + girls;
        
        System.out.println("There were " + totalBirth + " total Births");
        System.out.println("There were " + girls + " girls births");
        System.out.println("There were " + boys + " boys birth");
    }
    
    
    void testTotalBirth() {
        
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false);
        
        totalBirths(parser);
    
    }
}
