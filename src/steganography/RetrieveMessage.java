/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steganography;

import java.awt.Color;

/**
 *
 * @author abrahamcalvillo
 */




public class RetrieveMessage {
    
    static ImageData img = new ImageData();
    static int holder[][];
    
    
    
    public static String intToBinary(int n){
    String s = "";
    while (n > 0)
    {
        s =  ( (n % 2 ) == 0 ? "0" : "1") +s;
        n = n / 2;
    }
    return s;
}
    
  
    
    public static boolean checkLast(String num){
        
        boolean bool;
        
        bool = num.charAt(num.length() - 1) != 48;
        
        
        return bool;
    }
   
    
 
    
    public static void main(String[] args){
    
    holder = img.load("stegosaur_embedded.png");
    Picture pic = new Picture("stegosaur_embedded.png");

    
    boolean[] enc = new boolean[pic.height() * pic.width()] ;
    
    int count = 0;
    for(int row =0; row< holder.length; row++){
        for(int col=0; col < holder[row].length;col++){
            
            int colorr = holder[row][col];
            int coll = (colorr & 0xFF);
            
            String nums ;
            
            nums = intToBinary(coll);
            
            boolean addTo;
            
            if(coll > 0 ){
                addTo = checkLast(nums);
                enc[count] = addTo;
            }
            else{
                addTo = false;
                enc[count] = addTo;
            }
            
           count+=1;
            
        }
    }
    
    
    //0001010101001
    
    String s = ASCII.decode(enc);
    int nullOp = s.indexOf('\0');
    
    if(nullOp > 0){
       
       System.out.print(s.substring(0,nullOp));
   
   }
    else{
       System.out.print(s);
    }
    
}


}