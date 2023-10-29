package dataStructure;

import java.util.ArrayList;

class Heap {
	
	public static int left(int i) {
		return 2*i+1;
	}
	public static int right(int i) {
		return 2*i+2;
	}
	public static int parent(int i) {
		return (int)Math.floor((i-1)/2);
	}
	
	public static void maxHeapify(ArrayList<Integer> arr,int i,int end) {
		
		int l = left(i);
		int r = right(i);
		
		int biggest=i;
		
		if(l<=end && arr.get(l)>arr.get(biggest)) biggest = l;
		if(r<=end && arr.get(r)>arr.get(biggest)) biggest = r;
		
		if(biggest!=i) {
			int tmp=arr.get(i);
			arr.set(i, arr.get(biggest));
			arr.set(biggest, tmp);
			maxHeapify(arr, biggest,end);
		}
		
	}
	
	public static int[] buildMaxHeap(int[] intArr){
        int middle = parent(intArr.length-1);
        ArrayList<Integer> arr = new ArrayList<>();
        for(int i=0;i<intArr.length;i++) {
        	arr.add(intArr[i]);
        }
        for(int i=middle;i>=0;i--) {
        	maxHeapify(arr, i,intArr.length-1);
        }
        int[] res = new int[intArr.length];
        
        for(int i=0;i<intArr.length;i++) {
        	res[i] = arr.get(i);
        }
        return res;
        
        
    }
	public static ArrayList<Integer> buildMaxHeap(ArrayList<Integer> intList){
        int middle = parent(intList.size()-1);
        for(int i=middle;i>=0;i--) {
        	maxHeapify(intList,i,intList.size()-1);
        }
        return intList;
        
    }
	
	
	public static void minHeapify(ArrayList<Integer> arr,int i, int end) {
		
		int l = left(i);
		int r = right(i);
		
		int smallest=i;
		
		if(l<=end && arr.get(l)<arr.get(smallest)) smallest= l;
		if(r<=end && arr.get(r)<arr.get(smallest)) smallest = r;
		
		if(smallest!=i) {
			int tmp=arr.get(i);
			arr.set(i, arr.get(smallest));
			arr.set(smallest, tmp);
			minHeapify(arr, smallest,end);
		}
		
	}
	
	public static int[] buildMinHeap(int[] intArr){
        int middle = parent(intArr.length-1);
        ArrayList<Integer> arr = new ArrayList<>();
        for(int i=0;i<intArr.length;i++) {
        	arr.add(intArr[i]);
        }
        for(int i=middle;i>=0;i--) {
        	minHeapify(arr, i,arr.size()-1);
        }
        int[] res = new int[intArr.length];
        
        for(int i=0;i<intArr.length;i++) {
        	res[i] = arr.get(i);
        }
        return res;
        
    }
	public static ArrayList<Integer> buildMinHeap(ArrayList<Integer> intList){
        int middle = parent(intList.size()-1);
   
        for(int i=middle;i>=0;i--) {
        	minHeapify(intList, i,intList.size()-1);
        }
        
        return intList;
        
    }
	
	public static int[] heapSort(int[] intArr){
        // 関数を完成させてください
        ArrayList<Integer> heap = new ArrayList<>();
        for(int i=0;i<intArr.length;i++){
            heap.add(intArr[i]);
        }
        heapSort(heap);

        for(int i=0;i<intArr.length;i++){
            intArr[i]=heap.get(i);
        }
        return intArr;
    }
	
	
	public static void heapSort(ArrayList<Integer> arr){
        // まずは buildMaxHeap で arr をヒープ構造にします。1番上は最大値になっています。
        buildMaxHeap(arr);

        // ヒープサイズを追跡するため heapEnd を配列の最後の要素にします。
        int heapEnd = arr.size() - 1;
        while(heapEnd > 0){
            //  最大値であるヒープの根ルートと葉ノード heapEnd を入れ替えます。
            int temp = arr.get(heapEnd);
            arr.set(heapEnd, arr.get(0));
            arr.set(0, temp);

            //　一番最後はソートされているので、heapEnd から 1 引きます。
            heapEnd--;
            maxHeapify(arr, 0, heapEnd);
        }    
    }
	
	public static int[] topKElements(int[] intArr, int k){
        // 関数を完成させてください
        heapSort(intArr);

        int[] res = new int[k];

        for(int i=0;i<k;i++){
            res[i]=intArr[intArr.length-1-i];
        }

        return res;
    }
	
	public static int[] minKElements(int[] intArr, int k){
        heapSort(intArr);

        int[] res = new int[k];

        for(int i=0;i<k;i++){
            res[i]=intArr[i];
        }

        return res;
    }

}
