package project;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

import java.text.DecimalFormat;


public class Project {
	
	static int size;
	
	//method checks if array is sorted.
	public static boolean issorted(String[] arr)
	{
		for(int i=0; i<size-1; i++)
		{
			if(arr[i].compareToIgnoreCase(arr[i+1]) < 0)
				return false;
		}
		return true;
	}
	
	//method to swap values
	public static void swap(String[] arr, int n1, int n2)
	{
		String temp = arr[n1];
		arr[n1] = arr[n2];
		arr[n2] = temp;
	}
	
	//method to print all elements of the array.
	public static void print(String[] arr)
	{
		
		
		System.out.println("\nThe array is: ");
		for(int index = 0; index < size; index++)
		{
			System.out.print(arr[index] + "  ");
		}
		
	}
 	
	
	
	
	
	

	int ssort(String[] arr)
	// Sorts the values array using the selection sort algorithm.
	{
		int compare = 0;
		int endIndex = size - 1;
		for (int current = 0; current < endIndex; current++)
		{
			int min = current;
			for(int j = current +1; j <= endIndex; j++)
			{
				compare++;
				if(Float.parseFloat(arr[j]) < Float.parseFloat(arr[min]))
				{
					min = j;
				}
			}
			swap(arr, current, min);
		}
		return compare;
	}
	
	
	
	
	
	
	static int bubbleUp(String[] arr, int startIndex, int endIndex)
	// Switches adjacent pairs that are out of order
	// between values[startIndex]to values[endIndex]
	// beginning at values[endIndex].

	{
		int comparisons = 0;
		for (int index = endIndex; index > startIndex; index--)
		{
			comparisons++;
			if (Float.parseFloat(arr[index]) < Float.parseFloat(arr[index-1]))
				swap(arr, index, index - 1);
		}
	    return comparisons;
	}

	int bsort(String[] arr)
	// Sorts the values array using the bubble sort algorithm.
	{
		int compare = 0;
		int current = 0;
		while (current < (size - 1))
		{
			compare += bubbleUp(arr, current, size - 1);
			current++;
		}
		return compare;
	}
	
	
	
	
	
	static int insertElement(String[] arr, int startIndex, int endIndex)
	// Upon completion, values[0]to values[endIndex] are sorted.
	{
		int comparisons = 0;
		boolean finished = false; int current = endIndex; boolean moreToSearch = true;
		while (moreToSearch && !finished)
		{
			comparisons++;
			if (Float.parseFloat(arr[current]) < Float.parseFloat(arr[current-1]))

			{
				swap(arr, current, current - 1);
				current--;
				moreToSearch = (current != startIndex);
			}
			else
				finished = true;
		}
		return comparisons;
	}

	int isort(String[] arr)
	// Sorts the values array using the insertion sort algorithm.
	{
		int compare = 0;
		for (int count = 1; count < size; count++)
			compare +=insertElement(arr, 0, count);
		return compare;
	}
	
	
	
	
	
	
	static int merge (String[] arr, int leftFirst, int leftLast, int rightFirst, int rightLast)
	// Preconditions: values[leftFirst]to values[leftLast] are sorted.
//	     values[rightFirst]to values[rightLast] are sorted.
	//
	// Sorts values[leftFirst]to values[rightLast] by merging the two subarrays.
	{
		int comparisons = 0;
		String[] tempArray = new String [size];
		int index = leftFirst;
		int saveFirst = leftFirst;       // to remember where to copy back

		while ((leftFirst <= leftLast) && (rightFirst <= rightLast))
		{
			if (Float.parseFloat(arr[leftFirst]) < Float.parseFloat(arr[rightFirst]))
			{
				tempArray[index] = arr[leftFirst];
				leftFirst++;
				comparisons++;
			}
			else
			{
				tempArray[index] = arr[rightFirst];
				rightFirst++;
				comparisons++;
			}
			index++;
		}

		while (leftFirst <= leftLast)
			// Copy remaining elements from left half.
		{
			tempArray[index] = arr[leftFirst];
			leftFirst++;
			index++;
		}
		
		while (rightFirst <= rightLast)
			// Copy remaining elements from right half.
		{
			tempArray[index] = arr[rightFirst];
			rightFirst++;
			index++;
		}

		for (index = saveFirst; index <= rightLast; index++)
			arr[index] = tempArray[index];
		return comparisons;
	}
	
