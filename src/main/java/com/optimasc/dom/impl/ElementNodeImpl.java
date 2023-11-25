package com.optimasc.dom.impl;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/** Implements a minimal subset of the W3C DOM Element API. This
 *  Element node only permits to have at most one {@link Text} node 
 *  as a child, as the Text nodes are internally created when
 *  calling {@link #setTextContent(String)}, and it is not 
 *  possible to append Text nodes manually.
 * 
 * <p>The following API's are implemented and are valid for Element nodes:</p>
 * 
 * <table>
 * <tr>
 *  <th>Method name</th>
 *  <th>Interface</th>
 *  <th>DOM Core Level</th>
 *  <th>JSR 280</th>
 *  <th>JSR 287</th>
 *  <th>Implemented</th>
 * </tr>
  <tr>
    <td>{@link org.w3c.dom.Node#appendChild(Node)}</td>
    <td>Node</td>
    <td>DOM 2</td>
    <td align="center">TRUE</td>
    <td align="center">TRUE</td>
    <td align="center">TRUE</td>
  </tr>
  <tr>
    <td>{@link org.w3c.dom.Node#cloneNode(boolean)}</td>
    <td>Node</td>
    <td>DOM 1</td>
    <td align="center">TRUE</td>
    <td align="center">TRUE</td>
    <td align="center">TRUE</td>
  </tr>
  <tr>
    <td>{@link org.w3c.dom.Element#getAttributeNodeNS(String, String)}</td>
    <td>Element</td>
    <td>DOM 2</td>
    <td align="center">TRUE</td>
    <td align="center">FALSE</td>
    <td align="center">TRUE</td>
  </tr>
  <tr>
    <td>{@link org.w3c.dom.Element#getAttributeNS(String, String)}</td>
    <td>Element</td>
    <td>DOM 2</td>
    <td align="center">TRUE</td>
    <td align="center">TRUE</td>
    <td align="center">TRUE</td>
  </tr>
  <tr>
    <td>{@link org.w3c.dom.Node#getAttributes()}</td>
    <td>Node</td>
    <td>DOM 1</td>
    <td align="center">TRUE</td>
    <td align="center">FALSE</td>
    <td align="center">TRUE</td>
  </tr>
  <tr>
    <td>{@link org.w3c.dom.Node#getChildNodes()}</td>
    <td>Node</td>
    <td>DOM 1</td>
    <td align="center">TRUE</td>
    <td align="center">FALSE</td>
    <td align="center">TRUE</td>
  </tr>
  <tr>
    <td>{@link org.w3c.dom.Element#getElementsByTagName(String)}</td>
    <td>Element</td>
    <td>DOM 1</td>
    <td align="center">TRUE</td>
    <td align="center">FALSE</td>
    <td align="center">TRUE</td>
  </tr>
  <tr>
    <td>{@link org.w3c.dom.Element#getElementsByTagNameNS(String, localName)}</td>
    <td>Element</td>
    <td>DOM 2</td>
    <td align="center">TRUE</td>
    <td align="center">FALSE</td>
    <td align="center">TRUE</td>
  </tr>
  <tr>
    <td>{@link org.w3c.dom.Node#getFirstChild()}</td>
    <td>Node</td>
    <td>DOM 1</td>
    <td align="center">TRUE</td>
    <td align="center">FALSE</td>
    <td align="center">TRUE</td>
  </tr>
  <tr>
    <td>{@link org.w3c.dom.Node#getLastChild()}</td>
    <td>Node</td>
    <td>DOM 1</td>
    <td align="center">TRUE</td>
    <td align="center">FALSE</td>
    <td align="center">TRUE</td>
  </tr>
  <tr>
    <td>{@link org.w3c.dom.Node#getLocalName()}</td>
    <td>Node</td>
    <td>DOM 2</td>
    <td align="center">TRUE</td>
    <td align="center">TRUE</td>
    <td align="center">TRUE</td>
  </tr>
  <tr>
    <td>{@link org.w3c.dom.Node#getNamespaceURI()}</td>
    <td>Node</td>
    <td>DOM 2</td>
    <td align="center">TRUE</td>
    <td align="center">TRUE</td>
    <td align="center">TRUE</td>
  </tr>
  <tr>
    <td>{@link org.w3c.dom.Node#getNextSibling()}</td>
    <td>Node</td>
    <td>DOM 1</td>
    <td align="center">TRUE</td>
    <td align="center">FALSE</td>
    <td align="center">TRUE</td>
  </tr>
  <tr>
    <td>{@link org.w3c.dom.Node#getNodeName()}</td>
    <td>Node</td>
    <td>DOM 1</td>
    <td align="center">TRUE</td>
    <td align="center">FALSE</td>
    <td align="center">TRUE</td>
  </tr>
  <tr>
    <td>{@link org.w3c.dom.Node#getNodeType()}</td>
    <td>Node</td>
    <td>DOM 1</td>
    <td align="center">TRUE</td>
    <td align="center">FALSE</td>
    <td align="center">TRUE</td>
  </tr>
  <tr>
    <td>{@link org.w3c.dom.Node#getOwnerDocument()}</td>
    <td>Node</td>
    <td>DOM 2</td>
    <td align="center">TRUE</td>
    <td align="center">TRUE</td>
    <td align="center">TRUE</td>
  </tr>
  <tr>
    <td>{@link org.w3c.dom.Node#getParentNode()}</td>
    <td>Node</td>
    <td>DOM 1</td>
    <td align="center">TRUE</td>
    <td align="center">TRUE</td>
    <td align="center">TRUE</td>
  </tr>
  <tr>
    <td>{@link org.w3c.dom.Node#getPrefix()}</td>
    <td>Node</td>
    <td>DOM 2</td>
    <td align="center">TRUE</td>
    <td align="center">FALSE</td>
    <td align="center">TRUE</td>
  </tr>
  <tr>
    <td>{@link org.w3c.dom.Node#getPreviousSibling()}</td>
    <td>Node</td>
    <td>DOM 1</td>
    <td align="center">TRUE</td>
    <td align="center">FALSE</td>
    <td align="center">TRUE</td>
  </tr>
  <tr>
    <td>{@link org.w3c.dom.Element#getTagName()}</td>
    <td>Element</td>
    <td>DOM 1</td>
    <td align="center">TRUE</td>
    <td align="center">FALSE</td>
    <td align="center">TRUE</td>
  </tr>
  <tr>
    <td>{@link org.w3c.dom.Node#getTextContent()}</td>
    <td>Node</td>
    <td>DOM 3</td>
    <td align="center">TRUE</td>
    <td align="center">TRUE</td>
    <td align="center">TRUE</td>
  </tr>
  <tr>
    <td>{@link org.w3c.dom.Node#getUserData(String)}</td>
    <td>Node</td>
    <td>DOM 3</td>
    <td align="center">TRUE</td>
    <td align="center">FALSE</td>
    <td align="center">TRUE</td>
  </tr>
  <tr>
    <td>{@link org.w3c.dom.Element#hasAttributeNS(String, String)}</td>
    <td>Element</td>
    <td>DOM 2</td>
    <td align="center">TRUE</td>
    <td align="center">FALSE</td>
    <td align="center">TRUE</td>
  </tr>
  <tr>
    <td>{@link org.w3c.dom.Node#hasAttributes()}</td>
    <td>Node</td>
    <td>DOM 2</td>
    <td align="center">TRUE</td>
    <td align="center">FALSE</td>
    <td align="center">TRUE</td>
  </tr>
  <tr>
    <td>{@link org.w3c.dom.Node#hasChildNodes()}</td>
    <td>Node</td>
    <td>DOM 1</td>
    <td align="center">TRUE</td>
    <td align="center">FALSE</td>
    <td align="center">TRUE</td>
  </tr>
  <tr>
    <td>{@link org.w3c.dom.Node#insertBefore(Node, Node)}</td>
    <td>Node</td>
    <td>DOM 2</td>
    <td align="center">TRUE</td>
    <td align="center">TRUE</td>
    <td align="center">TRUE</td>
  </tr>
  <tr>
    <td>{@link org.w3c.dom.Node#isSameNode(Node)}</td>
    <td>Node</td>
    <td>DOM 3</td>
    <td align="center">FALSE</td>
    <td align="center">FALSE</td>
    <td align="center">TRUE</td>
  </tr>
  <tr>
    <td>{@link org.w3c.dom.Element#removeAttributeNode(Attr)}</td>
    <td>Element</td>
    <td>DOM 1</td>
    <td align="center">TRUE</td>
    <td align="center">FALSE</td>
    <td align="center">TRUE</td>
  </tr>
  <tr>
    <td>{@link org.w3c.dom.Element#removeAttributeNS(String, String)}</td>
    <td>Element</td>
    <td>DOM 2</td>
    <td align="center">TRUE</td>
    <td align="center">FALSE</td>
    <td align="center">TRUE</td>
  </tr>
  <tr>
    <td>{@link org.w3c.dom.Node#removeChild(Node)}</td>
    <td>Node</td>
    <td>DOM 2</td>
    <td align="center">TRUE</td>
    <td align="center">TRUE</td>
    <td align="center">TRUE</td>
  </tr>
  <tr>
    <td>{@link org.w3c.dom.Node#replaceChild(Node, Node)}</td>
    <td>Node</td>
    <td>DOM 2</td>
    <td align="center">TRUE</td>
    <td align="center">FALSE</td>
    <td align="center">TRUE</td>
  </tr>
  <tr>
    <td>{@link org.w3c.dom.Element#setAttributeNodeNS(Attr)}</td>
    <td>Element</td>
    <td>DOM 2</td>
    <td align="center">TRUE</td>
    <td align="center">FALSE</td>
    <td align="center">TRUE</td>
  </tr>
  <tr>
    <td>{@link org.w3c.dom.Element#setAttributeNS(String, String, String)}</td>
    <td>Element</td>
    <td>DOM 2</td>
    <td align="center">TRUE</td>
    <td align="center">TRUE</td>
    <td align="center">TRUE</td>
  </tr>
  <tr>
    <td>{@link org.w3c.dom.Element#setIdAttributeNode(Attr, boolean)}</td>
    <td>Element</td>
    <td>DOM 3</td>
    <td align="center">TRUE</td>
    <td align="center">FALSE</td>
    <td align="center">TRUE</td>
  </tr>
  <tr>
    <td>{@link org.w3c.dom.Element#setIdAttributeNS(String, String, boolean)}</td>
    <td>Element</td>
    <td>DOM 3</td>
    <td align="center">TRUE</td>
    <td align="center">FALSE</td>
    <td align="center">TRUE</td>
  </tr>
  <tr>
    <td>{@link org.w3c.dom.Node#setTextContent(String)}</td>
    <td>Node</td>
    <td>DOM 3</td>
    <td align="center">TRUE</td>
    <td align="center">TRUE</td>
    <td align="center">TRUE</td>
  </tr>
</table>

 * 
 * 
 *
 */
