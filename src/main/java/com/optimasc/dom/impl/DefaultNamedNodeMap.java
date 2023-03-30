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
public class DefaultNamedNodeMap extends Hashtable<String, Node> implements NamedNodeMap
{
  /** Array values cache for item() API call. */
  Node nodes[];


  @Override
  public Node getNamedItem(String name)
  {
    return get(name);
  }

  @Override
  public Node setNamedItem(Node node) throws DOMException
  {
    Node result = put(node.getNodeName(),node);
    nodes = values().toArray(new NodeImpl[0]);
    return result;
  }

  @Override
  public Node removeNamedItem(String name) throws DOMException
  {
    Node result = remove(name);;
    nodes = values().toArray(new NodeImpl[0]);
    return result;
  }

  @Override
  public Node item(int index)
  {
    if (nodes == null)
    {
      nodes = values().toArray(new NodeImpl[0]);
    }
    return nodes[index];
  }

  @Override
  public int getLength()
  {
    return size();
  }

  @Override
  public Node getNamedItemNS(String namespaceURI, String localName)
  {
    return get(namespaceURI+localName);
  }

  @Override
  public Node setNamedItemNS(Node node) throws DOMException
  {
    Node result = put(node.getNamespaceURI()+node.getLocalName(),node);
    nodes = values().toArray(new NodeImpl[0]);
    return result;
  }

  @Override
  public Node removeNamedItemNS(String namespaceURI, String localName) throws DOMException
  {
    Node result = remove(namespaceURI+localName);;
    nodes = values().toArray(new NodeImpl[0]);
    return result;
  }

}
