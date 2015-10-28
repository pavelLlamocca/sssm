import java.util.ArrayList;
import java.util.Objects;

public class GlobalBeverageCorporationExchange {
	
	public ArrayList<ArrayList<Object>> al_gbce = new ArrayList<ArrayList<Object>>();
	
	private ArrayList<Object> al_GbceRec; 
	
	/*
	 * Constructor GlobalBeverageCorporationExchange
	 * Fill the Global beverage Corporation Exchange Table
	 */
	public GlobalBeverageCorporationExchange ()
	{
		al_GbceRec = new ArrayList<Object>();		
		al_GbceRec.add("TEA");
		al_GbceRec.add("Common");
		al_GbceRec.add(0);
		al_GbceRec.add(null);
		al_GbceRec.add(100);		
		al_gbce.add(al_GbceRec);

		al_GbceRec = new ArrayList<Object>();
		al_GbceRec.add("POP");
		al_GbceRec.add("Common");
		al_GbceRec.add(8);
		al_GbceRec.add(null);
		al_GbceRec.add(100);
		al_gbce.add(al_GbceRec);
		
		al_GbceRec = new ArrayList<Object>();
		al_GbceRec.add("ALE");
		al_GbceRec.add("Common");
		al_GbceRec.add(23);
		al_GbceRec.add(null);
		al_GbceRec.add(60);
		al_gbce.add(al_GbceRec);
		
		al_GbceRec = new ArrayList<Object>();
		al_GbceRec.add("GIN");
		al_GbceRec.add("Preferred");
		al_GbceRec.add(8);
		al_GbceRec.add(2);
		al_GbceRec.add(100);
		al_gbce.add(al_GbceRec);		
		
		al_GbceRec = new ArrayList<Object>();
		al_GbceRec.add("JOE");
		al_GbceRec.add("Common");
		al_GbceRec.add(13);
		al_GbceRec.add(null);
		al_GbceRec.add(250);
		al_gbce.add(al_GbceRec);		
		
	}
	
	/*
	 * get_RecordIndexFromStockSimbol
	 * Look for a Stock Simbol in the GBCE Structure, and return the index of the Record which contains the Stock Simbol
	 */
	private int get_RecordIndexFromStockSimbol(String str_StockSimbol){
		
		int int_index=0;
		boolean bl_StockSimbolFound=false;
		
		for (ArrayList<Object> gbce : this.al_gbce) {			

			if ( Objects.equals(gbce.get(0),str_StockSimbol) ){
				bl_StockSimbolFound=true;
				break;				
			}
				

			int_index++;
		}

		if (!bl_StockSimbolFound){
			try {
				throw new StockSimbolNotFoundException();			    
			} catch (StockSimbolNotFoundException e) {
				e.printStackTrace();
			}
			System.exit(1);
		}
		
		return int_index;
	}

	/*
	 * get_DividendYiled
	 * Calculate the Dividiend Yield for a Stock Simbol and Inut Price given.
	 */
	public double get_DividendYiled(String str_StockSimbol, double dbl_InputPrice) {
		
		double dbl_numerator;
		ArrayList <Object> al_Record = new ArrayList <Object>();
		al_Record = al_gbce.get(get_RecordIndexFromStockSimbol(str_StockSimbol));
		
		if (Objects.equals(al_Record.get(1), "Common")) 
			dbl_numerator = Integer.parseInt(al_Record.get(2).toString());			
		else
			dbl_numerator = ( Integer.parseInt(al_Record.get(3).toString()) * Integer.parseInt(al_Record.get(4).toString()) ) / 100;
		
		
		return dbl_numerator/dbl_InputPrice;
	}
	
	/*
	 * get_PERatio
	 * Calculate the P/E Ratio for a Stock Simbol and Inut Price given.
	 */	
	public double get_PERatio(String str_StockSimbol, double dbl_InputPrice) {
	
		return dbl_InputPrice/get_DividendYiled(str_StockSimbol,dbl_InputPrice);
	}
	
	/*
	 * get_PricesGeometricMean
	 * Calculate the GBCE All Share for all records in GBCE Structure
	 */		
	public double get_PricesGeometricMean()	{
		
		double dbl_value=1.0;
		
		for (ArrayList<Object> gbce : this.al_gbce)
			dbl_value = dbl_value*Double.parseDouble(gbce.get(4).toString());
		
		return Math.pow(dbl_value, (double)1/this.al_gbce.size());
	}

}

/*
 * Exception Class to control an Invalid Stock Simbol
 */
class StockSimbolNotFoundException extends Exception {

	public StockSimbolNotFoundException(){
	      super("Please Input a Valid Stock Simbol,!");	      
	}
}