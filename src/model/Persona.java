package model;

import java.util.Date;

public interface Persona {

	/**
	 * 
	 * @param categoria
	 */
	public void agregarCategoria(Categoria categoria);

	/**
	 * 
	 * @param adicionSalud
	 * @param adicionPension
	 * @param adicionSolidaridadPensional
	 */
	public void agregarDeducciones(float adicionSalud, float adicionPension, float adicionSolidaridadPensional);

	/**
	 * 
	 * @param a
	 * @param b
	 */
	public void agregarHorasExtras(HoraTrabajo a, Date b);

	public int getEdad();

	public String getNombre();

}