//Name: Rachi Rana
//CWID: 10455300

package complex;

public class Complexity {

	public static void method1(int n) {		//Time complexity O(n^2).

			int counter = 0;
			for(int i = 0; i < n; i++) { 		// n+1 (use of nested loop)
				for(int j =0; j < n; j++) {		//n*(n+1)
					System.out.println("Operation "+counter); //n*n
					counter++;
					} // the degree of polynomial is n^2
				}
		}

	public static void method2(int n){		 //Time complexity O(n^3).

			int counter = 0;
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					for(int k = 0; k < n; k++) {
					System.out.println("Operation "+counter);
					counter++;
					}
				}
			}

		}

	public static void method3(int n){ 		//Time complexity O(logn).

			int counter = 0;
			for(int i = 1; i < n; i *= 2){ //for(i=n; i>=1; i=i/2)
				System.out.println("Operation "+counter);
				counter++;
				// log taken as ceil() or floor() value:important

			}
		}

	public static void method4(int n){ 		//Time complexity O(nlogn)

			int counter = 0;
			for(int i = 1; i < n; i++){	//n
				for(int j = 1; j <= n; j *= 2){	//nlogn
					System.out.println("Operation "+counter);	//nlogn
					counter++;

					}//2nlogn+n->O(nlogn)
				}
		}

	public static void method5(int n){ 		//Time complexity O(loglogn).

			int counter = 0;
			for(int i = 2; i < n; i =i*i){
				System.out.println("Operation "+counter);
				counter++;
			}
		}

	static int counter1 =1;
	public static int method6(int n){ 			//Time complexity O(2^n)

			if(n <= 1){
				counter1++;
	            System.out.println("Operation " + counter1);
	            return n;
			}
			counter1++;
			return method6(n-2)+ method6(n-1);
		}

}
