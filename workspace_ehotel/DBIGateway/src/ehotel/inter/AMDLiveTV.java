package ehotel.inter;

import java.util.Vector;

import ehotel.domain.liveTV.LiveTV;
import ehotel.domain.vod.Subject;

/**
 * AMDLiveTV interface supply functions to manipulate database in LiveTV-Admin
 * side.
 * 
 * @author hoavk@elcom.com.vn
 * @since SDK1.6
 * @version ehotel2.0
 */
public interface AMDLiveTV extends BaseAMD {
	/**
	 * create a new channel Subject in service side
	 * 
	 * @param subject
	 *            new subject name to add to the system
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
	public boolean removeSubject(int subjId);

	/**
	 * update information of subject
	 * 
	 * @param subject
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
	 *         <code>Vector<Subject></code> otherwise.
	 * @exception java.rmi.RemoteException
	 *                java.sql.SQLException ,
	 */
	public Vector<Subject> getSubjects(int langid);

	/**
	 * add relative between channel and subject.
	 * 
	 * @param subjId
	 *            A sequence of the desired subject.
	 * @param str_channelId
	 *            A sequence of the desired channel.Format Begin "(" and the end
	 *            ")". Ex (chanelId1,channelId2)
	 * 
	 * @return <code>true</code> if the channel is completely added.
	 *         <code>false</code> otherwise.
	 */
	public boolean addLiveTV(int subjId, String str_channelId);

	/**
	 * get detail information of a channel
	 * 
	 * @param channelId
	 *            A sequence of the desired channel(channelId) to get info
	 * @param langId
	 *            Data is present on screen by language ( 1=VN,2=EN)
	 * @return return the object which contain entire channel information (such
	 *         as channelid
	 *         ,channelname,channelcode,servicename,physical_address). Should
	 *         not be <code>null</code>
	 * @exception java.rmi.RemoteException
	 *                java.sql.SQLException, java.lang.NullPointerException
	 */
	public LiveTV getChannelInfo(int channelId, int langId);

	/**
	 * update information of channel
	 * 
	 * @param channel
	 *            The object contain entire channel information (such as
	 *            channelid
	 *            ,channelname,channelcode,servicename,physical_address). Should
	 *            not be <code>null</code>
	 * @param langid
	 *            Data is present on screen by language ( 1=VN,2=EN)
	 * @return <code>1</code> if the channel is completely updated.
	 *         <code>-1</code> channel don't update.
	 *         <code>-2<code> is duplicate otherwise.
	 * @exception java.rmi.RemoteException
	 *                java.sql.SQLException ,
	 */
	public int updateChannel(LiveTV channel, int langid);

	/**
	 * remove channel from a subject.
	 * 
	 * @param subjId
	 *            A sequence of the desired subject.
	 * @param str_channelId
	 *            Start brackets '(' and ending ')'. The channelId comma
	 *            separated such as format
	 *            <code>(channelId1,channelId2,channelId3) </code>
	 * @return <code>true</code> if the movie is completely removed.
	 *         <code>false</code> otherwise.
	 * @exception java.rmi.RemoteException
	 *                java.sql.SQLException ,
	 */
	public boolean removeChannel(int subjId, String str_channelId);// content,service

	/**
	 * get channel listings are limited from this row to other rows
	 * 
	 * @param subjId
	 *            A sequence of the desired subject for getting movie listings
	 * @param langid
	 *            Data is present on screen by language ( 1=VN,2=EN)
	 * @param fromRow
	 *            Begining data is fromRow
	 * @param ttoRow
	 *            Ending data is ttoRow
	 * @return Vector empty if there is no channel in the subject.
	 *         <code>Vector<LiveTV></code> otherwise.
	 * @exception java.rmi.RemoteException
	 *                java.sql.SQLException ,
	 */
	public Vector<LiveTV> getChannels(int subjId, int langid, int fromRow,
			int ttoRow);// content,service

	/**
	 * change channel from this subject to another subjects
	 * 
	 * @param livetvId
	 *            A sequence of the desired channel for changing
	 * @param str_subj_id
	 *            Start brackets '(' and ending ')'. The VodId comma separated
	 *            such as <code>(subjId1,subjId2,subjId3)</code>
	 * 
	 * @return <code>true</code> if the value is completely changed.
	 *         <code>false</code> otherwise.
	 * @exception java.rmi.RemoteException
	 *                java.sql.SQLException
	 */
	public boolean changeSubject(int livetvId, String str_subj_id);// content,service

	public boolean changStatus(int subjctId, int livetvId);

	/**
	 * count channel of subject
	 * 
	 * @return 0 if there is no song in the subject.
	 *         <code> value greater than 0</code> otherwise.
	 * @exception java.rmi.RemoteException
	 *                java.sql.SQLException
	 */
	public int countChannels(int subjId);

	/**
	 * search channle in the subject via name
	 * 
	 * @param subjId
	 *            A sequence of the desired subject for searching movie
	 * @param liveName
	 *            name to search
	 * @param fromRow
	 *            Begining data is fromRow
	 * @param ttoRow
	 *            Ending data is ttoRow
	 * @return Vector empty if there is no channel in the subject.
	 *         <code>Vector<LiveTV></code> otherwise.
	 * @exception java.rmi.RemoteException
	 *                java.sql.SQLException
	 */
	public Vector<LiveTV> searchChannel(int subjId, String liveName,
			int fromRow, int ttoRow);// content,service

	public int countSearchChannel(int subject, String liveName);

	/**
	 * Lay ds cac kenh ben content
	 * 
	 * @param subjectId
	 * @param langid
	 * @return
	 */
	public Vector<LiveTV> getChannelContentOutSuject(int subjectId, int langid);

}
