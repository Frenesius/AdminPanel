package manager;

import java.util.ArrayList;
import price.Price;

public class main {
	public static void main(String[] args){
		MySqlManager mysqlmann = new MySqlManager();
		GraphManager graphMan = new GraphManager();
		
		//ArrayList<Price> a = mysqlmann.getPricesByEan("4716659030010");
		
		String location = graphMan.generateGraphByEan("4716659030010");
		System.out.println(location);
	}
}
