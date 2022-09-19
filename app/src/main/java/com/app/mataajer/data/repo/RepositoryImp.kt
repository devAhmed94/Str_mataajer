package com.app.mataajer.data.repo

import com.app.mataajer.application.core.BaseRsm
import com.app.mataajer.data.repo.localRepo.LocalRepoImp
import com.app.mataajer.data.db.entities.PostLocal
import com.app.mataajer.data.repo.localRepo.LocalRepo
import com.app.mataajer.data.repo.remoteRepo.RemoteRepo
import com.app.mataajer.data.repo.remoteRepo.RemoteRepoImp
import com.app.mataajer.domain.remote.Post
import retrofit2.Response


/**
 * Ahmed Ali Ebaid
 * ahmedali26002844@gmail.com
 * 13/09/2022
 */
class RepositoryImp(private val localRep: LocalRepo, private val remoteRepo: RemoteRepo) :
    Repository {

    override suspend fun getPostsFromLocal(): List<PostLocal> = localRep.getPostsFromLocal()

    override suspend fun insertToLocal(post: PostLocal) = localRep.insertToLocal(post)

    override suspend fun getPosts(): Response<BaseRsm<Post>> = remoteRepo.getPosts()


}