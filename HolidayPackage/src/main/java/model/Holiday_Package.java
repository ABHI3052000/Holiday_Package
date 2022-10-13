package model;

public class Holiday_Package {
	
	private String packageId;
	private String sourcePlace;
	private String destinationPlace;
	private double basicFare;
	private int noOfDays;
	private double packageCost;
	
	//Constructors
	public Holiday_Package() {
		
	}	
	public Holiday_Package(String packageId, String sourcePlace, String destinationPlace, double basicFare, int noOfDays,
			double packageCost) {
		super();
		this.packageId = packageId;
		this.sourcePlace = sourcePlace;
		this.destinationPlace = destinationPlace;
		this.basicFare = basicFare;
		this.noOfDays = noOfDays;
		this.packageCost = packageCost;
	}


	//Getters and Setters
	public String getPackageId() {
		return packageId;
	}
	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}
	public String getSourcePlace() {
		return sourcePlace;
	}
	public void setSourcePlace(String sourcePlace) {
		this.sourcePlace = sourcePlace;
	}
	public String getDestinationPlace() {
		return destinationPlace;
	}
	public void setDestinationPlace(String destinationPlace) {
		this.destinationPlace = destinationPlace;
	}
	public double getBasicFare() {
		return basicFare;
	}
	public void setBasicFare(double basicFare) {
		this.basicFare = basicFare;
	}
	public int getNoOfDays() {
		return noOfDays;
	}
	public void setNoOfDays(int noOfDays) {
		this.noOfDays = noOfDays;
	}
	public double getPackageCost() {
		return packageCost;
	}
	public void setPackageCost(double packageCost) {
		this.packageCost = packageCost;
	}
	
	//Methods
	public void calculatePackageCost() {
		if(this.noOfDays<=5) {
			double cost = this.noOfDays*this.basicFare;
			double gst = cost*0.12;
			this.packageCost = (cost+gst);
		}
		else if(this.noOfDays>5 && this.noOfDays<=8) {
			double cost = this.noOfDays*this.basicFare - this.noOfDays*this.basicFare*0.03;
			double gst = cost*0.12;
			this.packageCost = (cost+gst);
		}
		else if(this.noOfDays>8 && this.noOfDays<=10) {
			double cost = this.noOfDays*this.basicFare - this.noOfDays*this.basicFare*0.05;
			double gst = cost*0.12;
			this.packageCost = (cost+gst);
		}
		else if(this.noOfDays>10) {
			double cost = this.noOfDays*this.basicFare - this.noOfDays*this.basicFare*0.07;
			double gst = cost*0.12;
			this.packageCost = (cost+gst);
		}
	}
		

}
