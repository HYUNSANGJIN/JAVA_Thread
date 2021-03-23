/*
 * Collection(Vector, ArrayList)
 * ㄴ 동기화를 보장하느냐
 * ㄴ 동기화를 보장하지 않느냐
 * 예) 한강 고수부지에서 화장실을 만들었다(공유자원) : 여러명의 사용자(Thread) 동시에 접근
 *   ㄴ 화장실은 1개인데 사람10명(Thread 10개) -> 동시에 사용
 *   
 * 문제점 : LOCK 장치가 없다.(화장실 문을 못잠금)
 * 해결방안 : LOCK을 만들어 줘야한다.(함수 단위)
 * 
 * synchronized void openDoor 동기화 보장(안전장치)
 * 
 * 반대
 * 
 * 비빔밥 : 동시에 먹는것이니까 자원을 보호 필요 없다 (LOCK)
 */
class Wroom{  // 화장실 클래스
    synchronized void openDoor(String name) {
        System.out.println(name + "님 응가하러 입장");
        for(int i=0; i<100; i++) { // 응가하는 시간 100초
            System.out.println(name + "화장실 사용"+ i);
            if(i == 10) { // 이때 뿌직 시작!
                System.out.println(name + "님 뿌직~!!");
            }
        }
        System.out.println("쾌변하셨나요^^");
    }
}

class User extends Thread{
    Wroom wr; // 화장실 주소(알아야 화장실을 감)
    String who; 
    User(String name, Wroom w){
        this.who = name;
        this.wr = w;
    }
    @Override
    public void run() {
        wr.openDoor(this.who);
    }
    
}

public class Ex10_Sync_Thread {

    public static void main(String[] args) {
        // 이제부터 여긴 한강 고수부지 이다
        Wroom w = new Wroom();
        
        // 사람들 (Thread)
        User hyun = new User("현씨", w);
        User jeong = new User("정씨", w);
        User lee = new User("이씨", w);

        //배가 아파서 화장실 뛰어감
        hyun.start();
        jeong.start();
        lee.start();
    }

}
