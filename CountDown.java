import org.w3c.dom.events.EventException;

import java.util.TimerTask;

public class CountDown extends TimerTask {
    private int n;

    public CountDown(){
        this.n = 10;
    }
    public CountDown(int n){
        this.n = n;
    }

    @Override
    public void run() throws EventException {
        while (n-- > 0){
//          draw(n) 调用绘制计时器的方法
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            //在哪里检测数完了？？
        }
        //循环结束自动结束游戏
        //如何在这样一个方法里面结束大进程里的循环？
        //return; 实现不了做完再return
        //throw new EventException((byte)1,"End");
        //我还没想好throw 什么
        this.cancel();
    }
}
