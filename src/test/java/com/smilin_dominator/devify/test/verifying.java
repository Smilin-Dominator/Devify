package com.smilin_dominator.devify.test;

import com.smilin_dominator.devify.backend.verify;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class verifying {

    private final verify Verify = new verify();

    @Test
    public void two_hashes() {

        String hash1 = "bf3f1a53a871ca130b3a238b3de8c757c6d9ba9614e8fc73ddb23daf003a8cd9";
        String hash2 = "bf3f1a53a871ca130b3a238b3de8c757c6d9ba9614e8fc73ddb23daf003a8cd9";
        String hash3 = "bf3f1a53a871ca130b3a238b3de8c757614e8fc73ddb23daf003a8cd9";

        boolean True = Verify.compare_hashes(hash1, hash2);
        boolean False = Verify.compare_hashes(hash1, hash3);

        assertTrue(True);
        assertFalse(False);

    }

}
