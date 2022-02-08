package com.task.noteapp.shared.extension

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

fun Disposable.disposed(by: CompositeDisposable): Disposable = apply { by.add(this) }