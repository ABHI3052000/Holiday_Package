package utilityPackage;

import java.io.*;
import java.sql.*;
import java.util.*;

import dao.PackageDBHandler;
import exception.InvalidPackageException;
import model.Holiday_Package;

public class holidayPackageUtility {
	
//************************Validating PaCkage ID***********************************
	public static boolean validatePackageId(String id) {
		try {
			if(id.length()==7 && id.charAt(3)=='/') {
				return true;
			}
			else {
				throw new InvalidPackageException("Invalid Package Id");
			}
		}catch(InvalidPackageException ipe) {
			System.out.println(ipe.getMessage() + ": "+id);
		}
		return false;		
	}

//**************************************Generating Package Cost***********************************
	public static List<Holiday_Package> generatePackageCost (String filePath) throws IOException{
		FileInputStream fin =  new FileInputStream(filePath);
		Scanner sc = new Scanner(fin);
		
		List<Holiday_Package> listOfPackages = new ArrayList<>();
		
		while(sc.hasNextLine()) {
			String[] details = sc.nextLine().replaceAll(" ", "").split(",");
			
			String id = details[0];
			String src = details[1];
			String dest = details[2];
			double basicFare = Double.parseDouble(details[3]);
			int days = Integer.parseInt(details[4]);
			Holiday_Package p = new Holiday_Package();
			
			if(validatePackageId(id)){
				p.setPackageId(id);
				p.setSourcePlace(src);
				p.setDestinationPlace(dest);
				p.setBasicFare(basicFare);
				p.setNoOfDays(days);
				p.calculatePackageCost();
				
				listOfPackages.add(p);
			}
	
		}
		System.out.println("\n****Generating Package Cost****");
		return listOfPackages;
	}

//************************Storing Data to HolidayPackage DB***********************************	
	public static boolean storeDataToDB(List<Holiday_Package> list) throws SQLException {
		System.out.println("\nConnecting to Holiday Package DB....");
		Connection conn = PackageDBHandler.connectToHolidayDB();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\nStoring Valid Package ID's Data to HolidayPackage DB....");
		
		int count = 0;
		for(Holiday_Package hp : list) {
			PreparedStatement ps =  conn.prepareStatement("insert into HolidayPackage values(?,?,?,?,?,?)");
			ps.setString(1, hp.getPackageId());
			ps.setString(2, hp.getSourcePlace());
			ps.setString(3, hp.getDestinationPlace());
			ps.setDouble(4, hp.getBasicFare());
			ps.setInt(5, hp.getNoOfDays());
			ps.setDouble(6, hp.getPackageCost());
			
			ps.executeUpdate();
			count++;
		}
		if(count==list.size()) {
			return true;
		}
		return false;
	}	


//***********************************Finding Packages with Minimum Days***********************************	
	public static List<Holiday_Package> findPackagesWithMinimumNumberOfDays() throws SQLException{
		System.out.println("\nConnecting to Holiday Package DB");
		Connection conn = PackageDBHandler.connectToHolidayDB();
		
		PreparedStatement ps =  conn.prepareStatement("select * from HolidayPackage");
		ResultSet rs = ps.executeQuery();
		
		int minDays = Integer.MAX_VALUE;
		while(rs.next()) {
			int days = rs.getInt(5);
			if(days<minDays) {
				minDays = days;
			}
		}
		
		PreparedStatement ps1 =  conn.prepareStatement("select * from HolidayPackage where no_of_days="+minDays);
		ResultSet rs1 = ps1.executeQuery();
		
		List<Holiday_Package> list =  new ArrayList<>();
		while(rs1.next()) {
			String id = rs1.getString(1);
			String src = rs1.getString(2);
			String dest = rs1.getString(3);
			Double fare = rs1.getDouble(4);
			int days = rs1.getInt(5);
			Double cost = rs1.getDouble(6);
			
			
			Holiday_Package hp = new Holiday_Package(id,src,dest,fare,days,cost);
			list.add(hp);
		}
		System.out.println("\n***************Packages with Minimum Days***************");
		return list;
	}

}
