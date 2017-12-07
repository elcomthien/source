package elcom.domain;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class Adapter extends XmlAdapter {
	@Override
	public Object unmarshal(Object v) throws Exception {
		// TODO Auto-generated method stub
		return v;
	}

	@Override
	public Object marshal(Object v) throws Exception {
		// TODO Auto-generated method stub
		if (v == null)
			return "<![CDATA[  ]]>";
		else
			return "<![CDATA[" + v + "]]>";
	}

}
