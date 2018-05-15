//sources were oracle docs, and 2 stackoverflow questions about initializing an array with null values
// and a null pointer exception from adding to an array

import java.util.*;

public class MyHashTable<K,V> {
   int myEntryCount;
   int myBuckets;
   int myCapacity;
   ArrayList<ValueData> myTable;
   
   
   MyHashTable(int capacity) {
      myTable = new ArrayList<ValueData>(Collections.nCopies(capacity, null));

      myEntryCount = 0;
      myBuckets = 0;
      myCapacity = capacity;
   }
   
   void put(K searchKey, V newValue) {
      int pos = hash(searchKey) % myCapacity;
      int cycle = 0;
         while( cycle < myCapacity && myTable.get(pos) != null) {
            if(myTable.get(pos).myKey.equals(searchKey)){
               myBuckets--;
               break;
            }
            pos = (pos + 1)%myCapacity;
            cycle++;
         }
         myBuckets++;
         myTable.set(pos, new ValueData(newValue, searchKey));  
   }
   
   V get(K searchKey) {
      int pos = hash(searchKey) % myCapacity;
      int cycle = 0;
      V temp;
      while(cycle < myCapacity) {
         if(myTable.get(pos).myKey.equals(searchKey)) {
            break;
         } else {
            pos = (pos + 1)% myCapacity;
         }
         cycle++;
      }
      temp = myTable.get(pos).myValue;
      return temp;
   }
   
   public boolean containsKey(K searchKey) {
      int pos = hash(searchKey) % myCapacity;
      int cycle = 0;
      boolean found = false;
      while(cycle < myCapacity) {
         if(myTable.get(pos) == null) {
            break;
         }else if(myTable.get(pos).myKey.equals(searchKey)) {
            found = true;
            break;
         } else {
            pos = (pos + 1)% myCapacity;
         }
         cycle++;
      }
      return found;
   }
   
   public HashSet<K> keySet() {
      HashSet<K> temp = new HashSet<K>();
      for(int i = 0; i < myCapacity; i++) {
         if(myTable.get(i) != null) {
            temp.add(myTable.get(i).myKey);
         }
      }
      return temp;
   }
   
   
   void stats() {
      System.out.println( "Number of Entries: " + (myBuckets));
      System.out.println( "Number of Buckets : " + myCapacity);
      ArrayList<Integer> histy = new ArrayList<Integer>(myCapacity);
      int bigProbe = 1;
      double averageProbe = 0;
      for(int i = 0; i < myCapacity; i++) {
         if(myTable.get(i) != null) {
            int temp = i - hash(myTable.get(i).myKey);
            if(temp < 0) {
               temp = temp + myCapacity;
            }
            if(temp > bigProbe) {
               bigProbe = temp;
            }
            averageProbe += ((double) temp) / myBuckets;
            histy.add(temp);
            
         }
      }
      
      int[] probeCount = new int[bigProbe];
      for(int i = 0; i < histy.size(); i++) {
         probeCount[histy.get(i)]++;
      }
      
      System.out.println("Histogram of Probes: " + Arrays.toString(probeCount));
      double fill = ((double)myBuckets) / myCapacity;
      System.out.println("Fill Percentage: " + fill);
      System.out.println("Max Linear Probe: " + bigProbe);
      System.out.println("Average Linear Probe: " + averageProbe);
   
   }
   public String toString() {
      StringBuilder temp = new StringBuilder();
      temp.append("{");
      int i = 0;
      int bucketCount = 0;
      for( i = 0; i < myCapacity; i++) {
         if(myTable.get(i) != null) {
            if(bucketCount == 0) {
               temp.append(myTable.get(i).myKey + "=" + myTable.get(i).myValue);
               
            } else {
               temp.append(", " + myTable.get(i).myKey + "=" + myTable.get(i).myValue);
            }
          bucketCount++;
         }
      }
      temp.append("}");      
      return temp.toString();
   }
   
   
  
  
  
   private int hash(K key) {
      return key.hashCode() % myCapacity;
   }
   /*
   private keyData getKeyData(K searchKey) {
      keyData temp = null;
      for(int i = 0; i < myBuckets; i++) {
         if(myKeys.get(i).myKey.equals(searchKey)){
            temp = myKeys.get(i);
            break;
         }
      }
      
      return temp;
   }*/
   //OOOHHH make a private class for Key values.  Each key can have multiple values mapped to it,
   //so each key object needs to keep track of what its pointing to
   //the tostring should print like the map normally does
   
   private class ValueData {
      V myValue;
      K myKey;
      
      ValueData(V theValue,K theKey) {
         myValue = theValue;
         myKey = theKey;
      }
      
   }
}