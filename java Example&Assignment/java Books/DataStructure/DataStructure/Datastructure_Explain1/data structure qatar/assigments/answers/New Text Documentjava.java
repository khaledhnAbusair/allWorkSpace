   public boolean intializeIsFull() {

        return isFull(root);
    }

    public boolean isFull(Node root) {
//        if (root == null) {
//            return true;
//        }

        if (getBalancedHeigthOfNode(root) == 0) {
            return false;
        }

        return true;
    }

    public int getBalancedHeigthOfNode(Node root) {//0=fl 1=tr

        if (root == null) { //only one element is the root then its full
            return 0;
        }
        if (root.leftChild == null && root.rightChild == null) {
            return 1;
        }

        if (getBalancedHeigthOfNode(root.leftChild) != 0 && getBalancedHeigthOfNode(root.rightChild) != 0) {
            return 1;
        }
        /* if (root.leftChild != null && root.rightChild != null) {
         left = getBalancedHeigthOfNode(root.leftChild);
         right = getBalancedHeigthOfNode(root.rightChild);
         }
         if (left == 1 && right == 1) {
         return 1;
         }
         if (left > right || right > left) {
         return 0;
         }

         //        if ((left - right) != 0) {//a bracnch has more elements than others ( i.e not the same number of levels)
         //            return -1;
         //        }
         if (left == 1 && right == 1) {
         return 1;
         }*/

        return 0; // or right +1

    }