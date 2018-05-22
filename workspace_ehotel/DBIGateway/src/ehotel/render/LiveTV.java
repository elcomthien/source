package ehotel.render;

import ehotel.impl.AMDCntLiveTVImp;
import ehotel.impl.AMDSvcLiveTVImp;
import ehotel.inter.AMDLiveTV;

public class LiveTV {
	public AMDLiveTV getAMDCntLiveTV() {
		return new AMDCntLiveTVImp();
	}

	public AMDLiveTV getAMDSvcLiveTV() {
		return new AMDSvcLiveTVImp();
	}
}
