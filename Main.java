import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {

   
   /**
    * The driver for the MyHashMap method
    * reads in a file and writes the coded file and the tree codes
    * @param args The command line arguments
    */
   public static void  main(String[] args) {
      testMyHashTable();
   }
   
   public static void testMyHashTable() {
      MyHashTable<String, String> temp = new MyHashTable<String, String>(2<<15);
      temp.put("yes" , "please");
      temp.put("No", "Bugs");
      System.out.println(temp.get("yes") + temp.get("No"));
      temp.put("yes", "What do you mean");
      System.out.println(temp);
   }
}