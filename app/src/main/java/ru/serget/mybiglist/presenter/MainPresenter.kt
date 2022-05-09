package ru.serget.mybiglist.presenter

import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import ru.serget.mybiglist.model.entity.AllAwarding
import ru.serget.mybiglist.model.repo.IRedditPosts
import ru.serget.mybiglist.presenter.list.IPresenterList
import ru.serget.mybiglist.view.IMainActivity
import ru.serget.mybiglist.view.list.IAwardingView

class MainPresenter<V : IMainActivity>(
    private val repo: IRedditPosts,
    private val uiScheduler: Scheduler,
    private var dsRX: CompositeDisposable = CompositeDisposable(),
) : IMainPresenter<V> {

    inner class AwardingListPresenter : IPresenterList<IAwardingView> {

        val allAwarding = mutableListOf<AllAwarding>()

        override fun getCount(): Int = allAwarding.size

        override fun bindView(view: IAwardingView) {
            if (view.pos == allAwarding.size - 1) {
                loadData()
            }
            val awarding = allAwarding[view.pos]
            view.setId(awarding.id)
            view.setCoinPrice(awarding.coinPrice)
            view.loadIcon(awarding.iconUrl)
        }
    }

    private var currentView: V? = null
    private var currentItemPage = 0
    private var lastItemPage = 0
    private val countItemPage = 20
    val awardingList = AwardingListPresenter()

    private fun loadData() {
        dsRX.add(repo.getAllAwarding()
            .observeOn(uiScheduler)
            .subscribe { allAwarding ->
                loadPageData(allAwarding)
            }
        )
    }

    private fun loadPageData(listData: List<AllAwarding>) {
        currentItemPage = lastItemPage
        lastItemPage += countItemPage
        if (listData.size < lastItemPage) lastItemPage = listData.size
            awardingList.allAwarding.addAll(listData.subList(currentItemPage, lastItemPage))
            currentView?.setList()

    }

    override fun attachView(view: V) {
        if (view != currentView)
            currentView = view
        currentView?.init()
        awardingList.allAwarding.clear()
        loadData()
    }

    override fun detachView(view: V) {
        if (view == currentView)
            currentView = null
        dsRX.clear()
    }

}