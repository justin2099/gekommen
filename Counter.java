import java.time.Instant;

public class Counter {
    private boolean isOn;
    private long t0;
    private long t;
    private Instant timeReference;

    public Counter(int t0){
        this.t0 = t0;
        t = t0;
        isOn = true;
    }


    public long getT() {
        return t;
    }

    public boolean isOn() {
        return isOn;
    }

    public void openCounter() {
        isOn = true;
    }

    public void closeCounter() {
        isOn = false;
        t0 = t;//语义其实不太对
    }

    public void resetT(int t) {
        this.t = t;
    }

    public void changeReference(Instant instant){
        timeReference = instant;
    }

    public void changeTime(Instant instant){
        final long secondReference = timeReference.getEpochSecond();
        final long temporarySecond = instant.getEpochSecond();
        final long nanoReference = timeReference.getNano();
        final long temporaryNano = instant.getNano();

        final double DELTA_T = temporarySecond - secondReference +
                        (double)(temporaryNano - nanoReference)/1000000000;

        t = t0 - (int)(DELTA_T);
    }
    //没有多线程的话，拨动秒表的操作必须在主线程里实现。
    //通过Instant类。

    //OpenCounter： 在切换后打开秒表，与Instant对准时间，依据Instant来倒计时。
    //AdjustCounter：在循环过程中不断地与Instant进行对比，过一段时间后拨动一下秒表。
    //倒计时结束后 throw TimeOver。


}
