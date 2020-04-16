package died.guia06;

import java.util.ArrayList;
import java.util.List;

public class App {

	public static void main(String[] args) throws Exception{
		
		
			Alumno jose = new Alumno("jose", 123,null,null);
			Alumno pedrito = new Alumno("pedrito", 124,null,null);
			List<Alumno> inscrip = new ArrayList<Alumno>();
			 inscrip.add(jose);
			 inscrip.add(pedrito);
			 
			 
			Curso curso1 = new Curso(1,"Java1",2020,30,inscrip,2,0, null);
														
			Alumno juan = new Alumno("Juan", 123,null,null);
			Curso curso2 = new Curso(1,"DIED 2",2020,40,inscrip,2,0, null);
			Alumno pablo = new Alumno("Pablo", 124,null,null);
			
			
			
			System.out.println("Prueba:"+curso1.getNombre());
			System.out.println("Prueba2:"+juan.getNombre());
			System.out.println("Prueba3:"+curso2.getNombre());
			System.out.println("Prueba4:"+pablo.getNombre());
			
			curso1.inscribir(juan);
			
				
			curso1.inscribir(pablo);
			curso2.inscribir(juan);
			
			System.out.println("finaliza main");
				
		}	
	}
