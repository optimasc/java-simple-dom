package com.optimasc.dom.impl;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.TypeInfo;

public class AttrImpl extends NodeImpl implements Attr
{
  protected boolean isSpecified;
  
  public AttrImpl(Document owner, String name)
  {
    super(owner, name, Node.ATTRIBUTE_NODE);
    isSpecified = false;
    nodeValue="";
  }
  
  public AttrImpl(Document owner, String namespaceURI, String qualifiedName)
  {
    super(owner,namespaceURI, qualifiedName, Node.ATTRIBUTE_NODE);
    isSpecified = false;
    nodeValue = "";
  }
  
  @Override
  public String getName()
  {
    return nodeName;
  }

  @Override
  public boolean getSpecified()
  {
    return isSpecified;
  }

  @Override
  public String getValue()
  {
    return nodeValue;
  }

  @Override
  public void setValue(String value) throws DOMException
  {
    nodeValue = value;
    if (value == null)
      isSpecified = false;
    else
      isSpecified = true;
  }
  
  
  
  
  @Override
  public void setNodeValue(String nodeValue) throws DOMException
  {
    setValue(nodeValue);
  }

  @Override
  public Element getOwnerElement()
  {
    return null;
  }

  @Override
  public TypeInfo getSchemaTypeInfo()
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean isId()
  {
    return false;
  }


}
