package com.optimasc.dom.impl;

import java.util.HashMap;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.UserDataHandler;

/**
 * Basic node implementation with the following constraints: <ul>
 * <li>
 *  Does not support children nodes.
 * </li>
 * <li>
 * Does not support node values
 * </li>
 * <li>
 * Does not support attributes
 * </li>
 * </ul>
 *
 *
 * @author Carl Eric Codere
 *
 */
public class NodeImpl implements Node
{
  protected String nodeName;
  protected String nodeValue;
  protected Node parentNode;
  protected String localName;
  protected String prefixName;
  protected String namespaceURI;
  protected Document ownerDocument;
  protected HashMap<String, Object> userData;
  protected NodeListImpl children;
  protected short nodeType;

  public NodeImpl(Document owner, String name, short nodeType)
  {
    parentNode = null;
    nodeName = name;
    userData = new HashMap<String, Object>(2);
    nodeValue = null;
    this.nodeType = nodeType;
    ownerDocument = owner;
    children = new NodeListImpl();
  }

  public NodeImpl(Node parent, String namespaceURI, String qualifiedName, short nodeType)
  {
    parentNode = parent;
    nodeName = qualifiedName;
    nodeValue = null;
    this.nodeType = nodeType;
    this.namespaceURI = namespaceURI;
    prefixName = getPrefixFromPath(qualifiedName);
    localName = getLocalNameFromPath(qualifiedName);
    userData = new HashMap<String, Object>(2);
  }

  @Override
  public String getNodeName()
  {
    return nodeName;
  }

  @Override
  public String getNodeValue() throws DOMException
  {
    return nodeValue;
  }

  @Override
  public void setNodeValue(String nodeValue) throws DOMException
  {
    this.nodeValue = nodeValue;
  }

  @Override
  public short getNodeType()
  {
    return nodeType;
  }

  @Override
  public Node getParentNode()
  {
    return parentNode;
  }


  @Override
  public NodeList getChildNodes()
  {
    return children;
  }

  @Override
  public Node getFirstChild()
  {
    if (children.size()==0)
    {
      return null;
    }
    return children.get(0);
  }

  @Override
  public Node getLastChild()
  {
    if (children.size()==0)
    {
      return null;
    }
    return children.get(children.getLength()-1);
  }

  @Override
  public Node getPreviousSibling()
  {
    throw new DOMException(DOMException.NOT_SUPPORTED_ERR,"siblings not supported.");
  }

  @Override
  public Node getNextSibling()
  {
    throw new DOMException(DOMException.NOT_SUPPORTED_ERR,"siblings not supported.");
  }

  @Override
  public NamedNodeMap getAttributes()
  {
    return null;
  }

  @Override
  public Document getOwnerDocument()
  {
    return ownerDocument;
  }

  @Override
  public Node insertBefore(Node newChild, Node refChild) throws DOMException
  {
    throw new DOMException(DOMException.HIERARCHY_REQUEST_ERR, "No children support.");
  }

  @Override
  public Node replaceChild(Node newChild, Node oldChild) throws DOMException
  {
    throw new DOMException(DOMException.HIERARCHY_REQUEST_ERR, "No children support.");
  }

  @Override
  public Node removeChild(Node oldChild) throws DOMException
  {
    children.remove(oldChild);
    /* Set the parent to null. */
    ((NodeImpl)oldChild).parentNode = null;
    /* Set the parent to null. */
    ((NodeImpl)oldChild).ownerDocument = null;
    return oldChild;
  }

  @Override
  public Node appendChild(Node newChild) throws DOMException
  {
    children.add(newChild);
    /* Set the parent to null. */
    ((NodeImpl)newChild).parentNode = this;
    return newChild;
  }

  @Override
  public boolean hasChildNodes()
  {
    return children.size()>0;
  }

  @Override
  public Node cloneNode(boolean deep)
  {
    throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Unsupported operation.");
  }

  @Override
  public void normalize()
  {
  }

  @Override
  public boolean isSupported(String feature, String version)
  {
    return getOwnerDocument().isSupported(feature, version);
  }

  @Override
  public String getNamespaceURI()
  {
    return namespaceURI;
  }

  @Override
  public String getPrefix()
  {
    return prefixName;
  }

  @Override
  public void setPrefix(String prefix) throws DOMException
  {
    prefixName = prefix;
  }

  @Override
  public String getLocalName()
  {
    return localName;
  }

  @Override
  public boolean hasAttributes()
  {
    return false;
  }

