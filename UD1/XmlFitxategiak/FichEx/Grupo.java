public class Grupo
/* Grupo de alumnos de una clase que registra las notas obtenidas en 6 asignaturas 
Las asignaturas tienen c�digos del 1 al 6. 
Los alumnos tienen c�digos desde el 1 hasta el n�mero de alumnos del grupo. */ 
{
	private int curso; // n� de curso
	private char secci�n; // secci�n o grupo: 'A','B','C','D'
	private double[][] notas;	/* matriz de notas con una fila por asignatura y tantas columnas como alumnos
								   del grupo */  		

	public Grupo(int cur, char sec, int numAlum)
	/* Constructor que inicializa el grupo creando la matriz de notas para 6 asignaturas
	y numAlum alumnos */
	{	}

	public int getCurso()
	// Devuelve el n� de curso
	{	}

	public char getSecci�n()
	// Devuelve la secci�n
	{	}

	public int getNumeroAlumnos()
	// Devuelve el n�mero de alumnos del grupo
	{	}

	public double getNota(int asig, int alum)
	/* Devuelve la nota obtenida por el alumno alum en la asignatura asig
	Precondici�n: (1 <= asig <= 6) y (1 <= alum <= n�mero de alumnos del grupo) */
	{	}

	public double[] getNotasAsignatura(int asig)
	/* Devuelve las notas de todos los alumnos de la asignatura asig en un array
	Precondici�n: 1 <= asig <= 6 */
	{	}

	public double[] getNotasAlumno(int alum)
	/* Devuelve las notas de todas las asignaturas del alumno alum en un array
	Precondici�n: 1 <= alum <= n�mero de alumnos del grupo */
	{	}

	public void setCurso(int cur)
	// Asigna n� de curso
	{	}

	public void setSecci�n(char sec)
	// Asigna secci�n
	{	}

	public void setNota(int asig, int alum, double not)
	// Asigna la nota not de la asignatura asig del alumno alum
	{	}

	public void mostrarNotasAsignatura(int asig)
	// Visualiza las notas de todos los alumnos de la asignatura asig
	{	}

	public void mostrarNotasAlumno(int alum)
	// Visualiza las notas de todas las asignaturas del alumno alum
	{	}

}

