package hugeint;

//import libraries
import java.util.Random;
import java.lang.Math;


public class HugeInteger {
	
	private static final boolean True = false;
	private boolean negNum;//define positive negative identifier True is positive False  is negative
	public byte[] digits; //define array

	
	public HugeInteger(String val){
		
		if(val.length() == 0) {
			throw new NumberFormatException("Huge Integer length is not valid");//throws exception if integer length is 0
			
		}
		
		
		if(val.charAt(0) == '-'){//check first character of string for negative sign
			negNum = false;//stores '-' sign 
			digits = new byte[val.length()-1];//takes into account negative sign of number 		
												//and subtracts it from array
			
				
			for(int i=1; i< val.length(); i++) { //for loop that goes through entire length of number but skips over negative sign
					if(val.charAt(i) >='0' && val.charAt(i)<='9')
						digits[i-1] = (byte)(val.charAt(i)-48);//equates digits array to number not including negative sign
				
					else {
						
						throw new IllegalArgumentException("Huge Integer value is not valid ");
					}	
		
			}
		}										
	
		else {//else statement for positive numbers 
			negNum = true;// stores '+' sign 
			digits = new byte[val.length()];//define digits as length of string
			
			
			for(int i=0; i< val.length(); i++) //for loop that goes through entire length of number 
				if(val.charAt(i) >='0' && val.charAt(i)<='9')//if statement to see if inputs are valid
					digits[i] = (byte) (val.charAt(i)-48);//equates digits array to number 
		
				else {
					throw new IllegalArgumentException("Huge Integer value is not valid ");//if not throw exception
				}
		}
		
	}
	
	public HugeInteger(int n){
		if(n<=0) {
			throw new IllegalArgumentException("n has to be 1 or greater");//throws exception if n is less than 1
		}
		
		
		 int maxNum = 1;//defined for negNum
		 int minNum = -1;//defined for negNum
		
	
		digits = new byte[n];//define new array with length of integer
		  
				
		  maxNum = 9;//defined for first integer digit ensures no leading zeros
		  minNum = 1;//defined for first integer digit ensures no leading zeros
	
		  digits[0] = 	(byte) (Math.random()*((maxNum-minNum +1)+minNum));//generates the random number
	
		  minNum = 0;//defined for all other digits of the integer
	
	for(int i =1; i<n;i++) //start forloop at 1 as first digit has already been accounted for
		digits[i] = (byte) (Math.random()*((maxNum-minNum +1)+minNum));//fill all other array elements with random number
		
	
	}
				
	public HugeInteger add(HugeInteger h) {
		
		HugeInteger larger;
		HugeInteger smaller;
		HugeInteger numSum = new HugeInteger("0"); //define array for combined values of HugeInteger type
		
		
		 if(this.compareMagnitude(h) == -1) {
			larger = h;
			smaller = this;
		}
		
		else {//takes into account if compareMagniude is equal to 1 and 0 ; this is larger value
			larger = this;
			smaller = h;
		}
		
		if(this.negNum == h.negNum) {// both positive and negative values addition
			numSum.digits= numSum.sumMag(larger.digits, smaller.digits); //call sumMag function
			numSum.negNum = this.negNum; //make sure sign of final answer is the correct one(+/-)
		}
		 
		if(this.negNum!= h.negNum) {//if values are different in sign
			if(this.compareMagnitude(h) == 0) {//call compare function as when magnitudes are equal it returns 0
				return numSum;
			}
			numSum.negNum = larger.negNum;//use sign (+/-) of larger value being added for final answer
			numSum.digits = numSum.diffMag(larger.digits, smaller.digits); //call diffMag function
		}
		   
		 return numSum;
		
	}
	

	public String toString(  )
	{
		String larger = new String();
			
		if(negNum == false) {
			larger = ("-");
			
		}
					
		for(int i = 0; i<digits.length; i++) {//nested for loop that goes through
		
					larger += digits[i]; //puts array values in a string 
		}
		
	
		return larger;
	}


