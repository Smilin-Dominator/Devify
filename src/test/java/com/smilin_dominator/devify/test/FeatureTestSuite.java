package com.smilin_dominator.devify.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        commonFuncs.class,
        hashing.class,
        verifying.class
})

public class FeatureTestSuite {
    // the class remains empty,
    // used only as a holder for the above annotations
}