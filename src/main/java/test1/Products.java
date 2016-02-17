/**
 * Mimosa Networks, Inc. Confidential
 *
 * Copyright © 2015  Mimosa Networks, Inc.
 * All rights reserved.
 *
 * Notice: All information contained herein is, and remains the
 * property of Mimosa Networks, Inc. and its suppliers, if any.  The
 * intellectual and technical concepts contained herein are
 * proprietary to Mimosa Networks, Inc. and/or its suppliers, and may
 * be covered by U.S. and foreign patents or patents in process, and
 * are protected by trade secret or copyright law.  Dissemination of
 * this information or reproduction of this material is strictly
 * forbidden unless prior written permission is obtained from Mimosa
 * Networks, Inc.
 */
package test1;

public enum Products {
  
  B5 (1,"B5","Mimosa B5 Product", "10"), 
  B5c (2,"B5c","Mimosa B5c Product", "40"),
  B5Lite (3, "B5-Lite", "Mimosa B5Lite Product", "102,88"),
  B5cLite (4, "B5c-Lite", "Mimosa B5Lite Product", "402");
  
  private int code;
  private String label;
  private String description;
  private String serialNoPrefix;
  
  Products(int code){
    this.code = code;
  }
  
  Products(int code, String label, String desc, String serialNoPrefix){
    this.code = code;
    this.label = label;
    this.description = desc;
    this.serialNoPrefix = serialNoPrefix;
  }
  
  public String getLabel(){
    return this.label;
  }
  
  public int getCode(){
    return this.code;
  }
  
  public String getDescription(){
    return this.description;
  }

  public String getSerialNoPrefix() {
    return serialNoPrefix;
  }
  
  //TODO: Add serialPrefix in device-type.xml for each tmm (type/manufacturer/model)
  public boolean isB5Lite(String serial) {
    for(String prefix : this.serialNoPrefix.split(",")) {
      if(serial.startsWith(prefix)){
        return true;
      }
    }
    return false;
  }
  
}
