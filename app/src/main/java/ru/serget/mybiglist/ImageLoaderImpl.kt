package ru.serget.mybiglist

import android.widget.ImageView
import com.bumptech.glide.Glide
import ru.serget.mybiglist.model.IImageLoader

class ImageLoaderImpl: IImageLoader<ImageView> {
    override fun loadLeftImage(url: String, container: ImageView) {
        Glide.with(container.context)
            .load(url)
            .circleCrop()
            .into(container)
    }
}