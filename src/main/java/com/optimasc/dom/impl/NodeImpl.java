package com.optimasc.dom.impl;

import java.util.HashMap;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.UserDataHandler;

/**
 * Basic node implementation.
 * 
 * The following methods from DOM 2 Core specification are
 * supported:
 * 
 * <ul>
 *   <li>{@link #appendChild(Node)}}</li>
 *   <li>NOT DONE: {@link #cloneNode(boolean)}</li> 
 *   <li>{@link #getLocalName()}</li> 
 *   <li>{@link #getNamespaceURI()}</li>
 *   <li>{@link #getOwnerDocument()}</li> 
 *   <li>{@link #getParentNode()}</li> 
 *   <li>NOT DONE: {@link #insertBefore(Node, Node)}</li>
 *   <li>{@link #removeChild(Node)}</li> 
 * </ul>
 *
 * The following methods from DOM 3 Core specification are
 * supported:
 * <ul>
 *   <li>{@link #getFeature(String, String)}</li>
 *   <li>NOT DONE: {@link #getTextContent()}</li>
 *   <li>{@link #getUserData(String)}</li> 
 *   <li>NOT DONE: {@link #setTextContent(String)}</li>
 *   <li>{@link #setUserData(String, Object, UserDataHandler)}</li> 
 * </ul>
 *
 * @author Carl Eric Codere
 *
 */
public abstract class NodeImpl implements Node, Cloneable
{
  protected String nodeName;
  protected Node parentNode;
  protected String localName;
  protected String prefixName;
  protected String namespaceURI;
  protected Document ownerDocument;
  protected boolean readOnly;
  protected HashMap/*<String, Object>*/ userData;
  protected NodeListImpl children;
  protected short nodeType;
  
