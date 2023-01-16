import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.*;

// QUESTION 1 CLASSES START HERE
class task1
{
    int maxProfitBruteForce(int A[][], int m, int n) {
        long t1 = System.nanoTime();
        int i, j, k;
        int max_diff = 0;
        int dayB = -1, dayS = -1, stock = -1;
        for (k = 0; k < m; k++) {
            for (i = 0; i < n; i++) {
                for (j = i + 1; j < n; j++) {
                    if (A[k][j] - A[k][i] > max_diff) {
                        max_diff = A[k][j] - A[k][i];
                        stock = k;
                        dayB = i;
                        dayS = j;
                    }
                }
            }
        }
        long t2 = System.nanoTime();
        System.out.printf("\nExecution Time for Task1 : %d \n", t2 - t1);
        System.out.printf(
                "\n%d %d %d\n",stock, dayB, dayS);
        return max_diff;
    }
}

class task2
{
    int maxDiffGreedy(int A[][], int m, int n) {
        long t1 = System.nanoTime();
        var maxProfit = 0;
        int i, j, stock = -1, cheapestDay = -1, maxProfitDay = -1, cheapest;
        int[] cheapestDayArray = new int[m];
        if(m==0 || n==0)
        {
            return 0;
        }
        for (i = 0; i < m; i++) {
            for (j = 1, cheapest = A[i][0], cheapestDayArray[i] = 0; j < n; j++) {
            
                if (cheapest > A[i][j]) {
                    cheapest = A[i][j];
                    
                    cheapestDayArray[i] = j;
                }
                if (A[i][j] >= cheapest) {
                    if (A[i][j] - cheapest > maxProfit) {
                        maxProfit = A[i][j] - cheapest;
                        maxProfitDay = j;
                        stock = i;
                        cheapestDay = cheapestDayArray[i];
                        
                    }
                    maxProfit = Math.max(maxProfit, A[i][j] - cheapest);
                }

            }
        }
        long t2 = System.nanoTime();
        System.out.printf("\nExecution Time for Task2 : %d \n", t2 - t1);
        System.out.printf("\n%d %d %d\n",stock, cheapestDay, maxProfitDay);
        return maxProfit;
    }
}

class task3a
{
    public static int tempCheapest=0,count=0;
    public static int maxtotal,buy=-1,sell=-1,stock=-1;

    
    public static int maxprofit(int priceArray[],int n,int m,int storedProfit[]){
        
        
        if(n==1)
        {   
            // Base Case
            storedProfit[n]=Math.max(0,priceArray[n]-priceArray[n-1]);
            if(storedProfit[n]>maxtotal)
            {
                
                buy=n-1;
            }
            maxtotal=Math.max(maxtotal,storedProfit[n]);
 
            if(storedProfit[n]==0){
                tempCheapest = n;
                
            }
            else 
            {
                tempCheapest = priceArray[0];
               

            }
            return storedProfit[n];
        }
        else
        {   
            // Fetch Value from previous subproblem and calculate the current value
            storedProfit[n]=Math.max(0,maxprofit(priceArray,n-1,m,storedProfit)+priceArray[n]-priceArray[n-1]);
            
            if(storedProfit[n]==0){
                tempCheapest = n;
                
            }
            if(storedProfit[n]>maxtotal)
            {
                
                buy=tempCheapest;
                sell=n;
                stock=m;
            }
            maxtotal=Math.max(maxtotal,storedProfit[n]);
            return storedProfit[n];
        }
    
    }

    int recursiveMemoization(int A[][], int m, int n) {
        long t1 = System.nanoTime();
        int storedProfit[]=new int[n];
        storedProfit[0]=0;
        if(n>1)
        {
            for (int i = 0; i < m; i++) {
                maxprofit(A[i], A[i].length - 1, i,storedProfit);
            }
            long t2 = System.nanoTime();
            System.out.printf("\nExecution Time for Task3a : %d \n", t2 - t1);
            System.out.printf("\n%d %d %d\n",stock, buy, sell);
            
            return maxtotal;
        }
        else    
        {   
            System.out.println("No Transaction Possible");
            return 0;

        }
        
        
    }
}

