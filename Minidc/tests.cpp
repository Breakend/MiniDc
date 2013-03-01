//
//  tests.cpp
//  Minidc
//
//  Created by Peter Henderson on 2013-02-16.
//  Copyright (c) 2013 Breakend Productions. All rights reserved.
//

#include "gtest/gtest.h"

#include "stack.h"
#include "stack.cpp"
#include "mdc_func.h"

using namespace std;

TEST (StackTest, PushAndPeek) {
    Stack<float> intStack;
    float a = 12;
    float b = 15.0;
    EXPECT_EQ (12, intStack.push(a));  //push with no decimal
    EXPECT_EQ (15.0, intStack.push(b));  //push with decimal
    EXPECT_EQ (15.0, intStack.peek()); //make sure adding in LIFO Order
    EXPECT_EQ (15.0, intStack.peek()); //Should still be there
}

TEST (StackTest, PushAndPop) {
    Stack<float> intStack;
    float a = 12;
    float b = 15.0;
    EXPECT_EQ (12, intStack.push(a));
    EXPECT_EQ (15.0, intStack.push(b));
    EXPECT_EQ (15.0, intStack.pop()); //make sure adding in LIFO Order
    EXPECT_EQ (12, intStack.pop()); //Should have removed 15, then removed 12
    EXPECT_EQ (-1, intStack.pop()); //Should return -1 because there is nothing on the stack
}

TEST (MiniDc, ParseAndSeparateInputSimpleNumbers) {
    MiniDc newMiniDc;
    string input = "1"; //no space
    newMiniDc.parseAndSeparateInput(input);
    EXPECT_EQ(1, newMiniDc.peekFromRunningStack());
    string input2 = " 2"; //space at beginning
    newMiniDc.parseAndSeparateInput(input2);
    EXPECT_EQ(2, newMiniDc.peekFromRunningStack());
    string input3 = "3 "; //space after
    newMiniDc.parseAndSeparateInput(input3);
    EXPECT_EQ(3, newMiniDc.peekFromRunningStack());
    string input4 = " 4 "; //space on both sides
    newMiniDc.parseAndSeparateInput(input4);
    EXPECT_EQ(4, newMiniDc.peekFromRunningStack());

}
