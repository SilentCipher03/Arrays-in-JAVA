import java.util.*;

public class Arrays {

    //Pass arrays as argument 
    public static void update(int marks[]){
        for(int i = 0; i< marks.length; i++){
           marks[i] += 1;
        }
    }

    //Linear Search 
    public static int LinearSearch(int numbers[], int key){
        for(int i =0; i< numbers.length; i++){
            if(numbers[i] == key){
                return i;
            }
        }
        return -1;
    }
    public static int Practise_LinearSearch(String dish[], String key){
        for(int i =0; i< dish.length; i++){
            if(dish[i].equals(key)){
                return i;
            }
        }
        return -1;

    }

    //Print largest num in array with O(n)
    public static int Largest_inArray(int num[]){
        int largest = Integer.MIN_VALUE; // -Infinity
        int smallest = Integer.MAX_VALUE; //+Infinity
        for(int i =0; i<num.length; i++){
            if(num[i] >largest){
                largest = num[i];
            }
            if(num[i]<smallest){
                smallest= num[i];
            }
        }
        System.out.println("Smallest num is " + smallest);
        return largest;
    }

    // Binary Search Imagine how we search in Dictionary Prerequisite are sorted array find Mid 
    public static int BinarySearch(int n[], int key){
        int start = 0;
        int end = n.length-1;
        while(start<=end){ //Last case key found
            int mid = (start+end)/2;

            //Comparisons
            if(n[mid]==key){ // found
            return mid;
            }
            if(n[mid]<key){ //Right
                start = mid+1;
            }else{ //Left
                end = mid-1;
            }
        }
        return -1;
    }

    //Reverse the array (By swaping it is done first and last swap)
    public static void ReversetheArray(int num[]){
        int first = 0;
        int last = num.length-1;
        while(first  < last){
            //swap
            int temp = num[first];
            num [first] = num[last];
            num[last] = temp;
            first ++;
            last--;
        }
    }

    //Print pairs in an array (nested loop) Total pairs = n*(n-1)/2 impo
    public static void PairsinArray (int n[]){
        int tp =0;
        for(int i= 0; i<n.length; i++){
            //int curr = n[i]; // 2,,4,6,8,10
            for(int j= i+1; j <n.length; j++){
                //Pairs
                System.out.print("(" + n[i] + "," + n[j]+ ")");
                tp++;
            }
            System.out.println();
        }
        System.out.println("Total Pairs are:" +tp);
    }

    //Print sub arrays
    public static void PrintSubArray(int num[]){
        int ts =0;
        int sum = 0;
        for(int i = 0; i<num.length; i++){
            for(int j= i; j<num.length; j++){
                for(int k=i; k<=j; k++){
                    System.out.print(num[k]+"," );
                }
                System.out.println();
                ts++;
            }
            System.out.println();
        }
        System.out.println("Total number of sub arrays " +ts);
        System.out.println(sum);
    }

    // Calulating sum of the Sub array and finding the min and max out of those TC= O(n^3) Worst very bad TC
    public static void SumofSubArray(int num[]){
        //int ts=0;
        int index=0;
        int sumarray[] = new int [num.length * (num.length + 1)/2];

        for(int i = 0; i< num.length; i++){
            for(int j =i; j<num.length; j++){
                int sum =0;
                for(int k = i; k<=j; k++){
                    //System.out.println(num[k] + " ");
                    sum+=num[k];
                }
                System.out.println();
                System.out.println( "Sum of sub array " +num[i] + "," + num[j] + " = " + sum);
                sumarray[index++]= sum;
                //ts++;
            }
        }

        //System.out.println("Total numbers of sub arrays is " +ts);

        int maxsum = Integer.MIN_VALUE;
        int minsum = Integer.MAX_VALUE;

        for(int i = 0 ; i< sumarray.length ; i++){
            maxsum = Math.max(maxsum, sumarray[i]);
            minsum = Math.min(minsum,sumarray[i]);
        }
        System.out.println(" Max Sum inSub Array is "+maxsum);
        System.out.println("Min Sum in Sub Array is "+minsum);
    }

    //Kadane's Algorithm Very Important -ve value in currsum make it = 0 TC = O
    public static void KadaneAlgomax(int num[]){
        int currsum = 0;
        int maxsum = Integer.MIN_VALUE;

        for(int i = 0;i<num.length; i++){
            currsum = currsum +num[i];
            if(currsum<0){
                currsum =0;
            }
            maxsum = Math.max(currsum, maxsum);
        }
        System.out.println("Max Sum in Sub Array is " +maxsum);
    }

    //Kadane's Algorithm Very Important to find min sum of sub array
    public static void KadaneAlgomin(int num[]){
        int currmin = 0;
        int maxmin = Integer.MAX_VALUE;

        for(int i = 0; i<num.length; i++){
            currmin = currmin +num[i];
            maxmin = Math.min(currmin, maxmin);
        }
        System.out.println("Max Sum in Sub Array is " + maxmin);
    }

