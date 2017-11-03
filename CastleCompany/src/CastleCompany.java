import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CastleCompany {
	
	int n = 0;	
	List<Integer> landCoordinates = new ArrayList<Integer>();
	
	CastleCompany(int size, int array[])
	{
		n = size;
		for (int i=0; i<n; i++)
		{
			landCoordinates.add(array[i]);			
		}		
	}
	
	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public List<Integer> getLandCoordinates() {
		return landCoordinates;
	}

	public void setLandCoordinates(List<Integer> landCoordinates) {
		this.landCoordinates = landCoordinates;
	}
	
	private List<Integer> removeDuplicates(List<Integer> landCoordinates)
	{
		int n = landCoordinates.size();
		List<Integer> distinctCoordinates = new ArrayList<Integer>();
		
		distinctCoordinates.add(landCoordinates.get(0));
		for (int i=1; i<n; i++)
		{
			if (landCoordinates.get(i-1) != landCoordinates.get(i))
			{
				distinctCoordinates.add(landCoordinates.get(i));				
			}						
		}
		return distinctCoordinates;
	}
	
	private int getPeaks(List<Integer> distinctCoordinates)
	{
		int count=0, pre=0, curr=0, post=0;
		int n = distinctCoordinates.size();
		for (int i=2; i<n; i++)
		{			
			pre = distinctCoordinates.get(i-2);
			curr = distinctCoordinates.get(i-1);
			post = distinctCoordinates.get(i);
			if ((pre < curr) && (curr > post))
			{				
				count++;
			}			
		}
		return count;
	}
	
	private int getValleys(List<Integer> distinctCoordinates)
	{
		int count=0, pre=0, curr=0, post=0;
		int n = distinctCoordinates.size();
		for (int i=2; i<n; i++)
		{
			pre = distinctCoordinates.get(i-2);
			curr = distinctCoordinates.get(i-1);
			post = distinctCoordinates.get(i);
			if ((pre > curr) && (curr < post))
			{
				count++;
			}			
		}
		return count;
	}
	
	public int getCastles(List<Integer> landCoordinates)	
	{
		List<Integer> distinctCoordinates = new ArrayList<Integer>();
		distinctCoordinates = removeDuplicates(landCoordinates);
		int n = distinctCoordinates.size();
		int count=0, peaks=0, valleys=0;
		
		if ((n > 0) && (n < 3))
		{
			count = 1;		
		}
		else
		{
			peaks = getPeaks(distinctCoordinates);
			valleys = getValleys(distinctCoordinates);			
			count = 1 + peaks + valleys;
			System.out.println("No of peaks:" + peaks);
			System.out.println("No of valleys:" + valleys);
		}		
		return count;		
	}
	
	public static int[] readCoordinates()
	{
		System.out.println("Enter no of coordinates:");
		Scanner s1 = new Scanner(System.in);
		int count = s1.nextInt();
		s1.nextLine();
		
		int [] array = new int[count];
		System.out.println("Enter coordinates:");
        Scanner s2 = new Scanner(s1.nextLine());        
        for (int i = 0; i < count; i++) 
        {
            if (s2.hasNextInt()) {
                array[i] = s2.nextInt();
            }
        }
        s1.close();
        s2.close();
        
        return array;
	}
	
	public static void main(String[] args)
	{		
		int[] array = CastleCompany.readCoordinates();
		int n = array.length;
		CastleCompany cc = new CastleCompany(n, array);
		int castles = cc.getCastles(cc.getLandCoordinates());
		System.out.println("No of castles:" + castles);
	}
}
