package project;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

import java.text.DecimalFormat;


public class Project {
	
	static int size;
	
	//method to swap values
	public static void swap(float[] arr, int n1, int n2)
	{
		float temp = arr[n1];
		arr[n1] = arr[n2];
		arr[n2] = temp;
	}
	
	//method to print all elements of the array.
	public static void print(float[] arr)
	{
		
		
		System.out.println("\nThe array is: ");
		for(int index = 0; index < size; index++)
		{
			System.out.print(arr[index] + "  ");
		}
		
	}
 
	int ssort(float[] arr)
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
				if((arr[j]) < (arr[min]))
				{
					min = j;
				}
			}
			swap(arr, current, min);
		}
		return compare;
	}

	static int bubbleUp(float[] arr, int startIndex, int endIndex)
	// Switches adjacent pairs that are out of order
	// between values[startIndex]to values[endIndex]
	// beginning at values[endIndex].

	{
		int comparisons = 0;
		for (int index = endIndex; index > startIndex; index--)
		{
			comparisons++;
			if ((arr[index]) < (arr[index-1]))
				swap(arr, index, index - 1);
		}
	    return comparisons;
	}

	int bsort(float[] arr)
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
	
	static int insertElement(float[] arr, int startIndex, int endIndex)
	// Upon completion, values[0]to values[endIndex] are sorted.
	{
		int comparisons = 0;
		boolean finished = false; int current = endIndex; boolean moreToSearch = true;
		while (moreToSearch && !finished)
		{
			comparisons++;
			if ((arr[current]) < (arr[current-1]))

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

	int isort(float[] arr)
	// Sorts the values array using the insertion sort algorithm.
	{
		int compare = 0;
		for (int count = 1; count < size; count++)
			compare +=insertElement(arr, 0, count);
		return compare;
	}
	
	static int merge (float[] arr, int leftFirst, int leftLast, int rightFirst, int rightLast)
	// Preconditions: values[leftFirst]to values[leftLast] are sorted.
//	     values[rightFirst]to values[rightLast] are sorted.
	//
	// Sorts values[leftFirst]to values[rightLast] by merging the two subarrays.
	{
		int comparisons = 0;
		float[] tempArray = new float[size];
		int index = leftFirst;
		int saveFirst = leftFirst;       // to remember where to copy back

		while ((leftFirst <= leftLast) && (rightFirst <= rightLast))
		{
			if ((arr[leftFirst]) < (arr[rightFirst]))
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
	
	int msort(float[] arr, int first, int last)
	// Sorts the values array using the merge sort algorithm.
	{
		int compare = 0;
		if (first < last)
		{
			int middle = (first + last) / 2;
			compare += msort(arr, first, middle);
			compare += msort(arr, middle + 1, last);
			compare += merge(arr, first, middle, middle + 1, last);
		}
		return compare;
	}
	
	static int split(float[] arr, int first, int last)
	{
		float splitVal = arr[first];
		int saveF = first;
		boolean onCorrectSide;
	  
		first++;
		do
		{
			onCorrectSide = true;
			while (onCorrectSide)       // move first toward last
				if ((arr[first]) > (splitVal))
					onCorrectSide = false;
				else
				{
					first++;
					onCorrectSide = (first <= last);
				}

			onCorrectSide = (first <= last);
			while (onCorrectSide)       // move last toward first
				if ((arr[last]) <= (splitVal))
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
	
	void qsort(float[] arr, int first, int last)
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
	
	public int lsearch(float[] arr, float x)
	{
		int compare = 0;
		for(int i = 0; i< size-1; i++)
		{
			compare++;
			if( (arr[i]) ==  (x))
			{	
				System.out.println("element found at index: " + i);
				return compare;	
			}
		}
		System.out.println("element not found");
		return compare;
	}
	
	public int bsearch(float[] arr, float x)
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
			
			else if((arr[mid]) > (x)) 
				last = mid-1;
			else 
				first = mid +1;
		}
		System.out.println("element not found");
		return compare;
	}
	
	public static float[] readarray(String file)
	{
		
		try
		{
			Scanner s1 = new Scanner(new File(file));
			while(s1.hasNextLine())
			{
				
				size++;
				s1.nextLine();
				
				
			}
			float[] data = new float[size];
			
			Scanner s2 = new Scanner(new File("emp.txt"));
			for(int i = 0; i < size; i++)
			{
				data[i] = s2.nextFloat();
			}
			return data;
		}
		catch(FileNotFoundException e)
		{
			System.out.println("file nor found");
			return null;
		}
	}
	
	public static float max(Float [] arr)
	{
		
			float max = (arr[0]);
			for(int i = 0; i < arr.length; i++)
			{
				if((arr[i]) > max)
				{
					max = (arr[i]);
				}
			}
			System.out.println("max: " + max);
			return max;
		
	}	
	
	public static void main(String[] args) {

		Project original = new Project();
	
		Scanner input = new Scanner(System.in);
		
		int datatyp;
		float[] arr = readarray("emp.txt");
		
		System.out.println("original: ");
		print(arr);
		System.out.println();
		System.out.println("sorted: ");
		original.ssort(arr);
		print(arr);
	}
}
