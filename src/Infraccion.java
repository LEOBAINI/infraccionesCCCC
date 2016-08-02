import java.util.ArrayList;

public class Infraccion{
	private	String patente;
	private	String velocidad;
	private	String idLugar;
	private	String LugarNombre;
	private	String fecha;
	private	String fotoId;
	private String nombreFoto;
		
		
		public Infraccion(){}
		public Infraccion(
		String patente,
		String velocidad,
		String idLugar,
		String LugarNombre,
		String fecha,
		String fotoId,
		String nombreFoto){
			
			
			this.patente=patente;
			this.velocidad=velocidad;
			this.idLugar=idLugar;
			this.LugarNombre=LugarNombre;
			this.fecha=fecha;
			this.fotoId=fotoId;
			this.nombreFoto=nombreFoto;
			
			
			
			
		}
		
		public Infraccion generarInfraccion(ArrayList<String>datosLinea){
			Infraccion inf=new Infraccion();
			inf.setPatente(datosLinea.get(0));
			inf.setVelocidad(datosLinea.get(2));
			inf.setIdLugar(datosLinea.get(4));
			inf.setLugarNombre(datosLinea.get(6));
			inf.setFecha(datosLinea.get(8));
			inf.setFotoId(datosLinea.get(5));
			inf.setNombreFoto(datosLinea.get(9));
			return inf;
		}

		public String getPatente() {
			return patente;
		}

		public void setPatente(String patente) {
			this.patente = patente;
		}

		public String getVelocidad() {
			return velocidad;
		}

		public void setVelocidad(String velocidad) {
			this.velocidad = velocidad;
		}

		public String getIdLugar() {
			return idLugar;
		}

		public void setIdLugar(String idLugar) {
			this.idLugar = idLugar;
		}

		public String getLugarNombre() {
			return LugarNombre;
		}

		public void setLugarNombre(String lugarNombre) {
			LugarNombre = lugarNombre;
		}

		public String getFecha() {
			return fecha;
		}

		public void setFecha(String fecha) {
			this.fecha = fecha;
		}

		public String getFotoId() {
			return fotoId;
		}

		public void setFotoId(String fotoId) {
			this.fotoId = fotoId;
		}

		public String getNombreFoto() {
			return nombreFoto;
		}

		public void setNombreFoto(String nombreFoto) {
			this.nombreFoto = nombreFoto;
		}
	}