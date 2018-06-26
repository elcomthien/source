package ehotel.admin.common;
import sun.net.TelnetInputStream;
import sun.net.ftp.FtpClient;
public class ftpmp3reader 
{
	private byte[] Artist=new byte[1];
	
	private byte[]title=new byte[1];
	private int rate = 0;	
	private int TimeFile;
	private String Host="";
	private int Port=21;
	private String User="";
	private String Pass="";
	FtpClient client=new FtpClient();
	/**
	 * 
	 * 
	 * @param host address server ftp
	 * @param port 
	 * @param user 
	 * @param pass
	 */
	public  ftpmp3reader(String host,int port,String user,String pass)
	{		
		this.Host=host;this.Port=port;this.User=user;this.Pass=pass;
		
		try
		{
			client.openServer(Host,Port);
			client.login(User,Pass);
			client.binary();
			
		
		}catch (Exception e) 
		{
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	/**
	 * 
	 * @param filename 
	 */
	public void read(String filename)
	{	
		try
		{			
			TelnetInputStream input=client.get(filename);		
			byte[]CRC=new byte[1];		
			byte [] id3=new byte[3];
			
				int n=-1;
				int length=0;
				n=input.read(id3);			
				byte []header=new byte[1024];
				String sid3=new String(id3);		
				
				if(sid3.equals("ID3"))
				{					
					int l=1;					
					while(l>0)
					{
						byte[] ct=new byte[4];						
						input.read(ct);
						String stype=new String(ct);
						input.read(ct);
						l=ct[3];	
						if(ct[0]!=0||ct[1]!=0||ct[2]!=0)break;					
						byte []type=new byte[l+2];						
						input.read(type);
						
						if(checkArtist(stype))
						{
							Artist=type;
						}
						if(checkTitle(stype))
						{
							title=type;
						}
						length+=10+l;
						//break;						
					};
					
					/**
					 * get rate bit
					 * 
					 */
					int f=5;
					while(n>0)
					{
						n=input.read(CRC);		
						if(CRC[0]==-1&f>4)
						{
							byte b[] =new byte[3];
							input.read(b);									
							int leve=getLayer(b[0]);
							int v=getversion(b[0]);
							int index=getBitindex(b[1]);	
							rate=getbitRate(index, leve, v);						
							break;
						}
						if(CRC[0]==0)
						{
							f++;
						}else
						{
							f=0;
						}
					}
					
					
				}else
				{
					byte b[] =new byte[4];		
					b=id3;
					
					
					if(b[0]==-1)
					{
						int l=getLayer(b[1]);
						int v=getversion(b[1]);
						int index=getBitindex(b[2]);	
						rate=getbitRate(index, l, v);					
						
					}else
					{
						while(n>0)
						{
							n=input.read(CRC);	
							if(CRC[0]==-1)
							{
								input.read(b);
								int l=getLayer(b[0]);
								int v=getversion(b[0]);
								int index=getBitindex(b[1]);				
								rate=getbitRate(index, l, v);
													
								
							}
						}					
					}						
					
				}	
				
				length=4;
				byte []b=new byte[1024];
				byte[]data=new byte[128];				
				n=input.read(header);		
				length+=n;
				while(n>-1)
				{						
										
						if(n>=128)
						{
							for(int i=0;i<128;i++)
							{
								data[i]=b[n-128+i];
							}						
							
						}else
						{
									
									for(int i=0;i<128-n;i++)
									{								
										data[i]=data[n+i];								
									}	
									for(int i=0;i<n;i++)
									{
										data[128-n+i]=b[i];
									}													
						}						
						n=input.read(b);
						length+=n;		
						
				}										
					
				byte []btag=new byte[3];
				btag[0]=data[0];btag[1]=data[1];btag[2]=data[2];
				String stag=new String(btag);			
					
					
				if(stag.equals("TAG"))
				{
						//System.out.println("TAG");
						byte[] btitle=new byte[30];
						byte[] bartist=new byte[30];
						
						for(int i=0;i<30;i++)
						{
							btitle[i]=data[i+3];
							bartist[i]=data[i+33];
						}
						title=btitle;
						Artist=bartist;
						
				}
					
				int intKiloBitFileSize = (int) ((8 * length) / 1000);
				TimeFile = (int) (intKiloBitFileSize / rate);	
				
				input.close();
				
			
		}catch (Exception e) 
		{
			e.printStackTrace();
			// TODO: handle exception
		}
		
		
	}
	public void close()
	{
		try
		{
		client.closeServer();
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	//get 30 byte from 128 byte after TAG 
	public int getDurion()
	{
		return TimeFile;
	}
	public byte[] getTitle()
	{
			
		return title;
	}
	public byte[] getArtist()
	{		
		return Artist;
	}
	private String getInfo(byte[]b)
	{
		byte[]b1=new byte[4];
		for(int i=0;i<4;i++)
		{
			b1[i]=b[i];
		}
		return new String(b1);	
	}
	private boolean checkTitle(String st)
	{
		if(st.equals("TIT1")||st.equals("TIT2")||st.equals("TIT3"))
		{
			return true;
		}
		return false;
	}
	private boolean checkArtist(String st)
	{
		if(st.equals("TPE1")||st.equals("TPE2")||st.equals("TPE3")||st.equals("TPE4"))
		{
			return true;
		}
		return false;
	}
	private int getversion(byte b)
	{
		int c=24;
		int v=b&c;
		if(v==24)
		{
			return 1;
		}else
		{
			return 2;
		}
	}
	private int getLayer(byte b)
	{
		
		int c=6;
		int l=b&c;
		if(l==2)
			return 3;
		if(l==4)return 2;
		if(l==5)return 1;
		return 0;
				
	}
	private int getbitRate(int b,int level, int version)
	{
		int[] l1 = { 0, 32, 64, 96, 128, 160, 192, 224, 256, 288, 320, 352,
				384, 416, 448, 0 };
		int[] l2 = { 0, 32, 48, 56, 64, 80, 96, 112, 128, 160, 192, 224, 256,
				320, 384, 0 };
		int[] l3 = { 0, 32, 40, 48, 56, 64, 80, 96, 112, 128, 160, 192, 224,
				256, 320, 0 };

		if(version==1)
		{
			switch (level)
			{
			case 1:return l1[b];
				
			case 2:return l2[b];
				
			case 3:return l3[b];				

			default:
				break;
			}
		}else
		{
			if(level==1)
			{
				return l1[b]>>1;
			}else
			{
				return l2[b]>>1;
			}
		}

		return 0;

	}
	private int getBitindex(byte b)
	{
		int c=b&240;
		c=c>>4;
		return c;
	}
}
