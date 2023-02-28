package appproyecto1;

import java.util.ArrayList;
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
    ArrayList<Double> montoDeducido = new ArrayList<>();

    //varible contadora
    int conta = 1;
    
    public static void main(String[] args) {
        new AppProyecto1().menu();
    }

    public void menu() {

        int select = -1;

        while (select != 0) {

            try {
                System.out.println("************************\nElige opción:\n1.-Inicializar Vectores"
                        + "\n2.-Realizar Pagos\n"
                        + "3.-Consultar Pagos\n"
                        + "4.-Modificar Pagos\n"
                        + "5.-Eliminar Pagos\n"
                        + "6.-Submenú Reportes\n"
                        + "7.- Salir\n************************");
                select = Integer.parseInt(scanner.nextLine());
                switch (select) {
                    case 1:
                        InicializarVectores();
                        break;

                    case 2:
                        RealizarPagos();
                        break;
                    case 3:
                        ConsultarPagos();
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        SubMenu();
                        break;
                    case 7:
                        break;

                }
            } catch (Exception e) {
                System.err.println("Opps! un error" + e);
            }

        }
    }

    public void SubMenu() {

        int select = -1;

        while (select != 0) {

            try {
                System.out.println("************************\nElige opción:"
                        + "\n1.-Ver todos los Pagos"
                        + "\n2.-Ver Pagos por tipo de Servicio\n"
                        + "3.-Ver Pagos por código de caja\n"
                        + "4.-Ver Dinero Comisionado por servicios\n"
                        + "5.-Regresar Menú Principal\n");
                select = Integer.parseInt(scanner.nextLine());
                switch (select) {
                    case 1:
                        break;

                    case 2:
                        break;
                    case 3:

                    case 4:
                        break;
                    case 5:
                        menu();
                        break;

                }
            } catch (Exception e) {
                System.err.println("Opps! un error" + e);
            }

        }
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
        numeroDePago.add(conta);

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
        int servicioIngresado = 0;
        do {
            System.out.println("Ingrese el tipo de servicio: " + " \n [1-Electricidad 2-Telefono 3-Agua]");
            servicioIngresado = Integer.parseInt(scanner.next());
            if (servicioIngresado <= 3 && servicioIngresado > 0) {
                tipoDeServicio.add(servicioIngresado);
            } else {
                System.err.println("Error vuelva a ingresar el valor!");
            }
        } while (servicioIngresado > 3 || servicioIngresado < 0);

        System.out.println("Ingrese el número de factura: ");
        numeroDeFactura.add(scanner.next());

        System.out.println("Ingrese el monto a pagar: ");
        montoAPagar.add(Double.valueOf(scanner.next()));

        CalcularComision();

        conta++; //variable contadora que sirve de Quía para la posición de los arreglos.
        

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
    }

    public void ConsultarPagos() {
        System.out.println("**** Consulta de pagos ****");
        System.out.println("Ingrese el número de pago a consultar");
        Integer numeroPagoAConsultar = Integer.valueOf(scanner.nextLine());

        for (int i = 0; i < numeroDePago.size(); i++) {
            if (numeroPagoAConsultar == numeroDePago.get(i)) {
                ImprimirDatos(i);
            } else {
                System.err.println("Pago no encontrado!");

            }
        }
    }

    public void ImprimirDatos( int i ) {
        System.out.println("**** Impresión de datos ****");
        System.out.println("-Numero de pago: " + numeroDePago.get(i) );
        
    }

}
