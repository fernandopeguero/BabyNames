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
    
    int getRank(int year, String name, String gender) {
    
        FileResource fr = new FileResource("..\\Resources\\us_babynames\\us_babynames_test\\yob"+ year+"short.csv");
        CSVParser parser = fr.getCSVParser(false);
        
        int rank = -1;
        int count = 0;
        // loop trough the csvfile
            // update the count if the gender match
            // if name match update rank to count break out of the loop
        for(CSVRecord currentRecord: parser) {
            
            String currentGender = currentRecord.get(1);
            
            if(currentGender.equals(gender)) {
                count++;
                
                String currentName = currentRecord.get(0);
                if(currentName.equals(name)) {
                    rank = count;
                    break;
                }
            }
        }
            
        return rank; 
    }
    
    
    void testTotalBirth() {
        
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false);
        
        totalBirths(parser);
    
    }
}
