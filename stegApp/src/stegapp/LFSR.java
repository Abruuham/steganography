/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stegapp;

/**
 *
 * @author abrahamcalvillo
 */
public class LFSR {
    
    private static boolean[] lfsr;
    private static int tap;
    private static int n;
    
    public  LFSR(String seed, int tap)
            
    {
        lfsr = new boolean[seed.length()];
        
        for(int i =0; i < seed.length(); i++){
            if(seed.charAt(i) == 48){
                lfsr[i] = false;
            }
            else{
                lfsr[i] = true;
            }
                
            
        }
        n = seed.length();
        this.tap = tap;
        
        
        /*
     lfsr = new boolean[seed.length()];
     n = seed.length();

        
        this.tap = tap;
        //System.out.println(tap);
        
        for(int i =0; i < seed.length(); i++){
            if(seed.charAt(i) == 48){
                lfsr[i] = false;
            }
            else{
                lfsr[i] = true;
            }
                
            
        }
        
        */
    }
    
    
    
    
    
    public static boolean step(){
        
        int tap;
        boolean bit;
        
        if(lfsr[0] == false){
            bit = false;
            
        }
        else bit = true;
        
        if(lfsr[n - LFSR.tap -1] == false) tap = 0;
        else tap = 1;
        
        if(tap == 1){
        bit ^= true;
        }
        else if(tap == 0){
        bit ^= false;    
        
        }
        boolean f;
        
        if(bit == false) f = false;
        else f = true;
        for(int i =0; i< n-1; i++){
            lfsr[i] = lfsr[i+1];
        }
        lfsr[lfsr.length -1] = f;
        
        
        
        return bit;
        
        
        
        
        
        
        
        /*
        boolean newBit = lfsr[0] ^ lfsr[(n) - (tap)];
        
        for(int i =0; i<lfsr.length-1; i++){
            lfsr[i] = lfsr[i+1];
        }
        lfsr[lfsr.length-1] = newBit;
        
        
        
        return newBit != false;*/
    }
    
    public static String string(){
        
        String representation = "";
        
        for(int i=0; i<lfsr.length; i++){
            representation += lfsr[i] == false ?0:1;
            
        }
        
        return representation;
    }
    
    
    
    
    public static void main(String[] args){
        
        LFSR left = new LFSR("01101000010", 8);
        for(int i =0; i<10; i++){
            boolean bit = left.step();
            System.out.println(left.string() + " " + bit);
        }
        
    }
    
}
