package com.elcom.adcenter.rvcadv.util;

public class UntilSocket {
	 //------------------------------------------------
    public static byte[] get_int_arr(int number)  /*Dao thong so cho tuong tich voi kieu int 4 byte cua java va c*/
    {    	
    	byte[] data = new byte[4];
    	data[0] = (byte)(number & 0xFF);
    	data[1] = (byte)((number>>8)&0xFF);
    	data[2] = (byte)((number>>16)&0xFF);
    	data[3] = (byte)((number>>24)&0xFF);
    	return data;
    }
    //-----------------------------------------------
    public static int get_int(byte[] data)
    {
    	return (((int)data[3]&0xFF)<<24) | (((int)data[2]&0xFF)<<16) | (((int)data[1]&0xFF)<<8) | ((int)data[0]&0xFF);
    	
    }
   //-----------------------------------------------
    public static byte[] pack_data(String srcfile,String desfile)
    {
    	byte[] data ;
    	byte[] srcfile_ = srcfile.getBytes();
    	byte[] desfile_ = desfile.getBytes();
    	int lensrc = srcfile_.length;
    	int lendes = desfile_.length;
    	data = new byte[lensrc + lendes];
        
    	return data;
    }
    
}
