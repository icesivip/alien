package model;

import java.util.ArrayList;

public class MasterPlanSchedule {
	
	public static final String LOTEXLOTE = "";
	public static final String CANTIDAD_ORDEN_ECONOMICO = "";
	public static final String PERIODO_DE_SUMINISTRO = "";
	public static final String CANTIDAD_ORDEN_PERIODO = "";
	public static final String MENOR_COSTO_UNITARIO = "";
	public static final String COSTO_TOTAL_MINIMO = "";
	
	public static final String ANUAL = "Anual";
	public static final String ALGO = "";
	public static final String MENSUAL = "Mensual";
	public static final String SEMANAL = "Semanal";
	public static final String DIARIO = "Diario";
	
	public static final String UNKNOWN = "Unknown";
	
	private double costoArticulo;
	private double costoPreparacion;
	private double costoMantenimiento;
	private String periodicidad;

	private ArrayList<Integer> articulosSemanales;
	private ArrayList<Integer> planPedidos;
	
	
	
}
