package com.aa.cap7.listmaker.models

import android.os.Parcel
import android.os.Parcelable

class TaskList(val nameTask : String, val tasks : ArrayList<String> = ArrayList()) : Parcelable {

    // Se o objeto passar um outro objecto Parcel, então
    // extrair os atributos do objeto que seriam o nome
    // da lista e a própria lista.
    constructor(source: Parcel) : this (source.readString()!!, source.createStringArrayList()!!)

    override fun describeContents() = 0

    // Necessário para criar um parcel a partir da TaskList esperada
    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(nameTask)
        dest.writeStringList(tasks)
    }

    companion object CREATOR : Parcelable.Creator<TaskList> {
        override fun createFromParcel(source: Parcel): TaskList = TaskList(source)
        override fun newArray(size: Int): Array<TaskList?> = arrayOfNulls(size)
    }
}