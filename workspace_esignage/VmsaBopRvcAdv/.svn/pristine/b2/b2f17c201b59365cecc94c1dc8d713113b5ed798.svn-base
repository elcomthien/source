package com.elcom.adcenter.rvcadv.broker;

import java.util.Vector;

import java.io.Serializable;

public class SubProParam extends Object implements Serializable
{
  public static final int IN = 0;
  public static final int OUT = 1;
  public static final int INOUT = 2;

  private Object value = new String();
  private int type = 0;
  private String nameOfTypeArray = new String();

  public SubProParam(Object aValue, int aType)
  {
    value = aValue;
    type = aType;
  }

  public SubProParam(Vector aValues, String aNameOfTypeArray, int aType)
  {
    value = aValues;
    nameOfTypeArray = aNameOfTypeArray;
    type = aType;
  }

  public int getType() { return type; }

  public Object getValue() { return value; }

  public String getNameOfTypeArray()
  {
    return nameOfTypeArray;
  }

  /**
   *
   * @return null neu kieu cua thong so khong phai la java.lang.String
   */
  public java.lang.String getString()
  {
    if (value instanceof java.lang.String) return (java.lang.String)value;
    return null;
  }

  /**
   *
   * @return null neu kieu cua thong so khong phai la java.sql.Timestamp
   */
  public java.sql.Timestamp getTimeStamp()
  {
    if (value instanceof java.sql.Timestamp) return (java.sql.Timestamp)value;
    return null;
  }

  /**
   *
   * @return null neu kieu cua thong so khong phai la java.math.BigDecimal
   */
  public java.math.BigDecimal getBigDecimal()
  {
    if (value instanceof java.math.BigDecimal) return (java.math.BigDecimal)value;
    return null;
  }

  public java.util.Vector getVector()
  {
    if (value instanceof java.util.Vector) return (java.util.Vector)value;
    return null;
  }

}
