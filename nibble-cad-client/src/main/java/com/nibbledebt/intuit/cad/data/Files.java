package com.nibbledebt.intuit.cad.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.HashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBHashCodeStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder={"files"})
@XmlRootElement(name="Files", namespace="http://schema.intuit.com/platform/fdatafeed/files/v1")
public class Files
  implements Serializable, Equals, HashCode
{
  private static final long serialVersionUID = 1L;
  @XmlElement(name="file", namespace="http://schema.intuit.com/platform/fdatafeed/files/v1")
  protected List<File> files;
  
  public List<File> getFiles()
  {
    if (this.files == null) {
      this.files = new ArrayList();
    }
    return this.files;
  }
  
  public void setFiles(List<File> files)
  {
    this.files = files;
  }
  
  public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy)
  {
    if (!(object instanceof Files)) {
      return false;
    }
    if (this == object) {
      return true;
    }
    Files that = (Files)object;
    
    List<File> lhsFiles = (this.files != null) && (!this.files.isEmpty()) ? getFiles() : null;
    
    List<File> rhsFiles = (that.files != null) && (!that.files.isEmpty()) ? that.getFiles() : null;
    if (!strategy.equals(LocatorUtils.property(thisLocator, "files", lhsFiles), LocatorUtils.property(thatLocator, "files", rhsFiles), lhsFiles, rhsFiles)) {
      return false;
    }
    return true;
  }
  
  public boolean equals(Object object)
  {
    EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
    return equals(null, null, object, strategy);
  }
  
  public int hashCode(ObjectLocator locator, HashCodeStrategy strategy)
  {
    int currentHashCode = 1;
    
    List<File> theFiles = (this.files != null) && (!this.files.isEmpty()) ? getFiles() : null;
    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "files", theFiles), currentHashCode, theFiles);
    
    return currentHashCode;
  }
  
  public int hashCode()
  {
    HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
    return hashCode(null, strategy);
  }
}