class task3b
{
    static int maxProfitBottUp(int p[][], int m, int n) 
    {
        long t1 = System.nanoTime();
        int profit[] = new int[n];
        int maxtotal = 0, buyday = 1, sellday = 1, maxprofit = 0, bd = 1, sd = 1, stock = 0;
        profit[0] = 0;
        for (int j = 0; j < m; j++) {
            maxtotal = 0;
            buyday = 1;
            sellday = 1;
            for (int i = 1; i < n; i++) {
                profit[i] = Math.max(0, profit[i - 1] + (p[j][i] - p[j][i - 1]));
                
                if((p[j][i]-p[j][i-1]<0) && (profit[i]==0)){
                    
                    buyday = i+1;
                    
                }
                
                if (maxtotal < profit[i]) {
                    sellday = i + 1;
                   
                }
                maxtotal = Math.max(maxtotal, profit[i]);
                

            }
            if (maxtotal > maxprofit) {
                bd = buyday;
                sd = sellday;
                stock = j + 1;
            }
            maxprofit = Math.max(maxprofit, maxtotal);
            

        }
        long t2 = System.nanoTime();

        System.out.printf("\nExecution Time for Task3a : %d \n", t2 - t1);
        System.out.printf("\n%d %d %d\n",stock, bd , sd );
                
        return (maxprofit);

    }
}
// QUESTION 1 CLASSES END HERE







// QUESTION 2 CLASSES START HERE
class task5
{
 
    public int maximumProfit(int prices[][], int K, int m, int n) {
        if (K == 0 || m == 0 || n <= 1) {
            return 0;
        }
        int T[][] = new int[K+1][n];
 
        for (int x = 1; x < K+1; x++) {
            for (int y = 1; y < n; y++) {
                for (int z = 0; z < m; z++){
                    int maxVal = 0;
                    for (int zz = 0; zz < y; zz++) {
                        maxVal = Math.max(maxVal, prices[z][y] - prices[z][zz] + T[x-1][zz]);
                    }
                    T[x][y] = Math.max(Math.max(T[x][y-1], maxVal),T[x][y]);
                }
            }
        }
        backTrackAndPrint(T, prices);
        System.out.println(Arrays.deepToString(T));
        return T[K][n - 1];
    }
 
 
    public static void backTrackAndPrint(int T[][], int prices[][]) {
        

        Deque<Integer> daystack = new LinkedList<>();
        Deque<Integer> stockstack = new LinkedList<>();
        
           
            int i = T.length - 1;
            int j = T[0].length - 1;
            int count=0;
            while(true) {
                
                if(i == 0 || j == 0) {
                    break;
                }
                if (T[i][j] == T[i][j-1]) {
                    j = j - 1;
                } else {
                    daystack.addFirst(j);
                    int flag=0;
                    for (int k = j-1; k >= 0; k--) {

                            
                            
                            for(int a=0;a<prices.length;a++)
                            {

                                int maxDiff = T[i][j] - prices[a][j];
                                
                                if (T[i-1][k] - prices[a][k] == maxDiff) 
                                {
                                    i = i - 1;
                                    j = k;
                                    // buysell.put(j,a);
                                    daystack.addFirst(j);
                                    stockstack.addFirst(a);
                                    flag=1;
                                    count++;
                                    break;
                                }

                                

                            }
                            if(flag==1)
                            {
                                break;
                            }

                        
                    }
                    
                    
                }
            
        }
        

        while(!daystack.isEmpty()) {
            System.out.print(stockstack.pollFirst()+" "+daystack.pollFirst()+" "+daystack.pollFirst());
            System.out.println();
        }

    }
    
}