public class ElementNodeImpl extends NodeImpl implements Element
{
  protected DefaultNamedNodeMap attributes;

  
  public ElementNodeImpl(Document owner,String namespaceURI, String qualifiedName)
  {
    super(owner,namespaceURI, qualifiedName, Node.ELEMENT_NODE);
    attributes = new DefaultNamedNodeMap();
  }

  
  public String getTagName()
  {
    return nodeName;
  }

  
  public String getAttribute(String name)
  {
    throw UNSUPPORTED_OPERATION;
  }

  
  public void setAttribute(String name, String value) throws DOMException
  {
    throw UNSUPPORTED_OPERATION;
  }

  
  public void removeAttribute(String name) throws DOMException
  {
    throw UNSUPPORTED_OPERATION;
  }

  
  public Attr getAttributeNode(String name)
  {
    throw UNSUPPORTED_OPERATION;
  }

  
  public Attr setAttributeNode(Attr newAttr) throws DOMException
  {
    throw UNSUPPORTED_OPERATION;
  }

  
  public Attr removeAttributeNode(Attr oldAttr) throws DOMException
  {
    AttrImpl attr;
    if (readOnly) 
    {
       throw new DOMException(DOMException.NO_MODIFICATION_ALLOWED_ERR, "Node is read-only");
    }
    if (attributes.contains(oldAttr)==false)
    {
      throw new DOMException(DOMException.NOT_FOUND_ERR, "Attribute is not found.");
    }
    attr = (AttrImpl) attributes.removeNamedItemNS(oldAttr.getNamespaceURI(),oldAttr.getLocalName());
    attr.ownerElement = null;
    return attr;
  }

  
  public NodeList getElementsByTagName(String name)
  {
    NodeListImpl elements = new NodeListImpl();
    DOMUtilities.getElementsByNameNS(this, null, name, elements);
    return elements;
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
    if (readOnly) 
    {
       throw new DOMException(DOMException.NO_MODIFICATION_ALLOWED_ERR, "Node is read-only");
    }
    AttrImpl attr = (AttrImpl) ownerDocument.createAttributeNS(namespaceURI, qualifiedName);
    attr.ownerElement = this;
    attr.setValue(value);
    attributes.setNamedItemNS(attr);
  }

  
  public void removeAttributeNS(String namespaceURI, String localName) throws DOMException
  {
    AttrImpl attr;
    if (readOnly) 
    {
       throw new DOMException(DOMException.NO_MODIFICATION_ALLOWED_ERR, "Node is read-only");
    }
    attr = (AttrImpl) attributes.removeNamedItemNS(namespaceURI,localName);
    if (attr != null)
       attr.ownerElement = null;
  }

  
  public Attr getAttributeNodeNS(String namespaceURI, String localName)
  {
    return (Attr) attributes.getNamedItemNS(namespaceURI, localName);
  }

  
  public Attr setAttributeNodeNS(Attr newAttr) throws DOMException
  {
    AttrImpl attr;
    if (readOnly) 
    {
       throw new DOMException(DOMException.NO_MODIFICATION_ALLOWED_ERR, "Node is read-only");
    }
    if (newAttr.getOwnerDocument()!=ownerDocument)
    {
      throw new DOMException(DOMException.WRONG_DOCUMENT_ERR, "Attribute is not created using this Document instance.");
    }
    attr = (AttrImpl) newAttr;
    if (attr.ownerElement!=null)
    {
      throw new DOMException(DOMException.INUSE_ATTRIBUTE_ERR, "Attribute is already owned by another Element, clone it to add.");
    }
    attr.ownerElement = this;
    return (Attr) attributes.setNamedItemNS(newAttr);
  }

  
  public NodeList getElementsByTagNameNS(String namespaceURI, String localName)
  {
    NodeListImpl elements = new NodeListImpl();
    DOMUtilities.getElementsByNameNS(this, namespaceURI, localName, elements);
    return elements;
  }

  
  public boolean hasAttribute(String name)
  {
    throw UNSUPPORTED_OPERATION;
  }

  
  public boolean hasAttributeNS(String namespaceURI, String localName)
  {
    Node node = attributes.getNamedItemNS(namespaceURI, localName);
    if (node != null)
      return true;
    return false;
  }

  
  public void setIdAttribute(String name, boolean isID) throws DOMException
  {
    throw UNSUPPORTED_OPERATION;
  }

  
  public void setIdAttributeNS(String namespaceURI, String localName, boolean isID) throws DOMException
  {
    if (readOnly)
    {
       throw new DOMException(DOMException.NO_MODIFICATION_ALLOWED_ERR, "Node is read-only");
    }
    
    AttrImpl attr = (AttrImpl)attributes.getNamedItemNS(namespaceURI, localName);
    if (attr != null)
    {
      if (attr.readOnly)
      {
         throw new DOMException(DOMException.NO_MODIFICATION_ALLOWED_ERR, "Attribute is read-only");
      }    
      attr.isID = isID;
    } else
    {
      throw new DOMException(DOMException.NOT_FOUND_ERR,"Attribute not found.");
    }
  }

