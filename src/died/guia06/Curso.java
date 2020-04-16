package died.guia06;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import died.guia06.util.Registro;

/**
 * Clase que representa un curso. Un curso se identifica por su ID y por su nombre y ciclo lectivo.
 * Un curso guarda una lista de los inscriptos actuales que tienen.
 * Un curso, al aprobarlo, otorga una cantidad de creditos definidas en el curso.
 * Un curso requiere que para inscribirnos tengamos al menos la cantidad de creditos requeridas, y que haya cupo disponible
 * @author marti
 *
 */
public class Curso {

	private Integer id;
	private String nombre;
	private Integer cicloLectivo;
	private Integer cupo; 
	private List<Alumno> inscriptos;
	private Integer creditos;
	private Integer creditosRequeridos;
	
	private Registro log;
	
	public Curso() {
		super();
		this.inscriptos = new ArrayList<Alumno>();
		this.log = new Registro();
	}
	
	public Curso(Integer id, String nombre, Integer cicloLectivo, Integer cupo, List<Alumno> inscriptos,
			Integer creditos, Integer creditosRequeridos, Registro log) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.cicloLectivo = cicloLectivo;
		this.cupo = cupo;
		this.inscriptos = inscriptos;
		this.creditos = creditos;
		this.creditosRequeridos = creditosRequeridos;
		this.log = log;
	}

	/**
	 * Este método, verifica si el alumno se puede inscribir y si es así lo agrega al curso,
	 * agrega el curso a la lista de cursos en los que está inscripto el alumno y retorna verdadero.
	 * Caso contrario retorna falso y no agrega el alumno a la lista de inscriptos ni el curso a la lista
	 * de cursos en los que el alumno está inscripto.
	 * 
	 * Para poder inscribirse un alumno debe
	 * 		a) tener como minimo los creditos necesarios
	 *      b) tener cupo disponibles
	 *      c) puede estar inscripto en simultáneo a no más de 3 cursos del mismo ciclo lectivo.
	 * @param a
	 * @return
	 * @throws IOException 
	 */
	
	public Boolean inscribir(Alumno a) throws ArgumentInvalidException, IOException{
		
		if(a.creditosQuePoseeAlumno() >= creditosRequeridos) {
			//PODRIA USAR TB: throw new ArgumentInvalidException("No tiene los cr�ditos necesarios")
			log.registrar(this, "inscribir ",a.toString());
			return false; 												//RETORNO FALSE PORQUE NO PUEDO SALVAR  LA VALIDACION,
																		//SI NO TIENE CREDITOS, NO PUEDE
		}
		if(inscriptos.size() < cupo) {
			log.registrar(this, "inscribir ",a.toString());
			throw new ArgumentInvalidException("Cupo del curso");    //LANZO EXCEPTION XQ PUEDO SALVAR  LA VALIDACION; 
		}															//POR EJ PROBANDO EN OTRA COMISION
		if(a.getCantidadCursando() < 3) {
			log.registrar(this, "inscribir ",a.toString());
			throw new ArgumentInvalidException("Cant de materias cursando mayor a 3"); //LANZO EXCEPTION XQ PUEDO SALVAR  LA VALIDACION
		}																				//POR EJ RENUNCIAN A UNA MATERIA
		
		try {
			inscriptos.add(a);
			Curso newCurso = new Curso();
			
			newCurso.setId(this.id);
			newCurso.setNombre(this.nombre);
			newCurso.setCicloLectivo(this.cicloLectivo);
			newCurso.setCupo(this.cupo);
			newCurso.setInscriptos(this.inscriptos);
			newCurso.setCreditos(this.creditos);
			newCurso.setCreditosRequeridos(this.creditosRequeridos);
			newCurso.setLog(this.log);
	
			System.out.println("Se inscribio al alumno: "+a.getNombre());
			a.empezoAcursar(newCurso);
			return true;
			
		}catch(NullPointerException e) {
			throw new ExceptionPunteroNulo("Los punteros estan mal");
			
		}
		
	}	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCicloLectivo() {
		return cicloLectivo;
	}

	public void setCicloLectivo(Integer cicloLectivo) {
		this.cicloLectivo = cicloLectivo;
	}

	public Integer getCupo() {
		return cupo;
	}

	public void setCupo(Integer cupo) {
		this.cupo = cupo;
	}

	public List<Alumno> getInscriptos() {
		return inscriptos;
	}

	public void setInscriptos(List<Alumno> inscriptos) {
		this.inscriptos = inscriptos;
	}

	public Integer getCreditos() {
		return creditos;
	}

	public void setCreditos(Integer creditos) {
		this.creditos = creditos;
	}

	public Integer getCreditosRequeridos() {
		return creditosRequeridos;
	}

	public void setCreditosRequeridos(Integer creditosRequeridos) {
		this.creditosRequeridos = creditosRequeridos;
	}

	public Registro getLog() {
		return log;
	}

	public void setLog(Registro log) {
		this.log = log;
	}

	
	/**
	 * imprime los inscriptos en orden alfabetico
	 */
	
	public void imprimirInscriptos() throws Exception {
		
		try {
			System.out.println("ESTOY EN IMPRIMIR INSCRIPTOS");
			System.out.println(inscriptos);
			Collections.sort((List<Alumno> inscriptos);  //ORDENA LA LISTA SEGUN EL CRITERIO Q DEFINI EN "CompareTo", en este caso num de Libreta
			System.out.println(inscriptos);
			
			//Comparar por la interface Comparator por nombre
			
			CompararNombre comparador = new CompararNombre();				
			Collections.sort(inscriptos, comparador);
			System.out.println(inscriptos);
			
			//OTRA FORMA, para no tener q andar creando clases para cada criterio, es difiniendo directamente en el metodo
			//comparo por creditos
			Collections.sort(								// aca comparo por cantidad de creditos de cada alumno
							inscriptos, 
							new Comparator<Alumno>() {
								public int compare(Alumno a1, Alumno a2) {
									int creditos1 = a1.creditosQuePoseeAlumno();
									int creditos2 = a2.creditosQuePoseeAlumno();
									return creditos2 - creditos1;
								}
							}
			);
			
		}catch(Exception ex) {
			log.registrar(this, "imprimir listado",this.inscriptos.size()+ " registros ");
		}finally {
				System.out.println(inscriptos);		
		}
		
	}
	


}
