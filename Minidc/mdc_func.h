//
//  mdc_func.h
//  Minidc
//
//  Created by Peter Henderson on 2013-03-01.
//  Copyright (c) 2013 Breakend Productions. All rights reserved.
//

#ifndef __Minidc__mdc_func__
#define __Minidc__mdc_func__

#include <iostream>
#include <string>
#include "stack.h"
#include "stack.cpp"

class MiniDc{
public:
    void parseAndSeparateInput(string input);
    float peekFromRunningStack();
private:
    Stack<float> runningStack;
};
#endif /* defined(__Minidc__mdc_func__) */