	public HugeInteger subtract(HugeInteger h) { 
		
		HugeInteger larger;
		HugeInteger smaller;
 		HugeInteger numDiff = new HugeInteger("0"); //define array for combined values of HugeInteger type
		
	    
		
		 if(this.compareMagnitude(h) == -1) {
			 larger = h;
			 smaller = this;
			 }
		
		else {//takes into account if compareMagniude is equal to 1 and 0 ; this is larger value
			larger = this;
			smaller = h;
		}
		
		 
		if(this.negNum == h.negNum) {// both positive and negative values addition
			if(this.compareMagnitude(h) == 0) {//call compare function as when magnitudes are equal it returns 0
				return numDiff;
			}
			numDiff.digits= numDiff.diffMag(larger.digits, smaller.digits); //call diffMag function
			if(this ==larger) {
				numDiff.negNum = this.negNum;
			}
			else if(h == larger){
				numDiff.negNum  = !this.negNum; //sign is the opposite of smaller magnitude integer : this
				
			}
				
		}
		 
		if(this.negNum!= h.negNum) {//if values are different in sign
			numDiff.negNum = this.negNum;//use sign (+/-) of larger value being added for final answer
			numDiff.digits = numDiff.sumMag(larger.digits, smaller.digits); //call diffMag function
		}
		   
		 return numDiff;
		
	}
	
    
	public HugeInteger multiply(HugeInteger h) { 
    	
   	 HugeInteger numSum = new HugeInteger("0"); //define array for combined values of HugeInteger type
   	 numSum.digits= new byte[this.digits.length]; //define numSum array as size of larger integer
   	
   	if(this.toString().equals("0") || h.toString().equals("0"))//return 0 if one of two integers is 0
       	return new HugeInteger("0");
   	

   	if(h.toString().equals("-1") && this.negNum == true) {//return negative of intger that is not the -1
   		 	this.negNum = false;
   		 
   		 	return new HugeInteger( this.toString());
   	}
   	
   	else if(h.toString().equals("-1")&& this.negNum == false){//return positive intger that is not the 1
   			this.negNum = true;
				return new HugeInteger(this.toString());
		 }
   	if(h.toString().equals("1")&& this.negNum == false) {//return negative integer when integer is negative and other integer is 1
  		 
   			return new HugeInteger(this.toString());
   	}
  		else if(h.toString().equals("1")&& this.negNum == true){// return integer if other integer is 1 and integer is positive
  				
  				return new HugeInteger(this.toString());
  		 }
   	
   	if(this.toString().equals("-1") && h.negNum == true) {//return negative integer if integer is positive and other integer is -1
  		 		h.negNum = false;
  		 
  		 		return new HugeInteger( h.toString());
  	}
  	
   	else if(this.toString().equals("-1")&& h.negNum == false){//return positive integer if integer is negative and other integer is -1
   			h.negNum = true;
				
				return new HugeInteger(h.toString());
		 }
   	if(this.toString().equals("1")&& h.negNum == false) { //return integer if it is negative and other integer is -1
  		
  		return new HugeInteger(h.toString());
  	}
 		else if(this.toString().equals("1")&& h.negNum == true){//return integer if it is positive and other integer is 1
 				
 				return new HugeInteger(h.toString());
 		}
   	
	return numSum;
 	
   	    	
       	
}
        	
        
    public int compareTo(HugeInteger h) {  
    	
    	int answer = compareMagnitude(h); //define variable as compareMagnitude function
    	  
 		  	if(this.negNum == true && h.negNum == false) {//return l as this is larger
 		  		return 1;
 		  	}
    	
 		  	else if(this.negNum == false && h.negNum == true) {//return -1 as h is larger
 		  		return -1;
 		  	}
    		
 		  	if(this.negNum == false && h.negNum == false){//both negative but this has larger magnitude return -1 as h is bigger 
      	 		if(answer == 1) {
      	 			return -1;
      	 			}
      	 		else if(answer == -1) {//both negative but h has larger magnitude return 1 as this is bigger 
         	 		return 1;
         	 		}	
      	 		else if(answer ==0) {//same value return 0
      	 			return 0;
     	 		}	
 			 
 		
 			 }
 		 
		
 		  if(this.negNum == true && h.negNum == true){//compareMagnitude checks to see which value is larger if both positive
       	 			return answer;
       	 			}
		
 	return answer;
       	 		
 		  
 }
    	
