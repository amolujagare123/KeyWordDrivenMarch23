import KeyWordEngine.KeyWordEngine;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest {


    @Test
    public void loginTest() throws IOException {
        KeyWordEngine keyWordEngine = new KeyWordEngine();
        keyWordEngine.startEngine("KeywordsData/Keywords.xlsx","login");
    }
}
