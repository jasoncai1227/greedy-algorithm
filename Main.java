import java.util.ArrayList;
import java.util.Scanner;

class Main
{
	public static void main(String[] args)
	{
		// this code reads the input data into a couple of lists
		Scanner reader = new Scanner(System.in);
		double T = reader.nextDouble();
		int n = reader.nextInt();
		ArrayList<Double> times = new ArrayList<Double>(); 
		ArrayList<Double> marks = new ArrayList<Double>();
		for(int i = 1; i <= n; ++i)
		{
			double t = reader.nextDouble();
			double m = reader.nextDouble();
			times.add(t);
			marks.add(m);
		}
		reader.close();
		
		// you will want to put the data in a different data structure
		// (you don't need anything fancy, but two separate lists might
		// be a little bit awkward)
		double total_cost=0.0;
		for(int q = 0; q < n; ++q){
			total_cost=total_cost+times.get(q);
		}
		if(T<total_cost/2){
			System.out.println("0.0");
			return;
		}
		double MAX=0.0;
		double T_R=T;
		
		ArrayList<Integer> assignment=new ArrayList<Integer>();
		for(int a=0; a<n; a++){
			MAX=MAX+(marks.get(a)/2);
			T_R=T_R-(times.get(a)/2);
			assignment.add(a);
		}
		
		MergeSort(times,marks,assignment,0,assignment.size());
		
		double process=0.0;
		for(int z=0; z<n; z++){
			double t=times.get(assignment.get(z));
			if(T_R>=t/2){
				MAX=MAX+(marks.get(assignment.get(z))/2);
				T_R=T_R-t/2;
			}
			else
			{
				process=T_R/(t/2);
				MAX=MAX+(marks.get(assignment.get(z))/2)*process;
				T_R=T_R-(t/2*process);
			}
		}
		// TODO: implement your algorithm
		double answer = 0.0;
		answer=MAX;
		// output a single number representing the solution
		// (your program should not output any other text)
		System.out.println(answer);
	}
	
	private static void MergeSort(ArrayList<Double> a, ArrayList<Double> b, ArrayList<Integer> list, int left, int right)
	{
		int length = right - left;
		if (length <= 1) {
            return;
        }
		int mid = left + (length / 2);
		MergeSort(a,b,list,left,mid);
		MergeSort(a,b,list,mid,right);
		Merge(a,b,list,left,mid,right);
	}
	private static void Merge(ArrayList<Double> a, ArrayList<Double> b, ArrayList<Integer> list, int left,int mid, int right){
		
		int upperStart = left;
        int upperEnd = mid;
        int lowerStart = upperEnd;
        int lowerEnd = right;
		int i = upperStart;
        int j = lowerStart;
     	ArrayList<Integer> temp=new ArrayList<Integer>();
        while (i < upperEnd || j < lowerEnd) {
			if (i >= upperEnd) {
			 temp.add(list.get(j));
             j++;
        } else if (j >= lowerEnd) {
            temp.add(list.get(i));
            i++;
        } else {
            if (a.get(list.get(i))/b.get(list.get(i)) <= a.get(list.get(j))/b.get(list.get(j))) {
                temp.add(list.get(i));
                i++;
            } else {
                temp.add(list.get(j));
                j++;
            }
        }	
	  }
		  for (int k = left; k < right; k ++) {
            list.set(k, temp.get(k - left));
        }
	}
}

