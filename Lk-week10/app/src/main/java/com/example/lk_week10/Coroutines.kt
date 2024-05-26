package com.example.lk_week10

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

fun main(){
    GlobalScope.launch {
        println("code run in coroutine scope")
    }
}