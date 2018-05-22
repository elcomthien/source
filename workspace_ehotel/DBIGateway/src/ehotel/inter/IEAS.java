package ehotel.inter;

import ehotel.domain.mod.Mod;

public interface IEAS {
	/**
	 * add new song content into subject.
	 * 
	 * @param subjId
	 *            A sequence of the desired subject
	 * @param mod
	 *            The object contain entire song information (such as
	 *            title,singer,director,composer,lyric). Should not be
	 *            <code>null</code>
	 * @param langid
	 *            Data is added by language ( 1=VN,2=EN)
	 * @return return <code>sequence</code> value which save in database.if the
	 *         subject is completely added.<code>-1</code> otherwise.
	 */
	public int addMod(int subjId, Mod mod, int langid);

	public boolean removeMod(int modId);

	/**
	 * get detail information of a song
	 * 
	 * @param modId
	 *            A sequence of the desired song.
	 * @param langId
	 *            Data is present on screen by language ( 1=VN,2=EN).
	 * @return <code> Mod</code>. The object contain entire information (such as
	 *         id,name,urlImage). May be is null.
	 * @exception java.rmi.RemoteException
	 *                java.sql.SQLException, java.lang.NullPointerException
	 */
	public Mod getModInfo(int modId, int langId);

	public boolean setUrl(int modId, String url);

	public boolean setClip(String fileName, int subjId, int vodiId);
}
