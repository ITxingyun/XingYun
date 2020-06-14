package com.xingyun.android.base

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.xingyun.android.R
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), HasAndroidInjector {

    @IdRes
    open val containerIdRes: Int = R.id.fl_container

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }

    override fun androidInjector(): AndroidInjector<Any> = fragmentDispatchingAndroidInjector

    protected fun replaceFragment(fragment: Fragment, isAddToBackStack: Boolean = true, isAddAnimations: Boolean = true, transitionName: String? = null) =
            supportFragmentManager.beginTransaction().apply {
                if (isAddToBackStack) {
                    addToBackStack(transitionName)
                }
                if (isAddAnimations) {
                    setCustomAnimations(R.anim.switch_in_right, R.anim.switch_out_left, R.anim.switch_in_left, R.anim.switch_out_right)
                }
            }.replace(containerIdRes, fragment).commit()

}