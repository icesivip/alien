package model;

import java.util.ArrayList;

public class MasterPlanSchedule {
	
	public static final String LOTXLOT = "";
	public static final String ECONOMIC_ORDER_QUANTITY= "";
	public static final String PERIODS_OF_SUPPLY = "";
	public static final String PERIOD_ORDER_QUANTITY = "";
	public static final String LEAST_UNIT_COST = "";
	public static final String LEAST_TOTAL_COST = "";
	
	public static final String ANNUAL = "Anual"; 
	public static final String SOMETHING = "";
	public static final String MONTHLY = "Mensual";
	public static final String WEELLY = "Semanal";
	public static final String DAILY = "Diario";
	
	public static final String UNKNOWN = "Unknown";
	
	private double costArticle;
	private double preparationCost;
	private double maintenanceCost;
	private String periodicity;

	private ArrayList<Integer> weeklyArticles;
	private ArrayList<Integer> planOrders;
	
	
	
}