class task6b
{
    public static int maxProfit(int stockPrices[][], int K) {   
        if (K == 0 || stockPrices.length == 0) {
            return 0;
        }
        int tempArray[][] = new int[K+1][stockPrices[0].length];

        // THIS LOOP RUNS K TIMES
        for (int i = 1; i <= K; i++) {


            HashMap<Integer,Integer> maxDiff=new HashMap<>();

            for(int b=0;b<stockPrices.length;b++)
            {
                maxDiff.put(b, -stockPrices[b][0]);
            }
            

            for (int j = 1; j < tempArray[0].length; j++) {

                for(int a=0;a<stockPrices.length;a++)
                {    
                    // Make Entries Only if New value is more than current value
                    tempArray[i][j] = Math.max(Math.max(tempArray[i][j-1], stockPrices[a][j] + maxDiff.get(a)),tempArray[i][j]);
                    maxDiff.put(a,Math.max(maxDiff.get(a), tempArray[i-1][j] - stockPrices[a][j]));
                    
                }


            }
            

        }
        
        bottomUpToSolution(tempArray, stockPrices);
        return tempArray[K][stockPrices[0].length-1];
    }


    public static void bottomUpToSolution(int tempArray[][], int stockPrices[][]) {
        

        Deque<Integer> daystack = new LinkedList<>();
        Deque<Integer> stockstack = new LinkedList<>();
        
           
        int i = tempArray.length - 1;
        int j = tempArray[0].length - 1;
        int count=0;
        while(true) {
            
            if(i == 0 || j == 0) {
                break;
            }
            if (tempArray[i][j] == tempArray[i][j-1]) {
                j = j - 1;
            } else {
                daystack.addFirst(j);
                int flag=0;
                for (int k = j-1; k >= 0; k--) {

                        
                        
                        for(int a=0;a<stockPrices.length;a++)
                        {

                            int maxDiff = tempArray[i][j] - stockPrices[a][j];
                            
                            if (tempArray[i-1][k] - stockPrices[a][k] == maxDiff) 
                            {
                                i = i - 1;
                                j = k;
                                
                                daystack.addFirst(j);
                                stockstack.addFirst(a);
                                flag=1;
                                count++;
                                break;
                            }

                            

                        }
                        if(flag==1)
                        {
                            break;
                        }                     
                }
                
            }
        }
    
        while(!daystack.isEmpty()) {
            System.out.print(stockstack.pollFirst()+" "+daystack.pollFirst()+" "+daystack.pollFirst());
            System.out.println();
            
        }

    }


}

// QUESTION 2 CLASSES END HERE


// QUESTION 3 CLASSES START HERE
class task8
{
    public int maxProfitN2(int[][] prices, int c, int m, int n) {
        int final_day = 0;

        Queue<Integer>[] stock = new Queue[n];
        for (int i = 0; i < stock.length; i++)
            stock[i] = new LinkedList<Integer>();

        Queue<Integer>[] b = new Queue[n];
        for (int i = 0; i < b.length; i++)
            b[i] = new LinkedList<Integer>();

        Queue<Integer>[] s = new Queue[n];
        for (int i = 0; i < s.length; i++)
            s[i] = new LinkedList<Integer>();

        if (m == 0 || n < 2)
            return 0;
        int[] M = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // System.out.printf(" i : %d j : %d\n", i, j);
                if (i == 0)
                    M[0] = 0;
                else if (i == 1) {
                    M[1] = Math.max(Math.max(prices[j][1] - prices[j][0], 0), M[1]);
                    if (M[1] > prices[j][1] - prices[j][0]) {
                        // Nothing
                    } else if (prices[j][1] - prices[j][0] > 0) {
                        b[1].add(0);
                        s[1].add(1);
                        stock[1].add(j);
                    }
                    // System.out.printf("1111 : M[1] : %d && prices[j][1] - prices[j][0] : %d where
                    // j : %d \n", M[1],
                    // prices[j][1] - prices[j][0], j);
                } else {
                    M[i] = Math.max(M[i - 1], M[i]);

                    for (int k = 0; k < i; k++) {
                        int prev = k >= c + 1 ? M[k - c - 1] : 0;
                        // System.out.printf(
                        // "Before : M[i] : %d M[i-1] : %d && && prices[j][i] - prices[j][k] : %d prev :
                        // %d where j : %d and i : %d \n",
                        // M[i], M[i - 1], prices[j][i] - prices[j][k], prev, j, i);
                        if (prev + prices[j][i] - prices[j][k] > M[i]) {
                            b[i].clear();
                            s[i].clear();
                            stock[i].clear();

                            if (k >= c + 1) {
                                for (Integer item : b[k - c - 1]) {
                                    b[i].add(item);
                                }
                                for (Integer item : s[k - c - 1]) {
                                    s[i].add(item);
                                }
                                for (Integer item : stock[k - c - 1]) {
                                    stock[i].add(item);
                                }
                            }
                            b[i].add(k);
                            s[i].add(i);
                            stock[i].add(j);
                            M[i] = (prev + prices[j][i] - prices[j][k]);
                            final_day = i;
                            // System.out.printf(
                            // "After : M[i] : %d M[i-1] : %d && && prices[j][i] - prices[j][k] : %d prev :
                            // %d where j : %d and i : %d \n",
                            // M[i], M[i - 1], prices[j][i] - prices[j][k], prev, j, i);
                        }
                    }
                }
            }
        }
        // System.out.printf("%d\n", final_day);
        // for (int i = 0; i < b.length; i++) {
        //     System.out.println(b[i]);
        // }
        // System.out.printf("%dblleeehhhhhhhh\n", final_day);
        // for (int i = 0; i < s.length; i++) {
        //     System.out.println(s[i]);
        // }
        // System.out.printf("%dblleeehhhhhhhh SSSUPPPP\n", final_day);
        for (int i = 0; i < stock.length; i++) {
            System.out.println(stock[i]);
        }
        int size = b[final_day].size();
        for (int z = 0; z < size; z++) {
            int buy = b[final_day].remove();
            int sell = s[final_day].remove();
            int stocks = stock[final_day].remove();
            System.out.printf("%d %d %d\n", stocks, buy, sell);
        }
        // for (int x = n-2; x <= 0; x++) {
        // int pointer = n-1;
        // if(M[pointer] == M[x]){
        // pointer--;
        // }

        // }
        System.out.println(Arrays.toString(M));
        return M[n - 1];
    }
}

