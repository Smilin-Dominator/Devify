import backend.hash;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import java.util.Objects;

public class hashing {

    private final hash hashing_test = new hash();

    @Test
    void hashString() {
        String tbh = "This is a test!";
        String hashed = hashing_test.string(tbh);
        String real_hash = "54ba1fdce5a89e0d3eee6e4c587497833bc38c3586ff02057dd6451fd2d6b640";
        boolean eq = Objects.equals(real_hash, hashed);
        assertTrue(eq);
    }

}
