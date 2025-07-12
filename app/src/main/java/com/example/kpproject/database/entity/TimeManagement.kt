package com.example.kpproject.database.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "time_management")
@Parcelize
data class TimeManagement (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "nama_tugas")
    val namaTugas: String,
    @ColumnInfo(name = "deskripsi_tugas")
    val deskripsiTugas: String?,
    @ColumnInfo(name = "kategori")
    val kategori: String,
    @ColumnInfo(name = "hari_mulai")
    val hariMulai: String,
    @ColumnInfo(name = "tanggal_mulai")
    val tanggalMulai: String,
    @ColumnInfo(name = "jam_mulai")
    val jamMulai: String,
    @ColumnInfo(name = "hari_selesai")
    val hariSelesai: String?,
    @ColumnInfo(name = "tanggal_selesai")
    val tanggalSelesai: String?,
    @ColumnInfo(name = "jam_selesai")
    val jamSelesai: String?,
    @ColumnInfo(name = "status")
    val status: Boolean = false
) : Parcelable