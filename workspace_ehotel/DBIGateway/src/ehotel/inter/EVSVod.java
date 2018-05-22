package ehotel.inter;

import java.util.Vector;

import ehotel.domain.vod.EVSStorage;
import ehotel.domain.vod.SubTitle;
import ehotel.domain.vod.Trailer;
import ehotel.domain.vod.Vod;

/**
 * AMDVod interface supply functions to manipulate database in EVS module side.
 * (inserting and removing data in database(such as movie,trailer,subtitle).
 * 
 * @author hoavk@elcom.com.vn
 * @since SDK1.6
 * @version ehotel2.0
 */
public interface EVSVod {
	/**
	 * addVod
	 * <p>
	 * The method which allows to add a movie into system
	 * <p>
	 * 
	 * @param subjectId
	 *            A sequence identifies subject which movie is added.
	 * 
	 * @param url
	 *            An absolute URL giving the base location of the movie.Should
	 *            not be <code>null</code>.
	 * 
	 * @param movieInfo
	 *            The object contain entire movie information (such as
	 *            title,actor,direction,plot,poster). Should not be
	 *            <code>null</code>. (see <a href="#Vod">Vod</a>)
	 * 
	 * @return int : return <code>sequence</code> value which save in
	 *         database.if the movie is completely added.<code>-1</code>
	 *         otherwise.
	 * @exception java.rmi.RemoteException
	 *                java.sql.SQLException
	 * 
	 * @see Vod
	 */
	public int addVod(int subjectId, int storageId, String url, Vod movieInfo);

	/**
	 * set an absolute URL giving the base location of the movie
	 * 
	 * @param vodId
	 *            A sequence identifies movie
	 * @param url
	 * @return return <code>sequence</code> value which save in database.if the
	 *         movie is completely added.<code>-1</code> otherwise.
	 */
	public boolean setUrl(int vodId, String url);

	/**
	 * <p>
	 * The method which allows to add a movie's trailer into system
	 * </p>
	 * 
	 * 
	 * @param vodId
	 *            A sequence of the desired movie which trailer is added.
	 * @param trailerInfo
	 *            the object contain entire trailer information (such as
	 *            title,url..). Should not be <code>null</code>.(see <a
	 *            href="#Trailer">Trailer</a>)
	 * @return return <code>sequence </code> value which save in database.if the
	 *         trailer is completely added.<code>-1</code> otherwise.
	 * @see Trailer
	 * @exception java.rmi.RemoteException
	 *                java.sql.SQLException
	 */
	public int addTrailer(int vodId, Trailer trailerInfo);

	/**
	 * <p>
	 * The method which allows to add a movie's subtitle into system
	 * <p>
	 * 
	 * @param vodId
	 *            A sequence of the desired movie which subtitle is added.
	 * @param subTitleInfo
	 *            the object contain entire subtile information (such as
	 *            title,url,language..). Should not be <code>null</code>.
	 * @return return <code>sequence </code> value which save in database.if the
	 *         subtile is completely added.<code>-1</code> otherwise.
	 */
	public int addSubtitle(int vodId, Vector<SubTitle> subTitleInfo);

	/**
	 * <p>
	 * The method which allows to remove a movie out system
	 * <p>
	 * 
	 * @param vodId
	 *            A sequence of the desired movie to remove
	 * @return <code>true</code> if the value is completely removed.
	 *         <code>false</code> otherwise.
	 * 
	 * @exception java.rmi.RemoteException
	 *                java.sql.SQLException
	 */
	public boolean removeVod(int vodId);

	/**
	 * <p>
	 * The method which allows to remove movie's tralier
	 * <p>
	 * 
	 * @param trailerId
	 *            A sequence of the desired trailer to remove
	 * 
	 * @return <code>true</code> if the value is completely removed.
	 *         <code>false</code> otherwise.
	 * @exception java.rmi.RemoteException
	 *                java.sql.SQLException
	 */
	public boolean removeTrailer(int trailerId);

	/**
	 * removeTrailer the method which allows to remove movie's subtile
	 * 
	 * @param subtileId
	 *            A sequence of the desired subtitle to remove
	 * @return <code>true</code> if the value is completely removed.
	 *         <code>false</code> otherwise.
	 * @exception java.rmi.RemoteException
	 *                java.sql.SQLException
	 */
	public boolean removeSubtitle(int subtileId);

	public Vector<EVSStorage> getEVSStorages();

}