  protected static final DOMException UNSUPPORTED_OPERATION = new DOMException(DOMException.NOT_SUPPORTED_ERR,"Unsupported operation.");

  
  

  
  public NodeImpl(Document owner, String name, short nodeType)
  {
    parentNode = null;
    nodeName = name;
    userData = new HashMap/*<String, Object>*/(2);
    this.nodeType = nodeType;
    ownerDocument = owner;
    namespaceURI = null;
    localName = null;
    prefixName = null;
    children = new NodeListImpl();
  }

  
  /** Creates a generic node.
   * 
   * @param parent
   * @param namespaceURI
   * @param qualifiedName
   * @param nodeType
   * @throws DOMException with {@link DOMException#INVALID_CHARACTER_ERR} code
   *  if the qualified name contains invalid characters in NCName 
   *   
   */
  public NodeImpl(Document owner, String namespaceURI, String qualifiedName, short nodeType)
  {
    ownerDocument = owner;
    nodeName = qualifiedName;
    this.nodeType = nodeType;
    this.namespaceURI = namespaceURI;
    prefixName = DOMUtilities.getPrefixFromPath(qualifiedName);
    localName = DOMUtilities.getLocalNameFromPath(qualifiedName);
    userData = new HashMap/*<String, Object>*/(2);
  }

  
  public Node appendChild(Node newChild) throws DOMException
  {
     return insertBefore(newChild,null);
  }

  
  public Node cloneNode(boolean deep)
  {
    try
    {
      NodeImpl clonedNode = (NodeImpl) clone();
      if (deep)
      {
        for (int i=0; i < children.getLength(); i++)
        {
          NodeImpl childNode = (NodeImpl) children.item(i);
          clonedNode.appendChild(childNode.cloneNode(true));
        }
      }
      return clonedNode;
    } catch (CloneNotSupportedException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  
  public short compareDocumentPosition(Node other) throws DOMException
  {
    throw UNSUPPORTED_OPERATION;
  }


  
  public boolean equals(Object obj)
  {
    if (obj == this)
      return true;
    if ((obj instanceof Node) == false)
      return false;
    Node nodeObject = (Node) obj;
    return isEqualNode(nodeObject);
  }

  
  public NamedNodeMap getAttributes()
  {
    return null;
  }

  
  public String getBaseURI()
  {
    throw UNSUPPORTED_OPERATION;
  }

  
  public NodeList getChildNodes()
  {
    return children;
  }

  
  public Object getFeature(String feature, String version)
  {
    return getOwnerDocument().getFeature(feature, version);
  }

  
  public Node getFirstChild()
  {
    if (children.size()==0)
    {
      return null;
    }
    return (Node) children.get(0);
  }

  
  public Node getLastChild()
  {
    if (children.size()==0)
    {
      return null;
    }
    return (Node) children.get(children.getLength()-1);
  }

  
  public String getLocalName()
  {
    return localName;
  }

  
  public String getNamespaceURI()
  {
    return namespaceURI;
  }

  
  public Node getNextSibling()
  {
    // Use the parent children NodeList to determine current position
    if (parentNode != null)
    {
      NodeListImpl list = (NodeListImpl) parentNode.getChildNodes();
      int nextIndex = list.indexOf(this);
      if (nextIndex == -1)
        return null;
      // Point to next child
      nextIndex++;
      if (nextIndex >= list.getLength())
      {
        return null;
      }
      return list.item(nextIndex);
    }
    return null;
  }

  
  public String getNodeName()
  {
    return nodeName;
  }

  
  public short getNodeType()
  {
    return nodeType;
  }

  
  public Document getOwnerDocument()
  {
    return ownerDocument;
  }

  
  public Node getParentNode()
  {
    return parentNode;
  }

  
  public String getPrefix()
  {
    return prefixName;
  }

  
  public Node getPreviousSibling()
  {
    // Use the parent children NodeList to determine current position
    if (parentNode != null)
    {
      NodeListImpl list = (NodeListImpl) parentNode.getChildNodes();
      int nextIndex = list.indexOf(this);
      // Point to previous child
      nextIndex--;
      // If value was not found, nextIndex should be -2, if was first
      // child, then value here should be -1.
      if (nextIndex < 0)
        return null;
      return list.item(nextIndex);
    }
    return null;
  }

  
  public String getTextContent() throws DOMException
  {
    throw UNSUPPORTED_OPERATION;
  }

  
  public Object getUserData(String key)
  {
    return userData.get(key);
  }

  
  public boolean hasAttributes()
  {
    return false;
  }

  public boolean hasChildNodes()
  {
    return children.size()>0;
  }

  public Node insertBefore(Node newChild, Node refChild) throws DOMException
  {
    if (readOnly) 
    {
       throw new DOMException(DOMException.NO_MODIFICATION_ALLOWED_ERR, "Node is read-only");
    }
    if (newChild.getOwnerDocument() != ownerDocument) 
    {
        throw new DOMException(DOMException.WRONG_DOCUMENT_ERR,"newChild is not owned by this document.");
    }
    if (((NodeImpl)newChild.getParentNode()).readOnly) 
    {
      throw new DOMException(DOMException.NO_MODIFICATION_ALLOWED_ERR, "newChild parent is read-only.");
    }
    
  // Check error conditions
  if (newChild == this)
  {
    throw new DOMException(DOMException.HIERARCHY_REQUEST_ERR,"Trying to append Node to itself.");
  }
  
  if (children.contains(newChild))
  {
    children.remove(newChild);
  }
  
  // Append at end of list
  if (refChild == null)
  {
    children.add(newChild);
  } else
  {
    int childIndex = children.indexOf(refChild);
    if (childIndex == -1)
    {
      throw new DOMException(DOMException.NOT_FOUND_ERR,"Trying to insert Node before refChild that is not a child of this node.");
    }
     children.add(childIndex, newChild);
  }
   ((NodeImpl)newChild).parentNode = this;
   return newChild;
  }

  
  public boolean isDefaultNamespace(String namespaceURI)
  {
    throw UNSUPPORTED_OPERATION;
  }

  
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

  
  public boolean isSameNode(Node other)
  {
    // we do not use any wrapper so the answer is obvious
    return this == other;
  }

  
  public boolean isSupported(String feature, String version)
  {
    return getOwnerDocument().isSupported(feature, version);
  }

  
  public String lookupNamespaceURI(String prefix)
  {
    throw UNSUPPORTED_OPERATION;
  }

  
  public String lookupPrefix(String namespaceURI)
  {
    throw UNSUPPORTED_OPERATION;
  }

  
  public void normalize()
  {
    throw UNSUPPORTED_OPERATION;
  }

  
  public Node removeChild(Node oldChild) throws DOMException
  {
    if (readOnly) 
    {
       throw new DOMException(DOMException.NO_MODIFICATION_ALLOWED_ERR, "Node is read-only");
    }
    if (children.contains(oldChild)==false) 
    {
       throw new DOMException(DOMException.NOT_FOUND_ERR, "Node to remove was not found.");
    }
    children.remove(oldChild);
    /* Set the parent to null. */
    ((NodeImpl)oldChild).parentNode = null;
    /* Set the parent to null. */
    ((NodeImpl)oldChild).ownerDocument = null;
    return oldChild;
  }

  
  public Node replaceChild(Node newChild, Node oldChild) throws DOMException
  {
    if (readOnly) 
    {
       throw new DOMException(DOMException.NO_MODIFICATION_ALLOWED_ERR, "Node is read-only");
    }
    if (newChild.getOwnerDocument() != ownerDocument) 
    {
        throw new DOMException(DOMException.WRONG_DOCUMENT_ERR,"newChild is not owned by this document.");
    }
    
    // Check if oldChild exists or not
    int childIndex = children.indexOf(oldChild);
    if (childIndex == -1)
    {
      throw new DOMException(DOMException.NOT_FOUND_ERR,"oldChild is not a child of this Node.");
    }
    ((NodeImpl)oldChild).parentNode  =null;
    ((NodeImpl)oldChild).ownerDocument  = null;

     children.set(childIndex, newChild);
     ((NodeImpl)newChild).parentNode  =this;
     return oldChild;
  }

  

  
  public void setPrefix(String prefix) throws DOMException
  {
    prefixName = prefix;
  }

  
  public void setTextContent(String textContent) throws DOMException
  {
    throw UNSUPPORTED_OPERATION;
  }

  public Object setUserData(String key, Object data, UserDataHandler handler)
  {
    if (handler != null)
    {
      throw new DOMException(DOMException.NOT_SUPPORTED_ERR,"setUserData with a non-null handler is not supported.");
    }
    if (data == null)
    {
      return userData.remove(key);
    } else
    {
      return userData.put(key, data);
    }
  }

  
  /** NON-DOM method for debugging convenience. */
  public String toString()
  {
    return "[" + getNodeName() + ": " + getNodeValue() + "]";
  }


  public Object clone() throws CloneNotSupportedException
  {
    NodeImpl clonedNode =  (NodeImpl) super.clone();
    clonedNode.userData.clear();
    clonedNode.children.clear();
    clonedNode.parentNode = null;
    return clonedNode;
  }


  public String getNodeValue()
  {
    return null;
  }


  public void setNodeValue(String arg0)
  {
    // Ignored
  }
  
  

}
