package appproyecto1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class AppProyecto1 {

    static Scanner scanner = new Scanner(System.in); //Sirve para recoger texto por consola

    //Arreglos
    ArrayList<Integer> numeroDePago = new ArrayList<>();
    ArrayList<String> fecha = new ArrayList<>();
    ArrayList<String> hora = new ArrayList<>();
    ArrayList<String> cedula = new ArrayList<>();
    ArrayList<String> nombre = new ArrayList<>();
    ArrayList<String> apellido1 = new ArrayList<>();
    ArrayList<String> apellido2 = new ArrayList<>();
    ArrayList<Integer> numeroDeCaja = new ArrayList<>();
    ArrayList<Integer> tipoDeServicio = new ArrayList<>();
    ArrayList<String> numeroDeFactura = new ArrayList<>();
    ArrayList<Double> montoComision = new ArrayList<>();
    ArrayList<Double> montoAPagar = new ArrayList<>();
    ArrayList<Double> montoPagaCon = new ArrayList<>();
    ArrayList<Double> montoDeducido = new ArrayList<>();
    ArrayList<Double> montoVuelto = new ArrayList<>();

    //varible contadora
    int conta = 0;

    //VARIABLES UTILIDAD
    int tipoServicioReporte = 0;

    public static void main(String[] args) {
        new AppProyecto1().menu();
    }

    public void menu() {

        int opcion = 0;

        do {
            System.out.println("==================================================");
            System.out.println("                 MENU DE PRINCIPAL                ");
            System.out.println("==================================================");
            System.out.println(
                    "\n1.-Inicializar Vectores"
                    + "\n2.-Realizar Pagos\n"
                    + "3.-Consultar Pagos\n"
                    + "4.-Modificar Pagos\n"
                    + "5.-Eliminar Pagos\n"
                    + "6.-Submenú Reportes\n"
                    + "7.- Salir\n");
            System.out.println("==================================================");

            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    LimpiarPantalla();
                    InicializarVectores();
                    break;

                case 2:
                    LimpiarPantalla();
                    RealizarPagos();
                    break;
                case 3:
                    ConsultarPagos("CONSULTAR");
                    break;
                case 4:
                    ConsultarPagos("EDITAR");
                    break;
                case 5:
                    ConsultarPagos("ELIMINAR");
                    break;
                case 6:
                    LimpiarPantalla();
                    SubMenu();
                    break;
                case 7:
                    System.exit(0);
                    break;
            }
        } while (opcion != 8);

    }

    public void SubMenu() {

        int opcion = 0;

        do {
            System.out.println("==================================================");
            System.out.println("                SUBMENU DE REPORTES               ");
            System.out.println("==================================================");
            System.out.println(
                    "\nElige opción:"
                    + "\n1.-Ver todos los Pagos"
                    + "\n2.-Ver Pagos por tipo de Servicio\n"
                    + "3.-Ver Pagos por código de caja\n"
                    + "4.-Ver Dinero Comisionado por servicios\n"
                    + "5.-Regresar Menú Principal\n");
            System.out.println("==================================================");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    ReporteTodosLosPagos();
                    break;
                case 2:
                    ReportePorTipoDeServicio();
                    break;
                case 3:
                    
                    break;
                case 4:
                    break;
                case 5:
                    LimpiarPantalla();
                    menu();
                    break;

            }
        } while (opcion != 8);

    }

    public void InicializarVectores() {
        nombre.clear();
        apellido1.clear();
        apellido2.clear();
        cedula.clear();
        fecha.clear();
        hora.clear();
        numeroDePago.clear();
        numeroDeCaja.clear();
        tipoDeServicio.clear();
        numeroDeFactura.clear();
        montoComision.clear();
        montoAPagar.clear();
        montoDeducido.clear();
        montoPagaCon.clear();
        montoVuelto.clear();
        conta = 0;
        System.out.println(" ***Vectores inicializados*** ");
    }

    public void RealizarPagos() {
        if (numeroDePago.size() < 10) {
            IngresarDatos();
        } else {
            System.out.println("Vectores llenos");
        }
    }

    public void IngresarDatos() {
        numeroDePago.add(conta + 1);

        System.out.println("Ingrese la fecha: ");
        fecha.add(scanner.next());

        System.out.println("Ingrese la hora: ");
        hora.add(scanner.next());

        System.out.println("Ingrese la cedula: ");
        cedula.add(scanner.next());

        System.out.println("Ingrese el nombre: ");
        nombre.add(scanner.next());

        System.out.println("Ingrese el primer apellido: ");
        apellido1.add(scanner.next());

        System.out.println("Ingrese el segundo apellido: ");
        apellido2.add(scanner.next());

        //numero de caja
        int random = (int) (Math.random() * 3 + 1);
        numeroDeCaja.add(random);
        System.out.println("caja: " + numeroDeCaja.get(conta));

        //validar que la opción sea correcta solo se pueda ingresar 1,2,3
        ValidarServicioIngresado("INGRESAR", 0);

        System.out.println("Ingrese el número de factura: ");
        numeroDeFactura.add(scanner.next());

        System.out.println("Ingrese el monto a pagar: ");
        montoAPagar.add(Double.valueOf(scanner.next()));

        //Validar que el monto con el que se paga sea mayor al monto a pagar en el servicio
        ValidarMontoPagaCon(conta, "INGRESAR");

        CalcularComision();
        CalcularVuelto();
        ImprimirDatos(conta);
        conta++; //variable contadora que sirve de Quía para la posición de los arreglos.

    }

    public void ValidarServicioIngresado(String opcion, int indice) {
        int servicioIngresado = 0;
        do {
            System.out.println("Ingrese el tipo de servicio: " + " \n [1-Electricidad 2-Telefono 3-Agua]");
            servicioIngresado = Integer.parseInt(scanner.next());
            if (servicioIngresado <= 3 && servicioIngresado > 0) {

                switch (opcion) {
                    case "EDITAR":
                        tipoDeServicio.set(indice, servicioIngresado);
                        break;
                    case "INGRESAR":
                        tipoDeServicio.add(servicioIngresado);
                        break;
                    case "VALIDAR":
                        tipoServicioReporte = servicioIngresado;
                        break;
                    default:
                        throw new AssertionError();
                }

            } else {
                System.err.println("Error vuelva a ingresar el valor!");
            }
        } while (servicioIngresado > 3 || servicioIngresado < 0);
    }

    public void ValidarMontoPagaCon(int indice, String opcion) {
        double pagoCon = 0;
        do {
            System.out.println("Ingrese el monto con el que paga: ");
            pagoCon = Double.valueOf(scanner.next());
            if (pagoCon >= montoAPagar.get(indice)) {
                if (opcion == "EDITAR") {
                    montoPagaCon.set(indice, pagoCon);
                } else {
                    montoPagaCon.add(pagoCon);
                }

            } else {
                System.err.println("Error, el monto con el que paga debe ser mayor al monto a pagar, vuelva a ingresar el valor!");
            }
        } while (pagoCon < montoAPagar.get(indice));

    }

    public void CalcularComision() {
        double comision = 0;
        switch (tipoDeServicio.get(conta)) {
            case 1:
                comision = montoAPagar.get(conta) * 0.04;
                break;
            case 2:
                comision = montoAPagar.get(conta) * 0.055;
                break;
            case 3:
                comision = montoAPagar.get(conta) * 0.065;
                break;
            default:
                throw new AssertionError();
        }
        montoComision.add(comision);
        CalcularMontoDeducido();
    }

    public void CalcularMontoDeducido() {
        montoDeducido.add(montoAPagar.get(conta) - montoComision.get(conta));
        System.out.println("Monto deducido: " + montoDeducido.get(conta));
    }

    public void CalcularVuelto() {
        LimpiarPantalla();
        ImprimirDatos(conta);
        montoVuelto.add(montoPagaCon.get(conta) - montoAPagar.get(conta));
        System.out.println("Monto vuelto: " + montoVuelto.get(conta));
    }

    public void ConsultarPagos(String opcion) {
        System.out.println("==================================================");
        System.out.println("               CONSULTA DE SERVICIO               ");
        System.out.println("==================================================");
        System.out.println("Ingrese el número de pago a consultar:");

        int numeroPagoAConsultar = scanner.nextInt();

        for (int i = 0; i < numeroDePago.size(); i++) {
            if (numeroPagoAConsultar == numeroDePago.get(i)) {

                switch (opcion) {
                    case "EDITAR":
                        Editar(i);
                        break;
                    case "ELIMINAR":
                        Eliminar(i);
                        break;
                    case "CONSULTAR":
                        ImprimirDatos(i);
                        break;
                    default:
                        throw new AssertionError();
                }

            } else {
                LimpiarPantalla();
                System.err.println("Pago no se encuentra Registrado.");

            }
        }
        if (numeroDePago.size() == 0) {
            System.err.println("Vectores vacíos.");
        }
    }

    public void ImprimirDatos(int indice) {
        System.out.println("==================================================");
        System.out.println("                FACTURA DE SERVICIO               ");
        System.out.println("==================================================");
        System.out.println("A-Fecha: " + fecha.get(indice));
        System.out.println("B-Hora: " + hora.get(indice));
        System.out.println("C-Cedula: " + cedula.get(indice));
        System.out.println("D-Nombre: " + nombre.get(indice));
        System.out.println("E-Apellido1: " + apellido1.get(indice) + " " + " F-Apellido2: " + apellido2.get(indice));
        System.out.println("G-Tipo de servicio: " + tipoDeServicio.get(indice));
        System.out.println("H-Numero de factura: " + numeroDeFactura.get(indice));
        System.out.println("==================================================");

    }

    public static void LimpiarPantalla() {
        for (int clear = 0; clear < 100; clear++) {
            System.out.println("\b");
        }
    }

    public void Editar(int indice) {
        final ArrayList<String> opcionesEditar = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "F", "G", "H"));
        String editarOtroDato = "N";
        do {
            ImprimirDatos(indice);
            System.out.println("==================================================");
            System.out.println("                   EDITAR DATOS                   ");
            System.out.println("==================================================");

            System.out.println("Seleccóne opcion a editar: ");
            String opcionEditar = scanner.next();
            String nuevoDato = "";
            do {
                if (opcionesEditar.contains(opcionEditar.toUpperCase())) {
                    System.out.println("Ingrese el nuevo dato: ");
                    nuevoDato = scanner.next();
                } else {
                    System.err.println("Error, ingrese un valor valido para editar!");
                }
            } while (!opcionesEditar.contains(opcionEditar.toUpperCase()));
            switch (opcionEditar) {
                case "A":
                    fecha.set(indice, nuevoDato);
                    break;
                case "B":
                    hora.set(indice, nuevoDato);
                    break;
                case "C":
                    cedula.set(indice, nuevoDato);
                    break;
                case "D":
                    nombre.set(indice, nuevoDato);
                    break;
                case "E":
                    apellido1.set(indice, nuevoDato);
                    break;
                case "F":
                    apellido2.set(indice, nuevoDato);
                    break;
                case "G":
                    ValidarServicioIngresado("EDITAR", indice);
                    System.out.println("Al ingresar un nuevo tipo de servicio debe ingresar los siguientes datos: ");
                    System.out.println("Ingrese el nuevo número de factura: ");
                    numeroDeFactura.set(indice, scanner.next());

                    System.out.println("Ingrese el nuevo monto a pagar: ");
                    montoAPagar.set(indice, Double.valueOf(scanner.next()));

                    CalcularComision();
                    CalcularVuelto();

                    break;
                case "H":
                    System.out.println("Al ingresar un nuevo numero de factura debe ingresar los siguientes datos: ");

                    System.out.println("Ingrese el nuevo monto a pagar: ");
                    montoAPagar.set(indice, Double.valueOf(scanner.next()));
                    numeroDeFactura.set(indice, nuevoDato);
                    break;
                default:
                    throw new AssertionError();
            }

            System.out.println("Desea editar otro dato? ");
            System.out.println(" N = no // S = si ");
            editarOtroDato = scanner.next();

        } while (editarOtroDato.toUpperCase() != "N");

    }

    public void Eliminar(int indice) {
        LimpiarPantalla();
        System.out.println("==================================================");
        System.out.println("                 ELIMINAR DATOS                   ");
        System.out.println("==================================================");

        ImprimirDatos(indice);

        System.out.println("Esta seguro de eliminar el dato S/N?");
        String opcion = scanner.next();
        opcion = opcion.toUpperCase().trim();
        if ("S".equals(opcion)) {
            nombre.remove(indice);
            apellido1.remove(indice);
            apellido2.remove(indice);
            cedula.remove(indice);
            fecha.remove(indice);
            hora.remove(indice);
            numeroDePago.remove(indice);
            numeroDeCaja.remove(indice);
            tipoDeServicio.remove(indice);
            numeroDeFactura.remove(indice);
            montoComision.remove(indice);
            montoAPagar.remove(indice);
            montoDeducido.remove(indice);
            montoPagaCon.remove(indice);
            montoVuelto.remove(indice);
            System.out.println("La información ya fue eliminada!");

        } else {
            System.out.println("La información no fue eliminada!");

        }
    }

    public void ReporteTodosLosPagos() {
        System.out.println("=============================================================================");
        System.out.println("                         REPORTE TODOS LOS PAGOS                             ");
        System.out.println("=============================================================================");
        System.out.println("# pago Fecha/Hora  Pago Cedula   Nombre   Apellido1   Apellido2  Monto Recibo");
        System.out.println("==============================================================================");
        int totalRegistros = 0;
        double totalMontos = 0;
        for (int i = 0; i < numeroDeFactura.size(); i++) {
            System.out.println(" " + (i + 1) + "     " + fecha.get(i) + " " + hora.get(i) + "   " + cedula.get(i) + "   " + nombre.get(i) + "   " + apellido1.get(i) + "   " + apellido2.get(i) + "   " + montoAPagar.get(i));
            totalMontos += montoAPagar.get(i);
            totalRegistros++;
        }
        System.out.println("==============================================================================");
        System.out.println("Total de registros: " + totalRegistros + "                                     Monto total: " + totalMontos);
    }

    public void ReportePorTipoDeServicio() {
        System.out.println("=============================================================================");
        System.out.println("                         REPORTE TODOS LOS PAGOS                             ");
        System.out.println("=============================================================================");
        System.out.println("# pago Fecha/Hora  Pago Cedula   Nombre   Apellido1   Apellido2  Monto Recibo");
        System.out.println("==============================================================================");
        int totalRegistros = 0;
        double totalMontos = 0;

        ValidarServicioIngresado("VALIDAR", 0);
        for (int i = 0; i < numeroDeFactura.size(); i++) {
            if (tipoServicioReporte == tipoDeServicio.get(i)) {
                System.out.println(" " + (i + 1) + "     " + fecha.get(i) + " " + hora.get(i) + "   " + cedula.get(i) + "   " + nombre.get(i) + "   " + apellido1.get(i) + "   " + apellido2.get(i) + "   " + montoAPagar.get(i));
                totalMontos += montoAPagar.get(i);
                totalRegistros++;
            }
        }
        System.out.println("==============================================================================");
        System.out.println("Total de registros: " + totalRegistros + "                                     Monto total: " + totalMontos);
    }
}
