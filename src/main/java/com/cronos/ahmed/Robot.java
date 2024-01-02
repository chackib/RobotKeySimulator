import java.awt.*;
import java.awt.event.KeyEvent;

public class Test {
public static void main(String[] args) {
try {
Robot r = new Robot();
int i = 0;

        while (i < 3000) {
            r.keyPress(KeyEvent.VK_R);
            r.keyRelease(KeyEvent.VK_R);
            i++;
            System.out.println(i);
            Thread.sleep(10000);
        }
    } catch (AWTException | InterruptedException e) {
        throw new RuntimeException(e);
    }
}

}
