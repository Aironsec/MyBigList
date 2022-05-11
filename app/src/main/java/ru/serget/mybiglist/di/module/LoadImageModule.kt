package ru.serget.mybiglist.di.module

import android.widget.ImageView
import dagger.Module
import dagger.Provides
import ru.serget.mybiglist.ImageLoaderImpl
import ru.serget.mybiglist.model.IImageLoader

@Module
class LoadImageModule {

    @Provides
    fun imageLoader(): IImageLoader<ImageView> = ImageLoaderImpl()
}