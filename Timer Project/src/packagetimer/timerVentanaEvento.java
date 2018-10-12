package packagetimer;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.*;

public class timerVentanaEvento implements WindowStateListener{


	public void windowStateChanged(WindowEvent e) {
	
		
		if(e.getNewState() == Frame.MAXIMIZED_BOTH) {
			
			cambiarTamano(laminaTimer.contadorS, 400);
			cambiarTamano(laminaTimer.contadorMS, 400);
			cambiarTamano(laminaTimer.contadorDS, 400);
			cambiarTamano(laminaTimer.separador, 400);
			cambiarTamano(laminaTimer.scramble, 60);
			
		}else {
			
			cambiarTamano(laminaTimer.contadorS, 90);
			cambiarTamano(laminaTimer.contadorMS, 90);
			cambiarTamano(laminaTimer.contadorDS, 90);
			cambiarTamano(laminaTimer.separador, 90);
			cambiarTamano(laminaTimer.scramble, 20);
			
			
		}
		
	}
	
	void cambiarTamano(JLabel e, int tam) {
		
		Font actual = e.getFont();
		
		e.setFont(new Font(actual.getFamily(), actual.getStyle(), tam));
	}
	
	
	
}
