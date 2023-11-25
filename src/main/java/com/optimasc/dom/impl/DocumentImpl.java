package com.optimasc.dom.impl;

import java.util.Hashtable;

import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.EntityReference;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;

/** Partial Implementation of Document node interface. The following API's are implemented 
 *  and are valid on Document nodes:
 * 
 * <table>
  <tr>
    <th>Method</th>
    <th>Interface</th>
    <th>W3C DOM</th>
    <th>JSR 280</th>
    <th>JSR 287</th>
    <th>Implemented</th>
  </tr>
  <tr>
    <td>{@link org.w3c.dom.Document#adoptNode(Node)}</td>
    <td>Document</td>
    <td>DOM 3</td>
    <td align="center">TRUE</td>
    <td align="center">TRUE</td>
    <td align="center">TRUE</td>
  </tr>
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
    <td>{@link org.w3c.dom.Document#createAttributeNS(String, String)}</td>
    <td>Document</td>
    <td>DOM 2</td>
    <td align="center">TRUE</td>
    <td align="center">FALSE</td>
    <td align="center">TRUE</td>
  </tr>
  <tr>
    <td>{@link org.w3c.dom.Document#createElementNS(String, String)}</td>
    <td>Document</td>
    <td>DOM 2</td>
    <td align="center">TRUE</td>
    <td align="center">TRUE</td>
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
    <td>{@link org.w3c.dom.Document#getDocumentElement()}</td>
    <td>Document</td>
    <td>DOM 1</td>
    <td align="center">TRUE</td>
    <td align="center">TRUE</td>
    <td align="center">TRUE</td>
  </tr>
  <tr>
    <td>{@link org.w3c.dom.Document#getDocumentURI()}</td>
    <td>Document</td>
    <td>DOM 3</td>
    <td align="center">FALSE</td>
    <td align="center">FALSE</td>
    <td align="center">TRUE</td>
  </tr>
  <tr>
    <td>{@link org.w3c.dom.Document#getElementById(String)}</td>
    <td>Document</td>
    <td>DOM 2</td>
    <td align="center">TRUE</td>
    <td align="center">TRUE</td>
    <td align="center">TRUE</td>
  </tr>
  <tr>
    <td>{@link org.w3c.dom.Document#getElementsByTagName(String)}</td>
    <td>Document</td>
    <td>DOM 1</td>
    <td align="center">TRUE</td>
    <td align="center">FALSE</td>
    <td align="center">TRUE</td>
  </tr>
  <tr>
    <td>{@link org.w3c.dom.Document#getElementsByTagNameNS(String, localName)}</td>
    <td>Document</td>
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
    <td>{@link org.w3c.dom.Document#getStrictErrorChecking()}</td>
    <td>Document</td>
    <td>DOM 3</td>
    <td align="center">FALSE</td>
    <td align="center">FALSE</td>
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
    <td>{@link org.w3c.dom.Node#hasChildNodes()}</td>
    <td>Node</td>
    <td>DOM 1</td>
    <td align="center">TRUE</td>
    <td align="center">FALSE</td>
    <td align="center">TRUE</td>
  </tr>
  <tr>
    <td>{@link org.w3c.dom.Document#importNode(Node, boolean)}</td>
    <td>Document</td>
    <td>DOM 2</td>
    <td align="center">TRUE</td>
    <td align="center">TRUE</td>
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
    <td>{@link org.w3c.dom.Document#setDocumentURI(String)}</td>
    <td>Document</td>
    <td>DOM 3</td>
    <td align="center">FALSE</td>
    <td align="center">FALSE</td>
    <td align="center">TRUE</td>
  </tr>
</table>

 * 
 * @author carl
 *
 */
public class DocumentImpl extends NodeImpl implements Document
{
  protected String       documentURI;
  protected DocumentType documentType;
  protected Element      rootElement;
  protected Hashtable/*<String,Object>*/ featureObjects;

  public DocumentImpl()
  {
    super(null, "#document", Node.DOCUMENT_NODE);
    featureObjects = new Hashtable/*<String,Object>*/(4);
  }
  
