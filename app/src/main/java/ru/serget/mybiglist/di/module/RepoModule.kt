package ru.serget.mybiglist.di.module

import dagger.Module
import dagger.Provides
import ru.serget.mybiglist.model.api.IDataSource
import ru.serget.mybiglist.model.repo.IRedditPosts
import ru.serget.mybiglist.model.repo.RedditPostsImpl
import javax.inject.Singleton

@Module
class RepoModule {

    @Provides
    @Singleton
    fun repo(api: IDataSource): IRedditPosts = RedditPostsImpl(api)
}