  /**
   * Return the local name from an XML Namespace qualified name.
   *
   * @param qualifiedName
   * @return The local part of the qualified name.
   */
  public static String getLocalNameFromPath(String qualifiedName)
  {
    String s;
    int index = qualifiedName.indexOf(':');
    if (index >= 0)
    {
      s = qualifiedName.substring(index + 1);
    } else
    {
      s = qualifiedName;
    }
    /* Check if it ends with an array element */
    index = s.lastIndexOf("[");
    if (index != -1)
      s = s.substring(0, index);
    return s;
  }

  public static String getPrefixFromPath(String qualifiedName)
  {
    int index = qualifiedName.indexOf(':');
    if (index >= 0)
    {
      return qualifiedName.substring(0, index);
    }
    return null;
  }

  @Override
  public short compareDocumentPosition(Node other) throws DOMException
  {
    throw new DOMException(DOMException.NOT_FOUND_ERR, "Unsupported feature");
  }

  @Override
  public String getBaseURI()
  {
    throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Unsupported operation.");
  }

  @Override
  public Object getFeature(String feature, String version)
  {
    return getOwnerDocument().getFeature(feature, version);
  }

  @Override
  public String getTextContent() throws DOMException
  {
    throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Unsupported operation.");
  }

  @Override
  public Object getUserData(String key)
  {
    return userData.get(key);
  }

  @Override
  public boolean isDefaultNamespace(String namespaceURI)
  {
    throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Unsupported operation.");
  }

  @Override
  public boolean isEqualNode(Node arg)
  {
    int length;
    if (arg == null)
      return false;
    if (arg == this)
    {
      return true;
    }
    if (arg.getNodeType() != getNodeType())
    {
      return false;
    }
    // in theory nodeName can't be null but better be careful
    if (getNodeName() == null)
    {
      // who knows what other implementations may be doing?...
      if (arg.getNodeName() != null)
      {
        return false;
      }
    }
    else if (!getNodeName().equals(arg.getNodeName()))
    {
      return false;
    }

    if (getLocalName() == null)
    {
      if (arg.getLocalName() != null)
      {
        return false;
      }
    }
    else if (!getLocalName().equals(arg.getLocalName()))
    {
      return false;
    }

    if (getNamespaceURI() == null)
    {
      if (arg.getNamespaceURI() != null)
      {
        return false;
      }
    }
    else if (!getNamespaceURI().equals(arg.getNamespaceURI()))
    {
      return false;
    }

    if (getPrefix() == null)
    {
      if (arg.getPrefix() != null)
      {
        return false;
      }
    }
    else if (!getPrefix().equals(arg.getPrefix()))
    {
      return false;
    }

    if (getNodeValue() == null)
    {
      if (arg.getNodeValue() != null)
      {
        return false;
      }
    }
    else if (!getNodeValue().equals(arg.getNodeValue()))
    {
      return false;
    }

    NamedNodeMap original = arg.getAttributes();
    NamedNodeMap compared = getAttributes();
    if (original != compared)
    {
      if (compared.getLength() != original.getLength())
        return false;
      length = original.getLength();
      for (int i = 0; i < length; i++)
      {
        if (compared.getNamedItemNS(original.item(i).getNamespaceURI(), original.item(i)
            .getLocalName()) == null)
          return false;
      }
    }
    NodeList listOrg = arg.getChildNodes();
    NodeList listCompared = getChildNodes();
    if (listOrg != listCompared)
    {
    if (listOrg.getLength() != listCompared.getLength())
      return false;
    length = listOrg.getLength();
    //TODO : This is not yet fully implemented here, as it would take too much time to implement.
    /*      for (int i = 0; i < length; i++)
          {
            if (listC.isEqualNode(listOrg.item(i))==null)
              return false;
          }*/
    }

    return true;
  }

  @Override
  public boolean isSameNode(Node other)
  {
    // we do not use any wrapper so the answer is obvious
    return this == other;
  }

  @Override
  public String lookupNamespaceURI(String prefix)
  {
    throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Unsupported operation.");
  }

  @Override
  public String lookupPrefix(String namespaceURI)
  {
    throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Unsupported operation.");
  }

  @Override
  public void setTextContent(String textContent) throws DOMException
  {
    throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Unsupported operation.");
  }

  @Override
  public Object setUserData(String key, Object data, UserDataHandler handler)
  {
    return userData.put(key, data);
  }

  /** NON-DOM method for debugging convenience. */
  public String toString()
  {
    return "[" + getNodeName() + ": " + getNodeValue() + "]";
  }

  @Override
  public boolean equals(Object obj)
  {
    if (obj == this)
      return true;
    if ((obj instanceof Node) == false)
      return false;
    Node nodeObject = (Node) obj;
    return isEqualNode(nodeObject);
  }

}
