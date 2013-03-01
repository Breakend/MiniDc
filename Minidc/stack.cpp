//
//  stack.cpp
//  Minidc
//
//  Created by Peter Henderson on 2013-02-16.
//  Copyright (c) 2013 Breakend Productions. All rights reserved.
//

#include <iostream>
#include <stdlib.h>
#include "stack.h"

#define MAXSTACKSIZE 50

using namespace std;
template <class T>
Stack<T>::Stack(void){
	this->top = -1;
	this->nodes = new T[MAXSTACKSIZE];
}

template <class T>
Stack<T>::~Stack(void){
	delete this->nodes;
}

template <class T>
int Stack<T>::empty(){
	
	if(this->top < 0)
		return 0;
	return -1;
}

template <class T>
int Stack<T>::push(T & x){
	
	if(this->top < MAXSTACKSIZE){
		
		this->nodes[++this->top] = x;
		return x;
	}
	cout << "stack overflow in push. \n" << endl;
	return -1;
}

template <class T>
T Stack<T>::pop(){
	
	T x;
	if(!this->empty()){
        
		x = this->nodes[this->top];
        top--;
		return x;
	}
	cout << "stack underflow in pop.\n" << endl;
	return -1;
}

template <class T>
T Stack<T>::peek(){
    
	T x;
    x= this->nodes[this->top];
	return x;
}


