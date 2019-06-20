package project;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;




public class Project {
	
	static int size;
	static float[] error = new float[1];
	
	public static boolean issorted(float[] arr)
	{
		for(int i = 0; i < size; i++)
		{
			if(arr[i] > arr[i+1])
				return false;
		}
		return true;
	}
	
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
		
		for(int index = 0; index < size; index++)
		{
			System.out.print(arr[index] + "  ");
		}
		System.out.println();
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
	
	static int[] split(float[] arr, int first, int last)
	{
		float splitVal = arr[first];
		int saveF = first;
		boolean onCorrectSide;
		int[] cl = new int[2];	  
		first++;
		do
		{
			onCorrectSide = true;
			while (onCorrectSide)       // move first toward last
				if ((arr[first]) > (splitVal))
				{
					onCorrectSide = false;
					cl[0]++;
				}
				else
				{
					first++;
					onCorrectSide = (first <= last);
					cl[0]++;
				}
			onCorrectSide = (first <= last);
			while (onCorrectSide)       // move last toward first
				if ((arr[last]) <= (splitVal))
				{
					onCorrectSide = false;
					cl[0]++;
				}
				else
				{
					last--;
					onCorrectSide = (first <= last);
					cl[0]++;
				}
			if (first < last)
			{
				swap(arr, first, last);
				first++;
				last--;
			}
		} while (first <= last);
		swap(arr, saveF, last);
		cl[1] = last;
		return cl;
	}
	
	int qsort(float[] arr, int first, int last)
	{
		int[] splitPoint = new int[2];
		if (first < last)
		{
			splitPoint = split(arr, first, last);
			// values[first]to values[splitPoint - 1] <= splitVal
			// values[splitPoint] = splitVal
			// values[splitPoint+1]to values[last] > splitVal

			qsort(arr, first, splitPoint[1] - 1);
			qsort(arr, splitPoint[1] + 1, last);			
		}
		return splitPoint[0];
	}
	
	public int[] lsearch(float[] arr, float x)
	{
		int[] ans = new int[2];	//ans[0] contains the position of element and ans[1] contains comparison count.
		for(int i = 0; i< size; i++)
		{
			ans[1]++;			
			if( (arr[i]) ==  (x))
			{	
				ans[0] = i;
				return ans;	
			}
		}
		ans[0] = -1;
		return ans;
	}
	
