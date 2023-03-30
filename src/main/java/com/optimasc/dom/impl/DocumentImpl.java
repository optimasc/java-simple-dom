package com.optimasc.dom.impl;

import java.util.Hashtable;

import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.EntityReference;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;

public class DocumentImpl extends NodeImpl implements Document
{
  protected String       documentURI;
  protected Hashtable<String,Object> featureObjects;

  public DocumentImpl(Document owner, String name)
  {
    super(owner, name, Node.DOCUMENT_NODE);
    featureObjects = new Hashtable<String,Object>(4);
  }

  public DocumentImpl(Document owner)
  {
    super(owner, "#document", Node.DOCUMENT_NODE);
    featureObjects = new Hashtable<String,Object>(4);
  }


  @Override
  public Node adoptNode(Node source) throws DOMException
  {
    throw new DOMException(DOMException.NOT_FOUND_ERR,"Unsupported feature");
  }


  @Override
  public Attr createAttribute(String name) throws DOMException
  {
    return new AttrImpl(this, name);
  }

  @Override
  public Attr createAttributeNS(String namespaceURI, String qualifiedName)  throws DOMException
  {
    return new AttrImpl(this, namespaceURI, qualifiedName);
  }



  @Override
  public CDATASection createCDATASection(String data) throws DOMException
  {
    throw new DOMException(DOMException.NOT_FOUND_ERR,"Unsupported feature");
  }

  @Override
  public Comment createComment(String data)
  {
    throw new DOMException(DOMException.NOT_FOUND_ERR,"Unsupported feature");
  }

  @Override
  public DocumentFragment createDocumentFragment()
  {
    throw new DOMException(DOMException.NOT_FOUND_ERR,"Unsupported feature");
  }

  @Override
  public Element createElement(String tagName) throws DOMException
  {
    return new ElementNodeImpl(this, tagName);
  }

  @Override
  public Element createElementNS(String namespaceURI, String qualifiedName) throws DOMException
  {
    throw new DOMException(DOMException.NOT_FOUND_ERR,"Unsupported feature");
  }

  @Override
  public EntityReference createEntityReference(String name) throws DOMException
  {
    throw new DOMException(DOMException.NOT_FOUND_ERR,"Unsupported feature");
  }

  @Override
  public ProcessingInstruction createProcessingInstruction(String target, String data)
      throws DOMException
  {
    throw new DOMException(DOMException.NOT_FOUND_ERR,"Unsupported feature");
  }

  @Override
  public Text createTextNode(String data)
  {
    throw new DOMException(DOMException.NOT_FOUND_ERR,"Unsupported feature");
  }

  @Override
  public DocumentType getDoctype()
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Element getDocumentElement()
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getDocumentURI()
  {
    return documentURI;
  }

  @Override
  public DOMConfiguration getDomConfig()
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Element getElementById(String elementID)
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NodeList getElementsByTagName(String tagName)
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NodeList getElementsByTagNameNS(String namespaceURI, String localName)
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public DOMImplementation getImplementation()
  {
    throw new DOMException(DOMException.NOT_SUPPORTED_ERR,"call getChildNodes() instead.");
  }

  @Override
  public String getInputEncoding()
  {
    return null;
  }

  @Override
  public Document getOwnerDocument()
  {
    return this;
  }

  @Override
  public Node getParentNode()
  {
    return null;
  }

  @Override
  public boolean getStrictErrorChecking()
  {
    return true;
  }

  @Override
  public String getXmlEncoding()
  {
    return null;
  }

  @Override
  public boolean getXmlStandalone()
  {
    return false;
  }

  @Override
  public String getXmlVersion()
  {
    return null;
  }

  @Override
  public boolean hasAttributes()
  {
    return false;
  }


  @Override
  public Node importNode(Node importNode, boolean deep) throws DOMException
  {
    throw new DOMException(DOMException.NOT_FOUND_ERR,"Unsupported feature");
  }

  @Override
  public void normalizeDocument()
  {
    throw new DOMException(DOMException.NOT_FOUND_ERR,"Unsupported feature");
  }

  @Override
  public void setDocumentURI(String documentURI)
  {
    this.documentURI = documentURI;
  }

  @Override
  public void setStrictErrorChecking(boolean strictErrorChecking)
  {
    throw new DOMException(DOMException.NOT_FOUND_ERR,"Unsupported feature");
  }

  @Override
  public void setXmlStandalone(boolean xmlStandAlone) throws DOMException
  {
    throw new DOMException(DOMException.NOT_FOUND_ERR,"Unsupported feature");
  }

  @Override
  public void setXmlVersion(String xmlVersion) throws DOMException
  {
    throw new DOMException(DOMException.NOT_FOUND_ERR,"Unsupported feature");
  }

  @Override
  public Node renameNode(Node n, String namespaceURI, String qualifiedName) throws DOMException
  {
/*    if ((n instanceof Element) || (n instanceof Attr))
    {

    }*/
    throw new DOMException(DOMException.NOT_FOUND_ERR,"Unsupported feature");
  }

  @Override
  public boolean isSupported(String feature, String version)
  {
    return featureObjects.containsKey(feature);
  }

  @Override
  public Object getFeature(String feature, String version)
  {
    return featureObjects.get(feature);
  }

}
