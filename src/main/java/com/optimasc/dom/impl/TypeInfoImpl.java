package com.optimasc.dom.impl;

import javax.xml.namespace.QName;

import org.w3c.dom.TypeInfo;

/** Implementation of the <code>org.w3c.dom.TypeInfo</code> DOM Level 3 Core specification interface.
 *
 * @author Carl Eric Codere
 */

public class TypeInfoImpl implements TypeInfo
{
  protected QName name;

  public TypeInfoImpl(QName name)
  {
    this.name = name;
  }

  @Override
  public String getTypeName()
  {
    return name.getLocalPart();
  }

  @Override
  public String getTypeNamespace()
  {
    return name.getNamespaceURI();
  }

  @Override
  public boolean isDerivedFrom(String arg0, String arg1, int arg2)
  {
    return false;
  }

}
