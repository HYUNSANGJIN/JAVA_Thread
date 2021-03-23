import javax.swing.JOptionPane;

/*
 * 2개 함수가 개별로 별도 실행
 * 1. 시간을 제어하는 별도의 Thread(stack)이 필요 -> 별도로 빼기
 * 2. 단어 입력을 처리하는 Thread(stack) -> main함수에서 처리
 */
class WordTime extends Thread{ //-> Thread로 만들거야
    @Override
    public void run() {
        for(int i=10; i>0; i--) {
            try {
                System.out.println("남은 시간 : " + i);
                sleep(1000); // 1초간 깜빡 재운다 -> 규칙적으로 cpu를 점유하고 sleep을 만나면 1초간 휴식 -> 다시 실행
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
public class Ex04_Multi_Thread_word_Game {

    public static void main(String[] args) {
        // Thread 2개 돌릴거임
        // class WordTime extends Thread
        // static void main
        
        WordTime timer = new WordTime();
        timer.start();
        
        String inputdata = JOptionPane.showInputDialog("값을 입력하세요");
        System.out.println("입력값 : " + inputdata);
        System.out.println("main end");
        // 단어를 입력하지 않아도 시간은 흘러간다.
        

    }

}
