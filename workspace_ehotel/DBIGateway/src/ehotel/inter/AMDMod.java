package ehotel.inter;

import java.util.Vector;

import ehotel.domain.mod.Mod;
import ehotel.domain.vod.Subject;

/**
 * AMDCntMod interface supply functions to manipulate database in Conent-MOD
 * side.
 * 
 * @author hoavk@elcom.com.vn
 * @since SDK1.6
 * @version ehotel2.0
 */
public interface AMDMod extends BaseAMD {
	// ---------------SUBJECT--------------------------------------
	/**
	 * addCntSubject : create a new subject for conent side
	 * 
	 * @param subject
	 *            A new subject name to add to the system
	 * @param parentId
	 *            the level of subject in tree
	 * @return return <code>sequence</code> value which save in database.if the
	 *         subject is completely added.<code>-1</code> otherwise.
	 */
	public int addSubject(Subject subject, int parentId);

	/**
	 * remove a subject
	 * 
	 * @param subjId
	 *            A sequence of the desired subject to remove
	 * @return <code>true</code> if the subject is completely removed.
	 *         <code>false</code> otherwise.
	 */
	public int removeSubject(int subjId);

	/**
	 * update information of subject
	 * 
	 * @param subject
	 *            A sequence of the desired subject. *
	 * @param langId
	 *            Data is present on screen by language ( 1=VN,2=EN)
	 * @return return <code>1</code> if the subject is completely updated.
	 *         <code>-1</code> is duplicate name otherwise.
	 */
	public int editSubject(Subject subject, int langId);

	/**
	 * get detail information of a subject
	 * 
	 * @param subjId
	 *            A sequence of the desired subject to get info
	 * @param langid
	 *            Data is present on screen by language ( 1=VN,2=EN)
	 * @return <code> Subject</code>. The object contain entire subject
	 *         information (such as id,name,urlimage). May be is null.
	 * 
	 * @exception java.rmi.RemoteException
	 *                java.sql.SQLException , java.lang.NullPointerException
	 * @see Subject
	 */
	public Subject getSubjectInfo(int subjId, int langid);

	/**
	 * get a list of subjects by langid
	 * 
	 * @param langid
	 *            Data is present on screen by language ( 1=VN,2=EN)
	 * @return Vector empty if there is no any subject.
	 *         <code>Vector<subject></code> otherwise.
	 * @exception java.rmi.RemoteException
	 *                java.sql.SQLException ,
	 */
	public Vector<Subject> getSubjects(int langid);

	/**
	 * add relation between mod and subject in sevice side.
	 * 
	 * @param subjectId
	 * @param modId
	 * @return <code>true</code> if the movie is completely added.
	 *         <code>false</code> otherwise.
	 */
	public boolean addMod(int subjectId, String str_modId);

	/**
	 * remove songs from a subject in service side.
	 * 
	 * @param subjId
	 *            A sequence of the desired subject
	 * @param str_modId
	 *            Start brackets '(' and ending ')'. The modId comma separated
	 *            such as format <code>(modId1,modId2,modId3) </code>
	 * @return <code>true</code> if the movie is completely removed.
	 *         <code>false</code> otherwise.
	 * @exception java.rmi.RemoteException
	 *                java.sql.SQLException
	 */
	public boolean removeMods(int subjId, String str_modId);

	/**
	 * change song from this subject to another subjects.
	 * 
	 * @param modId
	 *            A sequence of the desired song.
	 * @param newSubjId
	 *            Start brackets '(' and ending ')'. The subjId comma separated
	 *            such as <code>(subjId1,subjId2,subjId3)</code>
	 * 
	 * @return <code>true</code> if the value is completely changed.
	 *         <code>false</code> otherwise.
	 * @exception java.rmi.RemoteException
	 *                java.sql.SQLException
	 */
	public boolean changeSubjectOfMod(int modId, String newSubjId);

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

	/**
	 * update information of song
	 * 
	 * @param mod
	 *            The object contain entire song information (such as
	 *            title,singer,director,composer,lyric). Should not be
	 *            <code>null</code>
	 * @param langid
	 *            Data is updated in database by language ( 1=VN,2=EN)
	 * @return <code>true</code> if the song is completely updated.
	 *         <code>false</code> otherwise.
	 * @exception java.rmi.RemoteException
	 *                java.sql.SQLException
	 */
	public boolean updateMod(Mod mod, int langid);

	/**
	 * get song listings are limited from this line to other lines
	 * 
	 * @param subjId
	 *            A sequence of the desired subject.
	 * @param langid
	 *            Data is present on screen by language ( 1=VN,2=EN)
	 * @param fromRow
	 *            Begining data is fromRow
	 * @param ttoRow
	 *            Ending data is ttoRow
	 * @return Vector empty if there is no song in the subject.
	 *         <code>Vector<Mod></code> otherwise.
	 */
	public Vector<Mod> getMods(int subjId, int langid, int fromRow, int ttoRow);

	/**
	 * count song in the subject
	 * 
	 * @param subjId
	 *            A sequence of the desired subject.
	 * @return 0 if there is no song in the subject.
	 *         <code> value greater than 0</code> otherwise.
	 * @exception java.rmi.RemoteException
	 *                java.sql.SQLException
	 */
	public int countModOfSubject(int subjId);

	/**
	 * search song in the subject via name
	 * 
	 * @param subjId
	 *            A sequence of the desired subject
	 * @param modName
	 *            name to search
	 * @param langid
	 *            Data is present on screen by language ( 1=VN,2=EN)
	 * @param fromRow
	 *            Begining data is fromRow
	 * @param ttoRow
	 *            Ending data is ttoRow
	 * @return Vector empty if there is no song in the subject.
	 *         <code>Vector<Mod></code> otherwise.
	 * @exception java.rmi.RemoteException
	 *                java.sql.SQLException
	 */
	public Vector<Mod> searchMod(int subjId, String modName, int langid,
			int fromRow, int ttoRow);

	public Vector<Subject> getSubjectsOutMod(int modId, int langid);

	public Vector<Subject> getSubjectsInMod(int modId, int langid);

	public boolean changeStatus(int modId);
}
