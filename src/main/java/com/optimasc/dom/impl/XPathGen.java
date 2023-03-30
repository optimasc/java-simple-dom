package com.optimasc.dom.impl;

import org.w3c.dom.Comment;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;

public class XPathGen
{
  public static String getXPath(Node node)
  {
    String path = "";
    while (node != null)
    {
      int idx = getElementIndex(node);
      String xname = getNodeName(node);
      if (idx >= 1) 
        xname += "[" + idx + "]";
      path = "/" + xname + path;
      node = node.getParentNode();
    }
    return path;                            
}  
  
  protected static String getNodeName(Node node)
  {
    if (node instanceof ProcessingInstruction)
    {
      return "processing-instruction()";
    }
    if (node instanceof Text)
    {
      return "text()";
    }
    if (node instanceof Comment)
    {
      return "comment()";
    }
    return node.getNodeName();
  }
  
  protected static int getElementIndex(Node original) 
  {
    int count = 1;
    NodeList nodeList; 
    Node parentNode = original.getParentNode();
    if (parentNode == null)
      return count;
    nodeList = parentNode.getChildNodes();
    int length = nodeList.getLength();
    for (int i = 0; i < length; i++)
    {
      Node itemNode = nodeList.item(i);
      if (original.getNodeName().equals(itemNode.getNodeName()))
      {
        if (original.isEqualNode(itemNode))
          return count;
        count++;
      }
    }
    return count;
  }
}
