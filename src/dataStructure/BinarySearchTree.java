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
}

