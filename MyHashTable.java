

import java.util.*;

public class MyHashTable<K,V> {
   int myEntryCount;
   int myBuckets;
   int myCapacity;
   ArrayList<keyData> myKeys;
   ArrayList<V> myValues;
   
   
   MyHashTable(int capacity) {
      myBuckets = capacity;
      myKeys = new ArrayList<keyData>(Collections.nCopies(capacity, null));
      myValues = new ArrayList<V>(Collections.nCopies(capacity, null));
      myEntryCount = 0;
      myBuckets = 0;
      myCapacity = capacity;
   }
   
   void put(K searchKey, V newValue) {
      int pos = hash(searchKey) % myCapacity;
      int cycle = 0;
         
         if(!containsKey(searchKey)) {
            while( cycle < myCapacity && myValues.get(pos) != null) {
               pos = (pos + 1)%myCapacity;
               cycle++;
            }
            myKeys.set(myBuckets, new keyData(searchKey, pos));
            myBuckets++;
            myValues.set(pos, newValue);  
         }else {
            //this part needs work
            myValues.set(getKeyData(searchKey).myValue,  newValue);
         }
          
   }
   
   V get(K searchKey) {
      int pos = hash(searchKey) % myCapacity;
      int cycle = 0;
      keyData myNode = null;
      V temp;
      for(int i = 0; i < myBuckets; i++) {
         if((myKeys.get(i).myKey).equals(searchKey) || myBuckets == 0){
            myNode = myKeys.get(i);
            break;
         }
      }
      temp = myValues.get(myNode.myValue);
      return temp;
   }
   
   public boolean containsKey(K searchKey) {
      boolean flag = false;
         for(int i = 0; i < myKeys.size(); i++) {
            if(myKeys.get(i) != null && myKeys.get(i).myKey.equals(searchKey)){
               flag = true;
               break;
            }
         }
      return flag;
   }
   
   public String toString() {
      StringBuilder temp = new StringBuilder();
      int i = 0;
      temp.append("{" + myKeys.get(i).myKey.toString() + "=" + myValues.get(myKeys.get(i).myValue));
      
      for(i = 1; i < myBuckets; i++) {
         temp.append(", " +  myKeys.get(i).myKey.toString() + "=" + myValues.get(myKeys.get(i).myValue));
      }
      temp.append("}");
      return temp.toString();
   }
  
   private int hash(K key) {
      return key.hashCode();
   }
   
   private keyData getKeyData(K searchKey) {
      keyData temp = null;
      for(int i = 0; i < myBuckets; i++) {
         if(myKeys.get(i).myKey.equals(searchKey)){
            temp = myKeys.get(i);
            break;
         }
      }
      
      return temp;
   }
   //OOOHHH make a private class for Key values.  Each key can have multiple values mapped to it,
   //so each key object needs to keep track of what its pointing to
   //the tostring should print like the map normally does
   
   private class keyData {
      K myKey;
      int myValue;
      
      keyData(K theKey) {
         myKey = theKey;
      }
      keyData(K theKey, int theValue) {
         myKey = theKey;
         myValue = theValue;
      }
      
      public void setValue(int theValue) {
         myValue = theValue;
      }
      
      
   }
}