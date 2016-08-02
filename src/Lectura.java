import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class Lectura {

	public static void imprimir(ArrayList<String>datosLinea){
		System.out.println("Patente->"+datosLinea.get(0));
		System.out.println("Velocidad Registrada->"+datosLinea.get(2));
		System.out.println("idLugar->"+datosLinea.get(4));
		System.out.println("LugarNombre->"+datosLinea.get(6));
		System.out.println("Fecha->"+datosLinea.get(8));
		System.out.println("FotoId->"+datosLinea.get(5));
		System.out.println("Nombre-Foto->"+datosLinea.get(9));
		System.out.println("-------------------------------------------------------");
		JOptionPane.showMessageDialog(null, "Datos creados");
	}
	
	
	/**
	 * 
	 * @param rutaFullArchivo
	 * @return Devuelve un array de Infracciones
	 */
	
	public static ArrayList<Infraccion> decodificar(String rutaFullArchivo){
		
		
		int contador=0;
		String sCadena;
		String auxCadena;
		ArrayList <String>datosLinea=new ArrayList<String>();
		ArrayList <Infraccion> infracciones=new ArrayList<Infraccion>();
		Infraccion infra=new Infraccion();
		try {
			
			BufferedReader bf = new BufferedReader(new FileReader(rutaFullArchivo));
			
			
			try {
				while ((sCadena = bf.readLine())!=null) {
					if(sCadena.contains(",")&& !sCadena.contains("Patente")) {  //Limpio titulos y me aseguro que la primer patente quede en la posición correcta
					auxCadena=sCadena.replaceAll("\"", "");/*quitamos todas las comillas*/
					contador++;// solo sirve para ver si la cant de registros coincide
					int posComa=0;
					int posComaAux=0;
					for(int i=0;i<auxCadena.length();i++){
						if(auxCadena.charAt(i)==','){
							posComa=i;
							datosLinea.add(auxCadena.substring(posComaAux, posComa));
							posComaAux=posComa+1;
						}
					}
					
					datosLinea.add(auxCadena.substring(posComa+1,auxCadena.length()));
					
					datosLinea.add("CAP_"+datosLinea.get(4)+"_"+datosLinea.get(5)+".jpg");
					
					System.out.println("Imprimir en Lectura.java linea 66");
					imprimir(datosLinea);
					//***************************************************
					//Generar las infracciones					
					
					infracciones.add(infra.generarInfraccion(datosLinea));				
					
					//***************************************************				
					
					datosLinea.removeAll(datosLinea);				
					}							
				}
			
					System.out.println("Registros->"+contador);
				
				
			} catch (IOException e) {
			
				JOptionPane.showMessageDialog(null, e.getMessage());
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
		return infracciones;
		
		
	}


	public ArrayList<String> leerArchivo(String rutaFull) throws HeadlessException, IOException {
		ArrayList<String>lineasTexto=new ArrayList<String>();
		InputStream s = this.getClass().getResourceAsStream(rutaFull);    
	    BufferedReader br = new BufferedReader(new InputStreamReader(s));
	    
		String sCadena;
		while ((sCadena = br.readLine())!=null) {
			
			if(!sCadena.contains("#")){
				lineasTexto.add(sCadena);
				System.out.println(sCadena);
			}
		}
		return lineasTexto;
		
	}
	
	public void extraerDatosdeCsv(String rutaFull){
			ArrayList<Infraccion> infra;
			infra=Lectura.decodificar(rutaFull);
			for(int i=0;i<infra.size();i++){
			System.out.println(infra.get(i).getPatente());
			System.out.println(infra.get(i).getVelocidad());
			System.out.println(infra.get(i).getNombreFoto());
			System.out.println(infra.get(i).getFecha());
			
		}

	}
	
	
	
		
}	


