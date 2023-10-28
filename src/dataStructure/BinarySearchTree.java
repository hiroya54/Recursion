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
}

