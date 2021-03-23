/*
 * Thread : 프로세스에서 하나의 최소 실행단위(=프로그램의 흐름) -> method라고 생각하면 됨!!
 * multi Thread는 stack을 여러개 만들어서 함수들을 동작하게 하겠다.
 * 
 * Thread 생성 방법
 * 1. Thread 클래스를 상속 -> class Test extends Thread -> 새로운 stack에 올라가서 놀거임
 *    ㄴ run() 함수 -> 새로운 stack가장 먼저 실행
 *    ㄴ Thread 클래스 -> Thread 스스로 객체 생성이 가능
 *    
 * 2. Runnable 인터페이스를 구현 
 *    ㄴ 다중 상속 안된다
 * ex) class Test extends Car implements Runnable -> run() 강제
 * 
 * 여기까지는 Test 클래스가 thread가 아니다.
 * 
 * Thread thread = new Thread(new Test()); -> parameter로 run()을 구현하고 있는 것
 * 
 */
class Thread_2 implements Runnable{ // run()을 재정의 하도록 강제하는 그냥 인터페이스

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            System.out.println("Thread_2 : " + i);

        }
        System.out.println("Thread_2 run() End!!");
        
    }
    
    
}
class Thread_1 extends Thread {
    @Override
    public void run() { // Thread안에 run()들어있음 -> 이 함수는 main()함수와 동일한 역할을 함
                        // 새로운 stack에 처음 올라가서 실행되는 함수 -> run()
        for (int i = 0; i < 10000; i++) {
            // getName() -> Thread가 생성되면 이름을 붙여줌
            System.out.println("Thread_1 : " + i + this.getName());

        }
        System.out.println("Thread_1 runt() End!!");
    }
}

public class Ex02_Multi_Thread {

    public static void main(String[] args) {
        // 1번
        Thread_1 th = new Thread_1();
        // 오늘의 point!!<start()> -> memory에 새로운 call stack을 생성하고 run()함수를 stack에 올려놓고 임무
        // 끝! 종료!
        th.start();
        
        // 2번
        //Thread_2 th2 = new Thread_2(); // 이거 자체는 thread가 아님
        //Thread thread = new Thread(th2); // runnable을 매게변수로 받는것
        Thread thread = new Thread(new Thread_2()); // -> Runnable을 구현하고 있는 객체 주소값을 넣어준다
        thread.start();
        
        
        for (int i = 0; i < 10000; i++) {
            System.out.println("main : " + i);

        }
        System.out.println("Main End");
    }

}
