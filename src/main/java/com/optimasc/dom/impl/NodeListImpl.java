package com.optimasc.dom.impl;

import java.util.Collection;
import java.util.Vector;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/** Implementation of the <code>org.w3c.dom.NodeList</code> DOM Level 2 and 3 Core specification interface.
 *
 * @author Carl Eric Codere
 */
public class NodeListImpl extends Vector<Node> implements NodeList
{

  public NodeListImpl()
  {

  }

  public NodeListImpl(Collection<? extends Node> c)
  {
    super(c);
  }

  private static final long serialVersionUID = 14882812107381567L;

  @Override
  public Node item(int index)
  {
    return (Node)get(index);
  }

  @Override
  public int getLength()
  {
    return size();
  }

}
