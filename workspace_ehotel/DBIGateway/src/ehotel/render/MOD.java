package ehotel.render;

import ehotel.impl.AMDModImp;
import ehotel.impl.AMDSvcModImp;
import ehotel.impl.EASImp;
import ehotel.inter.AMDMod;
import ehotel.inter.IEAS;

public class MOD {

	public AMDMod getAMDSvcMod() {
		return new AMDSvcModImp();
	}

	public AMDMod getAMDCntMod() {
		return new AMDModImp();
	}

	public IEAS getEAS() {
		return new EASImp();
	}

}
