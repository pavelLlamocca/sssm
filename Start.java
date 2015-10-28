import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Start {

	public static void main (String args[]) throws InterruptedException{

		/************************************************************/
		/* Test for the Global Beverage Corporation Exchange Table  */
		/************************************************************/
		GlobalBeverageCorporationExchange gbce_table = new GlobalBeverageCorporationExchange();
		
		/*
		 * Input the Stock and the Price
		 */
		String str_StockSimbol="GIN";
		Double dbl_InpuPrice=20.44;				
		
		/*
		 * GBCE All Share Calculation
		 */
		System.out.println ("GBCE All Share is : "+gbce_table.get_PricesGeometricMean());
			
		/*
		 * Dividend Yield and P/E Calculation
		 */
		System.out.println ("Dividend Yield for the Stock : "+str_StockSimbol+" and the price : "+dbl_InpuPrice+" is : "+gbce_table.get_DividendYiled(str_StockSimbol,dbl_InpuPrice));
		System.out.println ("P/E Ratio for the Stock : "+str_StockSimbol+" and the price : "+dbl_InpuPrice+" is : "+gbce_table.get_PERatio(str_StockSimbol,dbl_InpuPrice));		
		

		/**********************************/
		/* Test for the trades Recording  */
		/**********************************/
		
		TradeTable trade_table = new TradeTable();		
		
		/*
		 * Trade Structure
		 * 0 - Code
		 * 1 - Timestamp
		 * 2 - Quantity of Shares
		 * 3 - Buy/Sell Ind
		 * 4 - Treaded Price
		 */		
		ArrayList<Object> al_Trade;
		
		Calendar cal_Calendar = Calendar.getInstance();
		SimpleDateFormat sdf_Format = new SimpleDateFormat("yyyyMMDDHHmmss");
		
		Date dte_Today;  	
		String str_TradeCode;		
		
		
		/*
		 * Input a Trade		
		 */
		cal_Calendar.setTime(new Date());		
		dte_Today = cal_Calendar.getTime();
		str_TradeCode = sdf_Format.format(dte_Today);

		al_Trade = new ArrayList<Object>();
		al_Trade.add(str_TradeCode); //Code : Time in YYYYMMDDHHMMSS Format. Automatically calculated
		al_Trade.add(dte_Today); //Timestamp : System Date. Automatically calculated
		al_Trade.add(20); // Quantity of Shares : Input by the user
		al_Trade.add("B"); // Buy/Sell Indicator : Input by the user
		al_Trade.add(20.421); // Treader Price : Input by the user
		trade_table.add_ElementToTradeTable(al_Trade); // STORE TRADE DATA
		trade_table.print_Trades(); //Show the stored Trades
		
		Thread.sleep(1000);

		/*
		 * Input a Trade		
		 */
		cal_Calendar.setTime(new Date());		
		dte_Today = cal_Calendar.getTime();
		str_TradeCode = sdf_Format.format(dte_Today);
		
		al_Trade = new ArrayList<Object>();
		al_Trade.add(str_TradeCode);
		al_Trade.add(dte_Today);
		al_Trade.add(15);
		al_Trade.add("B");
		al_Trade.add(18.43);
		trade_table.add_ElementToTradeTable(al_Trade); // STORE TRADE DATA	
		trade_table.print_Trades(); //Show the stored Trades
		
		Thread.sleep(1000);
		
		/*
		 * Input a Trade		
		 */
		cal_Calendar.setTime(new Date());		
		dte_Today = cal_Calendar.getTime();
		str_TradeCode = sdf_Format.format(dte_Today);
		
		al_Trade = new ArrayList<Object>();
		al_Trade.add(str_TradeCode);
		al_Trade.add(dte_Today);
		al_Trade.add(40);
		al_Trade.add("S");
		al_Trade.add(30.68);
		trade_table.add_ElementToTradeTable(al_Trade); // STORE TRADE DATA		
		trade_table.print_Trades(); //Show the stored Trades
	
		/*
		 * Volume Weighted Stock Price Calculation for the input trades in the last 15 (as parameter) minutes
		 */
		double dbl_vwsp = trade_table.get_VolumeWeightedStockPrice(15);
		
		System.out.println ("The Volume Weighted Stock Price for the trades input in the last 15 minutes is : "+dbl_vwsp);

	}
	
}
