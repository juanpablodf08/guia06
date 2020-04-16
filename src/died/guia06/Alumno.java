package died.guia06;

import java.util.List;


public class Alumno implements Comparable<Alumno>{

	private String nombre;
	private Integer nroLibreta;
	private List<Curso> cursando;
	private List<Curso> aprobados;

	public Alumno(String nombre, Integer nroLibreta, List<Curso> cursando, List<Curso> aprobados) {
		super();
		this.nombre = nombre;
		this.nroLibreta = nroLibreta;
		this.cursando = cursando;
		this.aprobados = aprobados;
	}
	
	//DOS ALUMNOS SON IGUALES SI TIENEN MISMO NUMERO DE LIBRETA
	public boolean equals(Object obj) {
			
			Alumno other = (Alumno) obj;
			if (nroLibreta == null) {
				if (other.nroLibreta != null)
					return false;
			} else if (!nroLibreta.equals(other.nroLibreta))
				return false;
			return true;
	}
		
	//Cuando aprueba un curso, se quita el curso de la lista "cursando" y se agrega a la lista de cursos aprobados
	public boolean aproboCurso (Curso unCurso) {
		
			for(int i = 0; i < cursando.size();i++) {
				if(cursando.get(i).getId() == unCurso.getId()) {  //SI EL CURSO QUE PASE ESTA EN LA LISTA DE "CURSANDO"
					
					cursando.remove(i);
					aprobados.add(unCurso);
					System.out.println("Aprobo el curso "+unCurso.getNombre());
					return true;										//retorna 1 si estaba cursando y aprobo
				}
				System.out.println("No esta cursando dicho curso");
			}														//si no estaba cursando, retorna cero, es decir que no aprobo ese curso
			return false;
	}	
	
	public void empezoAcursar(Curso unCurso) {
		System.out.println("Se inscribio al curso "+unCurso.getNombre());
		cursando.add(unCurso);
	}
	
	public int compareTo(Alumno a) {
		if( nombre.compareTo(a.getNombre()) > 0) { 		//cadena1 va después que cadena2
			return 1;
		}else if (nombre.compareTo(a.getNombre()) < 0)   //"cadena1 va antes que cadena2"
					return -1;
			  else return 0;										//cadenas iguales
    }
	
	public Integer creditosQuePoseeAlumno() {
		Integer credTotales=0;
		
		if(aprobados != null) {
			for(Curso unCurso : aprobados) {
				credTotales = credTotales + unCurso.getCreditos();
			}
		}
		return  credTotales;
	}
	
	public int getCantidadCursando() {
		
		if(cursando == null) {
			return 0;
		}else return cursando.size();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getNroLibreta() {
		return nroLibreta;
	}

	public void setNroLibreta(Integer nroLibreta) {
		this.nroLibreta = nroLibreta;
	}

	public List<Curso> getCursando() {
		return cursando;
	}

	public void setCursando(List<Curso> cursando) {
		this.cursando = cursando;
	}

	public List<Curso> getAprobados() {
		return aprobados;
	}

	public void setAprobados(List<Curso> aprobados) {
		this.aprobados = aprobados;
	}
	
	
	
	public int creditosObtenidos() {
		return 1;
	}

	public void aprobar(Curso c) {
		//
	}

	public void inscripcionAceptada(Curso c) {
		//
	}
	
	

}
