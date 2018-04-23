
import java.security.MessageDigest;

public class HashFunction {
	
    public static String getSHA256(String str) {
       
    	//return SHA_256(str);
        
    	return mySHA_256(str);
        
    }


    public static String mySHA_256(String s){

    	byte[] bytes = s.getBytes();
  	  	StringBuilder binary = new StringBuilder();
  	  	
  	  	for (byte b : bytes)
  	  	{
  	  		//most of time hash is calculated time is change
  	     int val = b*99;	// * (int)System.currentTimeMillis();	
  	     for (int i = 0; i < 8; i++)
  	     {
  	    	 //256 bits === 8 bits*32words or 512 bits === 8 bits*64words
  	    	  if(binary.length() != 512)
  	    		  {	
  	    		  	//make compliment of value and perform OR opration 
  	    		  	//binary.append((~val & 128) == 0 ? 0 : 1);  
	  	    		  
  	    		  	binary.append((~val & 128) == 0 ? 0 : 1);
	  	    		//shift one bit right
  	    		  	val<<=1;
  	    		  }
  	     }
  	  }

  	  //String[] hex={"f","e","d","c","b","a","9","8","7","6","5","4","3","2","1","0"};
  	  String hexdecnum="";
  	  String[] hex={"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};
  	  int[] weight = {8,4,2,1};
  	  int inCont = 0;    
  	  int rem,x = 0;
  	  for(int i=0;i<binary.length();i++)
  	   {
  	    
  		  rem = binary.charAt(i);
  		  inCont++;
  	    
  		  if(rem == 49){
  	    	if(inCont==1){	x+=weight[0];	}
  	    	else if (inCont==2){	x+=weight[1];	}
  	    	else if (inCont==3){	x+=weight[2];	}
  	    	else if (inCont==4){	x+=weight[3];	}
  	    	
  	    	 if(i%4 == 0 && i!=0)
  	  		  {
  	  			  hexdecnum += hex[x];
  	  			  inCont=0;
  	  	    	  x = 0;
  	  		  }
  	  		  	else if(i>=binary.length()-1)
  	  		  			hexdecnum += hex[x];
  		  }
  	   }
  	  		return hexdecnum;
    }


  
    
    
    
    
    
    
    private static String SHA_256(String str) {
		MessageDigest messageDigest;
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (Exception e) {
            System.out.println("getSHA256 is error" + e.getMessage());
        }
        return encodeStr;
	}

    private static String byte2Hex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        String temp;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                builder.append("0");
            }
            builder.append(temp);
        }
        return builder.toString();
    }
}
