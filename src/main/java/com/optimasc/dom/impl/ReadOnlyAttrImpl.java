package com.optimasc.dom.impl;

import org.w3c.dom.Document;

public class ReadOnlyAttrImpl extends AttrImpl
{

  public ReadOnlyAttrImpl(Document owner, String name)
  {
    super(owner, name);
  }
  
  public ReadOnlyAttrImpl(Document owner, String namespaceURI, String qualifiedName)
  {
    super(owner,namespaceURI, qualifiedName);
    nodeValue = "";
  }
  

}
