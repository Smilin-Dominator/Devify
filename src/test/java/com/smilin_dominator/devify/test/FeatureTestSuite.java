/*
    Devify
    Copyright (C) 2021 Devisha Padmaperuma

    Devify is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Devify is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Devify.  If not, see <https://www.gnu.org/licenses/>.
*/
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