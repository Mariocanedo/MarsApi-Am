package com.example.marsapi_am.Model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.marsapi_am.Model.Local.MarsDao

import com.example.marsapi_am.Model.Remote.MarsRealState
import com.example.marsapi_am.Model.Remote.RetrofitClient

class MarsRepository (private val marsDao: MarsDao) {


    // llama al metodo de conexion

    private val retrofitClient = RetrofitClient.getRetrofit()

   // HACE REFERENCIA AL POJO Y LA RESPUESTA VAMOS A RECIBIR
     val dataFromInternet = MutableLiveData<List<MarsRealState>>()




    // funcion que se conecta a internet en base a los código de respuesta hace acciones

    suspend fun  fetchDataFromInternetCoroutines(){

        try {
            val response= retrofitClient.fetchMarsDataCoroutines()

            when(response.code()) {

                in 200..299 -> response?.body().let {

                    if (it != null) {
                        marsDao.insertAllTerrains(it)

                    }
                }

                in 300..301 -> Log.d("REPO", "${response.code()} --- ${response.errorBody()}")
                else -> Log.d("REPO", "${response.code()} --- ${response.errorBody()}")
            }
        }catch (t: Throwable){

            Log.e("REPO", "${t.message}")
        }

    }



    // recibe un terreno por id
    fun getMarsByid(id : Int) : LiveData<MarsRealState>{
        return getMarsByid(id)
    }


    // obtiene el listado de terrenos
    val listAllTask: LiveData<List<MarsRealState>> = marsDao.getAllTerrains()


    // insertar un terreno
    suspend fun  insertTerrain ( terrain : MarsRealState){
        marsDao.insertTerrain(terrain)
    }


    // actualizar terrenos
    suspend fun  updateTerrains(terrain: MarsRealState)
    {
        marsDao.updateTerrains(terrain)
    }

    // eliminar todos los terrenos
    suspend fun deleteAll(){
        marsDao.deletAll()
    }



}