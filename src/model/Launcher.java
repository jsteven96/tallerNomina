package model;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Scanner;


/**
 * main
 */public class Launcher {
	 
	 public static String leerString(Scanner sc) {
		 String line;
		 while( (line = sc.nextLine()) == "") {
			 System.out.println("Ingrese algo");
		 }
		 return line;
	 }
	 
	 public static int leerEntero(Scanner sc, int minimo, int maximo) {
		 
		 int entero;
		 while(true) {
			 
			 try {
				 entero = Integer.parseInt(sc.nextLine());
				 break;
			} catch (NumberFormatException e) {
				System.out.println("Por favor ingrese una opción válida");
				continue;
			}
            
		 }
		 
		 while(entero < minimo || entero > maximo) {
			 System.out.println("Ingrese un numero entre " + minimo + " y " + maximo);
			 try {
				 entero = Integer.parseInt(sc.nextLine());
				 continue;
			} catch (NumberFormatException e) {
				System.out.println("Por favor ingrese un valor válido");
				continue;
			}
		 }
		return entero;
	 }
	 
    public static void main(String[] args) throws Exception {

        Empresa empresa = new Empresa();

        boolean bandera = true;

        Scanner sc = new Scanner(System.in);
        while (bandera) {
            System.out.println();
            System.out.println("Opcion 1: Agregar empleado");
            System.out.println("Opcion 2: Consultar salario empleado");
            System.out.println("Opcion 3: Consultar suma total de salarios");
            System.out.println("Opcion 4: Eliminar empleado");
            System.out.println("Opcion 5: Habilitar empleado");
            System.out.println("Opcion 6: Mostrar todos los empleados");
            System.out.println("Opcion 7: Agregar las horas extras a un empleado");
            System.out.println("Opcion 8: Salir");
            
            
            
            int opcion = 0;
            opcion = leerEntero(sc, 1, 8);
            if(opcion == -1) {
            	continue;
            }

            switch (opcion) {
            case 1:
                System.out.println("Ingrese el documento del empleado");
                String documento = leerString(sc);
                System.out.println("Ingrese el nombre del empleado");
                String nombre = leerString(sc);
                
                int edad;
                while(true) {
                	System.out.println("Ingrese la edad del empleado");
                    
                    edad = leerEntero(sc, 1, Integer.MAX_VALUE);
                    if(edad == -1) {
                    	System.out.println("Ingrese una edad válida");
                    	continue;
                    }else {
                    	break;
                    }
                }
                
                Empleado empleado = new Empleado(documento, nombre, edad);
                empresa.agregarEmpleados(empleado);

                // Seleccionar cargo del empleado en la empresa

                boolean banderaCargo = true;
                System.out.println("Seleccione los cargos del empleado");
                while (banderaCargo) {
                    System.out.println("Opcion 1: Gerente");
                    System.out.println("Opcion 2: Director de proyecto");
                    System.out.println("Opcion 3: Scrum Master");
                    System.out.println("Opcion 4: Desarrollador Senior");
                    System.out.println("Opcion 5: Desarrollador Junior");
                    int horasExtrasPregunta = leerEntero(sc, 1, 5);
                    Categoria categoria;
                    switch (horasExtrasPregunta) {
                    case 1:
                        categoria = Categoria.GERENTE;
                        break;
                    case 2:
                        categoria = Categoria.DIRECTOR;
                        break;
                    case 3:
                        categoria = Categoria.SCRUM_MASTER;
                        break;
                    case 4:
                        categoria = Categoria.DESARROLLADOR_SENIOR;
                        break;
                    case 5:
                        categoria = Categoria.DESARROLLADOR_JUNIOR;
                        break;
                    default:
                        categoria = Categoria.DESARROLLADOR_JUNIOR;
                    }
                    empleado.agregarCategoria(categoria);

                    System.out.println("Â¿Desea seguir agregando categorias?");
                    System.out.println("Opcion 1: Si");
                    System.out.println("Opcion 2: No");
                    int categoriaPregunta = leerEntero(sc, 1, 2);

                    switch (categoriaPregunta) {
                    case 2:
                        banderaCargo = false;
                        break;
                    }

                }

                // ------------------------------------------------------------------------------------------------------

                // Indicar las deducciones del empleado

                System.out.println("Â¿El empleado agregarÃ¡ deducciones extras?");
                System.out.println("Opcion 1: Si");
                System.out.println("Opcion 2: No");
                int deduccionesExtraPregunta = leerEntero(sc, 1, 2);

                switch (deduccionesExtraPregunta) {
                case 1:
                    System.out.println("Ingrese la salud extra: El porcentaje en numero entero");
                    int adicionSalud = leerEntero(sc, 1, 92);
                    System.out.println("Ingrese la pension extra: El porcentaje en numero entero");
                    int adicionPension = leerEntero(sc, 1, 92);
                    System.out.println("Ingrese la solidaridad pensional extra: El porcentaje en numero entero");
                    int adicionSaludPensional = leerEntero(sc, 1, 99);

                    empleado.agregarDeducciones(adicionPension / 100, adicionSalud / 100, adicionSaludPensional / 100);
                    break;
                case 2:
                    break;
                }

                // ------------------------------------------------------------------------------------------------------

                empresa.agregarEmpleados(empleado);
                break;

            case 2:
                // Buscar empleado
                System.out.println("Ingrese el documento del empleado a buscar");
                String documentoBuscar = leerString(sc);
                
                Empleado empleadoEncontrado = empresa.buscarEmpleado(documentoBuscar);

                if ( empleadoEncontrado != null && empleadoEncontrado.isActivo()) {
                    imprimirEmpleado(empleadoEncontrado);
                } else {
                    System.out.println("El empleado no está activo o no existe");
                }

                break;
            case 3:
                // Calcular salario total de empleados
                System.out.println("Ingrese la fecha de los salarios a calcular");
                System.out.println("Ingrese el aÃ±o:");
                int year = leerEntero(sc, 1900, LocalDate.now().getYear() );
                System.out.println("Ingrese el mes:");
                int month = leerEntero(sc, 1, 12);
                float salarioTotal = 0f;
                for (Empleado empleadoSalario : empresa.getEmpleados().values()) {
                    if (empleadoSalario.isActivo()) {
                        float salarioIndividual = empleadoSalario.calcularsalario(
                                new SimpleDateFormat("dd/MM/yyyy").parse(year + "/" + month + "/1"));
                        salarioTotal += salarioIndividual;
                        System.out.println("Nombre: " + empleadoSalario.getNombre() + " Salario: " + salarioIndividual);
                    } else {
                        continue;
                    }

                }
                System.out.println("El salario total de todos los empleados es:");
                System.out.println(salarioTotal);
                break;
            case 4:
                System.out.println("Ingrese el documento del empleado a eliminar");
                String documentoEliminar = leerString(sc);
                Empleado empleadoTemp = empresa.buscarEmpleado(documentoEliminar);
                if (empleadoTemp == null) {
                	System.out.println("No existe un empleado con este documento");
                }else {
                	empleadoTemp.inhabilitar();
                }
                break;
                
            case 5:
                System.out.println("Ingrese el documento del empleado a habilitar");
                String documentoHabilitar = leerString(sc );
                Empleado empleadoTempo = empresa.buscarEmpleado(documentoHabilitar);
                if(empleadoTempo == null) {
                	System.out.println("No existe un empleado con este documento");
                }else {
                	empleadoTempo.habilitar();
                }
                
                break;
            case 6:
                for (Empleado emp : empresa.getEmpleados().values()) {
                    imprimirEmpleado(emp);
                    System.out.println();
                    System.out.println();
                }
                break;
            case 7:
                System.out.println("Ingrese el documento del empleado a agregarle las horas extras");
                String documentoHoras = leerString(sc);
                Empleado empleadoTempl = empresa.buscarEmpleado(documentoHoras);
                if(empleadoTempl == null) {
                	System.out.println("No existe un empleado con este documento, intente nuevamente");
                	
                }else {
                	Empleado empleadoHoras = empresa.buscarEmpleado(documentoHoras);
                	// Indicar las horas extras trabajadas por el empleado
                    System.out.println("Ingrese las horas nocturnas");
                    int nocturnas = leerEntero(sc, 0, Integer.MAX_VALUE );
                    System.out.println("Ingrese las horas dirunas festivas");
                    int dirFestiva = leerEntero(sc, 0, Integer.MAX_VALUE );
                    System.out.println("Ingrese las horas nocturnas festivas");
                    int nocFestiva = leerEntero(sc, 0, Integer.MAX_VALUE );

                    System.out.println("Ingrese el aÃ±o correspondiente a las horas");
                    int yearHoras = leerEntero(sc, 1900, LocalDate.now().getYear() );
                    System.out.println("Ingrese el mes correspondiente a las horas");
                    int monthHoras = leerEntero(sc, 1, 12 );

                    HoraTrabajo horasExtras = new HoraTrabajo(nocturnas, dirFestiva, nocFestiva);
                    empleadoHoras.agregarHorasExtras(horasExtras,
                    new SimpleDateFormat("dd/MM/yyyy").parse(yearHoras+ "/" + monthHoras + "/1"));
                }
                
                break;
            case 8:
                bandera = false;
                break;
            default:
                continue;
            }
            System.out.flush();

        }

        sc.close();

    }

    private static void imprimirEmpleado(Empleado empleado) {
        // Imprimir nombre
        System.out.println("Nombre:");
        System.out.println(empleado.getNombre());

        // Imprimir cargos asociados al empleado
        for (Categoria categoria : empleado.getCategorias()) {
            System.out.println("Cargo: " + categoria.getNombre());
            System.out.println("Salario: " + categoria.getSalarioBase());
        }

        // Impirmir deducciones del empleado
        Deduccion deduccion = empleado.getDeducciones();
        System.out.println("Porcentaje de pension: " + deduccion.getPension());
        System.out.println("Porcentaje de salud: " + deduccion.getSalud());
        System.out.println("Porcentaje de solidaridad pensional: " + deduccion.getSolidaridadPensional());

        // Imprimir salario del empleado
        System.out.println("Historial de salarios:");
        for (Date fecha : empleado.getSalarioHistorial().keySet()) {
            String key = fecha.toString();
            String value = empleado.getSalarioHistorial().get(fecha).toString();
            System.out.println(key + ": " + value);
        }
    }

}