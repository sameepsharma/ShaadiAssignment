package com.example.shaadiassignment.repository

import android.content.Context
import android.util.Log
import androidx.annotation.UiThread
import com.example.shaadiassignment.db.dao.MatchesDao
import com.example.shaadiassignment.db.entities.MatchesEntity
import com.example.shaadiassignment.rest.FetchService
import com.example.shaadiassignment.util.isNetworkAvailable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MatchesRepo(
    val fetchService: FetchService,
    val matchesDao: MatchesDao,
    val context: Context,
    val resultCallback: ResultCallback
) : BaseRepo {

    lateinit var scope: CoroutineScope

    override fun getMatches(
        numMatches: Int
    ): List<MatchesEntity> {

        return if (context.isNetworkAvailable) {
            val entityList = mutableListOf<MatchesEntity>()

            scope.launch(Dispatchers.IO) {
                val matchesOnline = fetchService.getMatches(numMatches).results
                Log.e("MatchOnlSize>>>", "${matchesOnline.size} <<<")
                matchesOnline.forEach { match ->
                    entityList.add(
                        MatchesEntity(
                            0,
                            name = "$${match.name.first} ${match.name.first} ${match.name.last}",
                            dob = match.dob.date,
                            largeThumbnail = match.picture.large,
                            accepted = false
                        )
                    )
                }
                matchesDao.insertMatches(entityList)
                scope.launch(Dispatchers.Main) {
                    if (entityList.isNotEmpty())
                        resultCallback.onReceivedMatches(entityList)
                    else
                        resultCallback.onError("List Empty")
                }
            }


            return entityList
        } else {
            scope.launch(Dispatchers.IO) {
                val dbData = matchesDao.getSavedMatches()
                scope.launch(Dispatchers.Main) {
                    if (dbData.isNullOrEmpty())
                        resultCallback.onError("No data found!!")
                    else
                        resultCallback.onReceivedMatches(dbData)

                }
            }
            return emptyList()
        }

    }

    fun updateAccepted(entity: MatchesEntity) {

        scope.launch(Dispatchers.IO) {
            matchesDao.updateAccepted(entity.id)
        }

    }


}

interface ResultCallback {

    fun onReceivedMatches(list: List<MatchesEntity>)

    fun onError(msg: String)

}
