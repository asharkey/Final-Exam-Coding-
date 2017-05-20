package rocketBase;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import rocketDomain.RateDomainModel;

public class Rate_Test {
	
	//TODO - RocketDAL rate_test
	//		Check to see if a known credit score returns a known interest rate
	@Test
	public void test1()
	{
		
		
		RateDomainModel testRate=new RateDomainModel();

		ArrayList<RateDomainModel> list=RateDAL.getAllRates();
		
		for(RateDomainModel r: list)
		{
			if(r.getiMinCreditScore()==600)
			{
				testRate=r;
				
				break;
			}
		}

		assertTrue(testRate.getdInterestRate()==7);

	}
	
	//TODO - RocketDAL rate_test
	//		Check to see if a RateException is thrown if there are no rates for a given
	//		credit score

	
	@Test
	public void test2()
	{
		RateDomainModel testRate=new RateDomainModel();

		ArrayList<RateDomainModel> list=RateDAL.getAllRates();
		
		testRate=list.get(0);
		
		testRate.setiMinCreditScore(-1);
		
		testRate.getdInterestRate();
	}
	

}