	public int[] bsearch(float[] arr, float x)
	{
		int[] ans = new int[2];//ans[0] contains the position of element and ans[1] contains comparison count.
		float first = 0;
		float last = size-1;		
		while(last >= first)
		{	
			ans[1]++;
			ans[0] = (int) ((first+last)/2);
			if(arr[ans[0]] == x)
			{	
				return ans;
			}			
			else if((arr[ans[0]]) > (x)) 
				last = ans[0]-1;
			else 
				first = ans[0] +1;
		}		
		ans[0] = -1;
		return ans;
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
			float[] data = new float[100];
			
			Scanner s2 = new Scanner(new File(file));
			for(int i = 0; i < size; i++)
			{
				try
				{
					data[i] = Float.parseFloat(s2.nextLine());
				}
				catch(NumberFormatException nfe)
				{
					System.out.println("Not a Float or int data type, please enter a valid file containing a float or int data type and restart the program.");
					Runtime.getRuntime().exit(0);
				}
			}
			return data;
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found");
			return null;
		}
	}
	
	public static void main(String[] args) {

		Project original = new Project();	
		boolean flag1 = true;
		int c1, c2, c3;
		int count1 = 0, count2 = 0;
		Scanner input = new Scanner(System.in);	
		System.out.println("Enter the file name: ");
		
		
		float[] arr1 = readarray(input.nextLine());
		
		
		float[] arr3 = new float[1000];
		float[] arr2 = new float[1000];
		
		while(flag1 == true)
		{
			//Choices for the user
			System.out.println("\n1. View List.");
			System.out.println("2. Add an element to the list.");
			System.out.println("3. Remove an element from the list.");
			System.out.println("4. Choose a simple sort.");
			System.out.println("5. Choose a O(NlogN) sort.");
			System.out.println("6. Seacrh an element.");
			System.out.println("7. Sorting results.");
			System.out.println("8. Exit program.");
			System.out.println("Enter your choice (1-8): ");
			c1 = input.nextInt();			
			if(c1 == 1)
			{				
				System.out.println("List:");
				print(arr1);				
			}
			else if(c1 == 2)
			{
				
				System.out.println("Enter the element you wish to add to the list");	
				arr1[size] = input.nextFloat();
				size++;	
			}
			else if(c1 == 3)
			{
				System.out.println("Enter which element you would like to remove from the original list: ");
				float remove = input.nextFloat();
				int[] pos = original.lsearch(arr1, remove);
				if(pos[0] == -1)
					System.out.println("Element not found in the list.");
				else
				{
					for(int i = pos[0]; i<size; i++)
					{
						arr1[i] = arr1[i+1];
					}					
					size--;	
				}							
			}			
			else if(c1 == 4)
			{
				boolean flag2 = true;
				while(flag2 == true)
				{
					System.out.println("\n1. Selection sort.");
					System.out.println("2. Bubble sort.");
					System.out.println("3. Insertion sort.");
					System.out.println("Enter your choice (1-3): ");
					c2 = input.nextInt();
					if(c2 == 1)
					{						
						for(int i = 0; i < size; i++)
							arr2[i] = arr1[i];
						System.out.println("\nList after selection sort:");
						count1 = original.ssort(arr2);
						print(arr2);
						System.out.println("\nSelection sort no of comparison count: " + count1);
						flag2 = false;
					}
					else if(c2 == 2)
					{						
						for(int i = 0; i < size; i++)
							arr2[i] = arr1[i];
						System.out.println("\nList after bubble sort:");
						count1 = original.bsort(arr2);
						print(arr2);
						System.out.println("\nbubble sort no of comparison count: " + count1);
						flag2 = false;
					}
					else if(c2 == 3)
					{						
						for(int i = 0; i < size; i++)
							arr2[i] = arr1[i];
						System.out.println("\nList after insertion sort:");
						count1 = original.isort(arr2);
						print(arr2);
						System.out.println("\ninsertion sort no of comparison count: " + count1);
						flag2 = false;
					}			
					else
						System.out.println("Wrong choice");	
				}				
			}				
			else if(c1 == 5) 
			{
				boolean flag3 = true;
				while(flag3 == true)
				{
					System.out.println("1. Merge sort.");
					System.out.println("2. Quick sort.");
					System.out.println("Enter your choice (1-2): ");
					c3 = input.nextInt();
					if(c3 == 1)
					{							
						for(int i = 0; i < size; i++)
							arr3[i] = arr1[i];
						System.out.println("\nList after merge sort:");
						count2 = original.msort(arr3,0,size-1);
						print(arr3);
						System.out.println("\nMerge sort no of comparison count: " + count2);
						flag3 = false;
					}
					else if(c3 == 2)
					{								
						for(int i = 0; i < size; i++)
							arr3[i] = arr1[i];
						System.out.println("\nList after quick sort:");
						count2 = original.qsort(arr3,0,size-1);
						print(arr3);
						System.out.println("\nQuick sort no of comparison count: " + count2);
						flag3 = false;
					}
					else 
						System.out.println("Wrong choice");	
				}			
			}			
			else if(c1 == 6)
			{
				int[] l = new int[2];
				int[] b = new int[2];
				System.out.println("Enter a number you want to search: ");
				input.nextLine();
				String search = input.nextLine();
				l = original.lsearch(arr1, Float.parseFloat(search));
				b = original.bsearch(arr2, Float.parseFloat(search));
				if(l[0] == -1 && b[0] == -1)
				{
					System.out.println("\nElement cannot be found.");
				}
				else
				{
					System.out.println();
					print(arr1);
					System.out.println("\nUsing linear search, element was found at position " + l[0] + " in the unsorted list");
					System.out.println("Number of comparisons made in linear search: " + l[1]);
					System.out.println();
					print(arr2);
					System.out.println("\nUsing binary search, element was found at position " + b[0] + " in the sorted list");
					System.out.println("Number of comparisons made in binary search: " + b[1]);
					if(l[1] < b[1])
						System.out.println("\nlinear search has less comparisons therefore has less complexity.");
					else
						System.out.println("\nbinary search has less comparisons therefore has less complexity.");
				}				
			}
			else if(c1 == 7)
			{
				if(count1 < count2)
				{
					System.out.println("\nSimple Sort comparison count: " + count1);
					System.out.println("O(NlogN) Sort comparison count: " + count2);
					System.out.println("Simple sort has less comparison count than O(NlogN) sort so complexity is lower.");
				}
				else if(count1 > count2)
				{
					System.out.println("\nSimple Sort comparison count: " + count1);
					System.out.println("O(NlogN) Sort comparison count: " + count2);
					System.out.println("O(NlogN) sort has less comparison count than simple sort so complexity is lower.");
				}
			}				
			else if(c1 == 8)
				flag1 = false;
			else
				System.out.println("Wrong choice entered.");
		}				
	}
}
