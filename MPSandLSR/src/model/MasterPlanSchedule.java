package model;

import java.util.ArrayList;

public class MasterPlanSchedule {
	
	public static final String LOTXLOT = "Lot for Lot";
	public static final String ECONOMIC_ORDER_QUANTITY= "Economic Order Quantity";
	public static final String PERIODS_OF_SUPPLY = "Periods Of Suply";
	public static final String PERIOD_ORDER_QUANTITY = "Period Order Quantity";
	public static final String LEAST_UNIT_COST = "Least Unit Cost";
	public static final String LEAST_TOTAL_COST = "Least Total Cost";
	
	public static final String ANNUAL = "Anual"; 
	public static final String SOMETHING = "";
	public static final String MONTHLY = "Mensual";
	public static final String WEEKLY = "Semanal";
	public static final String DAILY = "Diario";
	
	public static final String UNKNOWN = "Unknown";
	
	private String lotSizingMethod;
	private int leadTime;
	private int initialStock;
	private int securityStock;
	private String productCode;
	private String productName;
	private double costArticle;
	private double preparationCost;
	private double maintenanceCost;
	private String periodicity;

	private ArrayList<Integer> bruteRequirements;
	private ArrayList<Integer> scheduledReceptions;
	private ArrayList<Integer> scheduledAvailableStock;
	private ArrayList<Integer> netRequirements;
	private ArrayList<Integer> planOrders;
	private ArrayList<Integer> releasedPlanOrders;
	
	public MasterPlanSchedule(String lotSizingMethod, int leadTime, int initialStock, int securityStock,
			String productCode, String productName, double costArticle, double preparationCost, double maintenanceCost,
			String periodicity) {
		this.lotSizingMethod = lotSizingMethod;
		this.leadTime = leadTime;
		this.initialStock = initialStock;
		this.securityStock = securityStock;
		this.productCode = productCode;
		this.productName = productName;
		this.costArticle = costArticle;
		this.preparationCost = preparationCost;
		this.maintenanceCost = maintenanceCost;
		this.periodicity = periodicity;
	}
	
	
	
	
}
