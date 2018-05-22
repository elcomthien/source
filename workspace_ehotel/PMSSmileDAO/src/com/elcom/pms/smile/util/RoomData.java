package com.elcom.pms.smile.util;

public class RoomData {
	private String RN;
	private String CT;
	private String ID;
	private String RS;
	private String VM;

	public String getRN() {
		return RN;
	}

	public void setRN(String rN) {
		RN = rN;
	}

	public String getCT() {
		return CT;
	}

	public void setCT(String cT) {
		CT = cT;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getRS() {
		return RS;
	}

	public void setRS(String rS) {
		RS = rS;
	}

	public String getVM() {
		return VM;
	}

	public void setVM(String vM) {
		VM = vM;
	}

	@Override
	public String toString() {
		return "RoomData [RN=" + RN + ", CT=" + CT + ", ID=" + ID + ", RS=" + RS + ", VM=" + VM + "]";
	}
}