    public int compareMagnitude(HugeInteger h){//returns 1 if this is larger than h and -1 if this is smaller than h return 0 when magnitudes of integers are identical
    	

    	if( this.digits.length > h.digits.length) {//if statement to see which integer has more digits
   		 
   		 return 1;
   	 	}
   	 else if(this.digits.length < h.digits.length) {
   		 return -1;
   		 
   	 }
   		 
   	 else if(this.digits.length == h.digits.length) {//if values are equal go through values and see the size of each element and return 1 if this is larger and -1 if h is larger
   		
   			
   			for(int i=0; i<this.digits.length;i++) {
       	 		
   	 		
   				if(this.digits[i]> h.digits[i]) {
       	 			return 1;
       	 		}
       	 	
       	 		else if(h.digits[i]> this.digits[i]) {
       	 			return -1;
       	 		}		
   			}	
   		
   	 }
		return 0; //return 0 if the same 
   	 
   	 }
   		  
    
    public byte[] sumMag  (byte[] a, byte[] b) 
    {
	
    	
     HugeInteger numSum = new HugeInteger("0"); //define array for combined values of HugeInteger type
	 numSum.digits= new byte[a.length ]; //define numSum array as size of larger integer
	
	  
	 
	 for(int i = b.length-1,  j=a.length-1; i >= 0;i--,j--) {//add smaller number with larger one
		  
		  numSum.digits[j] =   (byte) (b[i] + a[j]);//put addition in the newly created array
		
	  }
	 
	  
	  for(int j = a.length-b.length-1; j >=0  ;j--) {//drops down all other integers in larger number
		  numSum.digits[j] = a[j];//add leftover numbers from larger integer into the final sum in array
	  }		  
	  
	 
		
	  numSum.digits = this.carryOver(numSum.digits);
	  
	 
	 return numSum.digits;
	
}   
    
    public byte[] diffMag  (byte[] a, byte[] b) {//a is larger number b is smaller
    	 
    	HugeInteger numSum = new HugeInteger("0"); //define array for combined values of HugeInteger type
    	 numSum.digits= new byte[a.length ]; //define numSum array by one element to account for carry overs
    	
    	 
    	 
    	 for(int i = b.length-1,  j=a.length-1; i >= 0;i--,j--) {//subtract smaller number with larger one
    		  
    		  numSum.digits[j] =   (byte) (a[j] - b[i]);//put subtraction in the newly created array: a is larger b is smaller
    		
    	  }
    	 
    	  
    	  for(int j = a.length-b.length-1; j >=0  ;j--) {//drops down all other integers in larger number
    		  numSum.digits[j] = a[j];//add leftover numbers from larger integer into the final subtraction in array
    	  }		  
    	  
    	 
    		
    	  for(int i=numSum.digits.length-1;i>=1;i--){//goes through array with subtracted integers backwards 
    		  int n = numSum.digits[i]; //denotes array element value
    		  if(n<0) {//if n is a negative value
    			  		numSum.digits[i] = (byte) (n+10);//add 10 for borrowing from larger digit  
    			  		numSum.digits[i-1] = (byte) (numSum.digits[i-1] -1);//takes into account of the borrowing
    		  }
    	  }
    	  int leading = 0; //define variable to count leading zeros
    	  //shrinking array by taking away leading zeros using for loop
    	  for(int i = 0 ; i<=numSum.digits.length-1; i++) {
    		  if(numSum.digits[i]!= 0) {
    			  leading = i;//number of leading zeros
    			  break;//leave for loop after first non zero is encountered
    	  
    			  
    		  }
    	  }
    	  byte[] temp = new byte [numSum.digits.length - leading ];//add element to temp array which was resized to fit subtracted value total which is why its starting at leading
		  for(int i=leading; i<=numSum.digits.length-1;i++) {//go through array
			  temp[i-leading] = numSum.digits[i]; //equates temp array to numSum --> offsets digits to have extra space for potentially new carry over
		  }
		  numSum.digits = temp; //equate temp array to numSum array
    	 
    	 return numSum.digits;
    	
    }
    
    
    public byte[] carryOver(byte[] a) {
    	
    	for(int i=a.length-1;i>=1;i--){//goes through array with added integers
  		  int n = a[i]; //denotes array element value
  		 a[i] = (byte) (n%10);//checks for remainder 
  		  a[i-1] = (byte) (a[i-1] + n/10);//calculates carry on value
  		  
  	  }
  	  
  	  int n = a[0]; //denotes array element value
  	 
  	  if( n>=10){
  		  byte[] temp = new byte [a.length+1];//add element to array
  		  for(int i=0; i<=a.length-1;i++) {//go through array
  			  temp[i+1] = a[i]; //equates temp array to numSum --> offsets digits to have extra space for potentially new carry over
  		  }
  		 a= temp; //equate temp array to numSum array
  		 a[1] = (byte) (n%10);//checks for remainder 
  		  a[0] = (byte) (n/10);//gets carry over
  	  
  	  }
  	  
  	return a;
    	
    }
    
