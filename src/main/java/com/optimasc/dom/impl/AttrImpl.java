package com.optimasc.dom.impl;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/** W3C DOM Level 2 Attr implementation.
 * 
 * @author carl
 *
 */
public class AttrImpl extends NodeImpl implements Attr
{
  protected boolean isSpecified;
  protected boolean isID;
  protected Element ownerElement;
  protected String nodeValue;
  
  public AttrImpl(Document owner, String namespaceURI, String qualifiedName)
  {
    super(owner,namespaceURI, qualifiedName, Node.ATTRIBUTE_NODE);
    isSpecified = false;
    isID = false;
    nodeValue = "";
    ownerElement = null;
  }
  
  public String getName()
  {
    return nodeName;
  }

  public boolean getSpecified()
  {
    return isSpecified;
  }

  public String getValue()
  {
    return nodeValue;
  }

  public void setValue(String value) throws DOMException
  {
    if (readOnly) 
    {
       throw new DOMException(DOMException.NO_MODIFICATION_ALLOWED_ERR, "Node is read-only");
    }
    nodeValue = value;
    if (value == null)
      isSpecified = false;
    else
      isSpecified = true;
  }
  
  
  public void setNodeValue(String nodeValue) throws DOMException
  {
    setValue(nodeValue);
  }

  public boolean isId()
  {
    return isID;
  }

  public Node getParentNode()
  {
    return null;
  }
  
  public Node appendChild(Node newChild) throws DOMException
  {
    throw new DOMException(DOMException.NOT_SUPPORTED_ERR,"Cannot adds children to Attr nodes.");
  }

  public Node insertBefore(Node newChild, Node refChild) throws DOMException
  {
    throw new DOMException(DOMException.NOT_SUPPORTED_ERR,"Cannot adds children to Attr nodes.");
  }
  

  public Node replaceChild(Node newChild, Node oldChild) throws DOMException
  {
    throw new DOMException(DOMException.NOT_SUPPORTED_ERR,"Cannot adds children to Attr nodes.");
  }

  public Element getOwnerElement()
  {
    return ownerElement;
  }

  public Object clone() throws CloneNotSupportedException
  {
    return super.clone();
  }

  public String getNodeValue()
  {
    return nodeValue;
  }
  
  
  
  
}



