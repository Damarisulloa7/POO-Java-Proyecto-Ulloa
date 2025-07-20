/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.damarisulloa.proyecto.dashboard;

import com.damarisulloa.proyecto.modelo.Tarea;
import com.damarisulloa.proyecto.servicio.TareaServicio;
import java.util.List;
import java.util.Scanner;

public class Dashboard {
    private final TareaServicio tareaServicio;
    private final Scanner scanner;

    public Dashboard() {
        this.tareaServicio = new TareaServicio();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Método principal que inicia la aplicación.
     */
    public void iniciar() {
        mostrarBienvenida();
        boolean salir = false;
        
        while (!salir) {
            mostrarMenu();
            int opcion = obtenerOpcionUsuario();
            salir = procesarOpcion(opcion);
        }
        
        mostrarDespedida();
        scanner.close();
    }

    private void mostrarBienvenida() {
        System.out.println("**************************************");
        System.out.println("*  SISTEMA DE GESTIÓN DE TAREAS POO  *");
        System.out.println("*                                    *");
        System.out.println("*  Versión 2.0 - Funcional           *");
        System.out.println("**************************************");
    }

    private void mostrarMenu() {
        System.out.println("\nMENÚ PRINCIPAL");
        System.out.println("1. Registrar nueva tarea");
        System.out.println("2. Listar todas las tareas");
        System.out.println("3. Marcar tarea como completada");
        System.out.println("4. Eliminar tarea");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción (1-5): ");
    }

    private int obtenerOpcionUsuario() {
        while (!scanner.hasNextInt()) {
            System.out.println("¡Error! Ingrese un número válido.");
            scanner.next(); // Limpiar el buffer
        }
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del enter
        return opcion;
    }

    private boolean procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                registrarTarea();
                break;
            case 2:
                listarTareas();
                break;
            case 3:
                marcarTareaCompletada();
                break;
            case 4:
                eliminarTarea();
                break;
            case 5:
                return true;
            default:
                System.out.println("Opción no válida. Intente nuevamente.");
        }
        return false;
    }

    private void registrarTarea() {
        System.out.println("\n--- REGISTRAR NUEVA TAREA ---");
        System.out.print("Nombre de la tarea: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();
        
        System.out.print("Fecha de vencimiento (YYYY-MM-DD): ");
        String fechaVencimiento = scanner.nextLine();
        
        Tarea nuevaTarea = new Tarea(nombre, descripcion, fechaVencimiento);
        tareaServicio.registrarTarea(nuevaTarea);
     
        
        System.out.println("\n¡Tarea registrada con éxito!");
    }

    private void listarTareas() {
        List<Tarea> tareas = tareaServicio.obtenerTodasLasTareas();
        
        if (tareas.isEmpty()) {
            System.out.println("\nNo hay tareas registradas.");
            return;
        }
        
        System.out.println("\n--- LISTA DE TAREAS ---");
        for (int i = 0; i < tareas.size(); i++) {
            Tarea t = tareas.get(i);
            System.out.printf("%d. %s - %s (Vence: %s) %s%n",
                i + 1,
                t.getNombre(),
                t.getDescripcion(),
                t.getFechaVencimiento(),
                t.isCompletada() ? "[COMPLETADA]" : "[PENDIENTE]");
        }
    }

    private void marcarTareaCompletada() {
        listarTareas();
        if (tareaServicio.obtenerTodasLasTareas().isEmpty()) return;
        
        System.out.print("\nIngrese el número de la tarea a marcar como completada: ");
        try {
            int indice = scanner.nextInt() - 1;
            scanner.nextLine(); // Limpiar buffer
            
            if (indice >= 0 && indice < tareaServicio.obtenerTodasLasTareas().size()) {
                tareaServicio.marcarTareaCompletada(indice);
                System.out.println("Tarea marcada como completada.");
            } else {
                System.out.println("Número de tarea inválido.");
            }
        } catch (Exception e) {
            System.out.println("Error: Debe ingresar un número válido.");
            scanner.nextLine(); // Limpiar buffer en caso de error
        }
    }

    private void eliminarTarea() {
        listarTareas();
        if (tareaServicio.obtenerTodasLasTareas().isEmpty()) return;
        
        System.out.print("\nIngrese el número de la tarea a eliminar: ");
        try {
            int indice = scanner.nextInt() - 1;
            scanner.nextLine(); // Limpiar buffer
            
            if (indice >= 0 && indice < tareaServicio.obtenerTodasLasTareas().size()) {
                tareaServicio.eliminarTarea(indice);
                System.out.println("Tarea eliminada con éxito.");
            } else {
                System.out.println("Número de tarea inválido.");
            }
        } catch (Exception e) {
            System.out.println("Error: Debe ingresar un número válido.");
            scanner.nextLine(); // Limpiar buffer en caso de error
        }
    }

    private void mostrarDespedida() {
        System.out.println("\nGracias por usar el Sistema de Gestión de Tareas");
        System.out.println("¡Hasta pronto!");
    }

    /**
     * Método main para ejecutar la aplicación.
     */
    public static void main(String[] args) {
        Dashboard dashboard = new Dashboard();
        dashboard.iniciar();
    }
}
    /**
     * Método principal que inicia la aplicación.
     */
    