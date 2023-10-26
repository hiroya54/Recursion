package dataStructure;

import java.util.Arrays;

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
	
}