  public void setIdAttributeNode(Attr attr, boolean isID) throws DOMException
  {
    if (readOnly)
    {
       throw new DOMException(DOMException.NO_MODIFICATION_ALLOWED_ERR, "Node is read-only");
    }
    if (attributes.contains(attr)==false)
    {
      throw new DOMException(DOMException.NOT_FOUND_ERR, "Attribute is not an attribute of this Node");
    }
    ((AttrImpl)attr).isID = isID;  
  }

  protected void removeAllChildren()
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

  
  /** Sets the text content associated with this Element. This
   *  removes all children nodes of this element, and then associates
   *  the specified text to this node.
   *  
   *  <p>Association of the character data to this element is done
   *  by appending a single internal {@link org.w3c.dom.Text} child node
   *  to this element.</p>
   * 
   */
  public void setTextContent(String textContent) throws DOMException
  {
    if (readOnly) 
    {
       throw new DOMException(DOMException.NO_MODIFICATION_ALLOWED_ERR, "Node is read-only");
    }
    removeAllChildren();
    if (textContent != null && textContent.length() != 0)
    {
      appendChild(new TextImpl(ownerDocument,textContent));
    }
  }


  public String getTextContent() throws DOMException
  {
    String v;
    NodeListImpl nodes = new NodeListImpl();
    DOMUtilities.getNodeByType(this, Node.TEXT_NODE, nodes);
    StringBuffer buffer = new StringBuffer(); 
    for (int i=0; i < nodes.getLength(); i++)
    {
      buffer.append(nodes.item(i).getNodeValue());
    }
    v = buffer.toString();
    buffer = null;
    return v;
  }


  public Object clone() throws CloneNotSupportedException
  {
    ElementNodeImpl clonedNode = (ElementNodeImpl) super.clone();
    if (clonedNode.attributes != null)
    {
      clonedNode.attributes.clear();
      clonedNode.readOnly = false;
      // Clone each attribute nodes
      if (attributes != null)
      {
        for (int i=0; i < attributes.getLength(); i++)
        {
          Attr attr = (Attr) attributes.item(i);
          Attr clonedAttr = (Attr) attr.cloneNode(true);
          clonedNode.attributes.setNamedItemNS(clonedAttr);
        }
      }
    }
    return clonedNode;
  }


  public NamedNodeMap getAttributes()
  {
    return attributes;
  }

  
}
