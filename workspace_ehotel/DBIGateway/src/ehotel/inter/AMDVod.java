package ehotel.inter;

import java.util.Vector;

import ehotel.domain.vod.SubTitle;
import ehotel.domain.vod.Subject;
import ehotel.domain.vod.Vod;

/**
 * AMDVod interface supply functions to manipulate database in Admin-VOD side.
 * 
 * @author hoavk@elcom.com.vn
 * @since SDK1.6
 * @version ehotel2.0
 */
public interface AMDVod extends BaseAMD {
	/**
	 * create a new VOD Subject
	 * 
	 * @param subjName
	 *            new subject name to add to the system
	 * @param parentId
	 *            the level of subject in tree
	 * 
	 * @return return <code>sequence</code> value which save in database.if the
	 *         subject is completely added.<code>-1</code> otherwise.
	 */
	public int addSubject(String subjName, String urlImage, int parentId);

	/**
	 * remove a subject
	 * 
	 * @param subjId
	 *            A sequence of the desired subject to remove
	 * @return <code>true</code> if the subject is completely removed.
	 *         <code>false</code> otherwise.
	 */
	public boolean removeSubject(int subjId);

	/**
	 * update information of subject
	 * 
	 * @param subjId
	 *            A sequence of the desired subject to update info
	 * @param newName
	 *            a new desired subject's name
	 * @param langId
	 *            Data is present on screen by language ( 1=VN,2=EN)
	 * @return return <code>1</code> if the subject is completely updated.
	 *         <code>-1</code> is duplicate name otherwise.
	 */
	public int editSubject(int subjId, String newName, String urlImage,
			int langId);

	/**
	 * get detail information of a subject
	 * 
	 * @param subjId
	 *            A sequence of the desired subject to get info
	 * @param langid
	 *            Data is present on screen by language ( 1=VN,2=EN)
	 * @return <code> Subject</code>. The object contain entire information
	 *         (such as id,name,urlImage). May be is null.
	 * @see Subject
	 * @exception java.rmi.RemoteException
	 *                java.sql.SQLException , java.lang.NullPointerException
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
	 * Add relation between subject and movie in service-vod side
	 * 
	 * @param subjId
	 *            A sequence of the desired subject for adding movie
	 * @param vodId
	 *            A sequence of the desired movie
	 * @return <code>true</code> if the movie is completely added.
	 *         <code>false</code> otherwise.
	 * @exception java.rmi.RemoteException
	 *                java.sql.SQLException
	 */
	public boolean addVod(int subjId, String vodId);

	/**
	 * get detail information of a movie
	 * 
	 * @param vodId
	 *            A sequence of the desired movie to get info
	 * @param langId
	 *            Data is present on screen by language ( 1=VN,2=EN)
	 * @return return The object which contain entire movie information (such as
	 *         title,actor,direction,plot,poster). Maybe be <code>null</code>
	 * @exception java.rmi.RemoteException
	 *                java.sql.SQLException, java.lang.NullPointerException
	 */
	public Vod getVodInfo(int subjId, int vodId, int langId);// content,service

	/**
	 * update information of movie
	 * 
	 * @param vod
	 *            The object contain entire movie information (such as
	 *            title,actor,direction,plot,poster). Should not be
	 *            <code>null</code>
	 * @param langid
	 *            Data is present on screen by language ( 1=VN,2=EN)
	 * @return <code>true</code> if the movie is completely updated.
	 *         <code>false</code> otherwise.
	 * @exception java.rmi.RemoteException
	 *                java.sql.SQLException ,
	 */
	public boolean updateVod(Vod vod, int langid);// content,service

	/**
	 * remove movies from a subject
	 * 
	 * @param subjId
	 * @param str_vod_id
	 *            Start brackets '(' and ending ')'. The VodId comma separated
	 *            such as format <code>(vodId1,vodId2,vodI3) </code>
	 * @return <code>true</code> if the movie is completely removed.
	 *         <code>false</code> otherwise.
	 * @exception java.rmi.RemoteException
	 *                java.sql.SQLException ,
	 */
	public boolean removeVod(int subjId, String str_vod_id);// service

