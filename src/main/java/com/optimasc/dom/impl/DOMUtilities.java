package com.optimasc.dom.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Utilities to convert to and from the key-value pair data model to the W3C
 * Document object model. This only works if there is only one element of a
 * specified name in the leaf.
 * 
 * @author Carl Eric Codere
 * 
 */
public class DOMUtilities
{
  protected static final int INDEX_BRACKET_EXPR = 1;
  protected static final int INDEX_NAME = 0;

  /**
   * Returns the cleaned value of the element, by removing any bracket
   * information and returns the value in the bracket if any.
   * 
   * @param value
   *          The value that needs to be checked and parsed.
   * @return An array of string with up to 2 elements, the element at index 0 is
   *         the path term without any extra expression, and if there is a
   *         bracket value, it also returns the value within the bracket at
   *         index 1, otherwise a null value is stored at index 1.
   */
  static String[] splitTerm(String value)
  {
    String returnValue[] = new String[2];
    int beginIndex = -1;
    returnValue[INDEX_BRACKET_EXPR] = null;
    /** No brackets */
    if (value.endsWith("]") == false)
    {
      returnValue[INDEX_NAME] = value;
      return returnValue;
    }
    beginIndex = value.lastIndexOf('[');
    /* There is a problem somewhere, as this is not possible! */
    if (beginIndex < 0)
    {
      returnValue[INDEX_NAME] = value;
      return returnValue;
    }
    returnValue[INDEX_BRACKET_EXPR] = value.substring(beginIndex + 1, value.length() - 1);
    returnValue[INDEX_NAME] = value.substring(0, beginIndex);
    return returnValue;
  }

  /**
   * Search if the direct children node of this node is an element node of the
   * specified name, returns the index'th node with the specified name if
   * if index is different than -1. 
   * 
   * 
   * @param node
   *          The node that contains the elements to search for.
   * @param name
   *          The name of the node to search for.
   * @param index
   *          The index of the element, or -1 if there is no index. The array
   *          of elements starts at array index 1, not zero.
   * @return The node or null if no node was found
   */
  static Node findChildElement(Node node, String name, int index)
  {
    if (node == null)
      return null;
    int currentCounter = 0;
    NodeList nodeList = node.getChildNodes();
    for (int i = 0; i < nodeList.getLength(); i++)
    {
      Node n = nodeList.item(i);
      if ((n.getNodeType() == Node.ELEMENT_NODE) || (n.getNodeType() == Node.DOCUMENT_NODE))
      {
        String nodeName = n.getNodeName();
        if (name.equals(nodeName))
        {
          currentCounter++;
          /* Only one element needs to be selected */
          if (index == -1)
          {
            return n;
          }
          /* The value to search for is indexed */
          if (currentCounter == index)
          {
            return n;
          }
        }
      }
    }
    return null;
  }

  /** From a node, return the last existing index of of a child element with
   *  the specified name. This is used to determine if we need to create
   *  nodes with indexed access in XPath
   *   
   * @param node
   * @param name
   * @return 0 no child node found with that name
   */
  static int findLastElementIndex(Node node, String name)
  {
    if (node == null)
      return 0;
    int currentCounter = 0;
    NodeList nodeList = node.getChildNodes();
    for (int i = 0; i < nodeList.getLength(); i++)
    {
      Node n = nodeList.item(i);
      if ((n.getNodeType() == Node.ELEMENT_NODE) || (n.getNodeType() == Node.DOCUMENT_NODE))
      {
        String nodeName = n.getNodeName();
        if (name.equals(nodeName))
        {
          currentCounter++;
        }
      }
    }
    return currentCounter;
  }
  
  /** Returns true if this node has a direct child text node. */
  static boolean hasText(Node node)
  {
    NodeList nodeList = node.getChildNodes();
    for (int i = 0; i < nodeList.getLength(); i++)
    {
      Node n = nodeList.item(i);
      if (n.getNodeType() == Node.TEXT_NODE)
        return true;
    }
    return false;
  }

