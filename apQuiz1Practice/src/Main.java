public class Main {

    class University {
        private List<Student> db = new ArrayList<Student>();

        public void addStudent(Student s) {
            db.add(s);
        }
    }

    class Student {
        private String name;

        public String getName() {
            return name;
        }
    }

    // The execution time of this code will be 7 seconds as there are 2 threads
    // using the same runnable object. The keyword synchronized implements mutual
    // exclusion and therefore 2 thread cannot run the code at the same time and
    // therefore the code time will be 4 + 3 seconds. Yes the execution time can be
    // improved by simply removing the synchronized keyword from the run() method in
    // the code. This enables the different threads to execute the run() method
    // parallely and the answer would then be 4 second as that is the maximum time
    // taking process.

    class MyClass extends RecursiveAction {
        public Integer result;

        public MyClass(Integer r) {
            result = r;
        }

        public void compute() {
            if (n < 2) {
                this.result = n;
                return;
            }
            Fibonacci left = new Fibonacci(this.n - 1);
            Fibonacci right = new Fibonacci(this.n - 2);
            left.fork();
            right.compute();
            left.join();
            this.result = left.result + right.result;
        }

        public static void main(String[] args) {
            ForkJoinPool pool = new ForkJoinPool(2);
            Fibonacci task = new Fibonacci(40);
            pool.invoke(task);
            int result = task.result;
        }
    }

}
