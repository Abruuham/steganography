/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steganography;

/**
 *
 * @author abrahamcalvillo
 * 
 * 
 */

import java.math.*;
public class ASCII {
    
    
    public static void printBooleanArray(boolean[] bits){
        
        for (int i =0; i< bits.length;i++){
            // print a 1 for true and 0 for false
            if(bits[i]){
                System.out.print("1");
                
            }
            else
            {
                System.out.print("0");
            }
           
        }
        
        System.out.println();
        
    }
    
    public static boolean[] encode(String str){
        
        boolean[] booleanValues = new boolean[(7*str.length()) + 1 ];
        
        int count ;
        count = booleanValues.length -1;
        //System.out.println("This is the size of count:" + count);
        
        if(str == null){
            return null;
        }
        
        for(int j= str.length() - 1; j >= 0;j--){
            char character = str.charAt(j);
            int ascii = (int) character;
            for(int i = count ; i >= count - 7 ;i--){
                
                //System.out.println("This is i:" + i);
                 int boolVal = ascii % 2;
                    
                    if(boolVal == 1 ){
                        booleanValues[i] = true;
                        //System.out.print("true");
                        
                    }
                    else if (boolVal == 0 ){
                        booleanValues[i] = false;
                       
                    }
                     ascii = ascii / 2;
                }
            //System.out.println();
            
            count -=7;
            //System.out.println("This is the size of count:" + count);
           
        }
            
        return booleanValues;
     
    }
    
    
    
    
    public static String decode(boolean[] bits){
        int num = 0;
        String decoded ="";
        
        int power = 7;
        
        for(int i = 0; i < bits.length; i++){
            
            if(bits[i]){
                num += (int) Math.pow(2,power);
                power--;
            }
            else
            { power--;}
            
            if(power == 0){
                decoded += (char) (num/2);
                num = 0;
                power = 7;
                
            }
            
        }
        
        return decoded;
    }
    
    
    
    public static void main(String[] args){
        // convert command line argument to bits
       
       boolean[] bits = encode("A");
       
       
       
        
        
       //printBooleanArray(bits);
        
        
        //conver the bits back to a string
        // uncomment these lines once you have
        // implemented decode
        String s = decode(bits);
        System.out.println(s);
       
    }
    
}
