package com.optimasc.dom.impl;

import java.util.Collection;
import java.util.Vector;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/** Implementation of the <code>org.w3c.dom.NodeList</code> DOM Level 2 and 3 Core specification interface.
 *
 * @author Carl Eric Codere
 */
public class NodeListImpl extends Vector implements NodeList
{

  public NodeListImpl()
  {

  }

  public NodeListImpl(Collection c)
  {
    super(c);
  }

  private static final long serialVersionUID = 14882812107381567L;

  
  public Node item(int index)
  {
    return (Node)get(index);
  }

  
  public int getLength()
  {
    return size();
  }

}
