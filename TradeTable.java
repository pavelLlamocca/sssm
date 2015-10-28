import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class TradeTable {

	public ArrayList<ArrayList<Object>> al_TradeTable = new ArrayList<ArrayList<Object>>();
	
	/*
	 * Trade Structure
	 * 0 - Code
	 * 1 - Timestamp
	 * 2 - Quantity of Shares
	 * 3 - By/Sell Ind
	 * 4 - Treaded Price
	 */
		
	/*
	 * Constructor TradeTable
	 * Initialize the Structure for store Trades
	 */
	public TradeTable(){
		al_TradeTable = new ArrayList<ArrayList<Object>>(); 
	}
	
	/*
	 * add_ElementToTradeTable
	 * Add a Trade given to the Structure 
	 */
	public void add_ElementToTradeTable(ArrayList<Object> al_Trade) {
		al_TradeTable.add(al_Trade);		
	}
	
	/*
	 * get_VolumeWeightedStockPrice
	 * Get the Volume Weighted Stock Price in the last X (as parameter) minutes
	 */
	public double get_VolumeWeightedStockPrice(int num_LastMinutes){
		
		Calendar cal_calendar = Calendar.getInstance();
		cal_calendar.setTime(new Date());	
		
		cal_calendar.add(Calendar.MINUTE,num_LastMinutes*-1);
		Date dte_PrevDate = cal_calendar.getTime();
		
		Double dbl_numerator=0.0,db_denominator=0.0;
		
		Date dte_TradeDate;
		Double dbl_TreatedPrice;
		Double dbl_Quantity;	
		
		for (ArrayList<Object> trades : this.al_TradeTable) {

			dte_TradeDate = (Date)trades.get(1);
			
			if ( dte_TradeDate.after(dte_PrevDate) || dte_TradeDate.equals(dte_PrevDate) ){
				
				dbl_TreatedPrice = Double.parseDouble(trades.get(4).toString().replace(",","."));
				dbl_Quantity = Double.parseDouble(trades.get(2).toString().replace(",","."));
				
				dbl_numerator = dbl_numerator + dbl_TreatedPrice*dbl_Quantity;
				db_denominator = db_denominator + dbl_Quantity;
				
			}
			

		}		
		
		return dbl_numerator/db_denominator;
	}
	
	/*
	 * print_Trades
	 * Loop over Trades Structure, and print each of them.
	 */
	public void print_Trades(){
		int int_cont=0;
		for (ArrayList<Object> trades : this.al_TradeTable) {	
			int_cont++;
			System.out.println ("Trade :"+int_cont+" Code:"+trades.get(0)+" TimeStamp:"+trades.get(1)+" Quantity:"+trades.get(2)+" Buy/Sell:"+trades.get(3)+" Price:"+trades.get(4));
		}		
	}
	
}
