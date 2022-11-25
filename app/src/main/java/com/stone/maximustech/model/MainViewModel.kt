package com.stone.maximustech.model


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stone.maximustech.MyRepository
import com.stone.maximustech.network.ApiStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(private val myREpositry: MyRepository):ViewModel() {
    var factsresponse:MutableLiveData<ApiStates> = MutableLiveData(ApiStates.Empty)

    init {
        getfact()
    }
    fun getfact(){
        viewModelScope.launch {
            myREpositry.getfacts()
                .onStart {
                    factsresponse.value=ApiStates.Loading
                }.catch {
                    factsresponse.value=ApiStates.Failure(it)
                }.collect{
                    factsresponse.value=ApiStates.Success(it)
                }
        }
    }
}