package project;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

import java.text.DecimalFormat;


public class Project {
	
	static final int size = 5;
	static int[] arr = {50,40,30,20,10};
	
	//method initializes the array with data elements.
	public static void initvalues()
	{
		Random rand = new Random();
		for(int i=0; i<size; i++)
			arr[i] = Math.abs(rand.nextInt())%100;
	}
	
	//method checks if array is sorted.
	public static boolean issorted()
	{
		for(int i=0; i<size-1; i++)
		{
			if(arr[i]>arr[i+1])
				return false;
		}
		return true;
	}
	
	//method to swap values
	public static void swap(int n1, int n2)
	{
		int temp = arr[n1];
		arr[n1] = arr[n2];
		arr[n2] = temp;
	}
	
	//method to print all elements of the array.
	public static void print()
	{
		int value;
		DecimalFormat fmt = new DecimalFormat("00");
		System.out.println("The array is: ");
		for(int index = 0; index < size; index++)
		{
			value = arr[index];
			if(((index+1)%10) == 0)
				System.out.println(fmt.format(value));
			else
				System.out.print(fmt.format(value) + " ");
		}
		System.out.println();
	}
 	
	
	
	
	
	

	int ssort()
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
				if(arr[j] < arr[min])
				{
					min = j;
				}
			}
			swap(current, min);
		}
		return compare;
	}
	
	
	
	
	
	
	static int bubbleUp(int startIndex, int endIndex)
	// Switches adjacent pairs that are out of order
	// between values[startIndex]to values[endIndex]
	// beginning at values[endIndex].

	{
		int comparisons = 0;
		for (int index = endIndex; index > startIndex; index--)
		{
			comparisons++;
			if (arr[index] < arr[index - 1])
				swap(index, index - 1);
		}
	    return comparisons;
	}

	int bsort()
	// Sorts the values array using the bubble sort algorithm.
	{
		int compare = 0;
		int current = 0;
		while (current < (size - 1))
		{
			compare += bubbleUp(current, size - 1);
			current++;
		}
		return compare;
	}
	
	
	
	
	
	static int insertElement(int startIndex, int endIndex)
	// Upon completion, values[0]to values[endIndex] are sorted.
	{
		int comparisons = 0;
		boolean finished = false; int current = endIndex; boolean moreToSearch = true;
		while (moreToSearch && !finished)
		{
			comparisons++;
			if (arr[current] < arr[current - 1])

			{
				swap(current, current - 1);
				current--;
				moreToSearch = (current != startIndex);
			}
			else
				finished = true;
		}
		return comparisons;
	}

	int isort()
	// Sorts the values array using the insertion sort algorithm.
	{
		int compare = 0;
		for (int count = 1; count < size; count++)
			compare +=insertElement(0, count);
		return compare;
	}
	
	
	
	
	
	
	static int merge (int leftFirst, int leftLast, int rightFirst, int rightLast)
	// Preconditions: values[leftFirst]to values[leftLast] are sorted.
//	     values[rightFirst]to values[rightLast] are sorted.
	//
	// Sorts values[leftFirst]to values[rightLast] by merging the two subarrays.
	{
		int comparisons = 0;
		int[] tempArray = new int [size];
		int index = leftFirst;
		int saveFirst = leftFirst;       // to remember where to copy back

		while ((leftFirst <= leftLast) && (rightFirst <= rightLast))
		{
			if (arr[leftFirst] < arr[rightFirst])
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
	
	int msort(int first, int last)
	// Sorts the values array using the merge sort algorithm.
	{
		int compare = 0;
		if (first < last)
		{
			int middle = (first + last) / 2;
			compare += msort(first, middle);
			compare += msort(middle + 1,    last);
			compare += merge(first, middle, middle + 1, last);
		}
		return compare;
	}
	
	
	
	
	
	
	static int split(int first, int last)
	{
		int splitVal = arr[first];
		int saveF = first;
		boolean onCorrectSide;
	  
		first++;
		do
		{
			onCorrectSide = true;
			while (onCorrectSide)       // move first toward last
				if (arr[first] > splitVal)
					onCorrectSide = false;
				else
				{
					first++;
					onCorrectSide = (first <= last);
				}

			onCorrectSide = (first <= last);
			while (onCorrectSide)       // move last toward first
				if (arr[last] <= splitVal)
					onCorrectSide = false;
				else
				{
					last--;
					onCorrectSide = (first <= last);
				}

			if (first < last)
			{
				swap(first, last);
				first++;
				last--;
			}
		} while (first <= last);

		swap(saveF, last);
		return last;
	}
	
	void qsort(int first, int last)
	{
		if (first < last)
		{
			int splitPoint;

			splitPoint = split(first, last);
			// values[first]to values[splitPoint - 1] <= splitVal
			// values[splitPoint] = splitVal
			// values[splitPoint+1]to values[last] > splitVal

			qsort(first, splitPoint - 1);
			qsort(splitPoint + 1, last);
		}
	}
	
	
	
	
	
	public int lsearch(int x)
	{
		int compare = 0;
		for(int i = 0; i< size-1; i++)
		{
			compare++;
			if(arr[i] == x)
			{	
				System.out.println("element found at index: " + i);
				return compare;	
			}
		}
		System.out.println("element not found");
		return compare;
	}

	
	
	
	public int bsearch(int x)
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
			
			else if(arr[mid]>x) 
				last = mid-1;
			else 
				first = mid +1;
		}
		System.out.println("element not found");
		return compare;
	}
	
	
	
	
	public static void main(String[] args) {

		Project original = new Project();
		Project sort1 = new Project();
		Project sort2 = new Project();
		Scanner input = new Scanner(System.in);
		boolean flag = true;
		boolean flag2 = true;
		
		print();
		int result1 = original.lsearch(30);
	    System.out.println("no of comparisons: " + result1);
	    System.out.println();
	    
	    
	    
	    int result2 = sort1.bsearch(30);
	    System.out.println("no of comparisons: " + result2);
	    print();
	    if(result1<result2)
	    	System.out.println("linear search has less number of comparisons therefore is better for searching");
	    else
	    	System.out.println("binary search has less number of comparisons therefore is better for searching");
	}

}
