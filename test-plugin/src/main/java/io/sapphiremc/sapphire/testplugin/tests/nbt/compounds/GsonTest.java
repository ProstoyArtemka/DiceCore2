/*
 * Copyright (c) 2022 DenaryDev
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */
package io.sapphiremc.sapphire.testplugin.tests.nbt.compounds;

import io.sapphiremc.sapphire.api.nbt.NBTItem;
import io.sapphiremc.sapphire.api.nbt.exceptions.NBTException;
import io.sapphiremc.sapphire.testplugin.tests.nbt.NBTTest;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class GsonTest implements NBTTest {

    @Override
    public void test() throws Exception {
        try {
            ItemStack item = new ItemStack(Material.STONE, 1);
            NBTItem nbtItem = new NBTItem(item);

            nbtItem.setObject(JSON_TEST_KEY, new SimpleJsonTestObject());

            if (!nbtItem.hasKey(JSON_TEST_KEY)) {
                throw new NBTException("Wasn't able to find JSON key! The Item-NBT-API may not work with Json serialization/deserialization!");
            } else {
                SimpleJsonTestObject simpleObject = nbtItem.getObject(JSON_TEST_KEY, SimpleJsonTestObject.class);
                if (simpleObject == null) {
                    throw new NBTException("Wasn't able to check JSON key! The Item-NBT-API may not work with Json serialization/deserialization!");
                } else if (!(STRING_TEST_VALUE).equals(simpleObject.getTestString())
                    || simpleObject.getTestInteger() != INT_TEST_VALUE
                    || simpleObject.getTestDouble() != DOUBLE_TEST_VALUE
                    || !simpleObject.isTestBoolean() == BOOLEAN_TEST_VALUE) {
                    throw new NBTException("One key does not equal the original value in JSON! The Item-NBT-API may not work with Json serialization/deserialization!");
                }
            }
        } catch (Exception ex) {
            throw new NBTException("Exception during Gson check!", ex);
        }
    }

    // region STATIC FINAL VARIABLES
    private static final String JSON_TEST_KEY = "jsonTest";

    private static final String STRING_TEST_VALUE = "TestString";
    private static final int INT_TEST_VALUE = 42;
    private static final double DOUBLE_TEST_VALUE = 1.5d;
    private static final boolean BOOLEAN_TEST_VALUE = true;

    // end region STATIC FINAL VARIABLES

    public static class SimpleJsonTestObject {
        private String testString = STRING_TEST_VALUE;
        private int testInteger = INT_TEST_VALUE;
        private double testDouble = DOUBLE_TEST_VALUE;
        private boolean testBoolean = BOOLEAN_TEST_VALUE;

        public String getTestString() {
            return testString;
        }

        public void setTestString(String testString) {
            this.testString = testString;
        }

        public int getTestInteger() {
            return testInteger;
        }

        public void setTestInteger(int testInteger) {
            this.testInteger = testInteger;
        }

        public double getTestDouble() {
            return testDouble;
        }

        public void setTestDouble(double testDouble) {
            this.testDouble = testDouble;
        }

        public boolean isTestBoolean() {
            return testBoolean;
        }

        public void setTestBoolean(boolean testBoolean) {
            this.testBoolean = testBoolean;
        }
    }
}