	int msort(String[] arr, int first, int last)
	// Sorts the values array using the merge sort algorithm.
	{
		int compare = 0;
		if (first < last)
		{
			int middle = (first + last) / 2;
			compare += msort(arr, first, middle);
			compare += msort(arr, middle + 1,    last);
			compare += merge(arr, first, middle, middle + 1, last);
		}
		return compare;
	}
	
	
	
	
	
	
	static int split(String[] arr, int first, int last)
	{
		String splitVal = arr[first];
		int saveF = first;
		boolean onCorrectSide;
	  
		first++;
		do
		{
			onCorrectSide = true;
			while (onCorrectSide)       // move first toward last
				if (Float.parseFloat(arr[first]) > Float.parseFloat(splitVal))
					onCorrectSide = false;
				else
				{
					first++;
					onCorrectSide = (first <= last);
				}

			onCorrectSide = (first <= last);
			while (onCorrectSide)       // move last toward first
				if (Float.parseFloat(arr[last]) <= Float.parseFloat(splitVal))
					onCorrectSide = false;
				else
				{
					last--;
					onCorrectSide = (first <= last);
				}

			if (first < last)
			{
				swap(arr, first, last);
				first++;
				last--;
			}
		} while (first <= last);

		swap(arr, saveF, last);
		return last;
	}
	
	void qsort(String[] arr, int first, int last)
	{
		if (first < last)
		{
			int splitPoint;

			splitPoint = split(arr, first, last);
			// values[first]to values[splitPoint - 1] <= splitVal
			// values[splitPoint] = splitVal
			// values[splitPoint+1]to values[last] > splitVal

			qsort(arr, first, splitPoint - 1);
			qsort(arr, splitPoint + 1, last);
		}
	}
	
	
	
	
	
	public int lsearch(String[] arr, String x)
	{
		int compare = 0;
		for(int i = 0; i< size-1; i++)
		{
			compare++;
			if(Float.parseFloat(arr[i]) == Float.parseFloat(x))
			{	
				System.out.println("element found at index: " + i);
				return compare;	
			}
		}
		System.out.println("element not found");
		return compare;
	}

	
	
	
	public int bsearch(String[] arr, String x)
	{
		int compare = 0;
		int first = 0;
		int last = arr.length-1;
		while(first <= last)
		{	
			compare++;
			int mid = (first+last)/2;
			if(arr[mid] == x)
			{	
				
				System.out.println("element found at :" + mid);
				return compare;
			}
			
			else if(Float.parseFloat(arr[mid]) > Float.parseFloat(x)) 
				last = mid-1;
			else 
				first = mid +1;
		}
		System.out.println("element not found");
		return compare;
	}
	
	
	
	public static String[] readarray(String file)
	{
		
		try
		{
			Scanner s1 = new Scanner(new File(file));
			while(s1.hasNextLine())
			{
				
				size++;
				s1.nextLine();
				
				
			}
			String[] data = new String[size];
			
			Scanner s2 = new Scanner(new File("emp.txt"));
			for(int i = 0; i < size; i++)
			{
				data[i] = s2.nextLine();
			}
			return data;
		}
		catch(FileNotFoundException e)
		{
			System.out.println("file nor found");
			return null;
		}
	}
	
	public static float max(String [] arr)
	{
		float max = Float.parseFloat(arr[0]);
		for(int i = 0; i < arr.length; i++)
		{
			if(Float.parseFloat(arr[i]) > max)
			{
				max = Float.parseFloat(arr[i]);
			}
		}
		System.out.println("max: " + max);
		return max;
	}
	
	public static String datatype(String file)
	{
		try
		{
			
			String type = "";
			Scanner s1 = new Scanner(new File(file));
			while(s1.hasNextLine())
			{
				if(s1.hasNextInt())
				{
					type = "Integer";
					System.out.println("Integer");
				}
					
				else if(s1.hasNextFloat())
				{
					type = "Float";
					System.out.println("float");
				}
					
				else 
				{
					type = "String";
					System.out.println("String");
				}	
			}
			return type;
		}
		catch(FileNotFoundException e)
		{
			System.out.println("file not found");
			return null;
		}
		
	}
	
	public static void main(String[] args) {

		Project original = new Project();
	
		Scanner input = new Scanner(System.in);
		
		String datatyp;
		String[] arr = readarray("emp.txt");
		
		System.out.println("original: ");
		print(arr);
		System.out.println("Sorted: ");
		original.ssort(arr);
		print(arr);
	}
}