class task9b
{
    public int maxProfit(int[][] prices, int c, int m, int n) {
        if (m == 0 || n < 2)
            return 0;
        int[] M = new int[n];
        // int maxDiff = Integer.MIN_VALUE; // M[j - 2] - prices[j]
        int[] maxDiff = new int[m];
        Arrays.fill(maxDiff, Integer.MIN_VALUE);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i < c + 1)
                    maxDiff[j] = Math.max(maxDiff[j], -prices[j][i]);
                if (i == 0)
                    M[0] = 0;
                else if (i == 1) {
                    M[1] = Math.max(Math.max(prices[j][1] - prices[j][0], 0), M[1]);
                } else {
                    if ((i < c + 1)) {
                        int min = Math.min(prices[j][i], prices[j][i - 1]);
                        // M[i] = Math.max(M[i], prev + prices[j][i] - prices[j][k]);
                        M[i] = Math.max(Math.max(M[i - 1], prices[j][i] - min), M[i]);
                    } else {
                        // System.out.printf( "M[i] : %d M[i-1] : %d && maxdiff : %d where j : %d and i
                        // : %d \n",M[i],M[i-1],maxDiff[j],j,i);
                        M[i] = Math.max(Math.max(M[i - 1], maxDiff[j] + prices[j][i]), M[i]);
                        // System.out.printf( "M[i] : %d M[i-1] : %d && maxdiff : %d where j : %d and i
                        // : %d \n\n",M[i],M[i-1],maxDiff[j],j,i);
                        maxDiff[j] = Math.max(maxDiff[j], M[i - c - 1] - prices[j][i]);
                    }

                }
            }
        }
        System.out.println(Arrays.toString(M));
        return M[n - 1];
    }
}


// QUESTION 3 CLASSES END HERE


// MAIN CLASS
class Stocks {
    

