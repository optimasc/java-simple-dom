package com.optimasc.dom.impl;

import java.util.Hashtable;

import org.w3c.dom.DOMException;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/** Implementation of the <code>org.w3c.dom.NamedNodeMap</code> DOM Level 2 and 3 Core specification interface.
 *
 *  Internally the data is stored as a LinkedHashMap where the
 *  key represents the expanded path with the URI and localName and
 *  the object the actual node.
 *
 * @author Carl Eric Codere
 *
 */
public class DefaultNamedNodeMap extends Hashtable implements NamedNodeMap
{
  /** Array values cache for item() API call. */
  Node nodes[];


  public Node getNamedItem(String name)
  {
    return (Node)get(name);
  }

  public Node setNamedItem(Node node) throws DOMException
  {
    Node result = (Node)put(node.getNodeName(),node);
    nodes = (Node[]) values().toArray(new NodeImpl[0]);
    return result;
  }

  public Node removeNamedItem(String name) throws DOMException
  {
    Node result = (Node)remove(name);;
    nodes = (Node[]) values().toArray(new NodeImpl[0]);
    return result;
  }

  public Node item(int index)
  {
    if (nodes == null)
    {
      nodes = (Node[]) values().toArray(new NodeImpl[0]);
    }
    return nodes[index];
  }

  public int getLength()
  {
    return size();
  }

  public Node getNamedItemNS(String namespaceURI, String localName)
  {
    return (Node)get(namespaceURI+localName);
  }

  public Node setNamedItemNS(Node node) throws DOMException
  {
    Node result = (Node) put(node.getNamespaceURI()+node.getLocalName(),node);
    nodes = (Node[]) values().toArray(new NodeImpl[0]);
    return result;
  }

  public Node removeNamedItemNS(String namespaceURI, String localName) throws DOMException
  {
    Node result = (Node)remove(namespaceURI+localName);;
    nodes = (Node[]) values().toArray(new NodeImpl[0]);
    return result;
  }

}
