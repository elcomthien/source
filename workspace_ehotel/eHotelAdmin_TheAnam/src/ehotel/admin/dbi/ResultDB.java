package ehotel.admin.dbi;

import java.util.Vector;

public class ResultDB {
	private static int INDEX_COLUMN=0;
	@SuppressWarnings("rawtypes")
	private Vector columns;
	private int position=-1;
	@SuppressWarnings("rawtypes")
	private Vector data;
	
	@SuppressWarnings("rawtypes")
	public ResultDB(Vector v) {
		data=v;
		columns=(Vector)data.get(INDEX_COLUMN);
		if(data.size()>=3){
			position=1;
		}
	}
	
	public void next(){
		position++;
	}
	
	public void moveToFirst(){
		position=2;
	}
	
	@SuppressWarnings("rawtypes")
	public String getParam(String name){
		if(position < 2){
			return "";
		}
		Vector v=(Vector)data.get(position);
		int index=getIndex(name);
		if(index != -1){
			return v.get(index).toString();
		}
		return "";
	}
	
	public boolean hasNext(){
		if(position!= -1 && position<(data.size()-1)){
			return true;
		}
		return false;
	}
	
	public int size(){
		 return data.size()-2;
	}
	private int getIndex(String columnName){
		if(columns != null){
			for(int i =0; i< columns.size();i++){
				String name=columns.get(i).toString();
				name=name.toUpperCase();
				columnName=columnName.toUpperCase();
				if(columnName.equalsIgnoreCase(name)){
					return i;
				}
			}
			return -1;
		}
		return -1;
	}
	
	
	
}
