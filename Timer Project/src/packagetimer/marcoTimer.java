package packagetimer;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowStateListener;
import java.net.URL;

import javax.swing.*;


public class marcoTimer extends JFrame {
	
	public marcoTimer(){
		setDefaultCloseOperation(EXIT_ON_CLOSE);//Si le pongo do nothing el programa se tiene que cerrar desde el administrador de tareas. 
		setSize( 680, 220);
		setLocationRelativeTo(null);
		setAlwaysOnTop(false);
		setTitle("Cube timer by Xavi Gordillo");
		add(new laminaTimer());
		setResizable(true);
		setUndecorated(false);//Hace que desaparezcan los tres botones de windows en el frame
		
		//**************
		
		
		/*
		Toolkit imagen = Toolkit.getDefaultToolkit();
		
		URL rutaBoton = marcoTimer.class.getResource("iconoRubik.png");

		Image Icono = Toolkit.getDefaultToolkit().getImage(rutaBoton);
		
		*/
		
		Image Icono = Toolkit.getDefaultToolkit().getImage("src/packagetimer/iconoRubik.png");
		setIconImage(Icono);
		
		
		//*********************
		
		timerVentanaEvento maximized = new timerVentanaEvento();
		
		addWindowStateListener(maximized);
		
		setVisible(true);
		
		
	}
	
	
}
