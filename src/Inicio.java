import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;


public class Inicio {

	static int cantArch=0;
	static int cantArchAux=0;
	static int paridad=0;
	static int crecimiento=0;
	static int inactividad=1;
	static String directorioAux;
	static String directorioIn;
	static String directorioOut;
	static int tiempoMaxInactividad=0;


	private static void iniciarAplicacion() {
		try{
			cargarSettings();
			Timer timer = new Timer(); 

			timer.scheduleAtFixedRate(timerTask, 0, 1000);// ejecuta cada un segundo

			SwingUtilities.invokeLater	(
					new Runnable() {
						public void run() {
							Trayicon.createAndShowGUI();
							Trayicon.mostrarMensaje("Aplicación iniciada");// muestra pop up en try icon
						}//Fin run
					}//Fin runnable
			);//Fin invokelater

		}catch(Exception e){
			System.out.println(e.getMessage());
			System.exit(0);
		}

	}
	private static void monitorearActividadCarpetaIn() {
		if(cantArch>cantArchAux){
			System.out.println("Está CRECIENDO");
			crecimiento++;
			inactividad=0;
		}else if(cantArch<cantArchAux){
			System.out.println("Está DECRECIENDO");
			crecimiento--;
			inactividad=0;
		}else if(cantArch==cantArchAux){

			inactividad++;
		}      

	}
	
	private static int copiarDeUnDirectorioAotro(String directorioIn, String directorioOut) {
		Trayicon.mostrarMensaje("Trabajando con "+cantArch+" Archivos");
		// Copio todo de la carpeta in a la out
		String[] commands = {"cmd", " /c", " xcopy /s/c/y", " "+directorioIn, " "+directorioOut };
		System.out.println(commands[0]+commands[1]+commands[2]+commands[3]+commands[4]);
		Process pr = null;
		try {
			Runtime rt = Runtime.getRuntime();
			pr = rt.exec(commands[0]+commands[1]+commands[2]+commands[3]+commands[4]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			pr.waitFor();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}
	private static void borrarContenidoDirectorio(String directorioIn) {

		File directorio=new File(directorioIn);

		String[]lista=directorio.list();


		for(int i=0;i<lista.length;i++){
			File archivo=new File(directorio.getAbsolutePath()+"\\"+lista[i]);
			archivo.delete();
			System.out.println("Borrando "+archivo.getAbsolutePath());

		}

	}

	public static void cargarSettings(){
		Lectura l=new Lectura();
		ArrayList<String> settings = null;
		try {
			settings = l.leerArchivo("/Settings.txt");
		} catch (HeadlessException e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
			e.printStackTrace();
		}
		directorioIn=settings.get(0);
		directorioOut=settings.get(1);
		tiempoMaxInactividad=Integer.parseInt(settings.get(2));
	} 

	/*	private static void swapDir(){		//Es un inversor de carpetas destino origen
		directorioAux=directorioIn;     // sirve para pruebas
		directorioIn=directorioOut;
		directorioOut=directorioAux;
	}*/

	static TimerTask timerTask = new TimerTask() 

	{ 

		public void run()  
		{ 
			File directorio=new File(directorioIn);// directorio a leer

			if(paridad%2==0)// o sea, si es par
			{
				cantArch=directorio.listFiles().length;// carga primero porque inicia en 0
			}else{
				cantArchAux=directorio.listFiles().length;
			}        	

			if(paridad>=2){ //controla al menos 2 veces

				monitorearActividadCarpetaIn();

				System.out.println("HAY "+directorio.listFiles().length+" ARCHIVOS");        		

				System.out.println("LA INACTIVIDAD ES "+inactividad);

				if(cantArch >0 && inactividad >=tiempoMaxInactividad ){// si hay archivos y no hay mov hace un min

					inactividad=copiarDeUnDirectorioAotro(directorioIn,directorioOut);

					ArrayList<Infraccion>infracciones;

					infracciones=Lectura.decodificar(directorioOut+"\\Planilla.csv");


					if(infracciones.size()>0){
						borrarContenidoDirectorio(directorioIn);
					}
					else{
						System.out.println("Infracciones en 0");
					}				

				}


			}

			paridad++;/*contador para la condición if de si es par o no*/

			if(inactividad>=360){/*Sólo para que el integer no crezca mucho*/
				inactividad=1;
			}
		}


	};


	public static void main(String[] args) {// Ini Main
		//Probando git hub
		iniciarAplicacion();

	}//Main fin






}