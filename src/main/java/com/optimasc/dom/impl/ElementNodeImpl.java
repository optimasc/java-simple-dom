package com.optimasc.dom.impl;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.TypeInfo;

public class ElementNodeImpl extends NodeImpl implements Element
{
  protected NamedNodeMap attributes;

  public ElementNodeImpl(Document owner,String name)
  {
    super(owner,name, Node.ELEMENT_NODE);
    attributes = new DefaultNamedNodeMap();
  }

  @Override
  public void setNodeValue(String nodeValue) throws DOMException
  {
    /* As specified by the specification, this is ignored for Element nodes */
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

  @Override
  public String getTagName()
  {
    return nodeName;
  }

  @Override
  public String getAttribute(String name)
  {
    Node node =  attributes.getNamedItem(name);
    if (node == null)
      return "";
    return node.getNodeValue();
  }

  @Override
  public void setAttribute(String name, String value) throws DOMException
  {
    Attr attr = ownerDocument.createAttribute(name);
    attr.setValue(value);
    attributes.setNamedItem(attr);
  }

  @Override
  public void removeAttribute(String name) throws DOMException
  {
    attributes.removeNamedItem(name);
  }

  @Override
  public Attr getAttributeNode(String name)
  {
    return (Attr)attributes.getNamedItem(name);
  }

  @Override
  public Attr setAttributeNode(Attr newAttr) throws DOMException
  {
    return (Attr) attributes.setNamedItem(newAttr);
  }

  @Override
  public Attr removeAttributeNode(Attr oldAttr) throws DOMException
  {
    return (Attr) attributes.removeNamedItem(oldAttr.getName());
  }

  @Override
  public NodeList getElementsByTagName(String name)
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getAttributeNS(String namespaceURI, String localName)
  {
    Node node = attributes.getNamedItemNS(namespaceURI, localName);
    if (node==null)
      return "";
    return node.getNodeValue();
  }

  @Override
  public void setAttributeNS(String namespaceURI, String qualifiedName, String value)
      throws DOMException
  {
    Attr attr = ownerDocument.createAttributeNS(namespaceURI, qualifiedName);
    attr.setValue(value);
    attributes.setNamedItemNS(attr);
  }

  @Override
  public void removeAttributeNS(String namespaceURI, String localName) throws DOMException
  {
    attributes.removeNamedItemNS(namespaceURI, localName);
  }

  @Override
  public Attr getAttributeNodeNS(String namespaceURI, String localName)
  {
    return (Attr) attributes.getNamedItemNS(namespaceURI, localName);
  }

  @Override
  public Attr setAttributeNodeNS(Attr newAttr) throws DOMException
  {
    return (Attr) attributes.setNamedItemNS(newAttr);
  }

  @Override
  public NodeList getElementsByTagNameNS(String namespaceURI, String localName)
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean hasAttribute(String name)
  {
    return attributes.getLength()>0;
  }

  @Override
  public boolean hasAttributeNS(String namespaceURI, String localName)
  {
    Node node = attributes.getNamedItemNS(namespaceURI, localName);
    if (node != null)
      return true;
    return false;
  }

  @Override
  public TypeInfo getSchemaTypeInfo()
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setIdAttribute(String arg0, boolean arg1) throws DOMException
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void setIdAttributeNS(String arg0, String arg1, boolean arg2) throws DOMException
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void setIdAttributeNode(Attr arg0, boolean arg1) throws DOMException
  {
    // TODO Auto-generated method stub

  }

  public void removeAllChildren()
  {
      for (int i = 0; i < children.size(); i++)
      {
        NodeImpl oldChild = (NodeImpl)children.item(i);
        /* Set the parent to null. */
        oldChild.parentNode = null;
        oldChild.ownerDocument = null;
      }
     children.clear();
  }

}
