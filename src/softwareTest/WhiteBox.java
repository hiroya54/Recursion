package softwareTest;
import java.util.ArrayList;

class WhiteBox{
    public static void printStudents(ArrayList<Student> students){
        System.out.println("----Total students: " + students.size() + "----");
        for(Student student: students) System.out.println(student);
        System.out.println("---END---");
    }

    // 2 つの学生リストが等しいかどうかを返す述語関数
    public static boolean areStudentListsEquals(ArrayList<Student> studentList1, ArrayList<Student> studentList2) {
    	if(studentList1.size()!=studentList2.size()) return false;
    	
    	for(int i=0;i<studentList1.size();i++) {
    		if(studentList1.get(i).studentId!=studentList2.get(i).studentId) return false;
    	}
    	
        return true;
    }   

    // s1がs2より若く、背が高いかどうかを返します。もし、同じならs1とs2のIDを比較します。
    public static boolean studentCompare(Student s1, Student s2){
        if(s1.age == s2.age){
            return s1.height == s2.height? s1.studentId < s2.studentId : s1.height > s2.height;
        } 
        return s1.age < s2.age;
    }

    // studentListをheapifyし、最初のk個の要素をpopします。
    public static void heapify(ArrayList<Student> l){
        for(int index = l.size()/2; index >= 0; index--){
            minHeap(l, index);
        }
    }    
    
    public static void swap(ArrayList<Student> arr, int i, int j){
        Student temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }

    public static void minHeap(ArrayList<Student> l, int index){
        int lengthL = l.size();
        int curr = index;
        boolean flag = true;
        while(flag){
            int left = curr * 2 + 1;
            int right = curr * 2 + 2;
            int smallest = curr;

            if(lengthL > left && !studentCompare(l.get(smallest), l.get(left))) smallest = left;
            if(lengthL > right && !studentCompare(l.get(smallest), l.get(right))) smallest = right;

            if(smallest == curr) flag = false;
            else swap(l, curr, smallest);

            curr = smallest;
        }
    }

    // 入力の配列に副作用が発生しないようにコードに修正を加えてください
    public static ArrayList<Student> chooseStudent(ArrayList<Student> studentList, int k){
    	
    	ArrayList<Student> copyStudentList = new ArrayList<>();
    	copyStudentList.addAll(studentList);
        // Heapify studentList
        heapify(copyStudentList);

        ArrayList<Student> results = new ArrayList<>();
        for(int i = 0; i < k; i++){
            // minを最後のノードとswapし、削除します。O(1)
            swap(copyStudentList, 0, copyStudentList.size()-1);
            results.add(copyStudentList.remove(copyStudentList.size()-1));

            if(copyStudentList.size() > 0) minHeap(copyStudentList, 0);
            else break;
        }    
        return results;
    }

    public static void main(String[] args){
        ArrayList<Student> studentList1 = new ArrayList<>(){
            {
                add(new Student(1000,9,"Matt Verdict", 14, 5.5));
                add(new Student(1001,9,"Amy Lam", 14, 5.5));
                add(new Student(1002,10,"Bryant Gonzales", 15, 5.9));
                add(new Student(1003,9,"Kimberly York", 15, 5.3));
                add(new Student(1004,11,"Christine Bryant", 15, 5.8));
                add(new Student(1005,10,"Mike Allen", 16, 6.2));
            }
        };

        ArrayList<Student> studentList2 = new ArrayList<>(){
            {
                add(new Student(1000,9,"Matt Verdict", 14, 5.5));
                add(new Student(1001,9,"Amy Lam", 13, 5.5));// 変更され、13歳
                add(new Student(1002,10,"Bryant Gonzales", 15, 5.9));
                add(new Student(1003,9,"Kimberly York", 15, 5.3));
                add(new Student(1004,11,"Christine Bryant", 15, 5.8));
                add(new Student(1005,10,"Mike Allen", 16, 6.2));

            }
        };

        // studentList1をコピー       
        ArrayList<Student> copyStudentList1  = new ArrayList<>();
        copyStudentList1.addAll(studentList1);
        System.out.println(chooseStudent(studentList1,1).get(0).studentId == 1000);
        System.out.println(areStudentListsEquals(studentList1, copyStudentList1));

        // studentList2をコピー
        ArrayList<Student> copyStudentList2  = new ArrayList<>();
        copyStudentList2.addAll(studentList2);
        System.out.println(chooseStudent(studentList2,1).get(0).studentId == 1001);
        System.out.println(areStudentListsEquals(studentList2, copyStudentList2));
        
        ArrayList<Student> studentList3 = new ArrayList<>(){
            {
                add(new Student(1000,9,"Matt Verdict", 11, 5.5));// 変更、11歳
                add(new Student(1001,9,"Amy Lam", 13, 5.5));
                add(new Student(1002,10,"Bryant Gonzales", 13, 5.5));// 変更、13歳
                add(new Student(1003,9,"Kimberly York", 15, 5.3));
                add(new Student(1004,11,"Christine Bryant", 15, 5.3)); // 変更、5.3高さ
                add(new Student(1005,10,"Mike Allen", 16, 6.2));

            }
        };
        // studentList3をコピー
        ArrayList<Student> copyStudentList3 = new ArrayList<>();
        copyStudentList3.addAll(studentList3);
        // リスト3から4人を出力
        printStudents(chooseStudent(studentList3,4));
        System.out.println(areStudentListsEquals(studentList3, copyStudentList3));
    }
}