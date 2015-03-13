import java.util.Scanner;

/*Calculate the total cost of an item including tax based on whether it is considered a necessary or luxury item. 
The tax rate for basic necessities is 1%, the tax rate for luxury items is 9%. For simplicity of implementation, 
all transactions are measured in cents (pennies).*/


public class TaxPerson {
	String[] luxItems = {"Tesla", "Lexus", "Audi", "BMW", "Benz"};
	String[] basicItems = {"Ford", "VolksWagen", "Toyota", "Honda", "Nissan"};
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TaxPerson tp = new TaxPerson();
		System.out.println("*************************************************************************");
		System.out.println("Please enter one of the item from this list and price seperated by comma");
		System.out.println("Tesla, Lexus, Audi, BWM, Benz, Ford, Volkswagen, Toyota, Honda, Nissan");
		System.out.println("*************************************************************************\n");
		
		Scanner sc = new Scanner(System.in);
		String car = sc.next();
		
		System.out.println("Please enter the price of the item\n");
		long price = sc.nextLong();

		
		int retVal = tp.checkItem(car);
		
		if(retVal == -1)
			System.out.println("Item not found in the lists");
		
		if(retVal == 1){
			price += (price * 9) / 100;
			System.out.println("Car price is cents = " + price);
		}
		
		if(retVal == 2){
			price += (price * 1) / 100;
			System.out.println("Car price is cents = " + price);
		}
			
		sc.close();
	}
	
	private int checkItem(String car){
		int val = -1;
		
		
		for(String x : this.luxItems){
			if(car.equalsIgnoreCase(x))
				return val = 1;
		}
		
		for(String x : this.basicItems){
			if(car.equalsIgnoreCase(x))
				return val = 2;
		}
		
		return val;
	}
}
