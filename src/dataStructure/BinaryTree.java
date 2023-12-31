package dataStructure;

class BinaryTree<E> {
	
	public E data;
	public BinaryTree<E> left;
	public BinaryTree<E> right;
	
	public BinaryTree(E data) {
		this.data=data;
		
	}
	public BinaryTree(E data, BinaryTree<E> left, BinaryTree<E> right) {
		this.data=data;
		this.left=left;
		this.right=right;
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
	
	public static boolean keyExist(int key, BinaryTree<Integer> bst){
        if(bst == null) return false;
        if(bst.data == key) return true;

        // 現在のノードよりキーが小さければ左に、大きければ右に辿ります。
        if(bst.data > key) return keyExist(key, bst.left);
        else return keyExist(key, bst.right);
    }
	
	public static BinaryTree<Integer> bstSearch(BinaryTree<Integer> root, int key){
       if(root==null) return null;
       BinaryTree<Integer> iterator = root;
       while(iterator!=null) {
    	   if(iterator.data==key) return iterator;
    	   
    	   if(iterator.data>key) iterator=iterator.left;
    	   else iterator=iterator.right;
       }
       
       return null;
    }
	
	public static BinaryTree<Integer> minimumNode(BinaryTree<Integer> root){
        BinaryTree<Integer> iterator = root;
        
        while(iterator.left!=null) {
        	iterator=iterator.left;
        }
        
        return iterator;
    }
	public static BinaryTree<Integer> maximumNode(BinaryTree<Integer> root){
        BinaryTree<Integer> iterator = root;
        
        while(iterator.right!=null) {
        	iterator=iterator.right;
        }
        
        return iterator;
    }
	
	public static BinaryTree<Integer> successor(BinaryTree<Integer> root, int key){    
        BinaryTree<Integer> iterator1=bstSearch(root, key);
		BinaryTree<Integer> iterator2=root;
		BinaryTree<Integer> minNode=null;
		if(iterator1.right!=null) {
			return minimumNode(iterator1.right);
		}else {
            
			while(!iterator2.data.equals(key)) {
                if(iterator2.data>key) {
                    if(minNode==null) minNode = iterator2;
                    else if(iterator2.data<minNode.data) minNode = iterator2;
				}
				if(iterator2.data>key) iterator2=iterator2.left;
		    	else iterator2=iterator2.right;
			}
		}
        if(minNode==null || minNode.data<=key) return null;
		return minNode;
    }
	
	public static BinaryTree<Integer> predecessor(BinaryTree<Integer> root, int key){
		
		//引数のノードが存在しない場合と、二分木にkeyが存在しない場合はnullを返す
        if(root==null || !keyExist(key, root)) return null;
        
        BinaryTree<Integer> target = bstSearch(root, key);
        BinaryTree<Integer> predecessor = null;
        BinaryTree<Integer> iterator = root;
        //tagetに左部分木が存在する場合は、左部分木の最大値がpredecessorになる
        if(target.left!=null) return maximumNode(target.left);
        else {
        	//左部分木が存在しない場合は、rootからtargetまでに通るノードの中で
        	//条件を満たすノードがpredecessorになる
        	while(iterator!=null) {
       
        		if(iterator.data==key) break;
        		//iteratorの値がkeyより小さい場合、keyは右部分木にあるので右に移動する
        		//右部分木に移動する場合はiteratorはpredecessorになりうる
        		if(iterator.data<key) {
        			predecessor=iterator;
        			iterator=iterator.right;     			
        		}else {
        			//左部分木に移動する場合は
        			iterator=iterator.left;
        		}
        	}
        }
        return predecessor;
    }
	
	public static BinaryTree<Integer> sortedArrToBST(int[] numberList){
        // 関数を完成させてください
        int start = 0;
        int end = numberList.length-1;
        return sortedArrToBSTHelper(numberList,start,end);

    }

    public static BinaryTree<Integer> sortedArrToBSTHelper(int[] numberList,int start,int end){
        if(start==end) return new BinaryTree<Integer>(numberList[start]);
        
        int mid = (int)Math.floor((end-start)/2);
        BinaryTree<Integer> root = new BinaryTree<>(numberList[mid]);
        BinaryTree<Integer> left =null;
        BinaryTree<Integer> right=null;
        root.left=left;
        root.right=right;
        if(mid-1>=start) left=sortedArrToBSTHelper(numberList,start,mid-1);
        if(mid+1<=end) right=sortedArrToBSTHelper(numberList,mid+1,end);

        return root;
    }

	
	public static void main(String[] args){
        BinarySearchTree balancedBST = new BinarySearchTree(new int[]{1,2,3,4,5,6,7,8,9,10,11});
        System.out.println(balancedBST.keyExist(6));
        System.out.println(balancedBST.search(6).data);
        System.out.println(balancedBST.keyExist(2));
        System.out.println(balancedBST.search(2).data);
        System.out.println(balancedBST.search(34));
    }

}