    public HugeInteger multi( HugeInteger b1) {//multiply function for every number besides 0 and 1,-1
    	
    	byte[] a = this.digits;//define byte array
    	byte[] b = b1.digits; //define second byte array
		
    	byte[]  finalProduct = new byte[a.length+b.length +1]; //finalProduct array is one more than the length of both arrays in case theres another digit
  	  for(int i = 0; i< finalProduct.length;i++){//define every value in finalProduct as 0
  			finalProduct[i] = 0;
  	  }
  	byte [] partialProduct = new byte[b.length +1]; //define byte array as one more than length of b
  		
  		for( int i= a.length-1; i>= 0;i--) {//go through values from left to right
  			byte carry = 0;//define carry
  			byte product; //define product
  			int j;//define j
  			for(j= b.length-1;j>=0;j--) {//go through b array values
  				product = (byte) (a[i] * b[j] + carry); //define product as multiplication of each element between b and a array 
  				carry  = (byte) (product/10); // define carry as product/10 
  				partialProduct[j+1]=(byte) (product%10);	//correctly shift partial product so numbers are properly placed			
  			}
  			partialProduct[j+1] = carry; //redefine carry
  			int m = a.length-1 - i; //variable to correctly shift numbers so they are correctly placed in finalProduct
  			byte carry2 = 0;//define a carry variable
  			byte sum;//define the sum
  			
  			int kk = finalProduct.length-1 - m; //variable defined for the shifting of the values in finalProduct
  			for(int k = partialProduct.length -1 ; k>=0; k--,kk--){//for loop goes through partial product
  				sum = (byte) (finalProduct[kk] + partialProduct[k] +carry2); // add partial product to finalProduct as well as the new carry
  				finalProduct[kk] = (byte) (sum%10); //take epsilon to find finalProduct values
  				carry2 = (byte) (sum/10); //define new carry same way as old one
  			}
  			finalProduct[kk] = carry2; //equate carry to finalproduct
  		}
  		
					
		for(int i = 0; i<finalProduct.length; i++) {//nested for loop that goes through finalProduct and correctly converts values to integers
		
					finalProduct[i] += '0';  
		}
		
		int leading = 0; //define variable to count leading zeros
	  	  //shrinking array by taking away leading zeros using for loop
	  	  for(int i = 0 ; i<=finalProduct.length-1; i++) {
	  		  if(finalProduct[i]!= 0) {
	  			  //number of leading zeros
	  			  break;//leave for loop after first non zero is encountered
	  	  
	  			  }
	  		  	  leading++;//increment leading
	  	  }	
	  	  
	  	byte[] temp = new byte [finalProduct.length - leading ];//add element to temp array which was resized to fit multiplied value total which is why its starting at leading
		  for(int i=leading+1; i<=finalProduct.length-1;i++) {//go through finalProduct array using proper conditions
			  temp[i-leading] = finalProduct[i]; //equates temp array to finalProduct  to ensure finalProduct is correct length with no added zeros
		  }
//		  String ans = "";
//		  for(int i=0; i<temp.length;i++) {
//			  ans += (int) (temp[i]);
//			  System.out.println(ans);
//		  }
		  finalProduct = temp; //equate temp array to numSum array
		  String temp1 = new String(temp);//make temp a string
		  
		  temp1 = temp1.substring(1); //delete space caused by creating string
		  
	  	int cou = 0;//define count variable
		  for(int i = 0 ; i<=temp1.length(); i++) {//go through temp1 string
	  		  if(temp1.charAt(i)!= '0') { //take out any leading zeros 

	  			  //number of leading zeros
	  			  break;//leave for loop after first non zero is encountered
	  	  
	  			  }
	  		  	  cou++; //increment count
	  	  }	
	  	  
		  temp1 = temp1.substring(cou); //delete ay remaining zeros in string

	  	
	  	  if(this.negNum == false && b1.negNum == false || this.negNum == true && b1.negNum == true) {//if  negNum of integers is the same return positive value

  		  		HugeInteger h = new HugeInteger(temp1);
  		  		return h;

  	  }
	  	  
	  	  else if(this.negNum == false && b1.negNum == true || this.negNum == true && b1.negNum == false) {//if signs of integers are different return negative value
	  		temp1 = "-" + temp1; //add negative sign to temp
	  		HugeInteger h = new HugeInteger(temp1);
	  		return h;

	  	  }
		
  		HugeInteger h = new HugeInteger(temp1); //return huge integer value

	  		return h;	 
    	
    }
    
}
