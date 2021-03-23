import javax.swing.JOptionPane;

/*
 * 단어를 하나라도 입력해서 확인 버튼을 누르면 시간을 멈추게 하고 싶당
 * hint) 공유자원
 */
class WordInputProcess extends Thread{
    @Override
    public void run() {
        String dan = "2";
        String inputdata = JOptionPane.showInputDialog(dan + "단 값을 입력");
        if(inputdata != null && !inputdata.equals("")) {
            Ex05_WordGame.inputcheck = true; // 값을 변경
        }
        System.out.println("입력한 값은 : " + inputdata);
    }
}

class WordTimeOut extends Thread{
    @Override
    public void run() {
        for(int i=10; i>0; i--) {
            // run함수를 빠져나가는 것은 stack이 끝나는것.
            if(Ex05_WordGame.inputcheck) return; // 만약에 ex05에서 읽어온 값이 true라면 return
            System.out.println("남은 시간 : " + i);
            try {
                Thread.sleep(1000); // 1초간 쉬었다가 일할거임
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

public class Ex05_WordGame {
    // 얘가 point
    static boolean inputcheck = false; // 공유 

    public static void main(String[] args) {
        // 공유자원
        WordInputProcess word = new WordInputProcess();
        WordTimeOut time = new WordTimeOut();
        
        word.start();
        time.start();
        
        try {
            // join : 다른 Thread의 종료를 기다림
            // join을 요청한 Thread가 종료되면 그때 동작을 함.
            // 얘네둘이 끝날때까지 main은 끝나지 않는다.
            word.join(); // main에게 word가 "끝날때 까지 기다려주겠니?" 하고 부탁하는거임.
            time.join(); // main에게 time이 "끝날때 까지 기다려주겠니?" 하고 부탁하는거임.
            // join(); 을 안하면 'Main End'가 먼저 실행되고 시간이 실행됨
            // join(); 을 하면 시간이 실행되고 'Main End'실행
            
        } catch (Exception e) {
            
        }
        
        System.out.println("Main End");
    }

}
