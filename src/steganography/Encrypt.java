/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




package steganography;

/**
 *
 * @author abrahamcalvillo
 */
public class Encrypt {
    
    
    // print an array of booleans as 1s and 0s
private static void printBooleanArray(boolean[] bits) {
    for (int i = 0; i < bits.length; i++) {
        // print a 1 for true or 0 for false
        if (bits[i]) System.out.print("1");
        else         System.out.print("0");
    }

    System.out.println(); // add a newline
}
    
    
    
    public static boolean[] encrypt(boolean[] bits, String seed, int tap)
    {
        
        boolean[] newBools = new boolean[bits.length];
        
        
        LFSR newBit = new LFSR(seed, tap);
        
        for(int i=0; i<bits.length; i++){
            
            boolean bit = newBit.step();
            newBools[i] = bit;
        }
        
       
        for(int j = 0; j < bits.length; j++){
            
            newBools[j] = newBools[j] ^ bits[j];
            
        }
        
       boolean nums[] = new boolean[seed.length()];
        for(int i =0; i< seed.length(); i++){
            int holder = Character.getNumericValue(seed.charAt(i));
            if(holder == 1){
                nums[i] = true;
            }
            else{
                nums[i] = false;
            }
            //System.out.println(nums[i]);
        }
     
        
     for(int i = 0; i < bits.length; i++){
            
            newBools[i] = bits[i] ^ nums[i] ;
            
        }
        
         
        
        return newBools;
    }
    
    
    
    
    public static void main(String[] args)
    {
        
        
        //sample array for testing encrypt()
        boolean[] test = {true, true, true, true, true};
        
        
        //sample seed and tap position
        String seed = "01101000010";
        int tap = 8;
        
        
        boolean[] encrypted = encrypt(test, seed, tap);
        System.out.print("test encrypted: ");
        printBooleanArray(encrypted);
        boolean[] decrypted = encrypt(encrypted, seed, tap);
        System.out.print("test decrypted: ");
        printBooleanArray(decrypted);
        
        
        
    }
    
    
    
}
