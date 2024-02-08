package com.example.ontopapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.example.ontopapp.R
import com.example.ontopapp.domain.Error
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = true): View =
    LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)

fun ImageView.loadUrl(url: String) {
    Glide.with(context).load(url).into(this)
}

inline fun <T : Any> basicDiffUtil(
    crossinline areItemsTheSame: (T, T) -> Boolean = { old, new -> old == new },
    crossinline areContentsTheSame: (T, T) -> Boolean = { old, new -> old == new },
) = object : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
        areItemsTheSame(oldItem, newItem)

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
        areContentsTheSame(oldItem, newItem)
}

fun <T> LifecycleOwner.launchAndCollect(
    flow: Flow<T>,
    state: Lifecycle.State = Lifecycle.State.STARTED,
    body: (T) -> Unit,
) {
    lifecycleScope.launch {
        this@launchAndCollect.repeatOnLifecycle(state) {
            flow.collect(body)
        }
    }
}

fun View.setVisibleOrGone(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

fun <T, U> Fragment.diff(flow: Flow<T>, mapFun: (T) -> U, body: (U) -> Unit) {
    viewLifecycleOwner.launchAndCollect(
        flow = flow.map(mapFun).distinctUntilChanged(),
        body = body,
    )
}

fun View.showErrorSnackBar(error: Error, action: () -> Unit) {
    when (error) {
        is Error.Connectivity -> {
            this.showNoInternetConnectionSnackBar(this.context.errorToString(error)) {
                action()
            }
        }

        else -> this.showSnackBar(this.context.errorToString(error))
    }
}

fun View.showSnackBar(
    message: String?,
    duration: Int = Snackbar.LENGTH_SHORT,
) {
    Snackbar.make(this, message ?: "", duration).apply {
        setAction(R.string.snack_bar_dismiss_message) {
            dismiss()
        }
        show()
    }
}

fun View.showNoInternetConnectionSnackBar(
    message: String,
    isIndefinite: Boolean = true,
    tryAgain: () -> Unit,
) {
    val duration = if (isIndefinite) Snackbar.LENGTH_INDEFINITE else Snackbar.LENGTH_LONG
    Snackbar.make(this, message, duration).apply {
        setAction(R.string.snack_bar_try_again_message) {
            tryAgain()
            dismiss()
        }
        show()
    }
}

fun Context.errorToString(error: Error) = when (error) {
    Error.Connectivity -> getString(R.string.connectivity_error)
    is Error.Server -> getString(R.string.server_error) + error.code
    is Error.Unknown -> getString(R.string.unknown_error) + error.message
    else -> { getString(R.string.generic_error) }
}
