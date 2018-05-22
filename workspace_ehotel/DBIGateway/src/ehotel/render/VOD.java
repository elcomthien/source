package ehotel.render;

import ehotel.impl.AMDCntVodImp;
import ehotel.impl.AMDSvcVodImp;
import ehotel.impl.EVSVodImp;
import ehotel.inter.AMDVod;
import ehotel.inter.IEVS;

public class VOD {

	public AMDVod getAMDCntVod() {
		return new AMDCntVodImp();
	}

	public AMDVod getAMDSvcVod() {
		return new AMDSvcVodImp();
	}

	public IEVS getEVSVod() {
		return new EVSVodImp();
	}

	public static final String SUBTITLE = "SUBTITLE";
	public static final String TRAILER = "TRAILER";
	public static final String VOD = "VOD";

}