  private Node importNodeInternal(Node source, boolean deep)
  throws DOMException 
  {
   Node newnode=null;

  if(source instanceof NodeImpl)
  {
      int type = source.getNodeType();
      switch (type) {
          case ELEMENT_NODE: 
          {
              Element newElement;
              newElement = createElementNS(source.getNamespaceURI(),source.getNodeName());

              // Copy element's attributes, if any.
              NamedNodeMap sourceAttrs = source.getAttributes();
              if (sourceAttrs != null) 
              {
                  int length = sourceAttrs.getLength();
                  for (int index = 0; index < length; index++) 
                  {
                      Attr attr = (Attr)sourceAttrs.item(index);

                      if (attr.getSpecified()) 
                      {
                          Attr newAttr = (Attr)importNode(attr, true);
                          newElement.setAttributeNodeNS(newAttr);
                      }
                  }
              }

              newnode = newElement;
              break;
          }

          case ATTRIBUTE_NODE: 
          {
              newnode = createAttributeNS(source.getNamespaceURI(),source.getNodeName());
              Attr attr = (Attr) source;
              Attr newattr = (Attr) newnode;
              newattr.setValue(attr.getValue());
              deep = false;
              break;
          }

          case TEXT_NODE: 
          {
              newnode = createTextNode(source.getNodeValue());
              break;
          }

          case PROCESSING_INSTRUCTION_NODE: 
          {
              newnode = createProcessingInstruction(source.getNodeName(),
              source.getNodeValue());
              break;
          }

          case COMMENT_NODE: 
          {
              newnode = createComment(source.getNodeValue());
              break;
          }

          // Cannot import document type nodes
          case DOCUMENT_TYPE_NODE: 
          case DOCUMENT_NODE : // Can't import document nodes
          default: {           // Unknown node type
              throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "importNode on this node type is not supported :"+nodeType);
          }
      }

      // If deep, replicate and attach the kids.
      if (deep) 
      {
          NodeList children = source.getChildNodes();
          for (int i=0; i < children.getLength(); i++)
          {
            newnode.appendChild(importNode(children.item(i), true));
          }
      }
      return newnode;
  }
    return null;    
  } 
  
  
  protected Node adoptNode(Node source, boolean deep)  throws DOMException
  {
    NodeImpl node;
    try {
        node = (NodeImpl) source;
    } catch (ClassCastException e) {
        return null;
    }
    
    
    // Return null if the source is null
    if (source == null) {
        return null;
    } 
    else if (source != null && source.getOwnerDocument() != null) 
    {
        DOMImplementation thisImpl = this.getImplementation();
        DOMImplementation otherImpl = source.getOwnerDocument().getImplementation();

        // when the source node comes from a different implementation.
        if (thisImpl != otherImpl) 
        {
            // Adopting between two different implementations is not possible
            return null;  
        }
    }
    
    
    if (node.readOnly)
    {
      throw new DOMException(DOMException.NO_MODIFICATION_ALLOWED_ERR,"Node is read-only.");
    }
    
    
    switch (node.getNodeType()) 
    {
        case ATTRIBUTE_NODE: 
        {
            AttrImpl attr = (AttrImpl) node;
            attr.ownerElement = null;
            attr.isSpecified = true;
            attr.ownerDocument = this;
            break;
        }
        case DOCUMENT_NODE:
        case DOCUMENT_TYPE_NODE: 
        {
            throw new DOMException(DOMException.NOT_SUPPORTED_ERR, "Document and DocumentType nodes cannot be adopted.");
        }
        // ELEMENT Nodes
        default: 
        {
          // Remove this element from the tree.
            Node parent = node.getParentNode();
            if (parent != null) 
            {
                parent.removeChild(source);
            }
            node.ownerDocument = this;
            // Recursively set new owner of the children of this node
            DOMUtilities.setOwnerDocument(node,this);
            // Only keep specified attributes, all FIXED attributes are removed
            NamedNodeMap namedNodeList = node.getAttributes();
            // Finished the operation
            if (namedNodeList == null)
               break;
            // Indicate indicates that will need to be removed.
            Attr toRemove[] = new Attr[namedNodeList.getLength()];
            for (int i=0; i < toRemove.length; i++)
            {
              Attr attr = (Attr) namedNodeList.item(i);
              if (attr.getSpecified()==false)
              {
                toRemove[i] = attr;
              }
            }
            for (int i=0; i < toRemove.length; i++)
            {
              if (toRemove[i]!=null)
              {
                ((Element)node).removeAttributeNode(toRemove[i]);
              }
            }
            toRemove = null;
            break;
        }
    }

    return node;
  }

  public Node adoptNode(Node source) throws DOMException
  {
    return adoptNode(source,true);
  }

  public Attr createAttribute(String name) throws DOMException
  {
    throw UNSUPPORTED_OPERATION;
  }

  public Attr createAttributeNS(String namespaceURI, String qualifiedName)  throws DOMException
  {
    if (DOMUtilities.isValidName(qualifiedName)==false)
    {
      throw new DOMException(DOMException.INVALID_CHARACTER_ERR,"Qualified name contains invalid characters: '"+qualifiedName+"'");
    }
    DOMUtilities.validateQualifiedName(namespaceURI, qualifiedName);
    return new AttrImpl(this, namespaceURI, qualifiedName);
  }

  public CDATASection createCDATASection(String data) throws DOMException
  {
    throw UNSUPPORTED_OPERATION;
  }

  public Comment createComment(String data)
  {
    throw UNSUPPORTED_OPERATION;
  }

  public DocumentFragment createDocumentFragment()
  {
    throw UNSUPPORTED_OPERATION;
  }

  public Element createElement(String tagName) throws DOMException
  {
    throw UNSUPPORTED_OPERATION;
  }

  public Element createElementNS(String namespaceURI, String qualifiedName) throws DOMException
  {
    if (DOMUtilities.isValidName(qualifiedName)==false)
    {
      throw new DOMException(DOMException.INVALID_CHARACTER_ERR,"Qualified name contains invalid characters: '"+qualifiedName+"'");
    }
    DOMUtilities.validateQualifiedName(namespaceURI, qualifiedName);
    return new ElementNodeImpl(this, namespaceURI,qualifiedName);
  }

  public EntityReference createEntityReference(String name) throws DOMException
  {
    throw UNSUPPORTED_OPERATION;
  }

  public ProcessingInstruction createProcessingInstruction(String target, String data)
      throws DOMException
  {
    throw UNSUPPORTED_OPERATION;
  }

  public Text createTextNode(String data)
  {
    throw UNSUPPORTED_OPERATION;
  }

  public DocumentType getDoctype()
  {
    return documentType;
  }

  public Element getDocumentElement()
  {
    return rootElement;
  }

  public String getDocumentURI()
  {
    return documentURI;
  }

  public Element getElementById(String elementID)
  {
    return DOMUtilities.getElementByID(rootElement,elementID);
  }

  public NodeList getElementsByTagName(String tagName)
  {
    NodeListImpl elements = new NodeListImpl();
    DOMUtilities.getElementsByNameNS(rootElement, null, tagName, elements);
    return elements;
  }

  public NodeList getElementsByTagNameNS(String namespaceURI, String localName)
  {
    NodeListImpl elements = new NodeListImpl();
    DOMUtilities.getElementsByNameNS(rootElement, namespaceURI, localName, elements);
    return elements;
  }

  public DOMImplementation getImplementation()
  {
    throw new DOMException(DOMException.NOT_SUPPORTED_ERR,"call getChildNodes() instead.");
  }

  public Document getOwnerDocument()
  {
    return this;
  }

  public Node getParentNode()
  {
    return null;
  }

  public boolean getStrictErrorChecking()
  {
    return true;
  }

  public boolean hasAttributes()
  {
    return false;
  }


  public Node importNode(Node importedNode, boolean deep) throws DOMException
  {
    return importNodeInternal(importedNode,deep);
  }

  public void normalizeDocument()
  {
    throw UNSUPPORTED_OPERATION;
  }

  public void setDocumentURI(String documentURI)
  {
    this.documentURI = documentURI;
  }

  public void setStrictErrorChecking(boolean strictErrorChecking)
  {
    throw UNSUPPORTED_OPERATION;
  }


  public Node renameNode(Node n, String namespaceURI, String qualifiedName) throws DOMException
  {
    throw UNSUPPORTED_OPERATION;
  }

  public boolean isSupported(String feature, String version)
  {
    return featureObjects.containsKey(feature);
  }

  public Object getFeature(String feature, String version)
  {
    return featureObjects.get(feature);
  }

  public Node appendChild(Node newChild) throws DOMException
  {
    // Check if trying to append a new DocumentType
    if ((newChild instanceof DocumentType) && (documentType!=null))
    {
      throw new DOMException(DOMException.HIERARCHY_REQUEST_ERR,"Trying to append a DocumentType while this document already has a DocumentType");
    }
    
    // Check if trying to append a new DocumentType
    if (newChild instanceof Element)
    {
      if (rootElement!=null)
      {
        throw new DOMException(DOMException.HIERARCHY_REQUEST_ERR,"Trying to append a new root Element while this document already has a root Element");
      }
    }
 
    Node returnNode = super.appendChild(newChild);;
    // Successfully added this node, so assign it.
    if (newChild instanceof Element)
    {
      rootElement = (Element) newChild;
    }
    // Successfully added this node, so assign it.
    if (newChild instanceof DocumentType)
    {
      documentType = (DocumentType) newChild;
    }
    
    return returnNode;
  }

  public void setTextContent(String textContent) throws DOMException
  {
    throw UNSUPPORTED_OPERATION;
  }

  public Node insertBefore(Node newChild, Node refChild) throws DOMException
  {
    if (newChild instanceof DocumentType)
    {
      if (documentType != null)
      {
        throw new DOMException(DOMException.HIERARCHY_REQUEST_ERR,"Trying to insert a DocumentType while it is already defined.");
      }
    }
    if (newChild instanceof Element)
    {
      if (rootElement != null)
      {
        throw new DOMException(DOMException.HIERARCHY_REQUEST_ERR,"Trying to insert an Element while it is already defined.");
      }
    }
    
    return appendChild(newChild);
  }
  
  

}
