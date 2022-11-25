package com.stone.maximustech.network

import com.stone.maximustech.model.Facts

sealed class ApiStates(){
    class Success(val data: Facts):ApiStates()
    class Failure(val msg:Throwable):ApiStates()
    object Loading:ApiStates()
    object Empty:ApiStates()

}