	public boolean setVisileStatus(int vodId, int subjId);

	public boolean setNewReleased(int vodId, int subjId);

	/**
	 * get movie listings are limited from this line to other lines
	 * 
	 * @param subjId
	 *            A sequence of the desired subject for getting movie listings
	 * @param langid
	 *            Data is present on screen by language ( 1=VN,2=EN)
	 * @param fromRow
	 *            Begining data is fromRow
	 * @param ttoRow
	 *            Ending data is ttoRow
	 * @return Vector empty if there is no film in the subject.
	 *         <code>Vector<Vod></code> otherwise.
	 * @exception java.rmi.RemoteException
	 *                java.sql.SQLException
	 */
	public Vector<Vod> getVods(int subjId, int langid, int fromRow, int ttoRow);// content,service

	/**
	 * Ham gianh cho phia service load ds vod phia content Load ds vod tu kho
	 * content ma chua thuoc subj o tang service
	 * 
	 * @param subj_cnt
	 * @param subj_svc
	 * @param langid
	 * @param frRow
	 * @param toRow
	 * @return
	 */
	public Vector<Vod> getVascVods(int subj_cnt, int subj_svc, int langid,
			int frRow, int toRow);

	public Vector<SubTitle> getSubtiles(int vodId);

	public boolean updateSubtitleLang(int subtitleId, int langId);

	/**
	 * change movie from this subject to another subject in content side
	 * 
	 * @param vodId
	 *            A sequence of the desired movie
	 * @param newSubjId
	 *            A sequence of new desired subject for changing
	 * @return <code>true</code> if the movie is completely changed.
	 *         <code>false</code> otherwise.
	 * @exception java.rmi.RemoteException
	 *                java.sql.SQLException
	 */
	public boolean changeSubjectOfVod(int vodId, int newSubjId);// content

	/**
	 * change movie from this subject to another subjects in content side
	 * 
	 * @param vodId
	 *            A sequence of the desired movie for changing
	 * @param newSubjId
	 *            Start brackets '(' and ending ')'. The SubjId comma separated
	 *            such as format <code>(SubjId1,SubjId2,SubjId3) </code> A
	 *            sequence of new desired subject for changing
	 * @return <code>true</code> if the value is completely changed.
	 *         <code>false</code> otherwise.
	 * @exception java.rmi.RemoteException
	 *                java.sql.SQLException
	 */
	public boolean changeSubjectOfSvcVod(int vodId, String str_newSubjId);// service

	/**
	 * count movie in the subject
	 * 
	 * @param subjId
	 *            A sequence of the desired subject
	 * @return 0 if there is no song in the subject.
	 *         <code> value greater than 0</code> otherwise.
	 * @exception java.rmi.RemoteException
	 *                java.sql.SQLException
	 */
	public int countVodOfSubject(int subjId);

	/**
	 * search movie in a subject via name
	 * 
	 * @param subjId
	 *            A sequence of the desired subject for searching movie
	 * @param vodName
	 *            name to search
	 * @param langid
	 *            Data is present on screen by language ( 1=VN,2=EN)
	 * @param fromRow
	 *            Begining data is fromRow
	 * @param ttoRow
	 *            Ending data is ttoRow
	 * @return Vector empty if there is no film in the subject.
	 *         <code>Vector<Vod></code> otherwise.
	 * @exception java.rmi.RemoteException
	 *                java.sql.SQLException
	 */
	public Vector<Vod> searchVod(int subjId, String vodName, int langid,
			int fromRow, int ttoRow);// // content,service

	public Vector<Subject> getSubjectsOutVod(int vodId, int langid);

	public Vector<Subject> getSubjectsInVod(int vodId, int langid);
}
