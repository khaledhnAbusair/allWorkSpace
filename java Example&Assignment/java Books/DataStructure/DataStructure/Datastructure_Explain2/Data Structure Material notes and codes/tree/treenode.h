// Template TreeNode class definition.
#ifndef TREENODE_H
#define TREENODE_H

// forward declaration of class Tree
template< class NODETYPE > class Tree;  

template< class NODETYPE >
class TreeNode {
   friend class Tree< NODETYPE >;

public:

   // constructor
   TreeNode( const NODETYPE &d )   
      : leftPtr( 0 ), 
        data( d ), 
        rightPtr( 0 ) 
   { 
      // empty body 
   
   } // end TreeNode constructor

   // return copy of node's data
   NODETYPE getData() const 
   { 
      return data; 

   } // end getData function

private:
   TreeNode< NODETYPE > *leftPtr;  // pointer to left subtree
   NODETYPE data;
   TreeNode< NODETYPE > *rightPtr; // pointer to right subtree

}; // end class TreeNode

#endif

