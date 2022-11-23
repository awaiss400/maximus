package com.stone.maximustech.network

import com.stone.maximustech.model.Facts

sealed class ApiStates(){
    class Sucess(val data:List<Facts>):ApiStates()
    class Failure(val msg:Throwable):ApiStates()
    object Loading:ApiStates()
    object Empty:ApiStates()

}