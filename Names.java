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
        
        int boysNames = 0;
        int girlsNames = 0;
        int totalNames = 0;
        
        for(CSVRecord currentRecord: parser){
        
            String gender = currentRecord.get(1);
            
            if(gender.equals("F")) {
                girlsNames++;
                girls += Integer.parseInt(currentRecord.get(2));
            } else {
                boysNames++;
                boys += Integer.parseInt(currentRecord.get(2));
            }
        
        }
    
        totalBirth = boys + girls;
        totalNames = boysNames + girlsNames;
        System.out.println("There were " + totalBirth + " total Births");
        System.out.println("There were " + girls + " girls births");
        System.out.println("There were " + boys + " boys birth");
        
         System.out.println("There were " + totalNames + " total Names");
        System.out.println("There were " + girlsNames + " girls names");
        System.out.println("There were " + boysNames + " boys names");
    }
    
    int getRank(int year, String name, String gender) {
    
        FileResource fr = new FileResource("..\\Resources\\us_babynames\\us_babynames_by_year\\yob"+ year+".csv");
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
        FileResource fr = new FileResource("..\\Resources\\us_babynames\\us_babynames_by_year\\yob"+ year+".csv");
        CSVParser parser = fr.getCSVParser(false);
        
        int count = 0 ;
        
        for(CSVRecord record : parser){
            
            String currentGender = record.get(1);
            
            if(currentGender.equals(gender)){
                count++;
            }
            
            
            if(count == rank){
                return record.get(0);
            }
            
        }
        
        return "No Name";
    }
    
    void whatIsNameInYear(String name, int year, int newYear, String gender) {
        
        
        int rank = getRank(year, name, gender); 
        
        String newName = getName(newYear, rank, gender);
        
        System.out.println( name + " born in " + year + " would be " + newName + " if born in " + newYear);
    
    }
    
    int yearOfHighestRank(String name, String gender) {
        
        DirectoryResource dr = new DirectoryResource();
        int highestRank = -1;
        int highestRankYear =  -1;

        for(File f: dr.selectedFiles()){
            int currentYear = Integer.parseInt(f.getName().substring(3,7));
            int rank = getRank(currentYear, name, gender);
            
            if(rank == -1) {
                continue;
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
    
    int getTotalBirthsRankedHigher(int year, String name, String gender) {
        
        FileResource fr = new FileResource("..\\Resources\\us_babynames\\us_babynames_by_year\\yob"+ year+".csv");
        CSVParser parser = fr.getCSVParser(false);
        int totalBirth = 0;
        
        for(CSVRecord record: parser){
        
            String currentName = record.get(0);
            String currentGender = record.get(1);

            
            if(currentGender.equals(gender)){
                
                if(currentName.equals(name)){
                    break;
                }
                
                totalBirth += Integer.parseInt(record.get(2));
            }
           
        }
        
        return totalBirth;
    }
    
    
    void testTotalBirth() {
        
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false);
        
        totalBirths(parser);
    
    }
}