    public static void main(String[] args) {
        Random r = new Random();
        int m,n,k,c;

        // TAKING INPUTS
        String taskName=args[0];
        
        
        if(taskName.equals("1") || taskName.equals("2") || taskName.equals("3a") || taskName.equals("3b"))
        {   
            
            Scanner sc=new Scanner(System.in);  
            m=sc.nextInt();
            n=sc.nextInt();
            int A[][]=new int[m][n];
            for(int i=0;i<m;i++)
            {
                for(int j=0;j<n;j++)

                {
                    A[i][j]=sc.nextInt();
                }
            }

            System.out.println();


            if(taskName.equals("1"))
            {
                task1 my_task1=new task1();
                my_task1.maxProfitBruteForce(A, m, n);
            }
            else if(taskName.equals("2"))
            {
                task2 my_task2=new task2();
                my_task2.maxDiffGreedy(A, m, n);
            }
            else if(taskName.equals("3a"))
            {
                task3a my_task3a=new task3a();
                my_task3a.recursiveMemoization(A, m, n);
            }
            else if(taskName.equals("3b"))
            {
                task3b my_task3b=new task3b();
                my_task3b.maxProfitBottUp(A,m,n);
            }
            
        }
        else if(taskName.equals("4") || taskName.equals("5") || taskName.equals("6a") || taskName.equals("6b"))
        {
            Scanner sc=new Scanner(System.in);  
            k=sc.nextInt();
            m=sc.nextInt();
            n=sc.nextInt();
            int A[][]=new int[m][n];
            for(int i=0;i<m;i++)
            {
                for(int j=0;j<n;j++)

                {
                    A[i][j]=sc.nextInt();
                }
            }

            System.out.println();

            
            if(taskName.equals("4"))
            {
                task1 my_task1=new task1();
                my_task1.maxProfitBruteForce(A, m, n);
            }
            else if(taskName.equals("5"))
            {
                task5 my_task5=new task5();
                my_task5.maximumProfit(A, 3  , 3, 5);
            }
            else if(taskName.equals("6a"))
            {
                task1 my_task1=new task1();
                my_task1.maxProfitBruteForce(A, m, n);
            }
            else if(taskName.equals("6b"))
            {
                task6b my_task6b=new task6b();
                my_task6b.maxProfit(A, k);
            }
            
            }
            else if(taskName.equals("7") || taskName.equals("8") || taskName.equals("9a") || taskName.equals("9b"))
            {
                Scanner sc=new Scanner(System.in);  
                c=sc.nextInt();
                m=sc.nextInt();
                n=sc.nextInt();
                
                
                int A[][]=new int[m][n];
                for(int i=0;i<m;i++)
                {
                    for(int j=0;j<n;j++)
    
                    {
                        A[i][j]=sc.nextInt();
                    }
                }
    
                System.out.println();
    
                
                
                if(taskName.equals("7"))
                {
                    // task6b my_task6b=new task6b();
                    // my_task6b.maxProfit(A, k);
                }
                else if(taskName.equals("8"))
                {
                    task8 my_task8=new task8();
                    my_task8.maxProfitN2(A, c,m,n);
                }
                else if(taskName.equals("9a"))
                {
                    // task6b my_task6b=new task6b();
                    // my_task6b.maxProfit(A, k);
                }
                else if(taskName.equals("9b"))
                {
                    task9b my_task9b=new task9b();
                    my_task9b.maxProfit(A,c,m,n);
                }

        }
        else
        {
            System.out.println("Invalid Input:");
            return;

        }

        // UNCOMMEND IF DEFAULT VALUES OF M N K NEEDED
        // int m=3;
        // int n = 5;
        // int k=3;


        // UNCOMMENT IF M N ARE REQUIRED RANDOMLY
        // int m = r.nextInt(100);
        // int n =r.nextInt(10000);
        
        // int i, j;
        // System.out.printf("m : %d && n : %d \n", m, n);
        // int A[][] = new int[m][n];
        // for (i = 0; i < m; i++) {
        //     for (j = 0; j < n; j++) {
        //         A[i][j] = r.nextInt(10000);
        //     }
        // }
        
        // Question1 Sample Test Case
        // int A[][]={{1,7,4,0,9},{4,8,8,2,4},{5,5,1,7,1}};

        // Question2 Sample Test Case
        // int A[][]={{1,7,4,0,9},{4,8,8,2,4},{5,5,1,7,1}};

        // Question3 Sample Test Case
        // int A[][]={{1,7,4,3,0,9},{4,8,8,2,4,6},{5,5,1,7,1,1}};


    }
}
