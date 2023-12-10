package softwareTest;

class Student{
    public int studentId;
    public int grade;
    public String name;
    public int age;
    public double height;

    public Student(int studentId, int grade, String name, int age, double height){
        this.studentId = studentId;
        this.grade = grade;
        this.name = name;
        this.age = age;
        this.height = height;
    }
// 最年少で最も身長の高い学生を返す、関数を作成してください。もし、複数該当する場合は若いIDを持つ学生を優先してください。
    public static Student choseStudent(Student[] studentList){
        //  関数を実装してください。 
        int minIdx = 0;
        for(int i=0;i<studentList.length;i++){
            if(studentList[i].age<studentList[minIdx].age 
            		|| (studentList[i].age==studentList[minIdx].age && studentList[i].height > studentList[minIdx].height)) {
            	minIdx = i;
            }
        }
        return studentList[minIdx];
    }

}

class Tdd{
    public static void main(String[] args){
        Student[] studentList1 = new Student[]{
            new Student(1000,9,"Matt Verdict", 14, 5.5),
            new Student(1001,9,"Amy Lam", 14, 5.5),
            new Student(1002,10,"Bryant Gonzales", 15, 5.9),
            new Student(1003,9,"Kimberly York", 15, 5.3),
            new Student(1004,11,"Christine Bryant", 15, 5.8),
            new Student(1005,10,"Mike Allen", 16, 6.2),
        };
        Student[] studentList2 = new Student[]{
            new Student(1000,9,"Matt Verdict", 14, 5.5),
            new Student(1001,9,"Amy Lam", 13, 5.5),
            new Student(1002,10,"Bryant Gonzales", 15, 5.9),
            new Student(1003,9,"Kimberly York", 15, 5.3),
            new Student(1004,11,"Christine Bryant", 15, 5.8),
            new Student(1005,10,"Mike Allen", 16, 6.2),
        };

        System.out.println(Student.choseStudent(studentList1).studentId==1000);
        System.out.println(Student.choseStudent(studentList2).studentId==1000);
        //  テストケースを以下作成してください。
        System.out.println(Student.choseStudent(studentList1).studentId==1000);
        System.out.println(Student.choseStudent(studentList2).studentId==1001);
    }
}
