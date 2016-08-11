package OpkFileRenamer;
import java.io.File;
import java.util.Calendar;
import java.util.HashMap;


public class OpkFileRenamer {

	/**
	 * @param args
	 * -----0  1  2  3  4  5  6  7  8  9--------
	// CCHICO-24-06-16-15-50-33-20-31-EXW339.JPG
	 * 
	 * String nombreArchivoOrig (solo, no ruta completa)
	 * String _estado (siempre es 1)
	 * String maxIdinfraccion (el max id infracción que se guarda en la base, traer el max + 1)
	 */
	// 
   
	// 2016\06\000005905-001-EXW339-20160624155033.JPG    -->ESTADO SIEMPRE = 001
	//													  -->MILENIO=20
    //    3+"\"+2+"\"+(MAX_ID_INFRACCION_FOTO+1)+ESTADO+9+MILENIO+3+2+1+4+5+6+".JPG"
	
	
	public static String darNombre(String nombreArchivoOrig,String _estado,String maxIdinfraccion){
		String fotoOriginal=nombreArchivoOrig;
		
		String anioAAAA = null;
		String mesMM =null;
		String estado = complementarMaxIdInfraccion(_estado, 3);
		String dominio = null;
		String diaDD = null;
		String horaHH = null;
		String minutoMM = null;
		String segundoSS = null;
		String extensión = null;
		
		
		
		HashMap<Integer, String>  map = new HashMap<Integer, String>();
		int indice=0;
		int indiceMapa=0;
		String auxPalabra=null;
		
		
		for(int i=0;i<fotoOriginal.length();i++){
			
			if(fotoOriginal.charAt(i)=='-'){
				auxPalabra=fotoOriginal.substring(indice,i);
			
				map.put(indiceMapa, auxPalabra);
				
				indice=i+1;
				indiceMapa++;
				
			}
		}
		map.put(indiceMapa++, fotoOriginal.substring(indice,fotoOriginal.length()).replaceAll(".JPG",""));	
		
		map.put(indiceMapa++,fotoOriginal.substring(fotoOriginal.indexOf('.')));
		
		/*-----0  1  2  3  4  5  6  7  8  9-----10---
	//    CCHICO-24-06-16-15-50-33-20-31-EXW339.JPG*/
		Calendar now = Calendar.getInstance();   // Gets the current date and time
		int year = now.get(Calendar.YEAR);    
		anioAAAA = String.valueOf(year);
		mesMM = map.get(2);
		dominio = map.get(9);
		diaDD = map.get(1);
		horaHH = map.get(4);
		minutoMM = map.get(5);
		segundoSS = map.get(6);
		extensión = map.get(10);
		
		return anioAAAA+"\\"+mesMM+"\\"+maxIdInfraccion(maxIdinfraccion,9)+"-"+estado+"-"+dominio+"-"+anioAAAA+mesMM+diaDD+horaHH+minutoMM+segundoSS+extensión; 
		
	}
	private static String maxIdInfraccion(String idInfraccion, int largoMax) {
		
		return complementarMaxIdInfraccion(idInfraccion, largoMax);
		
	}
	/**
	 * 
	 * @param idInfraccion String con el max nro infraccion existente en la base + 1
	 * @param largoMax Largo total que debe tener la cadena
	 * @return si el largo es 10 y el max + 1 =2 -> 0000000002
	 */
	private static String complementarMaxIdInfraccion(String idInfraccion,int largoMax){
		String nroFinal=null;
		nroFinal=idInfraccion;
		while (nroFinal.length()!=largoMax){
			nroFinal="0"+nroFinal;
		}	
		
		return nroFinal;
		
	}
	
	
	
	
	public static void main(String[] args) {
		File archivo=new File("C:\\Documents and Settings\\AR00122207\\Escritorio\\CCHICO-24-06-16-15-50-33-20-31-EXW339.JPG");
		
	System.out.println(darNombre("CCHICO-24-06-16-15-50-33-20-31-EXW339.JPG","1", "5905"));
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
