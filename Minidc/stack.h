//
//  stack.h
//  Minidc
//
//  Created by Peter Henderson on 2013-02-16.
//  Copyright (c) 2013 Breakend Productions. All rights reserved.
//

#ifndef __Minidc__stack__
#define __Minidc__stack__

#pragma once

template <class T>
class Stack {
public:
    Stack(void);
    ~Stack(void);
    int empty(void); 		// 1=true, 0=false
    T push(T &); 			// 1=successful,0=stack overflow
    T pop(void);
    T peek(void);
private:
    int top;
    T* nodes;
}; 

#endif /* defined(__Minidc__stack__) */
