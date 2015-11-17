package com.nibbledebt.intuit.cad.core;

public enum EntityName
{
  INSTITUTIONS("institutions"), 

  ACCOUNTS("accounts"), 

  CUSTOMERS("customers"), 

  TRANSACTIONS("transactions"), 

  LOGINS("logins"), 

  FILES("files"), 

  POSITIONS("positions");

  private String name = null;

  private EntityName(String name)
  {
    this.name = name;
  }

  public String toString()
  {
    return this.name;
  }
}