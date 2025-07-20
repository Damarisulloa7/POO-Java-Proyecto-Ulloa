/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.damarisulloa.proyecto.servicio;

import com.damarisulloa.proyecto.modelo.Tarea;
import java.util.ArrayList;
import java.util.List;
public class TareaServicio {
    private final List<Tarea> tareas = new ArrayList<>();

    public void registrarTarea(Tarea tarea) {
        tareas.add(tarea);
    }

    public List<Tarea> obtenerTodasLasTareas() {
        return new ArrayList<>(tareas);
    }

    public void marcarTareaCompletada(int indice) {
        tareas.get(indice).setCompletada(true);
    }

    public void eliminarTarea(int indice) {
        tareas.remove(indice);
    }
}
