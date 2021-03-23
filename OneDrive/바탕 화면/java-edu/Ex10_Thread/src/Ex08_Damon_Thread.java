/*
 * 데몬 : 보조 thread
 * 
 * 주 thread -> 주 thread -> 보조 thread 운명
 * 
 * 한글 -> 글쓰기 -> 3초마다 저장 옵션
 * ㄴ 글쓰기 작업이 종료되면  -> 3초마다 저장되는 보조 옵션도 같이 종료하고 싶음
 * 
 */
public class Ex08_Damon_Thread implements Runnable{
    static boolean autosave = false;
    public static void main(String[] args) {
      Thread demonthread = new Thread(new Ex08_Damon_Thread());
      
      // demonthread 보조(Damon)
      // main함수 thread의 보조 함수
      // 보조 - 나는(demonthread) 당신(main(주 thread)과 생명을 같이하겟숩니다(?)
      demonthread.setDaemon(true);
      demonthread.start();
      
      // 주작업(한글쓰기)
      for(int i=0; i<=30; i++) {
          try {
            Thread.sleep(1000); // 1초만 재움
        } catch (Exception e) {
            
        }
          System.out.println("Main 한글 쓰기 작업" + i);
          // 자동 저장 시점
          if(i==5) {
              System.out.println("i : " + i);
              autosave = true;
          }
      }

    }
    public void autoSave() {
        System.out.println("한글 작업이 3초 간격으로 자동 저장된다.");
    }

    @Override
    public void run() {
       while(true) {
           try {
            Thread.sleep(3000);
        } catch (Exception e) {
            // TODO: handle exception
        }
           if(autosave) { //autosave ->  true라면..
               // 저장 작업
               autoSave(); // autosave함수 호출
               
           }
       }
        
    }

}
