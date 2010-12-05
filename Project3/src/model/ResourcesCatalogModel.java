package model;

import java.util.*;
import java.io.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;



import resourcesCatalog.jaxb.Bookings;
import resourcesCatalog.jaxb.Dataroot;
import resourcesCatalog.jaxb.IterativeBookings;
import resourcesCatalog.jaxb.Resources;
import resourcesCatalog.jaxb.Users;

/**
 * 
 * @author Raeid Saqur
 * @version 1.0
 *
 */
public class ResourcesCatalogModel 
{

	private static Map<Integer, Resources> resources;
	private static Map<Integer, Bookings> bookings;
	private static Map<Integer, Users> users;
	private static Map<Integer, IterativeBookings>iterativeBookings;
	
	private static Map<Integer, List<Bookings>>resourceIDtoBookings;
	private static Map<Integer, List<Bookings>>userIDtoBookings;
	private static Map<Integer, List<Bookings>>iterativeBookingsIDtoBookings;
	
	public ResourcesCatalogModel(String catalogFile) throws JAXBException, FileNotFoundException, IOException  
	{
		
		JAXBContext jc = JAXBContext.newInstance("resourcesCatalog.jaxb");
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		//String rCatalogFilePath = catalogFile + System.getProperty("file.separator") + "ResourceCatalog.xml";
		Dataroot data = (Dataroot)unmarshaller.unmarshal(new File(catalogFile));
		
		resources = new TreeMap<Integer, Resources>(new ResourcesComparator());
		bookings = new TreeMap<Integer, Bookings> (new BookingsComparator());
		users = new TreeMap <Integer, Users>(new UsersComparator());
		iterativeBookings = new TreeMap<Integer, IterativeBookings>(new IterativeBookingsComparator());
		
		resourceIDtoBookings = new TreeMap<Integer, List<Bookings>>();
		userIDtoBookings = new TreeMap<Integer, List<Bookings>>();;
		iterativeBookingsIDtoBookings = new TreeMap<Integer, List<Bookings>>();
		
		/*INITIAL PROCESSING OF THE DATA FROM CATALOG. No Error validation done*/
		loadBookingsMap(data.getBookings());
		loadUsersMap(data.getUsers());
		loadResourcesMap(data.getResources());
		loadIterativeBookingsMap(data.getIterativeBookings());
		
		populateBookingsMap(data.getBookings());
		
		System.out.println("Done populating the data. Let's have a look to what we've got!");
		System.out.println(iterativeBookingsIDtoBookings.get(1));
	}		
	
	//the most important method, add a booking and update all proper references to Entities
	protected synchronized void addBooking()
	{
		//generate unique number for bookingID
	}
	
	private void populateBookingsMap(List<Bookings> allBookings) 
	{
		//create a hashmap that links the resources, users and iterativeBookings to Bookings.
		for(Bookings b: allBookings)
		{
			int uid = b.getUserID();
			int rid = b.getResourceID();
			
			if(userIDtoBookings.get(uid) == null)
			{
				ArrayList<Bookings> temp = new ArrayList<Bookings>();
				temp.add(b);
				userIDtoBookings.put(uid, temp);
				
			}else if(!(userIDtoBookings.get(uid).contains(b)))
			{
				userIDtoBookings.get(uid).add(b);
			}						
			if(resourceIDtoBookings.get(rid) == null)
			{
				ArrayList<Bookings> temp = new ArrayList<Bookings>();
				temp.add(b);
				resourceIDtoBookings.put(rid, temp);
				
			}else if(!(resourceIDtoBookings.get(rid).contains(b)))
			{
				resourceIDtoBookings.get(rid).add(b);
			}
			
			if(b.getIterativeBookingsID() != null)
			{
				int ibid = b.getIterativeBookingsID();
				if(iterativeBookingsIDtoBookings.get(ibid) == null)
				{
					ArrayList<Bookings> temp = new ArrayList<Bookings>();
					temp.add(b);
					iterativeBookingsIDtoBookings.put(ibid, temp);

				}else if(!(iterativeBookingsIDtoBookings.get(ibid).contains(b)))
				{
					iterativeBookingsIDtoBookings.get(ibid).add(b);
				}
			}
			
		}
		
	}




	private void loadIterativeBookingsMap(
			List<IterativeBookings> iBookings) {
		Iterator<IterativeBookings> iBkItr = iBookings.iterator();
		while(iBkItr.hasNext())
		{
			IterativeBookings temp = iBkItr.next();
			iterativeBookings.put(temp.getIterativeBookingsID(), temp);	
		}	
		
	}
	private void loadBookingsMap(List<Bookings> bks) {
		
		Iterator<Bookings> bItr = bks.iterator();
		while(bItr.hasNext())
		{
			Bookings booking = bItr.next();
			bookings.put(booking.getBookingID(), booking);
			
		}	
	}
	private void loadResourcesMap(List<Resources> resources) {
		
		Iterator<Resources> rItr = resources.iterator();
		while(rItr.hasNext())
		{
			Resources r = rItr.next();
			ResourcesCatalogModel.resources.put(r.getResourceID(), r);		
		}	
	}
	private void loadUsersMap(List<Users> users2) {
		
		for (Users u : users2)
		{
			users.put(u.getUserID(), u);
		}	
	}

	

}

class IterativeBookingsComparator implements Comparator
{
	@Override
	public int compare(Object o1, Object o2)	
	{
		//TODO nothing.
		return 0;
	}
	
}
class ResourcesComparator implements Comparator
{

	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		return 0;
	}

}
class BookingsComparator implements Comparator
{

	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		return 0;
	}

}
class UsersComparator implements Comparator
{

	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		return 0;
	}

}


