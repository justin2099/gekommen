import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.MouseListener;

public class TimerTest {
    public static void main(String[] args) {
        Timer timer = new Timer();
        TestingTimerTask hama = new TestingTimerTask();
        timer.schedule(hama,2000);
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
            try {
                Thread.sleep(300);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
class TestingTimerTask extends TimerTask{
    @Override
    public void run(){
//        System.out.println("Hello Hama!");
        for (int i = 0; i < 1000; i++) {
            System.out.println("Hama");
            try {
                Thread.sleep(400);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
/**
 * 有这个简单的Demo，其实已经可以实现计时运行了。
 *下一步是要学会在宏观上管理线程。
 * 我不想要触发式的，而想要它在背后跑，而又与主线程有交互，应该怎么办？
 */
