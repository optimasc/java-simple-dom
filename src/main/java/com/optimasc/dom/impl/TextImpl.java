package com.optimasc.dom.impl;

import java.io.UnsupportedEncodingException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/** This Text node implementation is optimized 
 *  to save size in memory, since it uses 
 *  internally UTF-8 encoding to encode the 
 *  data.
 * 
 * @author Carl Eric Codere
 *
 */
public class TextImpl extends NodeImpl implements Text
{
  protected static final String NODE_NAME = "#text";
  protected byte[] characterData;
  protected int stringLength;
  
  public TextImpl(Document owner, String data)
  {
    super(owner,NODE_NAME ,Node.TEXT_NODE);
    readOnly = true;
    try
    {
      characterData = data.getBytes("UTF-8");
    } catch (UnsupportedEncodingException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    stringLength = data.length();
  }

  public String getData() throws DOMException
  {
    try
    {
      return new String(characterData,"UTF-8");
    } catch (UnsupportedEncodingException e)
    {
      return null;
    }
  }

  public void setData(String data) throws DOMException
  {
    throw new DOMException(DOMException.NOT_SUPPORTED_ERR,"setTextContent() should be called to modify Text nodes");
  }

  public int getLength()
  {
    return stringLength;
  }

  public String substringData(int offset, int count) throws DOMException
  {
    // TODO Auto-generated method stub
    return null;
  }

  public void appendData(String arg) throws DOMException
  {
    throw new DOMException(DOMException.NOT_SUPPORTED_ERR,"setTextContent() should be called to modify Text nodes");
  }

  public void insertData(int offset, String arg) throws DOMException
  {
    // TODO Auto-generated method stub
    
  }

  public void deleteData(int offset, int count) throws DOMException
  {
    // TODO Auto-generated method stub
    
  }

  public void replaceData(int offset, int count, String arg) throws DOMException
  {
    // TODO Auto-generated method stub
    
  }

  public Text splitText(int offset) throws DOMException
  {
    // TODO Auto-generated method stub
    return null;
  }

  public Node appendChild(Node newChild) throws DOMException
  {
    throw new DOMException(DOMException.HIERARCHY_REQUEST_ERR,"Cannot adds children to Text nodes.");
  }

  public Node insertBefore(Node newChild, Node refChild) throws DOMException
  {
    throw new DOMException(DOMException.HIERARCHY_REQUEST_ERR,"Cannot adds children to Text nodes.");
  }
  

  public Node replaceChild(Node newChild, Node oldChild) throws DOMException
  {
    throw new DOMException(DOMException.HIERARCHY_REQUEST_ERR,"Cannot adds children to Text nodes.");
  }

  public String getNodeValue() throws DOMException
  {
    try
    {
      return new String(characterData,"UTF-8");
    } catch (UnsupportedEncodingException e)
    {
      return null;
    }
  }

  /** Throws {@link java.lang.UnsupportedOperationException} */
  public boolean isElementContentWhitespace()
  {
    throw new UnsupportedOperationException("Unsupported operation.");
  }

  /** Throws {@link java.lang.UnsupportedOperationException} */
  public String getWholeText()
  {
    throw new UnsupportedOperationException("Unsupported operation.");
  }

  /** Throws {@link java.lang.UnsupportedOperationException} */
  public Text replaceWholeText(String content) throws DOMException
  {
    throw new UnsupportedOperationException("Unsupported operation.");
  }

}
