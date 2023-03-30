package com.optimasc.dom.impl;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;


/** Basic node implementation with the following attributes:
 *  <li>
 *  <ul>Does not support children nodes.</ul>
 *  <ul>Does not support node values</ul>
 *  <ul>Supports attributes</ul>
 *  </li>
 *  
 *  
 * @author Carl Eric Codere
 *
 */
public class AttributeNodeImpl extends NodeImpl
{
  protected NamedNodeMap attributes; 
  
  public AttributeNodeImpl(Document owner, String name)
  {
    super(owner, name, Node.ATTRIBUTE_NODE);
    attributes = new DefaultNamedNodeMap();
  }

  @Override
  public NamedNodeMap getAttributes()
  {
    return attributes;
  }

  @Override
  public boolean hasAttributes()
  {
    return attributes.getLength()>0;
  }
  
  public String getAttribute(String name)
  {
    Node node =  attributes.getNamedItem(name);
    if (node == null)
      return "";
    return node.getNodeValue();
  }

  public void setAttribute(String name, String value) throws DOMException
  {
    Attr attr = ownerDocument.createAttribute(name);
    attr.setValue(value);
    attributes.setNamedItem(attr);
  }

  public void removeAttribute(String name) throws DOMException
  {
    attributes.removeNamedItem(name);
  }

  public String getAttributeNS(String namespaceURI, String localName)
  {
    Node node = attributes.getNamedItemNS(namespaceURI, localName);
    if (node==null)
      return "";
    return node.getNodeValue();
  }

  public void setAttributeNS(String namespaceURI, String qualifiedName, String value)
      throws DOMException
  {
    Attr attr = ownerDocument.createAttributeNS(namespaceURI, qualifiedName);
    attr.setValue(value);
    attributes.setNamedItemNS(attr);
  }

  public void removeAttributeNS(String namespaceURI, String localName) throws DOMException
  {
    attributes.removeNamedItemNS(namespaceURI, localName);
  }

  public Attr getAttributeNodeNS(String namespaceURI, String localName)
  {
    return (Attr) attributes.getNamedItemNS(namespaceURI, localName);
  }

  public Attr setAttributeNodeNS(Attr newAttr) throws DOMException
  {
    return (Attr) attributes.setNamedItemNS(newAttr);
  }

  public boolean hasAttribute(String name)
  {
    return attributes.getLength()>0;
  }

  public boolean hasAttributeNS(String namespaceURI, String localName)
  {
    Node node = attributes.getNamedItemNS(namespaceURI, localName);
    if (node != null)
      return true;
    return false;
  }
  
  

}
