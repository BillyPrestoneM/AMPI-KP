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
    val transactionType: String, // "Pemasukan" atau "Pengeluaran"

    @ColumnInfo(name = "akun")
    val akun: String, // Tunai, Rekening, Kartu Kredit, E-wallet

    @ColumnInfo(name = "kategori")
    val kategori: String, // Makanan, Pakaian, Gaji, Uang Bulanan, dll.

    @ColumnInfo(name = "deskripsi")
    val deskripsi: String, // Contoh: Beli Nasi Goreng, Gaji Bulanan

    @ColumnInfo(name = "nominal")
    val nominal: Double,

    @ColumnInfo(name = "hari")
    val hari: String,

    @ColumnInfo(name = "tanggal")
    val date: String, // Format: YYYY-MM-DD

    @ColumnInfo(name = "waktu")
    val time: String // Format: HH:MM
) : Parcelable