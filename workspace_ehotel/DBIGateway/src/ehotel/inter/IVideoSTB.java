package ehotel.inter;

import java.util.Vector;

import ehotel.domain.pms.eSTB;
import ehotel.domain.vod.Vod;

public interface IVideoSTB {
	/**
	 * get all STB
	 * 
	 * @return
	 */
	public Vector<eSTB> getSTBList();

	/**
	 * Lay danh sach phim trong he thong dua tren tang service chua dong bo ve
	 * stb
	 * 
	 * @param langid
	 * @param from
	 * @param tto
	 * @return
	 */
	public Vector<Vod> getVodsOutStb(int subjectid, String serial_num,
			int langid);

	/**
	 * Lay ds phim da dong bo ve stb
	 * 
	 * @param serial_num
	 * @param langid
	 * @param from
	 * @param tto
	 * @return
	 */
	public Vector<Vod> getVodsOfStb(String serial_num, int langid, int from,
			int tto);

	/**
	 * ds phim chua dong bo thanh cong ve stb
	 * 
	 * @param serial_num
	 * @param langid
	 * @return
	 */
	public Vector<Vod> getVods_UnSynCompleted(String serial_num, int langid);

	/**
	 * insert dong bo phim ve stb
	 * 
	 * @param serial_num
	 * @param str_vod_id
	 *            = format "(vodId1,vodId2,vodi
	 * @return
	 */
	public boolean insertSynVodSTB(String serial_num, int vod_id,
			String session_core_id, String file_size);

	/**
	 * set trang thai dong bo hoan tat phim ve stb
	 * 
	 * @param serial_num
	 * @param vod_id
	 * @return
	 */
	public boolean setSynStatus(String serial_num, String session_id);

	/**
	 * Lay session phien dong bo phim ve stb tu core
	 * 
	 * @param serail_num
	 * @param vod_id
	 * @return
	 */
	public String getSessionId(String serail_num, int vod_id);

	/**
	 * Dem so luong phim da duoc dong bo tren stb
	 * 
	 * @param serial_num
	 * @return
	 */
	public int countVodOnSTB(String serial_num);

	/**
	 * Tim kiem phim tren stb
	 * 
	 * @param serial_num
	 * @param searchCode
	 * @param langid
	 * @return
	 */
	public Vector<Vod> searchVodOnStb(String serial_num, String searchCode,
			int langid);

	public String getFilePath(int vod_id);

	public String getIpStb(String serial_num);

	public String getEvs_storage(String protocol);

}
