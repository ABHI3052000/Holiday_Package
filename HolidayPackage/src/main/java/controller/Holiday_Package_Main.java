package controller;

import java.util.*;
import java.io.*;
import java.sql.SQLException;

import exception.InvalidPackageException;
import model.Holiday_Package;
import utilityPackage.holidayPackageUtility;

public class Holiday_Package_Main {

	public static void main(String[] args) throws IOException, SQLException, InterruptedException {
		
		System.out.println("Reading data from Text file....");
		String filePath = "C:\\Users\\ashar292\\Desktop\\Training\\Files\\JAVA\\Holiday_Package\\VarshTourPackageDetails.txt";
		Thread.sleep(2000);
		
		System.out.println("\nInvalid Package ID's: ");
		List<Holiday_Package> listOfPackages = holidayPackageUtility.generatePackageCost(filePath);
		Thread.sleep(2000);
		
		System.out.println("\nValid Packages: ");
		listOfPackages.forEach(hp->System.out.println(hp.getPackageId()+" "+hp.getSourcePlace()+" "+hp.getDestinationPlace()+" "+
											  hp.getBasicFare()+" "+hp.getNoOfDays()+" "+hp.getPackageCost()));
		Thread.sleep(2000);
		System.out.println("\nData Retrieving Successful.");
		
		
		
//		if(holidayPackageUtility.storeDataToDB(listOfPackages)) {
//			System.out.println("Data Stored Successfully.");
//		}
//		else {
//			System.out.println("Connection Error! Please Check.....");
//		}
		
		Thread.sleep(2000);
		List<Holiday_Package> list = holidayPackageUtility.findPackagesWithMinimumNumberOfDays();
		list.forEach(hp->System.out.println(hp.getPackageId()+" "+hp.getSourcePlace()+" "+hp.getDestinationPlace()+" "+
											hp.getBasicFare()+" "+hp.getPackageCost()+" "+hp.getNoOfDays()));
		
	}

}
