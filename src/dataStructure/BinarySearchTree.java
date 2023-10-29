package dataStructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class BinarySearchTree{
	public BinaryTree<Integer> root;
	
	public BinarySearchTree(int[] arr) {
		Arrays.sort(arr);
		this.root=sortedArrayToBST(arr);
	}
	
	public static BinaryTree<Integer> sortedArrayToBST(int[] nums) {
		if(nums.length==0) return null;
		return sortedArrayToBSTHelper(nums, 0, nums.length-1);
	}
	
	public static BinaryTree<Integer> sortedArrayToBSTHelper(int[] nums, int start, int end) {
		if(start==end) return new BinaryTree<Integer>(nums[start],null,null);
		
		int mid = (int)Math.floor((start+end)/2);
		
		BinaryTree<Integer> left=null;
		if(start<=mid-1) left = sortedArrayToBSTHelper(nums, start, mid-1);
		
		BinaryTree<Integer> right = null;
		if(mid+1<=end) right = sortedArrayToBSTHelper(nums, mid+1, end);
		
		
		BinaryTree<Integer> root = new BinaryTree<Integer>(nums[mid], left,right);	
		return root;
	}
	
	public BinaryTree<Integer> search(int key) {
		BinaryTree<Integer> iterator = this.root;
		while(iterator!=null) {
			if(iterator.data==key) return iterator;
			
			if(iterator.data>key) iterator = iterator.left;
			else iterator = iterator.right;
		}
		
		return null;
	}
	
	public boolean keyExist(int key) {
		BinaryTree<Integer> iterator = this.root;
		
		while(iterator!=null) {
			if(iterator.data==key) return true;
			
			if(iterator.data>key) iterator=iterator.left;
			else iterator=iterator.right;
		}
		
		return false;
	}
	
	
	
	public BinaryTree<Integer> findParent(BinaryTree<Integer> keyNode){
		BinaryTree<Integer> iterator = this.root;
		BinaryTree<Integer> parent = null;
		while(iterator!=keyNode) {
			parent = iterator;
			iterator = (iterator.data>keyNode.data) ? iterator.left:iterator.right;
		}
		return parent;
 	}
	
	public static boolean keyExist(int key, BinaryTree<Integer> bst){
        if(bst == null) return false;
        if(bst.data == key) return true;

        // 現在のノードよりキーが小さければ左に、大きければ右に辿ります。
        if(bst.data > key) return keyExist(key, bst.left);
        else return keyExist(key, bst.right);
    }
	
	public static int[] preorderTraversal(BinaryTree<Integer> root){
        if(root==null) return new int[0];
		
        List<Integer> resList = new ArrayList<>();
        preorderTraversalHelper(resList, root);
        
        int[] res = new int[resList.size()];
        
        for(int i=0;i<resList.size();i++) {
        	res[i] = resList.get(i);
        }
		return res;
    }
	public static void preorderTraversalHelper(List<Integer> resList, BinaryTree<Integer> root) {
		if(root==null) return;
		resList.add(root.data);
		preorderTraversalHelper(resList, root.left);
		preorderTraversalHelper(resList, root.right);
	}
	
	public static int[] inorderTraversal(BinaryTree<Integer> root){
        if(root==null) return new int[0];
		
        List<Integer> resList = new ArrayList<>();
        inorderTraversalHelper(resList, root);
        
        int[] res = new int[resList.size()];
        
        for(int i=0;i<resList.size();i++) {
        	res[i] = resList.get(i);
        }
		return res;
    }
	public static void inorderTraversalHelper(List<Integer> resList, BinaryTree<Integer> root) {
		if(root==null) return;
		inorderTraversalHelper(resList, root.left);
		resList.add(root.data);
		inorderTraversalHelper(resList, root.right);
	}
	
	public static int[] postorderTraversal(BinaryTree<Integer> root){
        if(root==null) return new int[0];
		
        List<Integer> resList = new ArrayList<>();
        postorderTraversalHelper(resList, root);
        
        int[] res = new int[resList.size()];
        
        for(int i=0;i<resList.size();i++) {
        	res[i] = resList.get(i);
        }
		return res;
    }
	public static void postorderTraversalHelper(List<Integer> resList, BinaryTree<Integer> root) {
		if(root==null) return;
		postorderTraversalHelper(resList, root.left);
		postorderTraversalHelper(resList, root.right);
        resList.add(root.data);
	}
	
	public static int[] reverseInorderTraversal(BinaryTree<Integer> root){
        if(root==null) return new int[0];
		
        List<Integer> resList = new ArrayList<>();
        reverseInorderTraversalHelper(resList, root);
        
        int[] res = new int[resList.size()];
        
        for(int i=0;i<resList.size();i++) {
        	res[i] = resList.get(i);
        }
		return res;
    }
	public static void reverseInorderTraversalHelper(List<Integer> resList, BinaryTree<Integer> root) {
		if(root==null) return;
		reverseInorderTraversalHelper(resList, root.right);
		resList.add(root.data);
		reverseInorderTraversalHelper(resList, root.left);
	}
	
	public static int maximumDepth(BinaryTree<Integer> root){
		return maximumDepthHelper(root, 0);
    }
	
	public static int maximumDepthHelper(BinaryTree<Integer> root, int depth) {
		if(root==null) return depth;
		
		int leftDepth=depth;
		if(root.left!=null) leftDepth=maximumDepthHelper(root.left, depth+1);
		
		int rightDepth=depth;
		if(root.right!=null) rightDepth=maximumDepthHelper(root.right, depth+1);
		
		return Math.max(leftDepth, rightDepth);
	}
	
	public static int deepestLeaves(BinaryTree<Integer> root){
		
        int maximumDepth=maximumDepth(root);
        return deepestLeavesHelper(root,maximumDepth,0);
        
    }

	 public static int deepestLeavesHelper(BinaryTree<Integer> root,int maximumDepth,int currentDepth){
		 if(root.left==null && root.right==null) {
			 if(maximumDepth==currentDepth) return root.data;
			 else return 0;
		 }
		 int left=0;
		 if(root.left!=null) left = deepestLeavesHelper(root.left,maximumDepth,currentDepth+1);
		 int right=0;
		 if(root.right!=null) right = deepestLeavesHelper(root.right,maximumDepth,currentDepth+1);
		 
		 return left+right; 
		 
	 }
	
	public static int countNodes(BinaryTree<Integer> root){
		
        if(root==null) return 0;
        int leftcount = (root.left==null)?0:countNodes(root.left);
        int rightcount = (root.right==null)?0:countNodes(root.right);

        return leftcount+rightcount+1;
    }
	
	public static int[] allElementsSorted(BinaryTree<Integer> root1, BinaryTree<Integer> root2){

        int[] root1Array = inorderTraversal(root1);
        int[] root2Array = inorderTraversal(root2);

        int[] res = new int[root1Array.length+root2Array.length];

        int idx=0;
        int i=0;
        int j=0;

        while(idx<root1Array.length+root2Array.length){

            if(i<root1Array.length && j<root2Array.length){
                if(root1Array[i]<root2Array[j]) {
                    res[idx]=root1Array[i];
                    i++;
                }else{
                	res[idx]=root2Array[j];
                	j++;
                }
            }else if(i<root1Array.length){
                while(i<root1Array.length){
                    res[idx]=root1Array[i];
                    idx++;
                    i++;
                }
            }else{
                while(j<root2Array.length){
                    res[idx]=root2Array[j];
                    idx++;
                    j++;
                }
            }
            idx++;
        }

        return res;
    }
	
	public static BinaryTree<Integer> invertTree(BinaryTree<Integer> root){
        if(root==null) return null;
        
        BinaryTree<Integer> tmp = root.left;
        root.left=root.right;
        root.right=tmp;
        invertTree(root.left);
        invertTree(root.right);
        
        return root;
        
    }
	public static boolean treeWithTheSameValue(BinaryTree<Integer> root){
        if(root==null) return false;
        int val = root.data;
        return treeWithTheSameValueHelper(root, val); 
        
	}
	public static boolean treeWithTheSameValueHelper(BinaryTree<Integer> root, int val) {
		
		if(root.data!=val) return false;
		
		boolean left=true;
        if(root.left!=null) {
        	left=treeWithTheSameValueHelper(root.left,val);
        }
        boolean right=true;
        if(root.right!=null) {
        	right = treeWithTheSameValueHelper(root.right,val);
        }
        
        if(left && right) return true;
        else return false;
	}
	
	public static BinaryTree<Integer> mergeBST(BinaryTree<Integer> root1, BinaryTree<Integer> root2){
		if(root1==null && root2==null) return null;
		if(root1==null) return root2;
		if(root2==null) return root1;
		
		BinaryTree<Integer> newRoot = new BinaryTree<Integer>(root1.data+root2.data);
		
		return mergeBSTHelper(newRoot, root1, root2);
        
    }
	
	public static BinaryTree<Integer> mergeBSTHelper(BinaryTree<Integer> newRoot,BinaryTree<Integer> root1, BinaryTree<Integer> root2){
		BinaryTree<Integer> mergeBT=null;

		if(root1==null && root2==null) return null;
		else if(root1==null) return root2;
		else if(root2==null) return root1;
		else {
			mergeBT = new BinaryTree<Integer>(root1.data+root2.data);
			mergeBT.left = mergeBSTHelper(mergeBT.left, root1.left, root2.left);
			mergeBT.right = mergeBSTHelper(mergeBT.right, root1.right, root2.right);
		}
		
		return mergeBT;      
    }
	
	public static boolean isSameTree(BinaryTree<Integer> root1, BinaryTree<Integer> root2){
		if(root1==null && root2==null) return true;
		if(root1==null || root2==null) return false;
		boolean current = false;
		if(root1.data==root2.data) current = true;;
		
		boolean left = isSameTree(root1.left, root2.left);
		boolean right = isSameTree(root1.right, root2.right);
		
		if(current&&left&&right) return true;
		else return false;
		
    }
	
//	public BinaryTree<Integer> findSuccessor(BinaryTree<Integer> node){
//	}
//	
//	public void deleteNode(int key){
//		if(this.root==null || !keyExist(key)) return;
//		
//		BinaryTree<Integer> target = search(key);
//		BinaryTree<Integer> parent = findParent(target);
//		//targetが葉の場合
//		if(target.left==null && target.right==null) {
//			if(parent.left==target) parent.left=null;
//			else parent.right=null;
//		}	
//		//targetの左部分木が存在しない場合
//		else if(target.left==null && target.right!=null) {
//			if(parent.left==target) parent.left=target.right;
//			else parent.right=target.right;
//		}
//		//targetの右部分木が存在しない場合
//		else if(target.left!=null && target.right==null) {
//			if(parent.left==target) parent.left=target.left;
//			else parent.right=target.left;
//		}
//		//targetの両部分木が存在する場合
//		else {
//			BinaryTree<Integer> successor= this.findSuccessor(target);
//			BinaryTree<Integer> successorP = this.findParent(successor);
//			//targetの子ノードがsuccessorの場合
//			if(target==successorP) {
//				successor.left=target.left;
//				if(parent.left==target) parent.left=successor;
//				else parent.right=successor;
//			}
//			//targetの子ノードがsuccessorじゃない場合
//			else {
//				//successorPの左部分木をsuccessorの右部分木にする
//				successorP.left=successor.right;
//				//targetをsuccessorに移植
//				target.data=successor.data;
//				
//			}
//			
//		}
//	}
	
	public static int minDepth(BinaryTree<Integer> root){
        return minDepthHelper(root, 0);
    }
	
	public static int minDepthHelper(BinaryTree<Integer> root,int depth) {
		if(root.left==null && root.right==null) return depth;
		int left=Integer.MAX_VALUE;
		if(root.left!=null)  left=minDepthHelper(root.left, depth+1);
		int right=Integer.MAX_VALUE;
		if(root.right!=null) right=minDepthHelper(root.right, depth+1);
		
		return Math.min(left, right);
	}
	public static boolean hasGrandParent(BinaryTree<Integer> root) {
		if(root==null) return false;
		BinaryTree<Integer> left = root.left;
		BinaryTree<Integer> right = root.right;
		if(left!=null && (left.left!=null || left.right!=null)) return true;
		if(right!=null && (right.left!=null || right.right!=null)) return true;
		
		return false;
	}
	public static int totalEvenGrandparent(BinaryTree<Integer> root){
		return totalEvenGrandparentHelper(root);
     }
	
	public static int totalEvenGrandparentHelper(BinaryTree<Integer> root) {
		if(!hasGrandParent(root)) return 0;
		int count=0;
		int leftcount=0;
		int rightcount=0;
		if(root.data%2!=0) {
			leftcount=totalEvenGrandparentHelper(root.left);
			rightcount=totalEvenGrandparentHelper(root.right);
		}else {
			BinaryTree<Integer> left = root.left;
			BinaryTree<Integer> right = root.right;
			if(left!=null && left.left!=null) count+=left.left.data;
			if(left!=null && left.right!=null) count+=left.right.data;
			if(right!=null && right.left!=null) count+=right.left.data;
			if(right!=null && right.right!=null) count+=right.right.data;
			
			leftcount=totalEvenGrandparentHelper(root.left);
			rightcount=totalEvenGrandparentHelper(root.right);
		}
		return leftcount+rightcount+count;
	}
	
	public static BinaryTree<Integer> bstInsert(BinaryTree<Integer> root, int key){
		if(root==null || keyExist(key, root)) return root;
		BinaryTree<Integer> iterator = root;
		while(iterator.data!=key) {
			if(iterator.left==null&&iterator.data>key)iterator.left=new BinaryTree<Integer>(key);
			if(iterator.right==null&&iterator.data<key)iterator.right=new BinaryTree<Integer>(key);
			if(iterator.data>key) iterator=iterator.left;
			else iterator=iterator.right;
		}
		return root;
    }
	
	public static boolean validateBST(BinaryTree<Integer> root){
		if(root==null) return true;
		boolean left=validateLeftBSTHelper(root, root.left);
		boolean right=validateRightBSTHelper(root, root.right);
		if(left&&right) return true;
        else return false;
		
    }
	
	public static boolean validateLeftBSTHelper(BinaryTree<Integer> parent,BinaryTree<Integer> child) {
		if(parent==null || (parent!=null && child==null)) return true;
		if(parent.data<child.data) return false;
		boolean left = true;
		if(child.left!=null) {
			if((parent.data<child.left.data) || child.data<child.left.data) return false;
			left=validateLeftBSTHelper(child, child.left);
		}
		boolean right=true;
		if(child.right!=null) {

			if(parent.data<child.right.data || child.data>child.right.data) return false;
			right=validateRightBSTHelper(child, child.right);
		}
		if(left&&right) return true;
        else return false;
	}
	public static boolean validateRightBSTHelper(BinaryTree<Integer> parent,BinaryTree<Integer> child) {
		if(parent==null || (parent!=null && child==null)) return true;
		if(parent.data>child.data) return false;
		
		boolean left = true;
		if(child.left!=null) {
			if((parent.data>child.left.data) || child.data<child.left.data) return false;
			left=validateLeftBSTHelper(child, child.left);
		}
		boolean right=true;
		if(child.right!=null) {
			if(parent.data>child.right.data || child.data>child.right.data) return false;
			right=validateRightBSTHelper(child, child.right);
		}
		if(left&&right) return true;
        else return false;
	}
	
	public static boolean symmetricTree(BinaryTree<Integer> root){
        if(root==null) return true;
        if(root.left==null && root.right==null) return true;
        ArrayList<BinaryTree<Integer>> left = inorderTraversalBT(root.left);
        ArrayList<BinaryTree<Integer>> right = reverseInorderTraversalBT(root.right);
        if(left.size()!=right.size()) return false;
        
        for(int i=0;i<left.size();i++) {
        	if(left.get(i)==null && right.get(i)==null) continue;
        	else if(left.get(i)==null || right.get(i)==null) return false;
        	else {
        		if(!left.get(i).data.equals(right.get(i).data)) return false;
        	}
        }
        return true;
    }

   public static ArrayList<BinaryTree<Integer>> inorderTraversalBT(BinaryTree<Integer> root){
		ArrayList<BinaryTree<Integer>> resList = new ArrayList<>();
        inorderTraversalHelperBT(resList, root);
		return resList;
    }
	public static void inorderTraversalHelperBT(ArrayList<BinaryTree<Integer>> resList, BinaryTree<Integer> root) {
		if(root==null) {
			resList.add(root);
			return;
		}
        if(root.left==null && root.right==null) {
            resList.add(root);
            return;
        }else{
            inorderTraversalHelperBT(resList, root.left);
            resList.add(root);
		    inorderTraversalHelperBT(resList, root.right);
        }
	}

    public static ArrayList<BinaryTree<Integer>> reverseInorderTraversalBT(BinaryTree<Integer> root){
		ArrayList<BinaryTree<Integer>> resList = new ArrayList<>();
        reverseInorderTraversalHelperBT(resList, root);
		return resList;
    }
	public static void reverseInorderTraversalHelperBT(ArrayList<BinaryTree<Integer>> resList, BinaryTree<Integer> root) {
		if(root==null) {
			resList.add(root);
			return;
		}
        if(root.left==null && root.right==null){
            resList.add(root);
            return;
        }else{
            reverseInorderTraversalHelperBT(resList, root.right);
		    resList.add(root);
		    reverseInorderTraversalHelperBT(resList, root.left);
        }
	}
	
	public static Integer[] levelOrderTraversal(BinaryTree<Integer> root){
		Queue<BinaryTree<Integer>> p = new Queue<>();
		ArrayList<BinaryTree<Integer>> list = new ArrayList<>();
		list.add(root);
		p.enqueue(root);
		BinaryTree<Integer> current = null;
		while(p.peekFront()!=null) {
			current = p.dequeue();
			if(current==null) continue;
			if(current.left==null && current.right==null) continue;
			list.add(current.left);
			p.enqueue(current.left);
			list.add(current.right);
			p.enqueue(current.right);
		}
		int len = list.size()-1;
		while(list.get(len)==null) {
			list.remove(len);
			len--;
		}
		
		Integer[] res = new Integer[list.size()];
		
		for(int i=0;i<res.length;i++) {
			if(list.get(i)==null) res[i]=null;
			else res[i]=list.get(i).data;
		}
		return res;
    }
	public static void levelOrderTraversalHelper(Stack<BinaryTree<Integer>> s,ArrayList<BinaryTree<Integer>> list) {
		BinaryTree<Integer> current = s.pop();
		if(current==null) return;
		list.add(current.left);
		s.push(current.left);
		list.add(current.right);
		s.push(current.right);
	}
	
}

