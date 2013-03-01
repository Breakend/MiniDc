//
//  main.cpp
//  Minidc
//
//  Created by Peter Henderson on 2013-02-16.
//  Copyright (c) 2013 Breakend Productions. All rights reserved.
//

#include <iostream>
#include <string>
#include "stack.h"
#include "stack.cpp"
#include "gtest/gtest.h"

using namespace std;

int main(int argc, char * argv[])
{
    string input;
    cout << "Hey there! Welcome to MiniDc! If you wanna run the tests, type in tests. \nOther wise just hit enter to continue...\n";
    getline (cin, input);
    if(input == "tests"){
        ::testing::InitGoogleTest(&argc, argv);
        return RUN_ALL_TESTS();
    }

    return 0;
}

