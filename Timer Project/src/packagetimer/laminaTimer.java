package packagetimer;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.util.Arrays;

import javax.swing.*;

@SuppressWarnings("serial")
public class laminaTimer extends JPanel{
	
	//COMPONENTES: ################################################################
	
	static JLabel scramble, contadorS, contadorMS, contadorDS, separador;
	
	static JButton restart, start;
	
	static int sec, ms, ds,h;
	
	static boolean started = true;
	
	static Timer timerS, timerMS, timerDS;
	
	static JComboBox<String> boxtiempos;

	static boolean guardable;
	
	static boolean autoguardado = true;
	
	private JPopupMenu menuDesplegable;
	
	//LÁMINA : ################################################################

	public laminaTimer(){
		
		/*		
		Cursor cursorX = Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage("src/packagetimer/iconoRubik2.png"), new Point(0,0), "Cursor");
		
		setCursor(cursorX);
		*/
		
		
		
		
		//LAYOUT Y PANELES: ################################################################
		
		setBackground(Color.BLACK);		
		setLayout(new BorderLayout());
		
		JPanel laminaOeste = new JPanel();
		laminaOeste.setBackground(Color.BLACK);
		JLabel textocentrador = new JLabel("_________");
		textocentrador.setForeground(Color.BLACK);
		
		add(laminaOeste, BorderLayout.WEST);
		
		JPanel laminaEste = new JPanel();
		laminaEste.setBackground(Color.BLACK);
		
		JPanel laminaNorte = new JPanel();
		laminaNorte.setBackground(Color.BLACK);
		
		JPanel laminaSur = new JPanel();
		laminaSur.setBackground(Color.BLACK);
		
		JPanel laminaCentro = new JPanel();
		laminaCentro.setBackground(Color.BLACK);
		
		// LABELS (CONTADORES Y SCRAMBLE): ################################################################
		
		Font letra = new Font("Courier New", Font.PLAIN, 90);
		
		scramble = new JLabel(ponerTexto());
		scramble.setFont(new Font("Courier New", Font.PLAIN, 20));
		scramble.setForeground(Color.GREEN);
		
		
		contadorS = new JLabel("0");
		contadorS.setFont(letra);
		
		
		contadorDS = new JLabel("0");
		contadorDS.setFont(letra);
		contadorDS.setForeground(Color.GREEN);
		
		contadorMS = new JLabel("0");
		contadorMS.setFont(letra);
		contadorMS.setForeground(Color.GREEN);
		
		sec = 0;
		ms = 0;
		ds = 0;
		
		separador = new JLabel(":");
		separador.setFont(letra);
		separador.setForeground(Color.GREEN);
		
		restart = new JButton("Reiniciar");		
		start = new JButton("Iniciar");
				
		boxtiempos = new JComboBox<String>();
		boxtiempos.setEditable(false);
		boxtiempos.addItem("Tiempos");
		
				
		menuDesplegable = new JPopupMenu();
		JMenuItem activarAutoguardado = new JMenuItem("Desactivar autoguardado");
		menuDesplegable.add(activarAutoguardado);
		
		
		
		laminaCentro.add(menuDesplegable);
		laminaCentro.setComponentPopupMenu(menuDesplegable);
		laminaEste.setComponentPopupMenu(menuDesplegable);
		laminaNorte.setComponentPopupMenu(menuDesplegable);
		laminaSur.setComponentPopupMenu(menuDesplegable);
		laminaEste.add(boxtiempos);
		laminaOeste.add(textocentrador);
		laminaCentro.add(contadorS);
		laminaCentro.add(separador);
		laminaCentro.add(contadorDS);
		laminaCentro.add(contadorMS);
		laminaNorte.add(start);
		laminaNorte.add(restart);
		
	
		// EVENTOS DE BOTONES Y TIMERS!!!!: ################################################################
		
		
		eventoEspacio espacio = new eventoEspacio();
		start.addKeyListener(espacio);
		
		
		//PARA EL TIMER QUE SE ACCIONA CADA 10 MS Y HACE LA FUNCIÓN DE CONTAR
		ActionListener AccionContar = new ActionListener() {
			
			 public void actionPerformed(ActionEvent event) {
			  
			 contadorS.setText(""+ sec);
			 contadorMS.setText("" + ms);
			 contadorDS.setText("" + ds);
			 }

			};
			
		Timer timerContador = new Timer(10, AccionContar);//TIMER QUE HACE FUNCIONAR EL CONTADOR
	


		

		
		ActionListener AccionSegundos = new ActionListener() {

			
			 @Override
			 public void actionPerformed(ActionEvent event) {
			  
			 sec++;
			 
			timerContador.start();

			 
			 }

			};
			
		ActionListener AccionMiliSegundos = new ActionListener() {

				
				 @Override
				 public void actionPerformed(ActionEvent event) {
				  
				 ms++;
				 if (ms == 10) ms = 0;
				 
				timerContador.start();

				 
				 }

				};
				
				ActionListener AccionDeciSegundos = new ActionListener() {

					
					 @Override
					 public void actionPerformed(ActionEvent event) {
					  
					 ds++;
					 if (ds == 10) ds = 0;
					 
					timerContador.start();

					 
					 }

					};
					
				//ANIMACIÓN INICIAL CON LOS COLORES	
		ActionListener accionColor = new ActionListener() {

			int g = 0;
					
					public void actionPerformed(ActionEvent arg0) {
						contadorS.setForeground(new Color(0,g,0).brighter());
						contadorMS.setForeground(new Color(0,g,0).brighter());
						contadorDS.setForeground(new Color(0,g,0).brighter());
						separador.setForeground(new Color(0,g,0).brighter());
						scramble.setForeground(new Color(0,g,0).brighter());
						if (g<255)	g++;
						
						
					}
					
				};
	
		Timer animar = new Timer(20, accionColor);		
		animar.start();
		
		timerS = new Timer(1000, AccionSegundos);
		
		timerMS = new Timer(10,AccionMiliSegundos);
		
		timerDS = new Timer(100, AccionDeciSegundos);

		h = 0;

		
		restart.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent event) {
				
				
				
				//guardable es false en default
				if (guardable && Integer.parseInt(contadorS.getText())>=1) {
					if(autoguardado) {
						saveTime(conversor(sec, ds, ms));
						
						h++;
					}
					
					
				}
				
				boxtiempos.setSelectedIndex(h);
				
				
				guardable = false;
				
				sec = 0;
				ms = 0;
				ds = 0;
				timerS.stop();
				timerMS.stop();
				timerDS.stop();
				scramble.setText(ponerTexto());
			 
				 
				 if(started && timerS.isRunning()) {
					 start.setText("Parar");
					 started = !started;

				 }
				 
				 else if (started == false && timerS.isRunning() == false) {
					 start.setText("Iniciar");
					 started = !started;
					 
					

				 }
				 
				start.requestFocus();
			

;
			 }
		});
		
		
		
		start.addActionListener(new ActionListener() {

			
			
			 @Override
			 public void actionPerformed(ActionEvent event) {
			  			 
				 if(started) {
					 
					 Toolkit.getDefaultToolkit().beep();
					 
					 sec= 0;
					 ms = 0;
					 ds = 0;
					 timerS.start();
					 timerMS.start();
					 timerDS.start();
					 start.setText("Parar");
					 
				 }
				 else if (started == false) {
					 timerS.stop();
					 timerMS.stop();
					 timerDS.stop();
					 start.setText("Iniciar");
					// System.out.println(sec);
					 guardable = !guardable;

					 
					 
					 
				 }
				 started = !started;
			 
			 }

			});
		
		activarAutoguardado.addActionListener(new ActionListener() {

			
			
			 @Override
			 public void actionPerformed(ActionEvent event) {
				 
				 autoguardado = !autoguardado;
				 
				 if(autoguardado == false)activarAutoguardado.setText("Activar autoguardado");
			  	if(autoguardado)activarAutoguardado.setText("Desactivar autoguardado");
			 }

			});
		
		
	
		
		laminaSur.add(scramble);
		
		
		add(laminaCentro, BorderLayout.CENTER);
		add(laminaNorte, BorderLayout.NORTH);
		add(laminaSur, BorderLayout.SOUTH);
		add(laminaEste, BorderLayout.EAST);

		
		
		cargarTiempos();
		
		boxtiempos.insertItemAt(hacerMedia(), 0);//Pone la media nadamás empezar
		
		//boxtiempos.insertItemAt("Media: "+hacerMedia(), 0);
		
		

		
		
		//System.out.println(ponerTexto());
		
		
		  
		
	}//FIN DEL CONSTUCTOR!!! 
	
	public static String ponerTexto() {
		
		String movimientoAnterior = "";
		
		String[] letters = {"L", "R", "F", "B", "U", "D" };
		
		String[] simbolos = {"", "'", "2"};
		
		String[] scrambles = new String[18];
		
		String  scramblesS = "";

		
		for (int i = 0; i<scrambles.length; i++) {
			
			int rand; //= (int) (Math.random()* letters.length);
			
			double dd = Math.random()*3;
			
			int randS = (int) dd;
			
					
			do{
				
				rand = (int) (Math.random()* letters.length);

				
			}while (movimientoAnterior.equalsIgnoreCase(letters[rand]));
			
			movimientoAnterior = letters[rand];
			
			scrambles[i] = letters[rand] + simbolos[randS];
			
			scramblesS = scramblesS + scrambles[i] + " ";


			
		}
		
		
		
		
		return scramblesS;
		
		
		
	}
	

	
	public static void saveTime(String i) {
		
		
		
		boxtiempos.addItem(i);
		
		escribitTiempo(i);
		
		boxtiempos.removeItemAt(0);
		
		boxtiempos.insertItemAt(hacerMedia(), 1);
		
		
		
		
	}

	
	
	public static String conversor(int s, int ds, int cs) {
		
		String tiempoS = s+ ":" +ds+cs;
		
		return tiempoS;
		
	}
	
	static void escribitTiempo(String tiempo) {
		
		
		
		try {

			FileWriter escrituraTiempos = new FileWriter("tiempos.txt", true);

			
			for(int i = 0; i< tiempo.length(); i++) {
				
				escrituraTiempos.write(tiempo.charAt(i));
				
			}
			
			escrituraTiempos.write("#");
			
			
			escrituraTiempos.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al guardar el tiempo", "Error", 1);
		}
	}
	
	static void cargarTiempos() {
		
		try {
			//FileReader a = new FileReader("C:\\Users\\Xavi\\Desktop\\PROGRAMAS JAVA\\tiempos.txt");
			
			FileReader a = new FileReader("tiempos.txt");

			
			BufferedReader lector = new BufferedReader(a);
			
			String z = lector.readLine();
			
			if (z != null) {
			
			String[] tiempos = z.split("#");
			
			
			
			Arrays.sort(tiempos);
			
			
			
			for(String s: tiempos) {
				boxtiempos.addItem(s);
			}
			}
			
			lector.close();
			
			
		} catch (IOException e) {

			e.printStackTrace();
			
			JOptionPane.showMessageDialog(null, "No se encontraron tiempos guardados", "", 1);
		}
		
		
		
	}
	
	static String hacerMedia() {
		
		double media = 0;
		int items = 0;
		
		for(int i = 0 ; i< boxtiempos.getItemCount(); i++) {
			
			if(!boxtiempos.getItemAt(i).contains("Tiempos") ) {
				media +=  Double.parseDouble(boxtiempos.getItemAt(i).replace(':', '.'));
				items++;
			}
			
		}
		
		if (items!= 0)media = media/items;
		
		
		media = media*100;
		media = Math.round(media);
		media = media /100;
		
		return "Media: "+ media;
		
		
		
	}
	
	
	

	
	
	
}
	
