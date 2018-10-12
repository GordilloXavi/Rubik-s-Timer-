package packagetimer;

import javax.swing.*;
import javax.swing.UIManager.*;


public class timerMain {

	public static void main(String[] args) {
	
	    try {
	    	 for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {//Recorre todos los look and feels del sistema
	    	        if ("Nimbus".equals(info.getName())) {
	    	            UIManager.setLookAndFeel(info.getClassName());
	    	         
	    	            break;
	    	        }
	    	    }
	    	
	    	
	    } 
	    catch (UnsupportedLookAndFeelException e) {
	    	//e.printStackTrace();
	    }
	    catch (ClassNotFoundException e) {
	    	//e.printStackTrace();
	    }
	    catch (InstantiationException e) {
	    	//e.printStackTrace();
	    }
	    catch (IllegalAccessException e) {
	    	//e.printStackTrace();
	    }
	        
	    new marcoTimer(); 
	    

	}

}


