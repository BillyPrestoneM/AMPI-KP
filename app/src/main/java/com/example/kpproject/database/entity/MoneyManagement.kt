package com.example.kpproject.database.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "money_management")
@Parcelize
data class MoneyManagement (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "tipe_transaksi")
    val transactionType: String,
    @ColumnInfo(name = "akun")
    val akun: String,
    @ColumnInfo(name = "kategori")
    val kategori: String,
    @ColumnInfo(name = "deskripsi")
    val deskripsi: String,
    @ColumnInfo(name = "nominal")
    val nominal: Double,
    @ColumnInfo(name = "hari")
    val hari: String,
    @ColumnInfo(name = "tanggal")
    val date: String,
    @ColumnInfo(name = "waktu")
    val time: String
) : Parcelable