package com.stone.maximustech.model


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stone.maximustech.MyRepository
import com.stone.maximustech.network.ApiStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MyViewModel @Inject constructor(private val myREpositry: MyRepository):ViewModel() {
    var todolistresponse:MutableState<ApiStates> = mutableStateOf(ApiStates.Empty)
    init {
        getfact()
    }
    fun getfact(){
        viewModelScope.launch {
            myREpositry.getfacts()
                .onStart {
                    todolistresponse.value=ApiStates.Loading
                }.catch {
                    todolistresponse.value=ApiStates.Failure(it)
                }.collect{
                    todolistresponse.value=ApiStates.Sucess(it)
                }
        }
    }
}