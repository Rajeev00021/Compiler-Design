package radhe;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Left_Factoring {
	
	
	public static String cfg_Left_factored="";
	
	public static String cfg;
	public static String Not_LF="";

	public static boolean left_factoring=false;
	public static int f_count =0;
	public static void main(String[] args) throws IOException{
		
		BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in));
		System.out.println(" <<=== Enter the Production ===>> \n");
		cfg = reader.readLine();
		
		System.out.println("\n<<=== The New Productions ===>> \n");
	
		check_factor(cfg);
		    System.out.println(Not_LF);
		
}
		
public static void check_factor(String cfg) {

String lines [] = cfg.split("\\\n");
for (String line: lines) {
	
        line= line.replaceAll("\\s+", ""); 
		String productions [] = line.split("->",0); 
		String lhs = productions [0];
		String rhs = productions[1];
		
		String [] rhs_productions = rhs.split("\\|") ; 
		left_factoring=false;
		
		for (int i=0;i<rhs_productions.length-1;i++) {
			for(int j=i+1;j<rhs_productions.length;j++) {
				
				if (rhs_productions[i].charAt(0)==rhs_productions[j].charAt(0) && (!rhs_productions[i].equals("E")) ) {
					left_factoring=true;
				}
			
				
			}
			}
	
		if(left_factoring) {
		

		String common_prefix="";
		common_prefix=find_common_prefix(rhs_productions);
		left_factor_out (lhs , common_prefix,  rhs_productions ); 
	
		}
		else {
			Not_LF+=line +"\n";
        
           }
        }
	}



	private static void left_factor_out(String lhs,  String common_prefix,
			String[] rhs_productions) {
		cfg_Left_factored=lhs+"->";
		for(String pro:rhs_productions) {
			if(!pro.startsWith(common_prefix) ) { 
				cfg_Left_factored+=pro+"|";
			}
			
		}
		cfg_Left_factored+=common_prefix + "S"+ f_count + "\n"; 
		cfg_Left_factored+="S"+ f_count + " -> ";
		for(String pro:rhs_productions) {
			
			if(pro.startsWith(common_prefix) ) { 
				if( pro.substring(1 , pro.length()).length() ==0) {
					
					cfg_Left_factored+="E"+"|";
				}
				else {
					cfg_Left_factored+=pro.substring(1 , pro.length() )+"|";				}
				
			}
			
		}
		cfg_Left_factored=cfg_Left_factored.substring(0, cfg_Left_factored.length()-1);
		System.out.println(cfg_Left_factored);
        if(left_factoring){
        	f_count++;
	check_factor(cfg_Left_factored);
	
		}
       
	}
	
	public static String find_common_prefix(String[] rhs_productions) {
	    String common_prefix = "";
	    for (int i=0;i<rhs_productions.length-1;i++) {
	    	
			for(int j=i+1;j<rhs_productions.length;j++) {
				if (rhs_productions[i].charAt(0) == rhs_productions[j].charAt(0) ) {
					common_prefix=rhs_productions[i].charAt(0)+"";
					break;
				}
				
			}
			if(common_prefix.length()>0) 
					break;	 
}
	    
	    return common_prefix;
	}

	
}