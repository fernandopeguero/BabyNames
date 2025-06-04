import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File; 
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
    
    String getName(int year, int rank, String gender) {
    
        
        // return the name of the person with the rank given 
        FileResource fr = new FileResource("..\\Resources\\us_babynames\\us_babynames_test\\yob"+ year+"short.csv");
        CSVParser parser = fr.getCSVParser(false);
        
        int count = 0 ;
        
        for(CSVRecord record : parser){
        
            count++;
            
            if(count == rank){
                return record.get(0);
            }
            
        }
        
        return "No Name";
    }
    
    void whatIsNameInYear(String name, int year, int newYear, String gender) {
        
        
        int rank = getRank(year , name, gender); 
        
        String newName = getName(newYear, rank, gender);
        
        System.out.println( name + " born in " + year + " would be " + newName + " if born in " + newYear);
    
    }
    
    int yearOfHighestRank(String name, String gender) {
        
        DirectoryResource dr = new DirectoryResource();
        int highestRank = -1;
        int highestRankYear =  -1;
        int currentYear = 2012;
        for(File f: dr.selectedFiles()){
            
            int rank = getRank(currentYear, name, gender);
            
            if(rank == -1) {
                break;
            }
            
            if(highestRank == -1){
                 highestRank = rank;
                highestRankYear = currentYear;
            
            } else {
                if(rank < highestRank) {
                    highestRank = rank;
                    highestRankYear = currentYear;
                }
            }
            
        
            
            currentYear++;
        }
        
        return highestRankYear;
    
    }
    
    double getAverageRank (String name, String gender) {
        
        DirectoryResource dr = new DirectoryResource();
        double average = 0;
        int count = 0;
        for(File f: dr.selectedFiles()) {
            
            int currentYear = Integer.parseInt(f.getName().substring(3,7));
            int rank = getRank(currentYear, name, gender);
                
            count++;
            average += rank;
            
        }
        
        if(average == 0) {
            return -1;
        } 
        
        return average / count;
    }
    
    
    void testTotalBirth() {
        
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false);
        
        totalBirths(parser);
    
    }
}
