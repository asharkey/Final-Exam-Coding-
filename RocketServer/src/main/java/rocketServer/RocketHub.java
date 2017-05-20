package rocketServer;

import java.io.IOException;


import netgame.common.Hub;
import rocketBase.RateBLL;
import rocketData.LoanRequest;
import exceptions.RateException;


public class RocketHub extends Hub {

	private RateBLL _RateBLL = new RateBLL();
	
	public RocketHub(int port) throws IOException {
		super(port);
	}

	@Override
	protected void messageReceived(int ClientID, Object message) {
		
		double r = 0;
		double n = 0;
		double p = 0;
		double f = 0;
		double Payment = 0;
		boolean t = false;
		
		
		System.out.println("Message Received by Hub");
		
		if (message instanceof LoanRequest) {
			resetOutput();
			
			LoanRequest lq = (LoanRequest) message;
		
			
			int creditScore = lq.getiCreditScore();
			
			try {
				lq.setdRate(_RateBLL.getRate(lq.getiCreditScore()));
			}
			catch (RateException e) {
				sendToAll(e);
				System.out.println("There is an error. Stopped Processing!");
			}
			
			r = lq.getdRate()/(1200);
			n = lq.getiTerm()*12;
			p = lq.getdAmount();
			f = 0;
			t = false;
			Payment = RateBLL.getPayment(r, n, p, f, t);
			lq.setdPayment(Payment);
			
			sendToAll(lq);
		}
	}
}