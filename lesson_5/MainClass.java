package Java2.lesson_5;

public class MainClass {

    public static final int size = 10000000; //
    public static final int h = size / 2;

    public static void oneThread(){

        float[] arr = createArray();

        long a = System.currentTimeMillis();

        editArray(arr);

        System.out.println("delta: " + (System.currentTimeMillis() - a));

    }

    public static void doubleThread(){

        float[] arr = createArray();

        long a = System.currentTimeMillis();

        float[] timeArr1 = new float[h];
        System.arraycopy(arr, 0, timeArr1, 0, h);
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                editArray(timeArr1);
            }
        };

        Thread threadOne = new Thread(r1, "Thread #1");
        threadOne.start();

        float[] timeArr2 = new float[h];
        System.arraycopy(arr, h, timeArr2, 0, h);
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                editArray(timeArr2);
            }
        };

        Thread threadTwo = new Thread(r2, "Thread #2");
        threadTwo.start();

        try {
            threadOne.join();
            threadTwo.join();
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }

        System.arraycopy(timeArr1, 0, arr, 0, h);
        System.arraycopy(timeArr2, 0, arr, h, h);

        System.out.println("delta: " + (System.currentTimeMillis() - a));

    }

    private static float[] createArray(){
        float[] arr = new float[size];

        for (int i = 0; i < arr.length; i++){
            arr[i] = 1;
        }
        return arr;
    }

    private static void editArray(float[] arr){
        for (int i = 0; i < arr.length; i++){
            //В первом случае, когда результат операции зависит от i, то будет быстрее более чем в 3 раза
            //  Так как i уже не 10кк, а только 5
//            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));

            //А вот тут уже в один поток будет быстрее, так как действия итераций не зависят от размера i
            //  И время уходит на создание, разделение, склейку временных массивов
            arr[i] = (float)(arr[i] * Math.sin(0.2f) * Math.cos(0.2f) * Math.cos(0.4f));
        }
    }

    public static void printArray(float[] arr){
        for (int i = 0; i < arr.length; i++){
            System.out.println(arr[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        oneThread();
        doubleThread();
    }
}