  /** 
   * Map a list of key-value pairs following a subset of
   * the XPath syntax, to a {@link org.w3c.dom.Document} representation.
   * 
   * Both the keys and and the actual data are converted to strings using the
   * <code>toString()</code> method.
   * 
   * The following characteristics of the XPath syntax must be followed:
   * <ul>
   * <li>Only abbreviated XPath 1.0 syntax is supported</li>
   * <li>The general syntax to create unique elements is follow:
   * rootElement/elem1/elem2 ... where <code>rootElement</code> is the root
   * element of the target document. All elements in the hierarchy shall be
   * created if they do not already exist. An error shall be thrown if the last
   * element of the XPath already exists in the document.</li>
   * <li>The general syntax to create duplicate elements is follow:
   * rootElement/elem1/elem2[index] ... where <code>rootElement</code> is the
   * root element of the target document and <code>index</code> is an integer
   * value indicating the index'th element with the same name to create. All
   * elements in the hierarchy shall be created if they do not already exist.
   * The order of created elements is not necessarily maintained and
   * synchronised with the index integer value.</li>
   * <li>The general syntax to create attributes is follow:
   * rootElement/elem1/@attribute ... where <code>@attribute</code> is the
   * attribute to set for <code>elem1</code>. Duplicate attribute verification
   * is not performed, and attribute with the same name shall overwrite the
   * previous attribute of the same name for that element. Automatic attribute
   * inheritance is <em>not</em> supported.</li>
   * </ul>
   * 
   * @param map
   *          The map of key-value pairs to map to a DOM Document. The key
   *          values should use a subset of the XPath syntax as described above.
   * @return A created DOM Document.
   * @throws IllegalArgumentException
   *           if one of the syntax element is not supported.
   */
  public static Document convertToNode(Map map)
  {

    DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder documentBuilder;
    Document document = null;
    try
    {
      documentBuilder = documentFactory.newDocumentBuilder();
      document = documentBuilder.newDocument();
    } catch (ParserConfigurationException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    String returnValues[] = null;
    Node currentNode = null;
    Node rootNode = document;
    String attributeValue = null;
    int arrayIndex = -1;

    Iterator it = map.keySet().iterator();
    while (it.hasNext())
    {
      String key = it.next().toString();
      String value = map.get(key).toString();
      currentNode = rootNode;
      attributeValue = null;
      String[] pathValuesplit = key.split("/");
      for (int i = 0; i < pathValuesplit.length; i++)
      {
        arrayIndex = -1;
        returnValues = splitTerm(pathValuesplit[i]);
        /* Is there any bracket expression */
        if (returnValues[INDEX_BRACKET_EXPR] != null)
        {
          /* Check if this is an integer value. If not check other things */
          try
          {
            arrayIndex = Integer.parseInt(returnValues[1]);
            if (arrayIndex < 1)
            {
              throw new IllegalArgumentException(
                  "Index element in path expression cannot be 0 or negative.");
            }
          } catch (NumberFormatException e)
          {
            /* Check if this is an attribute value. */
            if (returnValues[INDEX_BRACKET_EXPR].startsWith("@"))
            {
              throw new IllegalArgumentException(
                  "Bracket expression for selecting an attribute are not supported.");
            }
          }
        } /* endif */

        /** Setting an attribute value */
        if (returnValues[INDEX_NAME].startsWith("@"))
        {
          attributeValue = returnValues[INDEX_NAME].substring(1);
          /* The path indicates that an attribute must be set. */
          if (currentNode.getNodeType() == Node.ELEMENT_NODE)
          {
            ((Element) currentNode).setAttribute(attributeValue, value);
          }
        } else
        /** Adding an element */
        {
          /* Check if the element already exists */
          Node foundNode = findChildElement(currentNode, returnValues[INDEX_NAME],arrayIndex);
          
          if ((foundNode != null))
          {
            /* Is this the last element in the path */
            if ((i == (pathValuesplit.length - 1)))
            {
              /* If the value is indexed, then set the context of the text, otherwise
               * error as this is a duplicate element.
               */
              if (arrayIndex != -1)
              {
                foundNode.setTextContent(value);
              } else
              {
                /** If node has a child text node, this is a duplicate, otherwise
                 *  we might just be trying to add some attributes  
                 */
                if (hasText(foundNode)==true)
                {
                  throw new IllegalArgumentException("Last element of path already exists.");
                } else
                {
                  foundNode.setTextContent(value);
                }
              }
            }
            
            currentNode = foundNode;
          } else
          {
            /* Special case, if arrayIndex is valid, and the node is not found,
             * create all nodes before that index, only if not the last element
             * in the path  
             */
            if ((arrayIndex != -1))
            {
              int lowestIndex = findLastElementIndex(currentNode,returnValues[INDEX_NAME]);
              for (int j = lowestIndex; j < arrayIndex-1; j++)
              {
                Element childNode = document.createElement(returnValues[INDEX_NAME]);
                currentNode.appendChild(childNode);
              }
            }
            
            
            Element childNode = document.createElement(returnValues[INDEX_NAME]);
            currentNode.appendChild(childNode);
            currentNode = childNode;
            /**
             * If this is the last element in the path, add the actual #text
             * data is set
             */
            if (i == (pathValuesplit.length - 1))
            {
              childNode.setTextContent(value);
            }
          }
        }

      } /* end for */
    } /* end while */
    return document;
  }
  
  
  
  
  static void addValues(String currentPath, Node node, Map<String,String> map)
  {
    Map<String,Integer> duplicateElements = new HashMap<String,Integer>();
    NodeList nodeList = node.getChildNodes();
    
    for (int i = 0; i < nodeList.getLength(); i++)
    {
      Node n = nodeList.item(i);
      
      if (n.getNodeType()==Node.ELEMENT_NODE)
      {
         Integer index = duplicateElements.get(n.getNodeName());
         if (index == null)
         {
           index = new Integer(1);
           duplicateElements.put(n.getNodeName(), index);
         } else
         {
           index = new Integer(index.intValue()+1);
           duplicateElements.put(n.getNodeName(), index);
         }
         
         String nodeName = n.getNodeName()+"["+index.toString()+"]";
         
         addValues(currentPath+"/"+nodeName,n,map);
         /** Add all attributes */
         NamedNodeMap attributes = n.getAttributes();
         if (attributes != null)
         {
         for (int j = 0; j < attributes.getLength(); j++)
         {
           Node attributeNode = attributes.item(j);
           map.put(currentPath+"/"+nodeName+"/@"+attributeNode.getNodeName(), 
               attributeNode.getNodeValue());
         }
         }
      }
      else
      if (n.getNodeType()==Node.TEXT_NODE)
      {
         map.put(currentPath, n.getNodeValue());
      }
    }
  }
  
  /** Converts a {@link org.w3c.dom.Document} to a key-value pair
   *  representation. The output representation is a subset of
   *  the XPath syntax. Only Elements and attributes are actually
   *  converted, all other node types are simply ignored.
   *  
   * The following is output:
   * <ul>
   *  <li>The key shall represent an XPath term, with the following
   *    general syntax:
   *    <ul>
   *  <li>Each element, except for the root element will be
   *   indexed according to XPath selection syntax, so 
   *   as to fully represent the location of the element. </li>
   *  <li>Attributes names are represents by a <code>@</code> in the
   *  last XPath element.</li>
   *  </ul>
   *  <li>The value shall represent the #text of an element or an
   *  attribute value.</li>
   * </ul>
   * 
   * @param doc The document to convert.
   * @return A key-value Map 
   */
  public static Map<String,String> convertToMap(Document doc)
  {
    Map<String,String> map = new TreeMap<String,String>();
    String currentPath = "";
    
    Node n = doc.getDocumentElement();
    addValues(currentPath+n.getNodeName(),n, map);
    return map;
  }
  
}
