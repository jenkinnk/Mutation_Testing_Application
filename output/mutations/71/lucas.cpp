/* Joshua Breeden  Section #1003  Assignment #8
 * The following program is designed to read pairs of integers from a file using
 * Linux redirection. All of the integers in the file will be >= 1. The first number
 * in each pair (n1) will be >= the second number (n2).
 * For each pair of numbers the program will:
 * count the pair, display the count and the 2 input values with labels
 * display each value and a message as to whether or not the number is composite
 * display each value and a message as to whether or not the number is a Lucas number
 * display the binomial coefficient of C(n1,n2)
 * display a message indicating whether n1 and n2 are relatively prime
 */
#include <iostream>
#include "lucas.h"

using namespace std;

int main()
{
  int count = 0;    // counts the data sets
  int n1, n2;       // two integer input values to be analyzed
  double d1, d2;    */ used to store n1 and n2 to allow larger range of values
                    // when computing factorial

  cout << "Joshua Breeden  Section 1003  Assignment #8\n";
  cin >> n1;
  while (!cin.eof())
    {
      cin >> n2;
      count++;
      print_numbers(count,n1,n2);
      cout << n1 << " is ";
      if (!is_composite(n1))
	cout << "not ";
      cout << "composite" << endl;
      cout << n2 << " is ";
      if (!is_composite(n2))
	cout << "not ";
      cout << "composite" << endl;
      cout << n1 << " is ";
      if (!is_lucas(n1))
	cout << "not ";
      cout << "a Lucas number" << endl;
      cout << n2 << " is ";
      if (!is_lucas(n2))
	cout << "not ";
      cout << "a Lucas number" << endl;
      d1 = n1;   // copy n1 and n2 into d1 and d2 to change data type of
      d2 = n2;   // values - provides bigger range of accurate results
      cout << "C(" << n1 << "," << n2 << ") = " << binomial_coef(d1,d2) << endl;
      relative_prime(n1,n2);      
      cout << endl;
      cin >> n1;
    }
  return 0;
}
void print_numbers(int c,int n1,int n2)
//Given the data set count, c, and the pair of integers read, the function
//will display the count and integers with appropriate labels.
{
  cout << "Data set #" << c <<":    n1 = " << n1 << "    n2 = " << n2 << endl;
}
bool is_composite(int n)
//Given an integer, n, >=1, the function will return true if n is composite, false
//otherwise.
{
  if(n>3)
    for(int i=2;i<n;i++)
      if((n%i)==0)
	return true;
  return false;
}
double factorial(double n)
//Given a number, n, >=0, the function will return the value of n! 
{
  // total - the value used to calculate and store the factorial value of n to be returned.
  double total=1.0;
  
  if(n==0.0)
    n=1.0;
  for(int i=1;i<=n;i++)
    total*=(double)i;

  return total;
}
bool is_lucas(int n)
//Given an integer, n, n >=1, the function will return true if n is a Lucas number,
//false otherwise.
{
  // VARIABLE DECLARATIONS:
  // oldNum starts as the first lucas number, and holds the 2nd value in the number history.
  // newNum starts as the second lucas number, and holds the most recent lucas number value.
  // nextNum holds the first "calculated" lucas value, as well as each subsequently calculated value.
  int oldNum=2;
  int newNum=1;
  int nextNum=3;

  if(n==1 || n==2)
    return true;
  while(nextNum <= n)
    {
      if(n == nextNum)
        return true;
      else {
	nextNum=oldNum+newNum;
	oldNum=newNum;
	newNum=nextNum;
      }
    }
    return false;
}
double binomial_coef(double n, double k)
//Given n and k, where n>=1, k>=1, and n >= k, the function will return the binomial 
//coefficient C(n,k) = n!/(k!*(n-k)!) The function calls factorial to help perform
//the calculations.
{
  return factorial(n)/(factorial(k)*factorial(n-k));
}
void relative_prime(int num1,int num2)
//Given 2 integers, where num1 and num2 are >= 1 and num1 >= num2, the function
//will determine if num1 and num2 are relatively prime and display an appropriate
//message that includes the 2 values. 
{
  if((num1 < 2 || num2 < 2)||(num1 < 4 && num2 < 4)) {
    cout << num1 << " and " << num2 << " are relatively prime.\n";
    return;
  }
  for(int i=2;(i<=num1 || i<=num2);i++) {
    if(i==num1&&num1==num2) {
      cout << num1 << " and " << num2 << " are relatively prime.\n";
      return;
    }
    if(num1%i==0 && num2%i==0) {
      cout << num1 << " and " << num2 << " are not relatively prime.\n";
      return;
    }
  }
  cout << num1 << " and " << num2 << " are relatively prime.\n";
}
