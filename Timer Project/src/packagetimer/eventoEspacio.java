package packagetimer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class eventoEspacio implements KeyListener{

	
	public void keyPressed(KeyEvent e) {

	
		}

	

	@Override
	public void keyReleased(KeyEvent e) {
		
		if(e.getKeyChar() == 'r' || e.getKeyChar() == KeyEvent.VK_ENTER) {
			
			
			
			if (laminaTimer.guardable && Integer.parseInt(laminaTimer.contadorS.getText())>=1) {
				if(laminaTimer.autoguardado) {
					laminaTimer.saveTime(laminaTimer.conversor(laminaTimer.sec, laminaTimer.ds, laminaTimer.ms));
					
					laminaTimer.h++;
				}
				
				
			}
			
			laminaTimer.boxtiempos.setSelectedIndex(laminaTimer.h);
			
			
			laminaTimer.guardable = false;
			
			laminaTimer.sec = 0;
			laminaTimer.ms = 0;
			laminaTimer.ds = 0;
			laminaTimer.timerS.stop();
			laminaTimer.timerMS.stop();
			laminaTimer.timerDS.stop();
			laminaTimer.scramble.setText(laminaTimer.ponerTexto());
		 
			 
			 if(laminaTimer.started && laminaTimer.timerS.isRunning()) {
				 laminaTimer.start.setText("Parar");
				 laminaTimer.started = !laminaTimer.started;

			 }
			 
			 else if (laminaTimer.started == false && laminaTimer.timerS.isRunning() == false) {
				 laminaTimer.start.setText("Iniciar");
				 laminaTimer.started = !laminaTimer.started;
				 
				

			 }
			 
			 laminaTimer.start.requestFocus();
		
			
		
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}


/*
if(laminaTimer.started) {
	 
	 laminaTimer.sec= 0;
	 laminaTimer.ms = 0;
	 laminaTimer.ds = 0;
	 laminaTimer.timerS.start();
	 laminaTimer.timerMS.start();
	 laminaTimer.timerDS.start();
	 laminaTimer.start.setText("Stop");
	 
}
else  {
	 laminaTimer.timerS.stop();
	 laminaTimer.timerMS.stop();
	 laminaTimer.timerDS.stop();
	 laminaTimer.start.setText("Start");
	// System.out.println(sec);
	 
}
laminaTimer.started = !laminaTimer.started;*/