    // Trapped Rain Water ques by AUxiliar/Helper arrays TC = O(n)
    public static int TrappedRainWater(int height[]){
        int n = height.length;
        if(n <=2){
            System.out.println(" No Trapped water");
        }

        //Ascending or Descending 
        boolean ascending = true;
        boolean descending = true;
        
        for(int i =1; i<n; i++){
            if(height[i] > height[i-1]){//Ascending 
                System.out.println(" No Water will be trapped ascending order");
            }
            else if(height[i] < height[i-1]){
                System.out.println("No Water will be trapped as descending order");
            }

        }
        //Calculate Max Left
        int maxleft[] = new int[n];
        maxleft[0] = height[0];
        for(int i =1; i<n; i++){
            maxleft[i] = Math.max(height[i], maxleft[i-1]);
        }

        //Calculate Max Right
        int maxright[] = new int[n];
        maxright[n-1] = height[n-1];
        for(int i = n-2; i>=0; i--){
            maxright[i] = Math.max(height[i], maxright[i+1]);
        }

        //Loop
        int trappedwater = 0;
        for(int i =0; i<n;i++){
            //WaterLevel = Math.min(leftmax[i] , rightmax[i])
            int waterlevel = Math.min(maxleft[i], maxright[i]);

            //Trapped water 
            trappedwater += waterlevel - height[i];
        }
        return trappedwater;
    }

    //Buy and Sell stock problem O(n)
    public static int BuyandSellStocks(int price[]){
        int BP = Integer.MAX_VALUE;
        int maxpro = 0;
        for(int i =0; i<price.length; i++){
            if(BP < price[i]){
                int pro = price[i] - BP;// Today's Profit
                maxpro = Math.max(maxpro,pro);
            }else{
                BP = price[i];
            }
        }
        return maxpro;
    }

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        // Arrays is List of element of the same data type with continuous memory allocation
        // Size of array is static we can not change size while code is running

        //Create array
        /*int marks[] = new int[100];

        //Input from user
        marks[0] = sc.nextInt();
        marks[1] = sc.nextInt();
        marks[2] = sc.nextInt();

        //Output 
        System.out.println("Phy = " + marks[0]);
        System.out.println("Chem = " + marks[1]);
        System.out.println("Maths = " + marks[2]);

        //Update 
        marks[2] = 100;
        System.out.println("Maths = " + marks[2]);
        marks[2]= marks[2]+1;
        System.out.println("Maths = " + marks[2]);

        //Percentage 
        int percentage = (marks[0]+marks[1]+marks[2]) / 3;
        System.out.println("Percentage =" + percentage + "%");

        // Arrays are passed by Reference (Means change is reflected in main function for Arrays)
        // Passing arrays as argument
        int marks [] =  {98,97,99};
        // to print marks 
        for(int i =0; i<marks.length;i++){
            System.out.print(marks[i]+ " ");
        }
        update(marks);
        
        // to print marks 
        for(int i =0; i<marks.length;i++){
            System.out.print(marks[i]+ " ");
        }


        // Linear search 
        int numbers[] = {2,3,4,5,6,7,8,9,10};
        //int key = 10;

        int index = LinearSearch(numbers,10);
        if(index == -1){
            System.out.println(" Not found");
        }else{
            System.out.println("Index for key is " + index);
        }
        // Paactise question for linear search
        String dish[] = {"Dosa", "Chowmein", "PavBhaji", "Pizza", "Coffee"};
        System.out.println(" Enter the dish you want to have");
        String key = sc.next();
        int order= Practise_LinearSearch(dish,key);
        if(order == -1){
            System.out.println(" Not found");
        }else{
            System.out.println("Available at table no " + order);
        }

        //Largest number in array
        int num[] = {7,78,90,22,65,0};
        System.out.println(Largest_inArray(num));
        }

        //Binary search TC= O(logn)
        int n[] = {2,4,6,8,10,12,14};
        System.out.println("Enter the key");
        int key = sc.nextInt();
        System.out.println("Index for key is "+ BinarySearch(n, key));


        // Reverse the array (Since array is passed as refernce so change will reflect inmain function) TC =O(n) ans SC = O(1)
        int num[] = {2,4,6,8,10};
        ReversetheArray(num);
        // To print the reversed array
        for(int i= 0; i< num.length; i++){
            System.out.print(num[i] + " ");
        }

        // Print pairs 
        int n[] = {2,4,6,8,10};
        PairsinArray(n);
 
        //Sub arrays print      
        int num[] = {2,4,6,8,10};
        PrintSubArray(num);

        //Print Sub Arrays and value of maxsum and minsum TC = O(n^3)
        int num[] = {2,4,6,8,10};
        SumofSubArray(num);

        //Kadane's Algorithm to find Max TC O(n)
        int numb[] = {-2, -3, 4, -1, -2, 1, 5,-3};
        KadaneAlgo(numb);


        int num [] = {-2, -3, 4, -1, -2, 1, 5,-3};
        KadaneAlgomin(num);*/

        //Trapped Water 
        int height[] = {4,2,0,6,3,2,5};
        System.out.println(TrappedRainWater(height));

        //Buy ans sell stocks according to price  TC = O(n)
        int price[] = {7,1,5,3,6,4};
        System.out.println(BuyandSellStocks(price));




        




    }
}

