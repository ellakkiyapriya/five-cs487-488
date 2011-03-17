package org.tempuri;
import java.rmi.RemoteException;
import org.tempuri.HoSTC_ServiceStub.*;

public class Test {

	public static void FFPUT_THROUGHT_TEST()throws RemoteException  {
		HoSTC_ServiceStub stub = new HoSTC_ServiceStub();
		FPUT_THROUGHT fput = new FPUT_THROUGHT();
		FPUT_THROUGHTResponse fput_r = stub.fPUT_THROUGHT(fput);
		System.out.println(fput_r.getFPUT_THROUGHTResult());
	}
	
	//Gia tri cua 3 dot : VnIndex|%|?|?|KLKL|GTKL|date ?|?
	public static void GetLiveTotalMKT_TEST()throws RemoteException  {
		HoSTC_ServiceStub stub = new HoSTC_ServiceStub();
		GetLiveTotalMKT gTotalMKT = new GetLiveTotalMKT();
		GetLiveTotalMKTResponse gTotalMKT_r = stub.GetLiveTotalMKT(gTotalMKT);
		System.out.println(gTotalMKT_r.getGetLiveTotalMKTResult());
	}
	
	public static void GetFileTotal_TEST()throws RemoteException  {
		HoSTC_ServiceStub stub = new HoSTC_ServiceStub();
		GetFileTotal gfTotal = new GetFileTotal();
		GetFileTotalResponse gfTotal_r = stub.GetFileTotal(gfTotal);
		System.out.println(gfTotal_r.getGetFileTotalResult());
	}
	
	public static void GetLiveSecurity_TEST()throws RemoteException  {
		HoSTC_ServiceStub stub = new HoSTC_ServiceStub();
		GetLiveSecurity glS = new GetLiveSecurity();
		GetLiveSecurityResponse glS_r = stub.GetLiveSecurity(glS);
		System.out.println(glS_r.getGetLiveSecurityResult());
	}
	
	// Ma + ten cong ty
	public static void FGetSTOCKROOM_TEST()throws RemoteException  {
		HoSTC_ServiceStub stub = new HoSTC_ServiceStub();
		FGetSTOCKROOM fgS = new FGetSTOCKROOM();
		FGetSTOCKROOMResponse fgS_r = stub.fGetSTOCKROOM(fgS);
		System.out.println(fgS_r.getFGetSTOCKROOMResult());
	}
	
	
	// Number of online users
	public static void FUserOnline_TEST()throws RemoteException  {
		HoSTC_ServiceStub stub = new HoSTC_ServiceStub();
		FUserOnline fUO = new FUserOnline();
		FUserOnlineResponse fUO_r = stub.fUserOnline(fUO);
		System.out.println(fUO_r.getFUserOnlineResult());
	}
	
	public static void GetStatus_TEST()throws RemoteException  {
		HoSTC_ServiceStub stub = new HoSTC_ServiceStub();
		GetStatus gS = new GetStatus();
		GetStatusResponse gS_r = stub.getStatus(gS);
		System.out.println(gS_r.getGetStatusResult());
	}
	
	public static void main(String[] args) throws RemoteException {
		GetLiveTotalMKT_TEST();
	
			

		
	}
}
