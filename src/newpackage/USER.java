package newpackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
public class USER {
      private String name;
   private  String role;
    private String password;
    boolean isFound= false;
   String filename="users.txt";
    
   void WriteToFIle ()
   {
       try {
           FileWriter awriter = new FileWriter ("Filename.txt");
           awriter.write("Sahi hai bhai");
           awriter.close();
           System.out.println("Succesfull");
       } catch (IOException ex) {
           System.out.println("File wasnt read");
           Logger.getLogger(USER.class.getName()).log(Level.SEVERE, null, ex);
       }
       
   }
     public boolean isRegistered(String newName, String newPassword, String newrole) 
           
    {
      boolean isRegistered;
      /*
      name = newName;
      //role= newrole;
      password= newPassword;
      role = newrole;
*/
      try{
          //   writer = new FileWriter (filename, true)   ;
          try (FileWriter writer = new FileWriter (filename,true)) {
              //   writer = new FileWriter (filename, true)   ;
              writer.write( newName + " " + newPassword+ " " +newrole + "\n");
              // writer.write("##"+System.getProperty("line.seperator"));
              isRegistered = true;
              writer.flush();
          }
      }
      catch(IOException ioe)
          {
              isRegistered = false;
          }
      return isRegistered;
      }
    boolean ReadFromFile(String username, String newpassword, String newrole)
    {
     
     //   File myObj = new File("filename.txt");
       name = username;
       password = newpassword;
       role = newrole;
       try{
     FileReader reader=new FileReader(filename);
  //      FileReader readerr=new FileReader("C:\\Users\\devsu\\Desktop\\MyFirstApplication\\login.txt");
      BufferedReader bin=new BufferedReader(reader);
   //     BufferedReader binn=new BufferedReader(reader);
   
   String record;
      record=new String();
     // pass = new String();
      while((record=bin.readLine())!=null)
      {
          if((name + " "+ password+ " "+  role).contentEquals(record) )
          {
              isFound=true;
          }
       }
   //   binn.close(); 
     // binn=null;
          bin.close();
          bin=null;
      }
  
      catch(IOException ioe)
              {
              isFound = false;
              }
      return isFound;
    }
}
     



      
  


    

  




   
    
  

